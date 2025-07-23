<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
    xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:wd="urn:com.workday/bsvc"
    exclude-result-prefixes="xsd env" version="3.0">
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:mode streamable="yes" on-no-match="shallow-copy" name="replicate"/>
    <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
    
    <xsl:param name="inAttachmentServiceName" as="xsd:string"/>
    
    <xsl:template match="/">
        <root>
            <xsl:apply-templates select="env:Envelope/env:Body/wd:Get_Integration_Systems_Response/wd:Response_Data/wd:Integration_System/wd:Integration_System_Data/wd:Integration_Service_Data/copy-of()[wd:Integration_Service_Reference/wd:ID[@wd:type = 'Integration_Service_Name']/text() = $inAttachmentServiceName]/wd:Integration_Attachment_Service_Data/wd:Attachment_Data" mode="replicate"/>
        </root>
    </xsl:template>
</xsl:stylesheet>
