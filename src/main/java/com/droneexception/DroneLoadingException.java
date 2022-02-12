package com.droneexception;

public class DroneLoadingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DroneLoadingException() {
		super();
	}

	public DroneLoadingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DroneLoadingException(String message, Throwable cause) {
		super(message, cause);
	}

	public DroneLoadingException(String message) {
		super(message);
	}

	public DroneLoadingException(Throwable cause) {
		super(cause);
	}

}
