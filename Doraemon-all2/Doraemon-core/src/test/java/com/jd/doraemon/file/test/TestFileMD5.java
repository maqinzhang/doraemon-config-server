/**
 * 
 */
package com.jd.doraemon.file.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

/**
 * @author luolishu
 *
 */
public class TestFileMD5 {
	
	public static void main(String args[]) throws Exception, IOException{
		String path="C:/Users/luolishu/Downloads/JakeWharton-ActionBarSherlock-4.3.0-0-gfe0125a.zip";
		long time=System.currentTimeMillis();
		String a1=DigestUtils.md5Hex(new FileInputStream(path));
		System.out.println("time offset="+(System.currentTimeMillis()-time));
		time=System.currentTimeMillis();
		String a2=DigestUtils.md5Hex(new FileInputStream(path));
		System.out.println("time offset="+(System.currentTimeMillis()-time));
		time=System.currentTimeMillis();
		String a3=DigestUtils.md5Hex(new FileInputStream("D:/opensources/diamond_taobao/pom.xml"));
		System.out.println("time offset="+(System.currentTimeMillis()-time));
		time=System.currentTimeMillis();
		
		HashCode md5 = Files.hash(new File(path), Hashing.md5());
		String md5Hex = md5.toString(); 
		System.out.println("time offset="+(System.currentTimeMillis()-time));		
		time=System.currentTimeMillis();
		md5 = Files.hash(new File(path), Hashing.md5());
		md5Hex = md5.toString(); 
		System.out.println("time offset="+(System.currentTimeMillis()-time));		
		time=System.currentTimeMillis();
		System.out.println(a1.equals(a2));
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(md5Hex);
	}

}
