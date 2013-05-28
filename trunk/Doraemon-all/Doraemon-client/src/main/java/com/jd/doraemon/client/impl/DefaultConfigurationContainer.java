/**
 * 
 */
package com.jd.doraemon.client.impl;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import org.jgroups.JChannel;

import com.jd.doraemon.client.BaseContainer;
import com.jd.doraemon.client.ConfigurationContainer;
import com.jd.doraemon.client.ConfigurationUtils;
import com.jd.doraemon.client.RemoteConfiguration;
import com.jd.doraemon.client.listener.AsynEventListenerExecutor;
import com.jd.doraemon.client.listener.ConfigurationListener;
import com.jd.doraemon.client.rpc.RpcInvoker;

/**
 * @author luolishu
 * 
 */
public class DefaultConfigurationContainer extends BaseContainer implements
		ConfigurationContainer {
	private String groups[];
	private String serverName;
	private ThreadPoolExecutor threadPoolExecutor;
	private DefaultRemoteConfiguration remoteConfiguration = new DefaultRemoteConfiguration();
	private Map<String, JChannel> groupChanels = new HashMap<String, JChannel>();
	private Map<String, RpcInvoker> rpcInvokerMap = new HashMap<String, RpcInvoker>();
	private List<ConfigurationListener> listeners = new ArrayList<ConfigurationListener>();
	private AsynEventListenerExecutor eventListenerExcecutor;

	public DefaultConfigurationContainer(String[] groups, String serverName,
			String address) {
		super();
		this.groups = groups;
		this.serverName = serverName;
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
			initGroupChannels();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
		initRpcModule();

	}

	private void initGroupChannels() throws Exception {
		for (String group : groups) {
			JChannel jchannel = new JChannel("tcp.xml");
			jchannel.setName("client." + group);
			jchannel.connect(serverName + "." + group);
			groupChanels.put(group, jchannel);
			ServerConfigMessageListener receiver = new ServerConfigMessageListener(
					listeners, eventListenerExcecutor);
			jchannel.setReceiver(receiver);
		}
	}

	/**
	 * 
	 */
	private void initRpcModule() {
		Set<Map.Entry<String, JChannel>> entrySet = groupChanels.entrySet();
		for (Map.Entry<String, JChannel> entry : entrySet) {
			RpcInvoker rpcInvoker = new RpcInvoker(entry.getValue());
			rpcInvokerMap.put(entry.getKey(), rpcInvoker);
		}

	}

	@Override
	public synchronized void shutdown() {
		if (threadPoolExecutor != null) {
			threadPoolExecutor.shutdown();
		}
		for (JChannel jchannel : groupChanels.values()) {
			jchannel.close();
		}
	}

	@Override
	public synchronized void reloadLocal() {
		List<File> groupFileList = null;
		for (File file : groupFileList) {
			Properties p = new Properties();
			try {
				p.load(new FileReader(file));
				String group = ConfigurationUtils.getGroupName(file);
				remoteConfiguration.reload(group, p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private Properties getRemoteProperties(String group) {
		return null;// TODO Auto-generated method stub
	}

	@Override
	public synchronized void synWithRemote() {
		for (String group : groups) {
			Properties p = getRemoteProperties(group);
			remoteConfiguration.reload(group, p);
		}
	}

}
