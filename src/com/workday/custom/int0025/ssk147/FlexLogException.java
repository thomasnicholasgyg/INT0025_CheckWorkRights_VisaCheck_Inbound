package com.workday.custom.int0025.ssk147;

public class FlexLogException extends RuntimeException {

	private static final long serialVersionUID = -8010347053167804481L;

	public FlexLogException() {
		super();
	}

	public FlexLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FlexLogException(String message, Throwable cause) {
		super(message, cause);
	}

	public FlexLogException(String message) {
		super(message);
	}

	public FlexLogException(Throwable cause) {
		super(cause);
	}

}
