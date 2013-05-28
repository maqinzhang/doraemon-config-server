/**
 * 
 */
package com.jd.doraemon.client.downgrade;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author luolishu
 * 
 */
public class MockInstanceHelper {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T tryCreateMockInstance(Class<T> claz) throws Exception{
		if(claz.isAssignableFrom(Map.class)){
			return (T) new HashMap();
		}
		if(claz.isAssignableFrom(List.class)){
			return (T) new ArrayList();
		}
		if(claz.isAssignableFrom(Set.class)){
			return (T) new HashSet();
		}

		Constructor<T> constructor = claz.getConstructor();
		if (constructor != null) {
			return constructor.newInstance();
		}
		return null;
	}
}
