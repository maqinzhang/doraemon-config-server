/**
 * 
 */
package com.jd.doraemon.server.impl;

import com.jd.doraemon.server.EventHandler;
import com.jd.doraemon.server.listener.SnapshotConfigurationListener;
import com.jd.doraemon.server.listener.SynConfigurationListener;

/**
 * @author luolishu
 * 
 */
public class DefaultServerContainer extends AbstractServerContainer {
	ListenerEventHandler eventHandler = new ListenerEventHandler();

	public DefaultServerContainer(String serverName) {
		this.serverName = serverName;
		this.resourceManager = new JgroupsResourceManager();
	}

	@Override
	public EventHandler getEventHandler() {
		eventHandler.listeners.add(new SnapshotConfigurationListener());
		eventHandler.listeners.add(new SynConfigurationListener());
		return eventHandler;
	}

}
