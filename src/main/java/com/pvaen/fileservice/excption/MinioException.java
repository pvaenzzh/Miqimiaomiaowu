package com.pvaen.fileservice.excption;

public class MinioException extends Exception{

	private static final long serialVersionUID = 9221048595380441390L;

	public MinioException() {
		super();
	}

	public MinioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MinioException(String message, Throwable cause) {
		super(message, cause);
	}

	public MinioException(String message) {
		super(message);
	}

	public MinioException(Throwable cause) {
		super(cause);
	}
	
	

}
