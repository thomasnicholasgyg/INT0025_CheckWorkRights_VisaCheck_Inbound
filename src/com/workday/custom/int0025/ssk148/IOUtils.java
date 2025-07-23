package com.workday.custom.int0025.ssk148;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * Everyone has an IOUtils class ... but the CapeClear and Workday ones are not public and we can't necessarily depend on the versions of Apache and other libraries
 * which are present in the integration runtime so we'll provide our own implementation.
 * 
 * 
 * @author Doug Lee, John Smail
 *
 */
public class IOUtils {
	
	/**
	 * Copy the contents of the InputStream to the OutputStream using a specified buffer size
	 * 
	 * @param input The InputStream from which the contents are to be read
	 * @param output The OutputStream to which the output is to be written
	 * @param bufferSize The size of the buffer to be allocated for holding the data
	 * @return the number of bytes copied
	 * @throws IOException if an error occurs reading or writing the data
	 */
	public static long copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
		byte buffer[] = new byte[bufferSize];
		int numberOfBytes;
		long numberOfBytesWritten = 0;
		
		do {
			numberOfBytes = input.read(buffer);
			if (numberOfBytes > 0) {
				output.write(buffer, 0, numberOfBytes);
				numberOfBytesWritten += numberOfBytes;
			}
		} while (numberOfBytes >= 0);
		
		return numberOfBytesWritten;
	}
	
	/**
	 * Copy the contents of the InputStream to the OutputStream
	 * @param input The InputStream from which the contents are to be read
	 * @param output The OutputStream to which the output is to be written
	 * @return
	 * @throws IOException
	 */
	public static long copy(InputStream input, OutputStream output) throws IOException {
		return copy(input, output, 65536);
	}
	
	/**
	 * Copy the contents of the Reader to the Writer using a specified buffer size
	 * 
	 * @param reader The Reader from which the contents are to be read
	 * @param writer The Writer to which the output is to be written
	 * @param bufferSize The size of the buffer to be allocated for holding the data
	 * @return the number of characters copied
	 * @throws IOException if an error occurs reading or writing the data
	 */
	public static long copy(Reader reader, Writer writer, int bufferSize) throws IOException {
		char buffer[] = new char[bufferSize];
		int numberOfCharacters;
		long numberOfCharactersWritten = 0;
		do {
			numberOfCharacters = reader.read(buffer);
			if (numberOfCharacters > 0) {
				writer.write(buffer, 0, numberOfCharacters);
				numberOfCharactersWritten += numberOfCharacters;
			}
		} while (numberOfCharacters >= 0);
		
		return numberOfCharactersWritten;
	}
	
	/**
	 * Copy the contents of the Reader to the Writer
	 * 
	 * @param reader The Reader from which the contents are to be read
	 * @param writer The Writer to which the output is to be written
	 * @return the number of characters copied
	 * @throws IOException if an error occurs reading or writing the data
	 */
	public static long copy(Reader reader, Writer writer) throws IOException {
		return copy(reader, writer, 32768);
	}
	
	/**
	 * Copy the text contents of an InputStream to an OutputStream changing the character encoding if required
	 * 
	 * @param input The InputStream from which the contents are to be read
	 * @param inputEncoding The character encoding when converting the input stream to character data
	 * @param output The OutputStream to which the output is to be written
	 * @param outputEncoding The character encoding to be used when converting the character data to bytes
	 * @return the number of characters copied
	 * @throws IOException if an error occurs reading or writing the data
	 */
	public static long copy(InputStream input, String inputEncoding, OutputStream output, String outputEncoding) throws IOException {
		long count = 0;

		if (outputEncoding.trim().toLowerCase().equals(inputEncoding.trim().toLowerCase())) {
			count = copy(input, output);
		} else {
			// The input and output encodings are not the same so we need to change the encoding
			try(
				InputStreamReader reader = new InputStreamReader(input, inputEncoding);
				OutputStreamWriter writer = new OutputStreamWriter(output, outputEncoding);
			) {
				count = IOUtils.copy(reader,  writer);  			
			}
		}
		
		return count;
	}
	
	/**
	 * Read as many bytes as possible into the target buffer.  Note that some InputStream implementations
	 * do not guarantee to fill a byte array in a single read.  This function guarantees that behaviour since
	 * we will block if necessary and continue reading until either the buffer is full or the end of file
	 * has been reached.
	 * 
	 * @param input
	 * @param buffer
	 * @throws IOException if an error occurs reading the file
	 * @return
	 */	
	public static int read(InputStream input, byte buffer[]) throws IOException {
		int totalBytes = 0;
		int numberOfBytes = 0;
		do {
			numberOfBytes = input.read(buffer, totalBytes, buffer.length - totalBytes);
			if (numberOfBytes > 0) {
				totalBytes += numberOfBytes;
			}
		} while ((numberOfBytes > 0) && (totalBytes < buffer.length));
		
		return totalBytes;
	}
}
