package com.jd.doraemon.server;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import com.jd.doraemon.core.cluster.ServerInfo;
import com.jd.doraemon.core.cluster.ServerInfo.ServerType;

/**
 * @author luolishu
 * 
 */
public abstract class BaseServerContainer implements ServerContainer {
	protected String directoryPath = System.getProperty("java.io.tmpdir")
			+ File.separator + "doraemon" + File.separator + "server"
			+ File.separator + "groups";

	protected String serverName;
	protected Set<String> groups = new HashSet<String>();
	protected ResourceManager resourceManager;
	protected boolean running = false;

	public BaseServerContainer() {
		this.setContainer(this);
	}

	public void setContainer(ServerContainer container) {
		ContainerUtils.setContainer(container);
	}

	public Set<String> getGroupNames() {
		return groups;
	}

	public String getServerName() {
		return serverName;
	}

	@Override
	public void addGroup(String group) {
		groups.add(group);
		resourceManager.addGroup(group);
	}


}
