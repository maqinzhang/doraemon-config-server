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
import org.jgroups.blocks.mux.MuxRpcDispatcher;
import org.junit.Test;

public class TestChannelRpc {
	String groups[] = { "testgroup1", "testgroup2" };
	String serverName = "test.server.name";
	TestReceiver receiver = new TestReceiver();

	/*
	 * @BeforeClass public static void beforeClass(){
	 * System.setProperty("jgroups.tcpping.initial_hosts",
	 * "10.12.212.33[7800]"); }
	 */
	@Test
	public void testStartCommand1Client() throws Exception {
		List<RpcDispatcher> dispatcherList = new ArrayList<RpcDispatcher>();
		List<JChannel> channelList = new ArrayList<JChannel>();
		Command1 command1 = new Command1();
		short i = 0;
		for (String group : groups) {
			i++;
			JChannel jchannel = new JChannel("mping2.xml");
			channelList.add(jchannel);
			jchannel.setReceiver(receiver);
			jchannel.setName("client." + group);
			jchannel.connect(serverName + "." + group);
			dispatcherList.add(new MuxRpcDispatcher(i, jchannel, null,
					null, command1));
		}
		Random random = new Random();
		while (true) {
			for (RpcDispatcher rpcDispatcher : dispatcherList) {
				rpcDispatcher.start();
				RequestOptions requestOptions = new RequestOptions(
						ResponseMode.GET_FIRST, 1000);
				View view = rpcDispatcher.getChannel().getView();

				Address dest = view.getMembers().get(
						random.nextInt(view.getMembers().size()));
				System.out.println("command1 view:" + view);
				System.out.println(dest.toString() + " ," + dest);
				String result = rpcDispatcher.callRemoteMethod(dest, "hello",
						null, null, requestOptions);
				System.out.println(result);
				rpcDispatcher.stop();
			}
			JChannel channel = channelList.get(random.nextInt(channelList
					.size()));
			Message msg = new Message();
			msg.setObject("Helllo .........................................................");
			channel.send(msg);
			Thread.sleep(2000);
		}
	}

	@Test
	public void testStartCommand2Client() throws Exception {
		List<RpcDispatcher> dispatcherList = new ArrayList<RpcDispatcher>();
		List<JChannel> channelList = new ArrayList<JChannel>();
		Command2 command2 = new Command2();
		short i = 10;
		for (String group : groups) {
			JChannel jchannel = new JChannel("mping2.xml");
			channelList.add(jchannel);
			jchannel.setReceiver(receiver);
			jchannel.setName("server." + group);
			jchannel.connect(serverName + "." + group);
			dispatcherList.add(new MuxRpcDispatcher(i, jchannel, receiver,
					null, command2));
		}
		Random random = new Random();
		while (true) {
			for (RpcDispatcher rpcDispatcher : dispatcherList) {
				rpcDispatcher.start();
				RequestOptions requestOptions = new RequestOptions(
						ResponseMode.GET_FIRST, 1000);

				View view = rpcDispatcher.getChannel().getView();
				Address dest = view.getMembers().get(
						random.nextInt(view.getMembers().size()));
				System.out.println("command2 view:" + view);
				System.out.println(dest.toString() + " ," + dest);
				String result = rpcDispatcher.callRemoteMethod(dest, "hello",
						null, null, requestOptions);
				System.out.println(result);
				rpcDispatcher.stop();
			}
			JChannel channel = channelList.get(random.nextInt(channelList
					.size()));
			Message msg = new Message();
			msg.setObject("Helllo .........................................................");
			channel.send(msg);
			Thread.sleep(3000);
		}
	}

}
