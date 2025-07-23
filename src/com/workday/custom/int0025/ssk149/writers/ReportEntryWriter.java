package com.workday.custom.int0025.ssk149.writers;

import java.util.Iterator;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.workday.custom.int0025.ssk149.model.MultiValuedColumn;
import com.workday.custom.int0025.ssk149.model.NestedColumn;
import com.workday.custom.int0025.ssk149.model.ReferenceValue;
import com.workday.custom.int0025.ssk149.model.ReportAtomicValue;
import com.workday.custom.int0025.ssk149.model.ReportColumn;
import com.workday.custom.int0025.ssk149.model.ReportEntry;

public class ReportEntryWriter {

	public static final String NS_DIFF = "urn:com.workday/reportdiff";
	public static final String PRIOR_VALUE = "priorValue";
	public static final String IS_ADDED	= "isAdded";
	public static final String IS_DELETED = "isDeleted";
	
	private String namespaceURI;

	private XMLStreamWriter writer;

	public ReportEntryWriter(XMLStreamWriter writer, String namespaceURI) throws XMLStreamException {
		super();
		this.writer = writer;
		this.namespaceURI = namespaceURI;
		
		writer.setPrefix("wd", namespaceURI);
		writer.writeStartDocument();
		writer.writeStartElement(namespaceURI, "Report_Data");
		writer.writeNamespace("diff", NS_DIFF);
	}
	
	public void endOutput() throws XMLStreamException {
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.flush();
		writer.close();
	}
	
	/**
	 * Output all data for this report entry with the addition of the specified attribute
	 * @param newAttribute
	 */
	public void outputReportEntry(ReportEntry reportEntry, String newAttribute) throws XMLStreamException {
		writer.writeStartElement(namespaceURI, "Report_Entry");
		
		if (newAttribute != null) {
			writer.writeAttribute(ReportEntryWriter.NS_DIFF, newAttribute, "true");
		}

		for (ReportColumn column : reportEntry.getColumns()) {
			column.writeColumn(this, newAttribute);
		}

		writer.writeEndElement();
	}
	
	public void outputReportEntryDiff(ReportEntry currentReportEntry, boolean isOutputDiffOnly, ReportEntry historicReportEntry) throws XMLStreamException {
		// If we are outputting non-changes or if the entry contains changes then output the XML version of this entry
		if ((!isOutputDiffOnly) || (!currentReportEntry.equals(historicReportEntry))) {
			writer.writeStartElement(namespaceURI, "Report_Entry");

			Iterator<ReportColumn> currentIterator = currentReportEntry.getColumns().iterator();
			Iterator<ReportColumn> historicIterator = historicReportEntry.getColumns().iterator();

			ReportColumn currentReportColumn = null;
			ReportColumn historicReportColumn = null;
			String currentName;
			String historicName;

			while (currentIterator.hasNext() || historicIterator.hasNext()) {
				if ((currentReportColumn == null) && currentIterator.hasNext()) {
					currentReportColumn = currentIterator.next();
				}
				
				if ((historicReportColumn == null) && historicIterator.hasNext()) {
					historicReportColumn = historicIterator.next();
				}
				
				currentName	= (currentReportColumn != null) ? currentReportColumn.getName() : null;
				historicName = (historicReportColumn != null) ? historicReportColumn.getName() : null;
				
				if ((currentName != null) && currentName.equals(historicName)) {
					// The column is present in both current and historical reports so we need to difference the data
					if ((currentReportColumn instanceof MultiValuedColumn) && !(historicReportColumn instanceof MultiValuedColumn)) {
						MultiValuedColumn historicMultiValueColumn = new MultiValuedColumn(historicReportColumn.getIndex(), historicReportColumn.getName());
						historicMultiValueColumn.addLine(historicReportColumn);
						currentReportColumn.outputDiff(this, historicMultiValueColumn);
					} else if ((historicReportColumn instanceof MultiValuedColumn) && !(currentReportColumn instanceof MultiValuedColumn)) {
						MultiValuedColumn currentMultiValueColumn = new MultiValuedColumn(currentReportColumn.getIndex(), currentReportColumn.getName());
						currentMultiValueColumn.addLine(currentReportColumn);
						currentMultiValueColumn.outputDiff(this, historicReportColumn);
					} else {
						currentReportColumn.outputDiff(this, historicReportColumn);
					}
					currentReportColumn = null;
					historicReportColumn = null;
				} else {
					// There's a difference in names.  A column has been added or removed
					
					// If the current column is present in the other entry then the other column
					// must have been removed
					if ((currentName == null) || (historicReportEntry.getKeyedColumns().containsKey(currentName))) {
						historicReportColumn.writeColumn(this, IS_DELETED);
						historicReportColumn = null;
					} else {
						// The current column is not present in the other entry and so it must have been added
						currentReportColumn.writeColumn(this, IS_ADDED);
						currentReportColumn = null;
					}
				}
			}
			
			writer.writeEndElement();
		}
	}

