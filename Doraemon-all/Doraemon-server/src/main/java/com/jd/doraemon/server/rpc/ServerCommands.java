package com.jd.doraemon.server.rpc;

import java.util.Properties;

import com.jd.doraemon.core.cluster.GroupClusters;
import com.jd.doraemon.core.cluster.ServerInfo;
import com.jd.doraemon.server.ContainerUtils;
import com.jd.doraemon.server.ResourceHolder;
 

public class ServerCommands {
	public String getGroupFileDigest(String group){
		return ContainerUtils.getContainer().getSnapshot(group).getFileDigest();
	}
	public Properties getGroupProperties(String group){
		return ContainerUtils.getContainer().getSnapshot(group).getProperties();
	}
	
	public ServerInfo getServerInfo(String group){
		GroupClusters clusters = ResourceHolder.groupClustersMap.get(group);
		return clusters.getSelf();
	}

}
