/**
 * 
 */
package com.jd.doraemon.core.event;

import java.io.Serializable;

/**
 * @author luolishu
 * 
 */
public class ConfigurationEvent implements Serializable {
	private static final long serialVersionUID = -1812376230997499623L;
	private EventType eventType; 
	
	private Serializable sources;

	public ConfigurationEvent(){}
	public ConfigurationEvent(EventType eventType) {
		this.eventType = eventType;
	}
 

	public Serializable getSources() {
		return sources;
	}

	public void setSources(Serializable sources) {
		this.sources = sources;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}


	@Override
	public String toString() {
		return "ConfigurationEvent [eventType=" + eventType + ", sources="
				+ sources + "]";
	}

}
