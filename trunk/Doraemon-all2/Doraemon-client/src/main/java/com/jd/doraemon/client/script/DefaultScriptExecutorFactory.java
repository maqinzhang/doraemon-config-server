/**
 * 
 */
package com.jd.doraemon.client.script;

import com.jd.doraemon.client.downgrade.DowngradeContext;

/**
 * @author luolishu
 * 
 */
public class DefaultScriptExecutorFactory implements ScriptExecutorFactory {
	static final JavascriptScriptExecutor DEFAULT_EXECUTOR = new JavascriptScriptExecutor();

	public ScriptExecutor create(DowngradeContext context) {
		return DEFAULT_EXECUTOR;
	}

}
