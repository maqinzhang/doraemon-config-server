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
		DefaultConfigurationContainer container=new DefaultConfigurationContainer(groups,serverName,address);
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
