package com.common.webconfig;

/**
 * Ӧ���쳣
 * 
 */
public class AppException extends Exception {
	/**
     * �չ��캯��
     */
	public AppException() {
		super();
	}
    /**
     * ���ι��캯��
     * @param message
     */
	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message,Exception e) {
		super(message,e);
	}

}
