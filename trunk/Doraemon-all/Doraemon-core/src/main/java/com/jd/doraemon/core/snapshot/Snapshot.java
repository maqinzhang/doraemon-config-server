package com.jd.doraemon.core.snapshot;

import java.io.File;
import java.util.Properties;

/**
 * @author luolishu
 * 
 */
public interface Snapshot {
	static final String SNAPSHOT_SUFFIX="_snapshot.properties"; 
	/** 
	 * @return
	 */
	public String getGroupName();
	/** 
	 * @return
	 */
	public String getFileDigest();
    /** 
     * @return
     */
	public File getFile();
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setConfig(String key,String value);
	/**
	 * remove
	 * @param key
	 */
	public void remove(String key);
	/**
	 * ¸üÐÂƒÈÈÝ
	 * @param properties
	 */
	public void update(Properties properties);
	/**
	 * 
	 * @return
	 */
	public Properties getProperties() ;
	public void load();
	public void flush();

	public void compareAndflush();
}