	public void outputReportAtomicValue(ReportAtomicValue reportColumn, String newAttribute) throws XMLStreamException {
		if ((newAttribute == null) && ((reportColumn.getValue() == null) || (reportColumn.getValue().length() == 0))) {
			writer.writeEmptyElement(namespaceURI, reportColumn.getName());
		} else {
			writer.writeStartElement(namespaceURI, reportColumn.getName());
			if (newAttribute != null) {
				writer.writeAttribute(NS_DIFF, newAttribute, "true");
			}
			writer.writeCharacters(reportColumn.getValue());
			writer.writeEndElement();
		}		
	}
	
	public void outputReportAtomicValueDiff(ReportAtomicValue currentReportColumn, ReportAtomicValue historicReportColumn) throws XMLStreamException {
		writer.writeStartElement(namespaceURI, currentReportColumn.getName());
		if (!currentReportColumn.equals(historicReportColumn)) {
			writer.writeAttribute(NS_DIFF, PRIOR_VALUE, historicReportColumn.getValue());
		}
		
		writer.writeCharacters(currentReportColumn.getValue());
		writer.writeEndElement();
	}

	public void outputReferenceValue(ReferenceValue reportColumn, String newAttribute) throws XMLStreamException {
		writer.writeStartElement(namespaceURI, reportColumn.getName());

		if (reportColumn.getDescriptor() != null) {
			writer.writeAttribute(namespaceURI, "Descriptor", reportColumn.getDescriptor());
		}

		if (newAttribute != null) {
			writer.writeAttribute(NS_DIFF, newAttribute, "true");
		}

		for (String idType: reportColumn.ids()) {
			writer.writeStartElement(namespaceURI, "ID");
			if (newAttribute != null ) {
				writer.writeAttribute(NS_DIFF, newAttribute, "true");
			}
			writer.writeAttribute(namespaceURI, "type", idType);
			writer.writeCharacters(reportColumn.getId(idType));
			writer.writeEndElement();
		}

		writer.writeEndElement();	
	}

	public void outputReferenceValueDiff(ReferenceValue currentReportColumn, ReferenceValue historicReportColumn) throws XMLStreamException {
		writer.writeStartElement(namespaceURI, currentReportColumn.getName());
		
		if (!currentReportColumn.getDescriptor().equals(historicReportColumn.getDescriptor())) {
			writer.writeAttribute(NS_DIFF, "priorDescriptor", historicReportColumn.getDescriptor());
		}
		writer.writeAttribute(namespaceURI, "Descriptor", currentReportColumn.getDescriptor());

		// It is not expected that the IDs will change at two successive points since a reference ID is not currently effective dated
		// However, we provide diffing of IDs just in case this functionality does appear at some point
		String currentIdValue, historicIdValue;
		for (String idType : currentReportColumn.ids()) {
			writer.writeStartElement(namespaceURI, "ID");
			writer.writeAttribute(namespaceURI, "type", idType);
			
			currentIdValue = currentReportColumn.getId(idType);
			historicIdValue = historicReportColumn.getId(idType);
			
			if (historicIdValue == null) {
				writer.writeAttribute(NS_DIFF, IS_ADDED, "true");
			} else if (!currentIdValue.equals(historicIdValue)) {
				writer.writeAttribute(NS_DIFF, PRIOR_VALUE, historicIdValue);
			}
			writer.writeCharacters(currentIdValue);
			
			writer.writeEndElement();
		}
		
		// Report on any IDs which were defined in the historic report but which are deleted here
		for (String idType : historicReportColumn.ids()) {
			currentIdValue = currentReportColumn.getId(idType);
			if (currentIdValue == null) {
				writer.writeStartElement(namespaceURI, "ID");
				writer.writeAttribute(namespaceURI, "type", idType);
				writer.writeAttribute(NS_DIFF, "isDeleted", "true");
				writer.writeCharacters(historicReportColumn.getId(idType));
				writer.writeEndElement();
			}
		}

		writer.writeEndElement();
	}
	
	public void outputNestedColumn(NestedColumn reportColumn, String newAttribute) throws XMLStreamException {
		writer.writeStartElement(namespaceURI, reportColumn.getName());
		if (newAttribute != null) {
			writer.writeAttribute(NS_DIFF, newAttribute, "true");
		}

		for (ReportColumn column : reportColumn.getOrderedColumns()) {
			column.writeColumn(this, newAttribute);
		}
		
		writer.writeEndElement();
	}

