package com.workday.custom.int0025.ssk147;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;

import org.apache.commons.lang.StringUtils;

import com.capeclear.assembly.impl.AssemblyUtils;
import com.capeclear.capeconnect.attachments.io.FileBackedManagedData;
import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.MediationMessage;
import com.capeclear.mediation.impl.DataHandlerSource;
import com.capeclear.xml.utils.XmlUtils;
import com.workday.custom.int0025.DateUtils;
import com.workday.custom.int0025.SSKUtils;
import com.workday.esb.StringList;


/**
 * Custom mediation
 *
 * TODO For more integration with the Assembly Editor, add a Component
 * annotation to the class and Property annotations to bean pattern methods.
 */
public class FlexLogBean {
	private Logger log = LogControl.getLogger(getClass());

	private final String CONTENT_TYPE = "text/csv";
	private final String DEFAULT_LOG_NAME = "flex-log";
	
	private final String IN_PARAMETER_LOG_NAME = "inLogName";
	private final String IN_VAR_CONFIG = "int0025FlexLogConfiguration147";
	private final String XSLT_MESSAGE_SEPARATOR = "<int0025:fl>";
	
	private Map<String, FlexLog> logConfiguration;
	
	public FlexLogBean() {
		logConfiguration = new HashMap<String, FlexLog>();
	}

	public void configureNewLog(MediationContext mc) throws Throwable {
		List<FlexLog> logs = null;
		XMLEventReader eventReader = null;
		
		try {
			eventReader = XmlUtils.getXMLInputFactory().createXMLEventReader(SSKUtils.getVariableInputStream(mc, IN_VAR_CONFIG), "utf-8");
			
			FlexLogConfigurationReader reader = new FlexLogConfigurationReader(eventReader);

			logs = reader.parseConfiguration(IN_VAR_CONFIG);
			
		} catch (Throwable t) {
    		log.error(t.getMessage(), t);
    		throw new RuntimeException(t);
		} finally {
    		try {
	    		if (eventReader != null) {
	    			eventReader.close();
	    		}
    		} catch (Throwable t) {
        		log.error(t.getMessage(), t);
        		throw new RuntimeException(t);
    		}
		}
		
		if (logs == null || logs.size() == 0) {
    		log.info("SSK Flex Log configuration has not been defined.  Flex Log will not be configured.");
		} else {
			for (FlexLog flog : logs) {
				if (logConfiguration.containsKey(flog.getLogName())) {
					throw new FlexLogConfigurationException("The integration already has a configured Flex Log of the name ["+ flog.getLogName() +"]");
				}
				
				flog.setLogData(createManagedData(mc));
				logConfiguration.put(flog.getLogName(), flog);
			}
		}
		
		mc.getVariables().remove(IN_VAR_CONFIG);
    }
	
	public boolean isConfigured() {
		return !this.logConfiguration.isEmpty();
	}

    private FileBackedManagedData createManagedData(MediationContext mc) {
    	FileBackedManagedData returnValue = new FileBackedManagedData(CONTENT_TYPE);
    	returnValue.setLongLived(true);
        mc.addDisposable(returnValue);
        
        return returnValue;
    }
	
	public void writeLogEntry(MediationContext mc) throws Throwable {
		String logName = StringUtils.defaultIfEmpty(getLogContextValue(mc, IN_PARAMETER_LOG_NAME), DEFAULT_LOG_NAME);
		FlexLog flog = logConfiguration.get(logName);
		List<FlexLogField> fields = flog.getFields();
		
		StringBuilder sb = new StringBuilder();
		FlexLogLevel level = null;

		for (FlexLogField f : fields) {
			if (sb.length() > 0) {
				sb.append(flog.getSeparator());
			}
			if (FlexLog.PARAMETER_TIMESTAMP.equals(f.getParameter())) {
				sb.append(flog.escapeContentForRFC4180(getTimestamp()));
			} else if (FlexLog.IN_PARAMETER_LOG_LEVEL.equals(f.getParameter())) {
				String content = getLogContextValue(mc, f.getParameter());
				if (StringUtils.isBlank(content)) {
					throw new FlexLogException("Field ["+ f.getParameter() +"] is required, but no log entry value was provided.");
				}
				level = FlexLogLevel.parse(content);
				sb.append(content);
			} else {
				String content = getLogContextValue(mc, f.getParameter());
				sb.append(flog.escapeContentForRFC4180(content));
			}
		}
		
		sb.append(flog.getNewline());

		flog.writeLine(level, sb.toString());
    }
	
