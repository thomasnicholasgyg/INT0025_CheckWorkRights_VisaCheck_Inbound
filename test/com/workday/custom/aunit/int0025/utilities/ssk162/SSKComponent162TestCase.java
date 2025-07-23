package com.workday.custom.aunit.int0025.utilities.ssk162;

import com.capeclear.capeconnect.transport.BadRequestException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.AssertBefore;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.MediationConstants;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent162TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_TOKEN = "inAccessToken";
	public static final String PROP_PARAMETER_IN_GATEWAY_ENDPOINT = "inGatewayEndpoint";
	public static final String PROP_PARAMETER_IN_API_PATH = "inApiPath";
	public static final String PROP_PARAMETER_IN_QUERY_SOURCE = "inQuerySource";
	public static final String PROP_PARAMETER_IN_QUERY_SOURCE_ID = "inQuerySourceId";
	public static final String PROP_PARAMETER_IN_QUERY_SOURCE_READY = "inQuerySourceIsBodyReady";
	public static final String PROP_PARAMETER_IN_LIMIT = "inLimit";
	public static final String PROP_PARAMETER_IN_OFFSET = "inOffset";
	public static final String PROP_PARAMETER_IN_RETRIEVE_ALL = "inIsRetrieveAll";
	public static final String PROP_PARAMETER_IN_RETURN_AS_XML = "inReturnResultsAsXml";
	
	public static final String PROP_PARAMETER_OUT_ACCESS_TOKEN = "outAccessToken160";
	public static final String PROP_PARAMETER_OUT_TOTAL_COUNT = "outTotalRecordCount162";
	
	public static final String PROP_LOCAL_ENDPOINT = "localEndpoint162";
	public static final String PROP_LOCAL_OFFSET = "localOffset162";
	
	public static final String VAR_LOCAL_QUERY = "queryCache162";

    public static final String VALUE_ACCESS_TOKEN = "ac09rao09svn5udbtsrh2kgdvar";
    public static final String VALUE_TENANT_ENDPOINT = "https://wcpdev-services1.wd101.myworkday.com/ccx/api";
    public static final String VALUE_EXTEND_ENDPOINT = "https://api.workday.com";
    public static final int VALUE_PAGE_SIZE = 10;
    public static final int VALUE_TOTAL_SIZE = 33;
    public static final String VALUE_QUERY_PLAIN = "SELECT workdayID, legalFullName, employeeID FROM workersForHCMReporting (dataSourceFilter = allActiveWorkers, workerTypes = (d588c41a446c11de98360015c5e6daf6)) WHERE worker in (0094a019bb0e100f650fa1feffcf20f6,0151b25b6e944546b765a607d622679c,02e864c9fffb4c3394e119251d3acf5a,02eddfbb22ab496ab241f830baf56e49,0341b29484324a8cb95217fe76bebc5e,03502615485840ceb4beacecf02ddc30,0416ac7c7d714327b22914ac06f2a0e6,042598cc163b45e9b7715ff0fdd328a8,04f766db7b474c2bbe21c308c26e8606,07051e1ec7c510d0189a595ec8ad3f8b,07051e1ec7c510d03e0fa245812bb14f,084d8a16c945442293d5bf4f15c75876,09343b3000f6423081fbaec43f340e5d,0973a3f056d04d1a988927aa04451f11,0a46063523fd469f96d4e81ed4d17812,0a9124e890ab4fc7ac134b504e481389,0ac84ff7d0e444278b103ca4568a38ea,0ad40269fa914f029e9925a740c3387d,0bd63069852b4613a6e24bf73566e41e,0cc84f94d39044c3bc6338240cdd6c93,0d0268b0f098424897773af94591321a,0d32ccd079004a1c8954caf934f220d6,0e44c92412d34b01ace61e80a47aaf6d,0e6d535eea5b4a81bd925350ec0690c5,1014ecc6e983105def2e4921e03a00ae,1014ecc6e983105df0014cfdc1eb0166,1014ecc6e983105dfa236f0e82f902f0,1014ecc6e983105dfa66c86d262203a0,11d4957b6d66459baf17247abff90b04,1262b39205e04bdf8807e775af714466,fe4e33b2a3081072a2cb5bb04aeb0217,fe5971a43e344e00bec99b54576ca5d2,fec9646a77184c78acc2d65222efdce9)";
    public static final String VALUE_QUERY_JSON = "{\"query\": \"SELECT workdayID, legalFullName, employeeID FROM workersForHCMReporting (dataSourceFilter = allActiveWorkers, workerTypes = (d588c41a446c11de98360015c5e6daf6)) WHERE worker in (0094a019bb0e100f650fa1feffcf20f6,0151b25b6e944546b765a607d622679c,02e864c9fffb4c3394e119251d3acf5a,02eddfbb22ab496ab241f830baf56e49,0341b29484324a8cb95217fe76bebc5e,03502615485840ceb4beacecf02ddc30,0416ac7c7d714327b22914ac06f2a0e6,042598cc163b45e9b7715ff0fdd328a8,04f766db7b474c2bbe21c308c26e8606,07051e1ec7c510d0189a595ec8ad3f8b,07051e1ec7c510d03e0fa245812bb14f,084d8a16c945442293d5bf4f15c75876,09343b3000f6423081fbaec43f340e5d,0973a3f056d04d1a988927aa04451f11,0a46063523fd469f96d4e81ed4d17812,0a9124e890ab4fc7ac134b504e481389,0ac84ff7d0e444278b103ca4568a38ea,0ad40269fa914f029e9925a740c3387d,0bd63069852b4613a6e24bf73566e41e,0cc84f94d39044c3bc6338240cdd6c93,0d0268b0f098424897773af94591321a,0d32ccd079004a1c8954caf934f220d6,0e44c92412d34b01ace61e80a47aaf6d,0e6d535eea5b4a81bd925350ec0690c5,1014ecc6e983105def2e4921e03a00ae,1014ecc6e983105df0014cfdc1eb0166,1014ecc6e983105dfa236f0e82f902f0,1014ecc6e983105dfa66c86d262203a0,11d4957b6d66459baf17247abff90b04,1262b39205e04bdf8807e775af714466,fe4e33b2a3081072a2cb5bb04aeb0217,fe5971a43e344e00bec99b54576ca5d2,fec9646a77184c78acc2d65222efdce9)\"}";
    public static final String VALUE_PROPERTY_QUERY = "myQuery";

    public static final String VAR_MY_DATA = "myDataVariable";

	public static final String MOCK_RESPONSE_NONE_JSON = "test/int0025/int0025162/SSK162_Response_NoResults.json";
	public static final String MOCK_RESPONSE_PAGE0_JSON = "test/int0025/int0025162/SSK162_Response_Paginated-0.json";
	public static final String MOCK_RESPONSE_PAGE1_JSON = "test/int0025/int0025162/SSK162_Response_Paginated-1.json";
	public static final String MOCK_RESPONSE_PAGE2_JSON = "test/int0025/int0025162/SSK162_Response_Paginated-2.json";
	public static final String MOCK_RESPONSE_PAGE3_JSON = "test/int0025/int0025162/SSK162_Response_Paginated-3.json";

	public static final String MOCK_RESULT_NONE_JSON = "test/int0025/int0025162/SSK162_MOCK_NoResults.json";
	public static final String MOCK_RESULT_NONE_XML = "test/int0025/int0025162/SSK162_MOCK_NoResults.xml";
	public static final String MOCK_RESULT_PAGE0_JSON = "test/int0025/int0025162/SSK162_MOCK_FirstPage.json";
	public static final String MOCK_RESULT_PAGE0_XML = "test/int0025/int0025162/SSK162_MOCK_FirstPage.xml";
	public static final String MOCK_RESULT_COLLECTION_JSON = "test/int0025/int0025162/SSK162_MOCK_Collection.json";
	public static final String MOCK_RESULT_COLLECTION_XML = "test/int0025/int0025162/SSK162_MOCK_Collection.xml";
	
	public static final String MOCK_QUERY_PLAIN = "test/int0025/int0025162/WQLquery-raw.txt";
	public static final String MOCK_QUERY_JSON = "test/int0025/int0025162/WQLquery-formatted.json";

	private int pageCounter;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		pageCounter = -1;
		
		if (getName().startsWith("testTenant")) {
			ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, "tenant");
		} else {
			ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, "extend");
		}
		
		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, true);
		
		ctx.setProperty(PROP_PARAMETER_OUT_ACCESS_TOKEN, VALUE_ACCESS_TOKEN);
		
		registerComponentProperty(PROP_LOCAL_ENDPOINT);
		registerComponentProperty(PROP_LOCAL_OFFSET);
		
		registerComponentVariable(VAR_LOCAL_QUERY);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromMessageQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromMessageQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromMessageQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromMessageQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromMessageQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromMessageQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromMessageQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromMessageQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromMessageQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromMessageQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromMessageQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromMessageQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromMessageReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromMessageReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromMessageReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromMessageReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromMessageReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromMessageReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromMessageReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromMessageReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromMessageReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromMessageReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromMessageReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromMessageReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromVariableQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromVariableQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromVariableQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromVariableQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromVariableQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromVariableQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromVariableQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromVariableQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromVariableQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromVariableQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromVariableQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromVariableQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromVariableReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromVariableReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromVariableReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromVariableReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromVariableReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromVariableReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromVariableReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromVariableReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromVariableReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromVariableReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromVariableReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromVariableReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromPropertyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromPropertyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromPropertyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromPropertyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromPropertyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromPropertyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromPropertyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromPropertyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromPropertyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromPropertyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromPropertyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromPropertyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromPropertyReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromPropertyReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromPropertyReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantNoResultFromPropertyReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromPropertyReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromPropertyReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromPropertyReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveOneFromPropertyReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromPropertyReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromPropertyReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromPropertyReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testTenantMultiPageRetrieveAllFromPropertyReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_TENANT_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromMessageQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromMessageQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromMessageQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromMessageQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromMessageQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromMessageQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromMessageQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromMessageQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromMessageQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromMessageQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromMessageQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromMessageQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromMessageReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromMessageReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromMessageReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromMessageReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromMessageReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromMessageReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromMessageReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromMessageReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromMessageReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromMessageReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromMessageReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromMessageReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "message");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setMessagePart(0, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromVariableQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromVariableQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromVariableQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromVariableQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromVariableQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromVariableQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromVariableQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromVariableQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromVariableQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromVariableQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromVariableQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromVariableQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_PLAIN, CONTENT_TYPE_TEXT_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromVariableReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromVariableReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromVariableReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromVariableReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromVariableReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromVariableReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromVariableReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromVariableReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromVariableReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromVariableReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromVariableReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromVariableReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "variable");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		setVariable(VAR_MY_DATA, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromPropertyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromPropertyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromPropertyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromPropertyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromPropertyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromPropertyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromPropertyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromPropertyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromPropertyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromPropertyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromPropertyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromPropertyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, false);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_PLAIN);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromPropertyReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromPropertyReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromPropertyReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendNoResultFromPropertyReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromPropertyReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromPropertyReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromPropertyReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveOneFromPropertyReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, false);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromPropertyReadyQueryToMessageAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromPropertyReadyQueryToMessageAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, "message");
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromPropertyReadyQueryToVariableAsJson() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, false);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
	}

	@UnitTest(startComponent="WQL")
	public void testExtendMultiPageRetrieveAllFromPropertyReadyQueryToVariableAsXml() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_API, VALUE_EXTEND_ENDPOINT);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE, "property");
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_ID, VALUE_PROPERTY_QUERY);
		ctx.setProperty(PROP_PARAMETER_IN_QUERY_SOURCE_READY, true);
		ctx.setProperty(PROP_PARAMETER_IN_LIMIT, 10);
		ctx.setProperty(PROP_PARAMETER_IN_OFFSET, 0);
		ctx.setProperty(PROP_PARAMETER_IN_RETRIEVE_ALL, true);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_RESULTS, VAR_MY_DATA);
		ctx.setProperty(PROP_PARAMETER_IN_RETURN_AS_XML, true);

		ctx.setProperty(VALUE_PROPERTY_QUERY, VALUE_QUERY_JSON);
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
		int expectedTotal = (getName().contains("NoResult") || getName().contains("Error")) ? 0 : VALUE_TOTAL_SIZE;

		Object totalCount = ctx.getProperty(PROP_PARAMETER_OUT_TOTAL_COUNT);
		if ("".equalsIgnoreCase(getName())) {
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
	
				if (getName().contains("NoResult")) {
					expectedResult = MOCK_RESULT_NONE_JSON;
				} else if (getName().contains("MultiPageRetrieveOne")) {
					expectedResult = MOCK_RESULT_PAGE0_JSON;
				} else if (getName().contains("MultiPageRetrieveAll")) {
					expectedResult = MOCK_RESULT_COLLECTION_JSON;
				} else {
					fail("Unable to determine test exit criteria for result evaluation.");
				}
			} else if (getName().contains("AsXml")) {
				contentType = CONTENT_TYPE_TEXT_XML;
				comparatorType = Comparator.dom;
				
				if (getName().contains("NoResult")) {
					expectedResult = MOCK_RESULT_NONE_XML;
				} else if (getName().contains("MultiPageRetrieveOne")) {
					expectedResult = MOCK_RESULT_PAGE0_XML;
				} else if (getName().contains("MultiPageRetrieveAll")) {
					expectedResult = MOCK_RESULT_COLLECTION_XML;
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

	@AssertAfter(id="BuildQuery_162", step="InitValues")
	public void verifyApiPath() throws Exception {
		String actualValue = (String)ctx.getProperty(PROP_PARAMETER_IN_API_PATH);
		String expectedValue = (getName().startsWith("testTenant")) ? "/wql/v1/testTenant/data" : "/wql/v1/data";
		
		assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_API_PATH), expectedValue, actualValue);
	}

	@AssertAfter(id="ApiCatch_162", step="CopyCacheToMsg")
	public void verifyQuery() throws Exception {
		assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_QUERY_JSON),
				compareAgainstMessageRoot(CONTENT_TYPE_APPLICATION_JSON, MOCK_QUERY_JSON, CONTENT_TYPE_APPLICATION_JSON, Comparator.binary));
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@AssertBefore(id="ApiCatch_162", step="SetHeaders")
	public void incrementPageCounter() throws Exception {
		pageCounter++;
		
		int offset = (int)ctx.getProperty(PROP_LOCAL_OFFSET);
		if (pageCounter == 0) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_OFFSET), 0, offset);
		} else if (getName().contains("MultiPageRetrieveAll")) {
			if (pageCounter == 1) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_OFFSET), 10, offset);
			} else if (pageCounter == 2) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_OFFSET), 20, offset);
			} else if (pageCounter == 3) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_LOCAL_OFFSET), 30, offset);
			} else {
				fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
			}
		} else {
			fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
		}
	}
	
	@Override
	protected String getResourceFileForSSK162() {
		if (getName().contains("NoResult")) {
			return MOCK_RESPONSE_NONE_JSON;
		} else if (getName().contains("MultiPageRetrieveOne")) {
			return MOCK_RESPONSE_PAGE0_JSON;
		} else if (getName().contains("MultiPageRetrieveAll")) {
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
				return super.getResourceFileForSSK162();
			}
		} else {
			return super.getResourceFileForSSK162();
		}
	}

	@Override
	protected Throwable getExceptionForSSK162() {
		if (getName().contains("BadRequestException")) {
			ctx.setProperty(MediationConstants.STUDIO_PROPERTY_HTTP_RESPONSE_CODE, 400);
			return new BadRequestException("{\"error\": \"invalid_grant\"}", "400");
		} else {
			return super.getExceptionForSSK162();
		}
	}
}
