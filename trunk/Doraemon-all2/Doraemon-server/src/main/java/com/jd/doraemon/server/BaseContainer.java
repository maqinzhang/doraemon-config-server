
package com.jd.doraemon.server;

/**
 * @author luolishu
 *
 */
public abstract class BaseContainer implements ServerContainer{
	public BaseContainer(){
		this.setContainer(this);
	}
	
	public void setContainer(ServerContainer container){
		ContainerUtils.setContainer(container);
	}

}
