package com.workday.custom.int0025.ssk149.model;

import javax.xml.stream.XMLStreamException;

import com.workday.custom.int0025.ssk149.writers.ReportEntryWriter;

public class ReportAtomicValue extends ReportColumn {
	private String value;
	
	ReportAtomicValue(int index, String name, String value) {
		super(index, name);
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value == null ? "" : value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof ReportAtomicValue)) {
			return false;
		}
		
		ReportAtomicValue other = (ReportAtomicValue) obj;
		
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}

		if ( !name.equals( other.name ) ) {
			return false;
		}
		
		return true;
	}

	@Override
	public void outputDiff(ReportEntryWriter writer, ReportColumn historicReportColumn) throws XMLStreamException {
		writer.outputReportAtomicValueDiff(this, (ReportAtomicValue)historicReportColumn);
	}

	/**
	 * Write out the atomic value as an XML column
	 * 
	 * @param writer
	 * @param namespace_uri		The namespace into which the element is to be written
	 * @param bool_attribute	If non-null this specifies the name of an attribute which will be output with a
	 * value of "true".  Typically this is used to output the isAdded and isDeleted attributes. 
	 */	

	@Override
	public void writeColumn(ReportEntryWriter writer, String newAttribute) throws XMLStreamException {
		writer.outputReportAtomicValue(this, newAttribute);
	}

}
