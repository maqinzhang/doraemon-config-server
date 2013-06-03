/**
 * 
 */
package com.jd.doraemon.client.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.jgroups.conf.ConfiguratorFactory;
import org.jgroups.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.doraemon.client.BaseClientContainer;
import com.jd.doraemon.client.ConfigurationContainer;
import com.jd.doraemon.client.ConfigurationException;
import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.RemoteConfiguration;
import com.jd.doraemon.client.RemoteReloadable;
import com.jd.doraemon.client.TransferType;
import com.jd.doraemon.client.TransportProtocal;
import com.jd.doraemon.client.factory.ConfigurationFactory;
import com.jd.doraemon.client.factory.DefaultConfigurationFactory;
import com.jd.doraemon.client.factory.ServiceFactory;
import com.jd.doraemon.client.listener.ConfigurationListener;
import com.jd.doraemon.client.listener.EventListenerExecutor;
import com.jd.doraemon.client.receiver.ConfigMessageReceiver;
import com.jd.doraemon.client.remote.RemoteServerService;
import com.jd.doraemon.client.rpc.RpcInvokeService;
import com.jd.doraemon.core.snapshot.FileSnapshot;
import com.jd.doraemon.core.snapshot.FileSnapshotUtils;
import com.jd.doraemon.core.snapshot.Snapshot;

/**
 * @author luolishu
 * 
 */
