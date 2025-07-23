package com.workday.custom.int0025.ssk148;

import static com.capeclear.assembly.annotation.Component.Type.mediation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.ArrayDeque;
import java.util.Queue;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import com.capeclear.assembly.annotation.Component;
import com.capeclear.assembly.annotation.ComponentMethod;
import com.capeclear.capeconnect.attachments.io.FileBackedManagedData;
import com.capeclear.logger.LogControl;
import com.capeclear.logger.Logger;
import com.capeclear.mediation.MediationContext;
import com.capeclear.mediation.MediationMessage;
import com.capeclear.mediation.impl.DataHandlerSource;
import com.capeclear.mediation.impl.cc.MediationTube;
import com.capeclear.xml.utils.XmlUtils;


/**
 * Custom mediation step used to configurably alter the content-type of the current message.
 * 
 * Note that this bean ignores the configuring of the following custom step properties:
 * <ul>
 * <li>Input</li>
 * <li>Output</li>
 * <li>Output Mimetype</li>
 * </ul>
 */
@Component(
        name = "CharacterEncodingBean",
        type = mediation,
        toolTip = "Configurably change the character encoding of the incoming message",
        scope = "prototype",
        smallIconPath = "icons/ChangeCharacterEncoding_16.png",
        largeIconPath = "icons/ChangeCharacterEncoding_24.png"
        )
public class CharacterEncodingBean {
	/**
	 * Configuration data.  This means of configuring the custom step should be thread-safe if executing on concurrent child threads.
	 * Each thread will set its own values to the named context properties in isolation, and the bean will retrieve them per instance.
	 */	
	Configuration beanConfig;
	String propertyNameIsRemoveBOM;
	String propertyNameIsAddBOM;
	String propertyNameTargetContentType;
	String propertyNameIsBOMAdded;
	String propertyNameIsBOMRemoved;

	Logger log = LogControl.getLogger(getClass());
	
	static final String EXCEPTION_LOG_MSG = "An error occurred processing, or determining the need for, changing the character encoding %s";

	/**
     * This method is called by the Assembly framework to convert the character encoding, or add a BOM to, a non-XML text message
     * 
     */
    @ComponentMethod
    public void processText(InputStream in) {
    	MediationContext mc = MediationTube.getCurrentMediationContext();
    	
		process(mc, in,  
				new MessageProcessor() {
	    			public boolean processMessage(Configuration config, MediationContext mc, MediationMessage msg, InputStream in, OutputStream out, String inputEncoding, String outputEncoding) {
	    				boolean changed = false;
	    				
	 		    		if (config.isRemoveBOM() || config.isAddBOM() || !inputEncoding.equalsIgnoreCase(outputEncoding)) {
	 		    			try {
		 		    			// First see if we have a BOM on the incoming message
	 		    				MessageStatus msgStatus = new MessageStatus().processBOM(config, msg, inputEncoding, outputEncoding);
	 		    				
	 		    				// If any changes are required to the input message then isRewrite it
								if (msgStatus.isRewrite) {
				    				// If a BOM is to be removed then skip over the existing BOM
				    				if (config.isRemoveBOM() && msgStatus.hasBOM()) {
				    					byte skip[] = new byte[msgStatus.getBOMLength()];
				    					IOUtils.read(in, skip);
				    				}
								
				    				// Add a BOM if one is required
				    				if (config.isAddBOM()) {
				    					out.write(getBOM(outputEncoding));
				    				}

				    				if (msgStatus.isTranscode) {
				    					IOUtils.copy(in, msgStatus.effectiveInputEncoding, out, outputEncoding);
				    				} else {
				    					IOUtils.copy(in, out);
				    				}
				    				
			    					changed = true;
			    				} else {
			    					IOUtils.copy(in, out);
								}
	 		    			} catch (IOException xcpn) {
	 		    				throw new RuntimeException("Error changing character encoding: " + xcpn.getMessage(), xcpn);
	 		    			} finally {
			    				try {
			    					out.flush();
			    					out.close();
			    				} catch (Throwable t) {
				    				throw new RuntimeException("Unable to close the output stream: " + t.getMessage(), t);
			    				}
			    			}
	 		    		}
							
	 		    		return changed;
	    			}
				}
		);
    }

