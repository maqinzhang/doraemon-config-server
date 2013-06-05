/**
 * 
 */
package com.jd.doraemon.client.rpc;

import java.util.List;
import java.util.Properties;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.blocks.RpcDispatcher;
import org.jgroups.util.RspList;

import com.jd.doraemon.core.cluster.ServerInfo;

/**
 * @author luolishu
 * 
 */
public class ClientRpcInvoker {
	static final ClientCommands commands = new ClientCommands();
	static short i = 0;
	RpcDispatcher rpcDispatcher;
	Class<?> parameterTypes[] = { String.class };
	Class<?> parameterTypes2[] = { String.class, String.class };

	public ClientRpcInvoker(JChannel jchannel) {
		i++;
		this.rpcDispatcher = new RpcDispatcher(jchannel, commands);
	}

	public String getGroupFileDigest(Address dest, String group) {
		try {
			rpcDispatcher.start();
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_FIRST, 3000);

			return rpcDispatcher.callRemoteMethod(dest, "getGroupFileDigest",
					new Object[] { group }, parameterTypes, requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rpcDispatcher.stop();
		}
		return null;
	}

	public Properties getGroupProperties(Address dest, String group) {

		try {
			rpcDispatcher.start();
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_FIRST, 3000);
			return rpcDispatcher.callRemoteMethod(dest, "getGroupProperties",
					new Object[] { group }, parameterTypes, requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rpcDispatcher.stop();
		}
		return null;
	}

	public String getProperty(Address dest, String group, String key) {

		try {
			rpcDispatcher.start();
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_FIRST, 3000);
			return rpcDispatcher.callRemoteMethod(dest, "getProperty",
					new Object[] { group, key }, parameterTypes2,
					requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rpcDispatcher.stop();
		}
		return null;
	}

	public List<ServerInfo> getAllServerInfos(String group) {
		try {
			rpcDispatcher.start();
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_ALL, 3000);

			RspList<ServerInfo> responseList = rpcDispatcher.callRemoteMethods(
					rpcDispatcher.getChannel().getView().getMembers(),
					"getServerInfo", new Object[] { group }, parameterTypes,
					requestOptions);

			if (responseList != null) {
				return responseList.getResults();
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rpcDispatcher.stop();
		}
		return null;

	}

}
