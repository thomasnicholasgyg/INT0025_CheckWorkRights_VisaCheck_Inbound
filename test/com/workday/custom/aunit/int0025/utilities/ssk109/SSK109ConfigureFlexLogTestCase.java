package com.workday.custom.aunit.int0025.utilities.ssk109;

import java.util.List;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.CommonTestCase;
import com.workday.custom.int0025.ssk147.FlexLog;
import com.workday.custom.int0025.ssk147.FlexLogBean;
import com.workday.custom.int0025.ssk147.FlexLogField;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSK109ConfigureFlexLogTestCase extends CommonTestCase {

	public static final String VAR_LOG1 = "log1";
	public static final String VAR_LOG2 = "log2";
	public static final String VAR_LOG3 = "log3";
	public static final String VAR_LOG4 = "log4";
	public static final String VAR_LOG5 = "log5";
	public static final String VAR_LOG6 = "log6";
	public static final String VAR_SOURCE = "int0025FlexLogConfiguration147";
	public static final String VAR_FLEX_LOG = "flex-log";
	
	public static final String PROP_PARAMETER_IN_XML_CONFIG = "inPathToXmlConfiguration";
	
	public static final String PROP_PARAMETER_OUT_BEAN = "int0025FlexLog";
	
	public static final String RESULT_CSV_FRESH_LOG = "test/int0025/int0025147/SSK147_FreshLog.csv";
	public static final String MOCK_XML_COMPLEX_CONFIG = "test/int0025/int0025147/SSK147_Configuration_IsolatedAttributes.xml";
	public static final String MOCK_XML_EMPTY_CONFIG = "test/int0025/int0025147/SSK147_Configuration_NoLogs.xml";
	public static final String MOCK_XML_ONE_CONFIG = "test/int0025/int0025147/SSK147_Configuration_OneLog.xml";
	public static final String MOCK_XML_TWO_CONFIG = "test/int0025/int0025147/SSK147_Configuration_TwoLogs.xml";
	public static final String MOCK_XML_DUPLICATE_CONFIG = "test/int0025/int0025147/SSK147_Configuration_DuplicateLogs.xml";
	public static final String MOCK_XML_NOT_CONFIG = "test/int0025/int0025147/SSK147_XML_NotConfiguration.xml";
	public static final String MOCK_XSD = "test/int0025/int0025147/SSK147_FlexLogs.xsd";
	public static final String MOCK_NOT_FOUND_CONFIG = "test/int0025/int0025147/SSK147_ThisFileDoesNotExist.xml";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

	}	

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="InitializeFrameworkThenRunMain")
	public void testComplexConfiguration() throws Exception {}
	
	@UnitTest(startComponent="InitializeFrameworkThenRunMain")
	public void testOneLogConfiguration() throws Exception {}
	
	@UnitTest(startComponent="InitializeFrameworkThenRunMain")
	public void testTwoLogsConfiguration() throws Exception {}
	
	@UnitTest(startComponent="InitializeFrameworkThenRunMain")
	public void testEmptyConfiguration() throws Exception {}
	
	@UnitTest(startComponent="InitializeFrameworkThenRunMain")
	public void testDuplicateConfiguration() throws Exception {
		expectHandledException();
	}
	
	@UnitTest(startComponent="InitializeFrameworkThenRunMain")
	public void testNotConfiguration() throws Exception {
		expectHandledException();
	}
	
	@UnitTest(startComponent="InitializeFrameworkThenRunMain")
	public void testFileNotFound() throws Exception {}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		if (!testsExpectingUnhandledExceptions.contains(getName())) {
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_SOURCE), ctx.getVariables().containsKey(VAR_SOURCE));

			Object bean = this.ctx.getProperty(PROP_PARAMETER_OUT_BEAN);
			FlexLogBean int0025FlexLog;
			
			if (getName().contains("Complex")) {
				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_PARAMETER_OUT_BEAN), bean);
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_PARAMETER_OUT_BEAN), bean instanceof FlexLogBean);
				int0025FlexLog = (FlexLogBean)bean;

				for (int i = 1; i <= 6; i++) {
					FlexLog log = int0025FlexLog.getFlexLog("log" + i); 
					List<FlexLogField> fields = null;
					switch (i) {
						case 1:
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 4, log.getFields().size());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), "log" + i, log.getLogName());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 0, log.getMaxLinesPerFile());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P30D", log.getRetention());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "filename1.csv", log.getStorageFilename());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), ",", log.getSeparator());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "", log.getDocumentTag());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "", log.getWorksheetTabName());
	
							fields = log.getFields();
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inLogMessage"+i, fields.get(2).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary"+i, fields.get(2).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inLogMessageDetail"+i, fields.get(3).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "Details"+i, fields.get(3).getHeader());
							break;
						
						case 2:
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 4, log.getFields().size());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), "log" + i, log.getLogName());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 2, log.getMaxLinesPerFile());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P30D", log.getRetention());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "FlexLog.csv", log.getStorageFilename());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), ",", log.getSeparator());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "", log.getDocumentTag());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "", log.getWorksheetTabName());
	
							fields = log.getFields();
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inLogMessage"+i, fields.get(2).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary"+i, fields.get(2).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inLogMessageDetail"+i, fields.get(3).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "Details"+i, fields.get(3).getHeader());
							break;
	
						case 3:
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 4, log.getFields().size());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), "log" + i, log.getLogName());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 0, log.getMaxLinesPerFile());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P42D", log.getRetention());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "FlexLog.csv", log.getStorageFilename());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), ",", log.getSeparator());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "", log.getDocumentTag());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "", log.getWorksheetTabName());
	
							fields = log.getFields();
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inLogMessage"+i, fields.get(2).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary"+i, fields.get(2).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inLogMessageDetail"+i, fields.get(3).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "Details"+i, fields.get(3).getHeader());
							break;
						
						case 4:
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 4, log.getFields().size());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), "log" + i, log.getLogName());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 0, log.getMaxLinesPerFile());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P30D", log.getRetention());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "FlexLog.csv", log.getStorageFilename());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), "~", log.getSeparator());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "", log.getDocumentTag());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "", log.getWorksheetTabName());
	
							fields = log.getFields();
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inLogMessage"+i, fields.get(2).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary"+i, fields.get(2).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inLogMessageDetail"+i, fields.get(3).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "Details"+i, fields.get(3).getHeader());
							break;
	
						case 5:
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 4, log.getFields().size());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), "log" + i, log.getLogName());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 0, log.getMaxLinesPerFile());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P30D", log.getRetention());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "FlexLog.csv", log.getStorageFilename());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), ",", log.getSeparator());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "INT_Log_Aggregator", log.getDocumentTag());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "", log.getWorksheetTabName());
	
							fields = log.getFields();
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inLogMessage"+i, fields.get(2).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary"+i, fields.get(2).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inLogMessageDetail"+i, fields.get(3).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "Details"+i, fields.get(3).getHeader());
							break;
						
						case 6:
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 4, log.getFields().size());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), "log" + i, log.getLogName());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 0, log.getMaxLinesPerFile());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P30D", log.getRetention());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "FlexLog.csv", log.getStorageFilename());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), ",", log.getSeparator());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "", log.getDocumentTag());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "TheLog", log.getWorksheetTabName());
	
							fields = log.getFields();
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inLogMessage"+i, fields.get(2).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary"+i, fields.get(2).getHeader());
							
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inLogMessageDetail"+i, fields.get(3).getParameter());
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "Details"+i, fields.get(3).getHeader());
							break;
	
						default:
							break;
					}
						
				}
				
			} else if (getName().contains("OneLog")) {
				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_PARAMETER_OUT_BEAN), bean);
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_PARAMETER_OUT_BEAN), bean instanceof FlexLogBean);
				int0025FlexLog = (FlexLogBean)bean;

				FlexLog log = int0025FlexLog.getFlexLog(VAR_FLEX_LOG);
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 4, log.getFields().size());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), VAR_FLEX_LOG, log.getLogName());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 32, log.getMaxLinesPerFile());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P42D", log.getRetention());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "filename1.csv", log.getStorageFilename());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), "~", log.getSeparator());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "INT_Log_Aggregator", log.getDocumentTag());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "TheLog", log.getWorksheetTabName());
				
				List<FlexLogField> fields = log.getFields();
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inLogMessage1", fields.get(2).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary1", fields.get(2).getHeader());
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inLogMessageDetail1", fields.get(3).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "Details1", fields.get(3).getHeader());
	
			} else if (getName().contains("TwoLogs")) {
				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_PARAMETER_OUT_BEAN), bean);
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_PARAMETER_OUT_BEAN), bean instanceof FlexLogBean);
				int0025FlexLog = (FlexLogBean)bean;

				FlexLog log = int0025FlexLog.getFlexLog(VAR_LOG1);
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 4, log.getFields().size());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), VAR_LOG1, log.getLogName());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 32, log.getMaxLinesPerFile());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P42D", log.getRetention());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "filename1.csv", log.getStorageFilename());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), "~", log.getSeparator());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "INT_Log_Aggregator", log.getDocumentTag());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "TheLog", log.getWorksheetTabName());
				
				List<FlexLogField> fields = log.getFields();
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inLogMessage1", fields.get(2).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary1", fields.get(2).getHeader());
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inLogMessageDetail1", fields.get(3).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "Details1", fields.get(3).getHeader());
				
				log = int0025FlexLog.getFlexLog(VAR_FLEX_LOG);
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field count"), 6, log.getFields().size());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "logName"), VAR_FLEX_LOG, log.getLogName());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "maxLinesPerFile"), 262, log.getMaxLinesPerFile());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "retention"), "P421D", log.getRetention());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "filename"), "filename2.csv", log.getStorageFilename());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "separator"), "|", log.getSeparator());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "documentTag"), "INT_Logs2Collect", log.getDocumentTag());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "worksheetTabName"), "TheLogTab", log.getWorksheetTabName());
				
				fields = log.getFields();
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 parameter"), "timestamp", fields.get(0).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 0 header"), "Timestamp", fields.get(0).getHeader());
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 parameter"), "inLogLevel", fields.get(1).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 1 header"), "Severity", fields.get(1).getHeader());
	
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 parameter"), "inUniqueA", fields.get(2).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 2 header"), "Summary", fields.get(2).getHeader());
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 parameter"), "inUniqueB", fields.get(3).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 3 header"), "B Details", fields.get(3).getHeader());
	
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 4 parameter"), "inUniqueC", fields.get(4).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 4 header"), "C Details", fields.get(4).getHeader());
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 5 parameter"), "inUniqueD", fields.get(5).getParameter());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "field 5 header"), "D Details", fields.get(5).getHeader());
	
			}
		}
	}
	
	@AssertAfter(id="Initialize_109", step="InitializeCoreSSK")
	public void verifyLogSetup() {
		Object bean = this.ctx.getProperty(PROP_PARAMETER_OUT_BEAN);

		if (getName().contains("Complex") || getName().contains("OneLog") || getName().contains("TwoLogs")) {
			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_PARAMETER_OUT_BEAN), bean);
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_PARAMETER_OUT_BEAN), bean instanceof FlexLogBean);
		} else if (getName().contains("Not") || getName().contains("Duplicate") || getName().contains("Empty")) {
			assertNull(String.format(MESSAGE_UNEXPECTED_INSTANCE, PROP_PARAMETER_OUT_BEAN), bean);
		} else {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected String getFlexLogConfigurationFile() {
		switch(getName()) {
			case "testComplexConfiguration":
				return MOCK_XML_COMPLEX_CONFIG;
	
			case "testOneLogConfiguration":
				return MOCK_XML_ONE_CONFIG;
	
			case "testTwoLogsConfiguration":
				return MOCK_XML_TWO_CONFIG;
	
			case "testEmptyConfiguration":
				return MOCK_XML_EMPTY_CONFIG;
	
			case "testDuplicateConfiguration":
				return MOCK_XML_DUPLICATE_CONFIG;
	
			case "testNotConfiguration":
				return MOCK_XML_NOT_CONFIG;
				
			case "testFileNotFound":
				return MOCK_NOT_FOUND_CONFIG;
	
			default:
				return super.getFlexLogConfigurationFile();
		}
	}

	@AtComponent(id="Initialize_109", step="StaticAnalysisExemptions")
	public Action terminate() throws Exception {
		return new StandardAction(Action.Type.terminate);
	}
}
