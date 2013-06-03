/**
 * 
 */
package com.jd.doraemon.server.listener;

import java.util.EventListener;

import com.jd.doraemon.core.event.ConfigurationEvent;

/**
 * @author luolishu
 *
 */
public interface ConfigurationListener extends EventListener{

	public void handleEvent(ConfigurationEvent event);

}

