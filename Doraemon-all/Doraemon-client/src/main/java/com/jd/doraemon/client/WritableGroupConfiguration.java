/**
 * 
 */
package com.jd.doraemon.client;

/**
 * @author luolishu
 * 
 */
public interface WritableGroupConfiguration extends GroupConfiguration {
	public void remove(String key);

	public void set(String key, String value);
}