	public void writeLogEntry(String logName, String severity, String... orderedValues) throws Throwable {
		FlexLog flog = logConfiguration.get(logName);
		
		StringBuilder sb = new StringBuilder();
		FlexLogLevel level = FlexLogLevel.parse(severity);

		sb.append(flog.escapeContentForRFC4180(getTimestamp()));
		sb.append(flog.getSeparator());
		sb.append(level.toString());

		for (int i = 0; i < orderedValues.length; i++) {
			sb.append(flog.getSeparator());
			sb.append(flog.escapeContentForRFC4180(orderedValues[i]));
		}
		
		sb.append(flog.getNewline());

		flog.writeLine(level, sb.toString());
	}

	public String writeLogEntryFromXsltExtension(String logName, String severity, String... orderedValues) {
		try {
			FlexLog flog = logConfiguration.get(logName);
			
			StringBuilder sb = new StringBuilder();
			FlexLogLevel level = FlexLogLevel.parse(severity);
	
			sb.append(flog.escapeContentForRFC4180(getTimestamp()));
			sb.append(flog.getSeparator());
			sb.append(level.toString());
	
			for (int i = 0; i < orderedValues.length; i++) {
				sb.append(flog.getSeparator());
				sb.append(flog.escapeContentForRFC4180(orderedValues[i]));
			}
			
			sb.append(flog.getNewline());
	
			flog.writeLine(level, sb.toString());
			
			return null;
		} catch (Throwable t) {
			log.error("Flex Log Entry Error", t);
			return t.getMessage();
		}
	}
	
	public void writeLogEntries(StringList entries) throws Throwable {
		writeLogEntries(entries.toArray(new String[0]));
	}
	
	public void writeLogEntries(String[] entries) throws Throwable {
		if (entries != null && entries.length > 0) {
			StringBuilder sb = new StringBuilder();
			FlexLog flog = null;
			
			for (int i = 0; i < entries.length; i++) {
				sb.setLength(0);
				String[] values = entries[i].split(XSLT_MESSAGE_SEPARATOR);
				
				String logName = values[0];
				if (flog == null || !flog.getLogName().equals(logName)) {
					flog = logConfiguration.get(logName);
				}

				String level = values[2];
				sb.append(values[1]);
				sb.append(flog.getSeparator());
				sb.append(level);
				
				for (int v = 3; v < values.length; v++) {
					sb.append(flog.getSeparator());
					sb.append(flog.escapeContentForRFC4180(values[v]));
				}
				
				sb.append(flog.getNewline());
		
				flog.writeLine(FlexLogLevel.parse(level), sb.toString());
			}
		}
	}

	private String getTimestamp() {
		return DateUtils.convertDateTimeToXmlString(Calendar.getInstance().getTime());
	}
    
    private String getLogContextValue(MediationContext mc, String propertyName) {
    	Object o = mc.getProperty(propertyName);
    	mc.removeProperty(propertyName); //remove contextProperty to avoid chance of stale values on the next entry written 
    	return (o == null) ? "" : (String)o;
    }

	public String[] getAllLogNames() {
		return this.logConfiguration.keySet().toArray(new String[0]);
	}
	
	public void setLogToMessageRootPart(MediationContext mc, String logName) throws Throwable {
		mc.setMessage(createMessage(this.logConfiguration.get(logName).getLogData()));
	}

	private MediationMessage createMessage(FileBackedManagedData fbmd) throws IOException {
		MediationMessage returnValue = AssemblyUtils.getMediationHelper().createMessage();
        returnValue.setRootPart(new DataHandlerSource(fbmd.getDataSource()), fbmd.getContentType());
        returnValue.setHeader("Content-Type", fbmd.getContentType());

        return returnValue;
	}

	public boolean isSplitLogFile(String logName) {
		FlexLog flog = this.logConfiguration.get(logName);
		return flog.getMaxLinesPerFile() > 0;
	}
	
	public int getMaxLinesPerFile(String logName) {
		return this.logConfiguration.get(logName).getMaxLinesPerFile();
	}

	public String getStorageFilename(String logName) {
		return this.logConfiguration.get(logName).getStorageFilename();
	}

	public String getRetention(String logName) {
		return this.logConfiguration.get(logName).getRetention();
	}
	
	public String getDocumentTag(String logName) {
		return this.logConfiguration.get(logName).getDocumentTag();
	}
	
	public boolean isDeliverable(String logName) {
		return this.logConfiguration.get(logName).isDeliverable();
	}
	
	public FlexLog getFlexLog(String logName) {
		return this.logConfiguration.get(logName);
	}
}
	
