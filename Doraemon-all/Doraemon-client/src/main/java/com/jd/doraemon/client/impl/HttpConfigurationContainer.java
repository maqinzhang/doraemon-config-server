/**
 * 
 */
package com.jd.doraemon.client.impl;

import com.jd.doraemon.client.ConfigurationContainer;
import com.jd.doraemon.client.TransportProtocal;

/**
 * @author luolishu
 *
 */
public class HttpConfigurationContainer extends DoraemonConfigurationContainer implements ConfigurationContainer {

	public HttpConfigurationContainer(String[] groups, String serverName,
			String address, TransportProtocal protocal) {
		super(groups, serverName, address, protocal); 
	}

	 

}
