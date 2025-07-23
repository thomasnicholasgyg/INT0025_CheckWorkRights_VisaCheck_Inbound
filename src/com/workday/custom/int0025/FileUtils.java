package com.workday.custom.int0025;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.transform.stream.StreamSource;

import com.capeclear.mediation.MediationContext;
import com.capeclear.utils.api.IDisposable;

/**
 * This class provides a means to create and use temporary files and directories within a Studio integration.  The creation of files/directories managed through the static
 * methods ensures that the files/directories are cleaned up by the integration framework at the end of the integration.
 * 
 * @author Doug lee
 *
 */
public class FileUtils {
	
	/**
	 * Create a temporary directory and add it to the MediationContext disposables list so that we
	 * can ensure that it is deleted when the integration completes
	 * 
	 * @param ctx
	 * @param name The basename of the directory to create. e.g. MyDirectory
	 * @return an instance of <code>TempDirectory</code> providing access to the temporary directory and ensuring that it is cleaned up at the end of the integration
	 * @throws IOException
	 */
	public static TempDirectory createTempDirectory(MediationContext ctx, String name) throws IOException {
		TempDirectory temp = new TempDirectory(name);
		ctx.addDisposable(temp);
		
		return temp;
	}
	
	/**
	 * Create a temporary file and populate it from the provided StreamSource
	 * 
	 * @param ctx
	 * @param source
	 * @return
	 * @throws IOException
	 */
	public static TempFile createTempFile(MediationContext ctx, StreamSource source) throws IOException {
		TempFile temp = new TempFile(source);
		ctx.addDisposable(temp);
		
		return temp;
	}
	
	/**
	 * Create a placeholder for a temporary file within a given temporary directory.
	 * @param ctx
	 * @param tmpDir
	 * @param filename
	 * @return
	 */
	
	public static TempFile createTempFile(MediationContext ctx, TempDirectory tmpDir, String filename) {
		TempFile temp = tmpDir.createFile(filename);
		ctx.addDisposable(temp);
		
		return temp;
	}
	
	/**
	 * Create a placeholder for a temporary file within the default temporary directory
	 * @param ctx
	 * @param prefix The prefix to be used for the temporary file
	 * @param suffix The suffix to be used for the temporary file (e.g. 
	 * @return
	 * @throws IOException
	 */
	public static TempFile createTempFile(MediationContext ctx, String prefix, String suffix) throws IOException {
		TempFile temp = new TempFile(prefix, suffix);
		ctx.addDisposable(temp);
		return temp;
	}
	
	public static void deleteDirectory(TempDirectory dir) {
		TempDirectory.deleteDirectory(dir.tempDirectory);
	}
	
	/**
	 * The TempFile class provides a means to safely create and use temporary files within a Studio integration
	 * 
	 */
	public static class TempFile implements IDisposable {
		Path tempFile;
		
		TempFile(StreamSource source) throws IOException {
			tempFile = Files.createTempFile("tmpfile", "tmp");
			copyToFile(source);
		}
		
		TempFile(Path directory, StreamSource source) throws IOException {
			tempFile = Files.createTempFile(directory, "tmpfile", "tmp");
			copyToFile(source);
		}
		
		TempFile(Path directory, String prefix, String suffix) throws IOException {
			tempFile = Files.createTempFile(directory, "tmpfile", "tmp");
		}
		
		public void copyToFile(StreamSource source) throws IOException {
			try (
				InputStream input = source.getInputStream();
				) 
			{
				copyToFile(input);
			} catch (Throwable t) {
				//
				// If we encounter an error then clean up the temporary file before rethrowing the exception
				try {
					Files.delete(tempFile);
				}
				catch (Throwable tt){
					// Consider logging the inability to delete the temporary file here
				}
				
				throw t;
			}
		}
		
