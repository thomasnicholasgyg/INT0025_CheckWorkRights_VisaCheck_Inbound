package com.workday.custom.aunit.int0025.utilities.ssk125;

import com.capeclear.mediation.MediationException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent125TestCase extends UtilitiesTestCase {

	private static final String PROP_PARAMETER_IN_ENDPOINT = "inEndpoint";
	private static final String PROP_PARAMETER_IN_QUEUE_NAME = "inQueueName";
	private static final String PROP_PARAMETER_IN_USERNAME = "inUsername";
	private static final String PROP_PARAMETER_IN_PASSWORD = "inPassword";
	private static final String PROP_PARAMETER_IN_MESSAGE_ID = "inMessageId";
	private static final String PROP_PARAMETER_IN_MESSAGE_URI = "inMessageURI";
	private static final String PROP_PARAMETER_IN_MESSAGE_TIMESTAMP = "inMessageTimestamp";
	private static final String PROP_PARAMETER_IN_DELETE_AFTER_READ = "inIsDeleteAfterRead";

	private static final String PROP_LOCAL_LOCAL_URI = "localURI";
	
	private static final String VALUE_QUEUE_NAME = "INT-SSK-MQ";
	private static final String VALUE_MOCK_ENDPOINT = "https://aunit.workday.com";
	private static final String VALUE_MOCK_USERNAME = "mqUser";
	private static final String VALUE_MOCK_PASSWORD = "mqPass";
	private static final String VALUE_MESSAGE_ID = "1";
	private static final String VALUE_MESSAGE_URI = "/ccx/wd-queue/INT-SSK-MQ/messages/1";
	private static final String VALUE_MESSAGE_TIMESTAMP = "2020-09-05T21:27:05.000Z";
	private static final String VALUE_VAR_RETURN_RESULTS = "myResults";
	
	private static final String MOCK_MESSAGE_RESPONSE_ERROR = "test/int0025/int0025125/SSK125_TXT_POST_Response_Error.txt";
	private static final String MOCK_MESSAGE_RESPONSE_READ = "test/int0025/int0025125/SSK125_XML_GET_Response.xml";
	private static final String MOCK_MESSAGE_RESPONSE_DELETE = "test/int0025/int0025125/SSK125_XML_DELETE_Response.xml";

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
	@UnitTest(startComponent="ReadMessageFromQueue")
	public void testMessageNotExists() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_ID, VALUE_MESSAGE_ID);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_URI, VALUE_MESSAGE_URI);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_TIMESTAMP, VALUE_MESSAGE_TIMESTAMP);
		ctx.setProperty(PROP_PARAMETER_IN_DELETE_AFTER_READ, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}
	
	@UnitTest(startComponent="ReadMessageFromQueue")
	public void testReadToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_ID, VALUE_MESSAGE_ID);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_URI, VALUE_MESSAGE_URI);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_TIMESTAMP, VALUE_MESSAGE_TIMESTAMP);
		ctx.setProperty(PROP_PARAMETER_IN_DELETE_AFTER_READ, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}
	
	@UnitTest(startComponent="ReadMessageFromQueue")
	public void testReadToMessageAndDelete() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_ID, VALUE_MESSAGE_ID);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_URI, VALUE_MESSAGE_URI);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_TIMESTAMP, VALUE_MESSAGE_TIMESTAMP);
		ctx.setProperty(PROP_PARAMETER_IN_DELETE_AFTER_READ, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	@UnitTest(startComponent="ReadMessageFromQueue")
	public void testReadToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_ID, VALUE_MESSAGE_ID);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_URI, VALUE_MESSAGE_URI);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_TIMESTAMP, VALUE_MESSAGE_TIMESTAMP);
		ctx.setProperty(PROP_PARAMETER_IN_DELETE_AFTER_READ, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VALUE_VAR_RETURN_RESULTS);
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}
	
	@UnitTest(startComponent="ReadMessageFromQueue")
	public void testReadToVariableAndDelete() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENDPOINT, VALUE_MOCK_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUEUE_NAME, VALUE_QUEUE_NAME);
		ctx.setProperty(PROP_PARAMETER_IN_USERNAME, VALUE_MOCK_USERNAME);
		ctx.setProperty(PROP_PARAMETER_IN_PASSWORD, VALUE_MOCK_PASSWORD);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_ID, VALUE_MESSAGE_ID);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_URI, VALUE_MESSAGE_URI);
		ctx.setProperty(PROP_PARAMETER_IN_MESSAGE_TIMESTAMP, VALUE_MESSAGE_TIMESTAMP);
		ctx.setProperty(PROP_PARAMETER_IN_DELETE_AFTER_READ, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VALUE_VAR_RETURN_RESULTS);
		
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
		if ("testReadToMessage".equalsIgnoreCase(getName()) ||
				"testReadToMessageAndDelete".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_MESSAGE_RESPONSE_READ),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESPONSE_READ, CONTENT_TYPE_TEXT_XML));
		} else if ("testReadToVariable".equalsIgnoreCase(getName()) ||
				"testReadToVariableAndDelete".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VALUE_VAR_RETURN_RESULTS),
					compareAgainstVariable(VALUE_VAR_RETURN_RESULTS, CONTENT_TYPE_TEXT_XML, MOCK_MESSAGE_RESPONSE_READ, CONTENT_TYPE_TEXT_XML));
		}
	}

	@AssertAfter(id="InitializeAndFinalize_125", step="SetValues")
	public void verifyInitialization() throws Exception {
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_LOCAL_URI), VALUE_MOCK_ENDPOINT + VALUE_MESSAGE_URI, (String)ctx.getProperty(PROP_LOCAL_LOCAL_URI));
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected String getResourceFileForSSK125Read() {
		String returnValue = super.getResourceFileForSSK125Read();
		
		switch (getName()) {
			case "testMessageNotExists":
				returnValue = MOCK_MESSAGE_RESPONSE_ERROR;
				break;
				
			case "testReadToMessage":
				returnValue = MOCK_MESSAGE_RESPONSE_READ;
				break;
				
			case "testReadToMessageAndDelete":
				returnValue = MOCK_MESSAGE_RESPONSE_READ;
				break;
				
			case "testReadToVariable":
				returnValue = MOCK_MESSAGE_RESPONSE_READ;
				break;
				
			case "testReadToVariableAndDelete":
				returnValue = MOCK_MESSAGE_RESPONSE_READ;
				break;
					
			default:
				break;
		}
		
		return returnValue;
	}

	@Override
	protected String getContentTypeForSSK125Read() {
		String returnValue = super.getContentTypeForSSK125Read();
		
		switch (getName()) {
			case "testMessageNotExists":
				returnValue = CONTENT_TYPE_TEXT_PLAIN;
				break;
				
			default:
				break;
		}
		
		return returnValue;
	}

	@Override
	protected Throwable getExceptionForSSK125Read() {
		Throwable returnValue = super.getExceptionForSSK125Read();
		
		switch (getName()) {
			case "testMessageNotExists":
				returnValue = new MediationException("Mocked error reading a queue for message");
				break;
				
			default:
				break;
		}
	
		return returnValue;
	}

	@Override
	protected String getResourceFileForSSK125Delete() {
		String returnValue = super.getResourceFileForSSK125Delete();
		
		switch (getName()) {
			case "testMessageNotExists":
				fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				break;
			
			case "testReadToMessage":
				fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				break;
				
			case "testReadToVariable":
				fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				break;
				
			case "testReadToMessageAndDelete":
				returnValue = MOCK_MESSAGE_RESPONSE_DELETE;
				break;
				
			case "testReadToVariableAndDelete":
				returnValue = MOCK_MESSAGE_RESPONSE_DELETE;
				break;
				
			default:
				break;
		}
		
		return returnValue;
	}

}
