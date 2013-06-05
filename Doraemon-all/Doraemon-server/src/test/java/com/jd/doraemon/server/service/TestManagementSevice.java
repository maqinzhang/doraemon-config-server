/**
 * 
 */
package com.jd.doraemon.server.service;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jd.doraemon.core.event.ConfigActionType;
import com.jd.doraemon.core.serialize.ConfigDescription;
import com.jd.doraemon.core.serialize.ConfigValue;
import com.jd.doraemon.core.serialize.ConfigurationItem;
import com.jd.doraemon.server.impl.DefaultServerContainer;
import com.jd.doraemon.server.service.ConfigurationService;
import com.jd.doraemon.server.service.DefaultConfigurationService;

/**
 * @author luolishu
 * 
 */
public class TestManagementSevice {
	@BeforeClass
	public static void beforeClass() {
		String serverName = "test.config.server";
		String group1 = "group1";
		DefaultServerContainer container = new DefaultServerContainer(
				serverName);
		container.startup(null);
		container.addGroup(group1);
	}

	@Test
	public void testService() throws Exception {
		ServerManagementService serverManagementService = new ServerManagementServiceImpl();
		List<ConfigDescription> results = serverManagementService
				.getConfigFromMemery("group1", "key1");
		Assert.assertNotNull(results);
		Assert.assertEquals(true, results.size() > 0);
		System.out.println("result size="+results.size());
		System.out.println("result content="+results);
	}
}
