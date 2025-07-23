package com.workday.custom.aunit.int0025.utilities.ssk149;

import java.util.HashSet;
import java.util.Set;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSK149ReportDiffTestCase extends UtilitiesTestCase {

	public static final String VAR_DATA_SOURCE_1 = "myVariable1";
	public static final String VAR_DATA_SOURCE_2 = "myVariable2";
	
	public static final String PROP_PARAMETER_IN_DATA_SOURCE_CURRENT = "inDataSourceCurrent";
	public static final String PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC = "inDataSourceHistoric";
	public static final String PROP_PARAMETER_IN_LIST_CURRENT = "inPropertyWithCurrentList";
	public static final String PROP_PARAMETER_IN_LIST_HISTORIC = "inPropertyWithHistoricList";
	public static final String PROP_PARAMETER_IN_DELTA_ONLY = "inIsReturnChangesOnly";
	public static final String PROP_PARAMETER_IN_LOG_TARGET = "inLogTarget";
	public static final String PROP_PARAMETER_IN_ABORT_ON_ERROR = "inIsAbortOnError";

	public static final String VALUE_POSITION_ID_1 = "P-00001";	//not in historic; added to current		add
	public static final String VALUE_POSITION_ID_2 = "P-00002";	//in historic; changed in current		updated
	public static final String VALUE_POSITION_ID_3 = "P-00003";	//in historic; removed from current		remove
	public static final String VALUE_POSITION_ID_4 = "P-00004";	//in historic; unchanged in current		static
	
	public static final String MOCK_XML_DATA_HISTORIC = "test/int0025/int0025149/SSK149_Positions_Historic.xml";
	public static final String MOCK_XML_DATA_CURRENT = "test/int0025/int0025149/SSK149_Positions_Current.xml";
	public static final String MOCK_XML_DATA_RESULT_DELTA = "test/int0025/int0025149/SSK149_Positions_DiffResult_DeltaFile.xml";
	public static final String MOCK_XML_DATA_RESULT_FULL = "test/int0025/int0025149/SSK149_Positions_DiffResult_FullFile.xml";

	public static final String MOCK_XML_DATA_NESTED_MULTI_HISTORIC = "test/int0025/int0025149/SSK149_NestedMultiValue_Historic.xml";
	public static final String MOCK_XML_DATA_NESTED_MULTI_CURRENT = "test/int0025/int0025149/SSK149_NestedMultiValue_Current.xml";
	public static final String MOCK_XML_RESULT_NESTED_MULTI_DELTA = "test/int0025/int0025149/SSK149_NestedMultiValue_DiffResult_DeltaFile.xml";
	public static final String MOCK_XML_RESULT_NESTED_MULTI_FULL = "test/int0025/int0025149/SSK149_NestedMultiValue_DiffResult_FullFile.xml";

	public static final String MOCK_XML_DATA_NESTED_CARDINALITY_HISTORIC = "test/int0025/int0025149/SSK149_MultiInstanceVsReference_Historic_SingleRelated.xml";
	public static final String MOCK_XML_DATA_NESTED_CARDINALITY_CURRENT = "test/int0025/int0025149/SSK149_MultiInstanceVsReference_Current_MultipleRelated.xml";
	public static final String MOCK_XML_RESULT_NESTED_CARDINALITY_FULL = "test/int0025/int0025149/SSK149_MultiInstanceVsReference_DiffResult_FullFile.xml";
	
	public static final String MOCK_XML_DATA_NESTED_CHANGESONLY_HISTORIC = "test/int0025/int0025149/SSK149_FalsePositiveMultiInstance_Historic.xml";
	public static final String MOCK_XML_DATA_NESTED_CHANGESONLY_HISTORIC_SINGLE = "test/int0025/int0025149/SSK149_FalsePositiveMultiInstance_HistoricSingleRecord.xml";
	public static final String MOCK_XML_DATA_NESTED_CHANGESONLY_CURRENT = "test/int0025/int0025149/SSK149_FalsePositiveMultiInstance_Current.xml";
	public static final String MOCK_XML_RESULT_NESTED_CHANGESONLY_DELTA = "test/int0025/int0025149/SSK149_FalsePositiveMultiInstance_DeltaFile.xml";
	public static final String MOCK_XML_RESULT_NESTED_CHANGESONLY_DELTA_SINGLE = "test/int0025/int0025149/SSK149_FalsePositiveMultiInstance_DeltaFileSingleRecord.xml";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		ctx.setProperty(PROP_PARAMETER_IN_LIST_HISTORIC, buildHistoricList());
		ctx.setProperty(PROP_PARAMETER_IN_LIST_CURRENT, buildCurrentList());
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="ReportDiff")
	public void testDeltaFileMessageSourceReturnMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, true);

		setMessagePart(0, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testDeltaFileMessageSourceReturnVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, true);

		setMessagePart(0, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testDeltaFileVariableSourceReturnMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, true);
		
		setVariable(VAR_DATA_SOURCE_1, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testDeltaFileVariableSourceReturnVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, true);
		
		setVariable(VAR_DATA_SOURCE_1, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testFullFileMessageSourceReturnMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, false);

		setMessagePart(0, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testFullFileMessageSourceReturnVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, false);

		setMessagePart(0, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testFullFileVariableSourceReturnMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, false);
		
		setVariable(VAR_DATA_SOURCE_1, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testFullFileVariableSourceReturnVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, false);
		
		setVariable(VAR_DATA_SOURCE_1, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testFullFileNestedMultiValue() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, false);
		
		setVariable(VAR_DATA_SOURCE_1, MOCK_XML_DATA_NESTED_MULTI_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_NESTED_MULTI_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testNestedMultiValueCardinalitySingleVsMultiple() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, false);
		
		setVariable(VAR_DATA_SOURCE_1, MOCK_XML_DATA_NESTED_CARDINALITY_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_NESTED_CARDINALITY_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testNestedChangesOnly() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, true);
		
		setVariable(VAR_DATA_SOURCE_1, MOCK_XML_DATA_NESTED_CHANGESONLY_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_NESTED_CHANGESONLY_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="ReportDiff")
	public void testMultiInstanceChangesOnlySingleVsMultiple() throws Exception {
		Set<String> idList = new HashSet<String>();

		idList.add("1234");
		idList.add("5678");
		
		ctx.setProperty(PROP_PARAMETER_IN_LIST_HISTORIC, idList);
		ctx.setProperty(PROP_PARAMETER_IN_LIST_CURRENT, idList);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_HISTORIC, VAR_DATA_SOURCE_2);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE_CURRENT, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_DATA_SOURCE_1);
		ctx.setProperty(PROP_PARAMETER_IN_DELTA_ONLY, true);
		
		setVariable(VAR_DATA_SOURCE_1, MOCK_XML_DATA_NESTED_CHANGESONLY_CURRENT, CONTENT_TYPE_TEXT_XML);
		setVariable(VAR_DATA_SOURCE_2, MOCK_XML_DATA_NESTED_CHANGESONLY_HISTORIC_SINGLE, CONTENT_TYPE_TEXT_XML);
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

	}
	
	@AssertAfter(id="ExecuteReportCompare_149")
	public void verifyDiffResult() throws Exception {
		if (getName().contains("DeltaFile") && getName().contains("ReturnMessage")) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_DATA_RESULT_DELTA),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_RESULT_DELTA, CONTENT_TYPE_TEXT_XML));
		} else if (getName().contains("DeltaFile") && getName().contains("ReturnVariable")) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_DATA_SOURCE_1, MOCK_XML_DATA_RESULT_DELTA),
					compareAgainstVariable(VAR_DATA_SOURCE_1, CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_RESULT_DELTA, CONTENT_TYPE_TEXT_XML));
		} else if (getName().contains("FullFile") && getName().contains("ReturnMessage")) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_DATA_RESULT_FULL),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_RESULT_FULL, CONTENT_TYPE_TEXT_XML));
		} else if (getName().contains("FullFile") && getName().contains("ReturnVariable")) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_DATA_SOURCE_1, MOCK_XML_DATA_RESULT_FULL),
					compareAgainstVariable(VAR_DATA_SOURCE_1, CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_RESULT_FULL, CONTENT_TYPE_TEXT_XML));
		} else if (getName().contains("FullFile") && getName().contains("Cardinality")) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_DATA_SOURCE_1, MOCK_XML_RESULT_NESTED_CARDINALITY_FULL),
					compareAgainstVariable(VAR_DATA_SOURCE_1, CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_RESULT_DELTA, CONTENT_TYPE_TEXT_XML));
		} else if (getName().contains("FullFile") && getName().contains("NestedMultiValue")) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_RESULT_NESTED_MULTI_FULL),
					compareAgainstVariable(VAR_DATA_SOURCE_1, CONTENT_TYPE_TEXT_XML, MOCK_XML_RESULT_NESTED_MULTI_FULL, CONTENT_TYPE_TEXT_XML));
		} else if (getName().contains("DeltaFile") && getName().contains("NestedMultiValue")) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_DATA_SOURCE_1, MOCK_XML_RESULT_NESTED_MULTI_DELTA),
					compareAgainstVariable(VAR_DATA_SOURCE_1, CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_RESULT_DELTA, CONTENT_TYPE_TEXT_XML));
		} else if (getName().equals("testNestedChangesOnly")) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_DATA_SOURCE_1, MOCK_XML_RESULT_NESTED_CHANGESONLY_DELTA),
					compareAgainstVariable(VAR_DATA_SOURCE_1, CONTENT_TYPE_TEXT_XML, MOCK_XML_RESULT_NESTED_CHANGESONLY_DELTA, CONTENT_TYPE_TEXT_XML));
		} else if (getName().equals("testMultiInstanceChangesOnlySingleVsMultiple")) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_DATA_SOURCE_1, MOCK_XML_RESULT_NESTED_CHANGESONLY_DELTA_SINGLE),
					compareAgainstVariable(VAR_DATA_SOURCE_1, CONTENT_TYPE_TEXT_XML, MOCK_XML_RESULT_NESTED_CHANGESONLY_DELTA_SINGLE, CONTENT_TYPE_TEXT_XML));
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	private Set<String> buildHistoricList() throws Exception {
		Set<String> returnValue = new HashSet<String>();

		returnValue.add(VALUE_POSITION_ID_2);
		returnValue.add(VALUE_POSITION_ID_3);
		returnValue.add(VALUE_POSITION_ID_4);
		
		return returnValue;
	}

	private Set<String> buildCurrentList() throws Exception {
		Set<String> returnValue = new HashSet<String>();

		returnValue.add(VALUE_POSITION_ID_2);
		returnValue.add(VALUE_POSITION_ID_1);
		returnValue.add(VALUE_POSITION_ID_4);
		
		return returnValue;
	}
}
