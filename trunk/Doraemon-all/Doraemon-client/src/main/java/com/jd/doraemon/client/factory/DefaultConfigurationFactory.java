/**
 * 
 */
package com.jd.doraemon.client.factory;

import java.util.Map;

import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.RemoteConfiguration;
import com.jd.doraemon.client.impl.DefaultGroupConfiguration;
import com.jd.doraemon.client.impl.DefaultRemoteConfiguration;

/**
 * @author luolishu
 *
 */
public class DefaultConfigurationFactory implements ConfigurationFactory {

	 
	@Override
	public GroupConfiguration createGroup(String group) { 
		return new DefaultGroupConfiguration(group);
	}
 
	@Override
	public RemoteConfiguration createRemote(
			Map<String, GroupConfiguration> groupsMap) {
		return new DefaultRemoteConfiguration(groupsMap);
	}

}
