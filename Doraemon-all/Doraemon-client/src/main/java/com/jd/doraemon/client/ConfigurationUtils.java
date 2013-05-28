package com.jd.doraemon.client;

import java.io.File;

/**
 * @author luolishu
 * 
 */
public abstract class ConfigurationUtils {
	static ConfigurationContainer container;

	public static RemoteConfiguration getRemoteConfiguration() {
		final ConfigurationContainer container = getContainer();
		return container.getRemoteConfiguration();
	}
	
	public static GroupConfiguration getGroupConfiguration(final String group){
		return  getRemoteConfiguration().getGroup(group);
	}

	public static ConfigurationContainer getContainer() {
		return container;
	}
    static void setContainer(ConfigurationContainer c){
		ConfigurationUtils.container=c;
	}
	/**
	 * GROUP_
	 * @param file
	 * @return
	 */
	public static String getGroupName(File file){
		String fileName=file.getName();
		String groupName=fileName.split("_")[0];		
		return groupName;
	}

}
