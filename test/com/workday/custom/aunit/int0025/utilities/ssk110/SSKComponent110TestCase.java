package com.workday.custom.aunit.int0025.utilities.ssk110;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent110TestCase extends UtilitiesTestCase {
	
	public static final String VAR_GLOBAL_DA_METADATA = "globalDAMetadata";
	
	public static final String VAR_LOCAL_XSLT = "localXslt110";
	public static final String VAR_LOCAL_COUNT = "localRecordCount110";

	public static final String PROP_PARAMETER_IN_NO_FILE_THROWS_EXCEPTION = "inNoFileThrowsException";
	public static final String PROP_PARAMETER_IN_MULTIPLE_FILES_THROWS_EXCEPTION = "inMultipleFilesThrowsException";
	public static final String PROP_PARAMETER_IN_EXACT_COUNT_EXPECTED = "inExactFileCountExpected";
	public static final String PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG = "inRetrievalDocTag";
	public static final String PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG_TYPE = "inRetrievalDocTagType";

	public static final String PROP_PARAMETER_OUT_FILE_COUNT = "outFileCount110";
	
	public static final String PROP_LOCAL_TAG_FILTER = "localTagFilter";

	public static final String VALUE_DOCUMENT_TAG_TYPE_ANY = "Any";
	public static final String VALUE_DOCUMENT_TAG_TYPE_ALL = "All";
	public static final Integer VALUE_NO_TAG_EXPECTED_COUNT = 4;
	public static final Integer VALUE_TAG_EXPECTED_COUNT = 3;
	public static final String VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED = "cc-retrieve";
	public static final String VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID = "50684860-7622-412a-3014-4f092f012129";
	public static final String VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID = "7277537f-4505-4b38-243c-242a3f21220d";
	public static final String VALUE_FILE_VENDOR3_XML_EVENT_DOCUMENT_ENTRYID = "4d5b7233-586b-444b-3d4c-215811332815";
	public static final String VALUE_TAG_REF_ID_RETRIEVED = "b2f7dfe1c41a41c89a358318b28d6e1c";
	public static final String VALUE_TAG_REF_ID_SSK110 = VALUE_WID_20;
	public static final String VALUE_TAG_REF_ID_OTHER_TAG = VALUE_WID_21;

	public static final String MOCK_FILE_SSK110_VENDOR1_XML = "test/int0025/int0025110/vendor-file1.xml";
	public static final String MOCK_FILE_SSK110_VENDOR2_XML = "test/int0025/int0025110/vendor-file2.xml";
	public static final String MOCK_FILE_SSK110_VENDOR3_XML = "test/int0025/int0025110/vendor-file3.xml";
	public static final String MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_MULTI_TAG = "test/int0025/int0025110/SSK110_SOAP_Get_Event_Documents_Response_MultiTag.xml";
	public static final String MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED = "test/int0025/int0025110/SSK110_SOAP_Get_Event_Documents_Response3.xml";
	public static final String MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_1RETRIEVED = "test/int0025/int0025110/SSK110_SOAP_Get_Event_Documents_Response1.xml";
	public static final String MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_0RETRIEVED = "test/int0025/int0025110/SSK110_SOAP_Get_Event_Documents_Response0.xml";
	public static final String MOCK_XML_SSK110_EXPECTED_RESULTS_NO_FILTER = "test/int0025/int0025110/SSK110_XML_globalDAMetadata_NoFilter.xml";
	public static final String MOCK_XML_SSK110_EXPECTED_RESULTS_MULTI_TAG = "test/int0025/int0025110/SSK110_XML_globalDAMetadata_MultiTag.xml";
	public static final String MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES = "test/int0025/int0025110/SSK110_XML_globalDAMetadata_3Files.xml";
	public static final String MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES = "test/int0025/int0025110/SSK110_XML_globalDAMetadata_1Files.xml";
	public static final String MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES = "test/int0025/int0025110/SSK110_XML_globalDAMetadata_0Files.xml";
	public static final String MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES_TAGS = "test/int0025/int0025110/SSK110_XML_globalDAMetadata_3FilesMultiTag.xml";
	public static final String MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES_TAGS = "test/int0025/int0025110/SSK110_XML_globalDAMetadata_1FilesMultiTag.xml";

	List<String> tags;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		super.setupGlobalInitialization();
		
		tags = new ArrayList<String>();
	}
	
	@Override
	protected String getFileForSetIntegrationEventDocuments() {
		String returnValue = super.getFileForSetIntegrationEventDocuments();
		
		switch(getName()) {
			case "testRetrievedTagAllNoFileError" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_0RETRIEVED;
				break;
			
			case "testRetrievedTagAllNoFileOk" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_0RETRIEVED;
				break;
			
			case "testRetrievedTagAllNoFileOkReturnOnMessage" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_0RETRIEVED;
				break;
			
			case "testRetrievedTagAllMultipleFilesError" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAllMultipleFilesOk" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAllMultipleFilesOkReturnOnMessage" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountError" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountOk" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountOkReturnOnMessage" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAllSingleFile" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_1RETRIEVED;
				break;
			
			case "testRetrievedTagAllSingleFileReturnOnMessage" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_1RETRIEVED;
				break;
			
			case "testRetrievedTagAnyNoFileError" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_0RETRIEVED;
				break;
			
			case "testRetrievedTagAnyNoFileOk" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_0RETRIEVED;
				break;
			
			case "testRetrievedTagAnyNoFileOkReturnOnMessage" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_0RETRIEVED;
				break;
			
			case "testRetrievedTagAnyMultipleFilesError" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountError" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountOk" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountOkReturnOnMessage" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAnyMultipleFilesOk" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAnyMultipleFilesOkReturnOnMessage" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_3RETRIEVED;
				break;
			
			case "testRetrievedTagAnySingleFile" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_1RETRIEVED;
				break;
			
			case "testRetrievedTagAnySingleFileReturnOnMessage" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_1RETRIEVED;
				break;
				
			case "testNoTagsReturnVariable" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_MULTI_TAG;
				break;

			case "testMultipleTagsAnyReturnVariable" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_MULTI_TAG;
				break;
				
			case "testMultipleTagsAllReturnVariable" :
				returnValue = MOCK_INITIALIZATION_SSK110_EVENT_DOCUMENTS_MULTI_TAG;
				break;
			
			default :
				break;
		}
		
		return returnValue;
	}
	
	private void mockOneFile() throws Exception {
		registerBlobitoryResourceToInitialize(MOCK_FILE_SSK110_VENDOR1_XML, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
	}

	private void mockThreeFiles() throws Exception {
		registerBlobitoryResourceToInitialize(MOCK_FILE_SSK110_VENDOR1_XML, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR1_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
		registerBlobitoryResourceToInitialize(MOCK_FILE_SSK110_VENDOR2_XML, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR2_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
		registerBlobitoryResourceToInitialize(MOCK_FILE_SSK110_VENDOR3_XML, VALUE_EVENT_DOCUMENT_COLLECTION_RETRIEVED, VALUE_FILE_VENDOR3_XML_EVENT_DOCUMENT_ENTRYID, CONTENT_TYPE_TEXT_XML);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllNoFileError() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.TRUE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllNoFileOk() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.FALSE, Boolean.FALSE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllMultipleFilesError() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.TRUE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllMultipleFilesOk() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.FALSE, Boolean.FALSE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllMultipleFilesExpectedCountOk() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, VALUE_TAG_EXPECTED_COUNT, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllMultipleFilesExpectedCountError() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, VALUE_NO_TAG_EXPECTED_COUNT, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllSingleFile() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.TRUE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
		mockOneFile();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyNoFileError() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		
		initializeInParameters(Boolean.TRUE, Boolean.TRUE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ANY);
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyNoFileOk() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		
		initializeInParameters(Boolean.FALSE, Boolean.FALSE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ANY);
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyMultipleFilesError() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		
		initializeInParameters(Boolean.TRUE, Boolean.TRUE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ANY);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyMultipleFilesOk() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		
		initializeInParameters(Boolean.FALSE, Boolean.FALSE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ANY);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyMultipleFilesExpectedCountOk() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, VALUE_TAG_EXPECTED_COUNT, tags, VALUE_DOCUMENT_TAG_TYPE_ANY);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyMultipleFilesExpectedCountError() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, VALUE_NO_TAG_EXPECTED_COUNT, tags, VALUE_DOCUMENT_TAG_TYPE_ANY);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnySingleFile() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);		
		initializeInParameters(Boolean.TRUE, Boolean.TRUE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ANY);
		mockOneFile();
	}
	
	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllNoFileOkReturnOnMessage() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.FALSE, Boolean.FALSE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL, "message");
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllMultipleFilesOkReturnOnMessage() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.FALSE, Boolean.FALSE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL, "message");
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllMultipleFilesExpectedCountOkReturnOnMessage() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, VALUE_NO_TAG_EXPECTED_COUNT, tags, VALUE_DOCUMENT_TAG_TYPE_ALL, "message");
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAllSingleFileReturnOnMessage() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.TRUE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL, "message");
		mockOneFile();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyNoFileOkReturnOnMessage() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.FALSE, Boolean.FALSE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL, "message");
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyMultipleFilesOkReturnOnMessage() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.FALSE, Boolean.FALSE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL, "message");
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnyMultipleFilesExpectedCountOkReturnOnMessage() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, VALUE_TAG_EXPECTED_COUNT, tags, VALUE_DOCUMENT_TAG_TYPE_ALL, "message");
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testRetrievedTagAnySingleFileReturnOnMessage() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.TRUE, VALUE_NEGATIVE_ONE, tags, VALUE_DOCUMENT_TAG_TYPE_ALL, "message");
		mockOneFile();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testNoTagsReturnVariable() throws Exception {
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, VALUE_NEGATIVE_ONE, null, null);
		mockThreeFiles();
	}

	@UnitTest(startComponent="GetDocumentList")
	public void testMultipleTagsAnyReturnVariable() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, 3, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
		mockThreeFiles();
	}
	
	@UnitTest(startComponent="GetDocumentList")
	public void testMultipleTagsAllReturnVariable() throws Exception {
		tags.add(VALUE_WID_TAG_RETRIEVED);
		tags.add(VALUE_TAG_REF_ID_SSK110);
		tags.add(VALUE_TAG_REF_ID_OTHER_TAG);
		initializeInParameters(Boolean.TRUE, Boolean.FALSE, 1, tags, VALUE_DOCUMENT_TAG_TYPE_ALL);
		mockThreeFiles();
	}
	
	private void initializeInParameters(Boolean noFileValue, Boolean multipleFileValue, Integer expectedCount, List<String> tags, String tagType) throws Exception {
		initializeInParameters(noFileValue, multipleFileValue, expectedCount, tags, tagType, VAR_GLOBAL_DA_METADATA);
	}

	private void initializeInParameters(Boolean noFileValue, Boolean multipleFileValue, Integer expectedCount, List<String> tags, String tagType, String returnResults) throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_NO_FILE_THROWS_EXCEPTION, noFileValue);
		ctx.setProperty(PROP_PARAMETER_IN_MULTIPLE_FILES_THROWS_EXCEPTION, multipleFileValue);
		ctx.setProperty(PROP_PARAMETER_IN_EXACT_COUNT_EXPECTED, expectedCount);
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, returnResults);

		if (CollectionUtils.isNotEmpty(tags)) {
			ctx.setProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG, tags);
			ctx.setProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG_TYPE, tagType);
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected void evaluateCreateLogEntryParameters() throws Exception {
		super.evaluateCreateLogEntryParameters();
		
		switch(String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_EXTRA_LOCAL_IN))) {
			case "RouteOnCount_110(NoFileError)" :
				verifyLogNoFilesError();
				break;
				
			case "RouteOnCount_110(NoFileProceed)" :
				verifyLogNoFilesOk();
				break;
				
			case "RouteOnCount_110(MultipleFilesError)" :
				verifyLogMultipleFilesError();
				break;
				
			case "RouteOnCount_110(MultipleFilesCountError)" :
				verifyLogMultipleFilesExpectedCountError();
				break;
		
			case "RouteOnCount_110(MultipleFilesProceed)" :
				verifyLogMultipleFilesOk();
				break;
				
			case "RouteOnCount_110(SingleFileProceed)" :
				verifyLogSingleFile();
				break;
				
			default :
				break;
		}
		
	}
	
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		Iterator<?> propertyNameIterator = ctx.getPropertyNames();

		while (propertyNameIterator.hasNext()) {
			String propertyName = String.valueOf(propertyNameIterator.next());
			
			assertFalse(String.format(MESSAGE_PROPERTY_NOT_REMOVED, PROP_LOCAL_TAG_FILTER), PROP_LOCAL_TAG_FILTER.equalsIgnoreCase(propertyName));
			
			verifyGlobalPropertyNotModified(propertyName, null);
		}
		
		switch(getName()) {
			case "testRetrievedTagAllNoFileError" :
				break;
				
			case "testRetrievedTagAllMultipleFilesError" :
				break;
				
			case "testRetrievedTagAllMultipleFilesExpectedCountError" :
				break;
				
			case "testRetrievedTagAnyNoFileError" :
				break;
				
			case "testRetrievedTagAnyMultipleFilesError" :
				break;
				
			case "testRetrievedTagAnyMultipleFilesExpectedCountError" :
				break;
				
			default :
				if (((String)ctx.getProperty(PROP_PARAMETER_IN_RETURN_RESULTS)).equalsIgnoreCase("message")) {
					assertFalse(String.format(MESSAGE_UNEXPECTED_VARIABLE_PRESENT, VAR_GLOBAL_DA_METADATA), ctx.getVariables().containsKey(VAR_GLOBAL_DA_METADATA));
				} else {
					assertTrue(String.format(MESSAGE_VARIABLE_NOT_ADDED, VAR_GLOBAL_DA_METADATA), ctx.getVariables().containsKey(VAR_GLOBAL_DA_METADATA));
				}
				break;
		}
	}

	@AssertAfter(id="DocumentAccessor_110", step="InitValues")
	public void verifyTagFilter() throws Exception {
		String tagType = (String)ctx.getProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG_TYPE);
		String expectedValue = "";
		
		if ((tags.size() == 1) && VALUE_DOCUMENT_TAG_TYPE_ALL.equalsIgnoreCase(tagType)) {
			expectedValue = "[wd:Document_Tag_Reference/wd:ID[@wd:type='WID'] = '" + VALUE_TAG_REF_ID_RETRIEVED + "']";
		} else if ((tags.size() == 1) && VALUE_DOCUMENT_TAG_TYPE_ANY.equalsIgnoreCase(tagType)) {
			expectedValue = "[wd:Document_Tag_Reference/wd:ID[@wd:type='WID'] = ('" + VALUE_TAG_REF_ID_RETRIEVED + "')]";
		} else if ((tags.size() > 1) && VALUE_DOCUMENT_TAG_TYPE_ALL.equalsIgnoreCase(tagType)) {
			expectedValue = "[wd:Document_Tag_Reference/wd:ID[@wd:type='WID'] = '" + VALUE_TAG_REF_ID_RETRIEVED + "'][wd:Document_Tag_Reference/wd:ID[@wd:type='WID'] = '" + VALUE_TAG_REF_ID_SSK110 + "'][wd:Document_Tag_Reference/wd:ID[@wd:type='WID'] = '" + VALUE_TAG_REF_ID_OTHER_TAG + "']";
		} else if ((tags.size() > 1) && VALUE_DOCUMENT_TAG_TYPE_ANY.equalsIgnoreCase(tagType)) {
			expectedValue = "[wd:Document_Tag_Reference/wd:ID[@wd:type='WID'] = ('" + VALUE_TAG_REF_ID_RETRIEVED + "','" + VALUE_TAG_REF_ID_SSK110 + "','" + VALUE_TAG_REF_ID_OTHER_TAG + "')]";
		}
		
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_TAG_FILTER), expectedValue, ctx.getProperty(PROP_LOCAL_TAG_FILTER));
	}
	
	@AssertAfter(id="DocumentAccessor_110", step="FilterEventDocuments")
	public void verifyDocumentFilterResult() throws Exception {
		switch(getName()) {
			case "testRetrievedTagAllNoFileError" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllNoFileOk" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllMultipleFilesError" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountError" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountOk" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllMultipleFilesOk" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllSingleFile" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyNoFileError" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyNoFileOk" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyMultipleFilesError" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountError" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountOk" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyMultipleFilesOk" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnySingleFile" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountOkReturnOnMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllMultipleFilesOkReturnOnMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllSingleFileReturnOnMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAllNoFileOkReturnOnMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyNoFileOkReturnOnMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountOkReturnOnMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnyMultipleFilesOkReturnOnMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testRetrievedTagAnySingleFileReturnOnMessage" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES, CONTENT_TYPE_TEXT_XML));
				break;
			
			case "testNoTagsReturnVariable" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_NO_FILTER),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_NO_FILTER, CONTENT_TYPE_TEXT_XML));
				break;

			case "testMultipleTagsAnyReturnVariable" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES_TAGS),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES_TAGS, CONTENT_TYPE_TEXT_XML));
				break;
				
			case "testMultipleTagsAllReturnVariable" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_MULTI_TAG),
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_MULTI_TAG, CONTENT_TYPE_TEXT_XML));
				break;

			default :
				fail("Validation was not configured for TestCase ["+ getName() +"].  Each TestCase must be explicitly defined, even if there is no validation to ensure no false pass statuses.");
				break;
		}
	}
	
	@AssertAfter(id="DocumentAccessor_110", step="SetValues")
	public void verifyDocumentAccessor() throws Exception {
		switch(getName()) {
			case "testRetrievedTagAllNoFileError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("0"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllNoFileOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("0"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllMultipleFilesError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllMultipleFilesOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllSingleFile" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("1"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyNoFileError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("0"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyNoFileOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("0"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyMultipleFilesError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyMultipleFilesOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnySingleFile" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("1"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountOkReturnOnMessage" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllMultipleFilesOkReturnOnMessage" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllSingleFileReturnOnMessage" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("1"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAllNoFileOkReturnOnMessage" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("0"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyNoFileOkReturnOnMessage" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("0"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountOkReturnOnMessage" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnyMultipleFilesOkReturnOnMessage" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testRetrievedTagAnySingleFileReturnOnMessage" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("1"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
			
			case "testNoTagsReturnVariable" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("8"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;

			case "testMultipleTagsAnyReturnVariable" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("3"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;
				
			case "testMultipleTagsAllReturnVariable" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_FILE_COUNT), Integer.parseInt("1"), ctx.getProperty(PROP_PARAMETER_OUT_FILE_COUNT));
				break;

			default :
				fail("Validation was not configured for TestCase ["+ getName() +"].  Each TestCase must be explicitly defined, even if there is no validation to ensure no false pass statuses.");
				break;
		}
	}
	
	@AssertAfter(id="Call_CreateLogEntry_NoFileProceed_Info_110")
	public void verifyNoFilesOk() throws Exception {
		if ("testRetrievedTagAllNoFileOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllNoFileOkReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyNoFileOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyNoFileOkReturnOnMessage".equalsIgnoreCase(getName())) {
			
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES, CONTENT_TYPE_TEXT_XML));
			
		} else {
			fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
		}
	}
	
	@AssertAfter(id="Call_CreateLogEntry_MultipleFilesProceed_Info_110")
	public void verifyMultipleFilesOk() throws Exception {
		if ("testRetrievedTagAllMultipleFilesOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllMultipleFilesExpectedCountOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllMultipleFilesOkReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllMultipleFilesExpectedCountOkReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyMultipleFilesOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyMultipleFilesExpectedCountOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyMultipleFilesOkReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyMultipleFilesExpectedCountOkReturnOnMessage".equalsIgnoreCase(getName())) {

			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
		} else if ("testMultipleTagsAnyReturnVariable".equalsIgnoreCase(getName())) {

			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES_TAGS),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES_TAGS, CONTENT_TYPE_TEXT_XML));
		} else if ("testNoTagsReturnVariable".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_NO_FILTER),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_NO_FILTER, CONTENT_TYPE_TEXT_XML));
		} else {
			fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
		}
	}
	
	@AssertAfter(id="Call_CreateLogEntry_SingleFileProceed_Info_110")
	public void verifySingleFileOk() throws Exception {
		if ("testRetrievedTagAllSingleFile".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnySingleFile".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllSingleFileReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnySingleFileReturnOnMessage".equalsIgnoreCase(getName())) {

			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES, CONTENT_TYPE_TEXT_XML));
		} else if ("testMultipleTagsAllReturnVariable".equalsIgnoreCase(getName())) {

			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES_TAGS),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES_TAGS, CONTENT_TYPE_TEXT_XML));
		} else {
			fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
		}
	}
	
	@AssertAfter(id="InitializeAndFinalize_110", step="MoveMsgToVar")
	public void verifyReturnOnMessage() throws Exception {
		if ("testRetrievedTagAllNoFileOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyNoFileOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllMultipleFilesOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllMultipleFilesExpectedCountOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyMultipleFilesOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyMultipleFilesExpectedCountOk".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllSingleFile".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnySingleFile".equalsIgnoreCase(getName())) {

			assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_GLOBAL_DA_METADATA), isVariableNullOrUndefined(VAR_GLOBAL_DA_METADATA));
		} else if ("testRetrievedTagAllNoFileOkReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyNoFileOkReturnOnMessage".equalsIgnoreCase(getName())) {

			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_0FILES, CONTENT_TYPE_TEXT_XML));
			
			assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_DA_METADATA), isVariableNullOrUndefined(VAR_GLOBAL_DA_METADATA));
		} else if ("testRetrievedTagAllSingleFileReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnySingleFileReturnOnMessage".equalsIgnoreCase(getName())) {

			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_1FILES, CONTENT_TYPE_TEXT_XML));
			
			assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_DA_METADATA), isVariableNullOrUndefined(VAR_GLOBAL_DA_METADATA));
		} else if ("testRetrievedTagAllMultipleFilesOkReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAllMultipleFilesExpectedCountOkReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyMultipleFilesOkReturnOnMessage".equalsIgnoreCase(getName()) ||
				"testRetrievedTagAnyMultipleFilesExpectedCountOkReturnOnMessage".equalsIgnoreCase(getName())) {

			assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES),
					compareAgainstMessageRoot(CONTENT_TYPE_TEXT_XML, MOCK_XML_SSK110_EXPECTED_RESULTS_3FILES, CONTENT_TYPE_TEXT_XML));
			
			assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_DA_METADATA), isVariableNullOrUndefined(VAR_GLOBAL_DA_METADATA));
		} else if (getName().endsWith("OnMessage")) {
			assertTrue(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_DA_METADATA), isVariableNullOrUndefined(VAR_GLOBAL_DA_METADATA));
		} else {
			assertFalse(String.format(MESSAGE_VARIABLE_NOT_REMOVED, VAR_GLOBAL_DA_METADATA), isVariableNullOrUndefined(VAR_GLOBAL_DA_METADATA));
		}
	}
	
	protected void verifyLogNoFilesError() throws Exception {
		switch(getName()) {
			case "testRetrievedTagAllNoFileError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_ERROR, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "No retrieved file was found!", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "0 file(s) were found as generated from the Document Retrieval Service on the BP.  Data is expected.  A no-data condition is an error and the output of the Document Retrieval Service should be investigated.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "0", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			case "testRetrievedTagAnyNoFileError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_ERROR, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "No retrieved file was found!", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "0 file(s) were found as generated from the Document Retrieval Service on the BP.  Data is expected.  A no-data condition is an error and the output of the Document Retrieval Service should be investigated.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "0", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), "Document Tag filter = ["+ String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG)) +"]", ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			default :
				fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
				break;
		}
	}
	
	protected void verifyLogNoFilesOk() throws Exception {
		switch(getName()) {
			case "testRetrievedTagAllNoFileOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_INFO, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "No retrieved file was found.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "0 file(s) were found as generated from the Document Retrieval Service on the BP.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "0", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			case "testRetrievedTagAnyNoFileOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_INFO, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "No retrieved file was found.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "0 file(s) were found as generated from the Document Retrieval Service on the BP.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "0", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), "Document Tag filter = ["+ String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG)) +"]", ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			default :
				fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
				break;
		}
	}
	
	protected void verifyLogMultipleFilesError() throws Exception {
		switch(getName()) {
			case "testRetrievedTagAllMultipleFilesError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_ERROR, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "Too many retrieved files were found!", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "4 file(s) were found as generated from the Document Retrieval Service on the BP.  Only a single file is expected.  A multiple-data condition is an error and the output of the Document Retrieval Service should be investigated.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "4", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			case "testRetrievedTagAnyMultipleFilesError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_ERROR, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "Too many retrieved files were found!", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "3 file(s) were found as generated from the Document Retrieval Service on the BP.  Only a single file is expected.  A multiple-data condition is an error and the output of the Document Retrieval Service should be investigated.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "3", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), "Document Tag filter = ["+ String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG)) +"]", ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			default :
				fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
				break;
		}
	}
	
	protected void verifyLogMultipleFilesExpectedCountError() throws Exception {
		switch(getName()) {
			case "testRetrievedTagAllMultipleFilesExpectedCountError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_ERROR, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "The expected number of files was not found!", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "4 file(s) were found as generated from the Document Retrieval Service on the BP.  However, 3 files(s) were expected.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "4", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountError" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_ERROR, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "The expected number of files was not found!", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "3 file(s) were found as generated from the Document Retrieval Service on the BP.  However, 4 files(s) were expected.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "3", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), "Document Tag filter = ["+ String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG)) +"]", ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			default :
				fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
				break;
		}
	}
	
	protected void verifyLogMultipleFilesOk() throws Exception {
		switch(getName()) {
			case "testRetrievedTagAllMultipleFilesOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_INFO, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "Multiple retrieved files were found.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "4 file(s) were found as generated from the Document Retrieval Service on the BP.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "4", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			case "testRetrievedTagAnyMultipleFilesOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_INFO, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "Multiple retrieved files were found.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "3 file(s) were found as generated from the Document Retrieval Service on the BP.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "3", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), "Document Tag filter = ["+ String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG)) +"]", ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			case "testRetrievedTagAllMultipleFilesExpectedCountOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_INFO, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "Multiple retrieved files were found.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "4 file(s) were found as generated from the Document Retrieval Service on the BP.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "4", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			case "testRetrievedTagAnyMultipleFilesExpectedCountOk" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_INFO, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "Multiple retrieved files were found.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "3 file(s) were found as generated from the Document Retrieval Service on the BP.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "3", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), "Document Tag filter = ["+ String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG)) +"]", ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			default :
				fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
				break;
		}
	}
	
	protected void verifyLogSingleFile() throws Exception {
		switch(getName()) {
			case "testRetrievedTagAllSingleFile" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_INFO, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "A Document Retrieval Service file was found and loaded.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "1 file(s) were found as generated from the Document Retrieval Service on the BP.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "1", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			case "testRetrievedTagAnySingleFile" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_LEVEL), VALUE_LOG_LEVEL_INFO, ctx.getProperty(PROP_PARAMETER_IN_LOG_LEVEL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE), "A Document Retrieval Service file was found and loaded.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL), "1 file(s) were found as generated from the Document Retrieval Service on the BP.", ctx.getProperty(PROP_PARAMETER_IN_LOG_MESSAGE_DETAIL));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_LOG_REFERENCE_ID), "1", ctx.getProperty(PROP_PARAMETER_IN_LOG_REFERENCE_ID));
				assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER), ctx.getProperty(PROP_PARAMETER_IN_EXTRA_RECORD_NUMBER));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA), "Document Tag filter = ["+ String.valueOf(ctx.getProperty(PROP_PARAMETER_IN_RETRIEVAL_DOCUMENT_TAG)) +"]", ctx.getProperty(PROP_PARAMETER_IN_EXTRA_SUPPORT_DATA));
				break;
			
			default :
				fail("TestCase ["+ getName() +"] should not have executed this Mediation.  Inspect the preceding Router for logical errors.");
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

}