    @ComponentMethod
    public void processXml(InputStream in) {
    	MediationContext mc = MediationTube.getCurrentMediationContext();
    	
    	// An XML input message could have the character encoding specified in the XML prolog, a BOM or the configured input encoding.
    	// We treat the actual encoding as the first of those which is detected
		process(mc, in, 
    			new MessageProcessor() {
    				public boolean processMessage(Configuration config, MediationContext mc, MediationMessage msg, InputStream in, OutputStream out, String inputEncoding, String outputEncoding) {
 		    			// First see if we have a BOM on the incoming message
    					try {
		    				MessageStatus msgStatus = new MessageStatus().processBOM(config, msg, inputEncoding, outputEncoding);
		    				
		    				if (msgStatus.isRewrite) {
	 		    				// If we detected a BOM then skip over the BOM ready for the XML processing
	 		    				if (msgStatus.hasBOM()) {
	 		    					IOUtils.read(in, new byte[BOMUtils.getBOMForEncoding(msgStatus.encodingFromBOM).length]);
	 		    				}
							
	 		    				// For XML messages we always need to process a little the content since the character encoding can be specified within the XML prolog
	 		    				XMLEventReader reader = XmlUtils.getXMLInputFactory().createXMLEventReader(in);
			    				Queue<XMLEvent>	eventQueue = new ArrayDeque<XMLEvent>();
			    				boolean changed = false;
			    				
			    				msgStatus.processXML(config, reader, eventQueue);
	    				
				    			try {
				    				// Add a BOM if one is required
				    				if (config.isAddBOM()) {
				    					out.write(getBOM(outputEncoding));
				    				}

				    				XMLEventWriter writer = XmlUtils.getXMLOutputFactory().createXMLEventWriter(out, outputEncoding);

	    		    				// Write any events that we've captured so far to the output rewriting the StartDocument if we had one
	    		    				XMLEvent e = null;
	    		    				while ((e = eventQueue.poll()) != null) {
	    		    					if (e.isStartDocument()) {
	    		    						StartDocument start = (StartDocument)e;
	    		    						if (start.encodingSet()  && !start.getCharacterEncodingScheme().trim().equals(outputEncoding)) {
	    		    							e = XmlUtils.getXMLEventFactory().createStartDocument(outputEncoding, start.getVersion());
	    		    						}
	    		    					}

	    		    					writer.add(e);
	    		    				}

	    		    				// Copy the remainder of the document
	    		    				writer.add(reader);
	    		    				writer.flush();
	    		    				writer.close();
	    		    				
	    		    				changed = true;
	    		    			} catch (XMLStreamException xse) {
	    		    				throw new RuntimeException("Unable to isRewrite XML: " + xse.getMessage(), xse);
	    		    			}

				    			reader.close();
			    				return changed;
		    				} else {
		    					IOUtils.copy(in, out);
			    				return false;
		    				}
    					} catch (Exception e) {
		    				throw new RuntimeException("Unable to read input XML: " + e.getMessage(), e);
		    			} finally {
		    				try {
		    					out.flush();
		    					out.close();
		    				} catch (Throwable t) {
			    				throw new RuntimeException("Unable to close the output stream: " + t.getMessage(), t);
		    				}
		    			}
    				}
    			}
		);
    }
    
