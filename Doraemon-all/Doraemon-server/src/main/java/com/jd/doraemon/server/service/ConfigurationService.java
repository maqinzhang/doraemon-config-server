package com.jd.doraemon.server.service;

import com.jd.doraemon.core.serialize.ConfigurationItem;

/**
 * @author luolishu
 * 
 */
public interface ConfigurationService {

	public void create(ConfigurationItem entity);

	public void modify(ConfigurationItem entity);

	public void remove(ConfigurationItem entity);

}
