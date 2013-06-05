package com.jd.doraemon.client;

import java.util.Map;

/**
 * @author luolishu
 *
 */
public interface GroupConfiguration {
	 
	
	public String get(String key);
	
	public boolean containsKey(String key);
	
	public Map<String,String> getAll();

}