    /**
     * Utility code common to processing text and XML messages
     * 
     * @param mc
     * @param processor
     */
    void process(MediationContext mc, InputStream in, MessageProcessor processor) {
    	try {
    		MediationMessage msg = mc.getMessage();
	    		
			if (msg.getNumberParts() > 1) {
				throw new RuntimeException("Multi-part message detected.  This bean can only process single-part messages.");
			}
			
			beanConfig = new Configuration();
			
			Object contentType = mc.getProperty(propertyNameTargetContentType);
			beanConfig.setTargetContentType((contentType == null) ? msg.getMimeType(0) : String.valueOf(contentType));
			beanConfig.setAddBOM((boolean)mc.getProperty(propertyNameIsAddBOM));
			beanConfig.setRemoveBOM((boolean)mc.getProperty(propertyNameIsRemoveBOM));
			
			String inputEncoding = CharacterUtils.getCharacterEncodingFromContentType(msg.getMimeType(0)).toLowerCase();
			String outputEncoding = CharacterUtils.getCharacterEncodingFromContentType(beanConfig.getTargetContentType()).toLowerCase();

			FileBackedManagedData fbmd = new FileBackedManagedData(beanConfig.getTargetContentType());
			mc.addDisposable(fbmd);
			OutputStream fbmdOutputStream = fbmd.getOutputStream();

			boolean isChanged = processor.processMessage(beanConfig, mc, msg, in, fbmdOutputStream, inputEncoding, outputEncoding);

			msg.setRootPart(new DataHandlerSource(fbmd.getDataSource()), fbmd.getContentType());

			// See if there's a need to change the mimetype on the message
			if (!beanConfig.getTargetContentType().equalsIgnoreCase(msg.getMimeType(0))) {
				msg.setMimeType(0, beanConfig.getTargetContentType());
			}
			
			mc.setProperty(propertyNameIsBOMAdded, (isChanged && beanConfig.isAddBOM));
			mc.setProperty(propertyNameIsBOMRemoved, (isChanged && beanConfig.isRemoveBOM));
    	} catch (Exception e) {
    		String msg = String.format(EXCEPTION_LOG_MSG, e.getMessage());

    		log.error(msg, e);

    		if (!(e instanceof RuntimeException)) {
    			throw new RuntimeException(msg, e);
    		} else {
    			throw (RuntimeException)e;
    		}
	    }
    }

	private byte[] getBOM(String outputEncoding) {
		try {
			return BOMUtils.getBOMForEncoding(outputEncoding);
		} catch (IllegalArgumentException iae) {
			throw new RuntimeException("Configured output encoding has no BOM: " + iae.getMessage(), iae);
		}
	}
    
    public Configuration getConfiguration() {
    	return beanConfig;
    }
    
    public void setConfiguration(Configuration config) {
   		this.beanConfig = config;
    }
    
	public void setPropertyNameIsRemoveBOM(String propertyNameIsRemoveBOM) {
		this.propertyNameIsRemoveBOM = propertyNameIsRemoveBOM;
	}

	public void setPropertyNameIsAddBOM(String propertyNameIsAddBOM) {
		this.propertyNameIsAddBOM = propertyNameIsAddBOM;
	}

	public void setPropertyNameTargetContentType(String propertyNameTargetContentType) {
		this.propertyNameTargetContentType = propertyNameTargetContentType;
	}

	public void setPropertyNameIsBOMAdded(String propertyNameIsBOMAdded) {
		this.propertyNameIsBOMAdded = propertyNameIsBOMAdded;
	}

	public void setPropertyNameIsBOMRemoved(String propertyNameIsBOMRemoved) {
		this.propertyNameIsBOMRemoved = propertyNameIsBOMRemoved;
	}

	class Configuration {
		boolean	isAddBOM = false;
		boolean isRemoveBOM = false;
		String targetContentType = "text/xml; charset=utf-8";
		
		public Configuration() {}
		
		public boolean isAddBOM() {
			return isAddBOM;
		}

		public void setAddBOM(boolean addBOM) {
			this.isAddBOM = addBOM;
		}
		
		public boolean isRemoveBOM() {
			return isRemoveBOM;
		}
		
		public void setRemoveBOM(boolean removeBOM) {
			isRemoveBOM = removeBOM;
		}

		public String getTargetContentType() {
			return targetContentType;
		}

		public void setTargetContentType(String contentType) {
			this.targetContentType = contentType.toLowerCase();
		}
		
		public String getTargetEncoding() {
			return CharacterUtils.getCharacterEncodingFromContentType(targetContentType) ;
		}		
	}
	
