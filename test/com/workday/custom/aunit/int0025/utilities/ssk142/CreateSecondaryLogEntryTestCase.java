package com.workday.custom.aunit.int0025.utilities.ssk142;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.CommonTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class CreateSecondaryLogEntryTestCase extends CommonTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		setupSecondaryLogInitialization();
		setupGlobalInitialization();
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlCriticalMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "critical");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Critical Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Critical Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testCriticalMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "1");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10000");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10000");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlErrorMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "error");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Error Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Error Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testErrorMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "2");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10001");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10001");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlWarnMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "warn");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Warn Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Warn Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testWarnMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "3");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10002");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10002");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlInfoMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Info Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Info Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testInfoMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "4");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlDebugMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "debug");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Debug Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Debug Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testDebugMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "5");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Deep technical details supporting debugging");
	}
	
	@UnitTest(startComponent="CreateLogEntry")
	public void testHtmlInfoMessageMinimal() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Info Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Minimum Detail");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvCriticalMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "critical");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Critical Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Critical Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testCriticalMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "1");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10000");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10000");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvErrorMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "error");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Error Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Error Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testErrorMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "2");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10001");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10001");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvWarnMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "warn");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Warn Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Warn Message, Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testWarnMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "3");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10002");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10002");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvInfoMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Info Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Info Message with \"Detail\"");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testInfoMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "4");
	}

	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvDebugMessage() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "debug");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Debug Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Debug Message, \"Detail\"");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testDebugMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "5");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Deep technical details supporting debugging");
	}
	
	@UnitTest(startComponent="CreateLogEntry")
	public void testCsvInfoMessageMinimal() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Info Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Minimum Detail");
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@AssertAfter(id="UpdateSecondaryLogTracking_142")
	public void verifyCountsHTML() throws Exception {
		Object debugLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG);
		Object infoLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_INFO);
		Object warnLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_WARN);
		Object errorLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR);
		Object criticalLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_CRITICAL);
		Object totalLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL);
		Object fileLogCount = ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE);

		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), debugLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), infoLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), warnLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), errorLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_CRITICAL), criticalLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), totalLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), fileLogCount);

		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), debugLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), infoLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), warnLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), errorLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_CRITICAL), criticalLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), totalLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), fileLogCount instanceof Integer);
		
		switch(getName()) {
		case "testHtmlCriticalCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 0, 0, 0, 1, 1, 1);
			break;

		case "testHtmlErrorCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 0, 0, 1, 0, 1, 1);
			break;

		case "testHtmlWarnCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 0, 1, 0, 0, 1, 1);
			break;

		case "testHtmlInfoCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 1, 0, 0, 0, 1, 1);
			break;

		case "testHtmlDebugCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 1, 0, 0, 0, 0, 1, 1);
			break;
			
		case "testHtmlInfoMessageMinimal":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 1, 0, 0, 0, 1, 1);
			break;

		default:
			break;
		}
	}
	
	private void validateLogCounts(Object debugLogCount, Object infoLogCount, Object warnLogCount, Object errorLogCount, Object criticalLogCount, Object totalLogCount, Object fileLogCount, Object debugExpectedValue, Object infoExpectedValue, Object warnExpectedValue, Object errorExpectedValue, Object criticalExpectedValue, Object totalExpectedValue, Object fileExpectedValue) {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG), debugExpectedValue, debugLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_INFO), infoExpectedValue, infoLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_WARN), warnExpectedValue, warnLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), errorExpectedValue, errorLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_CRITICAL), criticalExpectedValue, criticalLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL), totalExpectedValue, totalLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE), fileExpectedValue, fileLogCount);
	}
	
	@AssertAfter(id="SecondaryLog_HTML_142")
	public void verifyLoggedMessageHTML() throws Exception {
		if ("testHtmlCriticalMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("CRITICAL", "Critical Message", "Critical Message Detail", "101", "testCriticalMessage", "10000", "1", "Details about 10000");
		} else if ("testHtmlErrorMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("ERROR", "Error Message", "Error Message Detail", "101", "testErrorMessage", "10001", "2", "Details about 10001");
		} else if ("testHtmlWarnMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("WARN", "Warn Message", "Warn Message Detail", "101", "testWarnMessage", "10002", "3", "Details about 10002");
		} else if ("testHtmlInfoMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "Info Message", "Info Message Detail", "101", "testInfoMessage", null, "4", null);
		} else if ("testHtmlDebugMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("DEBUG", "Debug Message", "Debug Message Detail", "101", "testDebugMessage", null, "5", "Deep technical details supporting debugging");
		} else if ("testHtmlInfoMessageMinimal".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryHTML("INFO", "Info Message", "Minimum Detail", null, null, null, null, null);
		}
	}
	
	@AssertAfter(id="SecondaryLog_CSV_142")
	public void verifyLoggedMessageCSV() throws Exception {
		if ("testCsvCriticalMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("CRITICAL", "Critical Message", "Critical Message Detail", "101", "testCriticalMessage", "10000", "1", "Details about 10000");
		} else if ("testCsvErrorMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("ERROR", "Error Message", "Error Message Detail", "101", "testErrorMessage", "10001", "2", "Details about 10001");
		} else if ("testCsvWarnMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("WARN", "Warn Message", "Warn Message, Detail", "101", "testWarnMessage", "10002", "3", "Details about 10002");
		} else if ("testCsvInfoMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "Info Message", "Info Message with \"Detail\"", "101", "testInfoMessage", null, "4", null);
		} else if ("testCsvDebugMessage".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("DEBUG", "Debug Message", "Debug Message, \"Detail\"", "101", "testDebugMessage", null, "5", "Deep technical details supporting debugging");
		} else if ("testCsvInfoMessageMinimal".equalsIgnoreCase(getName())) {
			assertSecondaryCloudLogEntryCSV("INFO", "Info Message", "Minimum Detail", null, null, null, null, null);
		}
	}

	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {

	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	//No additional mocks necessary
}
