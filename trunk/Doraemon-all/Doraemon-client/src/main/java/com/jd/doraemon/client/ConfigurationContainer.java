package com.jd.doraemon.client;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import com.jd.doraemon.client.listener.ConfigurationListener;
import com.jd.doraemon.client.listener.EventListenerExecutor;
import com.jd.doraemon.core.snapshot.Snapshot;

/**
 * @author luolishu
 * 
 */
public interface ConfigurationContainer {
	/**
	 * 
	 * @return
	 */
	public ThreadPoolExecutor getThreadPoolExecutor();

	/**
	 * 
	 * @return
	 */
	public RemoteConfiguration getRemoteConfiguration();

	/*public Set<String> getGroupNames();*/

	public Snapshot getSnapshot(String group);

	public void startup(Properties properties);

	public void shutdown();

	public void reloadLocal();

	/**
	 *  
	 */
	public void synWithRemote();
	

	public EventListenerExecutor getListenerExecutor();

	public List<ConfigurationListener> getGlobalListeners();
	
	public List<ConfigurationListener> getGroupListeners(String group);

	public void addGlobalListener(ConfigurationListener listener);

	public void addGroupListener(String group, ConfigurationListener listener);
	
	public void setListenerExcecutor(EventListenerExecutor listenerExcecutor);
	
	public Set<String> getGroupNames();
	
	public String getServerName();

}
