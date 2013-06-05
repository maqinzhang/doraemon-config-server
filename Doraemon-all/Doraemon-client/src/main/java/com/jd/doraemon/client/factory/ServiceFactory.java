/**
 * 
 */
package com.jd.doraemon.client.factory;

import com.jd.doraemon.client.TransferType;
import com.jd.doraemon.client.TransportProtocal;
import com.jd.doraemon.client.receiver.ResourceManager;
import com.jd.doraemon.client.receiver.JgroupsResourceManager;
import com.jd.doraemon.client.remote.JgroupsRemoteServerService;
import com.jd.doraemon.client.remote.RemoteServerService;

/**
 * @author luolishu
 * 
 */
public class ServiceFactory {
	public static ResourceManager createReceiver(
			TransportProtocal protocal, TransferType transferType) {
		ResourceManager receiver = null;
		switch (protocal) {
		case TCP:
		case UDP:
			receiver = new JgroupsResourceManager();
			break;
		case HTTP:
			break;
		default:
			receiver = new JgroupsResourceManager();
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
			service=new JgroupsRemoteServerService();
			break;
		case HTTP:
			break;
		}
		return service;
	}
}
