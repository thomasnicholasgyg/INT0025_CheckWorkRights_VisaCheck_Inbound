package com.workday.custom.int0025.ssk149.model;

import javax.xml.namespace.QName;

public class ReportContext {
	QName	descriptor;
	QName	type;
	String	namespaceURI;

	/**
	 * Number of columns found in the report
	 */

	int		numColumns;
	
	ReportContext() {
		
	}
	
	public void setNamespaceURI(String uri) {
		if ( namespaceURI == null) {
			namespaceURI = uri;
			descriptor = new QName(uri, "Descriptor");
			type		= new QName(uri, "type");
		}
	}
	
	public QName getDescriptorName() {
		return descriptor;
	}
	
	public QName getTypeName() {
		return type;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public void setNumColumns(int num_columns) {
		numColumns = num_columns;
	}
}
