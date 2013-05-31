package com.jd.doraemon.client;


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
	

}
