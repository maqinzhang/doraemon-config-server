/**
 * 
 */
package com.jd.doraemon.server.startup;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.junit.Test;

import com.jd.doraemon.core.event.ConfigActionType;
import com.jd.doraemon.core.serialize.ConfigValue;
import com.jd.doraemon.core.serialize.ConfigurationItem;
import com.jd.doraemon.server.impl.DefaultServerContainer;
import com.jd.doraemon.server.service.ConfigurationService;
import com.jd.doraemon.server.service.DefaultConfigurationService;

/**
 * @author luolishu
 * 
 */
public class TestServerStartup {
	@Test
	public void testSendMessage() throws Exception {
		System.setProperty("java.net.preferIP4Stack", "true");
		String group = "group1";
		String serverName = "test.config.server";
		JChannel jchannel = new JChannel("tcp.xml");
		jchannel.setName("server." + group);
		jchannel.connect(serverName + "." + group);
		for (int i = 0;; i++) {
			Message msg = new Message();
			msg.setObject("Hello World!i=" + i);
			jchannel.send(msg);
			System.out.println("message Send!i=" + i);
			System.out.println(jchannel.getViewAsString());

			Thread.sleep(1000L);
		}
	}

    @Test
	public void testStartUpSyn() throws Exception {
		String serverName = "test.config.server";
		String group1 = "group1";
		DefaultServerContainer container = new DefaultServerContainer(
				serverName);
		container.startup(null);
		container.addGroup(group1);
		ConfigurationService configurationService = new DefaultConfigurationService();

		for (int i = 0;; i++) {
		
			/*ConfigurationItem item = new ConfigurationItem(group1);
			item.setActionType(ConfigActionType.CREATE);
			ConfigValue value = new ConfigValue();
			item.setConfigValue(value);
			value.setKey("key" + i);
			value.setUpdateTime(new Date());
			value.setValue("value " + i);
			value.setVersion(1L);
			configurationService.create(item);
			System.out.println("send msg="+item);*/
			Thread.sleep(10000L);
		}

	}
    @Test
	public void testStartUp() throws Exception {
		String serverName = "test.config.server";
		String group1 = "group1";
		DefaultServerContainer container = new DefaultServerContainer(
				serverName);
		container.startup(null);
		container.addGroup(group1);
		ConfigurationService configurationService = new DefaultConfigurationService();

		for (int i = 0;; i++) {
		
			ConfigurationItem item = new ConfigurationItem(group1);
			item.setActionType(ConfigActionType.CREATE);
			ConfigValue value = new ConfigValue();
			item.setConfigValue(value);
			value.setKey("key" + i);
			value.setUpdateTime(new Date());
			value.setValue("valu```e " + i);
			value.setVersion(222L);
			configurationService.create(item);
			System.out.println("send msg="+item);
			Thread.sleep(100L);
		}

	}
    @Test
	public void testStartUpRemove() throws Exception {
		String serverName = "test.config.server";
		String group1 = "group1";
		DefaultServerContainer container = new DefaultServerContainer(
				serverName);
		container.startup(null);
		container.addGroup(group1);
		ConfigurationService configurationService = new DefaultConfigurationService();

		for (int i = 0;; i++) {
		
			ConfigurationItem item = new ConfigurationItem(group1);
			item.setActionType(ConfigActionType.DELETE);
			ConfigValue value = new ConfigValue();
			item.setConfigValue(value);
			value.setKey("key" + i);
			value.setUpdateTime(new Date());
			value.setValue("value " + i);
			value.setVersion(1L);
			configurationService.remove(item);
			System.out.println("send msg="+item);
			Thread.sleep(5000L);
		}

	}
    
    @Test
	public void testProperties() throws Exception {
		String path = "C:/Users/luolishu/AppData/Local/Temp/doraemon/server/groups/group1_snapshot.properties";
		File file=new File(path);
        InputStream is=FileUtils.openInputStream(file);
        
        Properties p=new Properties();
        p.load(is);
        System.out.println(p);
        is.close();

	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
