package com.jd.doraemon.core.serialize;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author luolishu
 * 
 */
public abstract class SerializeUtils {
	static ObjectMapper mapper=new ObjectMapper();

	public static String serialize(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T deSerialize(String content, Class<T> claz) {
		return mapper.convertValue(content, claz);
	}
}
