package com.jd.doraemon.server;

import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

import com.jd.doraemon.core.snapshot.Snapshot;

/**
 * @author luolishu
 *
 */
public interface ServerContainer {
	/**
	 *  
	 * @return
	 */
	public ThreadPoolExecutor getThreadPoolExecutor(); 
	
	public Snapshot getSnapshot(String group);
   
	public void startup(Properties properties);
	 
	public void shutdown(); 
	public void reloadLocal();
	/**
	 *  
	 */
	public void pushAllToClients();
	/**
	 *  
	 */
	public void pushToClients(String group);
	 
}
