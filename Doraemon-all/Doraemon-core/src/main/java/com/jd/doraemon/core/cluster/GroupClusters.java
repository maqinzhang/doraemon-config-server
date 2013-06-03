package com.jd.doraemon.core.cluster;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author luolishu
 * 
 */
public class GroupClusters implements Serializable {
	private static final long serialVersionUID = -7585796504454281155L;
	private String name;
	private ServerInfo self;
	private ServerInfo master;
	private Set<ServerInfo> servers = new HashSet<ServerInfo>();
	private Set<ServerInfo> clients = new HashSet<ServerInfo>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServerInfo getSelf() {
		return self;
	}

	public void setSelf(ServerInfo self) {
		this.self = self;
	}

	public ServerInfo getMaster() {
		return master;
	}

	public void setMaster(ServerInfo master) {
		this.master = master;
	}

	public void addConfigClient(ServerInfo server) {
		this.clients.add(server);
	}

	public void addConfigSever(ServerInfo server) {
		this.servers.add(server);
	}

	public Set<ServerInfo> getServers() {
		return servers;
	}

	public void setServers(Set<ServerInfo> servers) {
		this.servers = servers;
	}

	public Set<ServerInfo> getClients() {
		return clients;
	}

	public void setClients(Set<ServerInfo> clients) {
		this.clients = clients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		GroupClusters other = (GroupClusters) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
