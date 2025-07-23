package com.workday.custom.int0025.ssk147;

import javax.xml.stream.events.XMLEvent;

public class FlexLogConfigurationException extends Exception {

	private static final long serialVersionUID = 2151118150783791338L;

	public FlexLogConfigurationException() {
		super();
	}

	public FlexLogConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FlexLogConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public FlexLogConfigurationException(String message) {
		super(message);
	}

	public FlexLogConfigurationException(Throwable cause) {
		super(cause);
	}

	public FlexLogConfigurationException(String message, XMLEvent event) {
		super(message + getLocation(event) );
	}
	
	static String getLocation(XMLEvent event) {
		return " on line" +
			event.getLocation().getLineNumber() +
			" at column " +
			event.getLocation().getColumnNumber();
	}
}
