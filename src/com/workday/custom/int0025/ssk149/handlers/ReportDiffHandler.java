package com.workday.custom.int0025.ssk149.handlers;

import java.util.Set;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;

import com.workday.custom.int0025.ssk149.model.ReportDiffException;
import com.workday.custom.int0025.ssk149.model.ReportEntry;
import com.workday.custom.int0025.ssk149.parsers.ReportEventReader;
import com.workday.custom.int0025.ssk149.writers.ReportEntryWriter;

public class ReportDiffHandler {
	private ReportEntryWriter writer;
	private boolean isOutputDiffOnly;
	
	public ReportDiffHandler(ReportEntryWriter writer, boolean isOutputDiffOnly) {
		super();
		this.writer = writer;
		this.isOutputDiffOnly = isOutputDiffOnly;
	}

	public void endOutput() throws Exception {
		writer.endOutput();
	}

	public void processReports(Set<String> currentIds, Set<String> historicIds, ReportEventReader currentReader, ReportEventReader historicReader) throws Exception {
		ReportEntry currentReportEntry = null;
		StartElement currentElement = null;

		ReportEntry historicReportEntry = null;
		StartElement historicElement = null;

		if (currentReader.hasNext()) {
			currentElement = currentReader.getReportEnty();
			currentReportEntry = null;
			
			if (currentElement != null) {
				currentReportEntry = new ReportEntry();
				currentReportEntry.load(currentReader);
			}
		}

		if (historicReader.hasNext()) {
			historicElement = historicReader.getReportEnty();
			historicReportEntry = null;
			
			if (historicElement != null) {
				historicReportEntry = new ReportEntry();
				historicReportEntry.load(historicReader);
			}
		}

		try {
			boolean isNeedHistoric;
			boolean isNeedCurrent;
			
			// If the ID of thie historical 
			while ((currentReportEntry != null) || (historicReportEntry != null)) {
				isNeedHistoric = false;
				isNeedCurrent = false;

				// When either the current or historic ReportEvents are null then this means that
				// the relevant report has no more report entries.  We assume that both reports order the entries
				// in the same manner and so if both are non-null then 
				if ((currentReportEntry != null) && (historicReportEntry != null)) {
					String currentId = currentReportEntry.getUniqueId();
					String historicId = historicReportEntry.getUniqueId();
					
					// If both IDs are the same then difference the values
					if (currentId.equals(historicId)) {
						currentReportEntry.outputDiff(writer, isOutputDiffOnly, historicReportEntry);
						isNeedHistoric = true;
						isNeedCurrent = true;
					} else {
						// Otherwise we need to figure out whether we've had removals or additions
						
						// If the historic ID is present in the current report then the current
						// value must be an addition.  So, we need to report this as an addition
						// and read another current value whilst keeping our present historic value
						if (currentIds.contains(historicId) ) {
							currentReportEntry.output(writer, ReportEntryWriter.IS_ADDED);
							isNeedCurrent = true;
						} else if (historicIds.contains(currentId)) {
							// Otherwise, if the current ID is in the historic report then the current
							// historic value must have been removed
							historicReportEntry.output(writer, ReportEntryWriter.IS_DELETED);
							isNeedHistoric = true;
						} else {
							// The current entry isn't present in historic and historic entry isn't present in
							// the current report.  So, we assume that the historic item has been removed and
							// the current value has been added.  We don't know which order these need to be in
							// so we output the deletion first.
							historicReportEntry.output(writer, ReportEntryWriter.IS_DELETED);
							currentReportEntry.output(writer, ReportEntryWriter.IS_ADDED);
							isNeedCurrent = true;
							isNeedHistoric = true;
						}
					}
				} else if (currentReportEntry != null) {
					// Historic must be null so at this point we must be dealing with an addition of an item to the file
					currentReportEntry.output(writer, ReportEntryWriter.IS_ADDED);
					isNeedCurrent = true;
				} else if (historicReportEntry != null) {
					// current must be null at this point so we must be dealing with a removal of the historic item
					historicReportEntry.output(writer, ReportEntryWriter.IS_DELETED);
					isNeedHistoric = true;
				}

				if (isNeedCurrent) {
					isNeedCurrent = false;
					currentElement = currentReader.getReportEnty();
					if (currentElement != null) {
						currentReportEntry.load(currentReader);
					} else {
						currentReportEntry = null;
					}
				}
				
				if (isNeedHistoric) {
					isNeedHistoric = false;
					historicElement = historicReader.getReportEnty();
					if (historicElement != null) {
						historicReportEntry.load(historicReader);
					} else {
						historicReportEntry = null;
					}
				}
			}
		} catch (XMLStreamException e) {
			System.out.println("currentReportEntry = " + (currentReportEntry == null ? "null" : currentReportEntry));
			System.out.println("historicReportEntry = " + (historicReportEntry == null ? "null" : historicReportEntry));
			throw new ReportDiffException("An error occurred differencing the documents", e);
		} catch (Exception t) {
			System.out.println("currentReportEntry = " + (currentReportEntry == null ? "null" : currentReportEntry));
			System.out.println("historicReportEntry = " + (historicReportEntry == null ? "null" : historicReportEntry));
			t.printStackTrace(System.out);
			throw new RuntimeException("An error occurred differencing the documents", t);
		}
		
	}
}
