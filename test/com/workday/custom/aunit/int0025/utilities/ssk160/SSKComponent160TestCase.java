package com.workday.custom.aunit.int0025.utilities.ssk160;

import com.capeclear.capeconnect.transport.BadRequestException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.MediationConstants;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent160TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_TOKEN_ENDPOINT = "inTokenEndpoint";
	public static final String PROP_PARAMETER_IN_PROVIDER = "inProvider";
	public static final String PROP_PARAMETER_IN_TENANT_ALIAS = "inTenantAlias";
	public static final String PROP_PARAMETER_IN_CLIENT_ID = "inClientId";
	public static final String PROP_PARAMETER_IN_CLIENT_SECRET = "inClientSecret";
	public static final String PROP_PARAMETER_IN_REFRESH_TOKEN = "inRefreshToken";
	
	public static final String PROP_PARAMETER_OUT_ACCESS_TOKEN = "outAccessToken160";

    public static final String VALUE_TENANT_CLIENT_ID = "abc";
    public static final String VALUE_TENANT_CLIENT_SECRET = "123";
    public static final String VALUE_TENANT_REFRESH_TOKEN = "ahhh";
    public static final String VALUE_TENANT_TOKEN_ENDPOINT = "https://wcpdev-services1.wd101.myworkday.com/ccx/oauth2/<tenant>/token";

    public static final String VALUE_EXTEND_CLIENT_ID = "xyz";
    public static final String VALUE_EXTEND_CLIENT_SECRET = "456";
    public static final String VALUE_EXTEND_TENANT_ALIAS = "testTenant";
    public static final String VALUE_EXTEND_REFRESH_TOKEN = "mmhm";
    public static final String VALUE_EXTEND_TOKEN_ENDPOINT = "https://auth.api.workday.com/v1/token";

    public static final String VALUE_PROVIDER_TENANT = "tenant";
    public static final String VALUE_PROVIDER_EXTEND = "extend";
    
    public static final String VALUE_TENANT_AUTH_HEADER = "Basic YWJjOjEyMw==";
    public static final String VALUE_EXTEND_AUTH_HEADER = "ID eHl6OjQ1Ng==";
    
    public static final String VALUE_TENANT_EXPECTED_TOKEN_ENDPOINT = "https://wcpdev-services1.wd101.myworkday.com/ccx/oauth2/testTenant/token";
    
    public static final String VALUE_TENANT_ACCESS_TOKEN_FROM_REFRESH_TOKEN = "ac09rao09svn5udbtsrh2kgdvar";
    public static final String VALUE_EXTEND_ACCESS_TOKEN_FROM_REFRESH_TOKEN = "eyJhbGciOiJSUzDUtMjAifQ.eyJpc3MiOiJDXHUwMDNkVVRldmVsb3BtZW50LE9cdTAwM2RXb3JrZGF5LENOXHUwMDNkT0NUT1BBQVMiLCJhdXRoX3RpbWUiOjE2NTMwMjE1NzEsImF1dGhfdHlwZSI6IlBhYVMiLCJzeXNfYWNjdF90eXAiOiJOIiwic2NvcGUiOnsicmVnaW9uX2ZxZG4iOiJhcGkud29ya2RheS5jb20iLCJyZWdpb24iOiJhd3M6dXMtd2VzdC0yIiwiY2xpZW50X2lkIjoiWVRVNU56Qm1ObVl0T1RCa1pTMDBZMkU0TFRsalpUY3RZemhoTlRZNE56TXlNVGxsIn0sInRva2VuVHlwZSI6IklkZW50aXR5Iiwic3ViIjoiSVNVX1NTSyIsImF1ZCI6IndkIiwiZXhwIjoxNjUzMDI1MTcxLCJpYXQiOjE2NTMwMjE1NzEsImp0aSI6IjEwc2lnN2ZjaG9jMmVmYmh6OWFiNjM3aXIxMnNwMTBxM2cybXE3Z3VhMzlsYjZ0NGU2IiwidGVuYW50Ijoid2RheWlzZV93Y3BkZXYzIn0.sBUGYE3Q7TGawGB03Mh1ZGh3mE2d4yLL75dRfhijoxXhDdQEsd2KdG9SWw0pHWkad65bnoicSs5u5WYrgEWKL0DC1JBNiIV8lMfSfCHiAbBdsl-SouC-LQdVnzt2RcF-DFrMTNyx-rs_Umlor6mKb-mXwsc63X1VNP3x46Buf3XrSeto-Y78ni4H3dalEBNqlNkC3K2PFJH2EB99O2fZtt0pErCm5Pf3JCHCzOQemyp0qOgdBHF3T0AjUIUh4Or-Ald-SG3V8hPEGaJ8WK1YmdK-_7GX5Q";
    public static final String VALUE_EXTEND_ACCESS_TOKEN_FROM_CLIENT_CREDENTIALS = "eyJhbGciOiJSUzUxMiIsImtpZCI6IjIwMjItMDUtMjAifQ.eyJpc3MiOiJDXHUwMDNkVVMsU1RcdTAwM2RDQSxbnRvbixPVVx1MDAzZERldmVsb3BtZW50LE9cdTAwM2RXb3JrZGF5LENOXHUwMDNkT0NUT1BBQVMiLCJhdXRoX3RpbWUiOjE2NTMwMjEyMzAsImF1dGhfdHlwZSI6IlBhYVMiLCJzeXNfYWNjdF90eXAiOiJFUyIsInNjb3BlIjp7InJlZ2lvbl9mcWRuIjoiYXBpLndvcmtkYXkuY29tIiwicmVnaW9uIjoiYXdzOnVzLXdlc3QtMiIsImNsaWVudF9pZCI6IllUVTVOekJtTm1ZdE9UQmtaUzAwWTJFNExUbGpaVGN0WXpoaE5UWTROek15TVRsbCJ9LCJ0b2tlblR5cGUiOiJJZGVudGl0eSIsInN1YiI6IklTVV9TU0siLCJhdWQiOiJ3ZCIsImV4cCI6MTY1MzAyNDgzMCwiaWF0IjoxNjUzMDIxMjMwLCJqdGkiOiI1cjdyejhpM3AxdTQydnppd3kxamdpbTZ3angxM2d6Y3Zta3c0eGV3ZjRyOGY4c3o0ZSIsInRlbmFudCI6IndkYXlpc2Vfd2NwZGV2MyJ9.Y4Q98KwpRD_PRkrKiLMKblD_1Onl3ThgPgdluB2vJkQAQfARppJuoeIgi4Jy5ZExrPEsYYqqtS9hyx7F11G_Qfic_Rgs_FGIC8dsEpncNu12aXrEXdiJWLABP1NuvTmGx6C7MI-cqfOsVHhU7Nv8qSPjb_3olVQVEPu-vcGM4pvWkwZLxrlPnjcZmfmdkwRNXbwTztntynFfBinV36R1wSfaEi4jZmRJGkQNx1Wu5p0ANEAnadeKmXXDoRLpypW8zNVCVgaoZtZlhi-fNuvritorqZVmNTD-JeplC9VB_MfLjd68z9FjBg";
    
    public static final String MOCK_RESPONSE_TENANT_REFRESH_TOKEN = "test/int0025/int0025160/SSK160_TENANT_RefreshToken_TokenEndpoint_Response.json";
    public static final String MOCK_RESPONSE_EXTEND_CLIENT_CREDENTIALS = "test/int0025/int0025160/SSK160_EXTEND_ClientCredentials_TokenEndpoint_Response.json";
	public static final String MOCK_RESPONSE_EXTEND_REFRESH_TOKEN = "test/int0025/int0025160/SSK160_EXTEND_RefreshToken_TokenEndpoint_Response.json";

	public static final String MOCK_RESULT_TENANT_PRE_ENCODED_AUTH = "test/int0025/int0025160/SSK160_TENANT_PreEncodedAuth.txt";
	public static final String MOCK_RESULT_EXTEND_PRE_ENCODED_AUTH = "test/int0025/int0025160/SSK160_EXTEND_PreEncodedAuth.txt";

	public static final String MOCK_RESULT_TENANT_ENCODED_AUTH = "test/int0025/int0025160/SSK160_TENANT_EncodedAuth.txt";
	public static final String MOCK_RESULT_EXTEND_ENCODED_AUTH = "test/int0025/int0025160/SSK160_EXTEND_EncodedAuth.txt";

	public static final String MOCK_RESULT_TENANT_BODY_REFRESH_TOKEN = "test/int0025/int0025160/SSK160_TENANT_Body_RefreshToken.txt";
	public static final String MOCK_RESULT_EXTEND_BODY_REFRESH_TOKEN = "test/int0025/int0025160/SSK160_EXTEND_Body_RefreshToken.txt";
	public static final String MOCK_RESULT_EXTEND_BODY_CLIENT_CREDENTIALS = "test/int0025/int0025160/SSK160_EXTEND_Body_ClientCredentials.txt";

	public static final String MOCK_RESULT_BAD_REQUEST_EXCEPTION = "test/int0025/int0025160/SSK160_HttpResponse_BadRequestException.json";

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
		ctx.setProperty(PROP_PARAMETER_IN_DEBUG_MODE, true);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="GetAccessToken")
	public void testTenantHappyPath() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, VALUE_PROVIDER_TENANT);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_ID, VALUE_TENANT_CLIENT_ID);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_SECRET, VALUE_TENANT_CLIENT_SECRET);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_REFRESH_TOKEN, VALUE_TENANT_REFRESH_TOKEN);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_TOKEN, VALUE_TENANT_TOKEN_ENDPOINT);
	}

	@UnitTest(startComponent="GetAccessToken")
	public void testTenantNoRefreshTokenError() throws Throwable {
		expectUnhandledException();
	}

	@UnitTest(startComponent="GetAccessToken")
	public void testTenantBadRequestExceptionError() throws Throwable {
		expectHandledException();

		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, VALUE_PROVIDER_TENANT);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_ID, VALUE_TENANT_CLIENT_ID);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_SECRET, VALUE_TENANT_CLIENT_SECRET);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_REFRESH_TOKEN, VALUE_TENANT_REFRESH_TOKEN);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_TOKEN, VALUE_TENANT_TOKEN_ENDPOINT);
	}

	@UnitTest(startComponent="GetAccessToken")
	public void testExtendHappyPathClientCredentials() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, VALUE_PROVIDER_EXTEND);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_ID, VALUE_EXTEND_CLIENT_ID);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_SECRET, VALUE_EXTEND_CLIENT_SECRET);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_TENANT_ALIAS, VALUE_EXTEND_TENANT_ALIAS);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_TOKEN, VALUE_EXTEND_TOKEN_ENDPOINT);
	}

	@UnitTest(startComponent="GetAccessToken")
	public void testExtendHappyPathRefreshToken() throws Throwable {
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, VALUE_PROVIDER_EXTEND);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_ID, VALUE_EXTEND_CLIENT_ID);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_SECRET, VALUE_EXTEND_CLIENT_SECRET);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_TENANT_ALIAS, VALUE_EXTEND_TENANT_ALIAS);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_REFRESH_TOKEN, VALUE_EXTEND_REFRESH_TOKEN);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_TOKEN, VALUE_EXTEND_TOKEN_ENDPOINT);
	}

	@UnitTest(startComponent="GetAccessToken")
	public void testExtendNoTenantAliasError() throws Throwable {
		expectUnhandledException();
	}

	@UnitTest(startComponent="GetAccessToken")
	public void testExtendBadRequestExceptionError() throws Throwable {
		expectHandledException();
		
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_PROVIDER, VALUE_PROVIDER_EXTEND);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_ID, VALUE_EXTEND_CLIENT_ID);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_CLIENT_SECRET, VALUE_EXTEND_CLIENT_SECRET);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_TENANT_ALIAS, VALUE_EXTEND_TENANT_ALIAS);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_REFRESH_TOKEN, VALUE_EXTEND_REFRESH_TOKEN);
		ctx.setProperty(MediationConstants.PROPERTY_SSK_REST_ENDPOINT_TOKEN, VALUE_EXTEND_TOKEN_ENDPOINT);
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
		Object tokenProperty;
		String tokenValue;

		if ("testTenantHappyPath".equalsIgnoreCase(getName())) {
			tokenProperty = ctx.getProperty(PROP_PARAMETER_OUT_ACCESS_TOKEN);
			assertNotNull(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_ACCESS_TOKEN, VALUE_TENANT_ACCESS_TOKEN_FROM_REFRESH_TOKEN), tokenProperty);
			
			tokenValue = String.valueOf(tokenProperty);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ACCESS_TOKEN), VALUE_TENANT_ACCESS_TOKEN_FROM_REFRESH_TOKEN, tokenValue);
		} else if ("testExtendHappyPathClientCredentials".equalsIgnoreCase(getName())) {
			tokenProperty = ctx.getProperty(PROP_PARAMETER_OUT_ACCESS_TOKEN);
			assertNotNull(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_ACCESS_TOKEN, VALUE_EXTEND_ACCESS_TOKEN_FROM_CLIENT_CREDENTIALS), tokenProperty);
			
			tokenValue = String.valueOf(tokenProperty);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ACCESS_TOKEN), VALUE_EXTEND_ACCESS_TOKEN_FROM_CLIENT_CREDENTIALS, tokenValue);
		} else if ("testExtendHappyPathRefreshToken".equalsIgnoreCase(getName())) {
			tokenProperty = ctx.getProperty(PROP_PARAMETER_OUT_ACCESS_TOKEN);
			assertNotNull(String.format(MESSAGE_EXPECTED_VALUE_MISSING, PROP_PARAMETER_OUT_ACCESS_TOKEN, VALUE_EXTEND_ACCESS_TOKEN_FROM_REFRESH_TOKEN), tokenProperty);
			
			tokenValue = String.valueOf(tokenProperty);
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ACCESS_TOKEN), VALUE_EXTEND_ACCESS_TOKEN_FROM_REFRESH_TOKEN, tokenValue);
		} else if (getName().contains("BadRequestException")) {
			tokenProperty = ctx.getProperty(PROP_PARAMETER_OUT_ACCESS_TOKEN);
			assertNull(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, PROP_PARAMETER_OUT_ACCESS_TOKEN), tokenProperty);

			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, MediationConstants.STUDIO_PROPERTY_HTTP_RESPONSE_CODE), 400, ctx.getProperty(MediationConstants.STUDIO_PROPERTY_HTTP_RESPONSE_CODE));
		} else if (getName().endsWith("Error")) {
			tokenProperty = ctx.getProperty(PROP_PARAMETER_OUT_ACCESS_TOKEN);
			assertNull(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, PROP_PARAMETER_OUT_ACCESS_TOKEN), tokenProperty);
		}
	}
	
	@AssertAfter(id="InitializeAndFinalize_160", step="WritePreEncodedAuthHeader")
	public void verifyPreEncodedAuth() throws Exception {
		switch (getName()) {
			case "testTenantHappyPath" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_TENANT_PRE_ENCODED_AUTH), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_TENANT_PRE_ENCODED_AUTH, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
				
			case "testExtendHappyPathClientCredentials" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_EXTEND_PRE_ENCODED_AUTH), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_EXTEND_PRE_ENCODED_AUTH, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
				
			case "testExtendHappyPathRefreshToken" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_EXTEND_PRE_ENCODED_AUTH), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_EXTEND_PRE_ENCODED_AUTH, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
				
			default :
				if (!getName().endsWith("Error")) {
					fail(String.format(MESSAGE_TEST_MISSING_VALIDATION, getName()));
				}
				break;
		}
	}
	
	@AssertAfter(id="InitializeAndFinalize_160", step="EncodeAuthHeader")
	public void verifyEncodedAuth() throws Exception {
		switch (getName()) {
			case "testTenantHappyPath" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_TENANT_ENCODED_AUTH), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_TENANT_ENCODED_AUTH, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
				
			case "testExtendHappyPathClientCredentials" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_EXTEND_ENCODED_AUTH), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_EXTEND_ENCODED_AUTH, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
				
			case "testExtendHappyPathRefreshToken" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_EXTEND_ENCODED_AUTH), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_EXTEND_ENCODED_AUTH, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
				
			default :
				if (!getName().endsWith("Error")) {
					fail(String.format(MESSAGE_TEST_MISSING_VALIDATION, getName()));
				}
				break;
		}
	}
	
	@AssertAfter(id="InitializeAndFinalize_160", step="SetHeaders")
	public void verifyEncodedHeader() throws Exception {
		switch (getName()) {
			case "testTenantHappyPath" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "Authorization header"), VALUE_TENANT_AUTH_HEADER, ctx.getMessage().getHeader("Authorization"));
				break;
				
			case "testExtendHappyPathClientCredentials" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "Authorization header"), VALUE_EXTEND_AUTH_HEADER, ctx.getMessage().getHeader("Authorization"));
				break;
				
			case "testExtendHappyPathRefreshToken" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "Authorization header"), VALUE_EXTEND_AUTH_HEADER, ctx.getMessage().getHeader("Authorization"));
				break;
				
			default :
				if (!getName().endsWith("Error")) {
					fail(String.format(MESSAGE_TEST_MISSING_VALIDATION, getName()));
				}
				break;
		}
	}
	
	@AssertAfter(id="InitializeAndFinalize_160", step="WriteMessageBodyClientCredentials")
	public void verifyBodyClientCredentials() throws Exception {
		switch (getName()) {
			case "testExtendHappyPathClientCredentials" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_EXTEND_BODY_CLIENT_CREDENTIALS), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_EXTEND_BODY_CLIENT_CREDENTIALS, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
			default :
				if (!getName().contains("Exception")) {
					fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				}
				break;
		}
	}
	
	@AssertAfter(id="InitializeAndFinalize_160", step="WriteMessageBodyRefreshToken")
	public void verifyBodyRefreshToken() throws Exception {
		switch (getName()) {
			case "testTenantHappyPath" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_TENANT_BODY_REFRESH_TOKEN), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_TENANT_BODY_REFRESH_TOKEN, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
				
			case "testExtendHappyPathRefreshToken" :
				assertTrue(String.format(MESSAGE_ROOT_UNEXPECTED_VALUE, MOCK_RESULT_EXTEND_BODY_REFRESH_TOKEN), 
						compareAgainstMessageRoot(CONTENT_TYPE_TEXT_PLAIN, MOCK_RESULT_EXTEND_BODY_REFRESH_TOKEN, CONTENT_TYPE_TEXT_PLAIN, Comparator.text));
				break;
				
			default :
				if (!getName().contains("Exception")) {
					fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				}
				break;
		}
	}
	
	@AssertAfter(id="TokenRequestCatch_160", step="InitValues")
	public void verifyTokenEndpoint() throws Exception {
		String actualValue = (String)ctx.getProperty(PROP_PARAMETER_IN_TOKEN_ENDPOINT);
		
		switch (getName()) {
			case "testTenantHappyPath" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_TOKEN_ENDPOINT), VALUE_TENANT_EXPECTED_TOKEN_ENDPOINT, actualValue);
				break;
				
			case "testExtendHappyPathClientCredentials" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_TOKEN_ENDPOINT), VALUE_EXTEND_TOKEN_ENDPOINT, actualValue);
				break;
				
			case "testExtendHappyPathRefreshToken" :
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_IN_TOKEN_ENDPOINT), VALUE_EXTEND_TOKEN_ENDPOINT, actualValue);
				break;
				
			default :
				if (!getName().contains("Exception")) {
					fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
				}
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
	protected String getResourceFileForSSK160() {
		if (getName().contains("BadRequestException")) {
			return MOCK_RESULT_BAD_REQUEST_EXCEPTION;
		} else if (getName().contains("Tenant")) {
			return MOCK_RESPONSE_TENANT_REFRESH_TOKEN;
		} else if (getName().contains("ClientCredentials")) {
			return MOCK_RESPONSE_EXTEND_CLIENT_CREDENTIALS;
		} else if (getName().contains("RefreshToken")) {
			return MOCK_RESPONSE_EXTEND_REFRESH_TOKEN;
		} else {
			return super.getResourceFileForSSK160();
		}
	}

	@Override
	protected Throwable getExceptionForSSK160() {
		if (getName().contains("BadRequestException")) {
			ctx.setProperty(MediationConstants.STUDIO_PROPERTY_HTTP_RESPONSE_CODE, 400);
			return new BadRequestException("{\"error\": \"invalid_grant\"}", "400");
		} else {
			return super.getExceptionForSSK160();
		}
	}
}
