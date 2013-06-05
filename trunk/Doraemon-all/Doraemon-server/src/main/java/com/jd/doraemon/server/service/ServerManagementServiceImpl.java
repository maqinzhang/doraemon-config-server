package com.jd.doraemon.server.service;

import java.util.List;

import com.jd.doraemon.core.cluster.ServerInfo;
import com.jd.doraemon.core.serialize.ConfigDescription;
import com.jd.doraemon.core.serialize.ConfigDescriptionResult;
import com.jd.doraemon.server.ContainerUtils;
import com.jd.doraemon.server.rpc.ServerRpcInvoker;

public class ServerManagementServiceImpl implements ServerManagementService {

	@Override
	public void removeConfig(String group, String key) {
		ServerRpcInvoker rpcInvoker = ContainerUtils.getRpcInvoker(group);
		rpcInvoker.removeConfig(group, key);
	}

	@Override
	public void setConfig(String group, String key, String value) {
		ServerRpcInvoker rpcInvoker = ContainerUtils.getRpcInvoker(group);
		rpcInvoker.setConfig(group, key, value);
	}

	@Override
	public void setConfig(ServerInfo client, String group, String key,
			String value) {
		ServerRpcInvoker rpcInvoker = ContainerUtils.getRpcInvoker(group);
		rpcInvoker.setConfig(client, group, key, value);
	}

	@Override
	public ConfigDescription getConfigFromMemery(ServerInfo client,
			String group, String key) {
		ServerRpcInvoker rpcInvoker = ContainerUtils.getRpcInvoker(group);
		return rpcInvoker.getConfigFromMemery(client, group, key);
	}

	@Override
	public List<ConfigDescription> getConfigFromMemery(String group, String key) {
		ServerRpcInvoker rpcInvoker = ContainerUtils.getRpcInvoker(group);
		return rpcInvoker.getConfigFromMemery(group, key);
	}

	@Override
	public ConfigDescriptionResult getConfigFromMemery(ServerInfo client,
			String group) {
		ServerRpcInvoker rpcInvoker = ContainerUtils.getRpcInvoker(group);
		return rpcInvoker.getConfigFromMemery(client, group);
	}

	@Override
	public ConfigDescription getConfigFromSnapshot(ServerInfo client,
			String group, String key) {
		ServerRpcInvoker rpcInvoker = ContainerUtils.getRpcInvoker(group);
		return rpcInvoker.getConfigFromSnapshot(client, group, key);
	}

}
