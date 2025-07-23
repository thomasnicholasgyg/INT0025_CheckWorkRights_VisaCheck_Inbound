package com.workday.custom.aunit.int0025;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.impl.mediators.MVELUtilHelper;
import com.capeclear.mediation.impl.mediators.utils.XPath;
import com.workday.aunit.AssemblyTestCase;
import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertAfterCompletion;
import com.workday.aunit.annotations.AtComponent;
import com.workday.custom.int0025.MediationConstants;
import com.workday.custom.int0025.SSKUtils;
import com.workday.custom.int0025.ssk109.FlexLogMetaConfiguration;
import com.workday.custom.int0025.ssk144.StringDeduplicator;


public abstract class CommonTestCase extends AssemblyTestCase {

	public static final String MOCK_EXCEPTION_MESSAGE = "RuntimeException thrown by the Test Plan";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for versioning
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String PROP_GLOBAL_API_VERSION = MediationConstants.PROPERTY_GLOBAL_API_VERSION;
	public static final String PROP_GLOBAL_SSK_VERSION = MediationConstants.PROPERTY_SSK_VERSION;

	public static final String VALUE_API_VERSION = MediationConstants.API_VERSION;
	public static final String VALUE_SSK_VERSION = MediationConstants.SSK_VERSION;
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants used as general example data and values
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final Integer VALUE_NEGATIVE_ONE = -1;
	public static final int VALUE_PRIMITIVE_INT = 2;
	public static final double VALUE_PRIMITIVE_DOUBLE = 5.615498302574;
	public static final Integer VALUE_OBJECT_INTEGER = new Integer(7);
	public static final Double VALUE_OBJECT_DOUBLE = new Double(11.1234567890123456);
	public static final String VALUE_EMPTY_STRING = "";

	public static final String VALUE_WID_1 = "19f7dabb79994d29931cd139df6272ed";
	public static final String VALUE_WID_2 = "1a21fe8602844d698488d17fde206233";
	public static final String VALUE_WID_3 = "1a6228638d14012c387a949aac4a5701";
	public static final String VALUE_WID_4 = "1a6228638d14014c27c88caaaf4a4605";
	public static final String VALUE_WID_5 = "1a6228638d1401a7e91052d2b14af008";
	public static final String VALUE_WID_6 = "1a6228638d1401c39ab7f72aae4a0403";
	public static final String VALUE_WID_7 = "1cf028c6f4484c248e8d7d573d7b8845";
	public static final String VALUE_WID_8 = "1d5964ddef944895a96f2a47cb4d14fc";
	public static final String VALUE_WID_9 = "1d7c55d2edbf44e09aedae56a8e27d01";
	public static final String VALUE_WID_10 = "1da8b422311b4929bfa4520f7f0b4e83";
	public static final String VALUE_WID_11 = "1f11684578714ebaa2ad8193c95bf4ad";
	public static final String VALUE_WID_12 = "1f8ff0a1f91410f6cb260e1be5fe0b91";
	public static final String VALUE_WID_13 = "2014150640fa42ebbafb6ab936b08073";
	public static final String VALUE_WID_14 = "2089f54014be486dabb76233e3c1e2d1";
	public static final String VALUE_WID_15 = "20af800cd57801a8b029fd3ae2391f65";
	public static final String VALUE_WID_16 = "20de7c89cf62486fa2e1e7be1ed8dff3";
	public static final String VALUE_WID_17 = "2115a14a04c5474d9feb7eb345ef78fa";
	public static final String VALUE_WID_18 = "2290cf186cf64d029027c64c4df7815a";
	public static final String VALUE_WID_19 = "22e3c423c9c34fcbb33cce253a4fd64e";
	public static final String VALUE_WID_20 = "231b4975b2a2461d98e7b7b3c613964d";
	public static final String VALUE_WID_21 = "234c31f7f86248a3a2ff93c3d6f7daf1";
	public static final String VALUE_WID_22 = "235a6f366da848fcab358f9c18bb49bf";
	public static final String VALUE_WID_23 = "237cd8b53b7010d254376547d7950b09";		//LP: Source Integration Event WID (Build and Debug Only)
	public static final String VALUE_WID_24 = "267a9f39967f405ab504bb8216ef55fb";
	public static final String VALUE_WID_25 = "26c439a5deed4a7dbab76709e0d2d2ca";
	public static final String VALUE_WID_26 = "26fe1e4bca6a4997a7f9153dc29f3834";
	public static final String VALUE_WID_27 = "2975f09937d1440da2772860d1a0696e";
	public static final String VALUE_WID_28 = "29aaeebac9bc4706852d848452b0187f";
	public static final String VALUE_WID_29 = "2a60516a115f481ca7c1063f0254b899";
	public static final String VALUE_WID_30 = "2aa76a0e9cef4ae9a082ef8fcdd632f9";
	public static final String VALUE_WID_31 = "d9253d793a300189f075c8a26a095e66";
	public static final String VALUE_WID_32 = "d9253d793a3001de67a291c06a097366";
	public static final String VALUE_WID_33 = "d9253d793a3001a6a584190efb095a98";
	public static final String VALUE_WID_34 = "d9253d793a30010d3112e126fb096698";		//LP: Log Aggregation Chain Event
	public static final String VALUE_WID_35 = "d9253d793a3001e14962763dfb097398";		//ATTR: Tag for Log Aggregator (overrides Cloud Log Output File Type)
	
	public static final String VALUE_WID_TAG_RETRIEVED = "b2f7dfe1c41a41c89a358318b28d6e1c";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for SSK-provided Attribute (and other) Services
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String PROP_GLOBAL_DOCUMENT_RETENTION_PERIOD = MediationConstants.PROPERTY_SSK_DOCUMENT_RETENTION_PERIOD;
	public static final String PROP_GLOBAL_OUTPUT_FILENAME = MediationConstants.PROPERTY_SSK_OUTPUT_FILENAME;
	public static final String PROP_GLOBAL_DOCUMENT_RETRIEVAL_TAG_TYPE = MediationConstants.PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG_TYPE;
	public static final String PROP_GLOBAL_DOCUMENT_RETRIEVAL_DOCTAG = MediationConstants.PROPERTY_SSK_RETRIEVAL_DOCUMENT_TAG;
	public static final String PROP_GLOBAL_DOCUMENT_DELIVERY_DOCTAG = MediationConstants.PROPERTY_SSK_DELIVERY_DOCUMENT_TAG;

	public static final String VALUE_DOCUMENT_EXPIRATION = "P30D";
	public static final String VALUE_OUTPUT_FILE = "INT_Results.txt";
	public static final String VALUE_DOCUMENT_RETRIEVAL_TAG_TYPE_DEFAULT = MediationConstants.PROPERTY_SSK_RETRIEVAL_MATCH_ALL;
	public static final String VALUE_DOCUMENT_RETRIEVAL_TAG = "Retrieved";
	public static final String VALUE_DOCUMENT_DELIVERY_TAG1 = "INT_Output";
	public static final String VALUE_DOCUMENT_DELIVERY_TAG2 = "INT_DocTag";

	public static final String MOCK_INITIALIZATION_SSK_INTEGRATION_SYSTEM = "test/int0025/StartHereTestCase_INTSYS_SSK-DEFAULT.xml";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for SSK-provided Launch Parameters
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String PROP_GLOBAL_IS_VALIDATION_MODE = MediationConstants.PROPERTY_SSK_IS_VALIDATION_MODE;
	public static final String PROP_GLOBAL_IS_DEBUG_MODE = MediationConstants.PROPERTY_SSK_IS_DEBUG_MODE;
	public static final String PROP_GLOBAL_DEBUG_EVENT_WID = MediationConstants.PROPERTY_SSK_DEBUG_EVENT_WID;
	public static final String PROP_GLOBAL_EVENT_WID = MediationConstants.PROPERTY_SSK_EVENT_WID;

