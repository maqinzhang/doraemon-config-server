/**
 * 
 */
package com.jd.doraemon.client.receiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgroups.JChannel;

import com.jd.doraemon.client.ResourceHolder;
import com.jd.doraemon.client.listener.ConfigurationListener;
import com.jd.doraemon.client.listener.EventListenerExecutor;
import com.jd.doraemon.client.rpc.ClientRpcInvoker;

/**
 * @author luolishu
 * 
 */
public class HttpResourceManager implements ResourceManager, ResourceHolder {
	EventListenerExecutor listenerExecutor;
	List<ConfigurationListener> listeners;
	String serverName;
	String groups[];
	Map<String, List<ConfigurationListener>> groupListnersMap = new HashMap<String, List<ConfigurationListener>>();

	public HttpResourceManager() { 
	}

	@Override
	public void shutdown() {
		for (JChannel jchannel : GROUP_MESSAGE_CHANNEL_MAP.values()) {
			jchannel.close();
		}

	}
	@Override
	public void init() {
		try {
			initMessageGroupChannels();
			initRpcGroupChannels();
			initRpcModule();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	private void initRpcModule() {
		Set<Map.Entry<String, JChannel>> entrySet = RPC_CHANNELS_MAP.entrySet();
		for (Map.Entry<String, JChannel> entry : entrySet) {
			ClientRpcInvoker rpcInvoker = new ClientRpcInvoker(entry.getValue());
			rpcInvokerMap.put(entry.getKey(), rpcInvoker);
		}

	}

	private JChannel createChannel(String channelName,String clusterName) throws Exception{
		JChannel jchannel = new JChannel("tcp.xml");
		jchannel.setName(channelName);
		jchannel.connect(clusterName);
		jchannel.setDiscardOwnMessages(true);
		return jchannel;
	}
	private void initMessageGroupChannels() throws Exception {
		for (String group : groups) {
			String channelName="client." + group;
			String clusterName=serverName + "." + group;
			JChannel jchannel =createChannel(channelName,clusterName); 			
			List<ConfigurationListener> excuteListeners = new ArrayList<ConfigurationListener>();
			if (listeners != null) {
				excuteListeners.addAll(listeners);
			}
			List<ConfigurationListener> groupListeners = groupListnersMap
					.get(group);
			if (groupListeners != null) {
				excuteListeners.addAll(groupListeners);
			}
			ServerConfigMessageListener receiver = new ServerConfigMessageListener(
					excuteListeners, listenerExecutor);
			jchannel.setReceiver(receiver); 
			GROUP_MESSAGE_CHANNEL_MAP.put(group, jchannel); 
		}
	}
	private void initRpcGroupChannels() throws Exception {
		for (String group : groups) {
			String channelName="client.rpc." + group;
			String clusterName=serverName + "." + group;
			JChannel jchannel =createChannel(channelName,clusterName); 
			jchannel =createChannel(channelName,clusterName);
			RPC_CHANNELS_MAP.put(group, jchannel);
			
			
		}
	}

	

}