	class MessageStatus {
		String effectiveInputEncoding;
		String encodingFromBOM;
		boolean	isRewrite;
		boolean	isTranscode;
		
		MessageStatus() {}
		
		public MessageStatus processBOM(Configuration config, MediationMessage msg, String configInputEncoding, String configOutputEncoding) throws IOException {
			try (
				@SuppressWarnings("unchecked")
				InputStream in = ((StreamSource)msg.getMessage(new Class[] {StreamSource.class})).getInputStream();
			) {
				byte bomBytes[] = new byte[5];
				IOUtils.read(in, bomBytes);
				
				return checkRequiredActions(config, BOMUtils.findEncodingFromBOM(bomBytes), configInputEncoding, configOutputEncoding);
			}
		}
		
		/**
		 * @return The length of the BOM, zero if no BOM has been detected
		 */
		public int getBOMLength() {
			int length = 0;

			if (encodingFromBOM != null) {
				byte bom[] = BOMUtils.getBOMForEncoding(encodingFromBOM);

				if (bom != null) {
					length = bom.length;
				}
			}
			
			return length;
		}
		
		public boolean hasBOM() {
			return encodingFromBOM != null;
		}
		
		private MessageStatus checkRequiredActions(Configuration config, String encodingFromBOM, String configInputEncoding, String configOutputEncoding) {
			this.encodingFromBOM = encodingFromBOM;
			effectiveInputEncoding = (encodingFromBOM != null) ? encodingFromBOM : configInputEncoding;
			
			isRewrite = false;
			
			// We need to modify the document under any of the following conditions:
			//  1. There is no BOM on the incoming file and we need one on the outgoing file
			//  2. There is a BOM on the incoming file but we do not require one on the outgoing file
			//  3. The input character encoding does not match the output character encoding
			
			// If there was a BOM on the input then move the input stream over the input BOM

			// We need to change the current message if there is no BOM required on the output
			isRewrite = (encodingFromBOM != null) && !config.isAddBOM();
			
			// We will be writing the message if there is no BOM on the input and we require a BOM on the output
			if (!isRewrite) {
				isRewrite = (encodingFromBOM == null) && config.isAddBOM();
			}
			
			// We need to isTranscode the file if the input encoding is not the same as the output encoding
			isTranscode = !effectiveInputEncoding.equalsIgnoreCase(configOutputEncoding);
			
			isRewrite = isRewrite || isTranscode;
			return this;
		}
		
		public MessageStatus processBOM(Configuration config, PushbackInputStream in, String configInputEncoding, String configOutputEncoding) throws IOException {
			return checkRequiredActions(config, BOMUtils.findEncodingFromBOM(in), configInputEncoding, configOutputEncoding);
		}
		
		public MessageStatus processXML(Configuration config, XMLEventReader reader, Queue<XMLEvent> eventQueue) throws XMLStreamException {
			XMLEvent event;
			
			// Inspect the XML document to see if the character encoding has been specified on the XML declaration
			// If present, then we'll allow this to override whatever was specified on the content-type of the incoming
			// message
			do {
				event = reader.nextEvent();
				eventQueue.add(event);
				
				if (event.isStartDocument()) {
					StartDocument start = (StartDocument)event;

					if (start.encodingSet()) {
						effectiveInputEncoding = start.getCharacterEncodingScheme().toLowerCase();
					}
				}
			} while (!(event.isStartElement() || event.isStartDocument()));
			
			return this;
		}
	}
	
	interface MessageProcessor {
		/**
		 * Processes the message to change the character encoding and/or add a BOM.
		 * 
		 * @param config the Configuration for the potential changes to be made to the message
		 * @param mc
		 * @param msg
		 * @param inputEncoding
		 * @param outputEncoding
		 * @return true if a change has been made to the content of the file, otherwise false
		 * @throws Exception
		 */
		public boolean processMessage(Configuration config, MediationContext mc, MediationMessage msg, InputStream in, OutputStream out, String inputEncoding, String outputEncoding) throws Exception;
	}
}