		public void copyToFile(InputStream input) throws IOException {
			byte buffer[] = new byte[65536];
			try (
				OutputStream output = new FileOutputStream(tempFile.toFile());
				) 
			{
				int numberOfBytes;
				do {
					numberOfBytes = input.read(buffer);
					if ( numberOfBytes > 0 ) {
						output.write(buffer, 0, numberOfBytes);
					}
				} while (numberOfBytes > 0);
			} catch (Throwable t) {
				//
				// If we encounter an error then clean up the temporary file before rethrowing the exception
				try {
					Files.delete(tempFile);
				}
				catch (Throwable tt){
					// Consider logging the inability to delete the temporary file here
				}
				
				throw t;
			}
		}
		
		TempFile(String prefix, String suffix) throws IOException {
			tempFile = Files.createTempFile(prefix, suffix);
		}
		
		TempFile(Path path) {
			tempFile = path;
		}
		
		public URI getURI() {
			return tempFile.toUri();
		}
		
		public OutputStream getOutputStream() throws FileNotFoundException {
			return new FileOutputStream(tempFile.toFile());
		}
		
		public InputStream getInputStream() throws FileNotFoundException {
			InputStream returnValue = new FileInputStream(tempFile.toFile());
			return returnValue;
		}
		
		public File getFile() {
			return tempFile.toFile();
		}
		
		public Path getPath() {
			return tempFile;
		}
		
		public boolean exists() {
			return tempFile.toFile().exists();
		}
		
		/*
		 * Delete the directory.  This function will be called when the MediationContext is torn down at the end of our integration
		 */
		@Override
		public void dispose() {
			if (tempFile != null) {
				File tmp = tempFile.toFile();
				if (tmp.exists()) {
					tmp.delete();
				}
				tempFile = null;
			}
		}
	}

	/**
	 * The TempDirectory class provides a means to safely create and use temporary directories within an integration
	 */
	public static class TempDirectory implements IDisposable, Iterable<File> {
		Path tempDirectory;
		
		TempDirectory(String name) throws IOException {
			tempDirectory = Files.createTempDirectory(name);
		}
		
		URI getURI() {
			return tempDirectory.toUri();
		}

		/**
		 * Delete the directory.  This function will be called when the MediationContext is torn down at the end of our integration
		 */
		@Override
		public void dispose() {
			if ( tempDirectory != null ) {
				File tmp = tempDirectory.toFile();
				if (tmp.exists()) {
					deleteDirectory(tempDirectory);
				}
				tempDirectory = null;
			}
		}
		
		public TempFile createFile(String filename) {
			return new TempFile(tempDirectory.resolve(filename));
		}
		
		public TempFile createTempFile(String prefix, String suffix) throws IOException {
			return new TempFile(tempDirectory, prefix, suffix);
		}
		
		/**
		 * Get a Path for the temporary directory.  Note that invoking .getPath().toString() from MVEL will fail as MVEL
		 * attempts to access the forbidden sun.nio package.  Where a String is required MVEL code should instead
		 * call .getPathAsString()
		 * 
		 * @return
		 */
		public Path	getPath() {
			if (tempDirectory == null) {
				throw new RuntimeException("Temporary directory has been deleted and cannot be used");
			}
			return tempDirectory;
		}
		
		/**
		 * Return a String representing the Path.
		 * @return
		 */
		public String getPathAsString() {
			return getPath().toString();
		}
		
		/**
		 * Utility function to recursively delete the contents of this directory and the directory itself
		 * @param dir The directory to be deleted
		 */
		public static void deleteDirectory(Path dir) {
			for (Path path: dir) {
				File file = path.toFile();
				if (file.isDirectory()) {
					deleteDirectory(path);
				}
				file.delete();
			}
			dir.toFile().delete();
		}
		
		public String toString() {
			return getURI().toString();
		}

		public DirectoryStream<Path> directoryStream() throws IOException {
			return Files.newDirectoryStream(getPath());
		}

		@Override
		public Iterator<File> iterator() {
			File files[] = tempDirectory.toFile().listFiles();
			Set<File> fileset = new TreeSet<File>();
			for (File file: files) {
				fileset.add(file);
			}
			return fileset.iterator();
		}
		
		/**
		 * Remove a file present in the directory.  Files which do not exist are ignored.
		 * @param filename
		 */
		
		public void removeFile(String filename) {
			File file = new File(tempDirectory.toFile(), filename);
			if (file.exists()) {
				file.delete();
			}
		}
	}
}
