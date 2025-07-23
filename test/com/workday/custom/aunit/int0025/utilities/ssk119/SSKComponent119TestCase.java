package com.workday.custom.aunit.int0025.utilities.ssk119;

import java.util.ArrayList;
import java.util.List;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound", displayLabel="Test runner for SetExternalIntegrationHeaders")
public class SSKComponent119TestCase extends UtilitiesTestCase {
	
	public static final String PROP_PARAMETER_IN_ACTION = "inAction";

	public static final String VALUE_ACTION_SET = "set";
	public static final String VALUE_ACTION_CLEAR = "clear";
	public static final String VALUE_EXTERNAL_REQUEST_HEADER = "request-int0025";
	public static final String VALUE_EXTERNAL_APPLICATION_HEADER = "application-int0025";
	public static final String VALUE_EXTERNAL_ORIGINATOR_HEADER = "originator-int0025";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		super.setupGlobalInitialization();

		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="SetExternalIntegrationHeaders")
	public void testSetRequest() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ACTION, VALUE_ACTION_SET);
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_HEADER, VALUE_EXTERNAL_REQUEST_HEADER);
	}

	@UnitTest(startComponent="SetExternalIntegrationHeaders")
	public void testSetSystem() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ACTION, VALUE_ACTION_SET);
		ctx.setProperty(PROP_PARAMETER_IN_APPLICATION_HEADER, VALUE_EXTERNAL_APPLICATION_HEADER);
	}

	@UnitTest(startComponent="SetExternalIntegrationHeaders")
	public void testSetOriginator() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ACTION, VALUE_ACTION_SET);
		ctx.setProperty(PROP_PARAMETER_IN_ORIGINATOR_HEADER, VALUE_EXTERNAL_ORIGINATOR_HEADER);
	}

	@UnitTest(startComponent="SetExternalIntegrationHeaders")
	public void testSetAll() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ACTION, VALUE_ACTION_SET);
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_HEADER, VALUE_EXTERNAL_REQUEST_HEADER);
		ctx.setProperty(PROP_PARAMETER_IN_APPLICATION_HEADER, VALUE_EXTERNAL_APPLICATION_HEADER);
		ctx.setProperty(PROP_PARAMETER_IN_ORIGINATOR_HEADER, VALUE_EXTERNAL_ORIGINATOR_HEADER);
	}

	@UnitTest(startComponent="SetExternalIntegrationHeaders")
	public void testClear() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ACTION, VALUE_ACTION_CLEAR);
		ctx.setProperty(PROP_PARAMETER_OUT_REQUEST_HEADER, VALUE_EXTERNAL_REQUEST_HEADER);
		ctx.setProperty(PROP_PARAMETER_OUT_APPLICATION_HEADER, VALUE_EXTERNAL_APPLICATION_HEADER);
		ctx.setProperty(PROP_PARAMETER_OUT_ORIGINATOR_HEADER, VALUE_EXTERNAL_ORIGINATOR_HEADER);	
		
		ctx.getMessage().setHeader(VALUE_REQUEST_HEADER, VALUE_EXTERNAL_REQUEST_HEADER);
		ctx.getMessage().setHeader(VALUE_APPLICATION_HEADER, VALUE_EXTERNAL_APPLICATION_HEADER);
		ctx.getMessage().setHeader(VALUE_ORIGINATOR_HEADER, VALUE_EXTERNAL_ORIGINATOR_HEADER);
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
		switch(getName()) {
			case "testSetRequest":
				assertTrue(String.format(MESSAGE_PROPERTY_NOT_ADDED, PROP_PARAMETER_OUT_REQUEST_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_REQUEST_HEADER));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_REQUEST_HEADER, VALUE_EXTERNAL_REQUEST_HEADER), VALUE_EXTERNAL_REQUEST_HEADER, (String)ctx.getProperty(PROP_PARAMETER_OUT_REQUEST_HEADER));
				break;
				
			case "testSetSystem":
				assertTrue(String.format(MESSAGE_PROPERTY_NOT_ADDED, PROP_PARAMETER_OUT_APPLICATION_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_APPLICATION_HEADER));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_APPLICATION_HEADER, VALUE_EXTERNAL_APPLICATION_HEADER), VALUE_EXTERNAL_APPLICATION_HEADER, (String)ctx.getProperty(PROP_PARAMETER_OUT_APPLICATION_HEADER));
				break;
				
			case "testSetOriginator":
				assertTrue(String.format(MESSAGE_PROPERTY_NOT_ADDED, PROP_PARAMETER_OUT_ORIGINATOR_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_ORIGINATOR_HEADER));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_ORIGINATOR_HEADER, VALUE_EXTERNAL_ORIGINATOR_HEADER), VALUE_EXTERNAL_ORIGINATOR_HEADER, (String)ctx.getProperty(PROP_PARAMETER_OUT_ORIGINATOR_HEADER));
				break;
				
			case "testSetAll":
				assertTrue(String.format(MESSAGE_PROPERTY_NOT_ADDED, PROP_PARAMETER_OUT_REQUEST_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_REQUEST_HEADER));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_REQUEST_HEADER, VALUE_EXTERNAL_REQUEST_HEADER), VALUE_EXTERNAL_REQUEST_HEADER, (String)ctx.getProperty(PROP_PARAMETER_OUT_REQUEST_HEADER));

				assertTrue(String.format(MESSAGE_PROPERTY_NOT_ADDED, PROP_PARAMETER_OUT_APPLICATION_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_APPLICATION_HEADER));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_APPLICATION_HEADER, VALUE_EXTERNAL_APPLICATION_HEADER), VALUE_EXTERNAL_APPLICATION_HEADER, (String)ctx.getProperty(PROP_PARAMETER_OUT_APPLICATION_HEADER));
				
				assertTrue(String.format(MESSAGE_PROPERTY_NOT_ADDED, PROP_PARAMETER_OUT_ORIGINATOR_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_ORIGINATOR_HEADER));
				assertEquals(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_ORIGINATOR_HEADER, VALUE_EXTERNAL_ORIGINATOR_HEADER), VALUE_EXTERNAL_ORIGINATOR_HEADER, (String)ctx.getProperty(PROP_PARAMETER_OUT_ORIGINATOR_HEADER));
				break;
				
			case "testClear":
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_PARAMETER_OUT_REQUEST_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_REQUEST_HEADER));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_PARAMETER_OUT_APPLICATION_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_APPLICATION_HEADER));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_PARAMETER_OUT_ORIGINATOR_HEADER), ctx.containsProperty(PROP_PARAMETER_OUT_ORIGINATOR_HEADER));
				
				List<String> componentHeaders = new ArrayList<String>();
				componentHeaders.add(VALUE_ORIGINATOR_HEADER);
				componentHeaders.add(VALUE_REQUEST_HEADER);
				componentHeaders.add(VALUE_APPLICATION_HEADER);
				verifyHeadersRemoved(componentHeaders);
				break;
				
			default:
				break;
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
