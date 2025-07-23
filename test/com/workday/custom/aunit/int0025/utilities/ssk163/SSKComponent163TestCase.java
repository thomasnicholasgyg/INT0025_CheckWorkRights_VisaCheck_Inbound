package com.workday.custom.aunit.int0025.utilities.ssk163;

import com.capeclear.capeconnect.transport.BadRequestException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.MediationConstants;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent163TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_TOKEN = "inAccessToken";
	public static final String PROP_PARAMETER_IN_GATEWAY_ENDPOINT = "inGatewayEndpoint";
	public static final String PROP_PARAMETER_IN_API_PATH = "inApiPath";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inDataLocation";
	public static final String PROP_PARAMETER_IN_FILE_LOCATION = "inFileLocation";
	public static final String PROP_PARAMETER_IN_FILE_NAME = "inFilename";
	
	public static final String PROP_PARAMETER_OUT_ACCESS_TOKEN = "outAccessToken160";
	public static final String PROP_PARAMETER_OUT_ATTACHMENT_WID = "outAttachmentWID163";
	
	public static final String PROP_LOCAL_ENDPOINT = "localEndpoint163";
	public static final String PROP_LOCAL_MIMETYPE = "localMimeType163";

    public static final String VAR_MY_DATA = "myDataVariable";
    public static final String VAR_MY_FILE = "myFileVariable";

    public static final String VALUE_ACCESS_TOKEN = "ac09rao09svn5udbtsrh2kgdvar";
    public static final String VALUE_EXTEND_ENDPOINT = "https://api.workday.com";
    public static final String VALUE_EXTEND_API_PATH = "/apps/appRefId_abcxyz/v1/attachmentObject";
    public static final String VALUE_ATTACHMENT_WID = VALUE_WID_35;

    public static final String MOCK_POST_BODY_PDF = "test/int0025/int0025163/SSK163_Body_PDF.json";
    public static final String MOCK_POST_BODY_PNG = "test/int0025/int0025163/SSK163_Body_PNG.json";

    public static final String MOCK_FILE_PDF = "test/int0025/int0025163/file.pdf";
    public static final String MOCK_FILE_PNG = "test/int0025/int0025163/file.png";

    public static final String MOCK_RESPONSE_POST_PDF = "test/int0025/int0025163/SSK163_Response_PDF.json";
    public static final String MOCK_RESPONSE_POST_PNG = "test/int0025/int0025163/SSK163_Response_PNG.json";
	public static final String MOCK_RESPONSE_BAD_REQUEST_EXCEPTION = "test/int0025/int0025163/SSK163_Response_BadRequestException.json";

    @Override
	protected void setUp() throws Exception {
		super.setUp();

		if (getName().equalsIgnoreCase("testValidationErrorTenantConfiguration")) {
			ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, "tenant");
		} else {
			ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, "extend");
		}
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, true);
		
		ctx.setProperty(PROP_PARAMETER_OUT_ACCESS_TOKEN, VALUE_ACCESS_TOKEN);

		registerComponentProperty(PROP_LOCAL_ENDPOINT);
		registerComponentProperty(PROP_LOCAL_MIMETYPE);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPdfFileFromMessageDataFromVariableToMessage() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setMessagePart(0, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPdfFileFromVariableDataFromMessageToMessage() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setMessagePart(0, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setVariable(VAR_MY_FILE, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPdfFileFromVariableDataFromVariableToMessage() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setVariable(VAR_MY_FILE, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPdfFileFromMessageDataFromVariableToVariable() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		
		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setMessagePart(0, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPdfFileFromVariableDataFromMessageToVariable() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		
		setMessagePart(0, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setVariable(VAR_MY_FILE, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPdfFileFromVariableDataFromVariableToVariable() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		
		setMessagePart(0, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setVariable(VAR_MY_FILE, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPngFileFromMessageDataFromVariableToMessage() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.png");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");

		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PNG, CONTENT_TYPE_APPLICATION_JSON);
		setMessagePart(0, MOCK_FILE_PNG, CONTENT_TYPE_IMAGE_PNG);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPngFileFromVariableDataFromMessageToMessage() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.png");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");

		setMessagePart(0, MOCK_POST_BODY_PNG, CONTENT_TYPE_APPLICATION_JSON);
		setVariable(VAR_MY_FILE, MOCK_FILE_PNG, CONTENT_TYPE_IMAGE_PNG);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPngFileFromVariableDataFromVariableToMessage() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.png");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");

		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PNG, CONTENT_TYPE_APPLICATION_JSON);
		setVariable(VAR_MY_FILE, MOCK_FILE_PNG, CONTENT_TYPE_IMAGE_PNG);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPngFileFromMessageDataFromVariableToVariable() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.png");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PNG, CONTENT_TYPE_APPLICATION_JSON);
		setMessagePart(0, MOCK_FILE_PNG, CONTENT_TYPE_IMAGE_PNG);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPngFileFromVariableDataFromMessageToVariable() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.png");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);

		setMessagePart(0, MOCK_POST_BODY_PNG, CONTENT_TYPE_APPLICATION_JSON);
		setVariable(VAR_MY_FILE, MOCK_FILE_PNG, CONTENT_TYPE_IMAGE_PNG);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testPngFileFromVariableDataFromVariableToVariable() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_FILE);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.png");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PNG, CONTENT_TYPE_APPLICATION_JSON);
		setVariable(VAR_MY_FILE, MOCK_FILE_PNG, CONTENT_TYPE_IMAGE_PNG);
	}

	
	
	@UnitTest(startComponent="StoreExtendAttachment")
	public void testValidationErrorTenantConfiguration() throws Throwable {
		expectUnhandledException();

		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, "https://wcpdev-services1.wd101.myworkday.com/ccx/api");
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, "/apps/appRefId_abcxyz/v1/<tenant>/attachmentObject");
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setMessagePart(0, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testValidationErrorSameFileAndDataLocationsMessage() throws Throwable {
		expectUnhandledException();

		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setMessagePart(0, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="StoreExtendAttachment")
	public void testValidationErrorSameFileAndDataLocationsVariable() throws Throwable {
		expectUnhandledException();

		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_FILE_NAME, "file.pdf");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		
		setVariable(VAR_MY_DATA, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON);
		setMessagePart(0, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF);
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
		Object attachmentWID = ctx.getProperty(PROP_PARAMETER_OUT_ATTACHMENT_WID);
		if (getName().startsWith("testValidationError")) {
			assertNull(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, PROP_PARAMETER_OUT_ATTACHMENT_WID), attachmentWID);		
		} else {
			assertNotNull(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_ATTACHMENT_WID, VALUE_ATTACHMENT_WID), attachmentWID);		
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ATTACHMENT_WID), VALUE_ATTACHMENT_WID, attachmentWID);
		}
		
		if (!isExpectedException()) {
			String expectedValue = (getName().contains("Pdf")) ? MOCK_RESPONSE_POST_PDF : MOCK_RESPONSE_POST_PNG;
			if (getName().endsWith("ToMessage")) {
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, expectedValue),
						compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, expectedValue, CONTENT_TYPE_APPLICATION_JSON, Comparator.text));				
			} else if (getName().endsWith("ToVariable")) {
				assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_MY_DATA, expectedValue),
						compareAgainstVariable(VAR_MY_DATA, CONTENT_TYPE_APPLICATION_JSON, expectedValue, CONTENT_TYPE_APPLICATION_JSON, Comparator.text));
			} 
		}
	}
	
	@AssertAfter(id="InitializeAndFinalize_163", step="InitValues")
	public void verifyEndpoint() throws Exception {
		String expectedValue = VALUE_EXTEND_ENDPOINT + VALUE_EXTEND_API_PATH;
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_ENDPOINT), expectedValue, (String)ctx.getProperty(PROP_LOCAL_ENDPOINT));
	}
	
	@AssertAfter(id="ComposeAttachment_163", step="SetAttachmentHeaders")
	public void verifyAttachment() throws Exception {
		if (getName().startsWith("testPdf")) {
			assertTrue(String.format(MESSAGE_DOCUMENT_UNEXPECTED_VALUE, "parts[1]", MOCK_FILE_PDF),
					compareAgainstMessagePart(1, CONTENT_TYPE_APPLICATION_PDF, MOCK_FILE_PDF, CONTENT_TYPE_APPLICATION_PDF, Comparator.binary));
			
			String disposition = (String)ctx.getMessage().getHeader(1, "Content-Disposition");
			String contentType = (String)ctx.getMessage().getHeader(1, "Content-Type");
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_ANY, "Content-Disposition attachment header"),
					"form-data; name=\"attachmentContent\"; filename=\"file.pdf\"", disposition);

			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_ANY, "Content-Type attachment header"),
					CONTENT_TYPE_APPLICATION_PDF, contentType);
		} else if (getName().startsWith("testPng")) {
			assertTrue(String.format(MESSAGE_DOCUMENT_UNEXPECTED_VALUE, "parts[1]", MOCK_FILE_PNG),
					compareAgainstMessagePart(1, CONTENT_TYPE_IMAGE_PNG, MOCK_FILE_PNG, CONTENT_TYPE_IMAGE_PNG, Comparator.binary));
			
			String disposition = (String)ctx.getMessage().getHeader(1, "Content-Disposition");
			String contentType = (String)ctx.getMessage().getHeader(1, "Content-Type");
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_ANY, "Content-Disposition attachment header"),
					"form-data; name=\"attachmentContent\"; filename=\"file.png\"", disposition);

			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_ANY, "Content-Type attachment header"),
					CONTENT_TYPE_IMAGE_PNG, contentType);
		} else {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		}
	}
	
	@AssertAfter(id="ComposeBody_163", step="SetMessageHeaders")
	public void verifyMessage() throws Exception {
		if (getName().startsWith("testPdf")) {
			assertTrue(String.format(MESSAGE_DOCUMENT_UNEXPECTED_VALUE, "root part", MOCK_POST_BODY_PDF),
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_POST_BODY_PDF, CONTENT_TYPE_APPLICATION_JSON, Comparator.text));
		} else if (getName().startsWith("testPng")) {
			assertTrue(String.format(MESSAGE_DOCUMENT_UNEXPECTED_VALUE, "root part", MOCK_POST_BODY_PNG),
					compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_POST_BODY_PNG, CONTENT_TYPE_APPLICATION_JSON, Comparator.text));
		} else {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		}
		
		String disposition = (String)ctx.getMessage().getHeader(0, "Content-Disposition");
		String contentType = (String)ctx.getMessage().getHeader(0, "Content-Type");
		
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_ANY, "Content-Disposition root header"),
				"form-data; name=\"data\"", disposition);

		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_ANY, "Content-Type root header"),
				CONTENT_TYPE_APPLICATION_JSON, contentType);
		
		String authorization = (String)ctx.getMessage().getHeader("Authorization");
		String msgContentType = (String)ctx.getMessage().getHeader("Content-Type");

		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_ANY, "Authorization message header"),
				"Bearer " + VALUE_ACCESS_TOKEN, authorization);

		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_ANY, "Content-Type message header"),
				"multipart/form-data", msgContentType);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	@Override
	protected String getResourceFileForSSK163() {
		if (getName().startsWith("testException")) {
			return MOCK_RESPONSE_BAD_REQUEST_EXCEPTION;
		} else if (getName().startsWith("testPdf")) {
			return MOCK_RESPONSE_POST_PDF;
		} else if (getName().startsWith("testPng")) {
			return MOCK_RESPONSE_POST_PNG;
		} else {
			return super.getResourceFileForSSK163();
		}
	}

	@Override
	protected Throwable getExceptionForSSK163() {
		if (getName().startsWith("testException")) {
			ctx.setProperty(MediationConstants.STUDIO_PROPERTY_HTTP_RESPONSE_CODE, 400);
			return new BadRequestException("{\"error\": \"invalid_grant\"}", "400");
		} else {
			return super.getExceptionForSSK163();
		}
	}

}
