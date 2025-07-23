package com.workday.custom.int0025.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.lang3.StringUtils;

public class CleanupProcessor {
	private static final String SWIMLANE_RED_FONT_CODE = "255";
	private static final String SWIMLANE_NAME_MARKER = "MARKED FOR REMOVAL:   ";
	
	private static final String DEBUG_ASSEMBLY_TARGET = "./assemblyFromCleanupAssistant.xml";
	private static final String DEBUG_DIAGRAM_TARGET = "./diagramFromCleanupAssistant.xml";
	
	Logger log = Logger.getLogger(CleanupProcessor.class.getName());
	
	private boolean isDebugMode = true;
	private List<SSKComponent> components;
	private List<String> projectTopLevelDirectories;
	
	private String assemblyXml;
	private String diagramXml;
	
	private XMLInputFactory inputFactory;
	
	public CleanupProcessor(boolean debugMode, String assemblyXml, String diagramXml) throws Exception {
		this.isDebugMode = debugMode;
		this.assemblyXml = assemblyXml;
		this.diagramXml = diagramXml;
		
		this.projectTopLevelDirectories = new ArrayList<String>();
		this.projectTopLevelDirectories.add("src");
		this.projectTopLevelDirectories.add("test");
		this.projectTopLevelDirectories.add("ws");
		
		this.inputFactory = XMLInputFactory.newInstance();
		
		initializeComponentSwimlaneConfiguration();
		
		if (isDebugMode) {
			System.out.println("The assembly file is: " + assemblyXml);
			System.out.println("The diagram file is: " + diagramXml);
			
			for (SSKComponent c : components) {
				System.out.println("Analyzing SSK for " + c.toString());
			}
		}
	}
	
