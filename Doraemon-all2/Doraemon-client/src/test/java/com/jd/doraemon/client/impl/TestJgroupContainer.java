/**
 * 
 */
package com.jd.doraemon.client.impl;

import org.junit.Test;

/**
 * @author luolishu
 *
 */
public class TestJgroupContainer {
	
	@Test
	public void testClientStartUp() throws Exception{
		System.setProperty("java.net.preferIP4Stack", "true");
		String[] groups={"group1","group2"};
		String serverName="test.config.server";
		String address="127.0.0.1";
		DoraemonConfigurationContainer container=new DoraemonConfigurationContainer(groups,serverName,address,null);
		container.startup(null);
		while(true){
			System.out.println(System.in.read());
		}
	}
	
	@Test
	public void testClientStartUp2() throws Exception{
		System.setProperty("java.net.preferIP4Stack", "true");
		String[] groups={"group1","group2"};
		String serverName="test.config.server";
		String address="127.0.0.1";
		DoraemonConfigurationContainer container=new DoraemonConfigurationContainer("prop.properties");
		container.startup(null);
		while(true){
			System.out.println(System.in.read());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
