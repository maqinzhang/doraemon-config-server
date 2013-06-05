package com.jd.doraemon.server;

import com.jd.doraemon.core.cluster.ServerInfo;
import com.jd.doraemon.server.rpc.ServerRpcInvoker;

/**
 * @author luolishu
 * 
 */
public abstract class ContainerUtils implements ResourceHolder {
	static ServerContainer container;

	public static ServerContainer getContainer() {
		return container;
	}

	static void setContainer(ServerContainer c) {
		ContainerUtils.container = c;
	}

	public static ServerInfo getLocalSeverInfo(String group) {
		ServerInfo server = groupClustersMap.get(group).getSelf();
		return server;
	}

	public static ServerRpcInvoker getRpcInvoker(String group) {
		return rpcInvokerMap.get(group);
	}
}
