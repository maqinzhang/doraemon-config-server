/**
 * 
 */
package com.jd.doraemon.server.impl;

import java.util.Set;

import org.jgroups.JChannel;

import com.jd.doraemon.server.ContainerUtils;
import com.jd.doraemon.server.ResourceManager;
import com.jd.doraemon.server.ServerContainer;
import com.jd.doraemon.server.rpc.RpcInvoker;

/**
 * @author luolishu
 *
 */
public class JgroupsResourceManager implements ResourceManager {
 
	@Override
	public void addGroup(String group) {
		try {
			this.createGroupMessageChannel(group);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.createGroupRpcChannel(group);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.createRpcInvoker(group);
	}
	@Override
	public void init() {
		try {
			initMessageGroupChannels();
			initRpcGroupChannels();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createRpcInvoker(String group){
		if(rpcInvokerMap.containsKey(group))return;
		JChannel channel=RPC_CHANNELS_MAP.get(group);
		RpcInvoker invoker=new RpcInvoker(channel);
		rpcInvokerMap.put(group, invoker);
	}
	private JChannel createChannel(String channelName, String clusterName)
			throws Exception {
		JChannel jchannel = new JChannel("mping2.xml");
		jchannel.setName(channelName);
		jchannel.connect(clusterName);
		jchannel.setDiscardOwnMessages(true);
		return jchannel;
	}
	
	private void createGroupMessageChannel(String group) throws Exception{
		if(GROUP_MESSAGE_CHANNEL_MAP.containsKey(group)){
			return;
		}
		ServerContainer container = ContainerUtils.getContainer(); 
		String serverName=container.getServerName();
		String channelName = "sever." + group;
		String clusterName = serverName + "." + group;
		JChannel jchannel = createChannel(channelName, clusterName);    
		GROUP_MESSAGE_CHANNEL_MAP.put(group, jchannel);
	}
	private void createGroupRpcChannel(String group) throws Exception{
		if(RPC_CHANNELS_MAP.containsKey(group)){
			return;
		}
		ServerContainer container = ContainerUtils.getContainer(); 
		String serverName=container.getServerName();
		String channelName = "sever.rpc." + group;
		String clusterName = serverName + "." + group;
		JChannel jchannel = createChannel(channelName, clusterName);
		jchannel = createChannel(channelName, clusterName);
		RPC_CHANNELS_MAP.put(group, jchannel);
	}


	private void initMessageGroupChannels() throws Exception {
		ServerContainer container = ContainerUtils.getContainer(); 
		Set<String> groups=container.getGroupNames();
		
		for (String group : groups) {
			this.createGroupMessageChannel(group);
		}
	}

	private void initRpcGroupChannels() throws Exception {
		ServerContainer container = ContainerUtils.getContainer(); 
		Set<String> groups=container.getGroupNames(); 
		for (String group : groups) {			
             this.createGroupRpcChannel(group);
		}
	}

	
}
