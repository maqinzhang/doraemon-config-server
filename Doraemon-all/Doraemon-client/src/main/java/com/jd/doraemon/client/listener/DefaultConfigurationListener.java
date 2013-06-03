package com.jd.doraemon.client.listener;

import com.jd.doraemon.client.ConfigurationContainer;
import com.jd.doraemon.client.ConfigurationUtils;
import com.jd.doraemon.client.Reloadable;
import com.jd.doraemon.client.RemoteConfiguration;
import com.jd.doraemon.core.event.ConfigurationEvent;
import com.jd.doraemon.core.event.EventType;
import com.jd.doraemon.core.serialize.ConfigurationItem;
import com.jd.doraemon.core.snapshot.Snapshot;

/**
 * @author luolishu
 * 
 */
public class DefaultConfigurationListener implements ConfigurationListener {

	@Override
	public void handleEvent(ConfigurationEvent event) {
		if (!EventType.CONFIG_CHANGE.equals(event.getEventType())) {
			return;
		}
		ConfigurationItem item = (ConfigurationItem) event.getSources();
		ConfigurationContainer container=ConfigurationUtils.getContainer();
		RemoteConfiguration remoteConfig=container.getRemoteConfiguration();
		String group=item.getGroup();
		String key=item.getConfigValue().getKey();
		Reloadable groupConfig=(Reloadable) remoteConfig.getGroup(group);
		Snapshot snapshot=container.getSnapshot(group);
		switch (item.getActionType()) {
		case DELETE:
			snapshot.remove(key);
			break;
		case CREATE:
			snapshot.setConfig(key, item.getConfigValue().toString());
			break;
		case MODIFIED:
			snapshot.setConfig(key, item.getConfigValue().toString());
			break;
		}

		groupConfig.reload(snapshot.getProperties());
	}

}
