/**
 * 
 */
package com.jd.doraemon.client.listener;

import com.jd.doraemon.core.event.ConfigurationEvent;

/**
 * @author luolishu
 *
 */
public class StatusChangeListener implements ConfigurationListener {

	 
	@Override
	public void handleEvent(ConfigurationEvent event) {
		String key=null;
		String value=null;
		switch(event.getActionType()){
		case DELETE:						
			if("booleanFlag".equals(key)){
				StatusConfig.booleanFlag=false;
			}
			break;
		default: 			
			if("booleanFlag".equals(key)){
				StatusConfig.booleanFlag=Boolean.valueOf(true);
			}
			break;
		}

	}

}
