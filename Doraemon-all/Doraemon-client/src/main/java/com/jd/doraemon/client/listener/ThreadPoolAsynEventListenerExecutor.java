/**
 * 
 */
package com.jd.doraemon.client.listener;

import java.util.concurrent.ThreadPoolExecutor;

import com.jd.doraemon.client.ConfigurationContainer;
import com.jd.doraemon.core.event.ConfigurationEvent;

/**
 * @author luolishu
 * 
 */
public class ThreadPoolAsynEventListenerExecutor implements
		EventListenerExecutor {

	final ConfigurationContainer container;
	final ListenerTaskFactory listenerTaskFactory = new DefaultListenerTaskFactory();

	public ThreadPoolAsynEventListenerExecutor(ConfigurationContainer container) {
		this.container = container;

	}

	@Override
	public void execute(ConfigurationListener listener, ConfigurationEvent event) {
		ThreadPoolExecutor threadPool = null;
		if (listener instanceof ThreadPoolConfigurationListener) {
			threadPool = ((ThreadPoolConfigurationListener) listener)
					.getThreadPool();
		}
		if (threadPool == null) {
			threadPool = container.getThreadPoolExecutor();
		}
		threadPool.execute(listenerTaskFactory.create(listener, event));
	}

}
