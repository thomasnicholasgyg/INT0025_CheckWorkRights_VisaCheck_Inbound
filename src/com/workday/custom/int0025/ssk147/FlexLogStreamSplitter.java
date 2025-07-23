package com.workday.custom.int0025.ssk147;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.capeclear.assembly.impl.AssemblyUtils;
import com.capeclear.assembly.model.SplitterStrategyType;
import com.capeclear.capeconnect.attachments.io.FileBackedManagedData;
import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.MediationMessage;
import com.capeclear.mediation.MediationMessageSplitter;
import com.capeclear.mediation.impl.DataHandlerSource;
import com.capeclear.mediation.impl.cc.MediationTube;

/**
 * Custom Splitter strategy.
 *
 * <p>Defines the logic for extracting data records from a bulk message.</p>
 *
 * <p>The implementation must maintain state between invocations; it is therefore 
 * necessary to define it with 'prototype' scope in the Spring configuration.</p>
 * 
 * <p>It is recommended that an implementation also implement <code>MediationContextAware</code>
 * in order to use mediation context specific calls (e.g. using MVEL).</p>
 */
public class FlexLogStreamSplitter implements MediationMessageSplitter {
	private Logger log = LogControl.getLogger(getClass());

	private FileBackedManagedData fbmdOriginal;
	private FlexLog flog;
	
	private BufferedReader reader;
	private String header = null;
	private String line = null;
	
	private int blockSize;
	private int totalLineCount;
	private int totalLogSize;
	
    /**
     * Sets the Mediation Context containing the <code>MediationMessage</code> to be split.
     */
	public void setMediationMessage(MediationMessage message) throws Exception {
		MediationContext mc = MediationTube.getCurrentMediationContext();

		String logName = (String)mc.getProperty("inLogName");
		FlexLogBean bean = (FlexLogBean)mc.getProperty("inFlexLogBean");
		flog = bean.getFlexLog(logName);
		
		fbmdOriginal = flog.getLogData();
		setLongLived(fbmdOriginal, true);
		
		reader = new BufferedReader(new InputStreamReader(fbmdOriginal.getInputStream()));
		header = flog.getHeaderLine();
		
		blockSize = flog.getMaxLinesPerFile();
		totalLogSize = flog.getTotalLineCount();
		totalLineCount = 0;
	}

	/**
	 * If the specified message is stored with FileBackedManagedData then set this to be long lived in order to prevent
	 * garbage collection whilst we are still interested in using it. 
	 * 
	 * @param isLongLived
	 */
	private void setLongLived(FileBackedManagedData fbmd, boolean isLongLived) {
		if (fbmd != null) {
			fbmd.setLongLived(isLongLived);
		}
	}
	
    /**
     * @return the next result of the splitting as a <code>MediationMessage</code>, 
     * or null if there are no more messages.  
     */
	public MediationMessage getNextMessage() throws Exception {
		MediationContext mc = MediationTube.getCurrentMediationContext();
		MediationMessage returnValue = null;
		BufferedWriter writer = null;
		boolean isTeardown = true;
		
		try {
			if (reader != null && totalLineCount < totalLogSize) {
				int blockLineCount = 0;

				FileBackedManagedData fbmdBlock = createManagedData(mc);
				writer = new BufferedWriter(new OutputStreamWriter(fbmdBlock.getOutputStream()));
				writer.write(header);
				
				if (line != null) {
					writer.write(line);
					writer.write(flog.getNewline());
					blockLineCount++;
					totalLineCount++;
				}
				
				while ((line = reader.readLine()) != null) {
					if (blockLineCount == blockSize) {
						isTeardown = false;
						break;
					} else {
						writer.write(line);
						writer.write(flog.getNewline());
						blockLineCount++;
						totalLineCount++;
					}
				}
				
				writer.flush();
				writer.close();
				
				returnValue = createMessage(fbmdBlock);
				fbmdBlock = null;
			}
		} catch (Throwable t) {
			isTeardown = true;
			
			log.error("An exception occurred during the parsing of the FlexLog content.", t);
			throw new RuntimeException(t.getMessage(), t);
		} finally {
			if (isTeardown) {
				if (reader != null) {
					reader.close();
					reader = null;
				}
				
				setLongLived(fbmdOriginal, false);
			}
		}
		
		return returnValue;
	}

	/**
	 * Create a MediationMessage to wrap the output to our FilebackedManagedData root part
	 * 
	 * @param fbmd
	 * @return
	 * @throws IOException
	 */
	private MediationMessage createMessage(FileBackedManagedData fbmd) throws IOException {
		MediationMessage returnValue = AssemblyUtils.getMediationHelper().createMessage();
        returnValue.setRootPart(new DataHandlerSource(fbmd.getDataSource()), fbmd.getContentType());
        returnValue.setHeader("Content-Type", fbmd.getContentType());

        return returnValue;
	}

    private FileBackedManagedData createManagedData(MediationContext mc) {
    	FileBackedManagedData returnValue = new FileBackedManagedData("text/csv");
        mc.addDisposable(returnValue);
        
        return returnValue;
    }

    /**
     * @return a <code>String</code> which will be placed into the 'header'
     * element of the bulk report, or null if this part should not be included.
     */
	public String getHeaderForReport() throws Exception {
		return null;
	}

    /**
     * @return a <code>String</code> which will be placed into the 'footer'
     * element of the bulk report, or null if this part should not be included.
     */
	public String getFooterForReport() throws Exception {
		return null;
	}

    /**
     * Sets the splitter strategy configuration.
     */
	public void setConfig(SplitterStrategyType splitterConfig) {
		// TODO Auto-generated method stub

	}

}
