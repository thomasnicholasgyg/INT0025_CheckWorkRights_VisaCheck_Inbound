<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:wd="urn:com.workday/bsvc"
    exclude-result-prefixes="#all"
    version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:mode streamable="no" on-no-match="shallow-skip" name="in-memory"/>
    
    <xsl:output indent="no"/>
    
    <xsl:template match="/">
        <Documents>
            <xsl:apply-templates/>
        </Documents>
    </xsl:template>

    <xsl:template match="wd:Repository_Document">
        <xsl:apply-templates select="wd:Repository_Document_Data/copy-of()[wd:Document_Tag_Reference/wd:ID[@wd:type='Document_Tag_Name'] = 'INT_LogAggregationList']" mode="in-memory"/>
    </xsl:template>

    <xsl:template match="wd:Repository_Document_Data" mode="in-memory">
	    <Document>
	    	<Collection><xsl:value-of select="substring-before(@wd:Document_ID, '/')"/></Collection>
	    	<EntryID><xsl:value-of select="substring-after(@wd:Document_ID, '/')"/></EntryID>
	    	<Filename><xsl:value-of select="@wd:File_Name"/></Filename>
	    	<Filesize><xsl:value-of select="wd:File_Size"/></Filesize>
	    	<Mimetype><xsl:value-of select="wd:Content_Type_Reference/wd:ID[@wd:type = 'Content_Type_ID']"/></Mimetype>
	    </Document>
    </xsl:template>
</xsl:stylesheet>
