package com.workday.custom.aunit.int0025.utilities.ssk102;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang3.StringUtils;

import com.capeclear.xml.utils.XmlUtils;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.MediationConstants;
import com.workday.custom.int0025.ssk102.ParameterMapHelper;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent102TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_RAAS_RESULTS = "globalRaaSResults";
	public static final String VAR_SOAP_REQUEST = "myPreBuiltSoap";
	
	public static final String PROP_PARAMETER_IN_SAVE_RESULTS = "inSaveResultsToIntegrationEvent";
	public static final String PROP_PARAMETER_IN_REPORT_ALIAS = "inReportServiceAlias";
	public static final String PROP_PARAMETER_IN_PROMPT_MAP = MediationConstants.PROP_PARAMETER_IN_PROMPT_MAP;
	public static final String PROP_PARAMETER_IN_RETURN_RESULTS = "inReturnResults";
	public static final String PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER = "inIsUseJavaUrlEncoder";
	public static final String PROP_PARAMETER_IN_COMPRESS_RESULTS = "inCompressSavedResults";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_IS_CHILD_THREAD = "inIsChildThreadContext";
	public static final String PROP_PARAMETER_IN_IS_FORCE_SOAP = "inIsForceUseSoap";
	public static final String PROP_PARAMETER_IN_IS_SOAP_REQUEST = "inForceSoapSourceOfRequest";
	public static final String PROP_PARAMETER_IN_IS_SKIP_SOAP_UNWRAP = "inForceSoapIsSkipUnwrap";

	public static final String PROP_LOCAL_PROMPT_MAP = "localMapReference";
	public static final String PROP_LOCAL_IS_PROMPTS_PROVIDED = "localIsPromptsProvided";
	public static final String PROP_LOCAL_IS_PROMPTS_MAP = "localIsPromptsMap";
	public static final String PROP_LOCAL_IS_PROMPTS_EMPTY = "localIsPromptsEmpty";
	public static final String PROP_LOCAL_IS_PROMPTS_VALID = "localIsPromptsValid";
	public static final String PROP_LOCAL_REST_URL = "localRestUrl";
	public static final String PROP_LOCAL_QUERYSTRING = "localQuerystring";
	public static final String PROP_LOCAL_KEY_VALUE_MAP = "localKeyValueMap";
	public static final String PROP_LOCAL_FILENAME = "localFilename";
	public static final String PROP_LOCAL_IS_VALID_LENGTH = "localIsValidUrlLength";

	public static final String VALUE_REPORT_PROMPT_WORKER = "Worker!WID";
	public static final String VALUE_REPORT_PROMPT_CAMPAIGN = "Campaign!WID";
	public static final String VALUE_ALL_CHARACTERS_XML_ENCODE_BEFORE = "0123456789`~!@#$%^&*()_+-= abcdefghijklmnopqrstuvwxyz[]\\{}|;':\",./<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String VALUE_REPORT_SERVICE_ALIAS = "Worker Data";
	public static final String VALUE_REPORT_SERVICE_ALIAS_ENROLLMENT = "Enrollment Data";
	public static final String VALUE_REPORT_PROMPT_VALUE1 = "0e44c92412d34b01ace61e80a47aaf6d";
	public static final String VALUE_REPORT_PROMPT_VALUE2 = "3aa5550b7fe348b98d7b5741afc65534";
	public static final String VALUE_REPORT_REST_ENDPOINT = "customreport2/testTenant/ISU_INT/CR_INT_Worker_Data";

	public static final String MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET1 = "test/int0025/int0025102/SSK102_REST_WorkerData_Response0.xml";
	public static final String MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET2 = "test/int0025/int0025102/SSK102_REST_WorkerData_Response1.xml";
	public static final String MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET3 = "test/int0025/int0025102/SSK102_REST_WorkerData_Response2.xml";
	public static final String MOCK_MESSAGE_SSK102_TRANSFORMED_REPORT_PARAMETERS = "test/int0025/int0025102/SSK102_Transformed_Report_Parameters.xml";
	public static final String MOCK_MESSAGE_SSK102_TRANSFORMED_REPORT_PARAMETERS_FILTERED = "test/int0025/int0025102/SSK102_Transformed_Report_Parameters_withWorkerFilter.xml";
	public static final String MOCK_MESSAGE_SSK102_RAAS_SOAP_REQUEST = "test/int0025/int0025102/SSK102_RAAS_SOAP_Request.xml";
	public static final String MOCK_RESPONSE_SSK102_RESULT_DATA = "test/int0025/int0025102/SSK102_Response_Data.xml";
	public static final String MOCK_RESPONSE_SSK102_SOAP_RESPONSE = "test/int0025/int0025102/SSK102_SoapResult.xml";
	public static final String MOCK_MESSAGE_ZIPPED_RESULT = "test/int0025/int0025102/SSK102_BIN_ZippedMessageRoot.zip";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setupGlobalInitialization();

		ParameterMapHelper.initializeParameterMap(this.ctx, PROP_LOCAL_PROMPT_MAP);

		ctx.setProperty(PROP_PARAMETER_IN_USE_JAVA_URL_ENCODER, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_FORCE_SOAP, false);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="CallRaaS")
	public void testWithReportPromptsFlatToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS);
		
		Map<String,Object> prompts = new HashMap<String,Object>();
		prompts.put(VALUE_REPORT_PROMPT_WORKER, VALUE_REPORT_PROMPT_VALUE1);

		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);
	}
	
	@UnitTest(startComponent="CallRaaS")
	public void testWithReportPromptsSetToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS);
		
		Map<String,Object> prompts = new HashMap<String,Object>();
		Set<String> workers = new HashSet<String>();
		workers.add(VALUE_REPORT_PROMPT_VALUE1);
		workers.add(VALUE_REPORT_PROMPT_VALUE2);
		prompts.put(VALUE_REPORT_PROMPT_WORKER, workers);

		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);
	}
	
	@UnitTest(startComponent="CallRaaS")
	public void testWithReportPromptsListToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS);
		
		Map<String,Object> prompts = new HashMap<String,Object>();
		List<String> workers = new ArrayList<String>();
		workers.add(VALUE_REPORT_PROMPT_VALUE1);
		workers.add(VALUE_REPORT_PROMPT_VALUE2);
		prompts.put(VALUE_REPORT_PROMPT_WORKER, workers);
		
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);
	}
	
	@UnitTest(startComponent="CallRaaS")
	public void testWithoutReportPromptsToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);
	}
	
	@UnitTest(startComponent="CallRaaS")
	public void testWithReportPromptsErrorToVariableParentThread() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, new HashMap<String,String>());
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);
	}

	@UnitTest(startComponent="CallRaaS")
	public void testWithReportPromptsErrorToVariableChildThread() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS);
		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, new HashMap<String,String>());
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}

	@UnitTest(startComponent="CallRaaS")
	public void testWithoutReportPromptsToMessageWithStorage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, Boolean.TRUE);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
	}
	
	@UnitTest(startComponent="ExecuteWithSoap_102")
	public void testCallRaaSSoapSingleThread() throws Exception {
		setMessagePart(0, MOCK_MESSAGE_SSK102_TRANSFORMED_REPORT_PARAMETERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS_ENROLLMENT);
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, true);
		ctx.setProperty(PROP_PARAMETER_IN_COMPRESS_RESULTS, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);

		Map<String,Object> prompts = new HashMap<String,Object>();
		List<String> campaigns = new ArrayList<String>();
		campaigns.add(VALUE_WID_31);
		campaigns.add(VALUE_WID_32);
		campaigns.add(VALUE_WID_33);
		campaigns.add(VALUE_WID_34);
		campaigns.add(VALUE_WID_35);
		prompts.put(VALUE_REPORT_PROMPT_CAMPAIGN, campaigns);

		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
	}
	
	@UnitTest(startComponent="ExecuteWithSoap_102")
	public void testCallRaaSSoapChildThread() throws Exception {
		setMessagePart(0, MOCK_MESSAGE_SSK102_TRANSFORMED_REPORT_PARAMETERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS_ENROLLMENT);
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, true);
		ctx.setProperty(PROP_PARAMETER_IN_COMPRESS_RESULTS, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);

		Map<String,Object> prompts = new HashMap<String,Object>();
		List<String> campaigns = new ArrayList<String>();
		campaigns.add(VALUE_WID_31);
		campaigns.add(VALUE_WID_32);
		campaigns.add(VALUE_WID_33);
		campaigns.add(VALUE_WID_34);
		campaigns.add(VALUE_WID_35);
		prompts.put(VALUE_REPORT_PROMPT_CAMPAIGN, campaigns);

		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="ExecuteWithSoap_102")
	public void testCallRaaSSoapXmlEncoding() throws Exception {
		setMessagePart(0, MOCK_MESSAGE_SSK102_TRANSFORMED_REPORT_PARAMETERS, CONTENT_TYPE_TEXT_XML);
		
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS_ENROLLMENT);
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, true);
		ctx.setProperty(PROP_PARAMETER_IN_COMPRESS_RESULTS, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);

		Map<String,Object> prompts = new HashMap<String,Object>();
		prompts.put(VALUE_REPORT_PROMPT_CAMPAIGN, VALUE_ALL_CHARACTERS_XML_ENCODE_BEFORE);

		ctx.setProperty(PROP_LOCAL_PROMPT_MAP, prompts);
		ctx.setProperty(PROP_PARAMETER_IN_PROMPT_MAP, PROP_LOCAL_PROMPT_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
	}

	@UnitTest(startComponent="CallRaaS")
	public void testForceSoap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SAVE_RESULTS, false);
		ctx.setProperty(PROP_PARAMETER_IN_REPORT_ALIAS, VALUE_REPORT_SERVICE_ALIAS_ENROLLMENT);
		
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_RAAS_RESULTS);
		ctx.setProperty(PROP_PARAMETER_IN_IS_FORCE_SOAP, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_SOAP_REQUEST, VAR_SOAP_REQUEST);
		ctx.setProperty(PROP_PARAMETER_IN_IS_SKIP_SOAP_UNWRAP, true);
		
		setVariable(VAR_SOAP_REQUEST, MOCK_MESSAGE_SSK102_RAAS_SOAP_REQUEST, CONTENT_TYPE_TEXT_XML);
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
		Iterator<?> propertyNameIterator = ctx.getPropertyNames();

		while (propertyNameIterator.hasNext()) {
			String propertyName = String.valueOf(propertyNameIterator.next());
			
			if (!(boolean)ctx.getProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD)) {
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_IS_PROMPTS_PROVIDED), PROP_LOCAL_IS_PROMPTS_PROVIDED.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_IS_PROMPTS_VALID), PROP_LOCAL_IS_PROMPTS_VALID.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_REST_URL), PROP_LOCAL_REST_URL.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_QUERYSTRING), PROP_LOCAL_QUERYSTRING.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_KEY_VALUE_MAP), PROP_LOCAL_KEY_VALUE_MAP.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_IS_PROMPTS_MAP), PROP_LOCAL_IS_PROMPTS_MAP.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_IS_PROMPTS_EMPTY), PROP_LOCAL_IS_PROMPTS_EMPTY.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_FILENAME), PROP_LOCAL_FILENAME.equalsIgnoreCase(propertyName));
			}
			
			verifyGlobalPropertyNotModified(propertyName, null);
		}

		switch(getName()) {
			case "testWithReportPromptsErrorToVariableParentThread" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
				break;
	
			case "testWithReportPromptsErrorToVariableChildThread" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "0", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));

				@SuppressWarnings("rawtypes") 
				ConcurrentLinkedQueue queue = ctx.containsProperty(PROP_LOCAL_PARALLEL_LOG) ? (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG) : null;

				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_PARALLEL_LOG), queue);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "number of messages on the threaded queue"), 1, queue.size());
				break;
	
			case "testWithoutReportPromptsToMessageWithStorage" :
				verifyZipContent();
				break;
				
			case "testCallRaaSSoapSingleThread" :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_GLOBAL_RAAS_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_RAAS_RESULTS));
				break;
				
			case "testCallRaaSSoapChildThread" :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_GLOBAL_RAAS_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_RAAS_RESULTS));
				break;
				
			case "testCallRaaSSoapXmlEncoding" :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_GLOBAL_RAAS_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_RAAS_RESULTS));
				break;
				
			case "testForceSoap" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_GLOBAL_RAAS_RESULTS),
						compareAgainstVariable(VAR_GLOBAL_RAAS_RESULTS, CONTENT_TYPE_TEXT_XML, MOCK_RESPONSE_SSK102_SOAP_RESPONSE, CONTENT_TYPE_TEXT_XML));
				break;
				
			default :
				assertTrue(String.format(MESSAGE_VARIABLE_NOT_ADDED, VAR_GLOBAL_RAAS_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_RAAS_RESULTS));
				break;
		}
	}
	
	private void verifyZipContent() throws Exception {
		ZipInputStream zipStream = new ZipInputStream(getMessageInputStream(), StandardCharsets.UTF_8);
		
		List<String> archivedFilenames = new ArrayList<String>();
		List<InputStream> archivedEntries = new ArrayList<InputStream>();
		
		while (zipStream.available() > 0) {
			ZipEntry file = zipStream.getNextEntry();
			if (file != null) {
				archivedFilenames.add(file.getName());

				byte[] buffer = new byte[1024];
				int length = 0;

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((length = zipStream.read(buffer, 0, buffer.length)) > 0) {
					baos.write(buffer, 0, length);
				}

				archivedEntries.add(new ByteArrayInputStream(baos.toByteArray()));
			}
		}

		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "number of files in the zip archive"), 1, archivedFilenames.size());
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "file name in zip archive"), "rootpart.xml", archivedFilenames.get(0));
		
		verifyZipData(archivedEntries.get(0));
	}

	private void verifyZipData(InputStream zippedFile) throws Exception {
		assertTrue(String.format("Zip archive contents were not the expected value for zipped message rootpart"), 
				compare(
						getTestResourceInputStream(MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET2), 
						CONTENT_TYPE_TEXT_XML, 
						zippedFile, 
						CONTENT_TYPE_TEXT_XML, 
						Comparator.dom));
	}
	
	@Override
	protected void handledExceptionVerification(Throwable t) throws Exception {
		if (!getName().equalsIgnoreCase("testWithReportPromptsErrorToVariable")) {
			super.handledExceptionVerification(t);
		} 
	}

	
	@AssertAfter(id="InitializeAndFinalize_102", step="EvaluateParameters")
	public void verifyParameters() throws Exception {
		switch(getName()) {
			case "testWithReportPromptsFlatToVariable" :
				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_IS_PROMPTS_PROVIDED), ctx.getProperty(PROP_LOCAL_IS_PROMPTS_PROVIDED));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_IS_PROMPTS_PROVIDED), (Boolean)ctx.getProperty(PROP_LOCAL_IS_PROMPTS_PROVIDED));

				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_IS_PROMPTS_VALID), ctx.getProperty(PROP_LOCAL_IS_PROMPTS_VALID));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_IS_PROMPTS_VALID), (Boolean)ctx.getProperty(PROP_LOCAL_IS_PROMPTS_VALID));
				break;
				
			case "testWithReportPromptsSetToVariable" :
				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_IS_PROMPTS_PROVIDED), ctx.getProperty(PROP_LOCAL_IS_PROMPTS_PROVIDED));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_IS_PROMPTS_PROVIDED), (Boolean)ctx.getProperty(PROP_LOCAL_IS_PROMPTS_PROVIDED));

				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_IS_PROMPTS_VALID), ctx.getProperty(PROP_LOCAL_IS_PROMPTS_VALID));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_IS_PROMPTS_VALID), (Boolean)ctx.getProperty(PROP_LOCAL_IS_PROMPTS_VALID));
				break;
				
			case "testWithReportPromptsListToVariable" :
				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_IS_PROMPTS_PROVIDED), ctx.getProperty(PROP_LOCAL_IS_PROMPTS_PROVIDED));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_IS_PROMPTS_PROVIDED), (Boolean)ctx.getProperty(PROP_LOCAL_IS_PROMPTS_PROVIDED));

				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_IS_PROMPTS_VALID), ctx.getProperty(PROP_LOCAL_IS_PROMPTS_VALID));
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_IS_PROMPTS_VALID), (Boolean)ctx.getProperty(PROP_LOCAL_IS_PROMPTS_VALID));
				break;
				
			case "testWithoutReportPromptsToVariable" :
				break;
				
			case "testWithReportPromptsErrorToVariable" :
				break;
				
			default :
				break;
		}
	}

	@AssertBefore(id="ChooseProtocol_102")
	public void verifyRaaSEndpoint() throws Exception {
		String expectedURL = null;
		
		switch(getName()) {
			case "testWithReportPromptsFlatToVariable" :
				expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_REPORT_PROMPT_WORKER + "=" + VALUE_REPORT_PROMPT_VALUE1;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
				break;
				
			case "testWithReportPromptsSetToVariable" :
				expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_REPORT_PROMPT_WORKER + "=" + VALUE_REPORT_PROMPT_VALUE2 + "!" + VALUE_REPORT_PROMPT_VALUE1;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
				break;
				
			case "testWithReportPromptsListToVariable" :
				expectedURL = VALUE_REPORT_REST_ENDPOINT + "?" + VALUE_REPORT_PROMPT_WORKER + "=" + VALUE_REPORT_PROMPT_VALUE1 + "!" + VALUE_REPORT_PROMPT_VALUE2;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_REST_URL), expectedURL, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
				break;
				
			case "testWithoutReportPromptsToVariable" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_REST_URL), VALUE_REPORT_REST_ENDPOINT, String.valueOf(ctx.getProperty(PROP_LOCAL_REST_URL)));
				break;
				
			case "testForceSoap" :
				assertFalse(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, PROP_LOCAL_IS_VALID_LENGTH), ctx.containsProperty(PROP_LOCAL_IS_VALID_LENGTH));
				break;
				
			default :
				break;
		}
	}
	
	@AssertBefore(id="Results_102")
	public void verifyRaaSResults() throws Exception {
		String expectedValue = null;
		
		switch(getName()) {
			case "testWithReportPromptsFlatToVariable" :
				expectedValue = "21521";
				break;
				
			case "testWithReportPromptsSetToVariable" :
				expectedValue = "21521";
				break;
				
			case "testWithReportPromptsListToVariable" :
				expectedValue = "21521";
				break;
				
			case "testWithoutReportPromptsToVariable" :
				expectedValue = "21104";
				break;
				
			default :
				break;
		}
		
		if (StringUtils.isNotEmpty(expectedValue)) {
			boolean isProcessTextNode = false;
			
			InputStream message = getMessageInputStream();
			
			final QName typeAttributeQName = new QName("urn:com.workday.report/CR_INT0025_CheckWorkRights_VisaCheck_Inbound_Workers_Datasource", "type", "wd");
			final QName idElementQName = new QName("urn:com.workday.report/CR_INT0025_CheckWorkRights_VisaCheck_Inbound_Workers_Datasource", "ID", "wd");
			
			if (message != null) {
				XMLInputFactory inputFactory = XmlUtils.getXMLInputFactory();
				XMLEventReader xmlReader = inputFactory.createXMLEventReader(message, "UTF-8");
				
				while (xmlReader.hasNext()) {
					XMLEvent event = xmlReader.nextEvent();
					if (event.isStartElement()) {
						StartElement e = (StartElement)event;
						if (idElementQName.equals(e.getName())) {
							if (e.getAttributeByName(typeAttributeQName).getValue().equalsIgnoreCase("Employee_ID")) {
								isProcessTextNode = true;
							}
						}
					} else if (event.isEndElement()) {
						isProcessTextNode = false;
					} else if (event.isCharacters() && isProcessTextNode) {
						String e = event.asCharacters().getData();
						assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "employee id"), expectedValue, e);
						break;
					}
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@AssertBefore(id="ComposeRequest_102")
	public void verifyThreadedLogging() throws Exception {
		ConcurrentLinkedQueue queue = ctx.containsProperty(PROP_LOCAL_PARALLEL_LOG) ? (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG) : null;

		if (!(boolean)ctx.getProperty(PROP_PARAMETER_IN_IS_FORCE_SOAP)) {
			if ((boolean)ctx.getProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD)) {
				assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_PARALLEL_LOG), queue);
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "number of messages on the threaded queue"), 1, queue.size());
			} else {
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_PARALLEL_LOG), queue);
				assertPrimaryCloudLogEntryHTML("INFO", "Executing reports-as-a-service for the " + VALUE_REPORT_SERVICE_ALIAS_ENROLLMENT + " report alias using SOAP.", "The URL constructed including querystring parameters exceed the maximum URL length for RESTful RaaS calls of 16000 Bytes.  Switching to use SOAP as protocol.", "", "", "", "", "");
			}
		}
	}
	
	@AssertAfter(id="ComposeRequest_102", step="WriteSoapRequest")
	public void verifyListPopulated() throws Exception {
		switch (getName()) {
			case "testCallRaaSSoap" :
				validateTestCallRaaSSoap();
				break;
	
			case "testCallRaaSSoapXmlEncoding" :
				validateTestCallRaaSSoapXmlEncoding();
				break;
				
			default:
				break;
		}
	}
	
	private void validateTestCallRaaSSoap() throws Exception {
		List<String> campaigns = new ArrayList<String>();
		campaigns.add(VALUE_WID_31);
		campaigns.add(VALUE_WID_32);
		campaigns.add(VALUE_WID_33);
		campaigns.add(VALUE_WID_34);
		campaigns.add(VALUE_WID_35);

		boolean isProcessTextNode = false;
		
		InputStream message = getMessageInputStream();
		
		int valuesCounted = 0;
		final QName typeAttributeQName = new QName("urn:com.workday/bsvc", "type", "wd");
		final QName idElementQName = new QName("urn:com.workday/bsvc", "ID", "wd");
		
		if (message != null) {
			XMLInputFactory inputFactory = XmlUtils.getXMLInputFactory();
			XMLEventReader xmlReader = inputFactory.createXMLEventReader(message, "UTF-8");
			
			while (xmlReader.hasNext()) {
				XMLEvent event = xmlReader.nextEvent();
				if (event.isStartElement()) {
					StartElement e = (StartElement)event;
					if (idElementQName.equals(e.getName())) {
						if (e.getAttributeByName(typeAttributeQName).getValue().equalsIgnoreCase("WID")) {
							isProcessTextNode = true;
						}
					}
				} else if (event.isEndElement()) {
					isProcessTextNode = false;
				} else if (event.isCharacters() && isProcessTextNode) {
					valuesCounted++;
					String e = event.asCharacters().getData();
					assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "campaign id"), campaigns.contains(e));
				}
			}
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "number of arguments found during parsing"), campaigns.size(), valuesCounted);
		}
	}
	
	private void validateTestCallRaaSSoapXmlEncoding() throws Exception {
		boolean isProcessTextNode = false;
		
		InputStream message = getMessageInputStream();
		
		int valuesCounted = 0;
		final QName typeAttributeQName = new QName("urn:com.workday/bsvc", "type", "wd");
		final QName idElementQName = new QName("urn:com.workday/bsvc", "ID", "wd");
		
		if (message != null) {
			XMLInputFactory inputFactory = XmlUtils.getXMLInputFactory();
			XMLEventReader xmlReader = inputFactory.createXMLEventReader(message, "UTF-8");
			
			while (xmlReader.hasNext()) {
				XMLEvent event = xmlReader.nextEvent();
				if (event.isStartElement()) {
					StartElement e = (StartElement)event;
					if (idElementQName.equals(e.getName())) {
						if (e.getAttributeByName(typeAttributeQName).getValue().equalsIgnoreCase("WID")) {
							isProcessTextNode = true;
						}
					}
				} else if (event.isEndElement()) {
					isProcessTextNode = false;
				} else if (event.isCharacters() && isProcessTextNode) {
					valuesCounted++;
					String e = event.asCharacters().getData();
					//Compare against the original value.  If encoding was applied, the Java-based parsing will decode it for return.
					//If encoding was appropriately applied, then the string containing characters requiring escape will be returned as originally supplied. 
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "campaign id"), VALUE_ALL_CHARACTERS_XML_ENCODE_BEFORE, e);
				}
			}
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "number of arguments found during parsing"), 1, valuesCounted);
		}
	}
	
	@AssertAfter(id="NormalizeResponse_102", step="UnwrapSoap")
	public void verifyEnrichAndTransform() throws Exception {
		assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, "the contents of " + MOCK_RESPONSE_SSK102_RESULT_DATA), compare(
				getTestResourceInputStream(MOCK_RESPONSE_SSK102_RESULT_DATA), 
				CONTENT_TYPE_TEXT_XML, 
				(InputStream)ctx.getMessage().getMessagePart(0, InputStream.class), 
				CONTENT_TYPE_TEXT_XML, 
				Comparator.dom));

	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected String getResourceFileForSSK102REST() {
		String returnValue = super.getResourceFileForSSK102REST();

		switch (getName()) {
			case "testWithReportPromptsFlatToVariable" :
				returnValue = MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET3;
				break;
	
			case "testWithReportPromptsListToVariable" :
				returnValue = MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET3;
				break;
	
			case "testWithReportPromptsSetToVariable" :
				returnValue = MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET3;
				break;
	
			case "testWithoutReportPromptsToVariable" :
				returnValue = MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET1;
				break;
			
			case "testWithoutReportPromptsToMessageWithStorage" :
				returnValue = MOCK_RESPONSE_SSK102_REST_WORKER_DATA_RESPONSE_SUBSET2;
				break;
				
			default :
				break;
		}
		
		return returnValue;
	}

	@Override
	protected String getResourceFileForSSK102SOAP() {
		String returnValue = super.getResourceFileForSSK102SOAP();
		
		switch(getName()) {
			case "testCallRaaSSoapSingleThread" :
				returnValue = MOCK_RESPONSE_SSK102_SOAP_RESPONSE;
				
			case "testCallRaaSSoapChildThread" :
				returnValue = MOCK_RESPONSE_SSK102_SOAP_RESPONSE;
				
			case "testCallRaaSSoapXmlEncoding" :
				returnValue = MOCK_RESPONSE_SSK102_SOAP_RESPONSE;
				
			case "testForceSoap" :
				returnValue = MOCK_RESPONSE_SSK102_SOAP_RESPONSE;
				
			default :
				break;
		}

		return returnValue;
	}
}
