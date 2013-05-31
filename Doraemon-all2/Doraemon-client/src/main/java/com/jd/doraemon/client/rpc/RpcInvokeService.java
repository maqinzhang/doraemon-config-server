/**
 * 
 */
package com.jd.doraemon.client.rpc;

import java.util.Properties;

import org.jgroups.Address;

/**
 * @author luolishu
 * 
 */
public interface RpcInvokeService {
	public String getGroupFileDigest(Address dest, String group);

	public Properties getGroupProperties(Address dest, String group);
}