	public static final Boolean VALUE_VALIDATION_MODE = Boolean.FALSE;
	public static final Boolean VALUE_DEBUG_MODE = Boolean.FALSE;

	public static final String MOCK_INITIALIZATION_SSK_EVENT_LAUNCH = "test/int0025/StartHereTestCase_LP_SSK-DEFAULT.xml";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for SSK core functionality
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String PROP_GLOBAL_STRING_DEDUPE = MediationConstants.PROPERTY_SSK_DEDUPLICATOR;

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for general-use mime types
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String CONTENT_TYPE_TEXT_CSV = "text/csv";
	public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
	public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
	public static final String CONTENT_TYPE_TEXT_XML = "text/xml";
	public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
	public static final String CONTENT_TYPE_APPLICATION_PDF = "application/pdf";
	public static final String CONTENT_TYPE_APPLICATION_ZIP = "application/zip";
	public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";
	
	public static final String CONTENT_TYPE_TEXT_PLAIN_UTF8 = "text/plain; charset=utf-8";
	public static final String CONTENT_TYPE_TEXT_PLAIN_UTF16 = "text/plain; charset=utf-16";
	public static final String CONTENT_TYPE_TEXT_PLAIN_UTF16LE = "text/plain; charset=utf-16le";
	public static final String CONTENT_TYPE_TEXT_PLAIN_UTF16BE = "text/plain; charset=utf-16be";
	
	public static final String CONTENT_TYPE_TEXT_XML_UTF8 = "text/xml; charset=utf-8";
	public static final String CONTENT_TYPE_TEXT_XML_UTF16 = "text/xml; charset=utf-16";
	public static final String CONTENT_TYPE_TEXT_XML_UTF16LE = "text/xml; charset=utf-16le";
	public static final String CONTENT_TYPE_TEXT_XML_UTF16BE = "text/xml; charset=utf-16be";
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for unit test framework support 
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String TEMPORARY_IN_MEMORY_XSLT = "localInMemoryXslt";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for formatting consistent unit test messages 
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String MESSAGE_UNEXPECTED_NULL = "The value of the [%1$s] property was unexpectedly null.";
	public static final String MESSAGE_UNEXPECTED_INSTANCE = "The value of the [%1$s] property was expected to be null but contained an unexpected instance.";
	public static final String MESSAGE_UNEXPECTED_TYPE = "Property [%1$s] was not the expected type.";
	public static final String MESSAGE_UNEXPECTED_VALUE_ANY = "[%1$s] did not contain the expected value.";
	public static final String MESSAGE_UNEXPECTED_VALUE_PROPERTY = "Property [%1$s] did not contain the expected value.";
	public static final String MESSAGE_UNEXPECTED_VALUE_VARIABLE = "Variable [%1$s] did not contain the expected value.";
	public static final String MESSAGE_VARIABLE_UNEXPECTED_VALUE = "The variable [%1$s] did not contain the expected value of [%2$s].";
	public static final String MESSAGE_DOCUMENT_UNEXPECTED_VALUE = "The source document [%1$s] did not contain the expected value of [%2$s].";
	public static final String MESSAGE_ROOT_UNEXPECTED_VALUE = "The message root part did not contain the expected value of [%1$s].";
	public static final String MESSAGE_PROPERTY_NOT_REMOVED = "Property [%1$s] was not expected to be defined in the MediationContext.";
	public static final String MESSAGE_PROPERTY_NOT_ADDED = "Property [%1$s] was expected to be defined in the MediationContext.";
	public static final String MESSAGE_VARIABLE_NOT_REMOVED = "Variable [%1$s] was not expected to be defined in the MediationContext.";
	public static final String MESSAGE_VARIABLE_NOT_ADDED = "Variable [%1$s] was expected to be defined in the MediationContext.";
	public static final String MESSAGE_UNEXPECTED_PROPERTY_PRESENT = "Property [%1$s] was not expected to be defined in the MediationContext.";
	public static final String MESSAGE_UNEXPECTED_VARIABLE_PRESENT = "Variable [%1$s] was not expected to be defined in the MediationContext.";
	public static final String MESSAGE_MOCKS_NOT_TRACKED = "Test [%1$s] attempted to validate the number of mock calls on component [%2$s]; however, [%2$s] was not initialized for tracking.  Check either the setUp() method of your test case, or the @UnitTest entry point for your test to confirm tracking is properly configured for the test.";
	public static final String MESSAGE_MOCKS_UNEXPECTED_CALLS = "The expected number of calls to mocked component [%2$s] on test [%1$s] was not returned.";
	public static final String MESSAGE_TEST_MISSING_VALIDATION = "Validation was not configured for TestCase [%1$s].  Each TestCase must be explicitly defined, even if there is no validation to ensure no false pass statuses.";
	public static final String MESSAGE_EXPECTED_STEP_NOT_CALLED = "Mediation/Step [%1$s] was expected to be executed but wasn't.";
	public static final String MESSAGE_UNEXPECTED_EXECUTION_PATH = "Test [%1$s] should not have reached this point of the integration flow.";
	public static final String MESSAGE_EXPECTED_VALUE_MISSING = "Property [%1$s] is missing or its value has been unexpectedly altered.  Expected value [%2$s] was not found.";
	public static final String MESSAGE_UNEXPECTED_VALUE_FOUND = "Property [%1$s] is missing or its value has been incorrectly configured.  Unexpected value [%2$s] should not have been found.";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for Studio built-in values
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String PROP_STUDIO_IS_ERROR_HANDLED = ":isErrorHandled";
	public static final String PROP_STUDIO_PAGEDGET_LAST_PAGE = "is.paged.get.last.page";
	public static final String PROP_STUDIO_PARALLEL_AGGREGATOR_COLLATE = "aggregator.destination.force.collate";
	public static final String PROP_STUDIO_PARALLEL_AGGREGATOR_BATCH = "aggregator.destination.force.batch";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for SA142 - General Cloud Logging
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	//Global logging-specific Property names used throughout the Integration .  These are generally either populated from Attributes or they are default initialized without options.
	public static final String PROP_GLOBAL_PRIMARYLOG_FILENAME = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_FILENAME;
	public static final String PROP_GLOBAL_PRIMARYLOG_EXPIRES = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_RETENTION;
	public static final String PROP_GLOBAL_PRIMARYLOG_MAX_COUNT_PER_FILE = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_MAX_SIZE;
	public static final String PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_FORMAT;
	public static final String PROP_GLOBAL_PRIMARYLOG_AGGREGATION_TAG = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_AGGREGATION_TAG;
	public static final String PROP_GLOBAL_PRIMARYLOG_AGGREGATION_TABNAME = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_AGGREGATION_TAB_NAME;
	public static final String PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_BY_FILE;
	public static final String PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_DEBUG;
	public static final String PROP_GLOBAL_PRIMARYLOG_COUNT_INFO = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_INFO;
	public static final String PROP_GLOBAL_PRIMARYLOG_COUNT_WARN = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_WARN;
	public static final String PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_ERROR;
	public static final String PROP_GLOBAL_PRIMARYLOG_COUNT_CRITICAL = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_CRITICAL;
	public static final String PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_COUNT_TOTAL;
	public static final String PROP_GLOBAL_PRIMARYLOG_FILES_STORED = MediationConstants.PROPERTY_SSK_PRIMARY_LOG_FILES_STORED;

