package com.workday.custom.int0025.ssk149.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.workday.custom.int0025.ssk149.parsers.ReportEventReader;
import com.workday.custom.int0025.ssk149.writers.ReportEntryWriter;

public class ReportEntry {
	private Vector<ReportColumn> columns = new Vector<ReportColumn>();
	Map<String, ReportColumn> keyedColumns = new HashMap<String, ReportColumn>();
	
	public ReportEntry() { }
	
	public void clear() {
		columns.clear();
	}
	
	/**
	 * Returns the unique Id for this report row.  The unique Id will be the value of the first column:  the WID for a reference value or
	 * the column contents for an atomic value
	 * @return
	 */
	public String getUniqueId() {
		return columns.get(0).getValue();
	}
	
	public void load(ReportEventReader reader) throws ReportDiffException {
		columns.clear();
		keyedColumns.clear();
		
		XMLEvent event = null;
		ReportColumn reportColumn = null;
		String lastColumnName = null; // Name of the last column
		String columnName = null;
		int index = 0;				// Index of the column within the row

		try {
			do {
				event = reader.nextEvent();
				
				if (event.isStartElement()) {
					reportColumn = getReportColumn(reader, event.asStartElement(), index);
					columnName = reportColumn.getName();
					if (columnName.equals(lastColumnName)) {
						try {
							--index;
							ReportColumn lastColumn = columns.lastElement();

							// If the last column was not already a multi-valued column then create a 
							// new MultiInstanceColumn, add the last column into this new MultiInstanceColumn and
							// then replace the last column entry with the new MultiInstanceColumn
							if (!(lastColumn instanceof MultiValuedColumn)) {
								MultiValuedColumn multiValueColumn = new MultiValuedColumn(index, columnName);
								columns.remove(index);
								columns.add(multiValueColumn);
								keyedColumns.put(columnName, multiValueColumn);
								multiValueColumn.addLine(lastColumn);
								lastColumn = multiValueColumn;
							}
							((MultiValuedColumn)lastColumn).addLine(reportColumn);
						} catch (ReportDiffException rde) {
							throw new ReportDiffException(rde.getMessage(), event, rde);
						}
					} else {
						lastColumnName = columnName;
						columns.add(reportColumn);
						keyedColumns.put(columnName, reportColumn);
					}
					++index;
				}
			} while (!event.isEndElement());
		} catch (XMLStreamException e) {
			throw new ReportDiffException("Error reading Report_Entry", e);
		}
	}

	public Vector<ReportColumn> getColumns() {
		return columns;
	}

	public Map<String, ReportColumn> getKeyedColumns() {
		return keyedColumns;
	}
	

	/**
	 * Retrieve the column information.  If the current StartElement has a wd:Descriptor attribute then we assume that
	 * this is a reference element.  If we encounter another StartElement then this is assumed to be NestedValue otherwise
	 * it must be an atomic value
	 * 
	 * @param reader
	 * @param startElement
	 * @return
	 * @throws XMLStreamException 
	 */
	ReportColumn getReportColumn(ReportEventReader reader, StartElement startElement, int index) throws XMLStreamException, ReportDiffException {
		QName name = startElement.getName();
		String columnName = name.getLocalPart();
		ReportColumn reportColumn = null;
		
		XMLEvent event;
		StringBuilder sb = null;
		
		// For the moment we assume that all reference values will have a wd:Descriptor attribute and so use
		// the presence of such an attribute as a trigger to attempt to load a reference value
		String descriptor = reader.getDescriptor(startElement);

		if (descriptor != null) {
			reportColumn = getReferenceColumn(reader, index, columnName, descriptor);
		} else {
			// The value must either be an atomic value or a nested column.  It will be a nested column if we encounter
			// another StartElement before reaching the EndElement
			while (reader.hasNext()) {
				event = reader.nextEvent();
				
				// If we encounter an element here then it's a nested column
				if (event.isStartElement()) {
					sb = null;
					
					NestedColumn nested = new NestedColumn(index, startElement.getName().getLocalPart());
					reportColumn = nested;
					ReportColumn nestedChildColumn = null;
					int nestedIndex = 0;
					String nestedLastColumnName = null; // Name of the last column in the nested column
					String childName = null;

					do {
						if (event.isStartElement()) {
							nestedChildColumn = getReportColumn(reader, event.asStartElement(), nestedIndex);
							childName = nestedChildColumn.getName();
							
							// Multi-Valued nested column
							if (childName.equals(nestedLastColumnName)) {
								try {
									--nestedIndex;
									ReportColumn lastColumn = nested.getOrderedColumns().lastElement();

									// If the last column was not already a multi-valued column then create a 
									// new MultiInstanceColumn, add the last column into this new MultiInstanceColumn and
									// then replace the last column entry with the new MultiInstanceColumn
									if (!(lastColumn instanceof MultiValuedColumn)) {
										MultiValuedColumn multiValueColumn = new MultiValuedColumn(nestedIndex, childName);
										nested.getOrderedColumns().remove(nestedIndex);
										nested.addColumn(multiValueColumn);
										multiValueColumn.addLine(lastColumn);
										lastColumn = multiValueColumn;
									}
									((MultiValuedColumn)lastColumn).addLine(nestedChildColumn);
								} catch (ReportDiffException rde) {
									throw new ReportDiffException(rde.getMessage(), event, rde);
								}
							} else {
								nestedLastColumnName = childName;
								nested.addColumn(nestedChildColumn);
							}
							++nestedIndex;
						}
						event = reader.nextEvent();
					} while (reader.hasNext() && !event.isEndElement());
					break;
				} else if (event.isCharacters()) {
					if ((sb == null) && (reportColumn == null)) {
						sb = new StringBuilder();
					}
					sb.append(event.asCharacters().getData());
				} else if (event.isEndElement()) {
					// We should only ever receive an EndElement for a simple report column as the EndEvent for nested columns and
					// reference values should be eaten within the nested-column-specific code
					reportColumn = new ReportAtomicValue(index, name.getLocalPart(), (sb != null) ? sb.toString(): null);
					break;
				}
			}
		}
		
		return reportColumn;
	}
	
