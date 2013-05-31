package com.jd.doraemon.server.service;

/**
 * @author luolishu
 * 
 */
public interface ConfigurationService<T> {

	public void create(T entity);

	public void modify(T entity);

	public void remove(T entity);

}
