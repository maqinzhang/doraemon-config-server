package com.jd.doraemon.server.snapshot;

import java.io.File;

/**
 * @author luolishu
 * 
 */
public interface Snapshot {
	/**
	 * �õ����������
	 * @return
	 */
	public String getGroupName();
	/**
	 * �õ�ժҪ
	 * @return
	 */
	public String getFileDigest();
    /**
     * �õ�������ļ�
     * @return
     */
	public File getFile();
	
	

}
