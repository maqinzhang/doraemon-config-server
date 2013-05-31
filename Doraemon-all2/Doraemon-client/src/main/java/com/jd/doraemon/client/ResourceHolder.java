/**
 * 
 */
package com.jd.doraemon.client;

import java.util.HashMap;
import java.util.Map;

import org.jgroups.JChannel;

import com.jd.doraemon.client.rpc.RpcInvoker;
import com.jd.doraemon.core.cluster.GroupClusters;

/**
 * @author luolishu
 * 
 */
public interface ResourceHolder {
	static final Map<String, JChannel> GROUP_MESSAGE_CHANNEL_MAP = new HashMap<String, JChannel>();
	static final Map<String, JChannel> RPC_CHANNELS_MAP = new HashMap<String, JChannel>();
	static final Map<String, RpcInvoker> rpcInvokerMap = new HashMap<String, RpcInvoker>();
	static final Map<String, GroupClusters> groupClustersMap = new HashMap<String, GroupClusters>();
}
