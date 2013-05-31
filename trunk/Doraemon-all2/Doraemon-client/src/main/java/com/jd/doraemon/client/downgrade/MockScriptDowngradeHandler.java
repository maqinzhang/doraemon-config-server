/**
 * 
 */
package com.jd.doraemon.client.downgrade;

import java.lang.reflect.Method;

import com.jd.doraemon.client.script.DefaultScriptExecutorFactory;
import com.jd.doraemon.client.script.ScriptContext;
import com.jd.doraemon.client.script.ScriptExecutor;
import com.jd.doraemon.client.script.ScriptExecutorFactory;

/**
 * @author luolishu
 * 
 */
public class MockScriptDowngradeHandler implements DowngradeHandler {
	static final ScriptExecutorFactory scriptExecutorFactory = new DefaultScriptExecutorFactory();

	public Object handle(Method method, DowngradeContext context) {
		Object value = null;
		ScriptContext scriptContext = new ScriptContext();
		scriptContext.setContent(context.getContent());
		scriptContext.setId(context.getId());
		Class<?> returnClaz = method.getReturnType();
		try {

			if (!returnClaz.equals(Void.TYPE)) {
				value = MockInstanceHelper.tryCreateMockInstance(returnClaz);
				scriptContext.put("returnVal", value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ScriptExecutor executor = scriptExecutorFactory.create(context);
		Object scriptVal = null;
		try {
			scriptVal = executor.execute(scriptContext);
		} catch (Exception e) {
			throw new RuntimeException("execute script mock error!script="
					+ context.getContent(), e);
		}
		if (scriptVal != null && !scriptVal.getClass().equals(Void.TYPE)
				&& (returnClaz.isAssignableFrom(scriptVal.getClass()))) {
			return scriptVal;
		}
		return value;
	}
}
