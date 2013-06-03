package com.jd.doraemon.server.listener;

import org.jgroups.JChannel;
import org.jgroups.Message;

import com.jd.doraemon.core.event.ConfigurationEvent;
import com.jd.doraemon.core.header.EventFlagHeader;
import com.jd.doraemon.core.serialize.ConfigurationItem;
import com.jd.doraemon.server.ResourceHolder;

/**
 * @author luolishu
 * 
 */
public class SynConfigurationListener implements ConfigurationListener {

	public void handleEvent(ConfigurationEvent event) {
		Message msg = new Message();
		/*EventFlagHeader header = new EventFlagHeader(
				EventFlagHeader.CONFIG_TYPE);
		msg.putHeader(EventFlagHeader.HEADER_ID, header);*/
		msg.setObject(event);
		ConfigurationItem item = (ConfigurationItem) event.getSources();
		try {
			JChannel jchannel = ResourceHolder.GROUP_MESSAGE_CHANNEL_MAP
					.get(item.getGroup());
			jchannel.send(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
