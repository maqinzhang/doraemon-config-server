
package com.jd.doraemon.client;

/**
 * @author luolishu
 *
 */
public abstract class BaseContainer implements ConfigurationContainer{
	public BaseContainer(){
		this.setContainer(this);
	}
	
	public void setContainer(ConfigurationContainer container){
		ConfigurationUtils.setContainer(container);
	}

}
