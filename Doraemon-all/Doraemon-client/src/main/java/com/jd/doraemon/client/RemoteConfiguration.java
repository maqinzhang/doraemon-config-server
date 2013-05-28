package com.jd.doraemon.client;

import java.util.List;

/**
 * @author luolishu
 * 
 */
public interface RemoteConfiguration {
	/** 
	 * @return
	 */
	public List<GroupConfiguration> getAllGroups();
    /** 
     * @param group
     * @return
     */
	public GroupConfiguration getGroup(String group);
    /**  ֵ
     * @param group
     * @param key
     * @return
     */
	public String get(String group, String key);
	/** 
	 * @param key
	 * @return
	 */
	public String get(String key);

}
