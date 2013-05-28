package com.jd.doraemon.client.impl;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luolishu
 *
 */
public class ConfigValue implements Serializable{ 
	private static final long serialVersionUID = -4549663113104125177L;
	private String key;
	private Long version;
	private Date updateTime;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