public class DoraemonConfigurationContainer extends BaseClientContainer
		implements ConfigurationContainer {
	protected static final Logger logger = LoggerFactory
			.getLogger(DoraemonConfigurationContainer.class);

	protected long initialDelay = 20000;
	protected long synPeriodTime = 30000;
	protected ThreadPoolExecutor threadPoolExecutor;
	protected ConfigurationFactory configurationFactory = new DefaultConfigurationFactory();
	protected RemoteConfiguration remoteConfiguration = null;
	protected RemoteServerService remoteServerService;
	protected ConfigMessageReceiver configMessageReceiver;
	protected RpcInvokeService rpcInvokeService;
	protected Map<String, Snapshot> snapshotMap = new HashMap<String, Snapshot>();
	protected List<ConfigurationListener> listeners = new ArrayList<ConfigurationListener>();
	protected TransportProtocal protocal = TransportProtocal.TCP;
	protected TransferType transferType = TransferType.PUSH;
	protected ScheduledExecutorService scheduledThreadPool = Executors
			.newSingleThreadScheduledExecutor(new ThreadFactory() {

				@Override
				public Thread newThread(Runnable r) {
					Thread thread = new Thread(r);
					thread.setDaemon(true);
					thread.setName("syn_remote_group_datas");
					return thread;
				}

			});

	public DoraemonConfigurationContainer(String prop) {
		super();
		Properties properties = new Properties();
		try {
			InputStream configStream = getConfigStream(prop);
			properties.load(configStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setProperties(properties);

		checkParameters();

	}

	private void setProperties(Properties properties) {
		String temp = properties.getProperty("doraemon.application.groups");
		if (StringUtils.isNotBlank(temp)) {
			this.groups.addAll(Arrays.asList(StringUtils.split(temp, ",")));
		}
		temp = properties.getProperty("doraemon.application.server_name");
		if (StringUtils.isNotBlank(temp)) {
			this.serverName = StringUtils.trim(temp);
		}

		String protocalStr = StringUtils.trim(properties
				.getProperty("doraemon.application.protocal"));
		if (StringUtils.isBlank(protocalStr)) {
			this.protocal = TransportProtocal.TCP;
		} else {
			this.protocal = TransportProtocal.valueOf(protocalStr);
		}
		temp = properties.getProperty("doraemon.application.name");
		if (StringUtils.isNotBlank(temp)) {
			this.appName = StringUtils.trim(temp);
		}
		temp = properties.getProperty("doraemon.snapshot.syn_period_time");
		if (StringUtils.isNotBlank(temp)) {
			this.synPeriodTime = Long.valueOf(StringUtils.trim(temp));
		}
		temp = properties.getProperty("doraemon.snapshot.directory");
		if (StringUtils.isNotBlank(temp)) {
			this.directoryPath = StringUtils.trim(temp);
		}
	}

	public static InputStream getConfigStream(String properties)
			throws IOException {
		InputStream configStream = null;

		// Check to see if the properties string is the name of a file.
		try {
			configStream = new FileInputStream(properties);
		} catch (FileNotFoundException fnfe) {
			// the properties string is likely not a file
		} catch (AccessControlException access_ex) {
			// fixes http://jira.jboss.com/jira/browse/JGRP-94
		}

		// Check to see if the properties string is a URL.
		if (configStream == null) {
			try {
				configStream = new URL(properties).openStream();
			} catch (MalformedURLException mre) {
				// the properties string is not a URL
			}
		}

		// Check to see if the properties string is the name of a resource, e.g.
		// udp.xml.
		if (configStream == null && properties.endsWith("properties"))
			configStream = Util.getResourceAsStream(properties,
					ConfiguratorFactory.class);
		return configStream;
	}

	public DoraemonConfigurationContainer(String[] groupsArr,
			String serverName, String address, TransportProtocal protocal) {
		super();
		this.groups.addAll(Arrays.asList(groupsArr));
		this.serverName = serverName;
		this.protocal = (protocal == null ? TransportProtocal.TCP : protocal);
		checkParameters();
	}

	private void checkParameters() {
		if (groups == null || groups.size() <= 0) {
			throw new ConfigurationException(
					"configuration arguments error!no group specified!");
		}
		if (StringUtils.isBlank(serverName)) {
			throw new ConfigurationException(
					"configuration arguments error!no serverName specified!");
		}
	}

	public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
		this.threadPoolExecutor = threadPoolExecutor;
	}

	public void setRemoteServerService(RemoteServerService remoteServerService) {
		this.remoteServerService = remoteServerService;
	}

	@Override
	public ThreadPoolExecutor getThreadPoolExecutor() {
		return threadPoolExecutor;
	}

	@Override
	public RemoteConfiguration getRemoteConfiguration() {
		return remoteConfiguration;
	}

	@Override
	public synchronized void startup(Properties properties) {
		try {
			initConfiguration();
			initLoadSnapShot();
			reloadLocal();// read configuration from local
			initNullGroups();// init the null groups
			initService();
			initMessageReceiver();// init the jgroup channels
			initSynRemote();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//

	}

	private void initService() {
		this.configMessageReceiver = ServiceFactory.createReceiver(protocal,
				transferType);
		this.remoteServerService = ServiceFactory.createRemoteServerService(
				protocal, transferType);
	}

	void initConfiguration() {
		Map<String, GroupConfiguration> groupsMap = createGroups();
		remoteConfiguration = this.configurationFactory.createRemote(groupsMap);
	}

	protected void initMessageReceiver() {
		if (configMessageReceiver != null) {
			configMessageReceiver.init();
		}
	}

	private String getDirectory() {
		return this.directoryPath;
	}

	Map<String, GroupConfiguration> createGroups() {
		Map<String, GroupConfiguration> groupConfigs = new HashMap<String, GroupConfiguration>();
		for (String group : groups) {
			groupConfigs.put(group,
					this.configurationFactory.createGroup(group));
		}
		return groupConfigs;
	}

	private void initLoadSnapShot() {
		File directory = new File(this.getDirectory());
		if (!directory.exists()) {
			directory.mkdirs();
		}
		for (String group : groups) {
			FileSnapshot snapshot = new FileSnapshot(directory, group);
			this.snapshotMap.put(group, snapshot);
		}
	}

	private void initSynRemote() {
		SynWithRemoteTask command = new SynWithRemoteTask();
		scheduledThreadPool.scheduleAtFixedRate(command, initialDelay,
				synPeriodTime, TimeUnit.MILLISECONDS);
	}

	private void initNullGroups() {
		Properties p = new Properties();
		for (String group : groups) {
			GroupConfiguration groupConfiguration = remoteConfiguration
					.getGroup(group);
			if (groupConfiguration == null) {
				((RemoteReloadable) remoteConfiguration).reload(group, p);
			}
		}
	}

	@Override
	public synchronized void shutdown() {
		if (threadPoolExecutor != null) {
			threadPoolExecutor.shutdown();
		}
		if (scheduledThreadPool != null) {
			scheduledThreadPool.shutdown();
		}
		if (configMessageReceiver != null) {
			configMessageReceiver.shutdown();
		}

	}

	@Override
	public synchronized void reloadLocal() {
		Map<String, Snapshot> groupFileMap = FileSnapshotUtils
				.getSnapshots(new File(directoryPath));
		if (groupFileMap == null || groupFileMap.isEmpty()) {
			return;
		}
		for (Map.Entry<String, Snapshot> entry : groupFileMap.entrySet()) {
			try {
				String key = entry.getKey();
				Snapshot snapshot = entry.getValue();
				((RemoteReloadable) remoteConfiguration).reload(key,
						snapshot.getProperties());
				this.snapshotMap.put(key, snapshot);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Snapshot getSnapshot(String group) {
		return this.snapshotMap.get(group);
	}

	private Properties getServerGroupProperties(String group) {
		return remoteServerService.getGroupProperties(group);
	}

	public final synchronized boolean checkDigest(String group) {
		String serverDigest = remoteServerService.getGroupFileDigest(group);
		Snapshot snapshot = getSnapshot(group);
		if (snapshot != null) {
			if (!StringUtils.equals(serverDigest, snapshot.getFileDigest())) {
				snapshot.compareAndflush();
			}
			return StringUtils.equals(serverDigest, snapshot.getFileDigest());
		}
		return false;
	}

	@Override
	public final synchronized void synWithRemote() {
		for (String group : groups) {
			synGroupWithRemote(group);
		}
	}

	public final synchronized void synGroupWithRemote(String group) {
		Properties p = getServerGroupProperties(group);
		((RemoteReloadable) remoteConfiguration).reload(group, p);
	}

	private class SynWithRemoteTask implements Runnable {

		@Override
		public void run() {
			for (String group : groups) {
				try {
					if (!checkDigest(group)) {
						synGroupWithRemote(group);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}



}
