package com.workday.custom.aunit.int0025.utilities.ssk151;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent151TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_SYSTEM_WID = "inIntegrationSystemWID";
	public static final String PROP_PARAMETER_IN_MULTIPLES = "inSystemHasMultipleAttachments";
	public static final String PROP_PARAMETER_IN_SERVICE_NAME = "inAttachmentServiceName";
	public static final String PROP_PARAMETER_IN_RETURN_MIME_TYPE = "inReturnResultsMimeType";

	public static final String PROP_LOCAL_IS_CACHED = "localIsCached";

    public static final String VAR_MY_DATA = "myDataVariable";
    public static final String VAR_LOCAL_SYSTEM_CACHE = "int0025151-98ee329f630d011af0a0c85ab0470000";
    public static final String VAR_LOCAL_BASE64_CONTENT = "attachmentContent151";

    public static final String VALUE_SYSTEM_REF_WID = "98ee329f630d011af0a0c85ab0470000";
    public static final String VALUE_SERVICE_CSV = "INT0025 CheckWorkRights VisaCheck Inbound Attachment Service - CSV";
    public static final String VALUE_SERVICE_JSN = "INT0025 CheckWorkRights VisaCheck Inbound Attachment Service - JSON";
    public static final String VALUE_SERVICE_PDF = "INT0025 CheckWorkRights VisaCheck Inbound Attachment Service - PDF";
    public static final String VALUE_SERVICE_PNG = "INT0025 CheckWorkRights VisaCheck Inbound Attachment Service - PNG";
    public static final String VALUE_SERVICE_XML = "INT0025 CheckWorkRights VisaCheck Inbound Attachment Service - XML";

    public static final String MOCK_INT_SYS_RESPONSE = "test/int0025/int0025151/SSK151_Get_Integration_System_Response.xml";
	public static final String MOCK_FILE_CSV = "test/int0025/int0025151/file.csv";
	public static final String MOCK_FILE_JSN = "test/int0025/int0025151/json.txt";
	public static final String MOCK_FILE_PDF = "test/int0025/int0025151/file.pdf";
    public static final String MOCK_FILE_PNG = "test/int0025/int0025151/file.png";
    public static final String MOCK_FILE_XML = "test/int0025/int0025151/file.xml";


    @Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mockTracker.addComponentTracking("GetIntegrationSystems_151");
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, true);
		
		registerComponentProperty(PROP_LOCAL_IS_CACHED);
		registerComponentVariable(VAR_LOCAL_BASE64_CONTENT);
	}
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentCsvToMessage() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_TEXT_CSV);		
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentJsonToMessage() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_JSN);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentPdfToMessage() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_PDF);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentPngToMessage() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_PNG);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_IMAGE_PNG);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentXmlToMessage() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_XML);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testMultipleAttachmentNotYetCachedXmlToMessage() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, true);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_XML);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testMultipleAttachmentPriorCachedXmlToMessage() throws Throwable {
		setVariable(VAR_LOCAL_SYSTEM_CACHE, MOCK_INT_SYS_RESPONSE, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, true);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_XML);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentCsvToVariable() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_CSV);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_TEXT_CSV);		
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentJsonToVariable() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_JSN);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentPdfToVariable() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_PDF);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_APPLICATION_PDF);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentPngToVariable() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_PNG);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_IMAGE_PNG);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testSingleAttachmentXmlToVariable() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, false);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_XML);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testMultipleAttachmentNotYetCachedXmlToVariable() throws Throwable {
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, true);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_XML);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="GetIntegrationAttachment")
	public void testMultipleAttachmentPriorCachedXmlToVariable() throws Throwable {
		setVariable(VAR_LOCAL_SYSTEM_CACHE, MOCK_INT_SYS_RESPONSE, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_SYSTEM_WID, VALUE_SYSTEM_REF_WID);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLES, true);
		ctx.setProperty(PROP_PARAMETER_IN_SERVICE_NAME, VALUE_SERVICE_XML);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_MIME_TYPE, CONTENT_TYPE_TEXT_XML);
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
		if (getName().startsWith("testMultipleAttachment")) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_LOCAL_SYSTEM_CACHE),
					compareAgainstVariable(VAR_LOCAL_SYSTEM_CACHE, CONTENT_TYPE_TEXT_XML, MOCK_INT_SYS_RESPONSE, CONTENT_TYPE_TEXT_XML));
		}
		
		boolean comparisonResult = false;
		String comparisonFile = null;
		if (getName().endsWith("ToMessage")) {
			if (getName().endsWith("CsvToMessage")) {
				comparisonFile = MOCK_FILE_CSV;
				comparisonResult = compareAgainstMessageRoot(CONTENT_TYPE_TEXT_CSV, comparisonFile, CONTENT_TYPE_TEXT_CSV, Comparator.text);
			} else if (getName().endsWith("JsonToMessage")) {
				comparisonFile = MOCK_FILE_JSN;
				comparisonResult = compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, comparisonFile, CONTENT_TYPE_APPLICATION_JSON, Comparator.text);
			} else if (getName().endsWith("PdfToMessage")) {
				comparisonFile = MOCK_FILE_PDF;
				comparisonResult = compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_PDF, comparisonFile, CONTENT_TYPE_APPLICATION_PDF, Comparator.binary);
			} else if (getName().endsWith("PngToMessage")) {
				comparisonFile = MOCK_FILE_PNG;
				comparisonResult = compareAgainstMessageRoot(CONTENT_TYPE_IMAGE_PNG, comparisonFile, CONTENT_TYPE_IMAGE_PNG, Comparator.binary);
			} else if (getName().endsWith("XmlToMessage")) {
				comparisonFile = MOCK_FILE_XML;
				comparisonResult = compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, comparisonFile, CONTENT_TYPE_TEXT_XML, Comparator.dom);
			} 

			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, comparisonFile), comparisonResult);
		} else if (getName().endsWith("ToVariable")) {
			if (getName().endsWith("CsvToVariable")) {
				comparisonFile = MOCK_FILE_CSV;
				comparisonResult = compareAgainstVariable(VAR_MY_DATA, CONTENT_TYPE_TEXT_CSV, comparisonFile, CONTENT_TYPE_TEXT_CSV, Comparator.text);
			} else if (getName().endsWith("JsonToVariable")) {
				comparisonFile = MOCK_FILE_JSN;
				comparisonResult = compareAgainstVariable(VAR_MY_DATA, CONTENT_TYPE_APPLICATION_JSON, comparisonFile, CONTENT_TYPE_APPLICATION_JSON, Comparator.text);
			} else if (getName().endsWith("PdfToVariable")) {
				comparisonFile = MOCK_FILE_PDF;
				comparisonResult = compareAgainstVariable(VAR_MY_DATA, CONTENT_TYPE_APPLICATION_PDF, comparisonFile, CONTENT_TYPE_APPLICATION_PDF, Comparator.binary);
			} else if (getName().endsWith("PngToVariable")) {
				comparisonFile = MOCK_FILE_PNG;
				comparisonResult = compareAgainstVariable(VAR_MY_DATA, CONTENT_TYPE_IMAGE_PNG, comparisonFile, CONTENT_TYPE_IMAGE_PNG, Comparator.binary);
			} else if (getName().endsWith("XmlToVariable")) {
				comparisonFile = MOCK_FILE_XML;
				comparisonResult = compareAgainstVariable(VAR_MY_DATA, CONTENT_TYPE_TEXT_XML, comparisonFile, CONTENT_TYPE_TEXT_XML, Comparator.dom);
			} 

			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_MY_DATA, comparisonFile), comparisonResult);
		}

	}

	@AssertBefore(id="GetAndDecodeFile_151")
	public void verifyGetIntegrationSystem() throws Exception {
		int expected = getName().startsWith("testMultipleAttachmentPriorCached") ? 0 : 1;
		int actual = mockTracker.getCallCount("GetIntegrationSystems_151");
		
		assertEquals(String.format(MESSAGE_MOCKS_UNEXPECTED_CALLS, "GetIntegrationSystems_151", getName()), expected, actual);
		
		assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_INT_SYS_RESPONSE), 
				compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_INT_SYS_RESPONSE, CONTENT_TYPE_TEXT_XML));
	}
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected String getResourceFileForSSK151() {
		return MOCK_INT_SYS_RESPONSE;
	}

}
