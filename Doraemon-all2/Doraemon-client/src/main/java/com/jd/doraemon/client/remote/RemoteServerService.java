package com.jd.doraemon.client.remote;

import java.util.Properties;

/**
 * @author luolishu
 * 
 */
public interface RemoteServerService {
	String getGroupFileDigest(String group);

	Properties getGroupProperties(String group);

	String getServerProperty(String group, String key);
}
