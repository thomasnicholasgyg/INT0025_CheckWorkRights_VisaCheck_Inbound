package com.workday.custom.int0025.ssk149.parsers;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.workday.custom.int0025.ssk149.handlers.ListBuilderHandler;
import com.workday.custom.int0025.ssk149.handlers.ReportDiffHandler;
import com.workday.custom.int0025.ssk149.model.ReportDiffException;

public class ReportEventReader implements XMLEventReader {
	private QName descriptor;
	private QName type;
	private String namespaceURI;
	private QName reportEntry;

	/**
	 * A name for the message source to be used when generating exception messages
	 */
	private String sourceName;	
	private XMLEventReader reader;
	
	public ReportEventReader(XMLEventReader reader, String name) {
		if (reader == null) {
			throw new IllegalArgumentException("XMLEventReader is a required argument to the constructor");
		}

		this.reader = reader;
		this.sourceName = name;
	}
	
	public void parseIds(ListBuilderHandler handler) throws Exception {
        XMLEvent event;
        StringBuilder content;
        StartElement startElement;
        String value;
        String elementName;
                
        try {
            getReportRoot();

            // Depth: 0 when on report root (-1 when closing tag of Report_Data has been read)
            // Depth: 1 when in Report_Entry element
            // Depth: 2 when in a non-nested column
            // Depth: 3 when in a nested column or ID element of a non-nested reference column
            
			while (reader.hasNext()) {
				// Position of the Start Element of a Report_Entry line or flow over to the end of the document
				
				event = null;
				do {
					event = reader.nextEvent();
				} while (reader.hasNext() && !(event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("Report_Entry")));
				
				int depth = 1;
				value = null;

				// If we haven't reached the end of the document then read the Report_Entry
				if (reader.hasNext()) {
					content = null;

					while ((depth > 0 ) && reader.hasNext()) {
						event = reader.nextEvent();
						if ((depth == 2) && event.isCharacters()) {
							if (content == null) {
								content = new StringBuilder();
							}
							content.append(event.asCharacters().getData());
						} else if (event.isStartElement()) {
							++depth;
							startElement = event.asStartElement();
							elementName = startElement.getName().getLocalPart(); 
							if ((depth > 2) && elementName.equals("ID")) {
								String type = getType(startElement); 
								
								if (type != null) {
									if (type.equals("WID")){
										value = reader.getElementText();
										--depth; // Decrease the depth as the getElementText() will have read the EndElement for the ID element
										break;
									} else {
										// Skip over the content of other ID types
										reader.getElementText();
									}
								} else {
									throw new ReportDiffException("Found element " + elementName + " in first column of " + sourceName + ".  First column of report must contain the unique ID and cannot be nested", startElement);									
								}
							} else if (depth > 2){
								throw new ReportDiffException("Found element " + elementName + " in first column of " + sourceName + ".  First column of report must contain the unique ID and cannot be nested", startElement);
							}
							
						} else if (event.isEndElement()) {
							if ((value == null) && (--depth == 1)) {
								value = content.toString();
								content = null;
								break;
							}
						}
					}

					if (value != null) {
						handler.processId(value);
						value = null;
					}
					
					// Skip to the end of the Report_Entry;
					while ((depth > 0) && reader.hasNext()) {
						event = reader.nextEvent();
						if (event.isStartElement()) {
							++depth;
						} else if (event.isEndElement()) {
							--depth;
						}
					}
				}
			}
		} catch (ReportDiffException rde) {
			throw new ReportDiffException( "Error while processing report " + sourceName + ": " + rde.getMessage(), rde);
		} catch (XMLStreamException e) {
			String msg = "Error while reading report IDs";
			if ((sourceName != null) && (sourceName.length() > 0)) {
				msg = msg + " for report " + sourceName;
			}
			throw new ReportDiffException( msg, e);
		}
    }
	
	public void compareReports(Set<String> currentIds, Set<String> historicIds, ReportEventReader historicReader, ReportDiffHandler handler) throws Exception {
		ReportEventReader currentReader = this;
		
		String currentNamespaceURI = currentReader.getNamespaceURI();
		String historicNamespaceURI = historicReader.getNamespaceURI();
		
		if (!currentNamespaceURI.equals(historicNamespaceURI)) {
			throw new ReportDiffException("The report namespaces do not match.  The current data snapshot namespace is " + currentNamespaceURI + " while the historic data snapshot namespace is " + historicNamespaceURI);
		}

		handler.processReports(currentIds, historicIds, currentReader, historicReader);
	}
	
