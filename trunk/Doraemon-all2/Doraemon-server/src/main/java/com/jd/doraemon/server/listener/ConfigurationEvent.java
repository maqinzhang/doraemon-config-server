/**
 * 
 */
package com.jd.doraemon.server.listener;

import java.io.Serializable;

/**
 * @author luolishu
 *
 */
public class ConfigurationEvent implements Serializable {
	
	private ConfigActionType actionType;
	private Serializable sources;
	public ConfigActionType getActionType() {
		return actionType;
	}
	public void setActionType(ConfigActionType actionType) {
		this.actionType = actionType;
	}
	public Serializable getSources() {
		return sources;
	}
	public void setSources(Serializable sources) {
		this.sources = sources;
	}
	
	

}
