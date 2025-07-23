package com.workday.custom.aunit.int0025.utilities.ssk148;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang3.StringUtils;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.SSKUtils;
import com.workday.custom.int0025.ssk148.BOMUtils;
import com.workday.custom.int0025.ssk148.IOUtils;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent148TestCase extends UtilitiesTestCase {

	public static final String VAR_GLOBAL_INPUT_DATA = "globalInputData";
	public static final String VAR_GLOBAL_OUTPUT_DATA = "globalOutputData";
	
	public static final String PROP_PARAMETER_IN_DATA_SOURCE = "inDataSource";
	public static final String PROP_PARAMETER_IN_CONTENT_TYPE = "inContentType";
	public static final String PROP_PARAMETER_IN_IS_ADD = "inIsAddBOM";
	public static final String PROP_PARAMETER_IN_IS_REMOVE = "inIsRemoveBOM";
	public static final String PROP_PARAMETER_IN_IS_CHILD_THREAD = "inIsChildThreadContext";

	public static final String PROP_LOCAL_PARALLEL_LOG = "localParallelLog143";

	public static final String MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8 = "test/int0025/int0025148/XML_ExpectedResults_UTF8.xml";
	public static final String MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF16 = "test/int0025/int0025148/XML_ExpectedResults_UTF16.xml";
	public static final String MOCK_XML_UTF8 = "test/int0025/int0025148/XML_UTF8.xml";
	public static final String MOCK_XML_UTF8_BOM = "test/int0025/int0025148/XML_UTF8_BOM.xml";
	public static final String MOCK_XML_UTF16_BE = "test/int0025/int0025148/XML_UTF16_BE.xml";
	public static final String MOCK_XML_UTF16_BE_BOM = "test/int0025/int0025148/XML_UTF16_BE_BOM.xml";
	public static final String MOCK_XML_UTF16_LE = "test/int0025/int0025148/XML_UTF16_LE.xml";
	public static final String MOCK_XML_UTF16_LE_BOM = "test/int0025/int0025148/XML_UTF16_LE_BOM.xml";
	
	public static final String MOCK_TXT_EXPECTED_RESULTS_REMOVE = "test/int0025/int0025148/TXT_ExpectedResults.txt";
	public static final String MOCK_TXT_UTF8 = "test/int0025/int0025148/TXT_UTF8.txt";
	public static final String MOCK_TXT_UTF8_BOM = "test/int0025/int0025148/TXT_UTF8_BOM.txt";
	public static final String MOCK_TXT_UTF16_BE = "test/int0025/int0025148/TXT_UTF16_BE.txt";
	public static final String MOCK_TXT_UTF16_BE_BOM = "test/int0025/int0025148/TXT_UTF16_BE_BOM.txt";
	public static final String MOCK_TXT_UTF16_LE = "test/int0025/int0025148/TXT_UTF16_LE.txt";
	public static final String MOCK_TXT_UTF16_LE_BOM = "test/int0025/int0025148/TXT_UTF16_LE_BOM.txt";

	String expectedResultsFile = null;
	String expectedMimeType = null;
	
	List<String> testsToVerifyBOMRemoval;
	List<String> testsToVerifyBOMAddition;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		if (getName().endsWith("ChildThread")) {
			@SuppressWarnings("rawtypes")
			ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
			ctx.setProperty(PROP_LOCAL_PARALLEL_LOG, queue);
		}
		
		testsToVerifyBOMRemoval = new ArrayList<String>();
		testsToVerifyBOMAddition = new ArrayList<String>();
		
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_GLOBAL_OUTPUT_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, false);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testXmlUtf8RemoveParentThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
		
		setMessagePart(0, MOCK_XML_UTF8, CONTENT_TYPE_TEXT_XML);
		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8;
		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF8;
	}
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testXmlUtf8BOMRemoveParentThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
		
		setMessagePart(0, MOCK_XML_UTF8_BOM, CONTENT_TYPE_TEXT_XML);
		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8;
		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF8;
		testsToVerifyBOMRemoval.add(getName());
	}
	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16BigEndianRemoveParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_UTF16_BE, CONTENT_TYPE_TEXT_XML_UTF16);
//		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF16;
//		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF16;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16BigEndianRemoveBOMParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_UTF16_BE_BOM, CONTENT_TYPE_TEXT_XML_UTF16);
//		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF16;
//		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF16;
//		testsToVerifyBOMRemoval.add(getName());
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16LittleEndianRemoveParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setMessagePart(0, MOCK_XML_UTF16_LE, CONTENT_TYPE_TEXT_XML_UTF16);
//		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF16;
//		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF16;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16LittleEndianBOMRemoveParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setMessagePart(0, MOCK_XML_UTF16_LE_BOM, CONTENT_TYPE_TEXT_XML_UTF16);
//		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF16;
//		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF16;
//		testsToVerifyBOMRemoval.add(getName());
//	}
	
