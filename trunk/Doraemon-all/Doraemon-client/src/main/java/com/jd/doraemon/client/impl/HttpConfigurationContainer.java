/**
 * 
 */
package com.jd.doraemon.client.impl;

import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

import com.jd.doraemon.client.BaseClientContainer;
import com.jd.doraemon.client.ConfigurationContainer;
import com.jd.doraemon.client.RemoteConfiguration;
import com.jd.doraemon.core.snapshot.Snapshot;

/**
 * @author luolishu
 *
 */
public class HttpConfigurationContainer extends BaseClientContainer implements ConfigurationContainer {

	 
	@Override
	public ThreadPoolExecutor getThreadPoolExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	 
	@Override
	public RemoteConfiguration getRemoteConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	 
	@Override
	public void startup(Properties properties) {
		// TODO Auto-generated method stub

	}
 
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}
 
	@Override
	public void reloadLocal() {
		// TODO Auto-generated method stub

	}
 
	@Override
	public void synWithRemote() {
		// TODO Auto-generated method stub

	}


	@Override
	public Snapshot getSnapshot(String group) {
		// TODO Auto-generated method stub
		return null;
	}

}
