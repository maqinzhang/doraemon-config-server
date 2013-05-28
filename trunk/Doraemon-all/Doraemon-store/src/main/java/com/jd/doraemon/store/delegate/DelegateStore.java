/**
 * 
 */
package com.jd.doraemon.store.delegate;

import java.util.List;

import com.jd.doraemon.model.ConfigurationItem;
import com.jd.doraemon.store.StoreReadable;
import com.jd.doraemon.store.StoreWritable;

/**
 * @author luolishu
 *
 */
public class DelegateStore implements StoreReadable, StoreWritable {
	
	StoreWritable storeWritable;
	StoreReadable storeReadable; 
	public boolean create(ConfigurationItem item) { 
		return storeWritable.create(item);
	}
 
	public boolean modify(ConfigurationItem item) { 
		return storeWritable.modify(item);
	}
 
	public boolean remove(ConfigurationItem item) { 
		return storeWritable.remove(item);
	}
 
	public ConfigurationItem get(String groupId, String key) { 
		return storeReadable.get(groupId, key);
	}
 
	public List<ConfigurationItem> listAll(String groupId) { 
		return storeReadable.listAll(groupId);
	}
 
	public List<ConfigurationItem> listPage(String groupId, int pageNo, int size) { 
		return storeReadable.listPage(groupId, pageNo, size);
	}

}
