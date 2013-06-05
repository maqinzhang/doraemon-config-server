package com.jd.doraemon.client.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.Reloadable;
import com.jd.doraemon.client.WritableGroupConfiguration;
import com.jd.doraemon.core.serialize.ConfigValue;
import com.jd.doraemon.core.serialize.SerializeUtils;

public class DefaultGroupConfiguration implements GroupConfiguration,
		Reloadable, WritableGroupConfiguration {
	private Map<String, ConfigValue> cachedMap = Collections
			.unmodifiableMap(new HashMap<String, ConfigValue>());
	private String groupName;

	public DefaultGroupConfiguration(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String get(String key) {
		ConfigValue valueObj = cachedMap.get(key);
		if (valueObj == null) {
			return null;
		}
		return valueObj.getValue();
	}

	@Override
	public void set(String key, String value) {
		ConfigValue valueObj = cachedMap.get(key);
		if (valueObj == null) {
			valueObj = new ConfigValue();
			valueObj.setUpdateTime(new Date());
			valueObj.setVersion(1L);
			valueObj.setKey(key);
			cachedMap.put(key, valueObj);
		}
		valueObj.setValue(value);
	}

	@Override
	public boolean containsKey(String key) {
		return cachedMap.containsKey(key);
	}

	private Map<String, ConfigValue> copy() {
		Map<String, ConfigValue> copyMap = new HashMap<String, ConfigValue>();
		copyMap.putAll(cachedMap);
		return copyMap;
	}

	public synchronized void remove(String key) {
		Map<String, ConfigValue> copyMap = copy();
		copyMap.remove(key);
		this.cachedMap = copyMap;
	}

	@Override
	public Map<String, String> getAll() {
		Set<Entry<String, ConfigValue>> entrySet = cachedMap.entrySet();
		Map<String, String> result = new HashMap<String, String>();
		for (Entry<String, ConfigValue> entry : entrySet) {
			result.put(entry.getKey(), entry.getValue().getValue());
		}
		return result;
	}

	public synchronized void reload(Properties properties) {
		Set<Entry<Object, Object>> entrySet = properties.entrySet();
		Map<String, ConfigValue> newMap = new HashMap<String, ConfigValue>();
		for (Entry<Object, Object> entry : entrySet) {
			String key = entry.getKey().toString();
			String v = entry.getValue().toString();
			newMap.put(key, SerializeUtils.deSerialize(v, ConfigValue.class));
		}
		this.cachedMap = newMap;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
