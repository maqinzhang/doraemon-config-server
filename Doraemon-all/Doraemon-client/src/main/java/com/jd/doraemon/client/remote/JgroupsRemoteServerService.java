/**
 * 
 */
package com.jd.doraemon.client.remote;

import java.util.Properties;

import org.jgroups.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.doraemon.client.ResourceHolder;
import com.jd.doraemon.client.rpc.ClientRpcInvoker;
import com.jd.doraemon.core.cluster.GroupClusters;

/**
 * @author luolishu
 * 
 */
public class JgroupsRemoteServerService implements RemoteServerService,
		ResourceHolder {
	static Logger logger = LoggerFactory
			.getLogger(JgroupsRemoteServerService.class);

	@Override
	public String getGroupFileDigest(String group) {
		ClientRpcInvoker rpcInvoker = this.rpcInvokerMap.get(group);
		Address dest = findServerAddress(group);
		if (dest == null) {
			return null;
		}
		String serverDigest = rpcInvoker.getGroupFileDigest(dest, group);
		return serverDigest;
	}

	private Address findServerAddress(String group) {
		GroupClusters clusters = groupClustersMap.get(group);
		if (clusters.getMaster() == null) {
			logger.error("Server not found!Check your config Please!group="
					+ group);
			return null;
		}
		Address address = clusters.getMaster().getAddress();
		return address;
	}

	@Override
	public Properties getGroupProperties(String group) {
		ClientRpcInvoker rpcInvoker = this.rpcInvokerMap.get(group);
		Address dest = findServerAddress(group);
		if (dest == null) {
			return null;
		}
		return rpcInvoker.getGroupProperties(dest, group);
	}

	@Override
	public String getServerProperty(String group, String key) {
		ClientRpcInvoker rpcInvoker = this.rpcInvokerMap.get(group);
		Address dest = findServerAddress(group);
		if (dest == null) {
			return null;
		}
		return rpcInvoker.getProperty(dest, group, key);
	}

}
