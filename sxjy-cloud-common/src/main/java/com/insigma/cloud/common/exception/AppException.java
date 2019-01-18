package com.insigma.cloud.common.exception;

/**
 * 自定义异常类
 */
public class AppException extends Exception {  
	  
    private static final long serialVersionUID = 1L;  
  
    public AppException() {  
        // TODO Auto-generated constructor stub  
    }  
  
    public AppException(String message) {  
        super(message);  
        // TODO Auto-generated constructor stub  
    }  
  
    public AppException(Throwable cause) {  
        super(cause);  
        // TODO Auto-generated constructor stub  
    }  
  
    public AppException(String message, Throwable cause) {  
        super(message, cause);  
        // TODO Auto-generated constructor stub  
    }  
  
} 
