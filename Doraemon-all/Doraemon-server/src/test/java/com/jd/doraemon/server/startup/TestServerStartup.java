/**
 * 
 */
package com.jd.doraemon.server.startup;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.junit.Test;

/**
 * @author luolishu
 *
 */
public class TestServerStartup {
	@Test
	public void testSendMessage() throws Exception{
		System.setProperty("java.net.preferIP4Stack", "true");
		String group="group1";
		String serverName="test.config.server";
		JChannel jchannel = new JChannel("tcp.xml");
		jchannel.setName("server." + group);
		jchannel.connect(serverName + "." + group);
		for(int i=0;;i++){
		Message msg=new Message();
		msg.setObject("Hello World!i="+i);
		jchannel.send(msg);
		System.out.println("message Send!i="+i);
		System.out.println(jchannel.getViewAsString()); 
	
		Thread.sleep(1000L);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
