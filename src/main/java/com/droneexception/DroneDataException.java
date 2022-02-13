package com.droneexception;

public class DroneDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DroneDataException() {
		super();
	}

	public DroneDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DroneDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public DroneDataException(String message) {
		super(message);
	}

	public DroneDataException(Throwable cause) {
		super(cause);
	}

}
