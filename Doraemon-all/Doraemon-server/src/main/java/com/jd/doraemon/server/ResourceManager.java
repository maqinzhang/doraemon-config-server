/**
 * 
 */
package com.jd.doraemon.server;

/**
 * @author luolishu
 *
 */
public interface ResourceManager extends ResourceHolder {
	
	void addGroup(String group);
	
	void init();

}