	public static final String PROP_GLOBAL_SECONDARYLOG_IS_ENABLED = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_IS_ENABLED;
	public static final String PROP_GLOBAL_SECONDARYLOG_FILENAME = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_FILENAME;
	public static final String PROP_GLOBAL_SECONDARYLOG_EXPIRES = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_RETENTION;
	public static final String PROP_GLOBAL_SECONDARYLOG_MAX_COUNT_PER_FILE = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_MAX_SIZE;
	public static final String PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_FORMAT;
	public static final String PROP_GLOBAL_SECONDARYLOG_AGGREGATION_TAG = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_AGGREGATION_TAG;
	public static final String PROP_GLOBAL_SECONDARYLOG_AGGREGATION_TABNAME = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_AGGREGATION_TAB_NAME;
	public static final String PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_BY_FILE;
	public static final String PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_DEBUG;
	public static final String PROP_GLOBAL_SECONDARYLOG_COUNT_INFO = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_INFO;
	public static final String PROP_GLOBAL_SECONDARYLOG_COUNT_WARN = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_WARN;
	public static final String PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_ERROR;
	public static final String PROP_GLOBAL_SECONDARYLOG_COUNT_CRITICAL = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_CRITICAL;
	public static final String PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_COUNT_TOTAL;
	public static final String PROP_GLOBAL_SECONDARYLOG_FILES_STORED = MediationConstants.PROPERTY_SSK_SECONDARY_LOG_FILES_STORED;
	
	//Input Parameters for the Common.CreateLogEntry sub-assembly.
	public static final String PROP_PARAMETER_IN_LOG_FINALIZE = "inLogFinalize";
	public static final String PROP_PARAMETER_IN_LOG_LEVEL = "inLogLevel";
	public static final String PROP_PARAMETER_IN_LOG_MESSAGE = "inLogMessage";
	public static final String PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL = "inLogMessageDetail";
	public static final String PROP_PARAMETER_IN_LOG_REFERENCE_ID = "inLogReferenceId";
	public static final String PROP_PARAMETER_IN_EXTRA_LOCAL_IN = "inExtraLocalIn";
	public static final String PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER = "inExtraRecordNumber";
	public static final String PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA = "inExtraSupportData";
	public static final String PROP_PARAMETER_IN_EXTRA_ERROR_CODE = "inExtraErrorCode";

	public static final String VAR_CLOUD_LOG_PRIMARY = "cloud-log-primary";
	public static final String VAR_CLOUD_LOG_SECONDARY = "cloud-log-secondary";

	public static final String PROP_LOCAL_PARALLEL_LOG = "localParallelLog143";
	public static final String PROP_LOCAL_PRIMARYLOG_FILENAME = "localPrimaryLogFilenameForStorage";
	public static final String PROP_LOCAL_SECONDARYLOG_FILENAME = "localSecondaryLogFilenameForStorage";

	public static final String VALUE_LOG_LEVEL_DEBUG = "debug";
	public static final String VALUE_LOG_LEVEL_INFO = "info";
	public static final String VALUE_LOG_LEVEL_WARN = "warn";
	public static final String VALUE_LOG_LEVEL_ERROR = "error";
	public static final String VALUE_LOG_LEVEL_CRITICAL = "critical";
	public static final String VALUE_PRIMARYLOG_FILE = "PrimaryLogFile";
	public static final String VALUE_SECONDARYLOG_FILE = "SecondaryLogFile";
	public static final String VALUE_LOGFILE_FORMAT_HTML = "HTML";
	public static final String VALUE_LOGFILE_FORMAT_CSV = "CSV";
	public static final String VALUE_LOGFILE_FORMAT_XLSX = "XLSX";
	public static final String VALUE_LOG_EXPIRATION = "P90D";
	public static final Integer VALUE_LOG_MAX_ENTRIES = 1500;
	public static final String VALUE_AGGREGATION_TAG_NAME = "SSK_LOGagg";
	public static final String VALUE_AGGREGATION_TAG_REFID = "SSK_LOGagg";
	public static final String VALUE_AGGREGATION_TAG_WID = VALUE_WID_35;
	public static final String VALUE_PRIMARYLOG_TABNAME = "tab1";
	public static final String VALUE_SECONDARYLOG_TABNAME = "tab2";

	public static final String VALUE_LOG_PRIMARY = "primary";
	public static final String VALUE_LOG_SECONDARY = "secondary";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for SA144 - Debug
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String PROP_GLOBAL_DEBUG_PROPERTY_LIST = MediationConstants.PROPERTY_SSK_DEBUG_LIST;
	public static final String PROP_GLOBAL_DEBUG_TARGET_TYPE = MediationConstants.PROPERTY_SSK_DEBUG_TARGET_TYPE_VALIDATION;
	public static final String PROP_GLOBAL_DEBUG_TARGET_NAME = MediationConstants.PROPERTY_SSK_DEBUG_TARGET_NAME_VALIDATION;
	public static final String PROP_GLOBAL_DEBUG_ARCHIVE_FILENAME = MediationConstants.PROPERTY_SSK_DEBUG_ARCHIVE;

	public static final String PROP_PARAMETER_IN_TARGET_TYPE = MediationConstants.PROP_PARAMETER_IN_TARGET_TYPE;
	public static final String PROP_PARAMETER_IN_TARGET_NAME = MediationConstants.PROP_PARAMETER_IN_TARGET_NAME;
	public static final String PROP_PARAMETER_IN_ENTITY_NAME = "inEntityName";
	public static final String PROP_PARAMETER_IN_ENTITY_SUFFIX = "inEntitySuffix";
	public static final String PROP_PARAMETER_OUT_ENTITY_NAME = "outEntityName";
	public static final String PROP_PARAMETER_OUT_DEBUG_LOGGED = "outDebugLogged144";

	public static final String VALUE_PARAMETER_TARGET_TYPE_MESSAGE = "message";
	public static final String VALUE_PARAMETER_TARGET_TYPE_PROPERTY = "property";
	public static final String VALUE_PARAMETER_TARGET_TYPE_PROPERTIES = "properties";
	public static final String VALUE_PARAMETER_TARGET_TYPE_VARIABLE = "variable";
	public static final String VALUE_PARAMETER_TARGET_TYPE_MAP = "map";
	public static final String VALUE_PARAMETER_TARGET_TYPE_LIST = "list";
	public static final String VALUE_PARAMETER_TARGET_TYPE_SET = "set";
	public static final String VALUE_PARAMETER_TARGET_TYPE_FINALIZE = "finalize";
	public static final String VALUE_PARAMETER_DEBUG_ARCHIVE_FILENAME = "DebugFiles-INT0025_CheckWorkRights_VisaCheck_Inbound";

