package com.jd.doraemon.core.snapshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public final class FileSnapshot implements Snapshot {

	Properties properties = new Properties();
	String groupName;
	File directory;
	File file;
	String fileDigest;
	FileSnapshotTask snapshotTask;

	public FileSnapshot(File directoryFile, String groupName) {
		this.directory = directoryFile;
		if (!directoryFile.isDirectory()) {
			directoryFile.mkdir();
		}
		this.groupName = groupName;
		this.file = new File(this.getFilePath());
		snapshotTask = new FileSnapshotTask(this);
		this.load();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileDigest() {
		return fileDigest;
	}

	public void setFileDigest(String fileDigest) {
		this.fileDigest = fileDigest;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public synchronized void load() {
		Properties p = new Properties();

		InputStream inputStream = null;

		try {
			if (file == null||!file.exists()) {
				String filePath = getFilePath();
				file = new File(filePath);
				file.createNewFile();
			}
			
			inputStream = FileUtils.openInputStream(file);	
			fileDigest = DigestUtils.md5Hex(inputStream);			
			IOUtils.closeQuietly(inputStream);
			inputStream = FileUtils.openInputStream(file);
			p.load(inputStream);  
			this.properties.putAll(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

	}

	@Override
	public void setConfig(String key, String value) {
		this.properties.put(key, value);
		this.update(properties);
	}

	@Override
	public synchronized void remove(String key) {
		this.properties.remove(key);
		this.update(properties);
	}
	@Override
	public synchronized void update(Properties properties) {
		this.properties = properties;
		try {
			snapshotTask.offer(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getFilePath() {
		if (file == null) {
			return this.directory.getPath() + File.separator + this.groupName
					+ SNAPSHOT_SUFFIX;
		}
		return file.getPath();
	}

	@Override
	public synchronized final void flush() {
		TreeMap sortedMap = new TreeMap();
		sortedMap.putAll(properties);
		Properties properties = new SnapshotProperties();
		properties.putAll(sortedMap);
		FileWriter writer = null;
		InputStream inputStream = null;
		try {
			String filePath = getFilePath();
			if (file != null && file.exists()) {
				FileUtils.forceDelete(file);
			}
			this.file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new FileWriter(file);
			properties.store(writer, null);
			inputStream = FileUtils.openInputStream(file);
			fileDigest = DigestUtils.md5Hex(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(inputStream);
		}
	}

	@Override
	public void compareAndflush() {
		Properties p = new Properties();

		InputStream inputStream = null;

		try {
			if (file == null||!file.exists()) {
				String filePath = getFilePath();
				file = new File(filePath);
				file.createNewFile();
			}
			inputStream = FileUtils.openInputStream(file);
			fileDigest = DigestUtils.md5Hex(inputStream);
			IOUtils.closeQuietly(inputStream);
			inputStream = FileUtils.openInputStream(file);
			p.load(inputStream); 
			if(!this.isEqual(p, properties)){
				this.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

	}
	boolean isEqual(Properties p1,Properties p2){
		Set<Entry<Object, Object>> entrySet=p1.entrySet();
		if(p1.size()!=p2.size()){
			return false;
		}
		for(Entry<Object, Object> entry:entrySet){
			Object v1=entry.getValue(),v2=p2.get(entry.getKey());
			if(!v1.equals(v2))return false;
		}
		return true;
	}

	private class FileSnapshotTask implements Runnable {

		final ExecutorService threadPool = Executors.newFixedThreadPool(1);
		FileSnapshot snapshot;
		BlockingQueue blockingQueue = new ArrayBlockingQueue(10);

		public FileSnapshotTask(FileSnapshot snapshot) {
			threadPool.submit(this);
			this.snapshot = snapshot;
		}

		public void offer(Object o) throws Exception {
			blockingQueue.put(o);
		}

		@Override
		public void run() {
			while (true) {
				try {
					Object o = blockingQueue.take();
					if (o != null) {
						snapshot.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	

}
