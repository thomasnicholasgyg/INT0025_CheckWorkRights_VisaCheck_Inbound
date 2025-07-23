package com.workday.custom.aunit.int0025.utilities.ssk149;

import java.util.HashSet;
import java.util.Set;

import com.capeclear.mediation.MediationException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSK149BuildUniqueIdListTestCase extends UtilitiesTestCase {

	public static final String VAR_DATA_SOURCE = "myVariable";
	
	public static final String PROP_PARAMETER_IN_DATA_SOURCE = "inDataSource";
	public static final String PROP_PARAMETER_IN_SET = "inPropertyWithIdList";
	public static final String PROP_PARAMETER_IN_LOG_TARGET = "inLogTarget";
	public static final String PROP_PARAMETER_IN_ABORT_ON_ERROR = "inIsAbortOnError";
	
	public static final String VALUE_POSITION_ID_1 = "P-00001";
	public static final String VALUE_POSITION_ID_2 = "P-00002";
	public static final String VALUE_POSITION_ID_3 = "P-00003";
	public static final String VALUE_POSITION_ID_4 = "P-00004";
	
	
	public static final String MOCK_XML_DATA_HISTORIC = "test/int0025/int0025149/SSK149_Positions_Historic.xml";
	public static final String MOCK_XML_DATA_CURRENT = "test/int0025/int0025149/SSK149_Positions_Current.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

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
	@UnitTest(startComponent="BuildUniqueIdList")
	public void testMessageSourceHistoric() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_SET, new HashSet<String>());
		
		setMessagePart(0, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="BuildUniqueIdList")
	public void testMessageSourceCurrent() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_SET, new HashSet<String>());
		
		setMessagePart(0, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="BuildUniqueIdList")
	public void testVariableSourceHistoric() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_DATA_SOURCE);
		ctx.setProperty(PROP_PARAMETER_IN_SET, new HashSet<String>());
		
		setVariable(VAR_DATA_SOURCE, MOCK_XML_DATA_HISTORIC, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="BuildUniqueIdList")
	public void testVariableSourceCurrent() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_DATA_SOURCE);
		ctx.setProperty(PROP_PARAMETER_IN_SET, new HashSet<String>());
		
		setVariable(VAR_DATA_SOURCE, MOCK_XML_DATA_CURRENT, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="BuildUniqueIdList")
	public void testEmptyMessage() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_SET, new HashSet<String>());
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
		if (!testsExpectingHandledExceptions.contains(getName())) {
			Object o = ctx.getProperty(PROP_PARAMETER_IN_SET);
			assertNotNull(String.format(MESSAGE_PROPERTY_NOT_ADDED, PROP_PARAMETER_IN_SET), o);
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_PARAMETER_IN_SET), o instanceof HashSet<?>);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_SET), 3, ((Set<?>)o).size());
	
			if ("testMessageSourceHistoric".equalsIgnoreCase(getName()) || 
					"testVariableSourceHistoric".equalsIgnoreCase(getName())) {
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_SET), ((Set<?>)o).contains(VALUE_POSITION_ID_2));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_SET), ((Set<?>)o).contains(VALUE_POSITION_ID_3));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_SET), ((Set<?>)o).contains(VALUE_POSITION_ID_4));
			} else if ("testMessageSourceCurrent".equalsIgnoreCase(getName()) ||
					"testVariableSourceCurrent".equalsIgnoreCase(getName())) {
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_SET), ((Set<?>)o).contains(VALUE_POSITION_ID_1));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_SET), ((Set<?>)o).contains(VALUE_POSITION_ID_2));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_SET), ((Set<?>)o).contains(VALUE_POSITION_ID_4));
			}
	
		} else {
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, "thrown exception"), t instanceof MediationException);
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