	private void initializeComponentSwimlaneConfiguration() throws Exception {
		components = new ArrayList<SSKComponent>();
		
		components.add(new SSKComponent("AddReportPromptFromProperty", "int0025101", "101 - Build Reports-as-a-Service Report Prompts", "REPORTS AS A SERVICE"));
		components.add(new SSKComponent("AddReportPromptFromXml", "int0025101", "101 - Build Reports-as-a-Service Report Prompts", "REPORTS AS A SERVICE"));
		components.add(new SSKComponent("CallRaaS", "int0025102", "102 - Reports-as-a-Service Execution", "REPORTS AS A SERVICE", "SSK102SoapAuthorBean"));
		components.add(new SSKComponent("InitializeObjectDiff", "int0025146", "146 - Reports-as-a-Service Comparison - ObjectDiff", "REPORTS AS A SERVICE", "SSK146DataStructureBean"));
		components.add(new SSKComponent("HistoricDataMapBuilder", "int0025146", "146 - Reports-as-a-Service Comparison - ObjectDiff", "REPORTS AS A SERVICE", "SSK146MapBuilderBean"));
		components.add(new SSKComponent("ObjectDiff", "int0025146", "146 - Reports-as-a-Service Comparison - ObjectDiff", "REPORTS AS A SERVICE", "SSK146ObjectDiffBean"));
		components.add(new SSKComponent("InitializeReportDiff", "int0025149", "149 - Reports-as-a-Service Comparison - ReportDiff", "REPORTS AS A SERVICE", "SSK149DataStructureBean"));
		components.add(new SSKComponent("BuildUniqueIdList", "int0025149", "149 - Reports-as-a-Service Comparison - ReportDiff", "REPORTS AS A SERVICE", "SSK149ListBuilderBean"));
		components.add(new SSKComponent("ReportDiff", "int0025149", "149 - Reports-as-a-Service Comparison - ReportDiff", "REPORTS AS A SERVICE", "SSK149ReportDiffBean"));
		
		components.add(new SSKComponent("CallSoap", "int0025103", "103 - Workday Web Service (SOAP) Execution", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("CallSoapPaged", "int0025104", "104 - Workday Web Service (SOAP) Paged Execution", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("CallSoapImport", "int0025113", "113 - Workday Web Service (SOAP) Import APIs (Process)", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("FinalizeCallSoapImport", "int0025113", "113 - Workday Web Service (SOAP) Import APIs (Process)", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("ReportResultsOfCallSoapImport", "int0025118", "118 - Workday Web Service (SOAP) Import APIs (Post-Process)", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("SetExternalIntegrationHeaders", "int0025119", "119 - Workday Web Service (SOAP) External Integration Headers", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("WriteNonEffectiveDatedCustomObject", "int0025120", "120 - Non-Effective Dated Custom Object Service", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("CreateQueue", "int0025122", "122 - Workday Message Queue - Create Queue", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("GetQueues", "int0025123", "123 - Workday Message Queue - Get Queues", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("PollQueue", "int0025124", "124 - Workday Message Queue - Poll Queue", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("ReadMessageFromQueue", "int0025125", "125 - Workday Message Queue - Read Message", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("AddMessageToQueue", "int0025126", "126 - Workday Message Queue - Write Message", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("DeleteMessageFromQueue", "int0025127", "127 - Workday Message Queue - Delete Message", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("PurgeQueue", "int0025128", "128 - Workday Message Queue - Purge Queue", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("GetAccessToken", "int0025160", "160 - Get Access Token", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("CallRestApi", "int0025161", "161 - Call REST API", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("WQL", "int0025162", "162 - WQL", "WORKDAY WEB SERVICES (WWS) API"));
		components.add(new SSKComponent("StoreExtendAttachment", "int0025163", "163 - Write Attachment Object", "WORKDAY WEB SERVICES (WWS) API"));
		
		components.add(new SSKComponent("GenerateOutput", "int0025106", "106 - Output Generation", "FILE UTILITIES"));
		components.add(new SSKComponent("GetDocumentList", "int0025110", "110 - Get Document List", "FILE UTILITIES"));
		components.add(new SSKComponent("LoadFile", "int0025111", "111 - Load File", "FILE UTILITIES"));
		components.add(new SSKComponent("GetDISResults", "int0025115", "115 - Data Initialization Service", "FILE UTILITIES", "SSK115FileSplitter"));
		components.add(new SSKComponent("ExtendLogAggregationChain", "int0025134", "134 - Extend Log Aggregation Chain", "FILE UTILITIES"));
		components.add(new SSKComponent("GetLogAggregationChain", "int0025136", "136 - Get Log Aggregation Chain", "FILE UTILITIES"));
		components.add(new SSKComponent("HandleByteOrderMark", "int0025148", "148 - Byte Order Mark Utilities", "FILE UTILITIES", "SSK148ProcessorBean"));
		
		components.add(new SSKComponent("PopulateJavaMap", "int0025105", "105 - Populate Java Map", "XSLT 3.0"));
		components.add(new SSKComponent("StreamDataMerge", "int0025107", "107 - Streaming Data Merge", "XSLT 3.0"));
		components.add(new SSKComponent("BlockSplitter", "int0025116", "116 - XML Block Splitting", "XSLT 3.0", "SSK116BlockSplitter"));
		components.add(new SSKComponent("Text2ParallelSplitter", "int0025117", "117 - Text to Parallel Splitter", "XSLT 3.0", "SSK117BlockSplitter"));
		components.add(new SSKComponent("GetXmlTransformer", "int0025117", "117 - Text to Parallel Splitter", "XSLT 3.0"));
		components.add(new SSKComponent("GetJsonTransformer", "int0025117", "117 - Text to Parallel Splitter", "XSLT 3.0"));
		components.add(new SSKComponent("GetContextPropertyTransformer", "int0025117", "117 - Text to Parallel Splitter", "XSLT 3.0"));
		components.add(new SSKComponent("XsltPlus", "int0025129", "129 - XSLT Transform", "XSLT 3.0"));
		components.add(new SSKComponent("MultiItemFlowControl", "int0025130", "130 - Multi-Item Flow Control", "XSLT 3.0"));
		components.add(new SSKComponent("GetRootOpenTag", "int0025135", "135 - Get Root Open Tag", "XSLT 3.0"));
		components.add(new SSKComponent("GetRootCloseTag", "int0025138", "138 - Get Root Close Tag", "XSLT 3.0"));
		components.add(new SSKComponent("StreamSourceMerge", "int0025153", "153 - Streaming Source Merge", "XSLT 3.0"));
		components.add(new SSKComponent("PrepareMergeSource", "int0025153", "153 - Streaming Source Merge", "XSLT 3.0"));
	}
	
	public void executeCleanupTasks(File rootDirectory) throws Exception {
		List<String> unusedComponents = getUnusedComponents();
		for (String c : unusedComponents) {
			System.out.println("Determined SSK Component [" + c.toString() + "] is unused.");
		}
		
		List<String> beansToDelete = getUnusedBeans(unusedComponents);
		for (String c : beansToDelete) {
			System.out.println("Determined Assembly Spring bean [" + c.toString() + "] is unnecessary because the associated SSK Component is unused and can therefore be deleted.");
		}

		cleanseAssemblyXmlFile(beansToDelete);

		List<String> swimlanesToDelete = getUnusedSwimlanes(unusedComponents);
		for (String c : swimlanesToDelete) {
			System.out.println("Determined Swimlane [" + c.toString() + "] is unnecessary because the associated SSK Component is unused and can therefore be deleted.");
		}
		
		cleanseAssemblyDiagramXmlFile(swimlanesToDelete);
		
		List<String> packagesToDelete = getUnusedPackages(unusedComponents);
		for (String c : packagesToDelete) {
			System.out.println("Determined Java package [" + c.toString() + "] is unnecessary because all associated SSK Components are unused and can therefore be deleted.");
		}

		processDirectory(rootDirectory, packagesToDelete, true);		
	}
	
	private List<String> getListOfAllSSKLocalIns() throws Exception {
		List<String> returnValue = new ArrayList<String>();
		
		for (SSKComponent c : components) {
			returnValue.add(c.localIn);
		}
		
		return returnValue;
	}
	
	private List<String> getUnusedComponents() throws Exception {
		Set<String> localOutEndpoints = new HashSet<String>();
		List<String> returnValue = getListOfAllSSKLocalIns();
		
		XMLStreamReader streamReader = null;
		
		try {
			streamReader = this.inputFactory.createXMLStreamReader(new FileInputStream(assemblyXml));

			while (streamReader.hasNext()) {
				streamReader.next();
				
				if ((streamReader.getEventType() == XMLStreamReader.START_ELEMENT) && (streamReader.getLocalName().equalsIgnoreCase("local-out"))) {
					localOutEndpoints.add(StringUtils.substringAfterLast(streamReader.getAttributeValue("", "endpoint"), "/"));
				}
			}
		} finally {
			if (streamReader != null) {
				streamReader.close();
			}
		}
		
		for (String endpoint : localOutEndpoints) {
			if (returnValue.remove(endpoint) && isDebugMode) {
				System.out.println("Found local-out endpoint match for SSK Component [" + endpoint + "]");
			}
		}
		
		return returnValue;
	}
	
	private List<String> getUnusedPackages(List<String> unusedComponents) throws Exception {
		List<String> returnValue = new ArrayList<String>();
		
		Map<String,List<String>> componentsByPackage = new HashMap<String,List<String>>();
		for (SSKComponent c : components) {
			if (!componentsByPackage.containsKey(c.packageId)) {
				componentsByPackage.put(c.packageId, new ArrayList<String>());
			}
			componentsByPackage.get(c.packageId).add(c.localIn);
		}
		
		List<SSKComponent> filteredComponents = components.stream().filter(c -> unusedComponents.contains(c.localIn)).collect(Collectors.toList());
		
		for (SSKComponent c : filteredComponents) {
			componentsByPackage.get(c.packageId).remove(c.localIn);
		}

		for (String key : componentsByPackage.keySet()) {
			if (componentsByPackage.get(key).isEmpty()) {
				returnValue.add(key);
			}
		}
		
		return returnValue;
	}

	private List<String> getUnusedBeans(List<String> unusedComponents) throws Exception {
		List<String> returnValue = new ArrayList<String>();
		List<SSKComponent> filteredComponents = components.stream().filter(c -> unusedComponents.contains(c.localIn)).collect(Collectors.toList());
		
		for (SSKComponent c : filteredComponents) {
			for (String b : c.beanIds) {
				returnValue.add(b);
			}
		}
		
		return returnValue;
	}

	private List<String> getUnusedSwimlanes(List<String> unusedComponents) throws Exception {
		List<String> returnValue = new ArrayList<String>();
		
		Map<String,List<String>> swimlanesByCategory = new HashMap<String,List<String>>();
		for (SSKComponent c : components) {
			if (!swimlanesByCategory.containsKey(c.categorySwimlane)) {
				swimlanesByCategory.put(c.categorySwimlane, new ArrayList<String>());
			}
			swimlanesByCategory.get(c.categorySwimlane).add(c.componentSwimlane);
		}
		
		List<SSKComponent> filteredComponents = components.stream().filter(c -> unusedComponents.contains(c.localIn)).collect(Collectors.toList());
		
		for (SSKComponent c : filteredComponents) {
			returnValue.add(c.componentSwimlane);
			swimlanesByCategory.get(c.categorySwimlane).remove(c.componentSwimlane);
		}

		for (String key : swimlanesByCategory.keySet()) {
			if (swimlanesByCategory.get(key).isEmpty()) {
				returnValue.add(key);
			}
		}
		
		return returnValue;
	}
	
	private void cleanseAssemblyXmlFile(List<String> beansToDelete) throws Exception {
		FileInputStream inputStream = null;
		BufferedReader reader = null;
		
		ByteArrayOutputStream outputStream = null;
		FileOutputStream fileStream = null;		
		
		try {
			inputStream = new FileInputStream(assemblyXml);
			reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			
			outputStream = new ByteArrayOutputStream();
			
			boolean isMultiLineBean = false;
			String line;
			while ((line = reader.readLine()) != null) {
				if (isMultiLineBean || (line.contains("<bean") && line.contains("class"))) {
					if (!isMultiLineBean) {
						boolean isMatch = false;
						for (String id : beansToDelete) {
							if (line.contains(id)) {
								isMatch = true;
								if (isDebugMode) {
									System.out.println("Found match to bean id=" + id + " on line:  " + line);
								}
								isMultiLineBean = !line.contains("/>");
							}
						}
						if (!isMatch) {
							outputStream.write(line.getBytes());
							outputStream.write(System.lineSeparator().getBytes());
						}
					} else {
						System.out.println("Additional line matched as multi-line:  " + line);
						isMultiLineBean = !line.contains("</bean>");
					}
				} else {
					outputStream.write(line.getBytes());
					outputStream.write(System.lineSeparator().getBytes());
				}
			}
			
			outputStream.flush();
			
			fileStream = new FileOutputStream(isDebugMode ? DEBUG_ASSEMBLY_TARGET : assemblyXml);
			outputStream.writeTo(fileStream);

		} catch (Exception e) {
			System.out.println("Failed to cleanse the " + assemblyXml + " of unused bean configuration.  Please check the file manually and confirm that the assembly properties do not reference unused/removed SSK Components.");
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			if (fileStream != null) {
				fileStream.close();
			}
		}
	}
	
	private void cleanseAssemblyDiagramXmlFile(List<String> swimlanesToDelete) throws Exception {
		FileInputStream inputStream = null;
		BufferedReader reader = null;
		
		ByteArrayOutputStream outputStream = null;
		FileOutputStream fileStream = null;		
		
		try {
			inputStream = new FileInputStream(diagramXml);
			reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			
			outputStream = new ByteArrayOutputStream();
			
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("<swimlanes") && line.contains("name=")) {
					for (String id : swimlanesToDelete) {
						if (line.contains("name=\"" + id + "\"")) {
							if (isDebugMode) {
								System.out.println("Found match to swimlane name=" + id + " on line:  " + line);
							}
							line = line.replaceAll("fontColor=\"[0-9]+\"", "fontColor=\""+ SWIMLANE_RED_FONT_CODE +"\"").replaceAll("name=\"", " name=\""+ SWIMLANE_NAME_MARKER);
						}
					}
				}
				outputStream.write(line.getBytes());
				outputStream.write(System.lineSeparator().getBytes());
			}
			
			outputStream.flush();
			
			fileStream = new FileOutputStream(isDebugMode ? DEBUG_DIAGRAM_TARGET : diagramXml);
			outputStream.writeTo(fileStream);
		} catch (Exception e) {
			System.out.println("Failed to cleanse the " + assemblyXml + " of unused bean configuration.  Please check the file manually and confirm that the assembly properties do not reference unused/removed SSK Components.");
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			if (fileStream != null) {
				fileStream.close();
			}
		}
	}
	
	private void processDirectory(File directory, List<String> packagesToDelete, boolean isRootLevelEvaluation) throws Exception {
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				System.out.println("The directory name is: " + f.getCanonicalPath());
				
				if ((isRootLevelEvaluation && projectTopLevelDirectories.contains(f.getName())) || (!isRootLevelEvaluation)) {
					processDirectory(f, packagesToDelete, false);
				}
				
				if (packagesToDelete.contains(f.getName())) {
					deleteDirectory(f);
				}
			}
		}
	}
	
	private void deleteDirectory(File directory) throws Exception {
		System.out.println("Deleting directory " + directory.getName());
		if (!isDebugMode) {
			for (File content : directory.listFiles()) {
				if (content.isDirectory()) {
					deleteDirectory(content);
				}
				content.delete();
			}
			directory.delete();
		}		
	}
	
	private class SSKComponent {
		String localIn;
		String packageId;
		String componentSwimlane;
		String categorySwimlane;
		List<String> beanIds;
		
		public SSKComponent(String localIn, String packageId, String componentSwimlane, String categorySwimlane) {
			this.localIn = localIn;
			this.packageId = packageId;
			this.componentSwimlane = componentSwimlane;
			this.categorySwimlane = categorySwimlane;
			this.beanIds = new ArrayList<String>();
		}
		
		public SSKComponent(String localIn, String packageId, String componentSwimlane, String categorySwimlane, String beanId) {
			this.localIn = localIn;
			this.packageId = packageId;
			this.componentSwimlane = componentSwimlane;
			this.categorySwimlane = categorySwimlane;
			
			this.beanIds = new ArrayList<String>();
			if (StringUtils.isNotEmpty(beanId)) {
				if (beanId.contains(",")) {
					for(String b : beanId.split(",")) {
						this.beanIds.add(b);
					}
				} else {
					this.beanIds.add(beanId);
				}
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(super.toString());
			sb.append(" Component: localIn = [")
				.append(localIn)
				.append("], packageId = [")
				.append(packageId)
				.append("], componentSwimlane = [")
				.append(componentSwimlane)
				.append("], categorySwimlane = [")
				.append(categorySwimlane)
				.append("], beanId = [")
				.append(StringUtils.join(beanIds, ","))
				.append("]");
			return sb.toString();
		}
		
		
	}
}
