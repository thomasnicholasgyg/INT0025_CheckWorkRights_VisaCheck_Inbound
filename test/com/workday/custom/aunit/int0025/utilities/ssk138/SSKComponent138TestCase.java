package com.workday.custom.aunit.int0025.utilities.ssk138;

import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AtComponent;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent138TestCase extends UtilitiesTestCase {

	public static final String VAR_DATA_LOCATION = "someVariableName";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inDataLocation";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	public static final String PROP_ROOT_TAG_OUTPUT = "outRootCloseTag138";
	public static final String PROP_PARAMETER_IN_LOG_TARGET = "inLogTarget";
	
	public static final String MOCK_XML_ONE_NAMESPACE_INPUT = "test/int0025/int0025138/SSK138_XML_SingleNamespace_Sample.xml";
	public static final String MOCK_XML_TWO_NAMESPACE_INPUT = "test/int0025/int0025138/SSK138_XML_TwoNamespace_Sample.xml";
	public static final String MOCK_CSV_INVALID_INPUT = "test/int0025/int0025138/SSK138_CSV_OneRowWithHeader_Sample.csv";
	
	public static final String VALUE_ROOT_TAG_SINGLE_NAMESPACE = "</wd:Report_Data>";
	public static final String VALUE_ROOT_TAG_TWO_NAMESPACE = "</soapenv:Envelope>";
	
	public static final String MOCK_ERROR_SUMMARY_MESSAGE = "GetRootCloseTag Error";
	public static final String MOCK_ERROR_DETAIL_MESSAGE = "An error occurred while parsing the input data for a root tag.";
	public static final String MOCK_ERROR_COMPONENT_ID = "Initialize_138";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupSecondaryLogInitialization();
		setupGlobalInitialization();
		
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ERROR, true);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	
	@UnitTest(startComponent="GetRootCloseTag")
	public void testOneNamespaceFromMessage() throws Exception {	
		setMessagePart(0, MOCK_XML_ONE_NAMESPACE_INPUT, CONTENT_TYPE_TEXT_XML);		
	}
	
	@UnitTest(startComponent="GetRootCloseTag")
	public void testTwoNamespaceFromMessage() throws Exception {
		setMessagePart(0, MOCK_XML_TWO_NAMESPACE_INPUT, CONTENT_TYPE_TEXT_XML);
	}
		
	@UnitTest(startComponent="GetRootCloseTag")
	public void testOneNamespaceFromVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_LOCATION);
		setVariable(VAR_DATA_LOCATION, MOCK_XML_ONE_NAMESPACE_INPUT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="GetRootCloseTag")
	public void testTwoNamespaceFromVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_LOCATION);
		setVariable(VAR_DATA_LOCATION, MOCK_XML_TWO_NAMESPACE_INPUT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="GetRootCloseTag")
	public void testInputAsCsvFromMessage() throws Exception {
		expectHandledException();
		setMessagePart(0, MOCK_CSV_INVALID_INPUT, CONTENT_TYPE_TEXT_CSV);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
	}
	
	@UnitTest(startComponent="GetRootCloseTag")
	public void testInputAsCsvFromVariable() throws Exception {
		expectHandledException();
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_DATA_LOCATION);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		setVariable(VAR_DATA_LOCATION, MOCK_CSV_INVALID_INPUT, CONTENT_TYPE_TEXT_CSV);
	}
	
	@UnitTest(startComponent="GetRootCloseTag")
	public void testErrorWrittenToPrimaryLog() throws Exception {		
		expectHandledException();
		setMessagePart(0, MOCK_XML_ONE_NAMESPACE_INPUT, CONTENT_TYPE_TEXT_XML);		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_PRIMARY);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
	}
	
	@UnitTest(startComponent="GetRootCloseTag")
	public void testErrorWrittenToSecondaryLog() throws Exception {		
		expectHandledException();
		setMessagePart(0, MOCK_XML_ONE_NAMESPACE_INPUT, CONTENT_TYPE_TEXT_XML);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, VALUE_LOG_SECONDARY);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
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

		if (getName().equalsIgnoreCase("testOneNamespaceFromMessage") || getName().equalsIgnoreCase("testOneNamespaceFromVariable")) {
			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_ROOT_TAG_OUTPUT), ctx.getProperty(PROP_ROOT_TAG_OUTPUT));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_ROOT_TAG_OUTPUT), VALUE_ROOT_TAG_SINGLE_NAMESPACE, ctx.getProperty(PROP_ROOT_TAG_OUTPUT));
		}
		
		else if (getName().equalsIgnoreCase("testTwoNamespaceFromMessage") || getName().equalsIgnoreCase("testTwoNamespaceFromVariable")) {
			assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, PROP_ROOT_TAG_OUTPUT), ctx.getProperty(PROP_ROOT_TAG_OUTPUT));
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_ROOT_TAG_OUTPUT), VALUE_ROOT_TAG_TWO_NAMESPACE, ctx.getProperty(PROP_ROOT_TAG_OUTPUT));
		}
		
		else if (getName().equalsIgnoreCase("testInputAsCsvFromMessage") || getName().equalsIgnoreCase("testInputAsCsvFromVariable")) {
			assertPrimaryCloudLogEntryHTML(VALUE_LOG_LEVEL_ERROR, MOCK_ERROR_SUMMARY_MESSAGE, MOCK_ERROR_DETAIL_MESSAGE, "", "Initialize_138.request.1.XsltPlus", "-1", "", "Content is not allowed in prolog.");
		}
		
		else if (getName().equalsIgnoreCase("testErrorWrittenToPrimaryLog")) {
			assertPrimaryCloudLogEntryCSV(VALUE_LOG_LEVEL_ERROR, MOCK_ERROR_SUMMARY_MESSAGE, MOCK_ERROR_DETAIL_MESSAGE, "", MOCK_ERROR_COMPONENT_ID, "-1", "", MOCK_EXCEPTION_MESSAGE);
		}
		
		else if (getName().equalsIgnoreCase("testErrorWrittenToSecondaryLog")) {
			assertSecondaryCloudLogEntryHTML(VALUE_LOG_LEVEL_ERROR, MOCK_ERROR_SUMMARY_MESSAGE, MOCK_ERROR_DETAIL_MESSAGE, "", MOCK_ERROR_COMPONENT_ID, "-1", "", MOCK_EXCEPTION_MESSAGE);
		}
	}
	
	@Override
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
			
	}
	
	@Override
	protected void handledExceptionVerification(Throwable t) throws Exception {
		
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	
	@AtComponent(id="Initialize_138", step="MoveVarToMsg")
	public Action mockError() throws Exception {
		if (getName().equalsIgnoreCase("testErrorWrittenToPrimaryLog") || getName().equalsIgnoreCase("testErrorWrittenToSecondaryLog")) {			
			return new StandardAction(Action.Type.mock_exception, new Exception(MOCK_EXCEPTION_MESSAGE));
		}
		else {
			return new StandardAction(Action.Type.invoke);
		}	
	}
}
