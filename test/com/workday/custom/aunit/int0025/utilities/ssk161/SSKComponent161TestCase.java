package com.workday.custom.aunit.int0025.utilities.ssk161;

import com.capeclear.capeconnect.transport.BadRequestException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.MediationConstants;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent161TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_TOKEN = "inAccessToken";
	public static final String PROP_PARAMETER_IN_GATEWAY_ENDPOINT = "inGatewayEndpoint";
	public static final String PROP_PARAMETER_IN_API_PATH = "inApiPath";
	public static final String PROP_PARAMETER_IN_HTTP_METHOD = "inHttpMethod";
	public static final String PROP_PARAMETER_IN_PAGINATED_GET = "inIsPaginatedGet";
	public static final String PROP_PARAMETER_IN_LIMIT = "inLimitForPaginatedGet";
	public static final String PROP_PARAMETER_IN_OFFSET = "inOffsetForPaginatedGet";
	public static final String PROP_PARAMETER_IN_RETRIEVE_ALL = "inIsRetrieveAllForPaginatedGet";
	public static final String PROP_PARAMETER_IN_DATA_LOCATION = "inDataLocation";
	public static final String PROP_PARAMETER_IN_HEADER_ACCEPT = "inHttpHeaderAccept";
	public static final String PROP_PARAMETER_IN_RETURN_AS_XML = "inReturnResultsAsXml";
	
	public static final String PROP_PARAMETER_OUT_ACCESS_TOKEN = "outAccessToken160";
	public static final String PROP_PARAMETER_OUT_TOTAL_COUNT = "outTotalRecordCountForPagination161";
	
	public static final String PROP_LOCAL_ENDPOINT = "localEndpoint161";
	public static final String PROP_LOCAL_CLIENT = "localHttpClientCache161";
	public static final String PROP_LOCAL_OFFSET = "localOffset161";

	public static final String VAR_LOCAL_RESPONSE = "responseAsXml161";

    public static final String VALUE_ACCESS_TOKEN = "ac09rao09svn5udbtsrh2kgdvar";
    public static final String VALUE_TENANT_ENDPOINT = "https://wcpdev-services1.wd101.myworkday.com/ccx/api";
    public static final String VALUE_TENANT_API_PATH_GET_NO_PAGE = "/staffing/v3/<tenant>/workers/6dcb8106e8b74b5aabb1fc3ab8ef2b92";
    public static final String VALUE_TENANT_API_PATH_GET_PAGINATED = "/staffing/v3/<tenant>/workers";
    public static final String VALUE_TENANT_API_PATH_POST = "/connect/v2/<tenant>/sendMessage";
    public static final String VALUE_EXTEND_ENDPOINT = "https://api.workday.com";
    public static final String VALUE_EXTEND_API_PATH_GET_NO_PAGE = "/staffing/v3/workers/6dcb8106e8b74b5aabb1fc3ab8ef2b92";
    public static final String VALUE_EXTEND_API_PATH_GET_PAGINATED = "/staffing/v3/workers";
    public static final String VALUE_EXTEND_API_PATH_POST = "/connect/v2/sendMessage";
    public static final String VALUE_HTTP_METHOD_GET = "GET";
    public static final String VALUE_HTTP_METHOD_POST = "POST";
    public static final int VALUE_PAGE_SIZE = 5;
    public static final int VALUE_TOTAL_SIZE = 20;

    public static final String VAR_MY_DATA = "myDataVariable";

    public static final String MOCK_POST_BODY = "test/int0025/int0025161/SSK161_POST_Body.json";
    public static final String MOCK_RESPONSE_POST_JSON = "test/int0025/int0025161/SSK161_POST_Response.json";
    public static final String MOCK_RESULT_POST_XML = "test/int0025/int0025161/SSK161_POST_Response.xml";

	public static final String MOCK_RESPONSE_BAD_REQUEST_EXCEPTION = "test/int0025/int0025161/SSK161_HttpResponse_BadRequestException.json";
	public static final String MOCK_RESPONSE_NONE_JSON = "test/int0025/int0025161/SSK161_GET_NoResults.json";
	public static final String MOCK_RESULT_NONE_XML = "test/int0025/int0025161/SSK161_GET_NoResults.xml";
	public static final String MOCK_RESPONSE_NONE_PAGINATED_JSON = "test/int0025/int0025161/SSK161_GET_NoResults_Paginated.json";
	public static final String MOCK_RESULT_NONE_PAGINATED_JSON = "test/int0025/int0025161/SSK161_GET_NoResults_Paginated-return.json";
	public static final String MOCK_RESULT_NONE_PAGINATED_XML = "test/int0025/int0025161/SSK161_GET_NoResults_Paginated.xml";
	public static final String MOCK_RESPONSE_SINGLE_JSON = "test/int0025/int0025161/SSK161_GET_Result.json";
	public static final String MOCK_RESULT_SINGLE_XML = "test/int0025/int0025161/SSK161_GET_Result.xml";
	public static final String MOCK_RESPONSE_ALL_JSON = "test/int0025/int0025161/SSK161_GET_AllResults.json";
	public static final String MOCK_RESPONSE_PAGE0_JSON = "test/int0025/int0025161/SSK161_GET_ResultPage-0.json";
	public static final String MOCK_RESULT_PAGE0_JSON = "test/int0025/int0025161/SSK161_GET_ResultPage-0-return.json";
	public static final String MOCK_RESULT_PAGE0_XML = "test/int0025/int0025161/SSK161_GET_ResultPage-0-return.xml";
	public static final String MOCK_RESPONSE_PAGE1_JSON = "test/int0025/int0025161/SSK161_GET_ResultPage-1.json";
	public static final String MOCK_RESPONSE_PAGE2_JSON = "test/int0025/int0025161/SSK161_GET_ResultPage-2.json";
	public static final String MOCK_RESPONSE_PAGE3_JSON = "test/int0025/int0025161/SSK161_GET_ResultPage-3.json";
	public static final String MOCK_RESULT_AGGREGATED_JSON = "test/int0025/int0025161/SSK161_GET_AllResults_Aggregated.json";
	public static final String MOCK_RESULT_AGGREGATED_XML = "test/int0025/int0025161/SSK161_GET_AllResults_Aggregated.xml";

	private int pageCounter;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		pageCounter = -1;
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, true);
		
		ctx.setProperty(PROP_PARAMETER_OUT_ACCESS_TOKEN, VALUE_ACCESS_TOKEN);
		
		registerComponentProperty(PROP_LOCAL_ENDPOINT);
		registerComponentProperty(PROP_LOCAL_CLIENT);
		registerComponentProperty(PROP_LOCAL_OFFSET);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetNoPageToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetNoPageToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetNoPageToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetNoPageToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetSinglePageToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_TOTAL_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetSinglePageToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_TOTAL_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetSinglePageToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_TOTAL_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetSinglePageToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_TOTAL_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetMultiPageRetrieveOneToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetMultiPageRetrieveOneToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetMultiPageRetrieveOneToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetMultiPageRetrieveOneToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetMultiPageRetrieveAllToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetMultiPageRetrieveAllToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetMultiPageRetrieveAllToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetMultiPageRetrieveAllToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetNoPageEmptyResultToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetNoPageEmptyResultToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetSinglePageEmptyResultToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 20);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantGetSinglePageEmptyResultToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 20);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantPostFromMessageToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantPostFromMessageToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantPostFromMessageToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantPostFromMessageToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantPostFromVariableToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantPostFromVariableToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantPostFromVariableToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testTenantPostFromVariableToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}
	
	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetNoPageToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetNoPageToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetNoPageToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetNoPageToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetSinglePageToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_TOTAL_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetSinglePageToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_TOTAL_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetSinglePageToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_TOTAL_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetSinglePageToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_TOTAL_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetMultiPageRetrieveOneToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetMultiPageRetrieveOneToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetMultiPageRetrieveOneToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetMultiPageRetrieveOneToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetMultiPageRetrieveAllToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetMultiPageRetrieveAllToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetMultiPageRetrieveAllToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetMultiPageRetrieveAllToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, VALUE_PAGE_SIZE);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetNoPageEmptyResultToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetNoPageEmptyResultToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetSinglePageEmptyResultToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 20);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendGetSinglePageEmptyResultToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_GET_PAGINATED);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 20);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendPostFromMessageToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendPostFromMessageToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendPostFromMessageToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendPostFromMessageToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendPostFromVariableToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendPostFromVariableToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendPostFromVariableToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testExtendPostFromVariableToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_EXTEND_API_PATH_POST);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_POST);
		ctx.setProperty(PROP_PARAMETER_IN_DATA_LOCATION, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_POST_BODY, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="CallRestApi")
	public void testErrorBadRequestException() throws Throwable {
		expectHandledException();
		
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_API_PATH, VALUE_TENANT_API_PATH_GET_NO_PAGE);
		ctx.setProperty(PROP_PARAMETER_IN_HTTP_METHOD, VALUE_HTTP_METHOD_GET);
		ctx.setProperty(PROP_PARAMETER_IN_PAGINATED_GET, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);
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
		int expectedTotal = (getName().contains("Empty") || getName().contains("Error") || !(boolean)ctx.getProperty(PROP_PARAMETER_IN_PAGINATED_GET)) ? 0 : VALUE_TOTAL_SIZE;

		Object totalCount = ctx.getProperty(PROP_PARAMETER_OUT_TOTAL_COUNT);
		if ("testErrorDataLocationRequiredByMethod".equalsIgnoreCase(getName())) {
			assertNull(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, PROP_PARAMETER_OUT_TOTAL_COUNT), totalCount);		
		} else {
			assertNotNull(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_TOTAL_COUNT, expectedTotal), totalCount);		
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_TOTAL_COUNT), expectedTotal, totalCount);
		}

		if (!isExpectedException()) {
			String expectedResult = null;
			String contentType = null;
			Comparator comparatorType = null;
			
			if (getName().contains("AsJson")) {
				contentType = CONTENT_TYPE_APPLICATION_JSON;
				comparatorType = Comparator.text;
	
				if (getName().contains("GetNoPageEmptyResult")) {
					expectedResult = MOCK_RESPONSE_NONE_JSON;
				} else if (getName().contains("GetSinglePageEmptyResult")) {
					expectedResult = MOCK_RESULT_NONE_PAGINATED_JSON;
				} else if (getName().contains("GetNoPage")) {
					expectedResult = MOCK_RESPONSE_SINGLE_JSON;
				} else if (getName().contains("GetSinglePage")) {
					expectedResult = MOCK_RESULT_AGGREGATED_JSON;
				} else if (getName().contains("GetMultiPageRetrieveOne")) {
					expectedResult = MOCK_RESULT_PAGE0_JSON;
				} else if (getName().contains("GetMultiPageRetrieveAll")) {
					expectedResult = MOCK_RESULT_AGGREGATED_JSON;
				} else if (getName().contains("Post")) {
					expectedResult = MOCK_RESPONSE_POST_JSON;
				} else {
					fail("Unable to determine test exit criteria for result evaluation.");
				}
			} else if (getName().contains("AsXml")) {
				contentType = CONTENT_TYPE_TEXT_XML;
				comparatorType = Comparator.dom;
				
				if (getName().contains("GetNoPageEmptyResult")) {
					expectedResult = MOCK_RESULT_NONE_XML;
				} else if (getName().contains("GetSinglePageEmptyResult")) {
					expectedResult = MOCK_RESULT_NONE_PAGINATED_XML;
				} else if (getName().contains("GetNoPage")) {
					expectedResult = MOCK_RESULT_SINGLE_XML;
				} else if (getName().contains("GetSinglePage")) {
					expectedResult = MOCK_RESULT_AGGREGATED_XML;
				} else if (getName().contains("GetMultiPageRetrieveOne")) {
					expectedResult = MOCK_RESULT_PAGE0_XML;
				} else if (getName().contains("GetMultiPageRetrieveAll")) {
					expectedResult = MOCK_RESULT_AGGREGATED_XML;
				} else if (getName().contains("Post")) {
					expectedResult = MOCK_RESULT_POST_XML;
				} else {
					fail("Unable to determine test exit criteria for result evaluation.");
				}
			} 
			
			if (getName().contains("ToMessage")) {
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, expectedResult),
						compareAgainstMessageRoot(contentType, expectedResult, contentType, comparatorType));
			} else if (getName().contains("ToVariable")) {
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_VARIABLE, VAR_MY_DATA),
						compareAgainstVariable(VAR_MY_DATA, contentType, expectedResult, contentType, comparatorType));
			}
		}
	}

	@AssertAfter(id="InitializeAndFinalize_161", step="InitValues")
	public void verifyApiPath() throws Exception {
		if (!getName().contains("Error")) {
			String actualValue = (String)ctx.getProperty(PROP_PARAMETER_IN_API_PATH);
			String expectedValue = null;
			
			if (getName().contains("Post")) {
				expectedValue = (getName().startsWith("testTenant")) ? "/connect/v2/testTenant/sendMessage" : "/connect/v2/sendMessage";
			} else if (getName().contains("GetNoPage")) {
				expectedValue = (getName().startsWith("testTenant")) ? "/staffing/v3/testTenant/workers/6dcb8106e8b74b5aabb1fc3ab8ef2b92" : "/staffing/v3/workers/6dcb8106e8b74b5aabb1fc3ab8ef2b92";
			} else {
				expectedValue = (getName().startsWith("testTenant")) ? "/staffing/v3/testTenant/workers" : "/staffing/v3/workers";
			}
			
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_API_PATH), expectedValue, actualValue);
		}
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AssertBefore(id="ApiCatch_161", step="SetHeaders")
	public void incrementPageCounter() throws Exception {
		pageCounter++;
	}
	
	@Override
	protected String getResourceFileForSSK161() {
		if (getName().contains("GetNoPageEmptyResult")) {
			return MOCK_RESPONSE_NONE_JSON;
		} else if (getName().contains("GetSinglePageEmptyResult")) {
			return MOCK_RESPONSE_NONE_PAGINATED_JSON;
		} else if (getName().contains("GetNoPage")) {
			return MOCK_RESPONSE_SINGLE_JSON;
		} else if (getName().contains("GetSinglePage")) {
			return MOCK_RESPONSE_ALL_JSON;
		} else if (getName().contains("GetMultiPageRetrieveOne")) {
			return MOCK_RESPONSE_PAGE0_JSON;
		} else if (getName().contains("GetMultiPageRetrieveAll")) {
			if (pageCounter == 0) {
				return MOCK_RESPONSE_PAGE0_JSON;
			} else if (pageCounter == 1) {
				return MOCK_RESPONSE_PAGE1_JSON;
			} else if (pageCounter == 2) {
				return MOCK_RESPONSE_PAGE2_JSON;
			} else if (pageCounter == 3) {
				return MOCK_RESPONSE_PAGE3_JSON;
			} else {
				fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				return super.getResourceFileForSSK161();
			}
		} else if (getName().contains("Post")) {
			return MOCK_RESPONSE_POST_JSON;
		} else {
			return super.getResourceFileForSSK161();
		}
	}

	@Override
	protected Throwable getExceptionForSSK161() {
		if (getName().contains("BadRequestException")) {
			ctx.setProperty(MediationConstants.STUDIO_PROPERTY_HTTP_RESPONSE_CODE, 400);
			return new BadRequestException("{\"error\": \"invalid_grant\"}", "400");
		} else {
			return super.getExceptionForSSK161();
		}
	}
}
