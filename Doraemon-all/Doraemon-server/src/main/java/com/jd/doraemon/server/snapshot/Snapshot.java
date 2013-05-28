package com.jd.doraemon.server.snapshot;

import java.io.File;

/**
 * @author luolishu
 * 
 */
public interface Snapshot {
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
	
	

}
