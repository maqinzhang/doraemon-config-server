/**
 * 
 */
package com.jd.doraemon.server.rpc;

import java.util.Properties;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.RpcDispatcher;

/**
 * @author luolishu
 * 
 */
public class RpcInvoker {
	static final ServerCommands commands = new ServerCommands();
	static short i = 0;
	RpcDispatcher rpcDispatcher;
	Class<?> parameterTypes[] = { String.class };
	Class<?> parameterTypes2[] = { String.class, String.class };

	public RpcInvoker(JChannel jchannel) {
		i++;
		this.rpcDispatcher = new RpcDispatcher(jchannel, commands);
	}

	public String getGroupFileDigest(Address dest, String group) {
		try {
			RequestOptions requestOptions = new RequestOptions();

			return rpcDispatcher.callRemoteMethod(dest, "getGroupFileDigest",
					new Object[] { group }, parameterTypes, requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Properties getGroupProperties(Address dest, String group) {

		try {
			RequestOptions requestOptions = new RequestOptions();
			return rpcDispatcher.callRemoteMethod(dest, "getGroupProperties",
					new Object[] { group }, parameterTypes, requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getProperty(Address dest, String group, String key) {

		try {
			RequestOptions requestOptions = new RequestOptions();
			return rpcDispatcher.callRemoteMethod(dest, "getProperty",
					new Object[] { group, key }, parameterTypes2,
					requestOptions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
