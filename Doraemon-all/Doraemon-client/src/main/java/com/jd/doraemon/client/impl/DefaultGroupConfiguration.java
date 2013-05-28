package com.jd.doraemon.client.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.SerializeUtils;

public class DefaultGroupConfiguration implements GroupConfiguration {
	private Map<String,ConfigValue> cachedMap=Collections.unmodifiableMap(new HashMap<String,ConfigValue>());

	@Override
	public String get(String key) {
		ConfigValue valueObj=cachedMap.get(key);
		if(valueObj==null){
			return null;
		}
		return valueObj.getValue();
	}
	@Override
	public boolean containsKey(String key) { 
		return cachedMap.containsKey(key);
	}
	private Map<String,ConfigValue> copy(){
		Map<String,ConfigValue> copyMap=new HashMap<String,ConfigValue>();
		copyMap.putAll(cachedMap);
		return copyMap;
	}
	public synchronized void remove(String key){
		Map<String,ConfigValue> copyMap=copy();
		copyMap.remove(key);
		this.cachedMap=copyMap;
	}
	public synchronized void reload(Properties properties){
		Set<Entry<Object, Object>> entrySet=properties.entrySet();
		Map newMap=new HashMap<String,ConfigValue>();
		for(Entry entry:entrySet){
			String key=entry.getKey().toString();
			String v=entry.getValue().toString();
			newMap.put(key, SerializeUtils.deSerialize(v, ConfigValue.class));
		}  
		this.cachedMap=newMap;
	}
	
	

}
