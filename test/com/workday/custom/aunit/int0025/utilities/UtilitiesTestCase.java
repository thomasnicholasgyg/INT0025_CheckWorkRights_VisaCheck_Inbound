package com.workday.custom.aunit.int0025.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.capeclear.mediation.MediationMessage;
import com.workday.aunit.actions.Action;
import com.workday.aunit.actions.StandardAction;
import com.workday.aunit.annotations.AtComponent;
import com.workday.custom.aunit.int0025.CommonTestCase;

public abstract class UtilitiesTestCase extends CommonTestCase {

	Logger LOG = Logger.getLogger(getClass()); 

	public static final String PROP_PARAMETER_IN_REQUEST_HEADER = "inHeaderRequestId";
	public static final String PROP_PARAMETER_IN_APPLICATION_HEADER = "inHeaderApplicationId";
	public static final String PROP_PARAMETER_IN_ORIGINATOR_HEADER = "inHeaderOriginatorId";

	public static final String PROP_PARAMETER_OUT_REQUEST_HEADER = "outRequestIdHeader119";
	public static final String PROP_PARAMETER_OUT_APPLICATION_HEADER = "outApplicationIdHeader119";
	public static final String PROP_PARAMETER_OUT_ORIGINATOR_HEADER = "outOriginatorIdHeader119";
	public static final String PROP_PARAMETER_OUT_ERROR_OVERRIDE_MAP = "outCloudLogFieldOverrideMap145";

	public static final String PROP_PARAMETER_IN_API_VERSION = "inApiVersion";
	public static final String PROP_PARAMETER_IN_RETURN_RESULTS = "inReturnResults";
	public static final String PROP_PARAMETER_IN_DEBUG_MODE = "inDebugMode";
	public static final String PROP_PARAMETER_IN_VALIDATE_ONLY = "inValidateOnly";
	public static final String PROP_PARAMETER_IN_ABORT_ON_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_IN_LOG_TARGET = "inLogTarget";
	public static final String PROP_PARAMETER_IN_CHILD_THREAD_CONTEXT = "inIsChildThreadContext";
	public static final String PROP_PARAMETER_IN_ERROR_OVERRIDE_MAP = "inErrorLogOverrideMap";
	
	public static final String VALUE_VALIDATE_ONLY_HEADER = "x-validate-only";
	public static final String VALUE_REQUEST_HEADER = "wd-external-request-id";
	public static final String VALUE_APPLICATION_HEADER = "wd-external-application-id";
	public static final String VALUE_ORIGINATOR_HEADER = "wd-external-originator-id";

	public static final String MESSAGE_SSK_AUNIT_NO_RESOURCE_CONTENT_PROVIDED = "No resource path was provided to load content to the target on test [%1$s].  This may reflect missing test configuration; it does not indicate a problem with the integration code.  This error is raised to attract attention to the missing test configuration and avoid unexpected behavior and results that may not be clear.";

	private boolean isEventDocumentsInitialized = false;
	private List<BlobitoryFileResource> blobitoryItems;
	private List<String> checkForHeaders;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		List<String> componentsToTrack = new ArrayList<String>();
		componentsToTrack.add("CallRaaS_102");
		componentsToTrack.add("HttpOut_102");
		componentsToTrack.add("CallSoap_103");
		componentsToTrack.add("CallSoapParallel_104");
		componentsToTrack.add("CallSoapSerial_104");
		componentsToTrack.add("PIM_Tag_106");
		componentsToTrack.add("GetEventDocuments_110");
		componentsToTrack.add("CallSoap_113");
		componentsToTrack.add("PIM_ImportProcessTarget_113");
		componentsToTrack.add("GetEventDocuments_115");
		componentsToTrack.add("GetEventDocuments_118");
		componentsToTrack.add("CallCustomObjectAPI_120");
		componentsToTrack.add("CallQueue_122");
		componentsToTrack.add("CreateQueue_122");
		componentsToTrack.add("CallQueue_123");
		componentsToTrack.add("CallQueue_124");
		componentsToTrack.add("CallQueueRead_125");
		componentsToTrack.add("CallQueueDelete_125");
		componentsToTrack.add("CallQueue_126");
		componentsToTrack.add("CallQueueDelete_127");
		componentsToTrack.add("CallQueue_128");
		componentsToTrack.add("CallQueue_Read_128");
		componentsToTrack.add("CallQueue_Delete_128");
		componentsToTrack.add("GetReportSchema_146");

