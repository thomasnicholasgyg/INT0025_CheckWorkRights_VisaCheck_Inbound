package com.workday.custom.int0025.ssk149.model;

import javax.xml.stream.XMLStreamException;

import com.workday.custom.int0025.ssk149.writers.ReportEntryWriter;

/**
 * 
 * Abstract class to hold the differencing output 
 * @author doug.lee
 *
 */
public abstract class ReportColumn implements Comparable<ReportColumn> {
	protected int index;	// Position of the column within it's parent
	protected String name;
	
	/**
	 * Create a new column at the specified position and with the specified name
	 * @param index
	 * @param name
	 */
	public ReportColumn(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public abstract void outputDiff(ReportEntryWriter writer, ReportColumn historicReportColumn) throws XMLStreamException ;
	public abstract void writeColumn(ReportEntryWriter writer, String newAttribute) throws XMLStreamException;
	public abstract String getValue();

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	@Override
	public int compareTo(ReportColumn o) {
		return index - o.index;
	}
}
