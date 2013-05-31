package com.jd.doraemon.core.serialize;

import java.io.Serializable;

/**
 * @author luolishu
 * 
 */
public interface Serializer {

	public String serialize(Serializable obj);

	public <T extends Serializable> T deSerialize(String content, Class<T> claz);
}
