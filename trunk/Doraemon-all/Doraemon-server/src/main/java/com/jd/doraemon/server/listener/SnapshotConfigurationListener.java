/**
 * 
 */
package com.jd.doraemon.server.listener;

import com.jd.doraemon.core.event.ConfigurationEvent;
import com.jd.doraemon.core.event.EventType;
import com.jd.doraemon.core.serialize.ConfigurationItem;
import com.jd.doraemon.core.snapshot.Snapshot;
import com.jd.doraemon.server.ContainerUtils;

/**
 * @author luolishu
 * 
 */
public class SnapshotConfigurationListener implements ConfigurationListener {

	public void handleEvent(ConfigurationEvent event) {
		if (!EventType.CONFIG_CHANGE.equals(event.getEventType())) {
			return;
		}
		ConfigurationItem item = (ConfigurationItem) event.getSources();
		String key=item.getConfigValue().getKey();
		Snapshot snapshot=ContainerUtils.getContainer().getSnapshot(item.getGroup());
		switch(item.getActionType()){
		case CREATE: 
		case MODIFIED:
			snapshot.setConfig(key, item.getConfigValue().toString());
			break;
		case DELETE:
			snapshot.remove(key);
			break;
			
		}

	}

}
