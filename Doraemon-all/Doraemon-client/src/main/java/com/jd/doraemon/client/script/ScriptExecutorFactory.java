/**
 * 
 */
package com.jd.doraemon.client.script;

import com.jd.doraemon.client.downgrade.DowngradeContext;

/**
 * @author luolishu
 * 
 */
public interface ScriptExecutorFactory {

	ScriptExecutor create(DowngradeContext context);

}