		mockTracker.addComponentTracking(componentsToTrack);
		
		blobitoryItems = new ArrayList<BlobitoryFileResource>();
		
		checkForHeaders = new ArrayList<String>();
		checkForHeaders.add(VALUE_VALIDATE_ONLY_HEADER);
		
		setupGlobalInitialization();
		setupDebugValidations();
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	protected void verifyHeadersRemoved() {
		verifyHeadersRemoved(null);
	}
	
	protected void verifyHeadersRemoved(List<String> componentHeaders) {
		if (componentHeaders != null) {
			checkForHeaders.addAll(componentHeaders);
		}
		
		MediationMessage message = ctx.getMessage();
				
		@SuppressWarnings("rawtypes")
		Iterator headers = message.getHeaderNameIterator();
		while (headers.hasNext()) {
			String header = String.valueOf(headers.next());
			
			if (checkForHeaders.contains(header)) {
				assertTrue(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, header + " HTTP Header"), StringUtils.isBlank(message.getHeader(header)));
			}
		}
	}

	private void validateHeaderValue(String headerName, String expectedValue, String assertionMessage) throws Exception {
		assertEquals(assertionMessage, expectedValue, ctx.getMessage().getHeader(headerName));
	}
	
	protected void validateValidateOnlyHeader(String expectedValue, String assertionMessage) throws Exception {
		validateHeaderValue(VALUE_VALIDATE_ONLY_HEADER, expectedValue, assertionMessage);
	}
	
	protected void validateExternalRequestHeader(String expectedValue, String assertionMessage) throws Exception {
		validateHeaderValue(VALUE_REQUEST_HEADER, expectedValue, assertionMessage);
	}
	
	protected void validateExternalSystemHeader(String expectedValue, String assertionMessage) throws Exception {
		validateHeaderValue(VALUE_APPLICATION_HEADER, expectedValue, assertionMessage);
	}
	
	protected void validateExternalOriginatorHeader(String expectedValue, String assertionMessage) throws Exception {
		validateHeaderValue(VALUE_ORIGINATOR_HEADER, expectedValue, assertionMessage);
	}
	
	private void validateHeaderIsNotSet(String headerName, String assertionMessage) throws Exception {
		@SuppressWarnings("rawtypes")
		Iterator headers = ctx.getMessage().getHeaderNameIterator();
		
		boolean isHeaderFound = false;
		while (headers.hasNext() && !isHeaderFound) {
			isHeaderFound = headerName.equalsIgnoreCase(String.valueOf(headers.next()));
		}

		if (isHeaderFound) {
			assertTrue(assertionMessage, StringUtils.isBlank(ctx.getMessage().getHeader(headerName)));
		}
	}
	
	protected void validateValidateOnlyHeaderIsNotSet(String assertionMessage) throws Exception {
		validateHeaderIsNotSet(VALUE_VALIDATE_ONLY_HEADER, assertionMessage);
	}

	protected void validateExternalRequestHeaderIsNotSet(String assertionMessage) throws Exception {
		validateHeaderIsNotSet(VALUE_REQUEST_HEADER, assertionMessage);
	}
	
	protected void validateExternalSystemHeaderIsNotSet(String assertionMessage) throws Exception {
		validateHeaderIsNotSet(VALUE_APPLICATION_HEADER, assertionMessage);
	}
	
	protected void validateExternalOriginatorHeaderIsNotSet(String assertionMessage) throws Exception {
		validateHeaderIsNotSet(VALUE_ORIGINATOR_HEADER, assertionMessage);
	}
	
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	/**
	 * Provided as an opportunity to override the default content type, which is typically text/xml.
	 * 
	 * @return
	 */
	protected String getDefaultResourceFileContentType() {
		return CONTENT_TYPE_TEXT_XML;
	}

	protected String getDefaultResourceTarget() {
		return "message";
	}

	protected Action handleAtComponentInterceptWithMockResponse(IResourceLocator resourceLocator, String mediationId) throws Exception {
		Throwable mockError = loadResource(resourceLocator);		
		
		mockTracker.incrementCallCount(mediationId);
		if (mockError != null && mockError instanceof StudioStarterKitAunitException) {
			return new StandardAction(Action.Type.mock_exception);
		} else if (mockError != null) {
			return new StandardAction(Action.Type.mock_exception, mockError);
		} else {
			return new StandardAction(Action.Type.mock);
		}
	}

	protected Throwable loadResource(IResourceLocator resourceLocator) throws Exception {
		String pathToMockData = resourceLocator.getResource();
		String contentType = resourceLocator.getContentType();
		String target = resourceLocator.getResourceTarget();
		Throwable mockError = resourceLocator.getExceptionToThrow();
		
		if (StringUtils.isNotBlank(pathToMockData)) {
			if ("message".equalsIgnoreCase(target)) {
				setMessagePart(0, pathToMockData, contentType);
			} else {
				setVariable(target, pathToMockData, contentType);
			}
		} else if (mockError == null) {
			throw new StudioStarterKitAunitConfigurationException(
					String.format(MESSAGE_SSK_AUNIT_NO_RESOURCE_CONTENT_PROVIDED, getName()));
		}
		
		return mockError;
	}
	
	protected void initializeFileBlobitory() throws Exception {
		for (BlobitoryFileResource f : this.blobitoryItems) {
			putToBlobitory(f.resourcePath, f.collectionName, f.entryId, f.contentType);
		}
	}
	
	protected void initializeEventDocuments() throws Exception {
		String pathToMockData = getFileForSetIntegrationEventDocuments();

		if (!isEventDocumentsInitialized) {
			setIntegrationEventDocuments(pathToMockData);
		}
		setMessagePart(0, pathToMockData, CONTENT_TYPE_TEXT_XML);
	}

	protected String getFileForSetIntegrationEventDocuments() {
		return "";
	}
