package com.workday.custom.aunit.int0025.utilities.ssk129;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent129TestCase extends UtilitiesTestCase {
	public static final String PROP_PARAMETER_IN_LOG_NAME = "inLogName";
	public static final String PROP_PARAMETER_IN_BEAN = "inFlexLogBean";
	public static final String VAR_CONFIG = "int0025FlexLogConfiguration147";
	
	public static final String PROP_UPSTREAM_PARAMETER_IN_LEVEL = "inLogLevel";
	public static final String PROP_UPSTREAM_PARAMETER_IN_MESSAGE = "inLogMessage";
	public static final String PROP_UPSTREAM_PARAMETER_IN_DETAIL = "inLogDetail";
	public static final String PROP_UPSTREAM_PARAMETER_IN_A = "inUniqueA";
	public static final String PROP_UPSTREAM_PARAMETER_IN_B = "inUniqueB";
	public static final String PROP_UPSTREAM_PARAMETER_IN_C = "inUniqueC";
	public static final String PROP_UPSTREAM_PARAMETER_IN_D = "inUniqueD";
	
	public static final String VALUE_LOG1 = "log1";
	public static final String VALUE_LOG2 = "log2";
	
	public static final String XML_CONFIGURATION = "test/int0025/int0025129/SSK129_Configuration_WriteTests.xml";
	
	public static final String VAR_GLOBAL_INPUT_DATA = "globalInputData";
	public static final String VAR_GLOBAL_OUTPUT_DATA = "globalOutputData";
	
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inDataLocation";
	public static final String PROP_PARAMETER_IN_XSLT = "inPathToXsltFile";
	public static final String PROP_PARAMETER_IN_RETURN_LOCATION = "inReturnOutputLocation";
	public static final String PROP_PARAMETER_IN_MIN_LEVEL = "inMinLogLevel";
	public static final String PROP_PARAMETER_IN_LOG_TARGET = "inLogTarget";
	public static final String PROP_PARAMETER_IN_ABORT_ON_ERROR = "inIsAbortOnError";
	
	public static final String MOCK_XML_DATA_7 = "test/int0025/int0025129/SSK129_XML_7Records.xml";
	public static final String MOCK_XML_DATA_1 = "test/int0025/int0025129/SSK129_XML_1Record.xml";
	public static final String MOCK_XML_BAD_DATA = "test/int0025/int0025129/SSK129_XML_ResultWithBadData.xml";
	public static final String MOCK_XML_BAD_DATA_RESULT = "test/int0025/int0025129/SSK129_XML_BadDataTransformResult.xml";
	
	public static final String MOCK_XSLT_GOOD_NOLOG = "test/int0025/int0025129/SSK129_StreamingCopyTemplate.xsl";
	public static final String MOCK_XSLT_GOOD_LOG = "test/int0025/int0025129/SSK129_CopyTemplateWithLog.xsl";
	public static final String MOCK_XSLT_BAD = "test/int0025/int0025129/SSK129_UnitTest_MalformedTransform.xsl";
	public static final String MOCK_XSLT_TRY_CATCH_JAVA = "test/int0025/int0025129/SSK129_TransformWithJavaMessages.xsl";
	public static final String MOCK_XSLT_TRY_CATCH_XSLT = "test/int0025/int0025129/SSK129_TransformWithXsltMessages.xsl";

	public static final String MOCK_XSLT_FLEX_LOG_JAVA = "test/int0025/int0025129/SSK129_TransformWithJavaFlexLog.xsl";
	public static final String MOCK_XSLT_FLEX_LOG_XSLT = "test/int0025/int0025129/SSK129_TransformWithXsltFlexLog.xsl";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);

//		setVariable(VAR_CONFIG, XML_CONFIGURATION, CONTENT_TYPE_TEXT_XML);
//		bean = new FlexLogBean();
//
//		try {
//			bean.configureNewLog(ctx);
//			ctx.setProperty(MediationConstants.PROPERTY_SSK_FLEX_LOG, bean);
//		} catch (Throwable t) {
//			log.critical("Setup Exception!", t);
//			throw new Exception(t);
//		}
	}
	
