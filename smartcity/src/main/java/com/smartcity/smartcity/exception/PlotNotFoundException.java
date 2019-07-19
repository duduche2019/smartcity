package com.smartcity.smartcity.exception;

public class PlotNotFoundException  extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	public PlotNotFoundException() {
		super("PLOT_NOT_FOUND_EXCEPTION");
	}

	public PlotNotFoundException(String message) {
		super(message);
	}

	public PlotNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlotNotFoundException(Throwable cause) {
		super(cause);
	}

	public PlotNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	

}
