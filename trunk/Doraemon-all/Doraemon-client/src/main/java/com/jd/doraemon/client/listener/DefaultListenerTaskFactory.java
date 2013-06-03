package com.jd.doraemon.client.listener;

import com.jd.doraemon.core.event.ConfigurationEvent;

public class DefaultListenerTaskFactory implements ListenerTaskFactory {

	@Override
	public ConfigurationListenerTask create(ConfigurationListener listener,
			ConfigurationEvent event) {
		return new ConfigurationListenerTask(listener, event);
	}

}
