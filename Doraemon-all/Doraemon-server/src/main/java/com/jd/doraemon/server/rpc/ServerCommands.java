package com.jd.doraemon.server.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.jd.doraemon.core.cluster.GroupClusters;
import com.jd.doraemon.core.cluster.ServerInfo;
import com.jd.doraemon.core.serialize.ConfigDescription;
import com.jd.doraemon.core.serialize.ConfigDescriptionResult;
import com.jd.doraemon.core.serialize.ConfigValue;
import com.jd.doraemon.core.serialize.SerializeUtils;
import com.jd.doraemon.server.ContainerUtils;
import com.jd.doraemon.server.ResourceHolder;

public class ServerCommands {
	public String getGroupFileDigest(String group) {
		return ContainerUtils.getContainer().getSnapshot(group).getFileDigest();
	}

	public Properties getGroupProperties(String group) {
		return ContainerUtils.getContainer().getSnapshot(group).getProperties();
	}

	public ServerInfo getServerInfo(String group) {
		GroupClusters clusters = ResourceHolder.groupClustersMap.get(group);
		return clusters.getSelf();
	}

	public void removeConfig(String group, String key) {
		ContainerUtils.getContainer().getSnapshot(group).remove(key);
	}

	public void setConfig(String group, String key, String value) {
		ContainerUtils.getContainer().getSnapshot(group).setConfig(key, value);
	}

	public ConfigDescription getConfigFromSnapshot(String group, String key) {
		Properties properties = ContainerUtils.getContainer()
				.getSnapshot(group).getProperties();
		String value = properties.getProperty(key);
		if (StringUtils.isBlank(value)) {
			return null;
		}
		ConfigDescription item = new ConfigDescription();
		item.setServer(ContainerUtils.getLocalSeverInfo(group));
		item.setKey(key);
		item.setValue(SerializeUtils.deSerialize(value, ConfigValue.class)
				.getValue());
		item.setGroup(group);
		return item;
	}

	public ConfigDescriptionResult getGroupConfigFromSnapshot(String group) {
		ConfigDescriptionResult result = new ConfigDescriptionResult();
		ServerInfo serverInfo = ContainerUtils.getLocalSeverInfo(group);
		Properties properties = ContainerUtils.getContainer()
				.getSnapshot(group).getProperties();
		result.setServerInfo(serverInfo);
		List<ConfigDescription> configList = new ArrayList<ConfigDescription>();
		Set<Entry<Object, Object>> entrySet = properties.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			ConfigDescription item = new ConfigDescription();
			item.setServer(serverInfo);
			item.setKey(entry.getKey().toString());
			item.setValue(SerializeUtils.deSerialize(
					entry.getValue().toString(), ConfigValue.class).getValue());
			item.setGroup(group);
			configList.add(item);
		}
		result.setConfigList(configList);
		return result;
	}

}
