package com.jd.doraemon.core.cluster;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.UUID;

import org.jgroups.Address;

public class ServerInfo implements Serializable {  
	private static final long serialVersionUID = 835564080138875674L;
	String uuid;
	ServerType serverType;
	String hostName;
	InetAddress ipAddress;
	Address address;//for jgroup
	public ServerInfo(ServerType serverType,String uuid){
		this.serverType=serverType;
		this.uuid=uuid;
	}
	public ServerInfo(ServerType serverType){
		this.serverType=serverType;
		this.uuid=UUID.randomUUID().toString();
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static enum ServerType{
		CLIENT,
		SEVER;
	}
	
	
}
