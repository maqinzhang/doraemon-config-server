/**
 * 
 */
package com.jd.doraemon.client.rpc;

import org.jgroups.JChannel;
import org.jgroups.blocks.RpcDispatcher;

/**
 * @author luolishu
 *
 */
public class RpcInvoker {
	static final StatusCommands commands=new StatusCommands();
	RpcDispatcher rpcDispatcher;
	
	public RpcInvoker(JChannel jchannel){
		this.rpcDispatcher=new RpcDispatcher(jchannel,commands);
	}
	

}
