/**
 * 
 */
package com.jd.doraemon.client.script;

import org.junit.Test;

import com.jd.doraemon.client.downgrade.DowngradeContext;
import com.jd.doraemon.client.downgrade.DowngradeHandler;
import com.jd.doraemon.client.downgrade.ExceptionDowngradeHandler;
import com.jd.doraemon.client.service.DemoService;
 

/**
 * @author luolishu
 *
 */
public class TestExceptionScriptHanlder {

	@Test
	public void testExceptionHandler() throws Exception{
		DowngradeHandler handler=new ExceptionDowngradeHandler();
		DowngradeContext context=new DowngradeContext();
		context.setContent("function test(){return new java.lang.RuntimeException(\"\");};test();");
		context.setId("1");
		System.out.println("value is:"+handler.handle(DemoService.class.getMethod("sayHello"), context));
		//handler.handle(DemoService.class.getMethod("jdkMap"), context);
		context.setContent("returnVal.put(\"a\",\"bb\");");
		context.setId("2");
		System.out.println("value is:"+handler.handle(DemoService.class.getMethod("jdkMap"), context));
		context.setContent("function test(){var val=new java.util.HashMap();val.put(\"aaa\",\"bb\");return val;} ;test();");
		context.setId("3");
		System.out.println("value is:"+handler.handle(DemoService.class.getMethod("jdkMap"), context));
		
		context.setContent("returnVal.setName(\"=================\");");
		context.setId("4");
		System.out.println("value is:"+handler.handle(DemoService.class.getMethod("definedPerson"), context));
		context.setContent("function test(){var val=new com.jd.doraemon.service.Person();val.setName(\"=========++++++========\");;return val;} ;test();");
		context.setId("5");
		System.out.println("value is:"+handler.handle(DemoService.class.getMethod("definedPerson"), context));
		
		context.setContent("throw new java.lang.RuntimeException();");
		context.setId("6");
		System.out.println("value is:"+handler.handle(DemoService.class.getMethod("definedPerson"), context));
	}
}
