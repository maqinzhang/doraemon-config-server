
package com.jd.doraemon.client.listener;

import com.jd.doraemon.core.event.ConfigurationEvent;

/**
 * @author luolishu
 *
 */
public interface EventListenerExecutor {
	
	public void execute(ConfigurationListener listener,ConfigurationEvent event);

}
