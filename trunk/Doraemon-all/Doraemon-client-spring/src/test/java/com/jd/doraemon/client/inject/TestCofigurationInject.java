/**
 * 
 */
package com.jd.doraemon.client.inject;

import javax.annotation.Resource;

import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.RemoteConfiguration;

/**
 * @author luolishu
 * 
 */
public class TestCofigurationInject {
	@Resource
	private RemoteConfiguration remoteConfiguration;
	@Resource(name = "group1")
	private GroupConfiguration group1Configuration;
	@Resource(name = "group2")
	private GroupConfiguration group2Configuration;

}
