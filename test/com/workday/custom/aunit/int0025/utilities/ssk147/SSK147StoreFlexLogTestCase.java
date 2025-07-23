package com.workday.custom.aunit.int0025.utilities.ssk147;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.SSKUtils;
import com.workday.custom.int0025.ssk147.FlexLogBean;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSK147StoreFlexLogTestCase extends UtilitiesTestCase {
	private Logger log = LogControl.getLogger(getClass());

	public static final String VAR_CONFIG = "int0025FlexLogConfiguration147";

	public static final String PROP_PARAMETER_IN_LOG_NAME = "inLogName";
	public static final String PROP_PARAMETER_IN_BEAN = "inFlexLogBean";
	
	public static final String PROP_UPSTREAM_PARAMETER_IN_LEVEL = "inLogLevel";
	public static final String PROP_UPSTREAM_PARAMETER_IN_SUMMARY = "inSummary";
	public static final String PROP_UPSTREAM_PARAMETER_IN_DETAIL = "inDetail";
	public static final String PROP_UPSTREAM_PARAMETER_IN_NUMBER = "inNumber";
	public static final String PROP_UPSTREAM_PARAMETER_IN_LINE = "inLine";
	public static final String PROP_UPSTREAM_PARAMETER_IN_POSITION = "inPosition";
	public static final String PROP_UPSTREAM_PARAMETER_IN_ORDINAL = "inOrdinal";
	public static final String PROP_UPSTREAM_PARAMETER_IN_INDEX = "inIndex";
	
	public static final String PROP_LOCAL_LOG_NAMES = "localLogNames147";
	public static final String PROP_LOCAL_FILES_COUNTER = "localFilesToWriteCounter147";
	public static final String PROP_LOCAL_FILES_EXPECTED = "localTotalFilesToWrite147";
	public static final String PROP_LOCAL_RETENTION = "localRetention147";
	public static final String PROP_LOCAL_FILENAME = "localLogFilenameForStorage147";
	public static final String PROP_LOCAL_DELIVERABLE = "localIsDeliverable147";
	public static final String PROP_LOCAL_DOC_TAG = "localDocumentTag147";
	public static final String PROP_LOCAL_DEBUG_COUNT = "localCountOfDebug147";
	public static final String PROP_LOCAL_INFO_COUNT = "localCountOfInfo147";
	public static final String PROP_LOCAL_WARN_COUNT = "localCountOfWarn147";
	public static final String PROP_LOCAL_ERROR_COUNT = "localCountOfError147";
	public static final String PROP_LOCAL_CRITICAL_COUNT = "localCountOfCritical147";
	public static final String PROP_LOCAL_HAS_CRITICAL = "localIsCriticalEntries147";
	public static final String PROP_LOCAL_HAS_ERROR = "localIsErrorEntries147";
	public static final String PROP_LOCAL_HAS_WARN = "localIsWarningEntries147";
	public static final String PROP_LOCAL_HAS_INFO = "localIsInfoEntries147";
	public static final String PROP_LOCAL_HAS_DEBUG = "localIsDebugEntries147";

	public static final String VALUE_SINGLE_LOG = "log-single";
	public static final String VALUE_MULTI_LOG1 = "log-multiple-1";
	public static final String VALUE_MULTI_LOG2 = "log-multiple-2";
	public static final String VALUE_MULTI_LOG3 = "log-multiple-3";
	public static final String VALUE_MULTI_LOG4 = "log-multiple-4";
	public static final String VALUE_MULTI_LOG5 = "log-multiple-5";
	
	public static final String XML_CONFIGURATION_SINGLE = "test/int0025/int0025147/SSK147_Configuration_Storage_SingleLog.xml";
	public static final String XML_CONFIGURATION_MULTIPLE = "test/int0025/int0025147/SSK147_Configuration_Storage_MultipleLogs.xml";
	public static final String XML_CONFIGURATION_ESCAPED = "test/int0025/int0025147/SSK147_Configuration_Storage_MultipleLogsEscaped.xml";

	private FlexLogBean bean;
	
	private int counterMulti2 = 0;
	private int counterMulti3 = 0;
	private int counterAllLogs = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		componentProperties.add(PROP_LOCAL_LOG_NAMES);
		componentProperties.add(PROP_LOCAL_FILES_COUNTER);
		componentProperties.add(PROP_LOCAL_FILES_EXPECTED);
		componentProperties.add(PROP_LOCAL_RETENTION);
		componentProperties.add(PROP_LOCAL_FILENAME);
		componentProperties.add(PROP_LOCAL_DELIVERABLE);
		componentProperties.add(PROP_LOCAL_DOC_TAG);
		componentProperties.add(PROP_LOCAL_DEBUG_COUNT);
		componentProperties.add(PROP_LOCAL_INFO_COUNT);
		componentProperties.add(PROP_LOCAL_WARN_COUNT);
		componentProperties.add(PROP_LOCAL_ERROR_COUNT);
		componentProperties.add(PROP_LOCAL_CRITICAL_COUNT);
		componentProperties.add(PROP_LOCAL_HAS_CRITICAL);
		componentProperties.add(PROP_LOCAL_HAS_ERROR);
		componentProperties.add(PROP_LOCAL_HAS_WARN);
		componentProperties.add(PROP_LOCAL_HAS_INFO);
		componentProperties.add(PROP_LOCAL_HAS_DEBUG);

		componentVariables.add("logStoreResult147");

		bean = new FlexLogBean();
		String pathToConfig = null;
		
		switch (getName()) {
			case "testSingleLog" :
				pathToConfig = XML_CONFIGURATION_SINGLE;
				break;
			
			case "testMultipleLogs" :
				pathToConfig = XML_CONFIGURATION_MULTIPLE;
				break;
			
			case "testMultipleLogsEscaped" :
				pathToConfig = XML_CONFIGURATION_ESCAPED;
				break;
			
			default :
				break;
		}

		try {
			setVariable(VAR_CONFIG, pathToConfig, CONTENT_TYPE_TEXT_XML);
			bean.configureNewLog(ctx);
			ctx.setProperty(PROP_PARAMETER_IN_BEAN, bean);
			
			stageLogEntries();
		} catch (Throwable t) {
			log.fatal("Setup Exception!", t);
			throw new Exception(t);
		}
	}
	
	private void stageLogEntries() throws Throwable {
		if ("testMultipleLogs".equals(getName())) {
			for (int i = 1; i <= 15; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_MULTI_LOG1);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, getLevel(1, i));
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - 1x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - 1x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_NUMBER, "counter - 1x" + i);
				
				bean.writeLogEntry(ctx);
			}
		
			for (int i = 1; i <= 8; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_MULTI_LOG2);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, getLevel(2, i));
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - 2"+ getFileID(2, i) +"x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - 2"+ getFileID(2, i) +"x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LINE, "counter - 2"+ getFileID(2, i) +"x" + i);
				
				bean.writeLogEntry(ctx);
			}
		
			for (int i = 1; i <= 8; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_MULTI_LOG3);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, getLevel(3, i));
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - 3"+ getFileID(3, i) +"x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - 3"+ getFileID(3, i) +"x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_POSITION, "counter - 3"+ getFileID(3, i) +"x" + i);
				
				bean.writeLogEntry(ctx);
			}
		
			for (int i = 1; i <= 8; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_MULTI_LOG4);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, getLevel(4, i));
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - 4x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - 4x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_ORDINAL, "counter - 4x" + i);
				
				bean.writeLogEntry(ctx);
			}
		
			for (int i = 1; i <= 8; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_MULTI_LOG5);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, getLevel(5, i));
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - 5x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - 5x" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_INDEX, "counter - 5x" + i);
				
				bean.writeLogEntry(ctx);
			}
		} else if ("testMultipleLogsEscaped".equals(getName())) {
			for (int i = 1; i <= 10; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_MULTI_LOG1);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, "INFO");
				if (i % 2 == 0) {
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary , 1x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail , 1x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_NUMBER, "counter , 1x" + i);
				} else {
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - 1x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - 1x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_NUMBER, "counter - 1x" + i);
				}
				
				bean.writeLogEntry(ctx);
			}
		
			for (int i = 1; i <= 10; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_MULTI_LOG2);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, "INFO");
				if (i % 2 == 0) {
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary | 2x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail | 2x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LINE, "counter | 2x" + i);
				} else {
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - \"2x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - 2x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LINE, "counter - 2x" + i);
				}
				
				bean.writeLogEntry(ctx);
			}

			for (int i = 1; i <= 10; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_MULTI_LOG3);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, "INFO");
				if (i % 2 == 0) {
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary : 3x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail : 3x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LINE, "counter : 3x" + i);
				} else {
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - 3x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - 3x" + i);
					ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LINE, "counter - 3x" + i);
				}
				
				bean.writeLogEntry(ctx);
			}
		} else {
			for (int i = 1; i <= 10; i++) {
				ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_SINGLE_LOG);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, "WARN");
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_SUMMARY, "summary - Sx" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "detail - Sx" + i);
				ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_NUMBER, "counter - Sx" + i);
				
				bean.writeLogEntry(ctx);
			}
		}
	}
	
	private String getLevel(int log, int entry) {
		String returnValue = null;
		switch(log) {
			case 1 :
				switch(entry) {
					case 1 :
						returnValue = "INFO";
						break;
						
					case 2 :
						returnValue = "INFO";
						break;
						
					case 3 :
						returnValue = "WARN";
						break;
						
					case 4 :
						returnValue = "DEBUG";
						break;
						
					case 5 :
						returnValue = "DEBUG";
						break;
						
					case 6 :
						returnValue = "DEBUG";
						break;
						
					case 7 :
						returnValue = "DEBUG";
						break;
						
					case 8 :
						returnValue = "DEBUG";
						break;

					case 9 :
						returnValue = "WARN";
						break;
						
					case 10 :
						returnValue = "WARN";
						break;
						
					case 11 :
						returnValue = "WARN";
						break;
						
					case 12 :
						returnValue = "ERROR";
						break;
						
					case 13 :
						returnValue = "ERROR";
						break;
						
					case 14 :
						returnValue = "ERROR";
						break;
						
					case 15 :
						returnValue = "ERROR";
						break;
				}
				break;
		
			case 2 :
				switch(entry) {
					case 1 :
						returnValue = "INFO";
						break;
						
					case 2 :
						returnValue = "WARN";
						break;
						
					case 3 :
						returnValue = "WARN";
						break;
						
					case 4 :
						returnValue = "ERROR";
						break;
						
					case 5 :
						returnValue = "ERROR";
						break;
						
					case 6 :
						returnValue = "ERROR";
						break;
						
					case 7 :
						returnValue = "ERROR";
						break;
						
					case 8 :
						returnValue = "CRITICAL";
						break;
				}
				break;
		
			case 3 :
				switch(entry) {
					case 1 :
						returnValue = "INFO";
						break;
						
					case 2 :
						returnValue = "INFO";
						break;
						
					case 3 :
						returnValue = "INFO";
						break;
						
					case 4 :
						returnValue = "INFO";
						break;
						
					case 5 :
						returnValue = "INFO";
						break;
						
					case 6 :
						returnValue = "INFO";
						break;
						
					case 7 :
						returnValue = "INFO";
						break;
						
					case 8 :
						returnValue = "INFO";
						break;
				}
				break;
		
			case 4 :
				switch(entry) {
					case 1 :
						returnValue = "INFO";
						break;
						
					case 2 :
						returnValue = "WARN";
						break;
						
					case 3 :
						returnValue = "WARN";
						break;
						
					case 4 :
						returnValue = "WARN";
						break;
						
					case 5 :
						returnValue = "WARN";
						break;
						
					case 6 :
						returnValue = "WARN";
						break;
						
					case 7 :
						returnValue = "WARN";
						break;
						
					case 8 :
						returnValue = "WARN";
						break;
				}
				break;
		
			case 5 :
				switch(entry) {
					case 1 :
						returnValue = "DEBUG";
						break;
						
					case 2 :
						returnValue = "DEBUG";
						break;
						
					case 3 :
						returnValue = "DEBUG";
						break;
						
					case 4 :
						returnValue = "DEBUG";
						break;
						
					case 5 :
						returnValue = "DEBUG";
						break;
						
					case 6 :
						returnValue = "DEBUG";
						break;
						
					case 7 :
						returnValue = "DEBUG";
						break;
						
					case 8 :
						returnValue = "DEBUG";
						break;
				}
				break;
		
		}

		if (returnValue == null) {
			this.log.fatal("Setup Exception on Level!");
			fail("Incorrect setup configuration calculating severity level");
		}
		
		return returnValue;
	}
	
	private String getFileID(int log, int i) {
		String returnValue = null;
		
		if (log == 2) {
			if (i < 3) {
				returnValue = "A";
			} else if (i < 5) {
				returnValue = "B";
			} else if (i < 7) {
				returnValue = "C";
			} else if (i < 9) {
				returnValue = "D";
			}
		} else if (log == 3) {
			if (i < 5) {
				returnValue = "E";
			} else if (i < 9) {
				returnValue = "F";
			}
		}
		
		if (returnValue == null) {
			this.log.fatal("Setup Exception on FileID!");
			fail("Incorrect setup configuration calculating log entry details");
		}
		
		return returnValue;
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		ctx.removeProperty(PROP_PARAMETER_IN_BEAN);
		bean = null;
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="StoreAllFlexLogs")
	public void testSingleLog() {
		ctx.setProperty(PROP_PARAMETER_IN_BEAN, bean);
		
		counterAllLogs = 0;
	}
	
	@UnitTest(startComponent="StoreAllFlexLogs")
	public void testMultipleLogs() {
		ctx.setProperty(PROP_PARAMETER_IN_BEAN, bean);

		counterAllLogs = 0;
		counterMulti2 = 0;
		counterMulti3 = 0;
	}
	
	@UnitTest(startComponent="StoreAllFlexLogs")
	public void testMultipleLogsEscaped() {
		ctx.setProperty(PROP_PARAMETER_IN_BEAN, bean);

	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		if ("testMultipleLogs".equals(getName())) {
			assertEquals("Incorrect iterations for total number of Flex Logs", 5, counterAllLogs);
			assertEquals("Incorrect iterations for partial Flex Log files for " + VALUE_MULTI_LOG2, 4, counterMulti2);
			assertEquals("Incorrect iterations for partial Flex Log files for " + VALUE_MULTI_LOG3, 2, counterMulti3);
		} else if ("testMultipleLogsEscaped".equals(getName())) {
			assertEquals("Incorrect iterations for total number of Flex Logs", 3, counterAllLogs);
			
		} else {
			assertEquals("Incorrect iterations for total number of Flex Logs", 1, counterAllLogs);
			
		}
	}
	
	@AssertAfter(id="InitializeAndFinalize_StoreAllFlexLogs_147", step="InitValues")
	public void verifyLogsToStore() throws Throwable {
		String[] names = (String[])ctx.getProperty(PROP_LOCAL_LOG_NAMES);

		List<String> logs = new ArrayList<String>(names.length);
		
		for (int i = 0; i < names.length; i++) {
			logs.add(names[i]);
		}
		
		if ("testMultipleLogs".equals(getName())) {
			assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG1), logs.contains(VALUE_MULTI_LOG1));
			assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG2), logs.contains(VALUE_MULTI_LOG2));
			assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG3), logs.contains(VALUE_MULTI_LOG3));
			assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG4), logs.contains(VALUE_MULTI_LOG4));
			assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG5), logs.contains(VALUE_MULTI_LOG5));

			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_SINGLE_LOG), logs.contains(VALUE_SINGLE_LOG));
		} else if ("testMultipleLogsEscaped".equals(getName())) {
				assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG1), logs.contains(VALUE_MULTI_LOG1));
				assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG2), logs.contains(VALUE_MULTI_LOG2));
				assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG3), logs.contains(VALUE_MULTI_LOG3));

				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_SINGLE_LOG), logs.contains(VALUE_SINGLE_LOG));
				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG4), logs.contains(VALUE_MULTI_LOG4));
				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG5), logs.contains(VALUE_MULTI_LOG5));
		} else {
			assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_LOG_NAMES, VALUE_SINGLE_LOG), logs.contains(VALUE_SINGLE_LOG));

			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG1), logs.contains(VALUE_MULTI_LOG1));
			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG2), logs.contains(VALUE_MULTI_LOG2));
			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG3), logs.contains(VALUE_MULTI_LOG3));
			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG4), logs.contains(VALUE_MULTI_LOG4));
			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_FOUND, PROP_LOCAL_LOG_NAMES, VALUE_MULTI_LOG5), logs.contains(VALUE_MULTI_LOG5));
		}
	}
	
	@AssertBefore(id="Call_StoreEachLog_147")
	public void updateLogCounter() throws Throwable {
		counterAllLogs++;
	}
	
	@AssertAfter(id="OutputFlexLog_147", step="Setup")
	public void verifyLogContent() throws Throwable {
		BufferedReader reader = null;
		String line = null;
		int lineCounter = 0, counterOffset = 0, effectiveCounter = 0;
		
		if ("testMultipleLogs".equals(getName())) {
			String whichLog = (String)ctx.getProperty(PROP_PARAMETER_IN_LOG_NAME);
			
			switch (whichLog) {
				case VALUE_MULTI_LOG1 :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_RETENTION), "P30D", (String)ctx.getProperty(PROP_LOCAL_RETENTION));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_FILENAME), "FlexLog.csv", (String)ctx.getProperty(PROP_LOCAL_FILENAME));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DELIVERABLE), false, (boolean)ctx.getProperty(PROP_LOCAL_DELIVERABLE));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DOC_TAG), "", (String)ctx.getProperty(PROP_LOCAL_DOC_TAG));
					
					reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
					while ((line = reader.readLine()) != null) {
						if (lineCounter == 0) {
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG1 + " header"), "Timestamp,Severity,Summary,Detail,Number", line);
						} else if (lineCounter < 16) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG1 + " entry " + lineCounter), line.endsWith(getLevel(1, lineCounter) + ",summary - 1x"+ lineCounter +",detail - 1x"+ lineCounter +",counter - 1x"+ lineCounter));
						} else {
							fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_MULTI_LOG1 + " header+entry line count " + lineCounter));
						}
						
						lineCounter++;
					}
					break;

				case VALUE_MULTI_LOG2 :
					counterMulti2++;

					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_RETENTION), "P7D", (String)ctx.getProperty(PROP_LOCAL_RETENTION));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DELIVERABLE), false, (boolean)ctx.getProperty(PROP_LOCAL_DELIVERABLE));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DOC_TAG), "INT_Logs2Collect", (String)ctx.getProperty(PROP_LOCAL_DOC_TAG));
					
					if (counterMulti2 < 5) {
						assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_FILENAME), "multi2_"+ counterMulti2 +".csv", (String)ctx.getProperty(PROP_LOCAL_FILENAME));
						
						counterOffset = (counterMulti2 - 1) * 2;
						reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
						while ((line = reader.readLine()) != null) {
							if (lineCounter == 0) {
								assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG2 + " header"), "Timestamp|Severity|Summary|Detail|Line", line);
							} else if (lineCounter < 3) {
								effectiveCounter = lineCounter + counterOffset;
								assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG2 + " entry " + effectiveCounter), line.endsWith(getLevel(2, effectiveCounter) + "|summary - 2"+ getFileID(2, effectiveCounter) +"x"+ effectiveCounter +"|detail - 2"+ getFileID(2, effectiveCounter) +"x"+ effectiveCounter +"|counter - 2"+ getFileID(2, effectiveCounter) +"x"+ effectiveCounter));
							} else {
								fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_MULTI_LOG2 + " header+entry line count " + lineCounter));
							}
							
							lineCounter++;
						}
					} else {
						fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName() + " partial log loop " + counterMulti2));
					}
					break;

				case VALUE_MULTI_LOG3 :
					counterMulti3++;

					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_RETENTION), "P14D", (String)ctx.getProperty(PROP_LOCAL_RETENTION));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DELIVERABLE), true, (boolean)ctx.getProperty(PROP_LOCAL_DELIVERABLE));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DOC_TAG), "", (String)ctx.getProperty(PROP_LOCAL_DOC_TAG));

					if (counterMulti3 < 3) {
						assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_FILENAME), "multi3_"+ counterMulti3 +".csv", (String)ctx.getProperty(PROP_LOCAL_FILENAME));
						
						counterOffset = (counterMulti3 - 1) * 4;
						reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
						while ((line = reader.readLine()) != null) {
							if (lineCounter == 0) {
								assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG3 + " header"), "Timestamp~Severity~Summary~Detail~Position", line);
							} else if (lineCounter < 5) {
								effectiveCounter = lineCounter + counterOffset;
								assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG3 + " entry " + effectiveCounter), line.endsWith(getLevel(3, effectiveCounter) + "~summary - 3"+ getFileID(3, effectiveCounter) +"x"+ effectiveCounter +"~detail - 3"+ getFileID(3, effectiveCounter) +"x"+ effectiveCounter +"~counter - 3"+ getFileID(3, effectiveCounter) +"x"+ effectiveCounter));
							} else {
								fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_MULTI_LOG3 + " header+entry line count " + lineCounter));
							}
							
							lineCounter++;
						}
					} else {
						fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName() + " partial log loop " + counterMulti3));
					}
					break;

				case VALUE_MULTI_LOG4 :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_RETENTION), "P30D", (String)ctx.getProperty(PROP_LOCAL_RETENTION));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_FILENAME), "multi4.csv", (String)ctx.getProperty(PROP_LOCAL_FILENAME));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DELIVERABLE), false, (boolean)ctx.getProperty(PROP_LOCAL_DELIVERABLE));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DOC_TAG), "", (String)ctx.getProperty(PROP_LOCAL_DOC_TAG));
					
					reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
					while ((line = reader.readLine()) != null) {
						if (lineCounter == 0) {
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG4 + " header"), "Timestamp|Severity|Summary|Detail|Ordinal", line);
						} else if (lineCounter < 9) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG4 + " entry " + lineCounter), line.endsWith(getLevel(4, lineCounter) + "|summary - 4x"+ lineCounter +"|detail - 4x"+ lineCounter +"|counter - 4x"+ lineCounter));
						} else {
							fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_MULTI_LOG4 + " header+entry line count " + lineCounter));
						}
						
						lineCounter++;
					}
					break;

				case VALUE_MULTI_LOG5 :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_RETENTION), "P30D", (String)ctx.getProperty(PROP_LOCAL_RETENTION));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_FILENAME), "multi5.csv", (String)ctx.getProperty(PROP_LOCAL_FILENAME));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DELIVERABLE), false, (boolean)ctx.getProperty(PROP_LOCAL_DELIVERABLE));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DOC_TAG), "", (String)ctx.getProperty(PROP_LOCAL_DOC_TAG));
					
					reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
					while ((line = reader.readLine()) != null) {
						if (lineCounter == 0) {
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG5 + " header"), "Timestamp,Severity,Summary,Detail,Index", line);
						} else if (lineCounter < 9) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG5 + " entry " + lineCounter), line.endsWith(getLevel(5, lineCounter) + ",summary - 5x"+ lineCounter +",detail - 5x"+ lineCounter +",counter - 5x"+ lineCounter));
						} else {
							fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_MULTI_LOG5 + " header+entry line count " + lineCounter));
						}
						
						lineCounter++;
					}
					break;

				default :
					fail(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_NAME));
					break;
			}
		} else if ("testMultipleLogsEscaped".equals(getName())) {
			String whichLog = (String)ctx.getProperty(PROP_PARAMETER_IN_LOG_NAME);
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_RETENTION), "P30D", (String)ctx.getProperty(PROP_LOCAL_RETENTION));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_FILENAME), "FlexLog.csv", (String)ctx.getProperty(PROP_LOCAL_FILENAME));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DELIVERABLE), false, (boolean)ctx.getProperty(PROP_LOCAL_DELIVERABLE));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DOC_TAG), "", (String)ctx.getProperty(PROP_LOCAL_DOC_TAG));

			switch (whichLog) {
				case VALUE_MULTI_LOG1 :
					reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
					while ((line = reader.readLine()) != null) {
						if (lineCounter == 0) {
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG1 + " header"), "Timestamp,Severity,Summary,Detail,\"Number,Row\"", line);
						} else if (lineCounter < 11 && lineCounter % 2 == 0) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG1 + " entry " + lineCounter), line.endsWith("INFO,\"summary , 1x"+ lineCounter +"\",\"detail , 1x"+ lineCounter +"\",\"counter , 1x"+ lineCounter + "\""));
						} else if (lineCounter < 11 && lineCounter % 2 != 0) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG1 + " entry " + lineCounter), line.endsWith("INFO,summary - 1x"+ lineCounter +",detail - 1x"+ lineCounter +",counter - 1x"+ lineCounter));
						} else {
							fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_MULTI_LOG1 + " header+entry line count " + lineCounter));
						}
						
						lineCounter++;
					}
					break;

				case VALUE_MULTI_LOG2 :
					reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
					while ((line = reader.readLine()) != null) {
						if (lineCounter == 0) {
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG2 + " header"), "Timestamp|Severity|Summary|Detail|\"Row|Line\"", line);
						} else if (lineCounter < 11 && lineCounter % 2 == 0) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG2 + " entry " + lineCounter), line.endsWith("INFO|\"summary | 2x"+ lineCounter +"\"|\"detail | 2x"+ lineCounter +"\"|\"counter | 2x"+ lineCounter + "\""));
						} else if (lineCounter < 11 && lineCounter % 2 != 0) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG2 + " entry " + lineCounter), line.endsWith("INFO|\"summary - \"\"2x"+ lineCounter +"\"|detail - 2x"+ lineCounter +"|counter - 2x"+ lineCounter));
						} else {
							fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_MULTI_LOG2 + " header+entry line count " + lineCounter));
						}
						
						lineCounter++;
					}
					break;

				case VALUE_MULTI_LOG3 :
					reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
					while ((line = reader.readLine()) != null) {
						if (lineCounter == 0) {
							assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG3 + " header"), "Timestamp:Severity:Summary:Detail:Row/Line", line);
						} else if (lineCounter < 11 && lineCounter % 2 == 0) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG3 + " entry " + lineCounter), line.startsWith("\""));
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG3 + " entry " + lineCounter), line.endsWith("\":INFO:\"summary : 3x"+ lineCounter +"\":\"detail : 3x"+ lineCounter +"\":\"counter : 3x"+ lineCounter + "\""));
						} else if (lineCounter < 11 && lineCounter % 2 != 0) {
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG3 + " entry " + lineCounter), line.startsWith("\""));
							assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_MULTI_LOG3 + " entry " + lineCounter), line.endsWith("\":INFO:summary - 3x"+ lineCounter +":detail - 3x"+ lineCounter +":counter - 3x"+ lineCounter));
						} else {
							fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_MULTI_LOG3 + " header+entry line count " + lineCounter));
						}
						
						lineCounter++;
					}
					break;

				default :
					fail(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_NAME));
					break;
			}
		} else {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_RETENTION), "P30D", (String)ctx.getProperty(PROP_LOCAL_RETENTION));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_FILENAME), "FlexLog.csv", (String)ctx.getProperty(PROP_LOCAL_FILENAME));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DELIVERABLE), false, (boolean)ctx.getProperty(PROP_LOCAL_DELIVERABLE));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DOC_TAG), "", (String)ctx.getProperty(PROP_LOCAL_DOC_TAG));
			
			reader = new BufferedReader(new InputStreamReader(SSKUtils.getMessageInputStream(ctx)));
			while ((line = reader.readLine()) != null) {
				if (lineCounter == 0) {
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_SINGLE_LOG + " header"), "Timestamp,Severity,Summary,Detail,Number", line);
				} else if (lineCounter < 11) {
					assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_SINGLE_LOG + " entry " + lineCounter), line.endsWith("WARN,summary - Sx"+ lineCounter +",detail - Sx"+ lineCounter +",counter - Sx"+ lineCounter));
				} else {
					fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, VALUE_SINGLE_LOG + " header+entry line count " + lineCounter));
				}
				
				lineCounter++;
			}
		}
	}
	
	@AssertAfter(id="FlexLogStats_147", step="SetValues")
	public void verifyStatistics() throws Throwable {
		if ("testMultipleLogs".equals(getName())) {
			String whichLog = (String)ctx.getProperty(PROP_PARAMETER_IN_LOG_NAME);
			
			switch (whichLog) {
				case VALUE_MULTI_LOG1 :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_CRITICAL), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_CRITICAL));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_ERROR), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_ERROR));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_WARN), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_WARN));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_INFO), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_INFO));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_DEBUG), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_DEBUG));
					
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_CRITICAL_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_CRITICAL_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_ERROR_COUNT), 4, (int)ctx.getProperty(PROP_LOCAL_ERROR_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_WARN_COUNT), 4, (int)ctx.getProperty(PROP_LOCAL_WARN_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_INFO_COUNT), 2, (int)ctx.getProperty(PROP_LOCAL_INFO_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DEBUG_COUNT), 5, (int)ctx.getProperty(PROP_LOCAL_DEBUG_COUNT));
					break;

				case VALUE_MULTI_LOG2 :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_CRITICAL), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_CRITICAL));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_ERROR), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_ERROR));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_WARN), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_WARN));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_INFO), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_INFO));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_DEBUG), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_DEBUG));
					
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_CRITICAL_COUNT), 1, (int)ctx.getProperty(PROP_LOCAL_CRITICAL_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_ERROR_COUNT), 4, (int)ctx.getProperty(PROP_LOCAL_ERROR_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_WARN_COUNT), 2, (int)ctx.getProperty(PROP_LOCAL_WARN_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_INFO_COUNT), 1, (int)ctx.getProperty(PROP_LOCAL_INFO_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DEBUG_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_DEBUG_COUNT));
					break;

				case VALUE_MULTI_LOG3 :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_CRITICAL), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_CRITICAL));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_ERROR), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_ERROR));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_WARN), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_WARN));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_INFO), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_INFO));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_DEBUG), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_DEBUG));
					
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_CRITICAL_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_CRITICAL_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_ERROR_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_ERROR_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_WARN_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_WARN_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_INFO_COUNT), 8, (int)ctx.getProperty(PROP_LOCAL_INFO_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DEBUG_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_DEBUG_COUNT));
					break;

				case VALUE_MULTI_LOG4 :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_CRITICAL), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_CRITICAL));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_ERROR), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_ERROR));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_WARN), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_WARN));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_INFO), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_INFO));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_DEBUG), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_DEBUG));
					
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_CRITICAL_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_CRITICAL_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_ERROR_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_ERROR_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_WARN_COUNT), 7, (int)ctx.getProperty(PROP_LOCAL_WARN_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_INFO_COUNT), 1, (int)ctx.getProperty(PROP_LOCAL_INFO_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DEBUG_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_DEBUG_COUNT));
					break;

				case VALUE_MULTI_LOG5 :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_CRITICAL), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_CRITICAL));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_ERROR), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_ERROR));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_WARN), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_WARN));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_INFO), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_INFO));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_DEBUG), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_DEBUG));
					
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_CRITICAL_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_CRITICAL_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_ERROR_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_ERROR_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_WARN_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_WARN_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_INFO_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_INFO_COUNT));
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DEBUG_COUNT), 8, (int)ctx.getProperty(PROP_LOCAL_DEBUG_COUNT));
					break;

				default :
					fail(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_NAME));
					break;
			}
			
		} else if ("testSingleLog".equals(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_CRITICAL), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_CRITICAL));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_ERROR), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_ERROR));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_WARN), true, (boolean)ctx.getProperty(PROP_LOCAL_HAS_WARN));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_INFO), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_INFO));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_HAS_DEBUG), false, (boolean)ctx.getProperty(PROP_LOCAL_HAS_DEBUG));
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_CRITICAL_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_CRITICAL_COUNT));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_ERROR_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_ERROR_COUNT));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_WARN_COUNT), 10, (int)ctx.getProperty(PROP_LOCAL_WARN_COUNT));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_INFO_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_INFO_COUNT));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_DEBUG_COUNT), 0, (int)ctx.getProperty(PROP_LOCAL_DEBUG_COUNT));
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

}
