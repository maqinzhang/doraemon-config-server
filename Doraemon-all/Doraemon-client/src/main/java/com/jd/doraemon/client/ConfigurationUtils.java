package com.jd.doraemon.client;

import com.jd.doraemon.core.cluster.ServerInfo;

/**
 * @author luolishu
 * 
 */
public abstract class ConfigurationUtils implements ResourceHolder {
	static ConfigurationContainer container;

	public static RemoteConfiguration getRemoteConfiguration() {
		final ConfigurationContainer container = getContainer();
		return container.getRemoteConfiguration();
	}

	public static GroupConfiguration getGroupConfiguration(final String group) {
		return getRemoteConfiguration().getGroup(group);
	}

	public static ConfigurationContainer getContainer() {
		return container;
	}

	static void setContainer(ConfigurationContainer c) {
		ConfigurationUtils.container = c;
	}

	public static ServerInfo getLocalSeverInfo(String group) {
		ServerInfo server = groupClustersMap.get(group).getSelf();
		return server;
	}
}
