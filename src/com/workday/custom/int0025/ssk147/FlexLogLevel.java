package com.workday.custom.int0025.ssk147;

public enum FlexLogLevel {
	debug, 
	info, 
	warn, 
	error, 
	critical;
	
	/**
	 * 
	 * @param level The log level to be parsed.  Leading whitespaces and case differences are ignored.
	 * @return
	 * @throws IllegalArgumentException if the supplied level name does not match a FlexLogLevel value
	 */
	
	public static FlexLogLevel parse(String level) {
		return valueOf(level.trim().toLowerCase());
	}
}
