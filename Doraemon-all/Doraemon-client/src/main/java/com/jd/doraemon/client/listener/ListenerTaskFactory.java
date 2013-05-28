/**
 * 
 */
package com.jd.doraemon.client.listener;


/**
 * @author luolishu
 *
 */
public interface ListenerTaskFactory {
	ConfigurationListenerTask create(ConfigurationListener listener,
			ConfigurationEvent event);
}
