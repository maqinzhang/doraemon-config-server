/**
 * 
 */
package com.jd.doraemon.client.impl;

import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

import com.jd.doraemon.client.BaseContainer;
import com.jd.doraemon.client.ConfigurationContainer;
import com.jd.doraemon.client.RemoteConfiguration;

/**
 * @author luolishu
 *
 */
public class HttpConfigurationContainer extends BaseContainer implements ConfigurationContainer {

	 
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

}
