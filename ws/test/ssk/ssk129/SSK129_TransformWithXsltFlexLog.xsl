<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:err="http://www.w3.org/2005/xqt-errors" 
    xmlns:wd="urn:com.workday.report/INT_CR_Basic_Demographics"
	xmlns:tube="java:com.capeclear.mediation.impl.cc.MediationTube"
	xmlns:ctx="java:com.capeclear.mediation.MediationContext"
	xmlns:int0025="urn:com.workday.custom.int0025.common" 
    exclude-result-prefixes="xs tube ctx int0025 err"
    version="3.0">
    
    <!--
    <xsl:param name="int0025XsltLibFlexLogMessages" as="xs:string" select="ctx:getProperty(tube:getCurrentMediationContext(),'int0025XsltLibFlexLogMessages')" static="yes"/>
		FOR DEVELOPMENT AND DEBUG PURPOSES USING THIRD-PARTY TOOLS (e.g. Oxygen XML Editor)
	
		Comment out the parameter definitions above for int0025XsltLibMessage and then uncomment the one below.
		Update the file path to be accurate for your system and workspace.  The one given illustrates a Windows-based PC.	
	-->
    <xsl:param name="int0025XsltLibFlexLogMessages" as="xs:string" select="'file:/C:/Users/john.smail/workspace/ConfigCatalog/INT0025_CheckWorkRights_VisaCheck_Inbound/ws/WSAR-INF/xslt/int0025147/SSK147_FunctionLib_Messages.xsl'" static="yes"/>

    <xsl:mode streamable="no" on-no-match="shallow-copy"/>
    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:template match="/">
    	<wd:root>
    	<xsl:for-each select="wd:Report_Data/wd:Report_Entry/copy-of()">
    		<wd:Report_Entry>
	    		<xsl:apply-templates select="wd:Worker"/>
	
	   			<wd:DayAfterDOB>
	    		<xsl:try>
	    			<xsl:value-of select="xs:date(wd:DOB) + xs:dayTimeDuration('P1D')"/>
	    			
		    		<xsl:catch>
					    <!-- to match the aunit test scenario, the log being written to is named "log2" -->
					    <!-- because we're calling a function with no output but with "side-effects", xsl:value-of is the best choice to ensure it is executed.  If it were assigned to a variable, or instance, Saxon would realize the variable is never used and bypass initializing it, i.e. running our log function, entirely. -->
					    <!-- function arguments explanation:
					    	1 = name of flex log to be written with this entry
					    	2 = severity / log level
					    	3 = a set of strings to satisfy xs:string*, so the strings are enclosed in parentheses to support passing a variable number of values the way flex logs support it
					     -->
				        <xsl:value-of select="int0025:flexLog('log2', 'warn', ($err:description, xs:string($err:code), wd:Worker/wd:ID[@wd:type = 'Employee_ID'], xs:string(static-base-uri())))"/>
		    		</xsl:catch>
	    		</xsl:try>
	   			</wd:DayAfterDOB>
			</wd:Report_Entry>
    	</xsl:for-each>
		</wd:root>
    </xsl:template>
    
    
    <!-- 
    	This is essential and do not remove the following line of code.  
    	
    	The XSLT library of message-logging functions is included based on the static param above.
    	The param contains the path to the library file, and it is evaluated before the stylesheet is compiled so it can be set dynamically.
    -->
    <xsl:include _href="{$int0025XsltLibFlexLogMessages}"/>

</xsl:stylesheet>