	void setNamespaceURI(String uri) {
		if (namespaceURI == null) {
			namespaceURI = uri;
			descriptor = new QName(uri, "Descriptor");
			type = new QName(uri, "type");
		}
	}
	
	public String getNamespaceURI() {
		return namespaceURI;
	}
	
	/**
	 * Obtains the StartElement for the root Report_Data element of the 
	 * @return
	 */

	public StartElement getReportRoot() {
		StartElement startElement = getNamedElement("Report_Data");
		
		setNamespaceURI(startElement.getName().getNamespaceURI());
		reportEntry = new QName(namespaceURI, "Report_Entry");
		
		return startElement;
	}
	
	public String getDescriptor(StartElement element) {
		return getAttributeValue(element, descriptor);
	}
	
	public String getType(StartElement element) {
		return getAttributeValue(element, type);
	}
	
	private String getAttributeValue(StartElement element, QName name) {
		Attribute attribute = element.getAttributeByName(name);
		
		return (attribute != null) ? attribute.getValue() : null;
	}
	
	/**
	 * Finds the named element
	 * 
	 * @param name
	 * @return
	 * @throws ObjectDiffException if an error occurs, if the specified element is not present or if another
	 * element is encountered before the named element
	 */
	private StartElement getNamedElement(String name) {
		StartElement startElement = null;
		XMLEvent event;
		
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
			throw new ReportDiffException("Error while attempting to locate root element in " + sourceName, e);
		}
		
		if (startElement == null) {
			throw new ReportDiffException("Unable to find element " + name + " in " + sourceName );			
		} else {
			if (!startElement.getName().getLocalPart().equals(name)) {
				throw new ReportDiffException("Unexpected element " + name + " in " + sourceName, startElement);
			}
		}
		
		return startElement;		
	}

	/**
	 * Obtain the next StartElement for a Report_Entry element.
	 * 
	 * @return The StartElement for the next Report_Entry.  Returns null if no Report_Entry elements are located in the document
	 */
	public StartElement getReportEnty() {
		StartElement startElement = null;
		StartElement result = null;
		XMLEvent event;
		
		try {
			while (reader.hasNext()) {
				event = reader.nextEvent();
				if (event.isStartElement()) {
					startElement = event.asStartElement();
					if (startElement.getName().equals(reportEntry)) {
						result = startElement;
						break;
					}
				} else if (event.isEndDocument()) {
					break;
				}					
			}
		} catch (XMLStreamException e) {
			throw new ReportDiffException("Error attempting to find Report_Entry in " + sourceName);
		}
		
		return result;
	}

	/**
	 * @return
	 * @throws XMLStreamException
	 * @see javax.xml.stream.XMLEventReader#nextEvent()
	 */
	public XMLEvent nextEvent() throws XMLStreamException {
		return reader.nextEvent();
	}

	/**
	 * @return
	 * @see javax.xml.stream.XMLEventReader#hasNext()
	 */
	public boolean hasNext() {
		return reader.hasNext();
	}

	/**
	 * @return
	 * @throws XMLStreamException
	 * @see javax.xml.stream.XMLEventReader#peek()
	 */
	public XMLEvent peek() throws XMLStreamException {
		return reader.peek();
	}

	/**
	 * @return
	 * @see java.util.Iterator#next()
	 */
	public Object next() {
		return reader.next();
	}

	/**
	 * @return
	 * @throws XMLStreamException
	 * @see javax.xml.stream.XMLEventReader#getElementText()
	 */
	public String getElementText() throws XMLStreamException {
		return reader.getElementText();
	}

	/**
	 * 
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		reader.remove();
	}

	/**
	 * @return
	 * @throws XMLStreamException
	 * @see javax.xml.stream.XMLEventReader#nextTag()
	 */
	public XMLEvent nextTag() throws XMLStreamException {
		return reader.nextTag();
	}

	/**
	 * @param name
	 * @return
	 * @throws IllegalArgumentException
	 * @see javax.xml.stream.XMLEventReader#getProperty(java.lang.String)
	 */
	public Object getProperty(String name) throws IllegalArgumentException {
		return reader.getProperty(name);
	}

	/**
	 * @throws XMLStreamException
	 * @see javax.xml.stream.XMLEventReader#close()
	 */
	public void close() throws XMLStreamException {
		reader.close();
	}
}