//	
//	@AtComponent(id="Initialize_109", step="FlexLogAssets")
//	public Action handleFlexLogMetaBean() throws Exception {
//		this.ctx.setProperty(MediationConstants.PROPERTY_SSK_FLEX_LOG_META_BEAN, getFlexLogMetaConfiguration());
//		return new StandardAction(Action.Type.continue_to_next);
//	}
//	
//	protected FlexLogMetaConfiguration getFlexLogMetaConfiguration() {
//		return new FlexLogMetaConfiguration();
//	}
	
	@AtComponent(id="CallRaaS_102")
	public Action handleRaasViaREST() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK102REST();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK102REST();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK102REST();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK102REST();
			}
		}, "CallRaaS_102");
	}

	protected String getResourceFileForSSK102REST() {
		return "";
	}
	
	protected String getContentTypeForSSK102REST() {
		return getDefaultResourceFileContentType();
	}
	
	protected String getResourceTargetForSSK102REST() {
		return getDefaultResourceTarget();
	}
	
	protected Throwable getExceptionForSSK102REST() {
		return null;
	}
	
	@AtComponent(id="HttpOut_102")
	public Action handleRaasViaSOAP() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK102SOAP();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK102SOAP();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK102SOAP();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK102SOAP();
			}
		}, "HttpOut_102");
	}

	protected String getResourceFileForSSK102SOAP() {
		return "";
	}

	protected String getContentTypeForSSK102SOAP() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK102SOAP() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK102SOAP() {
		return null;
	}
	
	@AtComponent(id="CallSoap_103")
	public Action handleCallSoap() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK103();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK103();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK103();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK103();
			}
		}, "CallSoap_103");
	}

	protected String getResourceFileForSSK103() {
		return "";
	}

	protected String getContentTypeForSSK103() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK103() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK103() {
		return null;
	}
	
	@AtComponent(id="CallSoapParallel_104")
	public Action handleCallSoapPagedParallel104() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK104();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK104();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK104();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK104();
			}
		}, "CallSoapParallel_104");
	}
	
	@AtComponent(id="CallSoapSerial_104")
	public Action handleCallSoapPagedSerial104() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK104();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK104();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK104();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK104();
			}
		}, "CallSoapSerial_104");
	}

	protected String getResourceFileForSSK104() {
		return "";
	}

	protected String getContentTypeForSSK104() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK104() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK104() {
		return null;
	}

	@AtComponent(id="PIM_Tag_106")
	public Action mockGenerateOutputTagDeliverable() throws Exception {
		mockTracker.incrementCallCount("PIM_Tag_106");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="GetEventDocuments_110")
	public Action mockGetEventDocuments110() throws Exception {
		if (!isEventDocumentsInitialized) {
			initializeFileBlobitory();
		}
		initializeEventDocuments();
		
		isEventDocumentsInitialized = true;
		mockTracker.incrementCallCount("GetEventDocuments_110");
		return new StandardAction(Action.Type.mock);
	}

	@AtComponent(id="CallSoap_113")
	public Action handleCallSoapImport() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK113();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK113();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK113();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK113();
			}
		}, "CallSoap_113");
	}
	
	protected String getResourceFileForSSK113() {
		return "";
	}

	protected String getContentTypeForSSK113() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK113() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK113() {
		return null;
	}
	
	@AtComponent(id="PIM_ImportProcessTarget_113")
	public Action mockImportProcessTarget() throws Exception {
		mockTracker.incrementCallCount("PIM_ImportProcessTarget_113");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="GetEventDocuments_115")
	public Action mockGetEventDocuments115() throws Exception {
		if (!isEventDocumentsInitialized) {
			initializeFileBlobitory();
		}
		initializeEventDocuments();
		
		isEventDocumentsInitialized = true;
		mockTracker.incrementCallCount("GetEventDocuments_115");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="GetEventDocuments_118")
	public Action mockGetEventDocuments118() throws Exception {
		if (!isEventDocumentsInitialized) {
			initializeFileBlobitory();
		}
		initializeEventDocuments();
		
		isEventDocumentsInitialized = true;
		mockTracker.incrementCallCount("GetEventDocuments_118");
		return new StandardAction(Action.Type.mock);
	}
	
	@AtComponent(id="CallSoapPagedGet_118")
	public Action handleCallSoapPaged118() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK118();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK118();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK118();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK118();
			}
		}, "CallSoapPagedGet_118");
	}
	
	protected String getResourceFileForSSK118() {
		return "";
	}

	protected String getContentTypeForSSK118() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK118() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK118() {
		return null;
	}

	@AtComponent(id="CallCustomObjectAPI_120")
	public Action handleCallCustomObjectAPI120() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK120();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK120();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK120();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK120();
			}
		}, "CallCustomObjectAPI_120");
	}

	protected String getResourceFileForSSK120() {
		return "";
	}

	protected String getContentTypeForSSK120() {
		return CONTENT_TYPE_APPLICATION_JSON;
	}

	protected String getResourceTargetForSSK120() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK120() {
		return null;
	}

	@AtComponent(id="CallQueue_122")
	public Action mockCallQueue122() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK122GetQueue();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK122GetQueue();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK122GetQueue();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK122GetQueue();
			}
		}, "CallQueue_122");
	}

	protected String getResourceFileForSSK122GetQueue() {
		return "";
	}

	protected String getContentTypeForSSK122GetQueue() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK122GetQueue() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK122GetQueue() {
		return null;
	}

	@AtComponent(id="CreateQueue_122")
	public Action mockCreateQueue122() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK122CreateQueue();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK122CreateQueue();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK122CreateQueue();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK122CreateQueue();
			}
		}, "CreateQueue_122");
	}

	protected String getResourceFileForSSK122CreateQueue() {
		return "";
	}

	protected String getContentTypeForSSK122CreateQueue() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK122CreateQueue() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK122CreateQueue() {
		return null;
	}

	@AtComponent(id="CallQueue_123")
	public Action mockCallQueue123() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK123();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK123();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK123();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK123();
			}
		}, "CallQueue_123");
	}

	protected String getResourceFileForSSK123() {
		return "";
	}

	protected String getContentTypeForSSK123() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK123() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK123() {
		return null;
	}

	@AtComponent(id="CallQueue_124")
	public Action mockCallQueue124() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK124();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK124();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK124();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK124();
			}
		}, "CallQueue_124");
	}

	protected String getResourceFileForSSK124() {
		return "";
	}

	protected String getContentTypeForSSK124() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK124() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK124() {
		return null;
	}

	@AtComponent(id="CallQueueRead_125")
	public Action mockCallQueueRead125() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK125Read();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK125Read();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK125Read();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK125Read();
			}
		}, "CallQueueRead_125");
	}

	protected String getResourceFileForSSK125Read() {
		return "";
	}

	protected String getContentTypeForSSK125Read() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK125Read() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK125Read() {
		return null;
	}

	@AtComponent(id="CallQueueDelete_125")
	public Action mockCallQueueDelete125() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK125Delete();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK125Delete();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK125Delete();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK125Delete();			}
		}, "CallQueueDelete_125");
	}

	protected String getResourceFileForSSK125Delete() {
		return "";
	}

	protected String getContentTypeForSSK125Delete() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK125Delete() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK125Delete() {
		return null;
	}

	@AtComponent(id="CallQueue_126")
	public Action mockCallQueue126() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK126();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK126();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK126();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK126();
			}
		}, "CallQueue_126");
	}

	protected String getResourceFileForSSK126() {
		return "";
	}

	protected String getContentTypeForSSK126() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK126() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK126() {
		return null;
	}

	@AtComponent(id="CallQueueDelete_127")
	public Action mockCallQueueDelete127() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK127();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK127();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK127();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK127();
			}
		}, "CallQueueDelete_127");
	}

	protected String getResourceFileForSSK127() {
		return "";
	}

	protected String getContentTypeForSSK127() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK127() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK127() {
		return null;
	}

	@AtComponent(id="CallQueue_128")
	public Action mockCallQueue128() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK128GetQueue();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK128GetQueue();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK128GetQueue();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK128GetQueue();
			}
		}, "CallQueue_128");
	}

	protected String getResourceFileForSSK128GetQueue() {
		return "";
	}

	protected String getContentTypeForSSK128GetQueue() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK128GetQueue() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK128GetQueue() {
		return null;
	}

	@AtComponent(id="CallQueue_Read_128")
	public Action mockCallQueue_Read128() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK128Read();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK128Read();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK128Read();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK128Read();
			}
		}, "CallQueue_Read_128");
	}

	protected String getResourceFileForSSK128Read() {
		return "";
	}

	protected String getContentTypeForSSK128Read() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK128Read() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK128Read() {
		return null;
	}

	@AtComponent(id="CallQueue_Delete_128")
	public Action mockCallQueue_Delete128() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK128Delete();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK128Delete();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK128Delete();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK128Delete();
			}
		}, "CallQueue_Delete_128");
	}

	protected String getResourceFileForSSK128Delete() {
		return "";
	}

	protected String getContentTypeForSSK128Delete() {
		return getDefaultResourceFileContentType();
	}

	protected String getResourceTargetForSSK128Delete() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK128Delete() {
		return null;
	}

	@AtComponent(id="GetReportSchema_146")
	public Action mockGetReportSchema146() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK146();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK146();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK146();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK146();
			}
		}, "GetReportSchema_146");
	}

	protected String getResourceFileForSSK146() {
		return "";
	}
	
	protected String getContentTypeForSSK146() {
		return getDefaultResourceFileContentType();
	}
	
	protected String getResourceTargetForSSK146() {
		return getDefaultResourceTarget();
	}
	
	protected Throwable getExceptionForSSK146() {
		return null;
	}
	
	@AtComponent(id="GetIntegrationSystems_151")
	public Action mockGetIntegrationSystems151() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK151();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK151();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK151();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK151();
			}
		}, "GetIntegrationSystems_151");
	}

	protected String getResourceFileForSSK151() {
		return "";
	}
	
	protected String getContentTypeForSSK151() {
		return getDefaultResourceFileContentType();
	}
	
	protected String getResourceTargetForSSK151() {
		return getDefaultResourceTarget();
	}
	
	protected Throwable getExceptionForSSK151() {
		return null;
	}
	
	@AtComponent(id="RequestAccessToken_160")
	public Action mockRequestAccessToken160() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK160();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK160();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK160();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK160();
			}
		}, "RequestAccessToken_160");
	}

	protected String getResourceFileForSSK160() {
		return "";
	}

	protected String getContentTypeForSSK160() {
		return CONTENT_TYPE_APPLICATION_JSON;
	}

	protected String getResourceTargetForSSK160() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK160() {
		return null;
	}
	
	@AtComponent(id="CallApi_161")
	public Action mockCallApi161() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK161();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK161();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK161();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK161();
			}
		}, "CallApi_161");
	}

	protected String getResourceFileForSSK161() {
		return "";
	}

	protected String getContentTypeForSSK161() {
		return CONTENT_TYPE_APPLICATION_JSON;
	}

	protected String getResourceTargetForSSK161() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK161() {
		return null;
	}
	
	@AtComponent(id="CallApi_162")
	public Action mockCallApi162() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK162();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK162();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK162();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK162();
			}
		}, "CallApi_162");
	}

	protected String getResourceFileForSSK162() {
		return "";
	}

	protected String getContentTypeForSSK162() {
		return CONTENT_TYPE_APPLICATION_JSON;
	}

	protected String getResourceTargetForSSK162() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK162() {
		return null;
	}
	
	@AtComponent(id="CallApi_163")
	public Action mockCallApi163() throws Exception {
		return handleAtComponentInterceptWithMockResponse(new IResourceLocator() {
			@Override
			public String getResource() {
				return getResourceFileForSSK163();
			}

			@Override
			public String getContentType() {
				return getContentTypeForSSK163();
			}

			@Override
			public String getResourceTarget() {
				return getResourceTargetForSSK163();
			}

			@Override
			public Throwable getExceptionToThrow() {
				return getExceptionForSSK163();
			}
		}, "CallApi_163");
	}

	protected String getResourceFileForSSK163() {
		return "";
	}

	protected String getContentTypeForSSK163() {
		return CONTENT_TYPE_APPLICATION_JSON;
	}

	protected String getResourceTargetForSSK163() {
		return getDefaultResourceTarget();
	}

	protected Throwable getExceptionForSSK163() {
		return null;
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Utility Support
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

	private interface IResourceLocator {
		String getResource();
		String getContentType();
		String getResourceTarget();
		Throwable getExceptionToThrow();
	}
	
	private class BlobitoryFileResource {
		String resourcePath;
		String contentType;
		String collectionName;
		String entryId;
		
		public BlobitoryFileResource(String resourcePath, String contentType, String collectionName, String entryId) {
			super();
			this.resourcePath = resourcePath;
			this.contentType = contentType;
			this.collectionName = collectionName;
			this.entryId = entryId;
		}
	}
	
	protected final void registerBlobitoryResourceToInitialize(String resourcePath, String collectionName, String entryId, String contentType) {
		blobitoryItems.add(new BlobitoryFileResource(resourcePath, contentType, collectionName, entryId));
	}
}
