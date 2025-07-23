package com.workday.custom.int0025.ssk148;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.HashMap;
import java.util.Map;

public class BOMUtils {
	final static Map<String,byte[]> BOMs;
	static int maxLength = 0;
	
	/**
	 * Retrieves a byte array containing the BOM for the specified character encoding.  Note that the returned byte array values
	 * are mutable and provide direct access to the arra
	 * 
	 * @param characterEncoding
	 * @return a byte array containing the BOM for the specified character encoding.  Null if no matching character encoding is found.
	 * @throws IllegalArgumentException
	 */	
	public static byte[] getBOMForEncoding(String characterEncoding) throws IllegalArgumentException {
		if (characterEncoding == null) {
			throw new IllegalArgumentException("Character encoding must be not be null.");
		}
		
		String cleanCharacterEncoding = characterEncoding.trim().toUpperCase();
		
		byte[] bom = BOMs.get(cleanCharacterEncoding);
		if (bom == null) {
			throw new IllegalArgumentException(String.format("No BOM available for character encoding \"%s\"", cleanCharacterEncoding));
		}

		return bom;
	}
	
	/**
     * Read the first few bytes of the InputStream in order to detect a BOM.  If no BOM is found the InputStream is returned to
     * its original state so that the full content can be read from it.
     * 
     * @param input
     * @return The character encoding matching the BOM or null if no BOM is detected
     * @throws IOException if an error occurs reading the input stream
     */    
    public static String findEncodingFromBOM(PushbackInputStream input) throws IOException {
		byte possibleBOM[] = new byte[5];
		int totalBytes = IOUtils.read(input, possibleBOM);
		
		String sourceEncoding = findEncodingFromBOM(possibleBOM);
		
		if (sourceEncoding != null) {
			// Push back any bytes which were not part of the BOM
			pushback(input, possibleBOM, totalBytes, BOMs.get(sourceEncoding).length);
		} else {
			// Push back all of the bytes which were read to try to detect a BOM
			pushback(input, possibleBOM, totalBytes, 0);
		}

		return sourceEncoding;
    }
    
    /**
     * Inspects the provided byte array for the presence of BOM.  Only BOMs commencing at the first byte are considered.
     * 
     * @param possibleBOM An array of bytes to be inspected for a BOM
     * @return The name of the character encoding represented by the detected BOM.  null if no BOM is detected
     */
    public static String findEncodingFromBOM(byte possibleBOM[]) {
		String sourceEncoding = null;

		for (Map.Entry<String, byte[]> entry : BOMs.entrySet()) {
			byte[] bomBytes = entry.getValue();
			
			boolean isBOMmatch = false;

			// Only consider BOMs that are the same size or smaller than the provided array
			if (possibleBOM.length >= bomBytes.length) {
				for (int i = 0; i < bomBytes.length; i++) {
					isBOMmatch = bomBytes[i] == possibleBOM[i];
					if (!isBOMmatch) {
						break;
					}
				}
				
				if (isBOMmatch) {
					sourceEncoding = entry.getKey();
					break;
				}
			}
		}
		
		return sourceEncoding;
    }
    
    private static void pushback(PushbackInputStream input, byte buffer[], int length, int endIndex) throws IOException {
		for (int i = length - 1; i >= endIndex; i--) {
			input.unread(buffer[i]);
		}    	
    }
    
    /**
     * Obtain the maximum number of bytes present in the BOMs that we can handle.
     * 
     * @return The length of the longest BOM that we can handle
     */
    public static int getMaxBOMLength() {
    	return maxLength;
    }
    
    // Populate the lookup data for finding the encoding matching BOM
    static {
    	BOMs = new HashMap<String,byte[]>();
    		
    	BOMs.put("UTF-8", new byte[] {(byte)0xEF, (byte)0xBB, (byte)0xBF});
    	BOMs.put("UTF-16", new byte[] {(byte)0xFE, (byte)0xFF});
    	BOMs.put("UTF-16BE", BOMs.get("UTF-16"));
    	BOMs.put("UTF-16LE", new byte[] {(byte)0xFF, (byte)0xFE});
    	BOMs.put("UTF-32", new byte[] {(byte)0x00, (byte)0x00, (byte)0xFE, (byte)0xFF});
    	BOMs.put("UTF-32BE", BOMs.get("UTF-32"));
    	BOMs.put("UTF-32LE", new byte[] {(byte)0xFE, (byte)0xFF, (byte)0x00, (byte)0x00 });
    	BOMs.put("GB18030", new byte[] {(byte)0x84, (byte)0x31, (byte)0x95, (byte)0x33});

    	for (byte b[] : BOMs.values()) {
    		if (b.length > maxLength) {
    			maxLength = b.length;
    		}
    	}
    }
}
