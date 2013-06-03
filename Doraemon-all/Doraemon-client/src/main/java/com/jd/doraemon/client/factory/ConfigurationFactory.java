/**
 * 
 */
package com.jd.doraemon.client.factory;

import java.util.Map;

import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.RemoteConfiguration;

/**
 * @author luolishu
 *
 */
public interface ConfigurationFactory {

	GroupConfiguration createGroup(String group);
	
	RemoteConfiguration createRemote(Map<String, GroupConfiguration> groupsMap);
}
