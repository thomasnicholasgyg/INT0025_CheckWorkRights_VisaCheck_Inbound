package com.workday.custom.aunit.int0025.utilities.ssk143;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.CommonTestCase;
import com.workday.custom.int0025.ssk142.CloudLogMessage;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class CreateLogEntryOnThreadTestCase extends CommonTestCase {

	@SuppressWarnings("rawtypes")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupGlobalInitialization();
		
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, new java.util.concurrent.ConcurrentLinkedQueue());
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="CreateLogEntryOnThread")
	public void testWriteEntry() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_LOG_LEVEL, "warn");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE, "Warn Message");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Warn Message Detail");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "101");

		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "testWarnMessage");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "3");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "10002");
		ctx.setProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Details about 10002");
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
		@SuppressWarnings("rawtypes")
		ConcurrentLinkedQueue queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
		
		Object message = queue.peek();
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

		CloudLogMessage m = (CloudLogMessage)message;
		
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "Warn Message", m.getSummary());
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Warn Message Detail", m.getDetail());
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "reference id"), "101", m.getReferenceId());
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "warn", m.getLevel());
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "local in"), "testWarnMessage", m.getLocalIn());
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "record number"), "3", m.getRecordNumber());
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "error code"), "10002", m.getErrorCode());
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), "Details about 10002", m.getSupportData());
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
}
