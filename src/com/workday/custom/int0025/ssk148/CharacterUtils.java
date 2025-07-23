package com.workday.custom.int0025.ssk148;

import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;

import com.capeclear.capeconnect.attachments.io.FileBackedManagedData;

/**
 * Utilities in support of character manipulation in Workday assemblies
 * 
 * @author Doug Lee, John Smail
 */
public class CharacterUtils {
	public static final String DEFAULT_CHARSET = "utf-8";

	// Regex Pattern to extract the charset, if present, in a content-type field.
	static Pattern charsetPattern = Pattern.compile("(?:.*charset=)([^()<>@,;:\\\"/\\[\\]?.=\\s]*)");
	
	/**
	 * Obtain the character encoding from a content-type header
	 * @param contentType  a content-type value from which a character set name is to be extracted.  e.g. "text/plain; charset=iso-8859-1"
	 * @param defaultCharset  The name of the character set to be returned if non is specified in the content-type value supplied
	 * @return the character encoding contained in the content-type value or the default_charset if no encoding was specified
	 */
	public static String getCharacterEncodingFromContentType(String contentType, String defaultCharset) {
		String result = defaultCharset;
		
		if (contentType != null && contentType.length() > 0) {
			// If the content-type specifies a charset then extract this given the 
    		Matcher matcher = charsetPattern.matcher(contentType);
	    		
    		if (matcher.find()) {
    			result = matcher.group(1);
    		}
   		}
		
		return result;
	}
	
	/**
	 * Obtain the character encoding from a content-type header defaulting to UTF-8 is no character encoding is specified
	 * @param contentType  a content-type value from which a character set name is to be extracted.  e.g. "text/plain; charset=iso-8859-1"
	 * @return the character encoding contained in the content-type value or utf-8 if no encoding was specified
	 * 
	 */
	public static String getCharacterEncodingFromContentType(String contentType) {
		return getCharacterEncodingFromContentType(contentType, DEFAULT_CHARSET);
	}
	
	/**
	 * This utility function is used to obtain the character encoding specified on the input message to a custom
	 * mediation step.  A mediation step which needs to use this function should either use the DataHandler as
	 * the input parameter - although the DataHandler can be obtained via the MediationMessage from the MediationContext
	 * if you need direct access to those interfaces.
	 * 
	 * @param dataHandler
	 * @return The character encoding specified in the DataHandler or utf-8 if none was specified
	 */
	public static String getCharacterEncoding(DataHandler dataHandler) {
		return getCharacterEncodingFromContentType(dataHandler.getContentType());
	}
	
	/**
	 * This utility function is used to obtain the character encoding specified in the assembly on the output mimetype of a
	 * custom bean mediation step.  This function relies on the assembly framework providing an instance of FileBackedManagedData
	 * as the implementation of the OutputStream.
	 * 
	 * @param out The OutputStream from which the content-type is to be extracted.  This must be an instance of FileBackedManagedData
	 * @return The character encoding specified on the mimetype of the output of the mediation step.  "utf-8" will be returned as a default
	 * if no charset is configured onto the output mimetype.
	 * @throws IllegalArgumentException if the provided OutputStream is not an instance of FileBackedManagedData
	 */
	public static String getCharacterEncoding(OutputStream out) throws IllegalArgumentException {
		return getCharacterEncoding(out, DEFAULT_CHARSET);
	}
	
	/**
	 * This utility function is used to obtain the character encoding specified in the assembly on the output mimetype of a
	 * custom bean mediation step.  This function relies on the assembly framework providing an instance of FileBackedManagedData
	 * as the implementation of the OutputStream.
	 * 
	 * @param out The OutputStream from which the content-type is to be extracted.  This must be an instance of FileBackedManagedData
	 * @return The character encoding specified on the mimetype of the output of the mediation step
	 * @throws IllegalArgumentException if the provided OutputStream is not an instance of FileBackedManagedData
	 */
	public static String getCharacterEncoding(OutputStream out, String defaultCharset)  {
		String charset = defaultCharset;
		
		if (out instanceof FileBackedManagedData) {
			FileBackedManagedData fbmd = (FileBackedManagedData)out;
			
			charset = getCharacterEncodingFromContentType(fbmd.getContentType(), defaultCharset);
		} else {
			throw new IllegalArgumentException("Content-type cannot be extracted from the supplied OutputStream.");
		}

		return charset;
	}
	
	/**
	 * Determine if a character is legal in XML.  For the moment ignore 10000 to 10FFFF as possibilities
	 * @param c
	 * @return
	 */
	public static boolean isCharacterLegalInXML(int c) {
		return 	(		(		(c > 31) &&
								(		(c <= 0xd7ff) || 
										(		(c >= 0xe000) && 
												(c <= 0xfffd) 
										) ||
										(c >= 0x10000) && 
										(c <= 0x10ffff)
								)
						) ||
						(c == 9) || 
						(c == 10) || 
						(c == 13) 
				);
	}
}
