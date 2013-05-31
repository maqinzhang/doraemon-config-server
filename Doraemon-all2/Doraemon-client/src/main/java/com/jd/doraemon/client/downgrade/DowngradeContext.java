/**
 * 
 */
package com.jd.doraemon.client.downgrade;

/**
 * @author luolishu
 * 
 */
public class DowngradeContext {
	private DowngradeTypeEnum downgradeType;
	private String id;
	private String content;

	public DowngradeTypeEnum getDowngradeType() {
		return downgradeType;
	}

	public void setDowngradeType(DowngradeTypeEnum downgradeType) {
		this.downgradeType = downgradeType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
