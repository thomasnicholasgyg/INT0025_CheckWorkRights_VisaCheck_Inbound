package com.workday.custom.int0025.ssk149.mediations;

import static com.capeclear.assembly.annotation.Component.Type.mediation;

import java.util.HashSet;

import com.capeclear.assembly.annotation.Component;
import com.capeclear.assembly.annotation.ComponentMethod;
import com.capeclear.mediation.MediationContext;


/**
 * Custom mediation
 *
 * TODO Modify the Component annotation. Also add Property annotations to any
 * bean pattern methods you add and want to appear in the Assembly Editor.
 */
@Component(
        name = "DataStructureBean",
        type = mediation,
        toolTip = "",
        scope = "prototype",
        smallIconPath = "icons/DataStructureBean_16.png",
        largeIconPath = "icons/DataStructureBean_24.png"
        )
public class DataStructureBean {

	private String propertyNameHistoricIdList;
	private String propertyNameCurrentIdList;
	
	public void setPropertyNameHistoricIdList(String propertyNameHistoricIdList) {
		this.propertyNameHistoricIdList = propertyNameHistoricIdList;
	}

	public void setPropertyNameCurrentIdList(String propertyNameCurrentIdList) {
		this.propertyNameCurrentIdList = propertyNameCurrentIdList;
	}

	/**
     * This method is called by the Assembly framework.
     *
     * Use the <code>MediationContext</code> to access objects in the context,
     * such as the message, properties and variables e.g.
     * <ul>
     * <li><code>MediationMessage msg = arg0.getMessage();</code></li>
     * <li><code>String myPropValue = (String)arg0.getProperty("myprop");</code></li>
     * <li><code>Source myVar = arg0.getVariables().getVariable("myvar");</code></li>
     * </ul>    
     */
    @ComponentMethod
    public void process(MediationContext mc) {
        mc.setProperty(propertyNameHistoricIdList, new HashSet<String>());
        mc.setProperty(propertyNameCurrentIdList, new HashSet<String>());
    }
}
