/**
 * 
 */
package com.jd.doraemon.client.factory;

import com.jd.doraemon.client.TransferType;
import com.jd.doraemon.client.TransportProtocal;
import com.jd.doraemon.client.receiver.ConfigMessageReceiver;
import com.jd.doraemon.client.receiver.PushConfigMessageReceiver;
import com.jd.doraemon.client.remote.RemoteServerService;

/**
 * @author luolishu
 * 
 */
public class ServiceFactory {
	public static ConfigMessageReceiver createReceiver(
			TransportProtocal protocal, TransferType transferType) {
		ConfigMessageReceiver receiver = null;
		switch (protocal) {
		case TCP:
		case UDP:
		case HTTP:
			break;
	    default:
	    	receiver=new PushConfigMessageReceiver();
	    	break;
		}
		return receiver;

	}

	public static RemoteServerService createRemoteServerService(
			TransportProtocal protocal, TransferType transferType) {
		RemoteServerService service = null;
		switch (protocal) {
		case TCP:
		case UDP:
		case HTTP:
			break;
		}
		return service;
	}
}
