/**
 * 
 */
package com.jd.doraemon.server;

import java.util.HashMap;
import java.util.Map;

import org.jgroups.JChannel;

import com.jd.doraemon.core.cluster.GroupClusters;
import com.jd.doraemon.core.snapshot.Snapshot;
import com.jd.doraemon.server.rpc.ServerRpcInvoker;

/**
 * @author luolishu
 * 
 */
public interface ResourceHolder {
	static final Map<String, JChannel> GROUP_MESSAGE_CHANNEL_MAP = new HashMap<String, JChannel>();
	static final Map<String, JChannel> RPC_CHANNELS_MAP = new HashMap<String, JChannel>();
	static final Map<String, GroupClusters> groupClustersMap = new HashMap<String, GroupClusters>();
	static final Map<String, ServerRpcInvoker> rpcInvokerMap = new HashMap<String, ServerRpcInvoker>();
	static final Map<String, Snapshot> snapshotMap = new HashMap<String, Snapshot>();
	
}
