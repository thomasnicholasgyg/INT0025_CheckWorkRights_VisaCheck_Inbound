<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:int0025="urn:com.workday.custom.int0025.common" 
    exclude-result-prefixes="xs int0025"
    version="3.0">
    
    <xsl:function name="int0025:flexLog">
    	<xsl:param name="log" as="xs:string"/>
    	<xsl:param name="severity" as="xs:string"/>
    	<xsl:param name="values" as="xs:string*"/>
		
		<xsl:message select="concat(
				$log,
				'&lt;int0025:fl&gt;',
				current-dateTime(), 
				'&lt;int0025:fl&gt;',
				$severity, 
				'&lt;int0025:fl&gt;',
				string-join($values, '&lt;int0025:fl&gt;')
			)"/>
    </xsl:function>
</xsl:stylesheet>
