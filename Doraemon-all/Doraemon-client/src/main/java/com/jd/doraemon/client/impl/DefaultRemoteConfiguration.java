/**
 * 
 */
package com.jd.doraemon.client.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.RemoteConfiguration;

/**
 * @author luolishu
 * 
 */
public class DefaultRemoteConfiguration implements RemoteConfiguration {
	Map<String, GroupConfiguration> groupsMap = null;

	@Override
	public List<GroupConfiguration> getAllGroups() {
		return new ArrayList<GroupConfiguration>(groupsMap.values());
	}

	@Override
	public GroupConfiguration getGroup(String group) {
		return groupsMap.get(group);
	}

	@Override
	public String get(String group, String key) {
		GroupConfiguration groupConfig = getGroup(group);
		if (groupConfig == null)
			return null;
		return groupConfig.get(key);
	}

	@Override
	public String get(String key) {
		String content = null;
		for (GroupConfiguration group : groupsMap.values()) {
			content = group.get(key);
			if (content != null) {
				return content;
			}
		}
		return content;
	}

	public synchronized void reload(String group, Properties properties) {
		GroupConfiguration groupConfig = getGroup(group);
		if (groupConfig != null) {
			((DefaultGroupConfiguration) groupConfig).reload(properties);
		} else {
			DefaultGroupConfiguration newConfig = new DefaultGroupConfiguration();
			groupsMap.put(group, newConfig);
			newConfig.reload(properties);
		}

	}

}
