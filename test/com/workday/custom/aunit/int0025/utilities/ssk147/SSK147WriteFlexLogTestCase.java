package com.workday.custom.aunit.int0025.utilities.ssk147;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.MediationConstants;
import com.workday.custom.int0025.ssk147.FlexLogBean;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSK147WriteFlexLogTestCase extends UtilitiesTestCase {
	private Logger log = LogControl.getLogger(getClass());

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
	
	public static final String LOG1_HEADER = "Timestamp,Severity,Summary,Details";
	public static final String LOG2_HEADER = "Timestamp,Severity,Summary,B Details,C Details,D Details";
	
	public static final String LOG1_ENTRY = ",INFO,Succinct Summary,Useful details";
	public static final String LOG2_ENTRY = ",WARN,data for position A,data for position B,data for position C,data for position D";
	
	public static final String XML_CONFIGURATION = "test/int0025/int0025147/SSK147_Configuration_WriteTests.xml";
	
//	private FlexLogBean bean;

//	@Override
//	protected void setUp() throws Exception {
//		super.setUp();
//		
//		setVariable(VAR_CONFIG, XML_CONFIGURATION, CONTENT_TYPE_TEXT_XML);
//		bean = new FlexLogBean();
//
//		try {
//			bean.configureNewLog(ctx);
//		} catch (Throwable t) {
//			log.critical("Setup Exception!", t);
//			throw new Exception(t);
//		}
//	}
//	
//	@Override
//	protected void tearDown() throws Exception {
//		super.tearDown();
//		
//		ctx.removeProperty(PROP_PARAMETER_IN_BEAN);
//		bean = null;
//	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="CreateFlexLogEntry")
	public void testLog1AllProperties() {
//		ctx.setProperty(PROP_PARAMETER_IN_BEAN, bean);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_LOG1);
		
		ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, "INFO");
		
		ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_MESSAGE, "Succinct Summary");
		ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_DETAIL, "Useful details");
	}
	
	@Override
	protected String getFlexLogConfigurationFile() {
		return XML_CONFIGURATION;
	}

	@UnitTest(startComponent="CreateFlexLogEntry")
	public void testLog2AllProperties() {
//		ctx.setProperty(PROP_PARAMETER_IN_BEAN, bean);
		ctx.setProperty(PROP_PARAMETER_IN_LOG_NAME, VALUE_LOG2);
		
		ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_LEVEL, "WARN");
		
		ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_A, "data for position A");
		ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_B, "data for position B");
		ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_C, "data for position C");
		ctx.setProperty(PROP_UPSTREAM_PARAMETER_IN_D, "data for position D");
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
		try {
			BufferedReader reader = null;
			String entry = null;
			String remainder = null;
			
			FlexLogBean bean = (FlexLogBean)ctx.getProperty(MediationConstants.PROPERTY_SSK_FLEX_LOG);
			
			switch (getName()) {
				case "testLog1AllProperties" :
					reader = new BufferedReader(new InputStreamReader(bean.getFlexLog(VALUE_LOG1).getLogData().getInputStream()));

					entry = reader.readLine();
					assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, "log header line"), entry);
					assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log header line"), entry.endsWith(LOG1_HEADER));
					
					entry = reader.readLine();
					assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, "log entry line"), entry);
					assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log entry line"), entry.endsWith(LOG1_ENTRY));
					
					remainder = reader.readLine();
					assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log after entry"), remainder);					
					break;
					
				case "testLog2AllProperties" :
					reader = new BufferedReader(new InputStreamReader(bean.getFlexLog(VALUE_LOG2).getLogData().getInputStream()));

					entry = reader.readLine();
					assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, "log header line"), entry);
					assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log header line"), entry.endsWith(LOG2_HEADER));

					entry = reader.readLine();
					assertNotNull(String.format(MESSAGE_UNEXPECTED_NULL, "log entry line"), entry);
					assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log entry line"), entry.endsWith(LOG2_ENTRY));
					
					remainder = reader.readLine();
					assertNull(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log after entry"), remainder);					
					break;
					
				case "" :
					break;
					
				default :
					fail(String.format(MESSAGE_UNEXPECTED_EXECUTION_PATH, getName()));
					break;
			}
		} catch (Throwable t2) {
			log.fatal("Exception raised in final verification.", t2);
			throw new Exception(t2);
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
