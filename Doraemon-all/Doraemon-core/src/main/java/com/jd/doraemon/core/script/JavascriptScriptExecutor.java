/**
 * 
 */
package com.jd.doraemon.core.script;

import java.util.Map;
import java.util.WeakHashMap;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author luolishu
 * 
 */
public class JavascriptScriptExecutor implements ScriptExecutor {
	static final Map<String, CompiledScript> compiledScriptMap = new WeakHashMap<String, CompiledScript>();

	public Object execute(ScriptContext context) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		CompiledScript script = compiledScriptMap.get(context.getId());
		if (script == null) {
			Compilable compilingEngine = (Compilable) engine;
			script = compilingEngine.compile(context.getContent());
			compiledScriptMap.put(context.getId(), script);
		}
		Bindings bindings = engine.createBindings();
		if(context.getValues()!=null){
			for(Map.Entry<String,Object> entry:context.getValues().entrySet()){
				bindings.put(entry.getKey(), entry.getValue());
			}
		}
		
		Object result = script.eval(bindings);
		return result;
	}

}
