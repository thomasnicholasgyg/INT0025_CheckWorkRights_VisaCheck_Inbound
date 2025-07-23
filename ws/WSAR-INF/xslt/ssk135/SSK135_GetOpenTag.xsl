<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="3.0">
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:output method="text"/>
    
    <!-- Obtains the name of the root node and all of its namespaces from the input XML document -->
    <xsl:template match="/*">
        <xsl:value-of select="concat('&lt;',node-name())"/>
        
        <xsl:for-each select="namespace-node()/copy-of()">
            <xsl:value-of select="concat(' xmlns', if (not(empty(./node-name()))) then concat(':', ./node-name()) else '', '=&quot;', ., '&quot;')"/>
        </xsl:for-each>
        <xsl:value-of select="'>'"/>
    </xsl:template>
</xsl:stylesheet>
