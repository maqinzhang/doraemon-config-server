/**
 * 
 */
package com.jd.doraemon.store.database;

import java.util.List;

import com.jd.doraemon.model.ConfigurationItem;
import com.jd.doraemon.store.StoreReadable;
import com.jd.doraemon.store.StoreWritable;

/**
 * @author luolishu
 *
 */
public class DataBaseStore implements StoreReadable, StoreWritable {

	@Override
	public boolean create(ConfigurationItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(ConfigurationItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(ConfigurationItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ConfigurationItem get(String groupId, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigurationItem> listAll(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigurationItem> listPage(String groupId, int pageNo, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
