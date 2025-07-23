<?xml version="1.0" encoding="UTF-8"?>
<!-- THIS FILE IS USED AS A PART OF THE UNIT TESTING FRAMEWORK -->
<!-- PLEASE DO NOT DELETE -->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:err="http://www.w3.org/2005/xqt-errors" 
	xmlns:tube="java:com.capeclear.mediation.impl.cc.MediationTube"
	xmlns:ctx="java:com.capeclear.mediation.MediationContext"
	xmlns:int0025="urn:com.workday.custom.int0025.common" 
    xmlns:wd="urn:com.workday.report/INT_CR_Basic_Demographics"
    exclude-result-prefixes="xs err"
    version="3.0">
    
    <xsl:param name="int0025XsltLibMessage" as="xs:string" select="ctx:getProperty(tube:getCurrentMediationContext(),'int0025XsltLibMessage')" static="yes"/>
	<!--
		FOR DEVELOPMENT AND DEBUG PURPOSES USING THIRD-PARTY TOOLS (e.g. Oxygen XML Editor)
	
		Comment out the parameter definitions above for int0025XsltLibMessage and then uncomment the one below.
		Update the file path to be accurate for your system and workspace.  The one given illustrates a Windows-based PC.	
	-->
    <!-- <xsl:param name="int0025XsltLibMessage" as="xs:string" select="'file:/C:/Users/john.smail/workspace/ConfigCatalog/INT0025_CheckWorkRights_VisaCheck_Inbound/ws/WSAR-INF/xslt/int0025112/SSK112_FunctionLib_Messages.xsl'" static="yes"/> -->

    <xsl:mode streamable="yes" on-no-match="shallow-copy"/>
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
				        <xsl:message select="int0025:createMessage('warn', 'Error occurred during date calculation', $err:description, wd:Worker/wd:ID[@wd:type = 'Employee_ID'], '', xs:string(static-base-uri()), xs:string($err:code), wd:DOB)"/>
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
    <xsl:include _href="{$int0025XsltLibMessage}"/>
</xsl:stylesheet>
