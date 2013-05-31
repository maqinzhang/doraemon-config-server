package com.jd.doraemon.server;


/**
 * @author luolishu
 * 
 */
public abstract class ContainerUtils {
	static ServerContainer container;

	 
	public static ServerContainer getContainer() {
		return container;
	}
    static void setContainer(ServerContainer c){
		ContainerUtils.container=c;
	}
	

}
