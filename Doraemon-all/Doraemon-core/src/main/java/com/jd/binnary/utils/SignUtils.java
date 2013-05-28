/**
 * 
 */
package com.jd.binnary.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luolishu
 * 
 */
public abstract class SignUtils {
	static final String GET_VALUE_METHOD = "getValue";
	
	/**
	 * 清除二进制中的某一位
	 * @param value
	 * @param signEnum
	 * @return
	 */
	public static long clearSign(long value,Enum<?> signEnum){
		Long signVal = getSignValue(signEnum); 
		return (value&~signVal);
	}

	/**
	 * 判断某个位是否被设置
	 * 
	 * @param value
	 * @param signEnum
	 * @return
	 */
	public static boolean isSignSet(long value, Enum<?> signEnum) {
		Long signVal = getSignValue(signEnum); 
		return (value & signVal) == signVal;
	}

	/**
	 * 添加某个位
	 * 
	 * @param value
	 * @param signEnum
	 * @return
	 */
	public static long addSignAndGet(long value, Enum<?> signEnum) {
		Long signVal = getSignValue(signEnum); 
		return (value |=signVal);
	}

	/**
	 * 得到所有设置的位置
	 * 
	 * @param value
	 * @param allEnums
	 * @return
	 */
	public static Enum<?>[] getSignEnums(long value, Enum<?>[] allEnums) {
		if (allEnums == null || allEnums.length <= 0) {
			return null;
		}
		List<Enum<?>> list = new ArrayList<Enum<?>>();
		for (Enum<?> item : allEnums) {
			if (isSignSet(value, item)) {
				list.add(item);
			}
		}
		return list.toArray(new Enum<?>[] {});
	}

	private static Long getSignValue(Enum<?> signEnum) {
		try {
			return (Long) signEnum.getClass().getMethod(GET_VALUE_METHOD)
					.invoke(signEnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new RuntimeException("enum class="+signEnum.getClass()+",no method="+GET_VALUE_METHOD+" found!");
		
	}
	
	public static void checkDefinded(Enum code){
		
		
	}

	public static void main(String... a) {
		/*for (Enum e : SignUtils.getSignEnums(5, SignSampesEnum.values())) {
			System.out.println(e.name());
		}*/
		
		for (Enum e : SignUtils.getSignEnums(11, SignSampesEnum.values())) {
			System.out.println(e.name());
		}
		long v = SignUtils.addSignAndGet(5, SignSampesEnum.THIRD);
		System.out.println(v);
		Enum enumVals[]=SignUtils.getSignEnums(v, SignSampesEnum.values());
		System.out.println(enumVals.length);
		for (Enum e : SignUtils.getSignEnums(11, SignSampesEnum.values())) {
			System.out.println(e.name());
		}
		System.out.println("=================");
		System.out.println(v);
		v=SignUtils.clearSign(11, SignSampesEnum.FOUR);
		System.out.println(v);
		for (Enum e : SignUtils.getSignEnums(v, SignSampesEnum.values())) {
			System.out.println(e.name());
		}

	}
}
