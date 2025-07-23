package com.workday.custom.int0025.ssk149.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLStreamException;

import com.workday.custom.int0025.ssk149.writers.ReportEntryWriter;

public class ReferenceValue extends ReportColumn {
	private Map<String, String> ids = new HashMap<String, String>();
	private String descriptor;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descriptor == null) ? 0 : descriptor.hashCode());
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
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
		
		if (!(obj instanceof ReferenceValue)) {
			return false;
		}

		ReferenceValue other = (ReferenceValue)obj;
		
		if (descriptor == null) {
			if (other.descriptor != null) {
				return false;
			}
		} else if (!descriptor.equals(other.descriptor)) {
			return false;
		}
		
		if (ids == null) {
			if (other.ids != null) {
				return false;
			}
		} else if (!ids.equals(other.ids)) {
			return false;
		}
		
		return true;
	}

	public ReferenceValue(int index, String name) {
		super(index, name);
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}
	
	public String getDescriptor() {
		return descriptor;
	}
	
	public void putID(String type, String value) {
		ids.put(type, value);
	}

	@Override
	public String getValue() {
		return ids.get("WID");
	}
	
	@Override
	public void outputDiff(ReportEntryWriter writer, ReportColumn historicReportColumn) throws XMLStreamException {
		writer.outputReferenceValueDiff(this, (ReferenceValue)historicReportColumn);
	}
	
	public Set<String> ids() {
		return ids.keySet();
	}
	
	public String getId(String idType) {
		return ids.get(idType);
	}

	/**
	 * Write out the reference value as an XML column
	 * 
	 * @param writer
	 * @param namespace_uri		The namespace into which the element is to be written
	 * @param bool_attribute	If non-null this specifies the name of an attribute which will be output with a
	 * value of "true".  Typically this is used to output the isAdded and isDeleted attributes. 
	 */
	
	@Override
	public void writeColumn(ReportEntryWriter writer, String newAttribute) throws XMLStreamException {
		writer.outputReferenceValue(this, newAttribute);
	}
	
}
