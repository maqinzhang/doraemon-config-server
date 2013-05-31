/**
 * 
 */
package com.jd.doraemon.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luolishu
 *
 */
public class ConfigurationItem implements Serializable ,Comparable<ConfigurationItem>{ 
	private static final long serialVersionUID = -2015599510450302997L;
	private Long id;
	private Date createTime;
	private Date updateTime;
	
	private String group; 
	private ConfigTypeEnum type;
	private ConfigStatus status;
	private String key;
	private String value;
	private String valueMD5;

	private String description;
	private String operator;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	 
	public ConfigTypeEnum getType() {
		return type;
	}
	public void setType(ConfigTypeEnum type) {
		this.type = type;
	}
	
	public ConfigStatus getStatus() {
		return status;
	}
	public void setStatus(ConfigStatus status) {
		this.status = status;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValueMD5() {
		return valueMD5;
	}
	public void setValueMD5(String valueMD5) {
		this.valueMD5 = valueMD5;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Override
	public int compareTo(ConfigurationItem o) { 
		return (int) (o.id-this.id);
	}
	
	

}