	/**
	 * 
	 * Precondition:  The reader has read the StartElement of reference or has read the StartElement of the first ID
	 * PostCondition:  The reader has read the EndElement for the reference.  The next event to be processed will be the
	 * first event after that EndElement.
	 * 
	 * @param reader
	 * @param index Index of this column within it's parent.  0 is the first column
	 * @return
	 */
	ReferenceValue getReferenceColumn(ReportEventReader reader, int index, String columnName, String descriptor) throws XMLStreamException {
		XMLEvent event;
		StartElement startChild;
		
		ReferenceValue ref = new ReferenceValue(index, columnName);
		ref.setDescriptor(descriptor);
		
		while (reader.hasNext()) {
			event = reader.nextEvent();
			if (event.isStartElement()) {
				startChild = event.asStartElement();
				String elementName = startChild.getName().getLocalPart();
				if (elementName.equals("ID")) {
					// If it's an ID element and it has a wd:type attribute then this must be a Reference
					// otherwise this must be a child element of a nested column
					String idType = reader.getType(startChild);
					String idValue = reader.getElementText();
					if (idType != null) {
						ref.putID(idType, idValue);
					} else {
						throw new ReportDiffException("Reference IDs must have a type attribute", event);
					}
				} else {
					throw new ReportDiffException("Found element " + elementName + ". Only ID elements are permitted as child elements of references", event);
				}
			} else if (event.isEndElement()) {
				// TODO:  Add error handling for case in which an unexpected end element is encountered
				if (ref.ids().isEmpty()) {
					throw new ReportDiffException("Reference value must contain IDs", event);
				}
				break;
			}
		}
		
		return ref;
	}	

	/**
	 * Precondition:  The reader has read the StartElement of the first child of the nested column
	 * Postcondition:  The reader has processed the EndElement of the nested column.  The next event to be processed
	 * by the reader will be the first event after the EndElement
	 * @param reader
	 * @param startElement
	 * @param firstChild
	 * @param index
	 * @return
	 * @throws XMLStreamException
	 */
	ReportColumn getNestedReportColumn(ReportEventReader reader, StartElement startElement, StartElement firstChild, int index) throws XMLStreamException {
		// TODO
		throw new ReportDiffException("Not yet implemented");
	}

	public void outputAsDeleted(XMLStreamWriter writer, String namespace) {
		
	}

	/**
	 * Output all data for this report entry with the addition of the specified attribute
	 * @param newAttribute
	 */
	public void output(ReportEntryWriter writer, String newAttribute) throws XMLStreamException {
		writer.outputReportEntry(this, newAttribute);
	}
	
	public void outputDiff(ReportEntryWriter writer, boolean isOutputDiffOnly, ReportEntry historicReportEntry) throws XMLStreamException {
		writer.outputReportEntryDiff(this, isOutputDiffOnly, historicReportEntry);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ReportEntry)) {
			throw new IllegalArgumentException("Can only compare objects of type ReportEntry.  Provided object was " + o.getClass().getName() );
		}
		
		ReportEntry other = (ReportEntry)o;
		
		if (columns.size() != other.columns.size()) {
			return false;
		}
		
		ReportColumn otherColumn;
		for(ReportColumn column : columns) {
			otherColumn = other.keyedColumns.get(column.getName());
			if (!column.equals(otherColumn)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " - ID = " + ((columns == null || columns.isEmpty()) ? "null" : columns.get(0).getValue());
	}
}