	public static final String MOCK_MESSAGE_SSK144_PROPS_TO_XML = "test/int0025/int0025144/SSK144_XML_PropsToXML.xml";
	public static final String MOCK_MESSAGE_SSK144_LIST_TO_XML = "test/int0025/int0025144/SSK144_XML_ListToXML.xml";
	public static final String MOCK_MESSAGE_SSK144_MAP_TO_XML = "test/int0025/int0025144/SSK144_XML_MapToXML.xml";
	public static final String MOCK_MESSAGE_SSK144_SET_TO_XML = "test/int0025/int0025144/SSK144_XML_SetToXML.xml";
	public static final String MOCK_RESPONSE_SSK144_XML_DATA = "test/int0025/int0025144/SSK144_XML_Data.xml";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for SA121 - Static Code Analysis
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String PROP_GLOBAL_STATIC_ANALYSIS_EXEMPTIONS = MediationConstants.PROPERTY_SSK_STATIC_ANALYSIS_EXEMPTIONS;

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Constants for SA147 - Flex Log
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	public static final String MOCK_FLEXLOG_DEFAULT_SCHEMA = "test/int0025/int0025109/SSK109_FlexLogs.xsd";
	public static final String MOCK_FLEXLOG_DEFAULT_CONFIG = "test/int0025/int0025109/SSK109_FlexLogs.xml";

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Class used for tracking execution of mediations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	protected class MockCallTracker {
		Map<String, Integer> componentStatMap = null;
		
		public int getCallCount(String mockedComponent) {
			return (componentStatMap.containsKey(mockedComponent)) ? componentStatMap.get(mockedComponent) : -1;
		}
		
		public void incrementCallCount(String mockedComponent) {
			if (componentStatMap.containsKey(mockedComponent)) {
				synchronized(componentStatMap) {
					componentStatMap.put(mockedComponent, componentStatMap.get(mockedComponent) + 1);
				}
			}
		}
		
		public MockCallTracker(List<String> mockedComponents) {
			super();
			componentStatMap = new ConcurrentHashMap<String, Integer>();
			addComponentTracking(mockedComponents);
		}

		public void assertCounts(String testName, Map<String, Integer> mockedComponentsToAssert) {
			for (String key : mockedComponentsToAssert.keySet()) {
				if (componentStatMap.containsKey(key)) {
					assertEquals(String.format(MESSAGE_MOCKS_UNEXPECTED_CALLS, testName, key), mockedComponentsToAssert.get(key), componentStatMap.get(key));
				} else {
					fail(String.format(MESSAGE_MOCKS_NOT_TRACKED, testName, key));
				}
			}
		}
		
		public void addComponentTracking(List<String> mockedComponents) {
			for (String mockedComponent : mockedComponents) {
				addComponentTracking(mockedComponent);
			}
		}
		
		public void addComponentTracking(String mockedComponent) {
			componentStatMap.put(mockedComponent, new Integer(0));
		}
	}
	
	protected MediationContext ctx = null;
	protected MockCallTracker mockTracker = null;
	protected ArrayList<String> testsExpectingHandledExceptions = null;
	protected ArrayList<String> testsExpectingUnhandledExceptions = null;
	protected ArrayList<String> componentProperties = null;
	protected ArrayList<String> componentVariables = null;	
	

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Support for FlexLog that applies to all SSK aUnit tests
	 * 
	 * Accommodate different logging configurations per test with initiation per component
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	protected interface IFlexLogTestResourceLoader {

		public void loadFileToVariable(String variable, String fileResouce, String contentType) throws Throwable;
	}

	protected class MockFlexLogMetaConfig extends FlexLogMetaConfiguration {
		private IFlexLogTestResourceLoader loader;
		
		public MockFlexLogMetaConfig(IFlexLogTestResourceLoader loader) {
			super();
			this.loader = loader;
		}

		@Override
		public String getConfigurationFile() {
			return getFlexLogConfigurationFile();
		}

		@Override
		public String getSchema() {
			return MOCK_FLEXLOG_DEFAULT_SCHEMA;
		}

		@Override
		public void readConfigurationToVariable(MVELUtilHelper util, String variableName, String configurationFile) throws Throwable {
			loader.loadFileToVariable(variableName, configurationFile, CONTENT_TYPE_TEXT_XML);
		}

		@Override
		public void readSchemaToVariable(MVELUtilHelper util, String variableName, String schemaFile) throws Throwable {
			loader.loadFileToVariable(variableName, schemaFile, CONTENT_TYPE_TEXT_XML);
		}
	}
	
	protected String getFlexLogConfigurationFile() {
		return MOCK_FLEXLOG_DEFAULT_CONFIG;
	}
	
	protected FlexLogMetaConfiguration getFlexLogMetaConfiguration() {
		return new MockFlexLogMetaConfig(new IFlexLogTestResourceLoader() {
			@Override
			public void loadFileToVariable(String variable, String fileResouce, String contentType) throws Throwable {
				setVariable(variable, fileResouce, contentType);
			}
		});
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * TestCase setUp that applies to all SSK aUnit tests
	 * 
	 * Also includes other setup-helper methods
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		List<String> componentsToTrack = new ArrayList<String>();
		componentsToTrack.add("PIM_CriticalUnhandledException_GEH");
		componentsToTrack.add("PIM_InitializationFailed_109");
		componentsToTrack.add("PIM_PrimaryTag_142");
		componentsToTrack.add("PIM_Critial_Stats_Primary_142");
		componentsToTrack.add("PIM_Error_Stats_Primary_142");
		componentsToTrack.add("PIM_Warning_Stats_Primary_142");
		componentsToTrack.add("PIM_Info_Stats_Primary_142");
		componentsToTrack.add("PIM_Debug_Stats_Primary_142");
		componentsToTrack.add("PIM_SecondaryTag_142");
		componentsToTrack.add("PIM_Critial_Stats_Secondary_142");
		componentsToTrack.add("PIM_Error_Stats_Secondary_142");
		componentsToTrack.add("PIM_Warning_Stats_Secondary_142");
		componentsToTrack.add("PIM_Info_Stats_Secondary_142");
		componentsToTrack.add("PIM_Debug_Stats_Secondary_142");
		componentsToTrack.add("PIM_FlexLogTag_147");
		componentsToTrack.add("PIM_Critial_Stats_FlexLog_147");
		componentsToTrack.add("PIM_Error_Stats_FlexLog_147");
		componentsToTrack.add("PIM_Warning_Stats_FlexLog_147");
		componentsToTrack.add("PIM_Info_Stats_FlexLog_147");
		componentsToTrack.add("PIM_Debug_Stats_FlexLog_147");
		componentsToTrack.add("PIM_108");
		componentsToTrack.add("PIE_108");

		mockTracker = new MockCallTracker(componentsToTrack);

		testsExpectingHandledExceptions = new ArrayList<String>();
		testsExpectingUnhandledExceptions = new ArrayList<String>();
		componentProperties = new ArrayList<String>();
		componentVariables = new ArrayList<String>();

		ctx = getMediationContext();

		setupIntegrationSystem();
		setupIntegrationLaunchEvent();
	}

	protected void setupIntegrationSystem() throws Exception {
		setIntegrationSystem(getFileForSetupIntegrationSystem());
	}
	
	protected String getFileForSetupIntegrationSystem() {
		return MOCK_INITIALIZATION_SSK_INTEGRATION_SYSTEM;
	}
	
	protected void setupIntegrationLaunchEvent() throws Exception {
		setIntegrationLaunchEvent(getFileForSetupIntegrationLaunchEvent());
	}
	
	protected String getFileForSetupIntegrationLaunchEvent() {
		return MOCK_INITIALIZATION_SSK_EVENT_LAUNCH;
	}
	
	protected void setupGlobalInitialization() throws Exception {
		setupGlobalInitializationIntegrationSystem();
		setupGlobalInitializationLaunchParameters();
		setupPrimaryLogInitialization();
		setupSecondaryLogInitialization();
		setupFlexLog();
		setupXsltLibraries();
	}
	
	protected void setupGlobalInitializationIntegrationSystem() throws Exception {
		ctx.setProperty(PROP_GLOBAL_DOCUMENT_RETENTION_PERIOD, VALUE_LOG_EXPIRATION);

		ctx.setProperty(PROP_GLOBAL_API_VERSION, VALUE_API_VERSION);
	}

	protected void setupGlobalInitializationLaunchParameters() throws Exception {
		ctx.setProperty(PROP_GLOBAL_IS_VALIDATION_MODE, VALUE_VALIDATION_MODE);
		ctx.setProperty(PROP_GLOBAL_IS_DEBUG_MODE, VALUE_DEBUG_MODE);
		ctx.setProperty(PROP_GLOBAL_DEBUG_EVENT_WID, VALUE_WID_23);
		ctx.setProperty(PROP_GLOBAL_EVENT_WID, VALUE_WID_22);
	}
	
