package com.jd.doraemon.client;

import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

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
