<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:wd="urn:com.workday/bsvc"
    exclude-result-prefixes="#all"
    version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:mode name="in-memory" streamable="no" on-no-match="shallow-skip"/>
    
    <xsl:output indent="no" encoding="UTF-8" method="xml"/>
    
    <xsl:param name="inRequestFilename" as="xs:string"/>
    
    <xsl:template match="/">
        <Documents>
            <xsl:apply-templates select="env:Envelope/env:Body/wd:Get_Event_Documents_Response/wd:Response_Data/wd:Event_Documents/wd:Repository_Document/wd:Repository_Document_Data/copy-of()[@wd:File_Name = $inRequestFilename]" mode="in-memory"/>
        </Documents>
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
