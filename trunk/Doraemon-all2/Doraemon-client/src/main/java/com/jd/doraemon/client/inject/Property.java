package com.jd.doraemon.client.inject;

public @interface Property {

	String group();

	String key() default "";

}
