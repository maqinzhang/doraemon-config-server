package com.jd.doraemon.client.downgrade;

import java.lang.reflect.Method;

/**
 * @author luolishu
 *
 */
public interface DowngradeHandler {

	public Object handle(Method method,DowngradeContext context);
}
