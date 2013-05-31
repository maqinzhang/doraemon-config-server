/**
 * 
 */
package com.jd.doraemon.client.listener;

import com.jd.doraemon.core.event.ConfigurationEvent;


/**
 * @author luolishu
 *
 */
public interface ListenerTaskFactory {
	ConfigurationListenerTask create(ConfigurationListener listener,
			ConfigurationEvent event);
}
