package com.jd.doraemon.server.service;

import com.jd.doraemon.core.event.ConfigActionType;
import com.jd.doraemon.core.event.ConfigurationEvent;
import com.jd.doraemon.core.event.EventType;
import com.jd.doraemon.core.serialize.ConfigurationItem;
import com.jd.doraemon.server.ContainerUtils;
import com.jd.doraemon.server.ServerContainer;

/**
 * @author luolishu
 * 
 */
public class DefaultConfigurationService implements ConfigurationService {

	@Override
	public void create(ConfigurationItem entity) {
		ConfigurationEvent event = new ConfigurationEvent(
				EventType.CONFIG_CHANGE);
		entity.setActionType(ConfigActionType.CREATE);
		event.setSources(entity);
		this.handleEvent(event);
	}

	@Override
	public void modify(ConfigurationItem entity) {
		ConfigurationEvent event = new ConfigurationEvent(
				EventType.CONFIG_CHANGE);
		entity.setActionType(ConfigActionType.MODIFIED);
		event.setSources(entity);
		this.handleEvent(event);
	}

	@Override
	public void remove(ConfigurationItem entity) {
		ConfigurationEvent event = new ConfigurationEvent(
				EventType.CONFIG_CHANGE);
		entity.setActionType(ConfigActionType.DELETE);
		event.setSources(entity);
		this.handleEvent(event);
	}

	private void handleEvent(ConfigurationEvent event) {
		ServerContainer container = ContainerUtils.getContainer();
		container.getEventHandler().handle(event);
	}

}
