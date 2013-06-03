
package com.jd.doraemon.server;

import com.jd.doraemon.core.event.ConfigurationEvent;

/**
 * @author luolishu
 *
 */
public interface EventHandler {
	
	public void handle(ConfigurationEvent event);

}
