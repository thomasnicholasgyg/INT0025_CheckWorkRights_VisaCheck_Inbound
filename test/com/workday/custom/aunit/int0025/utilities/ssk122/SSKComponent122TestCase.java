package com.workday.custom.aunit.int0025.utilities.ssk122;

import com.capeclear.mediation.MediationException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent122TestCase extends UtilitiesTestCase {

	private static final String PROP_PARAMETER_IN_ENDPOINT = "inEndpoint";
	private static final String PROP_PARAMETER_IN_QUEUE_NAME = "inQueueName";
	private static final String PROP_PARAMETER_IN_USERNAME = "inUsername";
	private static final String PROP_PARAMETER_IN_PASSWORD = "inPassword";

	private static final String PROP_PARAMETER_OUT_POLL_URI = "outPollURI";
	
	private static final String PROP_LOCAL_LOCAL_URI = "localURI";
	
	private static final String VALUE_QUEUE_NAME = "INT-SSK-MQ";
	private static final String VALUE_MOCK_ENDPOINT = "https://aunit.workday.com";
	private static final String VALUE_MOCK_USERNAME = "mqUser";
	private static final String VALUE_MOCK_PASSWORD = "mqPass";
	
	private static final String MOCK_MESSAGE_RESPONSE_EXISTS = "test/int0025/int0025122/SSK122_XML_GET_Response.xml";
	private static final String MOCK_MESSAGE_RESPONSE_NOT_EXISTS = "test/int0025/int0025122/SSK122_XML_GET_Response_NotExists.xml";
	private static final String MOCK_MESSAGE_RESPONSE_PUT = "test/int0025/int0025122/SSK122_XML_PUT_Response.xml";
	private static final String MOCK_MESSAGE_RESPONSE_PUT_ERROR = "test/int0025/int0025122/SSK122_TXT_PUT_Response_Error.txt";

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
	@UnitTest(startComponent="CreateQueue")
	public void testCreateQueue() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}
	
	@UnitTest(startComponent="CreateQueue")
	public void testQueueExists() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	@UnitTest(startComponent="CreateQueue")
	public void testCreateQueueFailed() throws Exception {
		expectHandledException();
		
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
	
	@AssertAfter(id="InitializeAndFinalize_122", step="SetValues")
	public void verifyInitialization() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_LOCAL_URI), VALUE_MOCK_ENDPOINT + "/ccx/wd-queue/", (String)ctx.getProperty(PROP_LOCAL_LOCAL_URI));
	}
	
	@AssertBefore(id="QueueResults_122")
	public void verifyRouterPathExists() throws Exception {
		if ("testCreateQueue".equalsIgnoreCase(getName()) ||
				"testCreateQueueFailed".equalsIgnoreCase(getName())) {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		}
	}
	
	@AssertBefore(id="CreateCatch_122")
	public void verifyRouterPathNotExists() throws Exception {
		if ("testQueueExists".equalsIgnoreCase(getName())) {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		}
	}

	@AssertAfter(id="QueueResults_122", step="UpdateValues")
	public void verifyQueueExistsReturnValue() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_POLL_URI), "/ccx/wd-queue/" + VALUE_QUEUE_NAME + "/fresh", (String)ctx.getProperty(PROP_PARAMETER_OUT_POLL_URI));
	}

	@AssertAfter(id="CreateResults_122", step="UpdateValues")
	public void verifyQueueNotExistsReturnValue() throws Exception {
		if ("testCreateQueueFailed".equalsIgnoreCase(getName())) {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		}
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_POLL_URI), "/ccx/wd-queue/" + VALUE_QUEUE_NAME + "/fresh", (String)ctx.getProperty(PROP_PARAMETER_OUT_POLL_URI));
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * @throws Exception 
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected String getResourceFileForSSK122GetQueue() {
		String returnValue = super.getResourceFileForSSK122GetQueue();
		
		switch (getName()) {
			case "testCreateQueue":
				returnValue = MOCK_MESSAGE_RESPONSE_NOT_EXISTS;
				break;
				
			case "testCreateQueueFailed":
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

	@Override
	protected String getResourceFileForSSK122CreateQueue() {
		String returnValue = super.getResourceFileForSSK122CreateQueue();

		switch (getName()) {
			case "testCreateQueue":
				returnValue = MOCK_MESSAGE_RESPONSE_PUT;
				break;
		
			case "testCreateQueueFailed":
				returnValue = MOCK_MESSAGE_RESPONSE_PUT_ERROR;
				break;
				
			default:
				break;
		}
		
		return returnValue;
	}

	@Override
	protected Throwable getExceptionForSSK122CreateQueue() {
		Throwable returnValue = super.getExceptionForSSK122CreateQueue();
		
		switch (getName()) {
			case "testCreateQueueFailed":
				returnValue = new MediationException("Mocked error creating a queue that exists");
				break;
				
			default:
				break;
		}

		return returnValue;
	}

}
