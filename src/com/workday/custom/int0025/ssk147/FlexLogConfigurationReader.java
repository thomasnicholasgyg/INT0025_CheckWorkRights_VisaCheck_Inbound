package com.workday.custom.int0025.ssk147;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang.StringUtils;

import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;

/**
 * 
 * 
 * 
 * Example Input XML:
 * <code>
 * <FlexLog>
 * 	<Field>
 * 		<Parameter/>
 * 		<Header/>
 * 	</Field>
 * 	<Field>
 * 		<Parameter/>
 * 		<Header/>
 * 		<Required/>
 * 	</Field>
 * 	<Field>
 * 		<Parameter/>
 * 		<Header/>
 * 	</Field>
 * 	<Field>
 * 		<Parameter/>
 * 		<Header/>
 * 		<Required/>
 * 	</Field>
 * 	<Field>
 * 		<Parameter/>
 * 		<Header/>
 * 	</Field>
 * </FlexLog>
 * </code>
 * @author john.smail
 *
 */
public class FlexLogConfigurationReader {
	private Logger log = LogControl.getLogger(getClass());
	
	private final QName ELEMENT_FLEXLOGS = new QName("urn:com.workday.custom.int0025.common", "FlexLogs", "int0025"); 
	private final QName ELEMENT_FLEXLOG = new QName("urn:com.workday.custom.int0025.common", "FlexLog", "int0025"); 
	private final QName ELEMENT_FIELD = new QName("urn:com.workday.custom.int0025.common", "Field", "int0025"); 
	private final QName ELEMENT_PARAMETER = new QName("urn:com.workday.custom.int0025.common", "Parameter", "int0025"); 
	private final QName ELEMENT_HEADER = new QName("urn:com.workday.custom.int0025.common", "Header", "int0025"); 

	private final QName ATTRIBUTE_LOG_NAME = new QName("urn:com.workday.custom.int0025.common", "logName", "int0025"); 
	private final QName ATTRIBUTE_FILENAME = new QName("urn:com.workday.custom.int0025.common", "filename", "int0025"); 
	private final QName ATTRIBUTE_MAX_ENTRIES = new QName("urn:com.workday.custom.int0025.common", "maxEntriesPerFile", "int0025"); 
	private final QName ATTRIBUTE_RETENTION = new QName("urn:com.workday.custom.int0025.common", "retention", "int0025"); 
	private final QName ATTRIBUTE_SEPARATOR = new QName("urn:com.workday.custom.int0025.common", "separator", "int0025"); 
	private final QName ATTRIBUTE_DELIVERABLE = new QName("urn:com.workday.custom.int0025.common", "deliverable", "int0025"); 
	private final QName ATTRIBUTE_DOCUMENT_TAG = new QName("urn:com.workday.custom.int0025.common", "aggregationDocumentTag", "int0025"); 
	private final QName ATTRIBUTE_WORKSHEET = new QName("urn:com.workday.custom.int0025.common", "aggregationWorksheet", "int0025"); 
	
	private XMLEventReader reader;

	public FlexLogConfigurationReader(XMLEventReader reader) {
		super();
		this.reader = reader;
	}

