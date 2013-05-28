/**
 * 
 */
package com.jd.doraemon.server.notify;

import java.io.Serializable;

/**
 * @author luolishu
 *
 */
public interface NotifyHandler<T extends Serializable> {
	
	public void notify(T entity);

}
