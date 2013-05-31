/**
 * 
 */
package com.jd.doraemon.client.inject;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import com.jd.doraemon.client.inject.Property;

/**
 * @author luolishu
 * 
 */
public class Configuration {
	@Property(group = "group1", key = "p1")
	static String property1;
	@Property(group = "group2")
	static String property2;
	@Test
	public void test() throws Exception {
		Field field = Configuration.class.getDeclaredField("property1");
		field.set(Configuration.class.newInstance(), "hello");
		Assert.assertNotNull(field);
		
		Assert.assertEquals("hello", Configuration.property1);
		System.out.println(Configuration.property1);
	}

}
