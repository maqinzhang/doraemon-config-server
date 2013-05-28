package com.jd.binnary.utils;

/**
 * @author luolishu
 * 
 */
public enum SignSampesEnum {

	ACITVITY(1), SECOND(1 << 1), THIRD(1 << 2),FOUR(1 << 3);

	final long value;

	SignSampesEnum(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}
	
	

}
