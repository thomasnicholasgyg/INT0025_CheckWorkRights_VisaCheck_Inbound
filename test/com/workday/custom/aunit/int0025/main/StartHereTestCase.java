package com.workday.custom.aunit.int0025.main;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.CommonTestCase;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

/**
 * Studio StarterKit AUnit test class to support full integration testing.
 * 
 * Steps to make this work for your integration:
 * <ol>
 * 	<li>Configure your <code>StartHere</code> Workday-in for your Integration Services and Launch Parameters.</li>
 * 	<li>Deploy your integration to your tenant and configure any services in the tenant.  This is easier than editing the XML directly!</li>
 * 	<li>Get the Reference ID of your integration from the tenant and copy it.</li>
 * 	<li>Use the WS Tester to retrieve the integration configuration from the tenant.  A sample is provided in /ws/test/int0025/SSK_INTSYS__Get_Integration_Systems_Request.xml.  Use the Reference ID you noted above.</li>
 * 	<li>Copy and paste the response into /ws/test/StartHereTestCase_INTSYS.xml.</li>
 * 	<li>Use the WS Tester to retrieve the launch parameter data from the tenant.  A sample is provided in /ws/test/int0025/SSK_LP__Launch_Integration_Event_Request.xml.  Use the Reference ID you noted above.  If you've added launch parameters to your integration, you may have to add more elements to the request.  The AUnit Implementer's Guide on Community can help with this.</li>
 * 	<li>Copy and paste the response into /ws/test/StartHereTestCase_LP.xml.</li>
 * 	<li>Uncomment <code>private static final String MOCK_INTEGRATION_SYSTEM = "test/StartHereTestCase_INTSYS.xml";</code></li>
 * 	<li>Comment <code>private static final String MOCK_INTEGRATION_SYSTEM = CommonTestCase.MOCK_INITIALIZATION_SSK_INTEGRATION_SYSTEM;</code></li>
 * 	<li>Uncomment <code>private static final String MOCK_INTEGRATION_EVENT = "test/StartHereTestCase_LP.xml";</code></li>
 * 	<li>Comment <code>private static final String MOCK_INTEGRATION_EVENT = CommonTestCase.MOCK_INITIALIZATION_SSK_EVENT_LAUNCH;</code></li>
 * 	<li>You should be able to run the <code>StartHereTestCase.testStartHere</code> test successfully now.</li>
 * 	<li>Customize and extend the test according to your requirements and needs.  Happy coding!</li>
 * </ol>
 * 
 * It is possible to test other configurations as well, e.g. different Launch Parameters.  To do so, create a copy of your 
 * StartHereTestCase_LP.xml file and make the desired LP additions/changes.  Then create a new test entry method.  This can
 * be done by copying and pasting the <code>testStartHere</code> method (including its <code>UnitTest</code> annotation)
 * and giving it a new name such as "testAlternateLP".  Then you can change the <code>getFileForSetupIntegrationLaunchEvent</code> method to something like:
 * <pre>
 * {@code
 *  protected String getFileForSetupIntegrationLaunchEvent() {
 *  	String returnValue = null;
 *  
 *  	switch (getName()) {
 *  		case "testAlternateLP":
 *  			returnValue = "test/OtherLPs.xml";
 *  			break;
 *  		default:
 *  			returnValue = MOCK_INTEGRATION_EVENT;
 *  			break;
 *		}
 *
 *		return returnValue;
 *	}
 * }
 * 
 * @author john.smail
 *
 */
