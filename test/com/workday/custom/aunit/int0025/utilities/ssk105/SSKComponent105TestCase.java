package com.workday.custom.aunit.int0025.utilities.ssk105;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.capeclear.mediation.MediationException;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent105TestCase extends UtilitiesTestCase {

	public static final String VAR_LOCAL_XSLT = "localXslt105";
	
	public static final String PROP_PARAMETER_IN_MAP = "inMap";
	public static final String PROP_PARAMETER_IN_STORE_DATA_AS = "inStoreDataAs";
	public static final String PROP_PARAMETER_IN_ENTRY_QUERY = "inQueryToEntry";
	public static final String PROP_PARAMETER_IN_ENTRY_FILTER = "inEntryFilter";
	public static final String PROP_PARAMETER_IN_KEY_QUERY = "inQueryToKey";
	public static final String PROP_PARAMETER_IN_VALUE_QUERY = "inQueryToValue";
	public static final String PROP_PARAMETER_IN_IS_EMPTY_VALUE_ERROR = "inEmptyValueIsError";
	public static final String PROP_PARAMETER_IN_ABORT_ERROR = "inIsAbortOnError";
	public static final String PROP_PARAMETER_OUT_MAP = "outMap";
	
	public static final String PROP_LOCAL_MAP_NAME = "localMapPropertyName";

	public static final String MOCK_XML_SSK105_REPORT = "test/int0025/int0025105/SSK105_XML_Report.xml";
	public static final String MOCK_XML_SSK105_REPORT_SECONDARY_OBJECTS = "test/int0025/int0025105/SSK105_XML_Report_SecondaryObjects.xml";
	public static final String MOCK_XML_SSK105_EMPTY_VALUES = "test/int0025/int0025105/SSK105_XML_EmptyValues.xml";
	public static final String MOCK_XSLT_SIMPLE = "test/int0025/int0025105/SSK105_XSLT_SimpleQuery.xml";
	public static final String MOCK_XSLT_FILTER = "test/int0025/int0025105/SSK105_XSLT_FilterQuery.xml";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupPrimaryLogInitialization();
		setupGlobalInitialization();
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="PopulateJavaMap")
	public void testError10500() throws Exception {
		expectHandledException();
		
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_QUERY, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_KEY_QUERY, "*:External_ID");
		ctx.setProperty(PROP_PARAMETER_IN_VALUE_QUERY, "*:InternalID");
		ctx.setProperty(PROP_PARAMETER_IN_MAP, "Not a map");
		
		setMessagePart(0, MOCK_XML_SSK105_REPORT, CONTENT_TYPE_TEXT_XML);
	}
	
	@SuppressWarnings("rawtypes")
	@UnitTest(startComponent="PopulateJavaMap")
	public void testSimpleXML() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_QUERY, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_KEY_QUERY, "*:External_ID");
		ctx.setProperty(PROP_PARAMETER_IN_VALUE_QUERY, "*:Employee_ID");
		ctx.setProperty(PROP_PARAMETER_IN_MAP, new java.util.TreeMap());
		
		setMessagePart(0, MOCK_XML_SSK105_REPORT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="PopulateJavaMap")
	public void testSimpleXMLCreateMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_QUERY, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_KEY_QUERY, "*:External_ID");
		ctx.setProperty(PROP_PARAMETER_IN_VALUE_QUERY, "*:Employee_ID");
		
		setMessagePart(0, MOCK_XML_SSK105_REPORT, CONTENT_TYPE_TEXT_XML);
	}
	
	@SuppressWarnings("rawtypes")
	@UnitTest(startComponent="PopulateJavaMap")
	public void testEntryFilter() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_QUERY, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_KEY_QUERY, "*:External_ID");
		ctx.setProperty(PROP_PARAMETER_IN_VALUE_QUERY, "*:Employee_ID");
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_FILTER, "xs:boolean(*:Eligible)");
		ctx.setProperty(PROP_PARAMETER_IN_MAP, new java.util.TreeMap());
		
		setMessagePart(0, MOCK_XML_SSK105_REPORT, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="PopulateJavaMap")
	public void testMapXmlAsString() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_QUERY, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_KEY_QUERY, "*:Employee_ID");
		ctx.setProperty(PROP_PARAMETER_IN_VALUE_QUERY, "*:Other_References");
		ctx.setProperty(PROP_PARAMETER_IN_STORE_DATA_AS, "XMLString");
		
		setMessagePart(0, MOCK_XML_SSK105_REPORT_SECONDARY_OBJECTS, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="PopulateJavaMap")
	public void testMapEmptyValuesOK() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_QUERY, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_KEY_QUERY, "*:Employee_ID");
		ctx.setProperty(PROP_PARAMETER_IN_VALUE_QUERY, "*:External_ID");
		ctx.setProperty(PROP_PARAMETER_IN_STORE_DATA_AS, "XMLString");
		ctx.setProperty(PROP_PARAMETER_IN_IS_EMPTY_VALUE_ERROR, false);
		
		setMessagePart(0, MOCK_XML_SSK105_EMPTY_VALUES, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="PopulateJavaMap")
	public void testMapEmptyValuesError() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_ENTRY_QUERY, "/*/*");
		ctx.setProperty(PROP_PARAMETER_IN_KEY_QUERY, "*:Employee_ID");
		ctx.setProperty(PROP_PARAMETER_IN_VALUE_QUERY, "*:External_ID");
		ctx.setProperty(PROP_PARAMETER_IN_STORE_DATA_AS, "XMLString");
		ctx.setProperty(PROP_PARAMETER_IN_IS_EMPTY_VALUE_ERROR, true);
		
		setMessagePart(0, MOCK_XML_SSK105_EMPTY_VALUES, CONTENT_TYPE_TEXT_XML);
	}
	
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Validations
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@SuppressWarnings("rawtypes")
	@Override
	protected void extendedExitStateVerification(Throwable t) throws Exception {
		if (!testsExpectingHandledExceptions.contains(getName())) {
			Map m = null;
			
			if ("testSimpleXML".equalsIgnoreCase(getName()) ||
					"testSimpleXMLCreateMap".equalsIgnoreCase(getName())) {
				m = (Map)ctx.getProperty(PROP_PARAMETER_OUT_MAP);
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP), 3, m.size());
				
				for (int id = 1; id <= 3; id++) {
					String idString = Integer.toString(id);
					String employeeId = Integer.toString(21000 + id);
					
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP + "@" + idString), employeeId, m.get(idString));
				}
			} else if ("testEntryFilter".equalsIgnoreCase(getName())) {
				m = (Map)ctx.getProperty(PROP_PARAMETER_OUT_MAP);
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP), 2, m.size());
				
				int ids[] = {1,3};
				for (int x = 0; x < ids.length; x++) {
					String idString = Integer.toString(ids[x]);
					String employeeId = Integer.toString(21000 + ids[x]);
					
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP + "@" + idString), employeeId, m.get(idString));
				}
			} else if ("testMapXmlAsString".equalsIgnoreCase(getName())) {
				m = (Map)ctx.getProperty(PROP_PARAMETER_OUT_MAP);
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP), 3, m.size());
				
				for (int id = 1; id <= 3; id++) {
					String employeeId = Integer.toString(21000 + id);
					String mapValue = String.valueOf(m.get(employeeId));
					
					String xpath = "/*:Other_References/*:Other_Reference[*:ID[@*:type='Some_ID']='" + id + "0']/*:ID[@*:type='WID']/text()";
					assertEqualsXPath(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP + "@" + employeeId), getExpectedWID(id), xpath, parseStringToXML(prependPrologToXmlString(mapValue)));
				}
			} else if ("testMapEmptyValuesOK".equalsIgnoreCase(getName())) {
				m = (Map)ctx.getProperty(PROP_PARAMETER_OUT_MAP);
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP), 4, m.size());
				
				for (int id = 1; id <= 4; id++) {
					String employeeId = Integer.toString(21000 + id);
					String mapValue = String.valueOf(m.get(employeeId));
					
					if (id == 4) {
						assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP + "@" + employeeId), StringUtils.isBlank(mapValue));
					} else {
						assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP + "@" + employeeId), mapValue, m.get(employeeId));
					}
				}
			} else if ("testMapEmptyValuesError".equalsIgnoreCase(getName())) {
				m = (Map)ctx.getProperty(PROP_PARAMETER_OUT_MAP);
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP), 3, m.size());
				
				for (int id = 1; id <= 3; id++) {
					String employeeId = Integer.toString(21000 + id);
					String mapValue = String.valueOf(m.get(employeeId));
					
					assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_MAP + "@" + employeeId), mapValue, m.get(employeeId));
				}
				
				assertPrimaryCloudLogEntryHTML(
						"error", 
						"Key has no value while loading Java Map", 
						"No matching value found in record at *:External_ID.  See additional details for the specific record.", 
						"21004", 
						"LoadMap_105", 
						"10504", 
						"", 
						"<r:Report_Entry xmlns:r=\\\"urn:report\\\"> <r:Employee_ID>21004</r:Employee_ID> <r:Eligible>0</r:Eligible> </r:Report_Entry>");
			}
		}
	}
	
	private String getExpectedWID(int id) {
		switch (id) {
			case 1:
				return VALUE_WID_20;
			case 2:
				return VALUE_WID_22;
			case 3:
				return VALUE_WID_24;
			default:
				return null;
		}
	}

	@Override
	protected void unhandledExceptionVerification(Throwable t) throws Exception {
		if (!testsExpectingHandledExceptions.contains(getName())) {
			super.unhandledExceptionVerification(t);
		} else {
			assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, "exception"), t instanceof MediationException);
			
			if ("testError10500".equalsIgnoreCase(getName())) {
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "error code"), 10500, ((MediationException)t).getErrorNumber());
			}
		}
	}
	
	@AssertAfter(id="PrepareForLoadMap_105")
	public void verifyXslt() throws Exception {
		if ("testSimpleXML".equalsIgnoreCase(getName()) ||
				"testSimpleXMLCreateMap".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_XSLT, MOCK_XSLT_SIMPLE),
					compareAgainstVariable(VAR_LOCAL_XSLT, CONTENT_TYPE_TEXT_XML, MOCK_XSLT_SIMPLE, CONTENT_TYPE_TEXT_XML));
		} else if ("testEntryFilter".equalsIgnoreCase(getName())) {
			assertTrue(String.format(MESSAGE_VARIABLE_UNEXPECTED_VALUE, VAR_LOCAL_XSLT, MOCK_XSLT_FILTER),
					compareAgainstVariable(VAR_LOCAL_XSLT, CONTENT_TYPE_TEXT_XML, MOCK_XSLT_FILTER, CONTENT_TYPE_TEXT_XML));
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
