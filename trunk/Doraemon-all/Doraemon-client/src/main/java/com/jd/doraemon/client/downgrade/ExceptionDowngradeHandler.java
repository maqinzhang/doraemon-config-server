/**
 * 
 */
package com.jd.doraemon.client.downgrade;

import java.lang.reflect.Method;

import com.jd.doraemon.client.script.DefaultScriptExecutorFactory;
import com.jd.doraemon.client.script.ScriptExecutorFactory;

/**
 * @author luolishu
 *
 */
public class ExceptionDowngradeHandler implements DowngradeHandler {
	static final ScriptExecutorFactory scriptExecutorFactory = new DefaultScriptExecutorFactory();

	 
	public Object handle(Method method, DowngradeContext context) {
		throw new RuntimeException("downgrade exception,message="+context.getContent()); 
	}

}
