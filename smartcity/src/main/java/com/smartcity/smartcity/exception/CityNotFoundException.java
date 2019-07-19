package com.smartcity.smartcity.exception;

public class CityNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public CityNotFoundException() {
		super("CITY_NOT_FOUND_EXCEPTION");
	}

	public CityNotFoundException(String message) {
		super(message);
	}

	public CityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CityNotFoundException(Throwable cause) {
		super(cause);
	}

	public CityNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
