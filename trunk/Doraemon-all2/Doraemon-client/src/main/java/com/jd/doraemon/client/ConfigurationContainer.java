package com.jd.doraemon.client;

import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

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
	
	public Snapshot getSnapshot(String group);
   
	public void startup(Properties properties);
	/**
	 * �ر�
	 */
	public void shutdown();
	/**
	 * 在本地snapshot中加载
	 */
	public void reloadLocal();
	/**
	 *  
	 */
	public void synWithRemote();
	 
}
