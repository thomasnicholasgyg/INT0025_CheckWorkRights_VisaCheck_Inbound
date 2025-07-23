package com.workday.custom.int0025.ssk149.mediations;

import static com.capeclear.assembly.annotation.Component.Type.mediation;

import java.util.Set;

import javax.xml.stream.XMLEventReader;

import com.capeclear.assembly.annotation.Component;
import com.capeclear.assembly.annotation.ComponentMethod;
import com.capeclear.mediation.MediationContext;
import com.capeclear.xml.utils.XmlUtils;
import com.workday.custom.int0025.SSKUtils;
import com.workday.custom.int0025.ssk149.handlers.ListBuilderHandler;
import com.workday.custom.int0025.ssk149.parsers.ReportEventReader;


/**
 * Custom mediation
 *
 * TODO Modify the Component annotation. Also add Property annotations to any
 * bean pattern methods you add and want to appear in the Assembly Editor.
 */
@Component(
        name = "MapBuilderBean",
        type = mediation,
        toolTip = "",
        scope = "prototype",
        smallIconPath = "icons/MapBuilderBean_16.png",
        largeIconPath = "icons/MapBuilderBean_24.png"
        )
public class ListBuilderBean {

	private String propertyNameIdList;
	private String propertyNameDataSource;
	
	public void setPropertyNameIdList(String propertyNameIdList) {
		this.propertyNameIdList = propertyNameIdList;
	}

	public void setPropertyNameDataSource(String propertyNameDataSource) {
		this.propertyNameDataSource = propertyNameDataSource;
	}

	/**
     * This method is called by the Assembly framework.    
     */
    @ComponentMethod
    public void process(MediationContext mc) {
		@SuppressWarnings("unchecked")
		Set<String> idList = (Set<String>)mc.getProperty(propertyNameIdList);
    	
		XMLEventReader eventReader = null;
    	
    	try {
    		String sourceName = ((String)mc.getProperty(propertyNameDataSource)).trim(); 
			if (sourceName.trim().equalsIgnoreCase("message")) {
				eventReader = XmlUtils.getXMLInputFactory().createXMLEventReader(SSKUtils.getMessageInputStream(mc), "utf-8");
			} else {
				eventReader = XmlUtils.getXMLInputFactory().createXMLEventReader(SSKUtils.getVariableInputStream(mc, sourceName), "utf-8");
			}
			
        	ReportEventReader parser = new ReportEventReader(eventReader, sourceName);
    		parser.parseIds(new ListBuilderHandler(idList));
            
	    	eventReader.close();
    	} catch (Throwable t) {
    		throw new RuntimeException(t);
        } finally {
    		try {
	    		if (eventReader != null) {
	    			eventReader.close();
	    		}
    		} catch (Throwable t) {
        		throw new RuntimeException(t);
    		}
    	}
    }
}
