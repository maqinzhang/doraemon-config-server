/**
 * 
 */
package com.jd.doraemon.client.rpc;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.doraemon.client.ConfigurationUtils;
import com.jd.doraemon.client.GroupConfiguration;
import com.jd.doraemon.client.script.JavascriptScriptExecutor;
import com.jd.doraemon.client.script.ScriptContext;
import com.jd.doraemon.client.script.ScriptExecutor;

/**
 * @author luolishu
 * 
 */
public class StatusCommands {
	static Logger logger = LoggerFactory.getLogger(StatusCommands.class);
	static final ScriptExecutor executor = new JavascriptScriptExecutor();
    /** 
     * @param group
     * @param key
     * @return
     */
	public String getContent(String group, String key) {
		GroupConfiguration groupConfiguration = ConfigurationUtils
				.getGroupConfiguration(group);
		if (groupConfiguration == null) {
			throw new RuntimeException("group=" + group + " not found!");
		}
		return groupConfiguration.get(key);
	}
    /** 
     * @param content
     * @return
     * @throws Exception
     */
	public Serializable executeScript(String content) throws Exception {
		logger.error("execute script command!script=" + content);
		ScriptContext context = new ScriptContext();
		Object value = executor.execute(context);
		if (value != null) {
			if (value instanceof Serializable) {
				return (Serializable) value;
			} else {
				return value.toString();
			}
		}
		return null;
	}
	/** 
	 * @param address
	 */
	public void synWithServer(){
		ConfigurationUtils.getContainer().synWithRemote();
	}

}
