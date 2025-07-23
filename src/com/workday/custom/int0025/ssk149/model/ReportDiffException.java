package com.workday.custom.int0025.ssk149.model;

import javax.xml.stream.events.XMLEvent;

public class ReportDiffException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReportDiffException(String msg) {
		super(msg);
	}
	
	public ReportDiffException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public ReportDiffException(String msg, XMLEvent event) {
		super(msg + getLocation(event) );
	}
	
	public ReportDiffException(String msg, XMLEvent event, Throwable t) {
		super( msg + getLocation(event), t);
	}
	
	static String getLocation(XMLEvent event) {
		return " on line" +
			event.getLocation().getLineNumber() +
			" at column " +
			event.getLocation().getColumnNumber();
	}
}
