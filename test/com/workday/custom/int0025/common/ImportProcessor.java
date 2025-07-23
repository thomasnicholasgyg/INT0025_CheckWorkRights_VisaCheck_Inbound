package com.workday.custom.int0025.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class ImportProcessor {
	private static final String PACKAGE_MYINT = "int0025";
	private static final String PROJECT_NAME_WITH_UNDERSCORES = "INT0025_CheckWorkRights_VisaCheck_Inbound";
	private static final String PROJECT_NAME_WITH_SPACES = "INT0025 CheckWorkRights VisaCheck Inbound";	
	
	private static final String[] FILE_EXTENSIONS = {"java", "xml", "xsl", "xsd", "json"};
	private static final Set<String> JAVA_KEYWORDS = new HashSet<String>();
	
	static {
		JAVA_KEYWORDS.add("abstract");
		JAVA_KEYWORDS.add("assert");
		JAVA_KEYWORDS.add("boolean");
		JAVA_KEYWORDS.add("break");
		JAVA_KEYWORDS.add("byte");
		JAVA_KEYWORDS.add("case");
		JAVA_KEYWORDS.add("catch");
		JAVA_KEYWORDS.add("char");
		JAVA_KEYWORDS.add("class");
		JAVA_KEYWORDS.add("continue");
		JAVA_KEYWORDS.add("const");
		JAVA_KEYWORDS.add("default");
		JAVA_KEYWORDS.add("do");
		JAVA_KEYWORDS.add("double");
		JAVA_KEYWORDS.add("else");
		JAVA_KEYWORDS.add("enum");
		JAVA_KEYWORDS.add("exports");
		JAVA_KEYWORDS.add("extends");
		JAVA_KEYWORDS.add("final");
		JAVA_KEYWORDS.add("finally");
		JAVA_KEYWORDS.add("float");
		JAVA_KEYWORDS.add("for");
		JAVA_KEYWORDS.add("goto");
		JAVA_KEYWORDS.add("if");
		JAVA_KEYWORDS.add("implements");
		JAVA_KEYWORDS.add("import");
		JAVA_KEYWORDS.add("instanceof");
		JAVA_KEYWORDS.add("int");
		JAVA_KEYWORDS.add("interface");
		JAVA_KEYWORDS.add("long");
		JAVA_KEYWORDS.add("module");
		JAVA_KEYWORDS.add("native");
		JAVA_KEYWORDS.add("new");
		JAVA_KEYWORDS.add("package");
		JAVA_KEYWORDS.add("private");
		JAVA_KEYWORDS.add("protected");
		JAVA_KEYWORDS.add("public");
		JAVA_KEYWORDS.add("requires");
		JAVA_KEYWORDS.add("return");
		JAVA_KEYWORDS.add("short");
		JAVA_KEYWORDS.add("static");
		JAVA_KEYWORDS.add("strictfp");
		JAVA_KEYWORDS.add("super");
		JAVA_KEYWORDS.add("switch");
		JAVA_KEYWORDS.add("synchronized");
		JAVA_KEYWORDS.add("this");
		JAVA_KEYWORDS.add("throw");
		JAVA_KEYWORDS.add("throws");
		JAVA_KEYWORDS.add("transient");
		JAVA_KEYWORDS.add("try");
		JAVA_KEYWORDS.add("var");
		JAVA_KEYWORDS.add("void");
		JAVA_KEYWORDS.add("volatile");
		JAVA_KEYWORDS.add("while");
	}

	Logger log = Logger.getLogger(ImportProcessor.class.getName());
	
	private String integrationNameSeparator;
	private boolean isDebugMode = true;
	private String newPackageName;
	private Map<String, String> fileContentReplacements;
	
	public ImportProcessor(boolean debugMode, File rootDirectory) throws IOException {
		isDebugMode = debugMode;
		
		String integrationName = StringUtils.substringAfterLast(rootDirectory.getCanonicalPath(), File.separator);
		setIntegrationNameSeparator(integrationName);
		String integrationId = StringUtils.substringBefore(integrationName, integrationNameSeparator);
		String newProjectNameWithSpaces = integrationName.replace(integrationNameSeparator.toCharArray()[0], ' ');
		
		if (JAVA_KEYWORDS.contains(StringUtils.lowerCase(integrationId))) {
			String[] nameParts = integrationName.split(integrationNameSeparator);
			StringBuilder sb = new StringBuilder(nameParts[0].toLowerCase());
			for (int i = 1; i < nameParts.length; i++) {
				if (nameParts[i] != null) {
					sb.append(StringUtils.capitalize(nameParts[i].toLowerCase()));
				}
			}
			if (sb.length() > 0 && !StringUtils.isNumeric(sb.substring(0, 1))) {
				newPackageName = sb.toString().replace("_", "").replace("-", "");
			} else {
				newPackageName = "int0025";
			}
			System.out.println("The Integration ID from the project name was read as \"" + integrationId + "\".  However, that is not a legal package name in Java.  The package name will be changed to \"" + newPackageName + "\" to avoid errors.");
		} else {
			newPackageName = integrationId.toLowerCase();
		}

		fileContentReplacements = new HashMap<String, String>();
		fileContentReplacements.put(PACKAGE_MYINT, newPackageName);
		fileContentReplacements.put(PROJECT_NAME_WITH_UNDERSCORES, integrationName);
		fileContentReplacements.put(PROJECT_NAME_WITH_SPACES, newProjectNameWithSpaces);

		if (isDebugMode) {
			System.out.println("The integration id is: " + integrationId);
			System.out.println("The integration name is: " + integrationName);
			System.out.println("The new package name is: " + newPackageName);
			System.out.println("The new integration name with spaces is: " + newProjectNameWithSpaces);
		}
	}
	
	private void setIntegrationNameSeparator(String integrationName) {
		int indexUnderscore = StringUtils.indexOf(integrationName, "_");
		int indexHyphen = StringUtils.indexOf(integrationName, "-");

		if (indexUnderscore < 0 && indexHyphen > 0) {
			integrationNameSeparator = "-";
		} else if (indexHyphen < 0 && indexUnderscore > 0) {
			integrationNameSeparator = "_";
		} else if (indexHyphen < indexUnderscore) {
			integrationNameSeparator = "-";
		} else {
			integrationNameSeparator = "_";
		}
	}

	public void executeRenamingTasks(File rootDirectory) throws Exception {
		processDirectory(rootDirectory);
	}
	
	private void processDirectory(File directory) throws Exception {
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				System.out.println("The directory name is: " + f.getCanonicalPath());
				
				if (!"build".equalsIgnoreCase(f.getName())) {
					processDirectory(f);
				}
				
				if (PACKAGE_MYINT.equalsIgnoreCase(f.getName())) {
					renameDirectory(f, newPackageName);
				}
			} else {
				processFile(f.toPath());
			}
		}
	}
	
	private void renameDirectory(File directory, String newPackageName) throws Exception {
		System.out.println("Renaming directory from " + directory.getName() + " to " + newPackageName);
		String newPackage = directory.getAbsolutePath().replace(PACKAGE_MYINT, newPackageName);
		if (!isDebugMode) {
			directory.renameTo(new File(newPackage));
		}		
	}
	
	private void processFile(Path path) throws Exception {
		String filename = path.toFile().getName(); 
		for (String ext : FILE_EXTENSIONS) {
			if (StringUtils.endsWith(filename, ext)) {
				modifyFileOnMatch(path);
				break;
			}
		}
	}
	
	private void modifyFileOnMatch(Path path) throws Exception {
		System.out.println("Processing file " + path.getFileName() + " for text replacements");
		if (!isDebugMode) {
			try {
				Stream<String> lines = Files.lines(path);
				List<String> replacedLine = lines.map(line -> doReplacements(line)).collect(Collectors.toList());
				Files.write(path, replacedLine);
				lines.close();
			} catch (Throwable t) {
				log.log(Level.ALL, t.getMessage(), t);
			}
		}
	}
	
	private String doReplacements(String line) {
		for (Map.Entry<String, String> entry : fileContentReplacements.entrySet()) {

			if (line.contains(entry.getKey())) {
				line = line.replace(entry.getKey(), entry.getValue());
			}
		}
		return line;
	}
}
