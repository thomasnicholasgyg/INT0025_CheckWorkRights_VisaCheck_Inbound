package com.workday.custom.int0025.ssk149.handlers;

import java.util.Set;

public class ListBuilderHandler {

	private Set<String> theList;
	
	public ListBuilderHandler(Set<String> theList) {
		super();
		this.theList = theList;
	}

	public void processId(String value) throws Exception {
		theList.add(value);
	}
}
