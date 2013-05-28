package com.jd.doraemon.client.downgrade;

public class DowngradeException extends RuntimeException { 
	private static final long serialVersionUID = 559122434761212861L;

	public DowngradeException() { 
	}

	public DowngradeException(String message) {
		super(message); 
	}

	public DowngradeException(Throwable cause) {
		super(cause); 
	}

	public DowngradeException(String message, Throwable cause) {
		super(message, cause); 
	}

}