@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class StartHereTestCase extends UtilitiesTestCase {
	
	/**
	 * <pre>
	 * Set this value with a file path relative to the /ws directory that provides INTSYS initialization support.
	 * 
	 * This can be obtained from the Integrations / Get_Integration_Systems web service response.  A sample request is provided in
	 * /INT0025_CheckWorkRights_VisaCheck_Inbound/ws/test/int0025/SSK_INTSYS_SOAP__Get_Integration_Systems_Request.xml, although you may need to update the
	 * wd:version of the request to match that of the WSDL for the service version being used.  Note that through use of the 
	 * SetupAssistant, if SSK naming conventions have been followed, then the sample request is ready to be executed.   
	 * 
	 * A default, empty file is already provided and wired-up where you can simply copy-and-paste the WWS Response.
	 * 
	 * Uncomment the line that uses the test/StartHereTestCase_INTSYS.xml file.
	 * 
	 * Comment out the line that uses CommonTestCase.MOCK_INITIALIZATION_SSK_INTEGRATION_SYSTEM.  This is here just
	 * to avoid false errors when maintaining the Configuration Catalog solution.
	 * </pre>
	 */
//	private static final String MOCK_INTEGRATION_SYSTEM = "test/StartHereTestCase_INTSYS.xml";
	private static final String MOCK_INTEGRATION_SYSTEM = CommonTestCase.MOCK_INITIALIZATION_SSK_INTEGRATION_SYSTEM;
	
	/**
	 * <pre>
	 * Set this value with a file path relative to the /ws directory that provides LP initialization support.
	 * 
	 * This can be obtained from the Integrations / Launch_Integration web service response.  A sample request is provided in
	 * /INT0025_CheckWorkRights_VisaCheck_Inbound/ws/test/int0025/SSK_LP_SOAP__Launch_Integration_Event_Request.xml, although you may need to update the
	 * wd:version of the request to match that of the WSDL for the service version being used.  Note that through use of the 
	 * SetupAssistant, if SSK naming conventions have been followed, then the sample request is ready to be executed.  If any 
	 * additional Launch Parameters have been added to the integration, then they will also need to be added to the sample launch
	 * request.  The following are examples of wd:Integration_Launch_Parameter_Data elements that can be added to the request
	 * for each data type parameter option.  Example values and placeholders such as "Your Parameter Name", "Your Parameter Value",
	 * and "YourEnumTypeName" are used below.  Update these accordingly for your integration configuration.
	 *  
	 * Text
	 * {@code
	 *   <wd:Integration_Launch_Parameter_Data>
	 *   <wd:Launch_Parameter_Reference>
	 *     <wd:ID wd:parent_id="INT0025_CheckWorkRights_VisaCheck_Inbound_Collection/INT0025_CheckWorkRights_VisaCheck_Inbound/StartHere" wd:parent_type="Integration_System_ID" wd:type="Launch_Parameter_Name">Your Parameter Name</wd:ID>
	 *   </wd:Launch_Parameter_Reference>
	 *   <wd:Launch_Parameter_Value_Data>
	 *     <wd:Text>Your Parameter Value</wd:Text>
	 *   </wd:Launch_Parameter_Value_Data>
	 * </wd:Integration_Launch_Parameter_Data>}
	 * 
	 * Number
	 * {@code
	 * <wd:Integration_Launch_Parameter_Data>
	 *   <wd:Launch_Parameter_Reference>
	 *     <wd:ID wd:parent_id="INT0025_CheckWorkRights_VisaCheck_Inbound_Collection/INT0025_CheckWorkRights_VisaCheck_Inbound/StartHere" wd:parent_type="Integration_System_ID" wd:type="Launch_Parameter_Name">Your Parameter Name</wd:ID>
	 *   </wd:Launch_Parameter_Reference>
	 *   <wd:Launch_Parameter_Value_Data>
	 *     <wd:Numeric>501</wd:Numeric>
	 *   </wd:Launch_Parameter_Value_Data>
	 * </wd:Integration_Launch_Parameter_Data>}
	 *
	 * DateTime
	 * {@code
	 * <wd:Integration_Launch_Parameter_Data>
	 *   <wd:Launch_Parameter_Reference>
	 *     <wd:ID wd:parent_id="INT0025_CheckWorkRights_VisaCheck_Inbound_Collection/INT0025_CheckWorkRights_VisaCheck_Inbound/StartHere" wd:parent_type="Integration_System_ID" wd:type="Launch_Parameter_Name">Your Parameter Name</wd:ID>
	 *   </wd:Launch_Parameter_Reference>
	 *   <wd:Launch_Parameter_Value_Data>
	 *     <wd:DateTime>2019-08-31T20:57:10-04:00</wd:DateTime>
	 *   </wd:Launch_Parameter_Value_Data>
	 * </wd:Integration_Launch_Parameter_Data>}
	 * 
	 * Date
	 * {@code
	 * <wd:Integration_Launch_Parameter_Data>
	 *   <wd:Launch_Parameter_Reference>
	 *     <wd:ID wd:parent_id="INT0025_CheckWorkRights_VisaCheck_Inbound_Collection/INT0025_CheckWorkRights_VisaCheck_Inbound/StartHere" wd:parent_type="Integration_System_ID" wd:type="Launch_Parameter_Name">Your Parameter Name</wd:ID>
	 *   </wd:Launch_Parameter_Reference>
	 *   <wd:Launch_Parameter_Value_Data>
	 *     <wd:Date>2019-08-31-04:00</wd:Date>
	 *   </wd:Launch_Parameter_Value_Data>
	 * </wd:Integration_Launch_Parameter_Data>}
	 * 
	 * Single Instance Reference
	 * {@code
	 * <wd:Integration_Launch_Parameter_Data>
	 *   <wd:Launch_Parameter_Reference>
	 *     <wd:ID wd:parent_id="INT0025_CheckWorkRights_VisaCheck_Inbound_Collection/INT0025_CheckWorkRights_VisaCheck_Inbound/StartHere" wd:parent_type="Integration_System_ID" wd:type="Launch_Parameter_Name">Your Parameter Name</wd:ID>
	 *   </wd:Launch_Parameter_Reference>
	 *   <wd:Launch_Parameter_Value_Data>
	 *     <wd:Instance_Reference>
	 *       <wd:ID wd:type="Employee_ID">21001</wd:ID>
	 *     </wd:Instance_Reference>
	 *   </wd:Launch_Parameter_Value_Data>
	 * </wd:Integration_Launch_Parameter_Data>}
	 * 
	 * Multi-Instance Reference
	 * {@code
	 * <wd:Integration_Launch_Parameter_Data>
	 *   <wd:Launch_Parameter_Reference>
	 *     <wd:ID wd:parent_id="INT0025_CheckWorkRights_VisaCheck_Inbound_Collection/INT0025_CheckWorkRights_VisaCheck_Inbound/StartHere" wd:parent_type="Integration_System_ID" wd:type="Launch_Parameter_Name">Your Parameter Name</wd:ID>
	 *   </wd:Launch_Parameter_Reference>
	 *   <wd:Launch_Parameter_Value_Data>
	 *     <wd:Instance_Reference>
	 *       <wd:ID wd:type="Employee_ID">21002</wd:ID>
	 *     </wd:Instance_Reference>
	 *     <wd:Instance_Reference>
	 *       <wd:ID wd:type="Employee_ID">21003</wd:ID>
	 *     </wd:Instance_Reference>
	 *     <wd:Instance_Reference>
	 *       <wd:ID wd:type="Employee_ID">21004</wd:ID>
	 *     </wd:Instance_Reference>
	 *   </wd:Launch_Parameter_Value_Data>
	 * </wd:Integration_Launch_Parameter_Data>}
	 * 
	 * Enumeration
	 * {@code
	 * <wd:Integration_Launch_Parameter_Data>
	 *   <wd:Launch_Parameter_Reference>
	 *     <wd:ID wd:parent_id="INT0025_CheckWorkRights_VisaCheck_Inbound_Collection/INT0025_CheckWorkRights_VisaCheck_Inbound/StartHere" wd:parent_type="Integration_System_ID" wd:type="Launch_Parameter_Name">Your Parameter Name</wd:ID>
	 *   </wd:Launch_Parameter_Reference>
	 *   <wd:Launch_Parameter_Value_Data>
	 *     <wd:Instance_Reference>
	 *       <wd:ID wd:parent_id="YourEnumTypeName" wd:parent_type="Integration_Enumeration_Definition_ID" wd:type="Integration_Enumeration_ID">YourEnumValue</wd:ID>
	 *     </wd:Instance_Reference>
	 *   </wd:Launch_Parameter_Value_Data>
	 * </wd:Integration_Launch_Parameter_Data>} 
	 *
	 * When working with enumeration types, "YourEnumTypeName" should be replaced with the Integration_Enumeration_Definition_ID of the enumeration.  
	 * For example, if the CloudLogOutputFileType enumeration were used as a launch parameter, then "YourEnumTypeName" would be replaced with
	 * "CloudLogOutputFileType", and "YourEnumValue" might be "HTML" (for the HTML-formatted log).  Full example is shown below:
	 * 
	 * {@code
	 * <wd:Integration_Launch_Parameter_Data>
	 *   <wd:Launch_Parameter_Reference>
	 *     <wd:ID wd:parent_id="INT0025_CheckWorkRights_VisaCheck_Inbound_Collection/INT0025_CheckWorkRights_VisaCheck_Inbound/StartHere" wd:parent_type="Integration_System_ID" wd:type="Launch_Parameter_Name">Cloud Log Output File Type</wd:ID>
	 *   </wd:Launch_Parameter_Reference>
	 *   <wd:Launch_Parameter_Value_Data>
	 *     <wd:Instance_Reference>
	 *       <wd:ID wd:parent_id="CloudLogOutputFileType" wd:parent_type="Integration_Enumeration_Definition_ID" wd:type="Integration_Enumeration_ID">HTML</wd:ID>
	 *     </wd:Instance_Reference>
	 *   </wd:Launch_Parameter_Value_Data>
	 * </wd:Integration_Launch_Parameter_Data>} 
	 *  
	 *  
	 *  
	 * A default, empty file is already provided and wired-up where you can simply copy-and-paste the WWS Response.
	 * 
	 * Uncomment the line that uses the test/StartHereTestCase_LP.xml file.
	 * 
	 * Comment out the line that uses CommonTestCase.MOCK_INITIALIZATION_SSK_EVENT_LAUNCH.  This is here just
	 * to avoid false errors when maintaining the Configuration Catalog solution.
	 * </pre>
	 */
//	private static final String MOCK_INTEGRATION_EVENT = "test/StartHereTestCase_LP.xml";
	private static final String MOCK_INTEGRATION_EVENT = CommonTestCase.MOCK_INITIALIZATION_SSK_EVENT_LAUNCH;

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	/**
	 * <pre>
	 * This AUnit test method will begin execution at the "StartHere" workday-in.
	 * 
	 * When the test starts at this point, all of the SSK initialization from Launch Parameters and Integration Attributes will be run.
	 * As a result, the sources which Studio uses to determine those values must be staged in the form of two XML documents.  To make test
	 * setup easier, we recommend that you configure your Launch Parameters, Integration Attributes and Maps, as well as other Integration 
	 * Services and do what in-tenant configuration you can before creating these XML documents described below.
	 * 
	 * The first XML document is the response from the Launch_Integration operation of the Integrations Workday Web Service.  This document
	 * will support initializing context properties related to Launch Parameters.  A document with the response should be added to the
	 * /ws/test directory, and that path set as the value of the <code>MOCK_INTEGRATION_EVENT</code> constant above.  For example, if the 
	 * file were /ws/test/Dummy_Event_Response.xml, then the variable value should be "test/Dummy_Event_Response.xml" (omitting the "/ws/" 
	 * from the path).  Note that invoking this web service will launch the integration, and the event may very well fail.  That is OK as the
	 * ending event status does not affect the response.
	 * 
	 * The second XML document is the response from the Get_Integration_Systems operation of the Integrations Workday Web Service.  This document
	 * will support initializing context properties related to Integration Attributes as well as Integration Maps and other services.  A 
	 * document with the response should be added to the /ws/test directory, and that path set as the value of the <code>MOCK_INTEGRATION_SYSTEM</code> 
	 * constant above.  For example, if the file were /ws/test/Dummy_System_Response.xml, then the variable value should be 
	 * "test/Dummy_System_Response.xml" (omitting the "/ws/" from the path).
	 * 
	 * With those 2 files in-place, the <code>setupIntegrationSystem</code> and <code>setupIntegrationLaunchEvent</code> methods below will 
	 * fully support SSK109 and the out-of-the-box initialization.
	 * 
	 * @throws Exception
	 */
	@UnitTest(startComponent="StartHere")
	public void testStartHere() throws Exception {
		//Do additional test case scenario setup/configuration here...
	}

	// Write additional @UnitTest methods here to cover additional use cases
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	/* (non-Javadoc)
	 * @see com.workday.custom.aunit.int0025.CommonTestCase#extendedExitStateVerification(java.lang.Throwable)
	 */
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception { }
	
	//Write @AssertBefore and @AssertAfter methods here to verify integration state after critical points in the code have been reached.
	@AssertAfter(id="InitializeAttributesAndLaunchParameters")
	public void verifyCustomInitialization() throws Exception {
		// This obviously doesn't do anything ... yet!
		// If you have special Launch Parameters or Integration Attributes for your integration, this is the perfect place to ensure 
		// they were initialized properly out of your integration system or event launch XML responses.

	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@Override
	protected String getFileForSetupIntegrationSystem() {
		return MOCK_INTEGRATION_SYSTEM;
	}

	@Override
	protected String getFileForSetupIntegrationLaunchEvent() {
		return MOCK_INTEGRATION_EVENT;
	}	

	// Write @AtComponent methods here to run alternative code for parts of your integration code that are dependent on the tenant or external resources.
	// You will use AUnit methods such as setMessagePart and/or setVariable to put the content that would have been returned from the external resource
	// onto either the message rootpart or into a variable, respectively.
	
	
	// Also @Override methods from the SSK's AUnit framework to give integration-specific behavior to SSK components that are dependent on the tenant.
	// When interacting with tenant resources through SSK components, there will be methods in UtilitiesTestCase that can be overridden here.  For example,
	// if using CallSoap, and there are several @UnitTest entry points, then the code that would return different XML SOAP responses per each test would
	// look something like the following:
	/*
	 * 	@Override
	 * 	protected String getResourceFileForSSK103() {
	 * 		String returnValue = super.getResourceFileForSSK103();
	 * 	
	 * 		switch (getName()) {
	 * 			case "testStartHere" :
	 * 				returnValue = "test/SOAP_Response.xml";
	 * 				break;
	 * 		
	 * 			case "testStartHereAlternate1" :
	 * 				returnValue = "test/SOAP_Response_Alternate1.xml";
	 * 				break;
	 * 		
	 * 			case "testStartHereAlternate2" :
	 * 				returnValue = "test/SOAP_Response_Alternate2.xml";
	 * 				break;
	 * 		
	 * 			default :
	 * 				break;
	 * 		}
	 * 	
	 * 		return returnValue;
	 * 	}
	 */
}
