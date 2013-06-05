/**
 * 
 */
package com.jd.doraemon.server.rpc;

import java.util.List;
import java.util.Properties;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.blocks.RpcDispatcher;
import org.jgroups.util.RspList;

import com.jd.doraemon.core.cluster.ServerInfo;
import com.jd.doraemon.core.serialize.ConfigDescription;
import com.jd.doraemon.core.serialize.ConfigDescriptionResult;

/**
 * @author luolishu
 * 
 */
public class ServerRpcInvoker {
	static final ServerCommands commands = new ServerCommands();
	static short i = 0;
	static final long BATCH_TIME_OUT=30000;
	static final long SINGLE_TIME_OUT=10000;
	RpcDispatcher rpcDispatcher;
	Class<?> parameterTypes[] = { String.class };
	Class<?> parameterTypes2[] = { String.class, String.class };
	Class<?> parameterTypes3[] = { String.class, String.class, String.class };

	public ServerRpcInvoker(JChannel jchannel) {
		i++;
		this.rpcDispatcher = new RpcDispatcher(jchannel, commands);
	}

	public String getGroupFileDigest(Address dest, String group) {
		try {
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_FIRST, SINGLE_TIME_OUT);
			rpcDispatcher.start();
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
					ResponseMode.GET_FIRST, SINGLE_TIME_OUT);
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
					ResponseMode.GET_FIRST, SINGLE_TIME_OUT);
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
					ResponseMode.GET_ALL, BATCH_TIME_OUT);

			RspList<ServerInfo> responseList = rpcDispatcher.callRemoteMethods(
					rpcDispatcher.getChannel().getView().getMembers(),
					"getServerInfo", new Object[] { group }, parameterTypes,
					requestOptions);
			if (responseList != null) {
				return responseList.getResults();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rpcDispatcher.stop();
		}
		return null;

	}

	public void removeConfig(String group, String key) {
		RequestOptions requestOptions = new RequestOptions();
		try {
			rpcDispatcher.callRemoteMethods(rpcDispatcher.getChannel()
					.getView().getMembers(), "removeConfig", new Object[] {
					group, key }, parameterTypes2, requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setConfig(String group, String key, String value) {
		RequestOptions requestOptions = new RequestOptions();
		try {
			rpcDispatcher.callRemoteMethods(rpcDispatcher.getChannel()
					.getView().getMembers(), "setConfig", new Object[] { group,
					key, value }, parameterTypes3, requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setConfig(ServerInfo client, String group, String key,
			String value) {

		RequestOptions requestOptions = new RequestOptions();
		try {
			rpcDispatcher.callRemoteMethod(client.getAddress(), "setConfig",
					new Object[] { group, key, value }, parameterTypes3,
					requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<ConfigDescription> getConfigFromMemery(String group, String key) {
		try {
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_ALL, SINGLE_TIME_OUT);

			RspList<ConfigDescription> responseList = rpcDispatcher
					.callRemoteMethods(rpcDispatcher.getChannel().getView()
							.getMembers(), "getConfigFromMemery", new Object[] {
							group, key }, parameterTypes2, requestOptions);
			return responseList.getResults();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ConfigDescription getConfigFromMemery(ServerInfo client,
			String group, String key) {
		try {
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_FIRST, SINGLE_TIME_OUT);

			ConfigDescription responseResult = rpcDispatcher.callRemoteMethod(
					client.getAddress(), "getConfigFromMemery", new Object[] {
							group, key }, parameterTypes2, requestOptions);
			if (responseResult != null) {
				return responseResult;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	} 

	public ConfigDescriptionResult getConfigFromMemery(ServerInfo client,
			String group) {
		try {
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_FIRST, SINGLE_TIME_OUT);

			ConfigDescriptionResult responseList = rpcDispatcher
					.callRemoteMethod(client.getAddress(),
							"getConfigFromMemery", new Object[] { group },
							parameterTypes, requestOptions);
			if (responseList != null) {
				return responseList;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<ConfigDescriptionResult> getConfigFromMemery(String group) {
		try {
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_ALL, BATCH_TIME_OUT);

			RspList<ConfigDescriptionResult> responseList = rpcDispatcher
					.callRemoteMethods(rpcDispatcher.getChannel().getView()
							.getMembers(), "getConfigFromMemery",
							new Object[] { group }, parameterTypes,
							requestOptions);
			if (responseList != null) {
				return responseList.getResults();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ConfigDescription getConfigFromSnapshot(ServerInfo client,
			String group, String key) {
		try {
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_FIRST, SINGLE_TIME_OUT);
			return rpcDispatcher.callRemoteMethod(client.getAddress(),
					"getConfigFromSnapshot", new Object[] { group, key },
					parameterTypes2, requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ConfigDescriptionResult getGroupConfigFromSnapshot(
			ServerInfo client, String group) {
		try {
			RequestOptions requestOptions = new RequestOptions(
					ResponseMode.GET_FIRST, SINGLE_TIME_OUT);
			return rpcDispatcher.callRemoteMethod(client.getAddress(),
					"getGroupConfigFromSnapshot", new Object[] { group },
					parameterTypes, requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
