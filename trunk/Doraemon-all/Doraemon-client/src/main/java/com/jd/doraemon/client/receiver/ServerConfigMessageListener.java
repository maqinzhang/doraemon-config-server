package com.jd.doraemon.client.receiver;

import java.util.List;

import org.jgroups.Header;
import org.jgroups.Message;
import org.jgroups.MessageListener;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import com.jd.doraemon.client.listener.ConfigurationListener;
import com.jd.doraemon.client.listener.EventListenerExecutor;
import com.jd.doraemon.client.listener.EventMessageListner;
import com.jd.doraemon.core.event.ConfigurationEvent;
import com.jd.doraemon.core.header.EventFlagHeader;
import com.jd.doraemon.core.serialize.SerializeUtils;

/**
 * @author luolishu
 * 
 */
public class ServerConfigMessageListener extends ReceiverAdapter implements
		MessageListener, EventMessageListner {
	private List<ConfigurationListener> listeners;
	private EventListenerExecutor listenerExcecutor;

	public ServerConfigMessageListener(List<ConfigurationListener> listeners,
			EventListenerExecutor eventListenerExcecutor) {
		this.listeners = listeners;
		this.listenerExcecutor = eventListenerExcecutor;
	}

	@Override
	public void receive(Message msg) {
		/*Header header = msg.getHeader(EventFlagHeader.HEADER_ID);
		if (header == null) {
			return;
		}*/
		 
		System.out.println("================="+msg.getObject());
		if(!(msg.getObject() instanceof ConfigurationEvent)){
			return;
		}
		ConfigurationEvent event = (ConfigurationEvent) msg.getObject();
		receive(event);

	}

	@Override
	public void receive(ConfigurationEvent event) {
		for (ConfigurationListener listener : this.listeners) {
			listenerExcecutor.execute(listener, event);
		}
	}

	@Override
	public void viewAccepted(View view) {
		System.out.println("===views:" + view.toString());
	}

}
