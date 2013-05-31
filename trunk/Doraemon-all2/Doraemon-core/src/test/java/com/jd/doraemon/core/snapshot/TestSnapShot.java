/**
 * 
 */
package com.jd.doraemon.core.snapshot;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

/**
 * @author luolishu
 * 
 */
public class TestSnapShot {

	@Test
	public void testSnapShot() {
		File directory = new File("c:/doraemon/groups");
		if (!directory.isDirectory()) {
			directory.mkdirs();
		}
		Date date = new Date();
		Properties p = new Properties();

		p.setProperty("a", "aaaa");
		p.setProperty("date", date.toString());
		Snapshot snapshot = new FileSnapshot(directory, "grouptest1");
		snapshot.update(p);
		snapshot.flush();
		System.out.println(snapshot.getFileDigest());

		p = new Properties();
		p.setProperty("date", date.toString());
		p.setProperty("a", "aaaa");
		Snapshot snapshot2 = new FileSnapshot(directory, "grouptest2");
		snapshot2.update(p);
		snapshot2.flush();
		System.out.println(snapshot2.getFileDigest());
	}

	@Test
	public void testGetSnapshots(){
		File directory = new File("c:/doraemon/groups");
		System.out.println(FileSnapshotUtils.getSnapshots(directory).size());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
