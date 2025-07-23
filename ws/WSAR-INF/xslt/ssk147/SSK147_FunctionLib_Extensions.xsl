<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:tube="java:com.capeclear.mediation.impl.cc.MediationTube"
	xmlns:ctx="java:com.capeclear.mediation.MediationContext"
	xmlns:fl="java:com.workday.custom.int0025.int0025147.FlexLogBean" 
	xmlns:cl="java:com.workday.custom.int0025.int0025112.CloudLogEntry" 
	xmlns:int0025="urn:com.workday.custom.int0025.common" 
    exclude-result-prefixes="xs tube ctx fl cl int0025"
    version="3.0">
    
    <xsl:param name="int0025FlexLog" select="ctx:getProperty(tube:getCurrentMediationContext(),'int0025FlexLog')"/>
    
    <xsl:function name="int0025:flexLog">
    	<xsl:param name="log" as="xs:string"/>
    	<xsl:param name="severity" as="xs:string"/>
    	<xsl:param name="values" as="xs:string*"/>
    	
    	<!-- Attempt to write the entry DIRECTLY to the log via extension.  If an error occurs, it will be returned to the variable. -->
    	<xsl:variable name="flexLogError" as="xs:string?" select="fl:writeLogEntryFromXsltExtension($int0025FlexLog, $log, $severity, $values)"/>

    	<xsl:if test="not(empty($flexLogError))">
    		<xsl:message select="cl:createMessage('error', 'FlexLog Entry Error', $flexLogError)"/>
    	</xsl:if>
    </xsl:function>
</xsl:stylesheet>
