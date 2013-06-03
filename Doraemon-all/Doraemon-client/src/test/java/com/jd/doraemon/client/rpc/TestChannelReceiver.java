package com.jd.doraemon.client.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.View;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.blocks.RpcDispatcher;
import org.junit.Test;

public class TestChannelReceiver {
	String groups[]={"testgroup1","testgroup2"};
	String serverName="test.server.name";
	TestReceiver receiver=new TestReceiver();
	/*@BeforeClass
	public static void beforeClass(){
		System.setProperty("jgroups.tcpping.initial_hosts", "10.12.212.33[7800]");
	}*/
	@Test
	public void testStartCommand1Client() throws Exception{ 
		List<JChannel> channelList=new ArrayList<JChannel>();
		for (String group : groups) {
			JChannel jchannel = new JChannel("mping2.xml");
			channelList.add(jchannel);
			jchannel.setReceiver(receiver);
			jchannel.setName("client." + group);
			jchannel.connect(serverName + "." + group); 
		}
		Random random=new Random();
		while(true){ 
			JChannel channel=channelList.get(random.nextInt(channelList.size()));
			Message msg=new Message();
			msg.setObject("Helllo .........................................................");
			channel.send(msg);
			Thread.sleep(2000);
		}
	}
	@Test
	public void testStartCommand2Client() throws Exception{ 
		List<JChannel> channelList=new ArrayList<JChannel>(); 
		for (String group : groups) {
			JChannel jchannel = new JChannel("mping2.xml");
			channelList.add(jchannel);
			jchannel.setReceiver(receiver);
			jchannel.setName("server." + group);
			jchannel.connect(serverName + "." + group);
			 
		}
		Random random=new Random();
		while(true){ 
			JChannel channel=channelList.get(random.nextInt(channelList.size()));
			Message msg=new Message();
			msg.setObject("Helllo .........................................................");
			channel.send(msg);
			Thread.sleep(3000);
		}
	}

}
