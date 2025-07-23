package com.workday.custom.int0025.ssk149.mediations;

import static com.capeclear.assembly.annotation.Component.Type.mediation;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.xml.stream.XMLEventReader;

import com.capeclear.assembly.annotation.Component;
import com.capeclear.assembly.annotation.ComponentMethod;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.impl.cc.MediationTube;
import com.capeclear.xml.utils.XmlUtils;
import com.workday.custom.int0025.SSKUtils;
import com.workday.custom.int0025.ssk149.handlers.ReportDiffHandler;
import com.workday.custom.int0025.ssk149.parsers.ReportEventReader;
import com.workday.custom.int0025.ssk149.writers.ReportEntryWriter;


/**
 * Custom mediation
 *
 * TODO Modify the Component annotation. Also add Property annotations to any
 * bean pattern methods you add and want to appear in the Assembly Editor.
 */
@Component(
        name = "ReportDiffBean",
        type = mediation,
        toolTip = "",
        scope = "prototype",
        smallIconPath = "icons/ReportDiffBean_16.png",
        largeIconPath = "icons/ReportDiffBean_24.png"
        )
public class ReportDiffBean {

	private String propertyNameDataSourceHistoric;
	private String propertyNameDataSourceCurrent;
	private String propertyNameHistoricIdList;
	private String propertyNameCurrentIdList;
	private String propertyNameDeltaOrFullFile;

    public void setPropertyNameDataSourceHistoric(String propertyNameDataSourceHistoric) {
		this.propertyNameDataSourceHistoric = propertyNameDataSourceHistoric;
	}

	public void setPropertyNameDataSourceCurrent(String propertyNameDataSourceCurrent) {
		this.propertyNameDataSourceCurrent = propertyNameDataSourceCurrent;
	}
	
	public void setPropertyNameHistoricIdList(String propertyNameHistoricIdList) {
		this.propertyNameHistoricIdList = propertyNameHistoricIdList;
	}

	public void setPropertyNameCurrentIdList(String propertyNameCurrentIdList) {
		this.propertyNameCurrentIdList = propertyNameCurrentIdList;
	}

    public void setPropertyNameDeltaOrFullFile(String propertyNameDeltaOrFullFile) {
		this.propertyNameDeltaOrFullFile = propertyNameDeltaOrFullFile;
	}

	/**
     * This method is called by the Assembly framework.    
     */
    @ComponentMethod
    public void process(InputStream in, OutputStream out) {
		MediationContext mc = MediationTube.getCurrentMediationContext();

		@SuppressWarnings("unchecked")
		Set<String> historicIds = (Set<String>)mc.getProperty(propertyNameHistoricIdList);
		@SuppressWarnings("unchecked")
		Set<String> currentIds = (Set<String>)mc.getProperty(propertyNameCurrentIdList);
		
		boolean isChangesOnly = (boolean)mc.getProperty(propertyNameDeltaOrFullFile);
		
		XMLEventReader historicReader = null;
		XMLEventReader currentReader = null;
		ReportEntryWriter writer = null;
		
		try {
    		String sourceName = ((String)mc.getProperty(propertyNameDataSourceHistoric)).trim(); 
			if (sourceName.trim().equalsIgnoreCase("message")) {
				historicReader = XmlUtils.getXMLInputFactory().createXMLEventReader(SSKUtils.getMessageInputStream(mc), "utf-8");
			} else {
				historicReader = XmlUtils.getXMLInputFactory().createXMLEventReader(SSKUtils.getVariableInputStream(mc, sourceName), "utf-8");
			}
			ReportEventReader historicParser = new ReportEventReader(historicReader, sourceName);
			historicParser.getReportRoot();
			
    		sourceName = ((String)mc.getProperty(propertyNameDataSourceCurrent)).trim(); 
			if (sourceName.trim().equalsIgnoreCase("message")) {
				currentReader = XmlUtils.getXMLInputFactory().createXMLEventReader(SSKUtils.getMessageInputStream(mc), "utf-8");
			} else {
				currentReader = XmlUtils.getXMLInputFactory().createXMLEventReader(SSKUtils.getVariableInputStream(mc, sourceName), "utf-8");
			}
			ReportEventReader currentParser = new ReportEventReader(currentReader, sourceName);
			currentParser.getReportRoot();
			
			writer = new ReportEntryWriter(XmlUtils.getXMLOutputFactory().createXMLStreamWriter(out), currentParser.getNamespaceURI());
			
			ReportDiffHandler handler = new ReportDiffHandler(writer, isChangesOnly);
			
			currentParser.compareReports(currentIds, historicIds, historicParser, handler);
			handler.endOutput();
		} catch (Throwable t) {
    		throw new RuntimeException(t);
		} finally {
    		try {
	    		if (historicReader != null) {
	    			historicReader.close();
	    		}

	    		if (currentReader != null) {
	    			currentReader.close();
	    		}
    		} catch (Throwable t) {
        		throw new RuntimeException(t);
    		}
		}
    }
}
