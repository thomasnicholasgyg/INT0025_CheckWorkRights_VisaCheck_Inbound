package com.workday.custom.aunit.int0025.utilities.ssk130;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.MediationConstants;
import com.workday.custom.int0025.SSKUtils;
import com.workday.custom.int0025.ssk130.FlowControlBean;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent130TestCase extends UtilitiesTestCase {

	public static final String VAR_DATA_SOURCE = "aunitVariableLocation";
	public static final String VAR_LOCAL_ITEM_COUNT = "localCountResult130";
	
	public static final String PROP_PARAMETER_IN_UNIQUE_KEY = MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_UNIQUE_ID;
	public static final String PROP_PARAMETER_IN_DATA_SOURCE = "inDataSource";
	public static final String PROP_PARAMETER_IN_ITEM_XPATH = "inItemXPath";
	public static final String PROP_PARAMETER_IN_PROCESS_ENDPOINT = "inProcessEndpoint";
	public static final String PROP_PARAMETER_IN_PERCENT_START = MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START;
	public static final String PROP_PARAMETER_IN_PERCENT_END = MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END;
	public static final String PROP_PARAMETER_IN_PERCENT_THRESHOLD = MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD;
	public static final String PROP_PARAMETER_IN_NO_SPLIT_LOG_LEVEL = "inLogLevelNoSplitMessageError";
	
	public static final String PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES = "localErrorLogFieldOverride";
	
	public static final String VALUE_DEFAULT_UNIQUE_KEY = "a";
	public static final String VALUE_MOCK_PARENT_UNIQUE_KEY = "outerLoopKey";
	public static final String VALUE_PROCESS_ENDPOINT = "vm://INT0025_CheckWorkRights_VisaCheck_Inbound/ProcessEndpointExample_130";

	public static final String MOCK_SSK130_XML_DOCUMENTS_3 = "test/int0025/int0025130/SSK130_XML_GetDocumentListResult_3Files.xml";
	public static final String MOCK_SSK130_XML_DOCUMENTS_0 = "test/int0025/int0025130/SSK130_XML_GetDocumentListResult_0Files.xml";
	public static final String MOCK_SSK130_XML_SOAPAPI_4 = "test/int0025/int0025130/SSK130_XML_BatchOfSoapCalls_4Requests.xml";
	
	public static final String MOCK_MESSAGE_SSK130_DOCUMENT_1 = "test/int0025/int0025130/SSK130_XML_GetDocumentListResult_File-1.xml";
	public static final String MOCK_MESSAGE_SSK130_DOCUMENT_2 = "test/int0025/int0025130/SSK130_XML_GetDocumentListResult_File-2.xml";
	public static final String MOCK_MESSAGE_SSK130_DOCUMENT_3 = "test/int0025/int0025130/SSK130_XML_GetDocumentListResult_File-3.xml";
	
	public static final String MOCK_MESSAGE_SSK130_SOAPAPI_1 = "test/int0025/int0025130/SSK130_XML_BatchOfSoapCalls_Request-21001.xml";
	public static final String MOCK_MESSAGE_SSK130_SOAPAPI_2 = "test/int0025/int0025130/SSK130_XML_BatchOfSoapCalls_Request-21002.xml";
	public static final String MOCK_MESSAGE_SSK130_SOAPAPI_3 = "test/int0025/int0025130/SSK130_XML_BatchOfSoapCalls_Request-21003.xml";
	public static final String MOCK_MESSAGE_SSK130_SOAPAPI_4 = "test/int0025/int0025130/SSK130_XML_BatchOfSoapCalls_Request-21004.xml";
	
	public static final String MOCK_MESSAGE_SSK130_BIG_BATCH = "test/int0025/int0025130/SSK130_XML_BigBatch.xml";
	
	private int perItemProcessVisitCounter;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
			
		mockTracker.addComponentTracking("MockMediationToCount_130");
		
		setupGlobalInitialization();
		
		ctx.setProperty(PROP_PARAMETER_IN_ITEM_XPATH, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_PROCESS_ENDPOINT, VALUE_PROCESS_ENDPOINT);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_PRIMARY);

		perItemProcessVisitCounter = 0;
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testMessageDocuments() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 10d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 89d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_THRESHOLD, 1d);
		
		setMessagePart(0, MOCK_SSK130_XML_DOCUMENTS_3, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testVariableDocuments() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_DATA_SOURCE);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 10d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 40d);
		
		setVariable(VAR_DATA_SOURCE, MOCK_SSK130_XML_DOCUMENTS_3, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testMessageSoap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 24d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 72d);

		setMessagePart(0, MOCK_SSK130_XML_SOAPAPI_4, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testMessageNoSplitInfo() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 10d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 15d);
		ctx.setProperty(PROP_PARAMETER_IN_NO_SPLIT_LOG_LEVEL, "info");

		setMessagePart(0, MOCK_SSK130_XML_DOCUMENTS_0, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testMessageNoSplitWarn() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 10d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 15d);
		ctx.setProperty(PROP_PARAMETER_IN_NO_SPLIT_LOG_LEVEL, "warn");

		setMessagePart(0, MOCK_SSK130_XML_DOCUMENTS_0, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testNestedCall() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_UNIQUE_KEY, VALUE_MOCK_PARENT_UNIQUE_KEY);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 10d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 80d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_THRESHOLD, 1d);

		FlowControlBean.initializeLoop(ctx);
		FlowControlBean.configureLoop(ctx, "7");
		FlowControlBean.recalculateProgressUpdateThreshold(VALUE_MOCK_PARENT_UNIQUE_KEY);
		
		ctx.setProperty(PROP_PARAMETER_IN_UNIQUE_KEY, VALUE_DEFAULT_UNIQUE_KEY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 10d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 20d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_THRESHOLD, 2d);

		setMessagePart(0, MOCK_MESSAGE_SSK130_BIG_BATCH, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testErrorStartGreaterEnd() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 40d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 30d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_THRESHOLD, 1d);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testErrorStartEqualEnd() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 40d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 40d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_THRESHOLD, 1d);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testErrorIncrementGreaterDifference() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 40d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 41d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_THRESHOLD, 5d);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testErrorZeroIncrement() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 10d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 40d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_THRESHOLD, 0d);
	}
	
	@UnitTest(startComponent="MultiItemFlowControl")
	public void testMessageBigBatchIncrementalUpdates() throws Exception {

		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_START, 10d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_END, 70d);
		ctx.setProperty(PROP_PARAMETER_IN_PERCENT_THRESHOLD, 5d);

		setMessagePart(0, MOCK_MESSAGE_SSK130_BIG_BATCH, CONTENT_TYPE_TEXT_XML);
	}
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@SuppressWarnings("unchecked")
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		assertTrue(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_LOCAL_ITEM_COUNT), SSKUtils.isVariableNullOrUndefined(ctx, VAR_LOCAL_ITEM_COUNT));
		assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map"), FlowControlBean.isUniqueKeyRegistered(VALUE_DEFAULT_UNIQUE_KEY));
		
		Map<String, Integer> mockedComponentsToAssert = new HashMap<String, Integer>();
		Map<String,String> fieldOverrides;
		
		switch (getName()) {
			case "testMessageDocuments":
				mockedComponentsToAssert.put("MockMediationToCount_130", new Integer(3));
				mockTracker.assertCounts(getName(), mockedComponentsToAssert);
				
				fieldOverrides = (Map<String,String>)ctx.getProperty(PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES);
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "62.6", String.valueOf(fieldOverrides.get(PROP_PARAMETER_IN_LOG_REFERENCE_ID)));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), "3", String.valueOf(fieldOverrides.get(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER)));
				break;
				
			case "testVariableDocuments":
				mockedComponentsToAssert.put("MockMediationToCount_130", new Integer(3));
				mockTracker.assertCounts(getName(), mockedComponentsToAssert);
				
				fieldOverrides = (Map<String,String>)ctx.getProperty(PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES);
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "30.0", String.valueOf(fieldOverrides.get(PROP_PARAMETER_IN_LOG_REFERENCE_ID)));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), "3", String.valueOf(fieldOverrides.get(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER)));
				break;
				
			case "testMessageSoap":
				mockedComponentsToAssert.put("MockMediationToCount_130", new Integer(4));
				mockTracker.assertCounts(getName(), mockedComponentsToAssert);

				fieldOverrides = (Map<String,String>)ctx.getProperty(PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES);
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "60.0", String.valueOf(fieldOverrides.get(PROP_PARAMETER_IN_LOG_REFERENCE_ID)));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_LOCAL_ENDPOINT_FIELD_OVERRIDES, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), "4", String.valueOf(fieldOverrides.get(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER)));
				break;
				
			case "testMessageNoSplitInfo":
				mockedComponentsToAssert.put("MockMediationToCount_130", new Integer(0));
				mockTracker.assertCounts(getName(), mockedComponentsToAssert);

				assertPrimaryCloudLogEntryHTML(
						"info", 
						"Multi-Item Flow Control - No Split Found", 
						"No items were found when the inItemXPath was applied to the inDataSource data.", 
						"", 
						"ItemSplitter_130", 
						"", 
						"", 
						"");
				break;
				
			case "testMessageNoSplitWarn":
				mockedComponentsToAssert.put("MockMediationToCount_130", new Integer(0));
				mockTracker.assertCounts(getName(), mockedComponentsToAssert);

				assertPrimaryCloudLogEntryHTML(
						"warn", 
						"Multi-Item Flow Control - No Split Found", 
						"No items were found when the inItemXPath was applied to the inDataSource data.", 
						"", 
						"ItemSplitter_130", 
						"13000", 
						"", 
						"");
				break;
				
			case "testNestedCall":
				mockedComponentsToAssert.put("MockMediationToCount_130", new Integer(100));
				mockTracker.assertCounts(getName(), mockedComponentsToAssert);
				
				verifyExitStateOfNestedCall();
				break;
				
			case "testMessageBigBatchIncrementalUpdates":
				mockedComponentsToAssert.put("MockMediationToCount_130", new Integer(100));
				mockTracker.assertCounts(getName(), mockedComponentsToAssert);

				verifyExitStateOfBigBatch();
				break;
	
			default:
				break;
		}
	}

	@AssertAfter(id="PerItemProcess_130", step="SetValues")
	public void verifyEndpointContext() throws Exception {
		perItemProcessVisitCounter++;
		
		switch (getName()) {
			case "testMessageDocuments":
				break;
				
			case "testVariableDocuments":
				break;
				
			case "testMessageSoap":
				break;
				
			case "testNestedCall":
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_MOCK_PARENT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START), "10.0", FlowControlBean.getValue(VALUE_MOCK_PARENT_UNIQUE_KEY, MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_MOCK_PARENT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END), "80.0", FlowControlBean.getValue(VALUE_MOCK_PARENT_UNIQUE_KEY, MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_MOCK_PARENT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD), "1.0", FlowControlBean.getValue(VALUE_MOCK_PARENT_UNIQUE_KEY, MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_MOCK_PARENT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_PER_ITEM), "10.0", FlowControlBean.getValue(VALUE_MOCK_PARENT_UNIQUE_KEY, MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_PER_ITEM));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_MOCK_PARENT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "7.0", FlowControlBean.getValue(VALUE_MOCK_PARENT_UNIQUE_KEY, MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));

				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START), "10.0", FlowControlBean.getValue(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END), "20.0", FlowControlBean.getValue(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD), "2.0", FlowControlBean.getValue(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_PER_ITEM), "0.1", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_PER_ITEM));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "100.0", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));
				
				verifyFlowControlBeanStateOnNestedIterations();
				break;
				
			case "testMessageBigBatchIncrementalUpdates":
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START), "10.0", FlowControlBean.getValue(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_START));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END), "70.0", FlowControlBean.getValue(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_END));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD), "5.0", FlowControlBean.getValue(MediationConstants.PROP_PARAMETER_IN_FLOWCONTROL_PROGRESS_INCREMENT_THRESHOLD));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_PER_ITEM), "0.6", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_PERCENT_PER_ITEM));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "100.0", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));
				
				verifyFlowControlBeanStateOnBigBatch();
				break;
	
			default:
				fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				break;
		}

		mockTracker.incrementCallCount("MockMediationToCount_130");
	}
	
	private void verifyFlowControlBeanStateOnNestedIterations() {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_MOCK_PARENT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_NEXT_UPDATE_THRESHOLD), "11.0", FlowControlBean.getValue(VALUE_MOCK_PARENT_UNIQUE_KEY, MediationConstants.PROP_LOCAL_FLOWCONTROL_NEXT_UPDATE_THRESHOLD));

		switch (perItemProcessVisitCounter) {
			case 1: assertNextThresholdForIteration("12.0"); break;
			case 2: assertNextThresholdForIteration("12.0"); break;
			case 3: assertNextThresholdForIteration("12.0"); break;
			case 4: assertNextThresholdForIteration("12.0"); break;
			case 5: assertNextThresholdForIteration("12.0"); break;
			case 6: assertNextThresholdForIteration("12.0"); break;
			case 7: assertNextThresholdForIteration("12.0"); break;
			case 8: assertNextThresholdForIteration("12.0"); break;
			case 9: assertNextThresholdForIteration("12.0"); break;
			case 10: assertNextThresholdForIteration("12.0"); break;
			case 11: assertNextThresholdForIteration("12.0"); break;
			case 12: assertNextThresholdForIteration("12.0"); break;
			case 13: assertNextThresholdForIteration("12.0"); break;
			case 14: assertNextThresholdForIteration("12.0"); break;
			case 15: assertNextThresholdForIteration("12.0"); break;
			case 16: assertNextThresholdForIteration("12.0"); break;
			case 17: assertNextThresholdForIteration("12.0"); break;
			case 18: assertNextThresholdForIteration("12.0"); break;
			case 19: assertNextThresholdForIteration("12.0"); break;
			case 20: assertNextThresholdForIteration("12.0"); break;
			case 21: assertNextThresholdForIteration("14.0"); break;
			case 22: assertNextThresholdForIteration("14.0"); break;
			case 23: assertNextThresholdForIteration("14.0"); break;
			case 24: assertNextThresholdForIteration("14.0"); break;
			case 25: assertNextThresholdForIteration("14.0"); break;
			case 26: assertNextThresholdForIteration("14.0"); break;
			case 27: assertNextThresholdForIteration("14.0"); break;
			case 28: assertNextThresholdForIteration("14.0"); break;
			case 29: assertNextThresholdForIteration("14.0"); break;
			case 30: assertNextThresholdForIteration("14.0"); break;
			case 31: assertNextThresholdForIteration("14.0"); break;
			case 32: assertNextThresholdForIteration("14.0"); break;
			case 33: assertNextThresholdForIteration("14.0"); break;
			case 34: assertNextThresholdForIteration("14.0"); break;
			case 35: assertNextThresholdForIteration("14.0"); break;
			case 36: assertNextThresholdForIteration("14.0"); break;
			case 37: assertNextThresholdForIteration("14.0"); break;
			case 38: assertNextThresholdForIteration("14.0"); break;
			case 39: assertNextThresholdForIteration("14.0"); break;
			case 40: assertNextThresholdForIteration("14.0"); break;
			case 41: assertNextThresholdForIteration("16.0"); break;
			case 42: assertNextThresholdForIteration("16.0"); break;
			case 43: assertNextThresholdForIteration("16.0"); break;
			case 44: assertNextThresholdForIteration("16.0"); break;
			case 45: assertNextThresholdForIteration("16.0"); break;
			case 46: assertNextThresholdForIteration("16.0"); break;
			case 47: assertNextThresholdForIteration("16.0"); break;
			case 48: assertNextThresholdForIteration("16.0"); break;
			case 49: assertNextThresholdForIteration("16.0"); break;
			case 50: assertNextThresholdForIteration("16.0"); break;
			case 51: assertNextThresholdForIteration("16.0"); break;
			case 52: assertNextThresholdForIteration("16.0"); break;
			case 53: assertNextThresholdForIteration("16.0"); break;
			case 54: assertNextThresholdForIteration("16.0"); break;
			case 55: assertNextThresholdForIteration("16.0"); break;
			case 56: assertNextThresholdForIteration("16.0"); break;
			case 57: assertNextThresholdForIteration("16.0"); break;
			case 58: assertNextThresholdForIteration("16.0"); break;
			case 59: assertNextThresholdForIteration("16.0"); break;
			case 60: assertNextThresholdForIteration("16.0"); break;
			case 61: assertNextThresholdForIteration("18.0"); break;
			case 62: assertNextThresholdForIteration("18.0"); break;
			case 63: assertNextThresholdForIteration("18.0"); break;
			case 64: assertNextThresholdForIteration("18.0"); break;
			case 65: assertNextThresholdForIteration("18.0"); break;
			case 66: assertNextThresholdForIteration("18.0"); break;
			case 67: assertNextThresholdForIteration("18.0"); break;
			case 68: assertNextThresholdForIteration("18.0"); break;
			case 69: assertNextThresholdForIteration("18.0"); break;
			case 70: assertNextThresholdForIteration("18.0"); break;
			case 71: assertNextThresholdForIteration("18.0"); break;
			case 72: assertNextThresholdForIteration("18.0"); break;
			case 73: assertNextThresholdForIteration("18.0"); break;
			case 74: assertNextThresholdForIteration("18.0"); break;
			case 75: assertNextThresholdForIteration("18.0"); break;
			case 76: assertNextThresholdForIteration("18.0"); break;
			case 77: assertNextThresholdForIteration("18.0"); break;
			case 78: assertNextThresholdForIteration("18.0"); break;
			case 79: assertNextThresholdForIteration("18.0"); break;
			case 80: assertNextThresholdForIteration("18.0"); break;
			case 81: assertNextThresholdForIteration("20.0"); break;
			case 82: assertNextThresholdForIteration("20.0"); break;
			case 83: assertNextThresholdForIteration("20.0"); break;
			case 84: assertNextThresholdForIteration("20.0"); break;
			case 85: assertNextThresholdForIteration("20.0"); break;
			case 86: assertNextThresholdForIteration("20.0"); break;
			case 87: assertNextThresholdForIteration("20.0"); break;
			case 88: assertNextThresholdForIteration("20.0"); break;
			case 89: assertNextThresholdForIteration("20.0"); break;
			case 90: assertNextThresholdForIteration("20.0"); break;
			case 91: assertNextThresholdForIteration("20.0"); break;
			case 92: assertNextThresholdForIteration("20.0"); break;
			case 93: assertNextThresholdForIteration("20.0"); break;
			case 94: assertNextThresholdForIteration("20.0"); break;
			case 95: assertNextThresholdForIteration("20.0"); break;
			case 96: assertNextThresholdForIteration("20.0"); break;
			case 97: assertNextThresholdForIteration("20.0"); break;
			case 98: assertNextThresholdForIteration("20.0"); break;
			case 99: assertNextThresholdForIteration("20.0"); break;
			case 100: assertNextThresholdForIteration("20.0"); break;

			default:
				break;
		}
	}
	
	private void verifyFlowControlBeanStateOnBigBatch() {
		switch (perItemProcessVisitCounter) {
			case 1: assertNextThresholdForIteration("15.0"); break;
			case 2: assertNextThresholdForIteration("15.0"); break;
			case 3: assertNextThresholdForIteration("15.0"); break;
			case 4: assertNextThresholdForIteration("15.0"); break;
			case 5: assertNextThresholdForIteration("15.0"); break;
			case 6: assertNextThresholdForIteration("15.0"); break;
			case 7: assertNextThresholdForIteration("15.0"); break;
			case 8: assertNextThresholdForIteration("15.0"); break;
			case 9: assertNextThresholdForIteration("15.0"); break;
			case 10: assertNextThresholdForIteration("20.0"); break;
			case 11: assertNextThresholdForIteration("20.0"); break;
			case 12: assertNextThresholdForIteration("20.0"); break;
			case 13: assertNextThresholdForIteration("20.0"); break;
			case 14: assertNextThresholdForIteration("20.0"); break;
			case 15: assertNextThresholdForIteration("20.0"); break;
			case 16: assertNextThresholdForIteration("20.0"); break;
			case 17: assertNextThresholdForIteration("20.0"); break;
			case 18: assertNextThresholdForIteration("25.0"); break;
			case 19: assertNextThresholdForIteration("25.0"); break;
			case 20: assertNextThresholdForIteration("25.0"); break;
			case 21: assertNextThresholdForIteration("25.0"); break;
			case 22: assertNextThresholdForIteration("25.0"); break;
			case 23: assertNextThresholdForIteration("25.0"); break;
			case 24: assertNextThresholdForIteration("25.0"); break;
			case 25: assertNextThresholdForIteration("25.0"); break;
			case 26: assertNextThresholdForIteration("30.0"); break;
			case 27: assertNextThresholdForIteration("30.0"); break;
			case 28: assertNextThresholdForIteration("30.0"); break;
			case 29: assertNextThresholdForIteration("30.0"); break;
			case 30: assertNextThresholdForIteration("30.0"); break;
			case 31: assertNextThresholdForIteration("30.0"); break;
			case 32: assertNextThresholdForIteration("30.0"); break;
			case 33: assertNextThresholdForIteration("30.0"); break;
			case 34: assertNextThresholdForIteration("30.0"); break;
			case 35: assertNextThresholdForIteration("35.0"); break;
			case 36: assertNextThresholdForIteration("35.0"); break;
			case 37: assertNextThresholdForIteration("35.0"); break;
			case 38: assertNextThresholdForIteration("35.0"); break;
			case 39: assertNextThresholdForIteration("35.0"); break;
			case 40: assertNextThresholdForIteration("35.0"); break;
			case 41: assertNextThresholdForIteration("35.0"); break;
			case 42: assertNextThresholdForIteration("35.0"); break;
			case 43: assertNextThresholdForIteration("40.0"); break;
			case 44: assertNextThresholdForIteration("40.0"); break;
			case 45: assertNextThresholdForIteration("40.0"); break;
			case 46: assertNextThresholdForIteration("40.0"); break;
			case 47: assertNextThresholdForIteration("40.0"); break;
			case 48: assertNextThresholdForIteration("40.0"); break;
			case 49: assertNextThresholdForIteration("40.0"); break;
			case 50: assertNextThresholdForIteration("40.0"); break;
			case 51: assertNextThresholdForIteration("45.0"); break;
			case 52: assertNextThresholdForIteration("45.0"); break;
			case 53: assertNextThresholdForIteration("45.0"); break;
			case 54: assertNextThresholdForIteration("45.0"); break;
			case 55: assertNextThresholdForIteration("45.0"); break;
			case 56: assertNextThresholdForIteration("45.0"); break;
			case 57: assertNextThresholdForIteration("45.0"); break;
			case 58: assertNextThresholdForIteration("45.0"); break;
			case 59: assertNextThresholdForIteration("45.0"); break;
			case 60: assertNextThresholdForIteration("50.0"); break;
			case 61: assertNextThresholdForIteration("50.0"); break;
			case 62: assertNextThresholdForIteration("50.0"); break;
			case 63: assertNextThresholdForIteration("50.0"); break;
			case 64: assertNextThresholdForIteration("50.0"); break;
			case 65: assertNextThresholdForIteration("50.0"); break;
			case 66: assertNextThresholdForIteration("50.0"); break;
			case 67: assertNextThresholdForIteration("50.0"); break;
			case 68: assertNextThresholdForIteration("55.0"); break;
			case 69: assertNextThresholdForIteration("55.0"); break;
			case 70: assertNextThresholdForIteration("55.0"); break;
			case 71: assertNextThresholdForIteration("55.0"); break;
			case 72: assertNextThresholdForIteration("55.0"); break;
			case 73: assertNextThresholdForIteration("55.0"); break;
			case 74: assertNextThresholdForIteration("55.0"); break;
			case 75: assertNextThresholdForIteration("55.0"); break;
			case 76: assertNextThresholdForIteration("60.0"); break;
			case 77: assertNextThresholdForIteration("60.0"); break;
			case 78: assertNextThresholdForIteration("60.0"); break;
			case 79: assertNextThresholdForIteration("60.0"); break;
			case 80: assertNextThresholdForIteration("60.0"); break;
			case 81: assertNextThresholdForIteration("60.0"); break;
			case 82: assertNextThresholdForIteration("60.0"); break;
			case 83: assertNextThresholdForIteration("60.0"); break;
			case 84: assertNextThresholdForIteration("60.0"); break;
			case 85: assertNextThresholdForIteration("65.0"); break;
			case 86: assertNextThresholdForIteration("65.0"); break;
			case 87: assertNextThresholdForIteration("65.0"); break;
			case 88: assertNextThresholdForIteration("65.0"); break;
			case 89: assertNextThresholdForIteration("65.0"); break;
			case 90: assertNextThresholdForIteration("65.0"); break;
			case 91: assertNextThresholdForIteration("65.0"); break;
			case 92: assertNextThresholdForIteration("65.0"); break;
			case 93: assertNextThresholdForIteration("70.0"); break;
			case 94: assertNextThresholdForIteration("70.0"); break;
			case 95: assertNextThresholdForIteration("70.0"); break;
			case 96: assertNextThresholdForIteration("70.0"); break;
			case 97: assertNextThresholdForIteration("70.0"); break;
			case 98: assertNextThresholdForIteration("70.0"); break;
			case 99: assertNextThresholdForIteration("70.0"); break;
			case 100: assertNextThresholdForIteration("70.0"); break;
	
			default:
				break;
		}
	}
	
	private void assertNextThresholdForIteration(String expectedValue) {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "Iteration " + perItemProcessVisitCounter + " FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_NEXT_UPDATE_THRESHOLD), expectedValue, FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_NEXT_UPDATE_THRESHOLD));
	}

	@AssertBefore(id="Teardown_130")	
	public void verifyExitStateBeforeTeardown() throws Exception {
		switch (getName()) {
			case "testMessageDocuments":
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "3.0", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));
				break;
				
			case "testVariableDocuments":
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "3.0", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));
				break;
				
			case "testMessageSoap":
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "4.0", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));
				break;
				
			case "testMessageNoSplitInfo":
				assertFalse(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), StringUtils.isNotBlank(FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL)));
				break;
				
			case "testMessageNoSplitWarn":
				assertFalse(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), StringUtils.isNotBlank(FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL)));
				break;
				
			case "testNestedCall":
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "100.0", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_MOCK_PARENT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "7.0", FlowControlBean.getValue(VALUE_MOCK_PARENT_UNIQUE_KEY, MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));
				break;
				
			case "testMessageBigBatchIncrementalUpdates":
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "FlowControlBean static map key=" + VALUE_DEFAULT_UNIQUE_KEY + " " + MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL), "100.0", FlowControlBean.getValue(MediationConstants.PROP_LOCAL_FLOWCONTROL_ITEM_TOTAL));
				break;
	
			default:
				fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				break;
		}
	
	}
	
	private void verifyExitStateOfNestedCall() throws Exception {
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 12.0% Complete", 
				"Completed processing of item number 20...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 14.0% Complete", 
				"Completed processing of item number 40...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 16.0% Complete", 
				"Completed processing of item number 60...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 18.0% Complete", 
				"Completed processing of item number 80...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 20.0% Complete", 
				"Completed processing of item number 100...", 
				"", "", "", "", "");
	}
	
	private void verifyExitStateOfBigBatch() throws Exception {
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 15.4% Complete", 
				"Completed processing of item number 9...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 20.2% Complete", 
				"Completed processing of item number 17...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 25.0% Complete", 
				"Completed processing of item number 25...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 30.4% Complete", 
				"Completed processing of item number 34...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 35.2% Complete", 
				"Completed processing of item number 42...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 40.0% Complete", 
				"Completed processing of item number 50...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 45.4% Complete", 
				"Completed processing of item number 59...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 50.2% Complete", 
				"Completed processing of item number 67...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 55.0% Complete", 
				"Completed processing of item number 75...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 60.4% Complete", 
				"Completed processing of item number 84...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 65.2% Complete", 
				"Completed processing of item number 92...", 
				"", "", "", "", "");
		assertPrimaryCloudLogEntryHTML(
				"info", 
				"Integration 70.0% Complete", 
				"Completed processing of item number 100...", 
				"", "", "", "", "");
	}
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

}
