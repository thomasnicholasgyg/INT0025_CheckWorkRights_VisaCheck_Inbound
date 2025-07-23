package com.workday.custom.int0025.ssk149.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

import com.workday.custom.int0025.ssk149.writers.ReportEntryWriter;

/**
 * Multiple lines within a RelatedObjectColumn are unordered.  Columns within a line are ordered
 * @author Doug Lee
 */

public class NestedColumn extends ReportColumn {
	private Vector<ReportColumn> orderedColumns = new Vector<ReportColumn>();
	private Map<String, ReportColumn> columns = new HashMap<String,ReportColumn>();
	
	public NestedColumn(int index, String name) {
		super(index, name);
	}
	
	public Vector<ReportColumn> getOrderedColumns() {
		return orderedColumns;
	}

	public Map<String, ReportColumn> getColumns() {
		return columns;
	}

	/**
	 * Add a row to the 
	 * @param row
	 */
	void addColumn(ReportColumn column) {
		orderedColumns.add(column);
		columns.put(column.getName(), column);
	}
	
	@Override
	public String getValue() {
		return orderedColumns.get(0).getValue();
	}

	@Override
	public void outputDiff(ReportEntryWriter writer, ReportColumn historicReportColumn) throws XMLStreamException {
		writer.outputNestedColumnDiff(this, (NestedColumn)historicReportColumn);
	}
	
	public Iterable<ReportColumn> columns() {
		return orderedColumns;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof NestedColumn)) {
			return false;
		}
		
		NestedColumn nested = (NestedColumn)o;
		
		if (!orderedColumns.equals(nested.orderedColumns)) {
			return false;
		}

		if (!name.equals(nested.name)) {
			return false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((orderedColumns == null) ? 0 : orderedColumns.hashCode());
		result = prime * result + ((columns == null) ? 0 : columns.hashCode());
		return result;
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
		writer.outputNestedColumn(this, newAttribute);
	}
	
}
