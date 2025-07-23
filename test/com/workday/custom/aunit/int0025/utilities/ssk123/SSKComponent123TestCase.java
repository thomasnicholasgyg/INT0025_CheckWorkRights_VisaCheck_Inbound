package com.workday.custom.aunit.int0025.utilities.ssk123;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent123TestCase extends UtilitiesTestCase {

	private static final String PROP_PARAMETER_IN_ENDPOINT = "inEndpoint";
	private static final String PROP_PARAMETER_IN_QUEUE_NAME = "inQueueName";
	private static final String PROP_PARAMETER_IN_USERNAME = "inUsername";
	private static final String PROP_PARAMETER_IN_PASSWORD = "inPassword";

	private static final String PROP_PARAMETER_OUT_POLL_URI = "outPollURI";
	private static final String PROP_PARAMETER_OUT_MESSAGE_COUNT = "outMessageCount";
	
	private static final String PROP_LOCAL_LOCAL_URI = "localURI";
	
	private static final String VALUE_QUEUE_NAME = "INT-SSK-MQ";
	private static final String VALUE_MOCK_ENDPOINT = "https://aunit.workday.com";
	private static final String VALUE_MOCK_USERNAME = "mqUser";
	private static final String VALUE_MOCK_PASSWORD = "mqPass";
	
	private static final String MOCK_MESSAGE_RESPONSE_EXISTS = "test/int0025/int0025123/SSK123_XML_GET_Response.xml";
	private static final String MOCK_MESSAGE_RESPONSE_NOT_EXISTS = "test/int0025/int0025123/SSK123_XML_GET_Response_NotExists.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();
		
		componentProperties.add(PROP_LOCAL_LOCAL_URI);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="GetQueues")
	public void testQueueNotExists() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}
	
	@UnitTest(startComponent="GetQueues")
	public void testQueueExists() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
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

	@AssertAfter(id="InitializeAndFinalize_123", step="SetValues")
	public void verifyInitialization() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_LOCAL_URI), VALUE_MOCK_ENDPOINT + "/ccx/wd-queue/", (String)ctx.getProperty(PROP_LOCAL_LOCAL_URI));
	}
	
	@AssertAfter(id="QueueResults_123", step="UpdateValues")
	public void verifyQueueExistsReturnValue() throws Exception {
		if ("testQueueNotExists".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_POLL_URI), "", (String)ctx.getProperty(PROP_PARAMETER_OUT_POLL_URI));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MESSAGE_COUNT), "0", String.valueOf(ctx.getProperty(PROP_PARAMETER_OUT_MESSAGE_COUNT)));
		} else if ("testQueueExists".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_POLL_URI), "/ccx/wd-queue/" + VALUE_QUEUE_NAME + "/fresh", (String)ctx.getProperty(PROP_PARAMETER_OUT_POLL_URI));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MESSAGE_COUNT), "1", String.valueOf(ctx.getProperty(PROP_PARAMETER_OUT_MESSAGE_COUNT)));
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
	protected String getResourceFileForSSK123() {
		String returnValue = super.getResourceFileForSSK123();
		
		switch (getName()) {
			case "testQueueNotExists":
				returnValue = MOCK_MESSAGE_RESPONSE_NOT_EXISTS;
				break;
			
			case "testQueueExists":
				returnValue = MOCK_MESSAGE_RESPONSE_EXISTS;
				break;
				
			default:
				break;
		}
		
		return returnValue;
	}

}
