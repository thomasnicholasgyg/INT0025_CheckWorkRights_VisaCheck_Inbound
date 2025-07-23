package com.workday.custom.aunit.int0025.utilities.ssk149;

import java.util.HashSet;

import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSK149InitializeReportDiffTestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_LOG_TARGET = "inLogTarget";
	public static final String PROP_PARAMETER_IN_ABORT_ON_ERROR = "inIsAbortOnError";
	
	public static final String PROP_PARAMETER_OUT_HISTORIC_IDS = "outPropertyNameHistoricIdList149";
	public static final String PROP_PARAMETER_OUT_CURRENT_IDS = "outPropertyNameCurrentIdList149";
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		ctx.setProperty(PROP_PARAMETER_IN_LOG_TARGET, "primary");
		ctx.setProperty(PROP_PARAMETER_IN_ABORT_ON_ERROR, true);
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="InitializeReportDiff")
	public void testReportDiff() throws Exception {

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
		Object h = ctx.getProperty(PROP_PARAMETER_OUT_HISTORIC_IDS);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, PROP_PARAMETER_OUT_HISTORIC_IDS), h);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_PARAMETER_OUT_HISTORIC_IDS), h instanceof HashSet<?>);
		assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_HISTORIC_IDS), ((HashSet<?>)h).isEmpty());
		
		Object c = ctx.getProperty(PROP_PARAMETER_OUT_CURRENT_IDS);
		assertNotNull(String.format(MESSAGE_UNEXPECTED_PROPERTY_PRESENT, PROP_PARAMETER_OUT_CURRENT_IDS), c);
		assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_PARAMETER_OUT_CURRENT_IDS), c instanceof HashSet<?>);
		assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_CURRENT_IDS), ((HashSet<?>)c).isEmpty());
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/

}