//	@Override
//	protected void tearDown() throws Exception {
//		super.tearDown();
//		
//		ctx.removeProperty(PROP_PARAMETER_IN_BEAN);
//		bean = null;
//	}
	
	@Override
	protected String getFlexLogConfigurationFile() {
		return XML_CONFIGURATION;
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformNoLogMessagesFromMessageToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_NOLOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML);
	}

	@UnitTest(startComponent="XsltPlus")
	public void testTransformNoLogMessagesFromMessageToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_NOLOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VAR_GLOBAL_OUTPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformNoLogMessagesFromVariableToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_GLOBAL_INPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_NOLOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VAR_GLOBAL_OUTPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformNoLogMessagesFromVariableToVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_GLOBAL_INPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_NOLOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, VAR_GLOBAL_OUTPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformWithMessagesFromMessageToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_GOOD_LOG));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_DATA_1, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformHardErrorFromMessageToMessage() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_BAD));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_DATA_1, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformWithErrorTryCatchFromMessageToMessageJavaLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_TRY_CATCH_JAVA));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_BAD_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformWithErrorTryCatchFromMessageToMessageXsltLog() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_TRY_CATCH_XSLT));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		
		setMessagePart(0, MOCK_XML_BAD_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="XsltPlus")
	public void testTransformWithFlexLogJavaFromMessageToMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_XSLT, loadXsltToVariable(TEMPORARY_IN_MEMORY_XSLT, MOCK_XSLT_FLEX_LOG_JAVA));
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_MIN_LEVEL, "info");
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "flexlog");
		
		setMessagePart(0, MOCK_XML_BAD_DATA, CONTENT_TYPE_TEXT_XML);
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
		if (!testsExpectingHandledExceptions.contains(getName())) {
			if ("testTransformWithMessagesFromMessageToMessage".equalsIgnoreCase(getName())) {
				assertPrimaryCloudLogEntryHTML("info", "Summary Message", "Message details", null, null, null, null, null);
			} else if ("testTransformWithMessagesFromMessageToMessage".equalsIgnoreCase(getName())) {
				assertPrimaryCloudLogEntryHTML("warn", "Error occurred during date calculation", "Invalid date \"`1959-05-22-07:00\" (Non-numeric year component)", "21002", "mctx:vars/localInMemoryXslt", "err:FORG0001", "", "`1959-05-22-07:00");
			} else if (getName().startsWith("testTransformWithFlexLog")) {
				assertFlexLogEntry("log2", "warn,\"Invalid date \"\"`1959-05-22-07:00\"\" (Non-numeric year component)\",err:FORG0001,21002,mctx:vars/localInMemoryXslt");
			}
		}
	}

	@Override
	protected void handledExceptionVerification(Throwable t) throws Exception {
		super.handledExceptionVerification(t);
		
		if ("testTransformHardErrorFromMessageToMessage".equalsIgnoreCase(getName())) {
			
		}
	}
	
	@AssertAfter(id="ApplyTransform_129")
	public void verifyTransform() throws Exception {
		if ("testTransformNoLogMessagesFromMessageToMessage".equalsIgnoreCase(getName()) ||
				"testTransformNoLogMessagesFromVariableToMessage".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_DATA_7),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML));
		} else if ("testTransformNoLogMessagesFromMessageToVariable".equalsIgnoreCase(getName()) ||
				"testTransformNoLogMessagesFromVariableToVariable".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_DATA_7),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_7, CONTENT_TYPE_TEXT_XML));
		} else if ("testTransformWithMessagesFromMessageToMessage".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_DATA_1),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_DATA_1, CONTENT_TYPE_TEXT_XML));
		} else if ("testTransformWithErrorTryCatchFromMessageToMessageJavaLog".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_BAD_DATA_RESULT),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_BAD_DATA_RESULT, CONTENT_TYPE_TEXT_XML));
		} else if ("testTransformWithErrorTryCatchFromMessageToMessageXsltLog".equalsIgnoreCase(getName())) {
			assertTrue(String.format("Application of the XSLT copy template did not match [%1$s]", MOCK_XML_BAD_DATA_RESULT),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_BAD_DATA_RESULT, CONTENT_TYPE_TEXT_XML));
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
