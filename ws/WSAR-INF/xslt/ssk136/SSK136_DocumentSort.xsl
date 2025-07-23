<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="#all"
    version="3.0">
    
    <xsl:mode streamable="no" on-no-match="shallow-skip"/>
    <xsl:mode streamable="no" on-no-match="shallow-copy" name="replicate"/>
    
    <xsl:output indent="no"/>
    
    <xsl:template match="/Documents">
        <Documents>
            <xsl:for-each select="Document">
            	<xsl:sort select="Filename" order="descending"/>
            	
            	<xsl:apply-templates select="." mode="replicate"/>            
            </xsl:for-each>
        </Documents>
    </xsl:template>
</xsl:stylesheet>
