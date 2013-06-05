package com.jd.doraemon.server.service;

import java.util.List;

import com.jd.doraemon.core.cluster.ServerInfo;
import com.jd.doraemon.core.serialize.ConfigDescription;
import com.jd.doraemon.core.serialize.ConfigDescriptionResult;

/**
 * @author luolishu
 * 
 */
public interface ServerManagementService {

	public void removeConfig(String group, String key);

	public void setConfig(String group, String key, String value);

	public void setConfig(ServerInfo client, String group, String key,
			String value);

	public ConfigDescription getConfigFromMemery(ServerInfo client,
			String group, String key);

	public List<ConfigDescription> getConfigFromMemery(String group, String key);

	public ConfigDescriptionResult getConfigFromMemery(ServerInfo client,
			String group);

	public ConfigDescription getConfigFromSnapshot(ServerInfo client, String group,
			String key);

}
