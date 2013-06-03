/**
 * 
 */
package com.jd.doraemon.core.serialize;

import java.io.Serializable;

import com.jd.doraemon.core.event.ConfigActionType;

/**
 * @author luolishu
 *
 */
public class ConfigurationItem implements Serializable{ 
	private static final long serialVersionUID = 2196946862653943021L;
	private ConfigActionType actionType;
	private String group;
	private ConfigValue configValue;
	public  ConfigurationItem(){}
	public ConfigurationItem(String group){
		this.group=group;
	}
	public ConfigActionType getActionType() {
		return actionType;
	}
	public void setActionType(ConfigActionType actionType) {
		this.actionType = actionType;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public ConfigValue getConfigValue() {
		return configValue;
	}
	public void setConfigValue(ConfigValue configValue) {
		this.configValue = configValue;
	}
	@Override
	public String toString() {
		return "ConfigurationItem [actionType=" + actionType + ", group="
				+ group + ", configValue=" + configValue + "]";
	}
	
}
