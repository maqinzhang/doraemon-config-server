/**
 * 
 */
package com.jd.doraemon.client.remote;

import java.util.Properties;

import org.jgroups.Address;

import com.jd.doraemon.client.ResourceHolder;
import com.jd.doraemon.client.rpc.RpcInvoker;
import com.jd.doraemon.core.cluster.GroupClusters;

/**
 * @author luolishu
 * 
 */
public class JgroupsRemoteServerService implements RemoteServerService,
		ResourceHolder {
 
	@Override
	public String getGroupFileDigest(String group) {
		RpcInvoker rpcInvoker = this.rpcInvokerMap.get(group);
		Address dest = findServerAddress(group);
		if (dest == null) {
			return null;
		}
		String serverDigest = rpcInvoker.getGroupFileDigest(dest, group);
		return serverDigest;
	}

	private Address findServerAddress(String group) {
		GroupClusters clusters = groupClustersMap.get(group);
		Address address = clusters.getMaster().getAddress();
		return address;
	}

	@Override
	public Properties getGroupProperties(String group) {
		RpcInvoker rpcInvoker = this.rpcInvokerMap.get(group);
		Address dest = findServerAddress(group);
		if (dest == null) {
			return null;
		}
		return rpcInvoker.getGroupProperties(dest, group);
	}
 
	@Override
	public String getServerProperty(String group, String key) {
		RpcInvoker rpcInvoker = this.rpcInvokerMap.get(group);
		Address dest = findServerAddress(group);
		if (dest == null) {
			return null;
		}
		return rpcInvoker.getProperty(dest, group,key);
	}

}
