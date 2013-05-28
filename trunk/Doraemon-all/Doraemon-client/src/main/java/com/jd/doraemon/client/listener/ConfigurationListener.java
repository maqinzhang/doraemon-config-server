package com.jd.doraemon.client.listener;

import java.util.EventListener;

/**
 * @author luolishu
 * 
 */
public interface ConfigurationListener extends EventListener{

	public void handleEvent(ConfigurationEvent event);

}