	protected void setupPrimaryLogInitialization() throws Exception {
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILENAME, VALUE_PRIMARYLOG_FILE);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_EXPIRES, VALUE_LOG_EXPIRATION);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_HTML);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_MAX_COUNT_PER_FILE, 0);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_BY_FILE, 0);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_DEBUG, 0);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_INFO, 0);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_WARN, 0);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_ERROR, 0);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_CRITICAL, 0);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_COUNT_TOTAL, 0);
		ctx.setProperty(PROP_GLOBAL_PRIMARYLOG_FILES_STORED, 0);
	}
	
	protected void setupSecondaryLogInitialization() throws Exception {
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_IS_ENABLED, true);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILENAME, VALUE_SECONDARYLOG_FILE);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_EXPIRES, VALUE_LOG_EXPIRATION);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILE_FORMAT, VALUE_LOGFILE_FORMAT_CSV);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_MAX_COUNT_PER_FILE, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_BY_FILE, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_DEBUG, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_INFO, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_WARN, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_ERROR, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_CRITICAL, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_COUNT_TOTAL, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_FILES_STORED, 0);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_AGGREGATION_TABNAME, VALUE_SECONDARYLOG_TABNAME);
		ctx.setProperty(PROP_GLOBAL_SECONDARYLOG_AGGREGATION_TAG, VALUE_AGGREGATION_TAG_REFID);
	}
	
	protected void setupXsltLibraries() throws Exception {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_XSLT_FUNCTION_LIBRARY_MESSAGES, SSKUtils.getContextualURL(ctx, MediationConstants.VALUE_XSLT_LIB_MESSAGE));
		ctx.setProperty(MediationConstants.PROPERTY_SSK_XSLT_FUNCTION_LIBRARY_FLEX_EXT, SSKUtils.getContextualURL(ctx, MediationConstants.VALUE_XSLT_LIB_FLEXLOG_EXTENSIONS));
		ctx.setProperty(MediationConstants.PROPERTY_SSK_XSLT_FUNCTION_LIBRARY_FLEX_MSG, SSKUtils.getContextualURL(ctx, MediationConstants.VALUE_XSLT_LIB_FLEXLOG_MESSAGES));
	}
	
	protected void setupFlexLog() throws Exception {
		MVELUtilHelper util = new MVELUtilHelper(ctx);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_FLEX_LOG_META_BEAN, getFlexLogMetaConfiguration());
		SSKUtils.configureFlexLog(ctx, util);		
 	}
	
	@SuppressWarnings("rawtypes")
	protected void setupThreadLogging() throws Exception {
		ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, new java.util.concurrent.ConcurrentLinkedQueue());
	}
	
	protected void setupDebugValidations() {
		ctx.setProperty(PROP_GLOBAL_DEBUG_PROPERTY_LIST, new ArrayList<String>());
		ctx.setProperty(PROP_GLOBAL_STRING_DEDUPE, new StringDeduplicator());
		ctx.setProperty(PROP_GLOBAL_DEBUG_ARCHIVE_FILENAME, VALUE_PARAMETER_DEBUG_ARCHIVE_FILENAME + ".zip");
		
		Set<String> typeSet = new java.util.HashSet<String>();
		typeSet.add("message");
		typeSet.add("property");
		typeSet.add("properties");
		typeSet.add("variable");
		typeSet.add("map");
		typeSet.add("list");
		typeSet.add("set");
		typeSet.add("finalize");
		ctx.setProperty(PROP_GLOBAL_DEBUG_TARGET_TYPE, typeSet);
		
		Set<String> nameSet = new java.util.HashSet<String>();
		nameSet.add("message");
		nameSet.add("property");
		nameSet.add("properties");
		nameSet.add("variable");
		nameSet.add("map");
		nameSet.add("list");
		nameSet.add("set");
		ctx.setProperty(PROP_GLOBAL_DEBUG_TARGET_NAME, typeSet);
	}
	
	protected String loadXsltToVariable(String variableName, String resourceXslt) throws Exception {
		setVariable(variableName, resourceXslt, CONTENT_TYPE_TEXT_XML);
		return "mctx:vars/" + variableName;
	}
	
	protected boolean isVariableNullOrUndefined(String variableName) throws Exception {
		return SSKUtils.isVariableNullOrUndefined(ctx, variableName);
	}
	
	protected InputStream getVariableInputStream(String variableName) throws Exception {
		return SSKUtils.getVariableInputStream(ctx, variableName);
	}
	
	protected InputStream getMessageInputStream() throws Exception {
		return SSKUtils.getMessageInputStream(ctx);
	}
	
	protected InputStream getMessagePartInputStream(int partNumber) throws Exception {
		return SSKUtils.getMessagePartInputStream(partNumber, ctx);
	}
	
	protected InputStream getMessageInputStream(MediationContext mc) throws Exception {
		return SSKUtils.getMessageInputStream(mc);
	}
	
	protected InputStream getMessagePartInputStream(int partNumber, MediationContext mc) throws Exception {
		return SSKUtils.getMessagePartInputStream(partNumber, mc);
	}
	
	protected Document getMessageAsDocument() throws Exception {
		return SSKUtils.getMessageAsXmlDocument(ctx);
	}

	protected String prependPrologToXmlString(String xml) {
		return "<?xml version='1.0' encoding='UTF-8'?>" + xml;
	}
	
	protected Document parseStringToXML(String data) throws Exception {
		return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
	}
	
	private Comparator getComparatorType(String resourceMimeType) throws Exception {
		Comparator comparatorType = Comparator.binary;
		if (resourceMimeType.toLowerCase().contains(CONTENT_TYPE_TEXT_XML.toLowerCase())) {
			comparatorType = Comparator.dom;
		} else if (CONTENT_TYPE_TEXT_CSV.equalsIgnoreCase(resourceMimeType) ||
					CONTENT_TYPE_TEXT_HTML.equalsIgnoreCase(resourceMimeType) ||
					CONTENT_TYPE_TEXT_PLAIN.equalsIgnoreCase(resourceMimeType) ||
					CONTENT_TYPE_APPLICATION_JSON.equalsIgnoreCase(resourceMimeType)) {
			comparatorType = Comparator.text;
		}
		
		return comparatorType;
	}
	
	protected boolean compareAgainstSource(InputStream dataStream, String dataMimeType, String resource, String resourceMimeType, Comparator comparatorType) throws Exception {
		return compare(
				getTestResourceInputStream(resource), 
				resourceMimeType, 
				dataStream, 
				dataMimeType, 
				comparatorType);

	}
	
	protected boolean compareAgainstVariable(String variableName, String variableMimeType, String resource, String resourceMimeType) throws Exception {
		
		return compareAgainstVariable(variableName, variableMimeType, resource, resourceMimeType, getComparatorType(resourceMimeType));
	}

	protected boolean compareAgainstVariable(String variableName, String variableMimeType, String resource, String resourceMimeType, Comparator comparatorType) throws Exception {
		return compare(
				getTestResourceInputStream(resource), 
				resourceMimeType, 
				getVariableInputStream(variableName), 
				variableMimeType, 
				comparatorType);
	}
	
	protected boolean compareAgainstMessageRoot(String rootMimeType, String resource, String resourceMimeType) throws Exception {
		
		return compareAgainstMessageRoot(rootMimeType, resource, resourceMimeType, getComparatorType(resourceMimeType));
	}

	protected boolean compareAgainstMessageRoot(String rootMimeType, String resource, String resourceMimeType, Comparator comparatorType) throws Exception {
		return compare(
				getTestResourceInputStream(resource), 
				resourceMimeType, 
				getMessageInputStream(), 
				rootMimeType, 
				comparatorType);
	}
	
	protected boolean compareAgainstMessagePart(int partNumber, String messagePartMimeType, String resource, String resourceMimeType) throws Exception {
		
		return compareAgainstMessagePart(partNumber, messagePartMimeType, resource, resourceMimeType, getComparatorType(resourceMimeType));
	}

	protected boolean compareAgainstMessagePart(int partNumber, String messagePartMimeType, String resource, String resourceMimeType, Comparator comparatorType) throws Exception {
		return compare(
				getTestResourceInputStream(resource), 
				resourceMimeType, 
				getMessagePartInputStream(partNumber), 
				messagePartMimeType, 
				comparatorType);
	}
	
	protected boolean compareMessageRootAgainstVariable(String rootMimeType, String variableName, String variableMimeType) throws Exception {
		
		return compareMessageRootAgainstVariable(rootMimeType, variableName, variableMimeType, getComparatorType(variableMimeType));
	}

	protected boolean compareMessageRootAgainstVariable(String rootMimeType, String variableName, String variableMimeType, Comparator comparatorType) throws Exception {
		return compare(
				getMessageInputStream(), 
				rootMimeType, 
				getVariableInputStream(variableName), 
				variableMimeType, 
				comparatorType);
	}
	
	protected boolean dynamicCompareAgainstMessageRoot(String rootMimeType, String resource, String resourceMimeType, Map<String, String> replacementKeys) throws Exception {
		
		return dynamicCompareAgainstMessageRoot(rootMimeType, resource, resourceMimeType, getComparatorType(resourceMimeType), replacementKeys);
	}

	protected boolean dynamicCompareAgainstMessageRoot(String rootMimeType, String resource, String resourceMimeType, Comparator comparatorType, Map<String, String> replacementKeys) throws Exception {
		ResourceConverter converter = new ResourceConverter();
		
		return compare(
				converter.prepareResourceWithReplacements(ctx, getTestResourceInputStream(resource), replacementKeys), 
				resourceMimeType, 
				getMessageInputStream(), 
				rootMimeType, 
				comparatorType);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	protected void assertFlexLogEntry(String logName, String messageRightOfTimestamp) throws Exception {
		String logContents = IOUtils.toString(SSKUtils.getFlexLogInputStream(ctx, logName));
		
		assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, logName), logContents.contains(messageRightOfTimestamp));
	}
	
	protected void assertPrimaryCloudLogEntryHTML(String level, String message, String messageDetail, String reference, String localIn, String errorCode, String recordNumber, String supportData) throws Exception {
		assertCloudLogEntryHTML(VAR_CLOUD_LOG_PRIMARY, level.toUpperCase(), message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}
	
	protected void assertSecondaryCloudLogEntryHTML(String level, String message, String messageDetail, String reference, String localIn, String errorCode, String recordNumber, String supportData) throws Exception {
		assertCloudLogEntryHTML(VAR_CLOUD_LOG_SECONDARY, level.toUpperCase(), message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}
	
	private void assertCloudLogEntryHTML(String logVariableName, String level, String message, String messageDetail, String reference, String localIn, String errorCode, String recordNumber, String supportData) throws Exception {
		String logContents = IOUtils.toString(getVariableInputStream(logVariableName));
		
		String expectedLogFirstHalf = new StringBuilder().append("{\"l\":\"").append(StringUtils.isEmpty(level) ? "" : level).append("\",\"m\":\"").append(StringUtils.isEmpty(message) ? "" : message).append("\",\"d\":\"").append(StringUtils.isEmpty(messageDetail) ? "" : messageDetail).append("\",\"r\":\"").append(StringUtils.isEmpty(reference) ? "" : reference).append("\",").toString();
		String expectedLogSecondHalf = new StringBuilder().append(",\"LocalIn\":\"").append(StringUtils.isEmpty(localIn) ? "" : localIn).append("\",\"ErrorCode\":\"").append(StringUtils.isEmpty(errorCode) ? "" : errorCode).append("\",\"RecordNumber\":\"").append(StringUtils.isEmpty(recordNumber) ? "" : recordNumber).append("\",\"SupportData\":\"").append(StringUtils.isEmpty(supportData) ? "" : supportData).append("\"}").toString();

		assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, logVariableName), logContents.contains(expectedLogFirstHalf) && logContents.contains(expectedLogSecondHalf));
	}
	
	protected void assertNoPrimaryCloudLogEntryHTML(String message) throws Exception {
		assertNoCloudLogEntryHTML(VAR_CLOUD_LOG_PRIMARY, message);
	}
	
	protected void assertNoSecondaryCloudLogEntryHTML(String message) throws Exception {
		assertNoCloudLogEntryHTML(VAR_CLOUD_LOG_SECONDARY, message);
	}
	
	private void assertNoCloudLogEntryHTML(String logVariableName, String message) throws Exception {
		if (!isVariableNullOrUndefined(logVariableName)) {
			String logContents = IOUtils.toString(getVariableInputStream(logVariableName));
			
			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, logVariableName), logContents.contains(message));
		}
	}
	
	protected void assertPrimaryCloudLogEntryCSV(String level, String message, String messageDetail, String reference, String localIn, String errorCode, String recordNumber, String supportData) throws Exception {
		assertCloudLogEntryCSV(VAR_CLOUD_LOG_PRIMARY, level.toUpperCase(), message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}
	
	protected void assertSecondaryCloudLogEntryCSV(String level, String message, String messageDetail, String reference, String localIn, String errorCode, String recordNumber, String supportData) throws Exception {
		assertCloudLogEntryCSV(VAR_CLOUD_LOG_SECONDARY, level.toUpperCase(), message, messageDetail, reference, localIn, errorCode, recordNumber, supportData);
	}

	private void assertCloudLogEntryCSV(String logVariableName, String level, String message, String messageDetail, String reference, String localIn, String errorCode, String recordNumber, String supportData) throws Exception {
		String logContents = IOUtils.toString(getVariableInputStream(logVariableName));
		
		String expectedLog = new StringBuilder().
				append(",").append(StringUtils.isEmpty(level) ? "" : level).
				append(",").append(StringUtils.isEmpty(reference) ? "" : StringEscapeUtils.escapeCsv(reference)).
				append(",").append(StringUtils.isEmpty(message) ? "" : StringEscapeUtils.escapeCsv(message)).
				append(",").append(StringUtils.isEmpty(messageDetail) ? "" : StringEscapeUtils.escapeCsv(messageDetail)).
				append(",").append(StringUtils.isEmpty(localIn) ? "" : StringEscapeUtils.escapeCsv(localIn)).
				append(",").append(StringUtils.isEmpty(errorCode) ? "" : StringEscapeUtils.escapeCsv(errorCode)).
				append(",").append(StringUtils.isEmpty(recordNumber) ? "" : StringEscapeUtils.escapeCsv(recordNumber)).
				append(",").append(StringUtils.isEmpty(supportData) ? "" : StringEscapeUtils.escapeCsv(supportData)).toString();

		assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, logVariableName), logContents.contains(expectedLog));
	}
	
	public void assertEqualsXPath(String msg, String expected, String xpath, Document doc) throws Exception {
		assertEquals(msg, expected, new XPath(xpath, MediationConstants.ASSEMBLY_VERSION, getMediationContext()).stringValueOf(doc));				
	}

	@AssertAfter(id="LogCounts_109", step="UpdateLogCounts")
	public void verifyCreateLogEntryParameters() throws Exception {
		evaluateCreateLogEntryParameters();
	}
	
	protected void evaluateCreateLogEntryParameters() throws Exception {
		//provided as an override opportunity for downstream tests to inspect local-in parameters with visibility since don't exist before local-out call and are removed upon returning
	}
	
	@AssertAfterCompletion
	public void verifyExitState() throws Exception {
		Throwable t = ctx.getException();
		
		if (t != null) {
			if (ctx.isErrorHandled()) {
				System.out.println("An exception was handled. Details=["+t+"]");
				handledExceptionVerification(t);
			} else {
				unhandledExceptionVerification(t);
			}
		} else {
			if (testsExpectingHandledExceptions.contains(getName()) || testsExpectingUnhandledExceptions.contains(getName())) {
				fail("Unit test was configured to expect an exception, but none was thrown.  Verify the unexpected results.");
			}
		}
		
		verifyContextPropertyExitCleanup(componentProperties);
		verifyContextVariableExitCleanup(componentVariables);
		extendedExitStateVerification(t);
	}
	
	protected final void expectHandledException() {
		testsExpectingHandledExceptions.add(getName());
	}
	
	protected final void expectUnhandledException() {
		testsExpectingUnhandledExceptions.add(getName());
	}
	
	protected final boolean isExpectedHandledException() {
		return testsExpectingHandledExceptions.contains(getName());
	}
	
	protected final boolean isExpectedUnhandledException() {
		return testsExpectingUnhandledExceptions.contains(getName());
	}
	
	protected final boolean isExpectedException() {
		return isExpectedHandledException() || isExpectedUnhandledException();
	}
	
	protected final void registerComponentProperty(String property) {
		componentProperties.add(property);
	}
	
	protected final void registerComponentVariable(String variable) {
		componentVariables.add(variable);
	}
	
	/**
	 * This method is used as a placeholder to allow every test to do verification on the exit state of the code.  In other words,
	 * when the test is over and code has finished running, this is an opportunity to check that it finished just as you expected.
	 * Check the values of the message, variables, properties, log content, etc...
	 * 
	 * The method is abstract because CommonTestCase provides the basic behavior of intercepting the end of the test, but how each
	 * test should have exited is very specific to that code and not something that can be in CommonTestCase.  So each test is 
	 * expected to override this method and define what it means to finish successfully.  If there is no verification desired, then 
	 * the method can be empty. 
	 * 
	 * @param t
	 * @throws Exception
	 */
	protected abstract void extendedExitStateVerification(Throwable t) throws Exception;
	
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
		//This method allows (among other things) testing of local-in validations that complete with unhandled exceptions and result in immediate failure even though
		//they're valid and proved what you wanted to test, i.e. the local-in validations worked.
		if (!testsExpectingUnhandledExceptions.contains(getName())) {
			fail("Unhandled exception occurred! " + ((t != null) ? t.getMessage() : ""));
		}
	}
	
	protected void handledExceptionVerification(Throwable t) throws Exception {
		//This method helps to avoid the unit test scenario when you hit a real error, but the test still passes green because the error is handled gracefully.
		//If you ARE expecting a handled exception as the result of your @UnitTest completing, then override this method and do something else, e.g. assertTrue("Got the exception", true)
		if (!testsExpectingHandledExceptions.contains(getName())) {
			fail("It's good that this exception was handled gracefully, but the fact remains that there was an exception that you probably weren't expecting...  Something needs to be fixed.  "+ t.getClass() +"  "+ t.getMessage());
		}
	}

	protected void verifyContextPropertyExitCleanup(List<String> componentProperties) {
		Iterator<?> propertyNameIterator = ctx.getPropertyNames();

		while (propertyNameIterator.hasNext()) {
			String propertyName = String.valueOf(propertyNameIterator.next());

			if (componentProperties.contains(propertyName)) {
				// The current context property should not be on the list of properties expected to have been removed, otherwise it wasn't cleaned up appropriately
				assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, propertyName), componentProperties.contains(propertyName));
			} else {
				verifyGlobalPropertyNotModified(propertyName, null);
			}
		}
	}

	protected void verifyContextVariableExitCleanup(List<String> componentVariables) throws Exception {
		for (String varName : componentVariables) {
			// The current context variable should not be on the list of variables expected to have been removed, otherwise it wasn't cleaned up appropriately
			
			assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, varName), SSKUtils.isVariableNullOrUndefined(ctx, varName));
		}
	}
	
	/**
	 * Validate that the sub-assembly didn't change a global Property that it should not have changed.  Sub-assemblies should not break the principle of encapsulation and modify values outside of their scope.
	 * 
	 * If global modifications are warranted by the sub-assembly's actions, then the value should be returned in an Output Parameter and modified in the main integration flow, not the sub-assembly.
	 * 
	 * @param propertyName
	 * @param excludedProperties
	 */
	@SuppressWarnings("unchecked")
	protected void verifyGlobalPropertyNotModified(String propertyName, List<String> excludedProperties) {
		if (StringUtils.isNotBlank(propertyName) && (excludedProperties == null || !excludedProperties.contains(propertyName))) {
			switch(propertyName) {
				case PROP_GLOBAL_API_VERSION :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_API_VERSION), VALUE_API_VERSION, ctx.getProperty(PROP_GLOBAL_API_VERSION));
					break;
					
				case PROP_GLOBAL_IS_VALIDATION_MODE :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_IS_VALIDATION_MODE), VALUE_VALIDATION_MODE, ctx.getProperty(PROP_GLOBAL_IS_VALIDATION_MODE));
					break;
					
				case PROP_GLOBAL_IS_DEBUG_MODE:
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_IS_DEBUG_MODE), VALUE_DEBUG_MODE, ctx.getProperty(PROP_GLOBAL_IS_DEBUG_MODE));
					break;
					
				case PROP_GLOBAL_DOCUMENT_RETENTION_PERIOD :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_DOCUMENT_RETENTION_PERIOD), VALUE_LOG_EXPIRATION, ctx.getProperty(PROP_GLOBAL_DOCUMENT_RETENTION_PERIOD));
					break;
					
				case PROP_GLOBAL_PRIMARYLOG_FILENAME :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_FILENAME), VALUE_PRIMARYLOG_FILE, ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_FILENAME));
					break;
					
				case PROP_GLOBAL_PRIMARYLOG_EXPIRES :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_PRIMARYLOG_EXPIRES), VALUE_LOG_EXPIRATION, ctx.getProperty(PROP_GLOBAL_PRIMARYLOG_EXPIRES));
					break;
					
				case PROP_GLOBAL_SECONDARYLOG_FILENAME :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_FILENAME), VALUE_SECONDARYLOG_FILE, ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_FILENAME));
					break;
					
				case PROP_GLOBAL_SECONDARYLOG_EXPIRES :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_SECONDARYLOG_EXPIRES), VALUE_LOG_EXPIRATION, ctx.getProperty(PROP_GLOBAL_SECONDARYLOG_EXPIRES));
					break;
					
				case PROP_GLOBAL_OUTPUT_FILENAME :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_OUTPUT_FILENAME), VALUE_OUTPUT_FILE, ctx.getProperty(PROP_GLOBAL_OUTPUT_FILENAME));
					break;
					
				case PROP_GLOBAL_DOCUMENT_RETRIEVAL_TAG_TYPE :
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_GLOBAL_DOCUMENT_RETRIEVAL_TAG_TYPE), VALUE_DOCUMENT_RETRIEVAL_TAG_TYPE_DEFAULT, ctx.getProperty(PROP_GLOBAL_DOCUMENT_RETRIEVAL_TAG_TYPE));
					break;
					
				case PROP_GLOBAL_DOCUMENT_RETRIEVAL_DOCTAG :
					List<String> actualRetrieveTags = (List<String>)ctx.getProperty(PROP_GLOBAL_DOCUMENT_RETRIEVAL_DOCTAG);
					List<String> expectedRetrieveTags = new ArrayList<String>();
					expectedRetrieveTags.add(VALUE_WID_TAG_RETRIEVED);

					assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_GLOBAL_DOCUMENT_RETRIEVAL_DOCTAG, "[" + VALUE_WID_TAG_RETRIEVED + "]"), CollectionUtils.isEqualCollection(actualRetrieveTags, expectedRetrieveTags));
					break;
					
				case PROP_GLOBAL_DOCUMENT_DELIVERY_DOCTAG :
					List<String> actualDeliverTags = (List<String>)ctx.getProperty(PROP_GLOBAL_DOCUMENT_DELIVERY_DOCTAG);
					List<String> expectedDeliverTags = new ArrayList<String>();
					expectedDeliverTags.add(VALUE_DOCUMENT_DELIVERY_TAG1);
					expectedDeliverTags.add(VALUE_DOCUMENT_DELIVERY_TAG2);

					assertTrue(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_GLOBAL_DOCUMENT_DELIVERY_DOCTAG, "[" + VALUE_DOCUMENT_DELIVERY_TAG1 + "," + VALUE_DOCUMENT_DELIVERY_TAG2 +"]"), CollectionUtils.isEqualCollection(actualDeliverTags, expectedDeliverTags));
					break;
					
				default :
					break;
			}
		}
	}
	
	protected void verifyMediationContextVariablesRemoved(List<String> variableNames) throws Exception {
		if (!CollectionUtils.isEmpty(variableNames)) {
			for (String variableName : variableNames) {
				assertNull(String.format(MESSAGE_VARIABLE_NOT_REMOVED, variableName), ctx.getVariables().get(variableName));
			}
		}
	}
	
	protected ZipInputStream getZipContent(String zipSource) throws Exception {
		return new ZipInputStream(
				zipSource.equalsIgnoreCase("message") ? getMessageInputStream() : getVariableInputStream(zipSource), 
				StandardCharsets.UTF_8);
	}
	
	protected void verifyZipContent(ZipInputStream zipStream, int numberOfFiles) throws Exception {
		List<String> zipFilenames = new ArrayList<String>();
		
		while (zipStream.available() > 0) {
			ZipEntry file = zipStream.getNextEntry();
			if (file != null) {
				zipFilenames.add(file.getName());
			}
		}

		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "number of files in the zip archive"), numberOfFiles, zipFilenames.size());
	}
	
	protected void verifyZipContent(ZipInputStream zipStream, String testContainsFilename) throws Exception {
		List<String> zipFilenames = new ArrayList<String>();
		
		while (zipStream.available() > 0) {
			ZipEntry file = zipStream.getNextEntry();
			if (file != null) {
				zipFilenames.add(file.getName());
			}
		}

		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "file name in zip archive"), testContainsFilename, zipFilenames.get(0));
	}
	
	protected void verifyZipContent(ZipInputStream zipStream, String testFilename, InputStream testResource, String resourceContentType) throws Exception {
		boolean isMatchFound = false;
		InputStream zippedFileContent = null;
		
		while (zipStream.available() > 0) {
			ZipEntry file = zipStream.getNextEntry();
			if (file != null && testFilename.equalsIgnoreCase(file.getName())) {
				isMatchFound = true;

				byte[] buffer = new byte[1024];
				int length = 0;

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((length = zipStream.read(buffer, 0, buffer.length)) > 0) {
					baos.write(buffer, 0, length);
				}

				zippedFileContent = new ByteArrayInputStream(baos.toByteArray());
				break;
			}
		}

		assertTrue("Zip Archive did not contain the expected file ["+ testFilename +"]", isMatchFound);
		assertTrue("Zip archive contents were not the expected value at ["+ testFilename +"]",  
				compare(
						testResource, 
						resourceContentType, 
						zippedFileContent, 
						resourceContentType, 
						CONTENT_TYPE_TEXT_XML.equalsIgnoreCase(resourceContentType) ? Comparator.dom : Comparator.binary));
	}


	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AtComponent(id="PIM_CriticalUnhandledException_GEH")
	public Action mockCriticalUnhandledException() throws Exception {
		mockTracker.incrementCallCount("GEH_PIMCriticalUnhandledException");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="Initialize_109", step="FlexLogAssets")
	public Action handleFlexLogMetaBean() throws Exception {
		this.ctx.setProperty(MediationConstants.PROPERTY_SSK_FLEX_LOG_META_BEAN, getFlexLogMetaConfiguration());
		return new StandardAction(Action.Type.continue_to_next);
	}
	
	@AtComponent(id="PIM_InitializationFailed_109")
	public Action mockLogInitializationFailed() throws Exception {
		mockTracker.incrementCallCount("PIM_InitializationFailed_109");
		return new StandardAction(Action.Type.mock);
	}

	@AtComponent(id="PIM_PrimaryTag_142")
	public Action mockPrimaryLogTag() throws Exception {
		mockTracker.incrementCallCount("PIM_PrimaryTag_142");
		return new StandardAction(Action.Type.mock);
	}

	@AtComponent(id="PIM_SecondaryTag_142")
	public Action mockSecondaryLogTag() throws Exception {
		mockTracker.incrementCallCount("PIM_SecondaryTag_142");
		return new StandardAction(Action.Type.mock);
	}

	@AtComponent(id="PIM_FlexLogTag_147")
	public Action mockFlexLogTag() throws Exception {
		mockTracker.incrementCallCount("PIM_FlexLogTag_147");
		return new StandardAction(Action.Type.mock);
	}

	@AtComponent(id="PIM_Critical_Stats_Primary_142")
	public Action mockPrimaryLogStatsCriticalStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Critical_Stats_Primary_142");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Error_Stats_Primary_142")
	public Action mockPrimaryLogStatsErrorStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Error_Stats_Primary_142");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Warning_Stats_Primary_142")
	public Action mockPrimaryLogStatsWarningStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Warning_Stats_Primary_142");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Info_Stats_Primary_142")
	public Action mockPrimaryLogStatsInfoStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Info_Stats_Primary_142");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Debug_Stats_Primary_142")
	public Action mockPrimaryLogStatsDebugStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Debug_Stats_Primary_142");
		return new StandardAction(Action.Type.mock);
	}

	@AtComponent(id="PIM_Critical_Stats_Secondary_142")
	public Action mockSecondaryLogStatsCriticalStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Critical_Stats_Secondary_142");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Error_Stats_Secondary_142")
	public Action mockSecondaryLogStatsErrorStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Error_Stats_Secondary_142");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Warning_Stats_Secondary_142")
	public Action mockSecondaryLogStatsWarningStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Warning_Stats_Secondary_142");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Info_Stats_Secondary_142")
	public Action mockSecondaryLogStatsInfoStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Info_Stats_Secondary_142");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Debug_Stats_Secondary_142")
	public Action mockSecondaryLogStatsDebugStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Debug_Stats_Secondary_142");
		return new StandardAction(Action.Type.mock);
	}

	@AtComponent(id="PIM_Critical_Stats_FlexLog_147")
	public Action mockFlexLogStatsCriticalStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Critical_Stats_FlexLog_147");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Error_Stats_FlexLog_147")
	public Action mockFlexLogStatsErrorStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Error_Stats_FlexLog_147");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Warning_Stats_FlexLog_147")
	public Action mockFlexLogStatsWarningStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Warning_Stats_FlexLog_147");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Info_Stats_FlexLog_147")
	public Action mockFlexLogStatsInfoStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Info_Stats_FlexLog_147");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_Debug_Stats_FlexLog_147")
	public Action mockFlexLogStatsDebugStats() throws Exception {
		mockTracker.incrementCallCount("PIM_Debug_Stats_FlexLog_147");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIM_108")
	public Action mockIntegrationEventProgressPIM() throws Exception {
		mockTracker.incrementCallCount("PIM_108");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="PIE_108")
	public Action mockIntegrationEventProgressPIE() throws Exception {
		mockTracker.incrementCallCount("PIE_108");
		return new StandardAction(Action.Type.mock);
	}
}
