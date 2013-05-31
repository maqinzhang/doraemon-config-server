package com.jd.doraemon.client.script;

import java.util.HashMap;
import java.util.Map;

public class ScriptContext {

	private String id;
	private String content;
	private Map<String, Object> values = new HashMap<String, Object>();

	public void put(String key, Object value) {
		values.put(key, value);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
