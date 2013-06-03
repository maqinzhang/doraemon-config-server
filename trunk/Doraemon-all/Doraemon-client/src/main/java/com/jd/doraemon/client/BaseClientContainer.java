package com.jd.doraemon.client;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jd.doraemon.client.listener.ConfigurationListener;
import com.jd.doraemon.client.listener.EventListenerExecutor;
import com.jd.doraemon.client.listener.ThreadPoolAsynEventListenerExecutor;

/**
 * @author luolishu
 * 
 */
public abstract class BaseClientContainer implements ConfigurationContainer {
	protected String directoryPath = System.getProperty("java.io.tmpdir")
			+ File.separator + "doraemon" +File.separator+"client"+ File.separator + "groups";

	protected Map<String, List<ConfigurationListener>> groupListenersMap = new HashMap<String, List<ConfigurationListener>>();
	protected List<ConfigurationListener> globalListeners = new ArrayList<ConfigurationListener>();
	protected EventListenerExecutor eventListenerExecutor = new ThreadPoolAsynEventListenerExecutor(
			this);

	protected Set<String> groups = new HashSet<String>(
			Arrays.asList(new String[] { "default" }));
	protected String appName = "client.name";
	protected String serverName = "doraemon.config.server";

	public BaseClientContainer() {
		this.setContainer(this);
	}

	public void setContainer(ConfigurationContainer container) {
		ConfigurationUtils.setContainer(container);
	}

	@Override
	public EventListenerExecutor getListenerExecutor() {
		return eventListenerExecutor;
	}

	@Override
	public List<ConfigurationListener> getGroupListeners(String group) {
		return groupListenersMap.get(group);
	}

	public List<ConfigurationListener> getGlobalListeners() {
		return globalListeners;
	}

	public synchronized void addGlobalListener(ConfigurationListener listener) {
		globalListeners.add(listener);
	}

	public synchronized void addGroupListener(String group,
			ConfigurationListener listener) {
		List<ConfigurationListener> listeners = groupListenersMap.get(group);
		if (listeners == null) {
			listeners = new ArrayList<ConfigurationListener>();
			groupListenersMap.put(group, listeners);
		}
		listeners.add(listener);

	}
	@Override
	public void setListenerExcecutor(EventListenerExecutor listenerExcecutor) {
		this.eventListenerExecutor=listenerExcecutor;
	}

	public Set<String> getGroupNames() {
		return groups;
	}

	public String getServerName() {
		return this.serverName;
	}
}
