/**
 * 
 */
package com.jd.doraemon.client.rpc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.doraemon.client.ConfigurationUtils;
import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.ResourceHolder;
import com.jd.doraemon.client.WritableGroupConfiguration;
import com.jd.doraemon.client.script.JavascriptScriptExecutor;
import com.jd.doraemon.client.script.ScriptContext;
import com.jd.doraemon.client.script.ScriptExecutor;
import com.jd.doraemon.core.cluster.GroupClusters;
import com.jd.doraemon.core.cluster.ServerInfo;
import com.jd.doraemon.core.serialize.ConfigDescription;
import com.jd.doraemon.core.serialize.ConfigDescriptionResult;
import com.jd.doraemon.core.serialize.ConfigValue;
import com.jd.doraemon.core.serialize.SerializeUtils;

/**
 * @author luolishu
 * 
 */
public class ClientCommands {
	static Logger logger = LoggerFactory.getLogger(ClientCommands.class);
	static final ScriptExecutor executor = new JavascriptScriptExecutor();

	/**
	 * @param group
	 * @param key
	 * @return
	 */
	public String getContent(String group, String key) {
		GroupConfiguration groupConfiguration = ConfigurationUtils
				.getGroupConfiguration(group);
		if (groupConfiguration == null) {
			throw new RuntimeException("group=" + group + " not found!");
		}
		return groupConfiguration.get(key);
	}

	/**
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public Serializable executeScript(String content) throws Exception {
		logger.error("execute script command!script=" + content);
		ScriptContext context = new ScriptContext();
		Object value = executor.execute(context);
		if (value != null) {
			if (value instanceof Serializable) {
				return (Serializable) value;
			} else {
				return value.toString();
			}
		}
		return null;
	}

	/**
	 * @param address
	 */
	public void synWithServer() {
		ConfigurationUtils.getContainer().synWithRemote();
	}

	public void setProperty(String group, String key, String value) {
		ConfigurationUtils.getContainer().getSnapshot(group)
				.setConfig(key, value);
		WritableGroupConfiguration groupConfig = (WritableGroupConfiguration) ConfigurationUtils
				.getContainer().getRemoteConfiguration().getGroup(group);
		groupConfig.set(key, value);

	}

	public String getGroupFileDigest(String group) {
		return ConfigurationUtils.getContainer().getSnapshot(group)
				.getFileDigest();
	}

	public Properties getGroupProperties(String group) {
		return ConfigurationUtils.getContainer().getSnapshot(group)
				.getProperties();
	}

	public ServerInfo getServerInfo(String group) {
		GroupClusters clusters = ResourceHolder.groupClustersMap.get(group);
		return clusters.getSelf();
	}

	public void removeConfig(String group, String key) {
		ConfigurationUtils.getContainer().getSnapshot(group).remove(key);
		WritableGroupConfiguration groupConfig = (WritableGroupConfiguration) ConfigurationUtils
				.getContainer().getRemoteConfiguration().getGroup(group);
		groupConfig.remove(key);
	}

	public void setConfig(String group, String key, String value) {
		ConfigurationUtils.getContainer().getSnapshot(group)
				.setConfig(key, value);
		WritableGroupConfiguration groupConfig = (WritableGroupConfiguration) ConfigurationUtils
				.getContainer().getRemoteConfiguration().getGroup(group);
		groupConfig.set(key, value);
	}

	public ConfigDescription getConfigFromMemery(String group, String key) {
		GroupConfiguration groupConfig = ConfigurationUtils.getContainer()
				.getRemoteConfiguration().getGroup(group);
		String value=groupConfig.get(key);
		if(StringUtils.isBlank(value)){
			return null;
		}
		ConfigDescription item = new ConfigDescription();
		item.setServer(ConfigurationUtils.getLocalSeverInfo(group));
		item.setKey(key);
		item.setValue(groupConfig.get(key));
		item.setGroup(group);
		return item;
	}

	public ConfigDescriptionResult getConfigFromMemery(String group) {
		ConfigDescriptionResult result = new ConfigDescriptionResult();
		GroupConfiguration groupConfig = ConfigurationUtils.getContainer()
				.getRemoteConfiguration().getGroup(group);
		ServerInfo serverInfo = ConfigurationUtils.getLocalSeverInfo(group);
		result.setServerInfo(serverInfo);
		Map<String, String> values = groupConfig.getAll();
		List<ConfigDescription> descList = new ArrayList<ConfigDescription>();
		for (Entry<String, String> entry : values.entrySet()) {
			ConfigDescription item = new ConfigDescription();
			item.setServer(serverInfo);
			item.setKey(entry.getKey());
			item.setValue(entry.getValue());
			item.setGroup(group);
			descList.add(item);
		}
		result.setConfigList(descList);

		return result;
	}

	public ConfigDescription getConfigFromSnapshot(String group, String key) {
		Properties properties = ConfigurationUtils.getContainer()
				.getSnapshot(group).getProperties();
		String value = properties.getProperty(key);
		if (StringUtils.isBlank(value)) {
			return null;
		}
		ConfigDescription item = new ConfigDescription();
		item.setServer(ConfigurationUtils.getLocalSeverInfo(group));
		item.setKey(key);
		item.setValue(SerializeUtils.deSerialize(value, ConfigValue.class)
				.getValue());
		item.setGroup(group);
		return item;
	}

	public ConfigDescriptionResult getGroupConfigFromSnapshot(String group) {
		ConfigDescriptionResult result = new ConfigDescriptionResult();
		ServerInfo serverInfo = ConfigurationUtils.getLocalSeverInfo(group);
		Properties properties = ConfigurationUtils.getContainer()
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
