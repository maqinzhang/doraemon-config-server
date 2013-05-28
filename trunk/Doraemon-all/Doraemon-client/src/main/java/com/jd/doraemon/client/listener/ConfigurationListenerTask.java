/**
 * 
 */
package com.jd.doraemon.client.listener;

/**
 * @author luolishu
 *
 */
public class ConfigurationListenerTask implements Runnable{
	private final ConfigurationListener listener;
	private final ConfigurationEvent event;
	public ConfigurationListenerTask(ConfigurationListener listener,
			ConfigurationEvent event){
		this.listener=listener;
		this.event=event;
	}

	@Override
	public void run() { 
		listener.handleEvent(event);
	}
	
}
