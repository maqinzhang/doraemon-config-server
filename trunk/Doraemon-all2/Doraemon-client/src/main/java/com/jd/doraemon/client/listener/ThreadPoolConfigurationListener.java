package com.jd.doraemon.client.listener;

import java.util.concurrent.ThreadPoolExecutor;

public interface ThreadPoolConfigurationListener extends ConfigurationListener {
	public ThreadPoolExecutor getThreadPool();

}
