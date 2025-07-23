package com.workday.custom.int0025.ssk112;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum CloudLogLevel {
	@XmlEnumValue("debug")
	debug, 
	
	@XmlEnumValue("info")
	info, 
	
	@XmlEnumValue("warn")
	warn, 
	
	@XmlEnumValue("error")
	error, 
	
	@XmlEnumValue("critical")
	critical;
	
	/**
	 * 
	 * @param level The log level to be parsed.  Leading whitespaces and case differences are ignored.
	 * @return
	 * @throws IllegalArgumentException if the supplied level name does not match a FlexLogLevel value
	 */
	
	public static CloudLogLevel parse(String level) {
		return valueOf(level.trim().toLowerCase());
	}
}