	public List<FlexLog> parseConfiguration(String sourceName) throws Throwable {
		List<FlexLog> logs = new ArrayList<FlexLog>();
		StartElement element = getElement(ELEMENT_FLEXLOGS, sourceName);

		while (reader.hasNext()) {
			element = getElement(ELEMENT_FLEXLOG, sourceName);
			
			if (element != null) {
			
				String logName = "flex-log";
				String filename = "FlexLog";
				int maxEntries = 0;
				int retention = 30;
				String separator = ",";
				String documentTag = "";
				String worksheet = "";
				boolean isDeliverable = false;
	
				@SuppressWarnings("rawtypes")
				Iterator attributes = element.getAttributes();
				
				while (attributes.hasNext()) {
					Attribute attribute = (Attribute)attributes.next();
					if (attribute.getName().equals(ATTRIBUTE_LOG_NAME)) {
						logName = attribute.getValue();
					} else if (attribute.getName().equals(ATTRIBUTE_FILENAME)) {
						filename = attribute.getValue();
					} else if (attribute.getName().equals(ATTRIBUTE_MAX_ENTRIES)) {
						maxEntries = Integer.parseInt(attribute.getValue());
					} else if (attribute.getName().equals(ATTRIBUTE_RETENTION)) {
						retention = Integer.parseInt(attribute.getValue());
					} else if (attribute.getName().equals(ATTRIBUTE_SEPARATOR)) {
						separator = attribute.getValue();
					} else if (attribute.getName().equals(ATTRIBUTE_DOCUMENT_TAG)) {
						documentTag = attribute.getValue();
					} else if (attribute.getName().equals(ATTRIBUTE_WORKSHEET)) {
						worksheet = attribute.getValue();
					} else if (attribute.getName().equals(ATTRIBUTE_DELIVERABLE)) {
						if (StringUtils.isNotEmpty(attribute.getValue())) {
							switch (attribute.getValue().toLowerCase()) {
								case "true":
									isDeliverable = true;
									break;
									
								case "yes":
									isDeliverable = true;
									break;
									
								case "1":
									isDeliverable = true;
									break;
									
								default:
									isDeliverable = false;
									break;
							}
						}
					}
				}
				
				List<FlexLogField> fields = new ArrayList<FlexLogField>();
				FlexLogField field = null;
				
				XMLEvent event = null;
	
				try {
					do {
						event = reader.nextEvent();
						field = null;
	
						if (event.isStartElement() && event.asStartElement().getName().equals(ELEMENT_FIELD)) {
							field = new FlexLogField();
							
							try {
								event = reader.nextEvent();
								
								do {
									if (event.isStartElement()) {
										QName childOfField = event.asStartElement().getName();
										
										if (ELEMENT_PARAMETER.equals(childOfField)) {
											do {
												event = reader.nextEvent();
												field.setParameter(event.asCharacters().getData());
											} while (!event.isCharacters());
										} else if (ELEMENT_HEADER.equals(childOfField)) {
											do {
												event = reader.nextEvent();
												field.setHeader(event.asCharacters().getData());
											} while (!event.isCharacters());
										}
									}
	
									event = reader.nextEvent();
									
								} while (!event.isEndElement() || (event.isEndElement() && !event.asEndElement().getName().equals(ELEMENT_FIELD)));
							} catch (XMLStreamException e) {
								throw new FlexLogConfigurationException("Error reading Field element", e);
							}
	
							fields.add(field);
						}
					} while (!event.isEndElement() || (event.isEndElement() && !event.asEndElement().getName().equals(ELEMENT_FLEXLOG)));
				} catch (XMLStreamException e) {
					throw new FlexLogConfigurationException("Error reading Field element", e);
				}
				
				logs.add(new FlexLog(logName, fields, maxEntries, retention, filename, separator, documentTag, worksheet, isDeliverable));
			}
			
		}
		
		return logs;
	}


	private StartElement getElement(QName name, String sourceName) throws FlexLogConfigurationException {
		StartElement startElement = null;
		XMLEvent event = null;

		try {
			while (reader.hasNext()) {
				event = reader.nextEvent();
				if (event.isStartElement()) {
					startElement = event.asStartElement();
					break;
				} else if (event.isEndElement()) {
					break;
				}
			}
		} catch (XMLStreamException e) {
    		log.error(e.getMessage(), e);
			throw new FlexLogConfigurationException("Error while attempting to locate root element in " + sourceName, e);
		}
		
		if (event.isEndDocument() || (event.isEndElement() && event.asEndElement().getName().equals(ELEMENT_FLEXLOGS))) {
			startElement = null;
		} else if (startElement == null) {
			throw new FlexLogConfigurationException("Unable to find element " + name + " in " + sourceName );			
		} else {
			if (!startElement.getName().equals(name)) {
				throw new FlexLogConfigurationException("Unexpected element " + name + " in " + sourceName, startElement);
			}
		}
		
		return startElement;		
	}
}
