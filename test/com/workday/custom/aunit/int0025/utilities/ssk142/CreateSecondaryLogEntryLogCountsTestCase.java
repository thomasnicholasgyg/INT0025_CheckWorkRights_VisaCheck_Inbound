package com.workday.custom.aunit.int0025.utilities.ssk142;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.CommonTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class CreateSecondaryLogEntryLogCountsTestCase extends CommonTestCase {

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

	@UnitTest(startComponent="WriteSecondaryLog_HTML_142")
	public void testHtmlCriticalCount() throws Exception {
		setupCriticalLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_HTML_142")
	public void testHtmlErrorCount() throws Exception {
		setupErrorLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_HTML_142")
	public void testHtmlWarnCount() throws Exception {
		setupWarnLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_HTML_142")
	public void testHtmlInfoCount() throws Exception {
		setupInfoLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_HTML_142")
	public void testHtmlDebugCount() throws Exception {
		setupDebugLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_CSV_142")
	public void testCsvCriticalCount() throws Exception {
		setupCriticalLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_CSV_142")
	public void testCsvErrorCount() throws Exception {
		setupErrorLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_CSV_142")
	public void testCsvWarnCount() throws Exception {
		setupWarnLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_CSV_142")
	public void testCsvInfoCount() throws Exception {
		setupInfoLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_CSV_142")
	public void testCsvDebugCount() throws Exception {
		setupDebugLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_XLSX_142")
	public void testXlsxCriticalCount() throws Exception {
		setupCriticalLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_XLSX_142")
	public void testXlsxErrorCount() throws Exception {
		setupErrorLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_XLSX_142")
	public void testXlsxWarnCount() throws Exception {
		setupWarnLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_XLSX_142")
	public void testXlsxInfoCount() throws Exception {
		setupInfoLog();
	}

	@UnitTest(startComponent="WriteSecondaryLog_XLSX_142")
	public void testXlsxDebugCount() throws Exception {
		setupDebugLog();
	}

	private void setupCriticalLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "critical");
	}
	
	private void setupErrorLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "error");
	}
	
	private void setupWarnLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "warn");
	}
	
	private void setupInfoLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "info");
	}
	
	private void setupDebugLog() {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "debug");
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	
	private void validateLogCounts(Object debugLogCount, Object infoLogCount, Object warnLogCount, Object errorLogCount, Object criticalLogCount, Object totalLogCount, Object fileLogCount, Object debugExpectedValue, Object infoExpectedValue, Object warnExpectedValue, Object errorExpectedValue, Object criticalExpectedValue, Object totalExpectedValue, Object fileExpectedValue) {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG), debugExpectedValue, debugLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_COUNT_INFO), infoExpectedValue, infoLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_COUNT_WARN), warnExpectedValue, warnLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR), errorExpectedValue, errorLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_COUNT_CRITICAL), criticalExpectedValue, criticalLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL), totalExpectedValue, totalLogCount);
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE), fileExpectedValue, fileLogCount);
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

	@AtComponent(id="Call_StoreLogSecondary_142")
	public Action terminate() throws Exception {
		Object debugLogCount = ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG);
		Object infoLogCount = ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_INFO);
		Object warnLogCount = ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_WARN);
		Object errorLogCount = ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR);
		Object criticalLogCount = ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_CRITICAL);
		Object totalLogCount = ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL);
		Object fileLogCount = ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE);

		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG), debugLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_SECONDARYLOG_COUNT_INFO), infoLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_SECONDARYLOG_COUNT_WARN), warnLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR), errorLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_SECONDARYLOG_COUNT_CRITICAL), criticalLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL), totalLogCount);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE), fileLogCount);

		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG), debugLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_INFO), infoLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_WARN), warnLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR), errorLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_CRITICAL), criticalLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL), totalLogCount instanceof Integer);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE), fileLogCount instanceof Integer);
		
		switch(getName()) {
		case "testCriticalCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 0, 0, 0, 1, 1, 1);
			break;

		case "testErrorCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 0, 0, 1, 0, 1, 1);
			break;

		case "testWarnCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 0, 1, 0, 0, 1, 1);
			break;

		case "testInfoCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 0, 1, 0, 0, 0, 1, 1);
			break;

		case "testDebugCount":
			validateLogCounts(debugLogCount, infoLogCount, warnLogCount, errorLogCount, criticalLogCount, totalLogCount, fileLogCount, 1, 0, 0, 0, 0, 1, 1);
			break;

		default:
			break;
		}

		return new StandardAction(Action.Type.terminate);
	}
}
