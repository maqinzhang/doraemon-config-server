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
	Address address;// for jgroup
	boolean master;

	public ServerInfo(ServerType serverType, String uuid) {
		this.serverType = serverType;
		this.uuid = uuid;
	}

	public ServerInfo(ServerType serverType) {
		this.serverType = serverType;
		this.uuid = UUID.randomUUID().toString();
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

	public ServerType getServerType() {
		return serverType;
	}

	public void setServerType(ServerType serverType) {
		this.serverType = serverType;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public boolean isClient() {
		return ServerType.CLIENT.equals(serverType);
	}

	public boolean isServer() {
		return ServerType.SEVER.equals(serverType);
	}

	public static enum ServerType {
		CLIENT, SEVER;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServerInfo other = (ServerInfo) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServerInfo [uuid=" + uuid + ", serverType=" + serverType
				+ ", hostName=" + hostName + ", ipAddress=" + ipAddress
				+ ", address=" + address + "]";
	}

}