	public void outputNestedColumnDiff(NestedColumn currentReportColumn, NestedColumn historicReportColumn) throws XMLStreamException {
		writer.writeStartElement(namespaceURI, currentReportColumn.getName());
		
		Iterator<ReportColumn> currentIterator = currentReportColumn.getOrderedColumns().iterator();
		Iterator<ReportColumn> historicIterator = historicReportColumn.getOrderedColumns().iterator();

		ReportColumn currentColumn = null;
		ReportColumn historicColumn = null;
		
		String currentName, historicName;		
		while (currentIterator.hasNext() || historicIterator.hasNext()) {
			if ((currentColumn == null) && currentIterator.hasNext()) {
				currentColumn = currentIterator.next();
			}
			
			if ((historicColumn == null) && historicIterator.hasNext()) {
				historicColumn = historicIterator.next();
			}
			
			currentName = (currentColumn != null) ? currentColumn.getName() : null;
			historicName = (historicColumn != null) ? historicColumn.getName() : null;
			
			if ((currentName != null) && currentName.equals(historicName)) {
				// The column is present in both current and historical reports so we need to difference the data
				
				// There is a chance that a multi-instance field may only have one record in either current or historic and would be interpreted as a ReferenceValue instead of MultiValuedColumn
				// This would result in a type-cast exception
				if ((currentColumn instanceof MultiValuedColumn) && !(historicColumn instanceof MultiValuedColumn)) {
					MultiValuedColumn historicMultiValueColumn = new MultiValuedColumn(historicColumn.getIndex(), historicColumn.getName());
					historicMultiValueColumn.addLine(historicColumn);
					currentColumn.outputDiff(this, historicMultiValueColumn);
				} else if ((historicColumn instanceof MultiValuedColumn) && !(currentColumn instanceof MultiValuedColumn)) {
					MultiValuedColumn currentMultiValueColumn = new MultiValuedColumn(currentColumn.getIndex(), currentColumn.getName());
					currentMultiValueColumn.addLine(currentColumn);
					currentMultiValueColumn.outputDiff(this, historicColumn);
				} else {
					currentColumn.outputDiff(this, historicColumn);
				}
				currentColumn = null;
				historicColumn = null;
			} else {
				// There's a difference in names.  A column has been added or removed
				
				// If the current column is present in the other entry then the other column
				// must have been removed
				if ((currentName == null) || (historicReportColumn.getColumns().containsKey(currentName))) {
					historicColumn.writeColumn(this, IS_DELETED);
					historicColumn = null;
				} else {
					// The current column is not present in the other entry and so it must have been added
					currentColumn.writeColumn(this, IS_ADDED);
					currentColumn = null;
				}
			}
		}
		
		writer.writeEndElement();
	}

	public void outputMultiValuedColumn(MultiValuedColumn reportColumn, String newAttribute) throws XMLStreamException {
		for (ReportColumn column : reportColumn.getOrderedLines()) {
			column.writeColumn(this, newAttribute);
		}
	}

	public void outputMultiValuedColumnDiff(MultiValuedColumn currentReportColumn, MultiValuedColumn historicReportColumn) throws XMLStreamException {
		Iterator<ReportColumn> currentIterator = currentReportColumn.getOrderedLines().iterator();
		Iterator<ReportColumn> historicIterator = historicReportColumn.getOrderedLines().iterator();
		
		ReportColumn currentColumn = null;
		ReportColumn historicColumn = null;
		String	currentId, historicId;
		
		while (currentIterator.hasNext() || historicIterator.hasNext()) {
			if ((currentColumn == null) && currentIterator.hasNext()) {
				currentColumn = currentIterator.next();
			}
			
			while ((historicColumn == null) && historicIterator.hasNext()) {
				historicColumn = historicIterator.next();

				// when a entry is processed out of order it's value is removed from the keyed lines
				// So, we need to iterate through until we find an entry which has not been removed
				// from the column
				if (!historicReportColumn.getKeyedLines().containsKey(historicColumn.getValue())) {
					historicColumn = null;
				}
			}
			
			currentId = (currentColumn != null) ? currentColumn.getValue() : null;
			historicId = (historicColumn != null) ? historicColumn.getValue() : null;

			if (currentId == null && historicId == null) {
				// do nothing - avoids an NPE
				// this condition is possible when there are no more current records to evaluate and the last historic record was already evaluated as a differentialColumn and removed
			} else if (currentId == null) {
				historicColumn.writeColumn(this, IS_DELETED);
				historicColumn = null;
			} else if (historicId == null) {
				currentColumn.writeColumn(this, IS_ADDED);
				currentColumn = null;
			} else if (currentId.equals(historicId)) {
				// If the IDs are the same then do a simple diff of the columns 
				currentColumn.outputDiff(this, historicColumn);
				currentColumn = null;
				historicColumn = null;
			} else {
				// IDs are different so attempt to figure out whether this is an insertion or deletion

				// If the IDs are present in both reports but are not the same then we must be getting the IDs out of order
				if (currentReportColumn.getKeyedLines().containsKey(historicId) && historicReportColumn.getKeyedLines().containsKey(currentId)) {
					ReportColumn differentialColumn = historicReportColumn.getKeyedLines().get(currentId);
					currentColumn.outputDiff(this, differentialColumn);
					historicReportColumn.getKeyedLines().remove(currentId);
					currentColumn = null;
				} else if (currentReportColumn.getKeyedLines().containsKey(historicId)) {
					// If the current ID is not present in the other report then it must have been added
					currentColumn.writeColumn(this, IS_ADDED);
					currentColumn = null;
				} else {
					// This ID is present in the other column.  If the other ID is not available in the current
					// report then it must have been deleted
					historicColumn.writeColumn(this, IS_DELETED);
					historicColumn = null;
				}
			}
		}
	}
}
