package com.workday.custom.aunit.int0025.main;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class MainTestCase extends UtilitiesTestCase {

	/*
	 * The setUp method overrides and extends the behavior of the method from its parent SSK, AUnit and JUnit classes.
	 * 
	 * This method will execute all of the parent behaviors, plus it will use SSK helpers to set context properties that 
	 * would have been set had the execution began at the workday-in and run through SSK109.
	 * 
	 * Any initialization that should occur before every @UnitTest entry point in this class should be added here.
	 * 
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setupPrimaryLogInitialization();
		setupGlobalInitialization();
		setupDebugValidations();
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	/**
	 * This AUnit test method will begin execution at the "Main" local-in.
	 * 
	 * When execution begins at this point, all of the SSK initialization from Launch Parameters and Integration Attributes will have been skipped.
	 * As a result, the test itself will need to initialize the relevant context properties on behalf of SSK109.  The <code>setUp</code> method 
	 * will call utility methods from the CommonTestCase to handle SSK values.  Integration-specific properties should be added to the end of the
	 * <code>setUp</code> method.
	 * 
	 * @throws Exception
	 */
	@UnitTest(startComponent="Main")
	public void testMain() throws Exception {
		
	}
	
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
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/



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
