package com.jd.doraemon.server.snapshot;

import java.io.File;

/**
 * @author luolishu
 * 
 */
public interface Snapshot {
	/**
	 * 得到分组的名称
	 * @return
	 */
	public String getGroupName();
	/**
	 * 得到摘要
	 * @return
	 */
	public String getFileDigest();
    /**
     * 得到分组的文件
     * @return
     */
	public File getFile();
	
	

}
