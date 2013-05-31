/**
 * 
 */
package com.jd.doraemon.client.rpc;

import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.junit.Test;

/**
 * @author luolishu
 * 
 */
public class TestReceiver extends ReceiverAdapter {
	@Test
	public void test(){
		System.out.println(System.getProperty("java.io.tmpdir"));
	}
	public void receive(Message msg) {
		System.out
				.println("=========================================================================");
		System.out.println(msg.getSrc());
		System.out.println(msg.getObject());
		 
	}

	public void viewAccepted(View view) {
		System.out.println("views:"+view);
	}
}