////////////////////////////////////////////////////////////
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testXmlUtf8RemoveChildThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setMessagePart(0, MOCK_XML_UTF8, CONTENT_TYPE_TEXT_XML);
		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8;
		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF8;
	}
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testXmlUtf8BOMRemoveChildThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setMessagePart(0, MOCK_XML_UTF8_BOM, CONTENT_TYPE_TEXT_XML);
		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8;
		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF8;
	}
	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16BigEndianRemoveChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_UTF16_BE, CONTENT_TYPE_TEXT_XML);
//		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16BigEndianRemoveBOMChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_UTF16_BE_BOM, CONTENT_TYPE_TEXT_XML);
//		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16LittleEndianRemoveChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setMessagePart(0, MOCK_XML_UTF16_LE, CONTENT_TYPE_TEXT_XML);
//		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16LittleEndianBOMRemoveChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setMessagePart(0, MOCK_XML_UTF16_LE_BOM, CONTENT_TYPE_TEXT_XML);
//		expectedResultsFile = MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8;
//	}
	
////////////////////////////////////////////////////////
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testXmlUtf8AddParentThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
		
		setMessagePart(0, MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8, CONTENT_TYPE_TEXT_XML);
		expectedResultsFile = MOCK_XML_UTF8_BOM;
		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF8;
		testsToVerifyBOMAddition.add(getName());
	}
	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16BigEndianAddParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8, CONTENT_TYPE_TEXT_XML);
//		expectedResultsFile = MOCK_XML_UTF16_BE_BOM;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16LittleEndianAddParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setMessagePart(0, MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8, CONTENT_TYPE_TEXT_XML);
//		expectedResultsFile = MOCK_XML_UTF16_LE_BOM;
//	}
	
////////////////////////////////////////////////////////////
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testXmlUtf8AddChildThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setMessagePart(0, MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8, CONTENT_TYPE_TEXT_XML);
		expectedResultsFile = MOCK_XML_UTF8_BOM;
		expectedMimeType = CONTENT_TYPE_TEXT_XML_UTF8;
		testsToVerifyBOMAddition.add(getName());
	}
	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16BigEndianAddChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8, CONTENT_TYPE_TEXT_XML);
//		expectedResultsFile = MOCK_XML_UTF16_BE_BOM;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testXmlUtf16LittleEndianAddChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setMessagePart(0, MOCK_XML_EXPECTED_RESULTS_REMOVE_UTF8, CONTENT_TYPE_TEXT_XML);
//		expectedResultsFile = MOCK_XML_UTF16_LE_BOM;
//	}
	
////////////////////////////////////////////////////////
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testTextUtf8RemoveParentThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_PLAIN_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
		
		setMessagePart(0, MOCK_TXT_UTF8, CONTENT_TYPE_TEXT_PLAIN);
		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF8;
	}
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testTextUtf8BOMRemoveParentThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_PLAIN_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
		
		setMessagePart(0, MOCK_TXT_UTF8_BOM, CONTENT_TYPE_TEXT_PLAIN_UTF8);
		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF8;
		testsToVerifyBOMRemoval.add(getName());
	}
	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16BigEndianRemoveParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_PLAIN_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_TXT_UTF16_BE, CONTENT_TYPE_TEXT_PLAIN_UTF16);
//		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
//		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF16;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16BigEndianRemoveBOMParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_PLAIN_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_TXT_UTF16_BE_BOM, CONTENT_TYPE_TEXT_PLAIN_UTF16);
//		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
//		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF16;
//		testsToVerifyBOMRemoval.add(getName());
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16LittleEndianRemoveParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_PLAIN_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setMessagePart(0, MOCK_TXT_UTF16_LE, CONTENT_TYPE_TEXT_PLAIN_UTF16);
//		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
//		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF16;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16LittleEndianBOMRemoveParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_PLAIN_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setMessagePart(0, MOCK_TXT_UTF16_LE_BOM, CONTENT_TYPE_TEXT_PLAIN_UTF16);
//		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
//		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF16;
//		testsToVerifyBOMRemoval.add(getName());
//	}
	
////////////////////////////////////////////////////////////
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testTextUtf8RemoveChildThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setMessagePart(0, MOCK_TXT_UTF8, CONTENT_TYPE_TEXT_PLAIN);
		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF8;
	}
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testTextUtf8BOMRemoveChildThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setMessagePart(0, MOCK_TXT_UTF8_BOM, CONTENT_TYPE_TEXT_PLAIN);
		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF8;
	}
	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16BigEndianRemoveChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_TXT_UTF16_BE, CONTENT_TYPE_TEXT_PLAIN);
//		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16BigEndianRemoveBOMChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_TXT_UTF16_BE_BOM, CONTENT_TYPE_TEXT_PLAIN);
//		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16LittleEndianRemoveChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setMessagePart(0, MOCK_TXT_UTF16_LE, CONTENT_TYPE_TEXT_PLAIN);
//		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16LittleEndianBOMRemoveChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setMessagePart(0, MOCK_TXT_UTF16_LE_BOM, CONTENT_TYPE_TEXT_PLAIN);
//		expectedResultsFile = MOCK_TXT_EXPECTED_RESULTS_REMOVE;
//	}
	
