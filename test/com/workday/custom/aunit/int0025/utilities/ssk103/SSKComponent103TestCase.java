package com.workday.custom.aunit.int0025.utilities.ssk103;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.capeclear.xml.utils.DOMUtils;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.StudioStarterKitAunitException;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent103TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_SOAP_RESULTS = "globalSoapResults";

	public static final String PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION = "inWebServiceApplication";
	public static final String PROP_PARAMETER_IN_API_VERSION = "inApiVersion";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inBuildRequestDataLocation";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION_ID = "inBuildRequestDataLocationId";
	public static final String PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH = "inBuildRequestPathToXsltFile";
	public static final String PROP_PARAMETER_IN_VALIDATE_ONLY = "inValidateOnly";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_IN_IS_CHILD_THREAD = "inIsChildThreadContext";
	public static final String PROP_PARAMETER_IN_ERROR_FIELD_OVERRIDES = "inErrorLogOverrideMap";
	
	public static final String PROP_PARAMETER_OUT_SOAP_ERROR = "outIsSoapError";

	public static final String PROP_LOCAL_QUALIFIER_TYPED = "localIsQualifierTyped";
	public static final String PROP_LOCAL_VALUE_DEFINED = "localIsValueDefined";
	public static final String PROP_LOCAL_VALUE_TYPED = "localIsValueTyped";

	public static final String VALUE_WS_APP_HUMAN_RESOURCES = "Human_Resources";
	public static final String VALUE_WS_APP_INTEGRATIONS = "Integrations";
	public static final String VALUE_DATA_LOCATION_MESSAGE = "message";
	public static final String VALUE_DATA_LOCATION_VARIABLE = "variable";
	public static final String VALUE_DATA_LOCATION_PROPERTY = "property";
	public static final String VALUE_EXTERNAL_REQUEST_HEADER = "request-int0025";
	public static final String VALUE_EXTERNAL_APPLICATION_HEADER = "application-int0025";
	public static final String VALUE_EXTERNAL_ORIGINATOR_HEADER = "originator-int0025";

	public static final String MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS = "test/int0025/int0025103/SSK103_XML_inMapVariableName_3Workers.xml";
	public static final String MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML = "test/int0025/int0025103/SSK103_TXT_inMapVariableName_NonXml.txt";
	public static final String MOCK_MESSAGE_SSK103_VARIABLE_MAP_EMPTY_FILE = "test/int0025/int0025103/SSK103_XML_inMapVariableName_EmptyFile.xml";
	public static final String MOCK_MESSAGE_SSK103_VARIABLE_MAP_EMPTY_ROOT = "test/int0025/int0025103/SSK103_XML_inMapVariableName_EmptyRoot.xml";
	public static final String MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE1 = "test/int0025/int0025103/SSK103_SOAP_Get_Workers_Response1.xml";
	public static final String MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3 = "test/int0025/int0025103/SSK103_SOAP_Get_Workers_Response3.xml";
	public static final String MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES = "test/int0025/int0025103/SSK103_GetWorkersRequest_MultipleInstances.xsl";
	public static final String MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_SINGLE_INSTANCE = "test/int0025/int0025103/SSK103_GetWorkersRequest_SingleInstance.xsl";
	public static final String MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_UNPARAMETERIZED = "test/int0025/int0025103/SSK103_GetWorkersRequest_Unparameterized.xsl";
	public static final String MOCK_REQUEST_SSK103_SOAP_GET_WORKERS = "test/int0025/int0025103/SSK103_SOAP_Get_Workers_Request.xml";
	public static final String MOCK_REQUEST_SSK103_SOAP_LAUNCH_EVENT = "test/int0025/int0025103/SSK103_SOAP_Launch_Integration_Event_Request.xml";
	public static final String MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT = "test/int0025/int0025103/SSK103_SOAPFAULT_Launch_Integration_Event_Response.xml";
	
	public static final String XML_SOAPFAULT_CODE = "SOAP-ENV:Client.validationError";
	public static final String XML_SOAPFAULT_STRING = "Validation error occurred. Invalid ID value.  'A Text Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'";
	public static final String XML_SOAPFAULT_DETAIL = "<detail xmlns:wd=\\\"urn:com.workday/bsvc\\\" xmlns:SOAP-ENV=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"> <wd:Validation_Fault> <wd:Validation_Error> <wd:Message>Invalid ID value. 'A Text Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Message> <wd:Detail_Message>Invalid ID value. 'A Text Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Detail_Message> <wd:Xpath>/bsvc:Launch_Integration_Event_Request[1]/bsvc:Integration_Launch_Parameter_Data[3]/bsvc:Launch_Parameter_Reference[1]</wd:Xpath> </wd:Validation_Error> <wd:Validation_Error> <wd:Message>Invalid ID value. 'A Number Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Message> <wd:Detail_Message>Invalid ID value. 'A Number Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Detail_Message> <wd:Xpath>/bsvc:Launch_Integration_Event_Request[1]/bsvc:Integration_Launch_Parameter_Data[4]/bsvc:Launch_Parameter_Reference[1]</wd:Xpath> </wd:Validation_Error> <wd:Validation_Error> <wd:Message>Invalid ID value. 'A DateTime Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Message> <wd:Detail_Message>Invalid ID value. 'A DateTime Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Detail_Message> <wd:Xpath>/bsvc:Launch_Integration_Event_Request[1]/bsvc:Integration_Launch_Parameter_Data[5]/bsvc:Launch_Parameter_Reference[1]</wd:Xpath> </wd:Validation_Error> <wd:Validation_Error> <wd:Message>Invalid ID value. 'A Date Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Message> <wd:Detail_Message>Invalid ID value. 'A Date Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Detail_Message> <wd:Xpath>/bsvc:Launch_Integration_Event_Request[1]/bsvc:Integration_Launch_Parameter_Data[6]/bsvc:Launch_Parameter_Reference[1]</wd:Xpath> </wd:Validation_Error> <wd:Validation_Error> <wd:Message>Invalid ID value. 'A Single Instance Reference' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Message> <wd:Detail_Message>Invalid ID value. 'A Single Instance Reference' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Detail_Message> <wd:Xpath>/bsvc:Launch_Integration_Event_Request[1]/bsvc:Integration_Launch_Parameter_Data[7]/bsvc:Launch_Parameter_Reference[1]</wd:Xpath> </wd:Validation_Error> <wd:Validation_Error> <wd:Message>Invalid ID value. 'A Multi Instance Reference' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Message> <wd:Detail_Message>Invalid ID value. 'A Multi Instance Reference' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Detail_Message> <wd:Xpath>/bsvc:Launch_Integration_Event_Request[1]/bsvc:Integration_Launch_Parameter_Data[8]/bsvc:Launch_Parameter_Reference[1]</wd:Xpath> </wd:Validation_Error> <wd:Validation_Error> <wd:Message>Invalid ID value. 'An Enumeration Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Message> <wd:Detail_Message>Invalid ID value. 'An Enumeration Parameter' is not a valid ID value for type = 'Launch_Parameter_Name'</wd:Detail_Message> <wd:Xpath>/bsvc:Launch_Integration_Event_Request[1]/bsvc:Integration_Launch_Parameter_Data[9]/bsvc:Launch_Parameter_Reference[1]</wd:Xpath> </wd:Validation_Error> <wd:Validation_Error> <wd:Message>'SSKEnumType' is not a valid value for attribute parent_id when parent_type='Integration_Enumeration_Definition_ID'</wd:Message> <wd:Detail_Message>'SSKEnumType' is not a valid value for attribute parent_id when parent_type='Integration_Enumeration_Definition_ID'</wd:Detail_Message> <wd:Xpath>/bsvc:Launch_Integration_Event_Request[1]/bsvc:Integration_Launch_Parameter_Data[9]/bsvc:Launch_Parameter_Value_Data[1]/bsvc:Instance_Reference[1]</wd:Xpath> </wd:Validation_Error> </wd:Validation_Fault> </detail>";
	
	int expectedSoapCalls = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
			
		List<String> componentsToTrack = new ArrayList<String>();
		componentsToTrack.add("Call_HandleError_Validation_Error_103");
		componentsToTrack.add("Call_HandleError_Transform_Error_103");

		mockTracker.addComponentTracking(componentsToTrack);
		
		ctx.setProperty(PROP_PARAMETER_IN_API_VERSION, VALUE_API_VERSION);
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_HUMAN_RESOURCES);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_SOAP_RESULTS);
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.FALSE);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_PRIMARY);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);

		setupGlobalInitialization();
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10300ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10300ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10301ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setMessagePart(0, MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10301ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setMessagePart(0, MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setMessagePart(0, MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
	}

	@UnitTest(startComponent="CallSoap")
	public void testValidationError10302ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10302ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10303ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10303ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testVariableLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "myVarData");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testVariableLocalInValidationErrorDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_NONXML, CONTENT_TYPE_TEXT_PLAIN);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testVariableLocalInValidationErrorDataLocationAndDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("myWrongVarData", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10304ParentThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10304ChildThread() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		expectedSoapCalls = 0;
		
		setupThreadLogging();
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10305NotMap() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		
		List<String> prompts = new ArrayList<String>();
		prompts.add("typeFromMap");
		prompts.add("valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);

		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testValidationError10305EmptyMap() throws Exception {
		expectHandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		ctx.setProperty("mapSingleLevel", prompts);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testPropertyLocalInValidationErrorDataLocation() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testPropertyLocalInValidationErrorDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testPropertyLocalInValidationErrorDataLocationAndDataLocationId() throws Exception {
		expectUnhandledException();

		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		
		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("mapSingleLevel", prompts);
		
		expectedSoapCalls = 0;
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testSingleLevelPropertyMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_SINGLE_INSTANCE));

		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");

		ctx.setProperty("mapSingleLevel", prompts);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapSingleLevel");
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMultiLevelVariableMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));

		setVariable("mapMultiLevel", MOCK_MESSAGE_SSK103_VARIABLE_MAP_3WORKERS, CONTENT_TYPE_TEXT_XML);

		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapMultiLevel");
	}
		
	@UnitTest(startComponent="CallSoap")
	public void testEmptyVariableMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_SOAP_XSLT_FILEPATH, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_SSK103_GET_WORKERS_REQUEST_MULTIPLE_INSTANCES));
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VALUE_DATA_LOCATION_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION_ID, "mapMultiLevel");

		setVariable("mapMultiLevel", MOCK_MESSAGE_SSK103_VARIABLE_MAP_EMPTY_ROOT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithDebug() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithErrorWithDebug() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, Boolean.TRUE);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithSoapErrorDefaultParameters() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_API_VERSION, VALUE_API_VERSION);
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VALUE_DATA_LOCATION_MESSAGE);
		
		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_LAUNCH_EVENT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithSoapErrorFieldOverridesAll() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_API_VERSION, VALUE_API_VERSION);
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VALUE_DATA_LOCATION_MESSAGE);

		Map<String,String> mapFieldOverrides = new HashMap<String,String>();
		mapFieldOverrides.put(PROP_PARAMETER_IN_LOG_MESSAGE, "Overridden Message Summary");
		mapFieldOverrides.put(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL, "Overridden Message Detail");
		mapFieldOverrides.put(PROP_PARAMETER_IN_LOG_LEVEL, "debug");
		mapFieldOverrides.put(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "Overridden Reference ID");
		mapFieldOverrides.put(PROP_PARAMETER_IN_EXTRA_LOCAL_IN, "Overridden Local In");
		mapFieldOverrides.put(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "123456789");
		mapFieldOverrides.put(PROP_PARAMETER_IN_EXTRA_ERROR_CODE, "ERR-Override");
		mapFieldOverrides.put(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA, "Overridden Support Data");
		
		ctx.setProperty(PROP_PARAMETER_IN_ERROR_FIELD_OVERRIDES, mapFieldOverrides);
		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_LAUNCH_EVENT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithSoapErrorFieldOverridesLimited() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_API_VERSION, VALUE_API_VERSION);
		ctx.setProperty(PROP_PARAMETER_IN_WEB_SERVICE_APPLICATION, VALUE_WS_APP_INTEGRATIONS);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VALUE_DATA_LOCATION_MESSAGE);

		Map<String,String> mapFieldOverrides = new HashMap<String,String>();
		mapFieldOverrides.put(PROP_PARAMETER_IN_LOG_REFERENCE_ID, "Overridden Reference ID");
		mapFieldOverrides.put(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER, "123456789");
		
		ctx.setProperty(PROP_PARAMETER_IN_ERROR_FIELD_OVERRIDES, mapFieldOverrides);
		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_LAUNCH_EVENT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithValidateHeaderTrue() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, Boolean.TRUE);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithValidateHeaderFalse() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_VALIDATE_ONLY, Boolean.FALSE);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithRequestHeaderOnly() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_HEADER, VALUE_EXTERNAL_REQUEST_HEADER);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithSystemHeaderOnly() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_APPLICATION_HEADER, VALUE_EXTERNAL_APPLICATION_HEADER);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithOriginatorHeaderOnly() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ORIGINATOR_HEADER, VALUE_EXTERNAL_ORIGINATOR_HEADER);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="CallSoap")
	public void testMessageRootRequestWithAllExternalHeaders() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_REQUEST_HEADER, VALUE_EXTERNAL_REQUEST_HEADER);
		ctx.setProperty(PROP_PARAMETER_IN_APPLICATION_HEADER, VALUE_EXTERNAL_APPLICATION_HEADER);
		ctx.setProperty(PROP_PARAMETER_IN_ORIGINATOR_HEADER, VALUE_EXTERNAL_ORIGINATOR_HEADER);

		setMessagePart(0, MOCK_REQUEST_SSK103_SOAP_GET_WORKERS, CONTENT_TYPE_TEXT_XML);
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
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_QUALIFIER_TYPED), PROP_LOCAL_QUALIFIER_TYPED.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_VALUE_DEFINED), PROP_LOCAL_VALUE_DEFINED.equalsIgnoreCase(propertyName));
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_VALUE_TYPED), PROP_LOCAL_VALUE_TYPED.equalsIgnoreCase(propertyName));
			}

			verifyGlobalPropertyNotModified(propertyName, null);
		}
		
		List<String> componentHeaders = new ArrayList<String>();
		componentHeaders.add(VALUE_ORIGINATOR_HEADER);
		componentHeaders.add(VALUE_REQUEST_HEADER);
		componentHeaders.add(VALUE_APPLICATION_HEADER);
		verifyHeadersRemoved(componentHeaders);

		switch (getName()) {
			case "testMessageRootRequestWithErrorWithDebug":
				break;
				
			case "testMessageRootRequestWithSoapErrorDefaultParameters":
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT, CONTENT_TYPE_TEXT_XML));
				break;
				
			case "testMessageRootRequestWithSoapErrorFieldOverridesAll":
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT, CONTENT_TYPE_TEXT_XML));
				break;
				
			case "testMessageRootRequestWithSoapErrorFieldOverridesLimited":
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT, CONTENT_TYPE_TEXT_XML));
				break;
				
			default :
				break;
		}
		
		if (getName().startsWith("testValidationError103") && getName().endsWith("ParentThread")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
			
			assertEquals(String.format(MESSAGE_EXPECTED_STEP_NOT_CALLED, "Call_HandleError_Validation_Error_103"), 1, mockTracker.getCallCount("Call_HandleError_Validation_Error_103"));
		} else if (getName().startsWith("testValidationError103") && getName().endsWith("ChildThread")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "0", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
			
			assertEquals(String.format(MESSAGE_EXPECTED_STEP_NOT_CALLED, "Call_HandleError_Validation_Error_103"), 1, mockTracker.getCallCount("Call_HandleError_Validation_Error_103"));
			
			@SuppressWarnings("rawtypes") 
			ConcurrentLinkedQueue queue = ctx.containsProperty(PROP_LOCAL_PARALLEL_LOG) ? (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG) : null;

			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_LOCAL_PARALLEL_LOG), queue);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "number of messages on the threaded queue"), 1, queue.size());
		} else if (getName().startsWith("testValidationError103")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
			
			assertEquals(String.format(MESSAGE_EXPECTED_STEP_NOT_CALLED, "Call_HandleError_Validation_Error_103"), 1, mockTracker.getCallCount("Call_HandleError_Validation_Error_103"));
		} else if (getName().equalsIgnoreCase("testMessageRootRequestWithErrorWithDebug")) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR), "1", String.valueOf(ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR)));
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
			
			assertPrimaryCloudLogEntryHTML("ERROR", "Human_Resources application HTTP request error", "An error occurred while executing a SOAP request for the Human_Resources application.", null, "CallSoap_103", "-1", null, MOCK_EXCEPTION_MESSAGE);
		} else if (!getName().contains("LocalInValidationError") && 
				!"testMessageRootRequestWithSoapErrorDefaultParameters".equalsIgnoreCase(getName()) &&
				!"testMessageRootRequestWithSoapErrorFieldOverridesAll".equalsIgnoreCase(getName()) &&
				!"testMessageRootRequestWithSoapErrorFieldOverridesLimited".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_VARIABLE_NOT_ADDED, VAR_GLOBAL_SOAP_RESULTS), ctx.getVariables().containsKey(VAR_GLOBAL_SOAP_RESULTS));
		} else if ("testMessageRootRequestWithSoapErrorDefaultParameters".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_SOAP_ERROR), "soap", (String)ctx.getProperty(PROP_PARAMETER_OUT_SOAP_ERROR));
			assertPrimaryCloudLogEntryHTML(
					"error", 
					"Integrations application SOAP request error", 
					XML_SOAPFAULT_STRING, 
					XML_SOAPFAULT_CODE, 
					"CallSoap_103", 
					"10307", 
					"", 
					XML_SOAPFAULT_DETAIL);
		} else if ("testMessageRootRequestWithSoapErrorFieldOverridesAll".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_SOAP_ERROR), "soap", (String)ctx.getProperty(PROP_PARAMETER_OUT_SOAP_ERROR));
			assertPrimaryCloudLogEntryHTML(
					"debug", 
					"Overridden Message Summary", 
					"Overridden Message Detail", 
					"Overridden Reference ID", 
					"CallSoap_103", 
					"ERR-Override", 
					"123456789", 
					"Overridden Support Data");
		} else if ("testMessageRootRequestWithSoapErrorFieldOverridesLimited".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_SOAP_ERROR), "soap", (String)ctx.getProperty(PROP_PARAMETER_OUT_SOAP_ERROR));
			assertPrimaryCloudLogEntryHTML(
					"error", 
					"Integrations application SOAP request error", 
					XML_SOAPFAULT_STRING, 
					"Overridden Reference ID", 
					"CallSoap_103", 
					"10307", 
					"123456789", 
					XML_SOAPFAULT_DETAIL);
		}
	}
	
	@AssertAfter(id="Call_HandleError_Validation_Error_103")
	public void updateInitializeError() throws Exception {
		mockTracker.incrementCallCount("Call_HandleError_Validation_Error_103");
	}
	
	@AssertAfter(id="Call_HandleError_Transform_Error_103")
	public void updateTransformError() throws Exception {
		mockTracker.incrementCallCount("Call_HandleError_Transform_Error_103");
	}
	
	@AssertAfter(id="Transform_103", step="WriteSoap")
	public void verifySoapRequest() throws Exception {
		int expectedValue = 1;
		String xpath = null;
		Document doc = DOMUtils.parseToDOM(new InputSource(new InputStreamReader(getMessageInputStream(), "UTF-8")));

		switch(getName()) {
			case "testSingleLevelPropertyMap" :
				xpath = "count(/*:Envelope/*:Body/*:Get_Workers_Request/*:Request_References/*:Worker_Reference[*:ID[@*:type = \"typeFromMap\"] = \"valueFromMap\"])";
				break;
				
			case "testMultiLevelVariableMap" :
				expectedValue = 3;
				xpath = "count(/*:Envelope/*:Body/*:Get_Workers_Request/*:Request_References/*:Worker_Reference[*:ID[@*:type = \"typeFromMap1\"] = \"valueFromMap1\" or *:ID[@*:type = \"typeFromMap2\"] = \"valueFromMap2\" or *:ID[@*:type = \"typeFromMap3\"] = \"valueFromMap3\"])";
				break;
				
			case "testEmptyVariableMap" :
				expectedValue = 0;
				xpath = "count(/*:Envelope/*:Body/*:Get_Workers_Request/*:Request_References/*:Worker_Reference[*:ID[@*:type = \"typeFromMap\"] = \"valueFromMap\"])";
				break;
				
			default :
				break;
		}
		
		if (xpath != null) {
			assertEqualsXPath(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, expectedValue + " xpath matches"), String.valueOf(expectedValue), xpath, doc);
		}
	}
	
	@AssertAfter(id="SetHeaders_103")
	public void verifyHeaders() throws Exception {
		switch(getName()) {
			case "testMessageRootRequestWithValidateHeaderTrue":
				validateValidateOnlyHeader("1", String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_VALIDATE_ONLY_HEADER + " HTTP Header"));
				validateExternalOriginatorHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_ORIGINATOR_HEADER + " HTTP Header"));
				validateExternalSystemHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_APPLICATION_HEADER + " HTTP Header"));
				validateExternalRequestHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_REQUEST_HEADER+ " HTTP Header"));
				break;
				
			case "testMessageRootRequestWithValidateHeaderFalse":
				validateValidateOnlyHeader("0", String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_VALIDATE_ONLY_HEADER + " HTTP Header"));
				validateExternalOriginatorHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_ORIGINATOR_HEADER + " HTTP Header"));
				validateExternalSystemHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_APPLICATION_HEADER + " HTTP Header"));
				validateExternalRequestHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_REQUEST_HEADER+ " HTTP Header"));
				break;
				
			case "testMessageRootRequestWithRequestHeaderOnly":
				validateValidateOnlyHeader("0", String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_VALIDATE_ONLY_HEADER + " HTTP Header"));
				validateExternalOriginatorHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_ORIGINATOR_HEADER + " HTTP Header"));
				validateExternalSystemHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_APPLICATION_HEADER + " HTTP Header"));
				validateExternalRequestHeader(VALUE_EXTERNAL_REQUEST_HEADER, String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_REQUEST_HEADER+ " HTTP Header"));
				break;
				
			case "testMessageRootRequestWithSystemHeaderOnly":
				validateValidateOnlyHeader("0", String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_VALIDATE_ONLY_HEADER + " HTTP Header"));
				validateExternalOriginatorHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_ORIGINATOR_HEADER + " HTTP Header"));
				validateExternalSystemHeader(VALUE_EXTERNAL_APPLICATION_HEADER, String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_APPLICATION_HEADER+ " HTTP Header"));
				validateExternalRequestHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_REQUEST_HEADER+ " HTTP Header"));
				break;
				
			case "testMessageRootRequestWithOriginatorHeaderOnly":
				validateValidateOnlyHeader("0", String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_VALIDATE_ONLY_HEADER + " HTTP Header"));
				validateExternalOriginatorHeader(VALUE_EXTERNAL_ORIGINATOR_HEADER, String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_ORIGINATOR_HEADER+ " HTTP Header"));
				validateExternalSystemHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_APPLICATION_HEADER + " HTTP Header"));
				validateExternalRequestHeaderIsNotSet(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_REQUEST_HEADER+ " HTTP Header"));
				break;
				
			case "testMessageRootRequestWithAllExternalHeaders":
				validateValidateOnlyHeader("0", String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, VALUE_VALIDATE_ONLY_HEADER + " HTTP Header"));
				validateExternalOriginatorHeader(VALUE_EXTERNAL_ORIGINATOR_HEADER, String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_ORIGINATOR_HEADER+ " HTTP Header"));
				validateExternalSystemHeader(VALUE_EXTERNAL_APPLICATION_HEADER, String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_APPLICATION_HEADER+ " HTTP Header"));
				validateExternalRequestHeader(VALUE_EXTERNAL_REQUEST_HEADER, String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, VALUE_REQUEST_HEADER+ " HTTP Header"));
				break;
				
			default :
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
	@Override
	protected String getResourceFileForSSK103() {
		String returnValue = super.getResourceFileForSSK103();
		
		switch (getName()) {
			case "testSingleLevelPropertyMap" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE1;
				break;
			
			case "testMultiLevelVariableMap" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
			
			case "testEmptyVariableMap" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
				
			case "testMessageRootRequestWithDebug" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
				
			case "testMessageRootRequestWithSoapErrorDefaultParameters":
				returnValue = MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT;
				break;
				
			case "testMessageRootRequestWithSoapErrorFieldOverridesAll":
				returnValue = MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT;
				break;
				
			case "testMessageRootRequestWithSoapErrorFieldOverridesLimited":
				returnValue = MOCK_RESPONSE_SSK103_SOAPFAULT_LAUNCH_EVENT;
				break;
				
			case "testMessageRootRequestWithValidateHeaderTrue" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
				
			case "testMessageRootRequestWithValidateHeaderFalse" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
				
			case "testMessageRootRequestWithRequestHeaderOnly" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
				
			case "testMessageRootRequestWithSystemHeaderOnly" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
				
			case "testMessageRootRequestWithOriginatorHeaderOnly" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
				
			case "testMessageRootRequestWithAllExternalHeaders" :
				returnValue = MOCK_RESPONSE_SSK103_SOAP_GET_WORKERS_RESPONSE3;
				break;
				
			default :
				break;
		}

		return returnValue;
	}

	@Override
	protected Throwable getExceptionForSSK103() {
		Throwable returnValue = super.getExceptionForSSK103();
		
		switch (getName()) {
			case "testMessageRootRequestWithErrorWithDebug":
				returnValue = new StudioStarterKitAunitException();
				break;
				
			case "testMessageRootRequestWithSoapErrorDefaultParameters":
				returnValue = new StudioStarterKitAunitException();
				break;
				
			case "testMessageRootRequestWithSoapErrorFieldOverridesAll":
				returnValue = new StudioStarterKitAunitException();
				break;
				
			case "testMessageRootRequestWithSoapErrorFieldOverridesLimited":
				returnValue = new StudioStarterKitAunitException();
				break;
				
			default :
				break;
		}

		return returnValue;
	}
	
	
}
