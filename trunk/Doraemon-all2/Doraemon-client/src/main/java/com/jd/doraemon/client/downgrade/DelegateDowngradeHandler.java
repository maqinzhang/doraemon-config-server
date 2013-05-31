/**
 * 
 */
package com.jd.doraemon.client.downgrade;

import java.lang.reflect.Method;

/**
 * @author luolishu
 * 
 */
public class DelegateDowngradeHandler implements DowngradeHandler {
	static final DowngradeHandler exceptionDowngradeHandler = new ExceptionDowngradeHandler();
	static final DowngradeHandler mockScriptDowngradeHandler = new MockScriptDowngradeHandler();

	@Override
	public Object handle(Method method, DowngradeContext context) {
		if (context.getDowngradeType() == null) {
			throw new RuntimeException(
					"down grade error!context of downgradeType must not null!");
		}
		switch (context.getDowngradeType()) {
		case EXCEPTION:
			return exceptionDowngradeHandler.handle(method, context);
		case MOCK:
			return mockScriptDowngradeHandler.handle(method, context);
		case NULL:
			return null;
		}
		return null;
	}

}
