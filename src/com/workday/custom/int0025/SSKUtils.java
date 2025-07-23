package com.workday.custom.int0025;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.capeclear.capeconnect.attachments.io.FileBackedManagedData;
import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.impl.DataHandlerSource;
import com.capeclear.mediation.impl.mediators.MVELUtilHelper;
import com.capeclear.mediation.impl.mediators.utils.XPath;
import com.capeclear.xml.utils.DOMUtils;
import com.capeclear.xml.utils.XmlUtils;
import com.workday.custom.int0025.ssk109.FlexLogMetaConfiguration;
import com.workday.custom.int0025.ssk112.CloudLogEntry;
import com.workday.custom.int0025.ssk147.FlexLogBean;

public final class SSKUtils {
	
	public static JAXBContext JAXB_CONTEXT;
	public static Unmarshaller UNMARSHALLER;
	public static Marshaller MARSHALLER;

	private static Map<String,String> mimeTypeExtensionMap;

	/*
	 * NOTE:
	 * 
	 * General Workday Studio development guidance is to avoid use of static instances in Java.  The reason for which is
	 * that the integration server may not be torn down after the event completes, and in a data center optimization, may
	 * instead be recycled to serve the next event sooner.  If data is stored in static references, then this can result
	 * in two consequences: reduced memory availability to the second integration, and potential leakage of data between
	 * events.
	 * 
	 * The static references below are used because:
	 * a) They are small memory footprints, and as the use of SSK becomes broader, the existing instances will save the
	 * second integration the overhead of reinstantiating them, and...
	 * b) They contain no customer data and have no opportunity to leakage information across events. 
	 */
	static {
		mimeTypeExtensionMap = new HashMap<String,String>();
		mimeTypeExtensionMap.put("text/xml", ".xml");
		mimeTypeExtensionMap.put("application/json", ".json");
		mimeTypeExtensionMap.put("text/csv", ".csv");
		mimeTypeExtensionMap.put("text/plain", ".txt");
		mimeTypeExtensionMap.put("text/html", ".html");
		mimeTypeExtensionMap.put("application/pdf", ".pdf");
		mimeTypeExtensionMap.put("application/zip", ".zip");
		mimeTypeExtensionMap.put("application/octet-stream", ".bin");
		mimeTypeExtensionMap.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx");
		mimeTypeExtensionMap.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx");
		mimeTypeExtensionMap.put("application/vnd.ms-excel", ".xls");
		mimeTypeExtensionMap.put("application/msword", ".doc");
		mimeTypeExtensionMap.put("image/jpeg", ".jpg");
		mimeTypeExtensionMap.put("image/gif", ".gif");
		mimeTypeExtensionMap.put("image/png", ".png");
		mimeTypeExtensionMap.put("image/x-ms-bmp", ".bmp");

		try {
			JAXB_CONTEXT = JAXBContext.newInstance(CloudLogEntry.class);

			UNMARSHALLER = JAXB_CONTEXT.createUnmarshaller();
			
			MARSHALLER = JAXB_CONTEXT.createMarshaller();
			MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
			MARSHALLER.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(), t);
		}
	}
	
	public static String getFileExtenstionFromMimetype(String mimetype) {
		String returnValue = "";

		for (String key : mimeTypeExtensionMap.keySet()) {
			if (mimetype.startsWith(key)) {
				returnValue = mimeTypeExtensionMap.get(key);
				break;
			}
		}

		return returnValue;
	}
	
	public static String getContextualURL(MediationContext mc, String relativeFilepathToWSARINF) throws Exception {
		String returnValue = null;
		String base = mc.getBaseURL().toExternalForm();
		if (StringUtils.contains(base, "WSAR-INF")) {
			returnValue = base + relativeFilepathToWSARINF;
		} else if (base.endsWith("/")) {
			returnValue = base + "WSAR-INF/" + relativeFilepathToWSARINF;
		} else {
			returnValue = base + "/WSAR-INF/" + relativeFilepathToWSARINF;
		}
		
		return returnValue;
	}
	
	public static boolean isVariableNullOrUndefined(MediationContext mc, String variableName) throws Exception {
		return (!mc.getVariables().containsKey(variableName)) || mc.getVariables().getVariable(variableName) == null;
	}
	
	public static InputStream getVariableInputStream(MediationContext mc, String variableName) throws Exception {
		return ((DataHandlerSource)mc.getVariables().getVariable(variableName)).getInputStream();
	}
	
	public static InputStream getMessageInputStream(MediationContext mc) throws Exception {
		return (InputStream)mc.getMessage().getMessagePart(0, InputStream.class);
	}
	
	public static InputStream getMessagePartInputStream(int partNumber, MediationContext mc) throws Exception {
		return (InputStream)mc.getMessage().getMessagePart(partNumber, InputStream.class);
	}
	
	public static Document getVariableAsXmlDocument(MediationContext mc, String variableName) throws Exception {
		return DOMUtils.parseToDOM(new InputSource(new InputStreamReader(((DataHandlerSource)mc.getVariables().getVariable(variableName)).getInputStream(), "UTF-8")));
	}
	
	public static Document getMessageAsXmlDocument(MediationContext mc) throws Exception {
		return mc.getMessage().isNullPart(0) ? null : DOMUtils.parseToDOM(new InputSource(new InputStreamReader(getMessageInputStream(mc), "UTF-8")));
	}
	
	@SuppressWarnings("unchecked")
	public static DOMSource getMessageAsDOMSource(MediationContext mc) throws Exception {
		return mc.getMessage().isNullPart(0) ? null : (DOMSource)mc.getMessage().getMessage(new Class[] {DOMSource.class});
	}
	
	public static Node getDocumentRootNode(DOMSource source) {
		return (source == null) ? null : source.getNode();
	}

	public static void logErrorAndThrowRuntimeException(Logger log, String message, Throwable t) throws Throwable {
		log.error(message, t);
		throw new RuntimeException(message, t);
	}
	
	public static String getValueFromVariableXml(MediationContext mc, String variableName, String xpathToValue) throws Exception {
    	return new XPath(xpathToValue, MediationConstants.ASSEMBLY_VERSION, mc).stringValueOf(getVariableAsXmlDocument(mc, variableName));
	}

	public static String cleanFilename(String filename, String replacement) {
		String returnValue = filename;
		
		if (StringUtils.isNotBlank(filename)) {
			returnValue = filename.replaceAll("[\\\\/:*?\"<>|]", replacement);
		}
		
		return returnValue;
	}
	
	public static void resetVariable(MediationContext mc, String variableName, String contentType) throws Exception {
    	FileBackedManagedData fbmd = new FileBackedManagedData(contentType);

    	mc.addDisposable(fbmd);
    	mc.getVariables().setVariable(variableName, new DataHandlerSource(fbmd.getDataSource()), contentType);
	}
	
	public static OutputStream createVariableWithOutputStream(MediationContext mc, String variableName, String contentType) throws Exception {
    	FileBackedManagedData fbmd = new FileBackedManagedData(contentType);

    	mc.addDisposable(fbmd);
    	mc.getVariables().setVariable(variableName, new DataHandlerSource(fbmd.getDataSource()), contentType);

    	OutputStream returnValue = fbmd.getOutputStream(); 
		
    	return returnValue;
	}
	
	public static XMLStreamWriter createVariableWithXmlStreamWriter(MediationContext mc, String variableName, String contentType) throws Exception {
    	return XmlUtils.getXMLOutputFactory().createXMLStreamWriter(createVariableWithOutputStream(mc, variableName, contentType));
	}

	public static boolean configureFlexLog(MediationContext mc, MVELUtilHelper util) throws Exception {
		Logger log = LogControl.getLogger(SSKUtils.class);
    	FlexLogMetaConfiguration flexLogMetaBean = (FlexLogMetaConfiguration)mc.getProperty(MediationConstants.PROPERTY_SSK_FLEX_LOG_META_BEAN);
    	
    	String xmlConfigurationFile = flexLogMetaBean.getConfigurationFile();
    	String xsdConfigurationSchema = flexLogMetaBean.getSchema();

    	// If the configuration files have been removed, then we'll assume FlexLog use is not intended and no need to be noisy about it
    	try {
    		flexLogMetaBean.readConfigurationToVariable(util, MediationConstants.VARIABLE_FLEXLOG_TEMP_CONFIG, xmlConfigurationFile);
    		flexLogMetaBean.readSchemaToVariable(util, MediationConstants.VARIABLE_FLEXLOG_TEMP_SCHEMA, xsdConfigurationSchema);
    	} catch (Throwable xcpn) {
    		log.info("An exception was raised while attempting to read SSK FlexLog configuration.  The configuration and/or schema file(s) may have been removed.  This message is informative in the event FlexLog use is intended, but the configuration has been lost.  If FlexLog use is not intended, then this message may be ignored.  The exception message encountered is: " + xcpn.getMessage());
    		return false;
		}

    	// If we got this far though, then files are there, and if an error is encountered, it would be because they were modified/broken.  Editing implies usage intent and the error should be more vocal as this will be most likely encountered during build.
    	try {
        	SchemaFactory factory = XmlUtils.getSchemaFactory();
        	Schema schema = factory.newSchema(new StreamSource(new InputStreamReader(((DataHandlerSource)mc.getVariables().getVariable(MediationConstants.VARIABLE_FLEXLOG_TEMP_SCHEMA)).getInputStream(), "UTF-8")));
        	Validator validator = schema.newValidator();
        	validator.validate(new StreamSource(new InputStreamReader(((DataHandlerSource)mc.getVariables().getVariable(MediationConstants.VARIABLE_FLEXLOG_TEMP_CONFIG)).getInputStream(), "UTF-8")));
    	} catch (Throwable xcpn) {
        	mc.getVariables().remove(MediationConstants.VARIABLE_FLEXLOG_TEMP_CONFIG);
        	mc.getVariables().remove(MediationConstants.VARIABLE_FLEXLOG_TEMP_SCHEMA);
        	
        	throw new Exception("Unable to find or validate the SSK Flex Log configuration.  Please confirm the configuration file exists at '"+ xmlConfigurationFile +"' and that it conforms to the schema in '"+ xsdConfigurationSchema +"'.", xcpn);
    	}
    	
    	boolean returnValue = false;
    	
    	try {
    		FlexLogBean flexLogBean = new FlexLogBean();
    		flexLogBean.configureNewLog(mc);
    		
    		if (flexLogBean.isConfigured()) {
    			mc.setProperty(MediationConstants.PROPERTY_SSK_FLEX_LOG, flexLogBean);
    			returnValue = true;
    		}
    	} catch (Throwable xcpn) {
    		throw new Exception("SSK's Flex Log failed to initialize due to errors encountered during configuration.  Please check the configuration file at '"+ xmlConfigurationFile +"' and resolve the configuration issue: " + xcpn.getMessage(), xcpn);
    	} finally {
        	mc.getVariables().remove(MediationConstants.VARIABLE_FLEXLOG_TEMP_CONFIG);
        	mc.getVariables().remove(MediationConstants.VARIABLE_FLEXLOG_TEMP_SCHEMA);
    	}
    	
    	return returnValue;
	}

	public static InputStream getFlexLogInputStream(MediationContext mc, String logName) throws Exception {
		return (((FlexLogBean)mc.getProperty(MediationConstants.PROPERTY_SSK_FLEX_LOG)).getFlexLog(logName)).getLogData().getInputStream();
	}

	public static String convertNamespaceArgumentToDefinitions(String namespacesAsString) throws IllegalArgumentException {
		StringBuilder returnValue = new StringBuilder();
		
		if (org.apache.commons.lang.StringUtils.isNotBlank(namespacesAsString)) {
			String[] tokens = namespacesAsString.split(" ");
			
			if (tokens.length % 2 != 0) {
				throw new IllegalArgumentException("The XML namespace argument must be provided as pairs of aliases and namespaces separated by spaces.  An unexpected number of values was found when parsing the argument.  Please ensure that each namespace defined is given an alias, and multiple namespaces are space-separated.");
			}
	
			for (int i = 0; i < tokens.length; i = i + 2) {
				String alias = tokens[i].trim();
				String namespace = tokens[i + 1].trim();
				
				returnValue.append("xmlns:" + alias + "=\"" + namespace + "\" ");
			}
		}
		
		return returnValue.toString().trim();
	}
}
