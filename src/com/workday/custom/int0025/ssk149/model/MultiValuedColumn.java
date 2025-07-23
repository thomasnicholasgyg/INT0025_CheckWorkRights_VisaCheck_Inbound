package com.workday.custom.int0025.ssk149.model;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.xml.stream.XMLStreamException;

import com.workday.custom.int0025.ssk149.writers.ReportEntryWriter;

public class MultiValuedColumn extends ReportColumn {
	private Map<String, ReportColumn> keyedLines = new TreeMap<String, ReportColumn>();
	private Vector<ReportColumn> orderedLines = new Vector<ReportColumn>();

	public MultiValuedColumn(int index, String name) {
		super(index, name);
	}
	
	public Map<String, ReportColumn> getKeyedLines() {
		return keyedLines;
	}

	public Vector<ReportColumn> getOrderedLines() {
		return orderedLines;
	}

	public void addLine(ReportColumn column) {
		orderedLines.add(column);
		String key = column.getValue();
		
		if (keyedLines.containsKey(key)) {
			throw new ReportDiffException("Line containing duplicate key " + key + " in nested column " + getName());
		}
		keyedLines.put(key, column);
	}

	/**
	 * Value is largely meaningless for a multi-line column, but if one is requested
	 * then we return the value of the first line
	 */
	@Override
	public String getValue() {
		return  (orderedLines.size() > 0) ? orderedLines.get(0).getValue() : "";
	}

	@Override
	/**
	 * Difference the column and output it.  Note that this is potentially destructive of the column information
	 */
	public void outputDiff(ReportEntryWriter writer, ReportColumn historicReportColumn) throws XMLStreamException {
		writer.outputMultiValuedColumnDiff(this, (MultiValuedColumn)historicReportColumn);
	}

	@Override
	public void writeColumn(ReportEntryWriter writer, String newAttribute) throws XMLStreamException {
		writer.outputMultiValuedColumn(this, newAttribute);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MultiValuedColumn)) {
			return false;
		}
		
		MultiValuedColumn other = (MultiValuedColumn)obj;
		
		if (orderedLines.size() != other.orderedLines.size()) {
			return false;
		}
		
		ReportColumn otherColumn;
		for(ReportColumn column : orderedLines) {
			otherColumn = other.keyedLines.get(column.getValue());
			if (!column.equals(otherColumn)) {
				return false;
			}
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
				+ ((orderedLines == null) ? 0 : orderedLines.hashCode());
		result = prime * result + ((keyedLines == null) ? 0 : keyedLines.hashCode());
		return result;
	}
}
