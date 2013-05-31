/**
 * 
 */
package com.jd.doraemon.store;

import com.jd.doraemon.model.ConfigurationItem;

/**
 * @author luolishu
 *
 */
public interface StoreWritable {
	
	public boolean create(ConfigurationItem item);
	
	public boolean modify(ConfigurationItem item);
	
	public boolean remove(ConfigurationItem item);

}
