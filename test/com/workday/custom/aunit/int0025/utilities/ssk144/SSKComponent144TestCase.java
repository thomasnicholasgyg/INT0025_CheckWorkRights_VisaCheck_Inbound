package com.workday.custom.aunit.int0025.utilities.ssk144;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.capeclear.mediation.impl.mediators.MVELUtilHelper;
import com.workday.aunit.annotations.AssemblyTest;
import com.workday.aunit.annotations.AssertAfter;
import com.workday.aunit.annotations.UnitTest;
import com.workday.custom.aunit.int0025.utilities.UtilitiesTestCase;
import com.workday.custom.int0025.ssk142.CloudLogMessage;

@AssemblyTest(project="INT0025_CheckWorkRights_VisaCheck_Inbound")
public class SSKComponent144TestCase extends UtilitiesTestCase {

	public static final String PROP_PARAMETER_IN_IS_CHILD_THREAD = "inIsChildThreadContext";
	
	public static final String VALUE_REPORT_SERVICE_ALIAS = "Worker Data";
	
	public static final String MOCK_MESSAGE_FRAGMENT = "test/int0025/int0025144/SSK144_XML_ThreadMessageFragment.txt";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupThreadLogging();
		
		mockTracker.addComponentTracking("Call_Aggregate_Finalize_144");
	}

	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Test Entry Points
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	@UnitTest(startComponent="Debug")
	public void testMessage() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMessageTargetName() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "messageRootPart");

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMessageEntityName() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_NAME, "rootpartByEntityName.xml");

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMessageEntitySuffix() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_SUFFIX, "rootpartByEntitySuffix");

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMessageEntitySuffixPath() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_SUFFIX, "xslt/componentTransform.xsl");

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testProperty() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");

		ctx.setProperty("unitTestProperty", VALUE_REPORT_SERVICE_ALIAS);
	}
	
	@UnitTest(startComponent="Debug")
	public void testProperties() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_PROPERTIES);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, PROP_GLOBAL_DEBUG_PROPERTY_LIST);

		List<String> names = new ArrayList<String>();
		names.add(PROP_GLOBAL_PRIMARYLOG_FILENAME);
		names.add(PROP_GLOBAL_PRIMARYLOG_EXPIRES);
		ctx.setProperty(PROP_GLOBAL_DEBUG_PROPERTY_LIST, names);
	}
	
	@UnitTest(startComponent="Debug")
	public void testVariable() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		
		setVariable("unitTestProperty", MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testVariableEntityName() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_NAME, "variableEntityName.xml");
		
		setVariable("unitTestProperty", MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testVariableEntitySuffix() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_SUFFIX, "variableEntityName");
		
		setVariable("unitTestProperty", MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testVariableEntitySuffixNonXml() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_SUFFIX, "variableEntityName");
		
		setVariable("unitTestProperty", MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_PLAIN);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMap() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");

		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("unitTestProperty", prompts);
	}
	
	@UnitTest(startComponent="Debug")
	public void testList() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_LIST);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");

		List<String> items = new ArrayList<String>();
		items.add("Item in position 0");
		items.add("Item in position 1");
		items.add("Item in position 2");
		ctx.setProperty("unitTestProperty", items);
	}
	
	@UnitTest(startComponent="Debug")
	public void testSet() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_SET);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");

		Set<String> items = new HashSet<String>();
		items.add("Item 0 in set");
		items.add("Item 1 in set");
		items.add("Item 2 in set");
		ctx.setProperty("unitTestProperty", items);
	}
	
	@UnitTest(startComponent="Debug")
	public void testNonArchiveUserAfterArchiveUser() throws Exception {
		ctx.setProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED, true);

		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_SET);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");

		Set<String> items = new HashSet<String>();
		items.add("Item 0 in set");
		items.add("Item 1 in set");
		items.add("Item 2 in set");
		ctx.setProperty("unitTestProperty", items);
	}
	
	@UnitTest(startComponent="Debug")
	public void testFinalize() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_FINALIZE);
	}

	@UnitTest(startComponent="Debug")
	public void testMessageParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMessageParallelTargetName() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "messageRootPart");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMessageParallelEntityName() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_NAME, "rootpartByEntityName.xml");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMessageParallelEntitySuffix() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_SUFFIX, "rootpartByEntitySuffix");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMessageParallelEntitySuffixPath() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MESSAGE);
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_SUFFIX, "xslt/componentTransform.xsl");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		setMessagePart(0, MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testPropertyParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_PROPERTY);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		ctx.setProperty("unitTestProperty", VALUE_REPORT_SERVICE_ALIAS);
	}
	
	@UnitTest(startComponent="Debug")
	public void testPropertiesParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_PROPERTIES);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, PROP_GLOBAL_DEBUG_PROPERTY_LIST);
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		List<String> names = new ArrayList<String>();
		names.add(PROP_GLOBAL_PRIMARYLOG_FILENAME);
		names.add(PROP_GLOBAL_PRIMARYLOG_EXPIRES);
		ctx.setProperty(PROP_GLOBAL_DEBUG_PROPERTY_LIST, names);
	}
	
	@UnitTest(startComponent="Debug")
	public void testVariableParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setVariable("unitTestProperty", MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testVariableParallelEntityName() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_NAME, "variableEntityName.xml");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setVariable("unitTestProperty", MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testVariableParallelEntitySuffix() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_SUFFIX, "variableEntityName");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setVariable("unitTestProperty", MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_XML);
	}
	
	@UnitTest(startComponent="Debug")
	public void testVariableParallelEntitySuffixNonXml() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_VARIABLE);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_ENTITY_SUFFIX, "variableEntityName");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);
		
		setVariable("unitTestProperty", MOCK_RESPONSE_SSK144_XML_DATA, CONTENT_TYPE_TEXT_PLAIN);
	}
	
	@UnitTest(startComponent="Debug")
	public void testMapParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_MAP);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		Map<String,String> prompts = new HashMap<String,String>();
		prompts.put("WorkerReferenceIdType", "typeFromMap");
		prompts.put("WorkerReferenceId", "valueFromMap");
		ctx.setProperty("unitTestProperty", prompts);
	}
	
	@UnitTest(startComponent="Debug")
	public void testListParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_LIST);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		List<String> items = new ArrayList<String>();
		items.add("Item in position 0");
		items.add("Item in position 1");
		items.add("Item in position 2");
		ctx.setProperty("unitTestProperty", items);
	}
	
	@UnitTest(startComponent="Debug")
	public void testSetParallel() throws Exception {
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_TYPE, VALUE_PARAMETER_TARGET_TYPE_SET);
		ctx.setProperty(PROP_PARAMETER_IN_TARGET_NAME, "unitTestProperty");
		ctx.setProperty(PROP_PARAMETER_IN_IS_CHILD_THREAD, true);

		Set<String> items = new HashSet<String>();
		items.add("Item 0 in set");
		items.add("Item 1 in set");
		items.add("Item 2 in set");
		ctx.setProperty("unitTestProperty", items);
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
		if ("testFinalize".equalsIgnoreCase(getName())) {
			assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "the number of calls to Call_Aggregate_Finalize_144"), 1, mockTracker.getCallCount("Call_Aggregate_Finalize_144"));
		}

		ConcurrentLinkedQueue queue;
		Object message;
		CloudLogMessage m;
    	MVELUtilHelper util;
    	
		switch(getName()) {
			case "testMessage" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "rootpart.xml", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testMessageTargetName" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "rootpart.xml", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testMessageEntityName" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "rootpartByEntityName.xml", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testMessageEntitySuffix" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "rootpart_rootpartByEntitySuffix.xml", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testMessageEntitySuffixPath" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "rootpart_componentTransform.xml", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testVariable" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "unitTestProperty.xml", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testVariableEntityName" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "variableEntityName.xml", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testVariableEntitySuffix" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "unitTestProperty_variableEntityName.xml", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testVariableEntitySuffixNonXml" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_ENTITY_NAME), "unitTestProperty_variableEntityName.txt", (String)ctx.getProperty(PROP_PARAMETER_OUT_ENTITY_NAME));
				break;
				
			case "testMessageParallel" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "rootpart.xml", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Message root part initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testMessageParallelTargetName" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "rootpart.xml", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Message root part initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testMessageParallelEntityName" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "rootpartByEntityName.xml", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Message root part initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testMessageParallelEntitySuffix" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "rootpart_rootpartByEntitySuffix.xml", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Message root part initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testMessageParallelEntitySuffixPath" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "rootpart_componentTransform.xml", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Message root part initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testVariableParallel" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "unitTestProperty.xml", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Variable initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testVariableParallelEntityName" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "variableEntityName.xml", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Variable initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testVariableParallelEntitySuffix" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "unitTestProperty_variableEntityName.xml", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Variable initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testVariableParallelEntitySuffixNonXml" :
				util = new MVELUtilHelper(ctx);
				queue = (ConcurrentLinkedQueue)ctx.getProperty(PROP_LOCAL_PARALLEL_LOG);
				
				message = queue.peek();
				assertTrue(String.format(MESSAGE_UNEXPECTED_TYPE, PROP_LOCAL_PARALLEL_LOG + " message"), message instanceof CloudLogMessage);

				m = (CloudLogMessage)message;
				
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message summary"), "unitTestProperty_variableEntityName.txt", m.getSummary());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "message detail"), "Variable initial fragment (up to 10 KB) saved in the Support Data field (full data may not be available if exceeds 10 KB)", m.getDetail());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "log level"), "debug", m.getLevel());
				assertEquals(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, "support data"), util.cleanString(getExpectedResultMessageFragment()), m.getSupportData());
				break;
				
			case "testNonArchiveUserAfterArchiveUser" :
				assertTrue(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				break;
				
			default :
				assertFalse(String.format(MESSAGE_UNEXPECTED_VALUE_PROPERTY, PROP_PARAMETER_OUT_DEBUG_LOGGED), (Boolean)ctx.getProperty(PROP_PARAMETER_OUT_DEBUG_LOGGED));
				break;
		}
	}
	
	@AssertAfter(id="Call_Aggregate_Finalize_144")
	public void verifyFinalize() throws Exception {
		mockTracker.incrementCallCount("Call_Aggregate_Finalize_144");
	}
	/**************************************************************************************************************
	 ************************************************************************************************************** 
	 * 
	 * Mocks
	 * 
	 **************************************************************************************************************
	 **************************************************************************************************************/
	private String getExpectedResultMessageFragment() throws Exception {
    	InputStream in = getTestResourceInputStream(MOCK_MESSAGE_FRAGMENT);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	byte[] buffer = new byte[1024];

    	int length;
		while ((length = in.read(buffer)) != -1) {
			baos.write(buffer, 0, length);
		}
		
    	return baos.toString("UTF-8");
	}
}
