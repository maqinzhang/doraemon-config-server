/**
 * 
 */
package com.jd.doraemon.server.impl;

import java.util.ArrayList;
import java.util.List;

import com.jd.doraemon.core.event.ConfigurationEvent;
import com.jd.doraemon.server.EventHandler;
import com.jd.doraemon.server.listener.ConfigurationListener;

/**
 * @author luolishu
 * 
 */
public class ListenerEventHandler implements EventHandler {
	List<ConfigurationListener> listeners = new ArrayList<ConfigurationListener>();

	@Override
	public void handle(ConfigurationEvent event) {
		for (ConfigurationListener listener : listeners) {
			listener.handleEvent(event);
		}

	}

}
