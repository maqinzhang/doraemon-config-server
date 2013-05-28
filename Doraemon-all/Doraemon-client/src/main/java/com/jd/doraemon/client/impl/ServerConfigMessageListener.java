/**
 * 
 */
package com.jd.doraemon.client.impl;

import java.util.List;

import org.jgroups.Message;
import org.jgroups.MessageListener;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import com.jd.doraemon.client.listener.AsynEventListenerExecutor;
import com.jd.doraemon.client.listener.ConfigurationListener;

/**
 * @author luolishu
 * 
 */
public class ServerConfigMessageListener extends ReceiverAdapter implements
		MessageListener {
	private List<ConfigurationListener> listeners;
	private AsynEventListenerExecutor eventListenerExcecutor;

	public ServerConfigMessageListener(List<ConfigurationListener> listeners,
			AsynEventListenerExecutor eventListenerExcecutor) {
		this.listeners = listeners;
		this.eventListenerExcecutor = eventListenerExcecutor;
	}

	@Override
	public void receive(Message msg) {
		// TODO Auto-generated method stub
		System.out.println("===receive message:" + msg.getObject().toString());

	}
	@Override
	public void viewAccepted(View view) {
		System.out.println("===views:" + view.toString());
    }

}
