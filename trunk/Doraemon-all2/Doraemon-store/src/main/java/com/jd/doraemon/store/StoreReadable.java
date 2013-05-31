package com.jd.doraemon.store;

import java.util.List;

import com.jd.doraemon.model.ConfigurationItem;

/**
 * @author luolishu
 * 
 */
public interface StoreReadable {

	ConfigurationItem get(String groupId, String key);

	List<ConfigurationItem> listAll(String groupId);

	List<ConfigurationItem> listPage(String groupId, int pageNo, int size);

}
