package com.workday.custom.int0025.ssk147;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.capeclear.capeconnect.attachments.io.FileBackedManagedData;
import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;

public class FlexLog {
	private Logger log = LogControl.getLogger(getClass());

	public static final String PARAMETER_TIMESTAMP = "timestamp";
	public static final String IN_PARAMETER_LOG_LEVEL = "inLogLevel";

	private String logName;
	private int maxLinesPerFile = 0;
	private int retention;
	private String filename;
	private String separator;
	private String documentTag;
	private String worksheetTabName;
	private boolean isDeliverable;
	
	private FileBackedManagedData fbmd;
	private PrintWriter writer;
	private List<FlexLogField> fields;
	private final Semaphore binarySemaphore = new Semaphore(1, true);
	
	private int countOfDebug = 0;
	private int countOfInfo = 0;
	private int countOfWarn = 0;
	private int countOfError = 0;
	private int countOfCritical = 0;
	
	private int partialFileCount = 0;
	private int totalLineCount = 0;

	private String newline = "\n";
	private final String DOUBLE_QUOTE = "\"";

	public FlexLog(String logName, List<FlexLogField> fields, int maxPerFile, int retention, String baseFilename, String separator, String documentTag, String worksheetTabName, boolean isDeliverable) throws Throwable {
		super();
		
		this.fields = new ArrayList<FlexLogField>();
		this.fields.add(new FlexLogField(PARAMETER_TIMESTAMP, "Timestamp"));
		this.fields.add(new FlexLogField(IN_PARAMETER_LOG_LEVEL, "Severity"));
		
		this.logName = logName;
		this.fields.addAll(fields);
		this.fbmd = null;
		this.writer = null;
		
		this.maxLinesPerFile = (maxPerFile <= 0) ? 0 : maxPerFile;
		this.retention = retention;
		this.filename = baseFilename;
		this.separator = separator;
		this.documentTag = documentTag;
		this.worksheetTabName = worksheetTabName;
		this.isDeliverable = isDeliverable;
	}

	public FileBackedManagedData getLogData() {
		closeLog();
		return this.fbmd;
	}
	
    public void setLogData(FileBackedManagedData fbmd) throws Throwable {
		this.fbmd = fbmd;
		this.writer = new PrintWriter(fbmd.getOutputStream());

		// If the log has no potential to be split to partial files (each of which needing its own header), then go ahead and emit the header for the monolith file now.
		if (this.maxLinesPerFile == 0) {
			writer.write(getHeaderLine());
		}
	}
    
    public String escapeContentForRFC4180(String content) {
    	boolean isContainsDoubleQuote = content.contains(DOUBLE_QUOTE);
    	
    	if (content.contains(separator) || content.contains(newline) || isContainsDoubleQuote) {
    		StringBuilder sb = new StringBuilder(DOUBLE_QUOTE);
    		sb.append(isContainsDoubleQuote ? content.replace(DOUBLE_QUOTE, "\"\"") : content);
    		sb.append(DOUBLE_QUOTE);
    		return sb.toString();
    	} else {
    		return content;
    	}
    }

	public String getHeaderLine() {
		StringBuilder sb = new StringBuilder();
    	for (FlexLogField field : fields) {
    		if (sb.length() > 0) {
    			sb.append(separator);
    		}
    		sb.append(escapeContentForRFC4180(field.getHeader()));
    	}
    	sb.append(newline);
    	
    	return sb.toString();
	}
    
	public List<FlexLogField> getFields() {
		return fields;
	}
	
    public String getLogName() {
		return logName;
	}

	public int getMaxLinesPerFile() {
		return maxLinesPerFile;
	}

	public String getRetention() {
		return "P" + retention + "D";
	}

	public String getStorageFilename() {
		return ((maxLinesPerFile > 0 && totalLineCount > maxLinesPerFile) ? filename + "_" + (++partialFileCount) : filename) + ".csv";
	}

	public String getSeparator() {
		return separator;
	}

	public String getDocumentTag() {
		return documentTag;
	}

	public String getWorksheetTabName() {
		return worksheetTabName;
	}

	public boolean isDeliverable() {
		return isDeliverable;
	}

	public String getNewline() {
		return newline;
	}

	public void writeLine(FlexLogLevel level, String logEntry) throws Throwable {
    	binarySemaphore.acquire();
    	
    	try {
	    	writer.write(logEntry);
	    	
	    	switch(level) {
		    	case debug:
		    		countOfDebug++;
		    		break;
		    		
		    	case info:
		    		countOfInfo++;
		    		break;
		    		
		    	case warn:
		    		countOfWarn++;
		    		break;
		    		
		    	case error:
		    		countOfError++;
		    		break;
		    		
		    	case critical:
		    		countOfCritical++;
		    		break;
	    	}

	    	totalLineCount++;
	    	writer.flush();
    	} finally {
    		binarySemaphore.release();
    	}
    }

	private void closeLog() {
		try {
			if (this.writer != null) {
				this.writer.flush();
				this.writer.close();
			}
		} catch (Throwable t) {
    		log.error(t.getMessage(), t);
    		throw new RuntimeException(t);
		} finally {
			this.writer = null;
		}
	}
	
	public int getCountOfDebug() {
		return countOfDebug;
	}

	public int getCountOfInfo() {
		return countOfInfo;
	}

	public int getCountOfWarn() {
		return countOfWarn;
	}

	public int getCountOfError() {
		return countOfError;
	}

	public int getCountOfCritical() {
		return countOfCritical;
	}

	public int getPartialFileCount() {
		return partialFileCount;
	}

	public int getTotalLineCount() {
		return totalLineCount;
	}
	
	public boolean isCriticalEntries() {
		return countOfCritical > 0;
	}

	public boolean isErrorEntries() {
		return countOfError > 0;
	}
	
	public boolean isWarningEntries() {
		return countOfWarn > 0;
	}

	public boolean isInfoEntries() {
		return countOfInfo > 0;
	}

	public boolean isDebugEntries() {
		return countOfDebug > 0;
	}
}