////////////////////////////////////////////////////////
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testTextUtf8AddParentThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
		
		setMessagePart(0, MOCK_TXT_EXPECTED_RESULTS_REMOVE, CONTENT_TYPE_TEXT_PLAIN);
		expectedResultsFile = MOCK_TXT_UTF8_BOM;
		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF8;
		testsToVerifyBOMAddition.add(getName());
	}
	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16BigEndianAddParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_TXT_EXPECTED_RESULTS_REMOVE, CONTENT_TYPE_TEXT_PLAIN);
//		expectedResultsFile = MOCK_TXT_UTF16_BE_BOM;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16LittleEndianAddParentThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, false);
//		
//		setMessagePart(0, MOCK_TXT_EXPECTED_RESULTS_REMOVE, CONTENT_TYPE_TEXT_PLAIN);
//		expectedResultsFile = MOCK_TXT_UTF16_LE_BOM;
//	}
	
////////////////////////////////////////////////////////////
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testTextUtf8AddChildThread() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setMessagePart(0, MOCK_TXT_EXPECTED_RESULTS_REMOVE, CONTENT_TYPE_TEXT_PLAIN);
		expectedResultsFile = MOCK_TXT_UTF8_BOM;
		expectedMimeType = CONTENT_TYPE_TEXT_PLAIN_UTF8;
		testsToVerifyBOMAddition.add(getName());
	}
	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16BigEndianAddChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, VAR_GLOBAL_INPUT_DATA);
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setVariable(VAR_GLOBAL_INPUT_DATA, MOCK_TXT_EXPECTED_RESULTS_REMOVE, CONTENT_TYPE_TEXT_PLAIN);
//		expectedResultsFile = MOCK_TXT_UTF16_BE_BOM;
//	}
//	
//	@UnitTest(startComponent="HandleByteOrderMark")
//	public void testTextUtf16LittleEndianAddChildThread() throws Exception {
//		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
//		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF16);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
//		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
//		
//		setMessagePart(0, MOCK_TXT_EXPECTED_RESULTS_REMOVE, CONTENT_TYPE_TEXT_PLAIN);
//		expectedResultsFile = MOCK_TXT_UTF16_LE_BOM;
//	}
	
////////////////////////////////////////////////////////
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testLocalInValidationNoAddNoRemove() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, false);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, false);
		
		setMessagePart(0, MOCK_XML_UTF8, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="HandleByteOrderMark")
	public void testLocalInValidationAddRemove() throws Exception {
		expectUnhandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_DATA_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_CONTENT_TYPE, CONTENT_TYPE_TEXT_XML_UTF8);
		ctx.setProperty(PROP_PARAMETER_IN_IS_ADD, true);
		ctx.setProperty(PROP_PARAMETER_IN_IS_REMOVE, true);
		
		setMessagePart(0, MOCK_XML_UTF8, CONTENT_TYPE_TEXT_XML);
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
		if (!testsExpectingHandledExceptions.contains(getName()) &&
				!testsExpectingUnhandledExceptions.contains(getName()) &&
				!testsToVerifyBOMAddition.contains(getName())) {
			assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_GLOBAL_OUTPUT_DATA), 
					compareAgainstVariable(VAR_GLOBAL_OUTPUT_DATA, expectedMimeType, expectedResultsFile, expectedMimeType));
		}
	}

	@Override
	protected void handledExceptionVerification(Throwable t) throws Exception {
		super.handledExceptionVerification(t);
	}
	
	@AssertAfter(id="Initialize_148")
	public void verifyInitialization() throws Exception {

	}

	@AssertAfter(id="PrepareXML_148")
	public void verifyXML() throws Exception {
		if (testsToVerifyBOMRemoval.contains(getName())) {
			verifyRemovalOfBOM();
		} else if (testsToVerifyBOMAddition.contains(getName())) {
			verifyAdditionOfBOM();
		}
	}

	@AssertAfter(id="PrepareNonXML_148")
	public void verifyNonXML() throws Exception {
		if (testsToVerifyBOMRemoval.contains(getName())) {
			verifyRemovalOfBOM();
		} else if (testsToVerifyBOMAddition.contains(getName())) {
			verifyAdditionOfBOM();
		}
	}
	
	private void verifyAdditionOfBOM() throws Exception {
		String cleanCharacterEncoding = StringUtils.substringAfter(expectedMimeType, "charset=").trim().toUpperCase();
		byte[] bom = BOMUtils.getBOMForEncoding(cleanCharacterEncoding);
		byte[] data = new byte[bom.length];

		InputStream input = SSKUtils.getMessageInputStream(ctx);
		IOUtils.read(input, data);
		
		for (int i = 0; i < bom.length; i++) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "BOM"), bom[i], data[i]);
		}
	}

	private void verifyRemovalOfBOM() throws Exception {
		String cleanCharacterEncoding = StringUtils.substringAfter(expectedMimeType, "charset=").trim().toUpperCase();
		byte[] bom = BOMUtils.getBOMForEncoding(cleanCharacterEncoding);
		byte[] data = new byte[bom.length];

		InputStream input = SSKUtils.getMessageInputStream(ctx);
		IOUtils.read(input, data);
		
		for (int i = 0; i < bom.length; i++) {
			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "BOM"), bom[i] == data[i]);
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
