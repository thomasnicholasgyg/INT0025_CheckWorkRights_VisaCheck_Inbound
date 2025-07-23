<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:wd="urn:com.workday/bsvc"
	xmlns:cl="java:com.workday.custom.int0025.int0025112.CloudLogEntry" 
    exclude-result-prefixes="xsd cl"
    version="3.0">

    <!-- The following code is SSK framework code.  It is designed to work with the flow of SSK113 and SSK118 and should not be altered unless you are fundamentally re-writing the whole component. -->   
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:mode name="in-memory" streamable="no" on-no-match="shallow-skip"/> 
    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:param name="inProcessWID" as="xsd:string"/>
    
    <xsl:key name="lookupKey" match="wd:Import_Process_Messages/wd:Import_Process_Message" use="wd:Import_Process_Message_Data/wd:Line_Number"/>

    <xsl:template match="Results">
        <xsl:iterate select="*">
            <xsl:param name="lookup" as="item()?"/>
            
            <xsl:choose>
                <xsl:when test="exists($lookup)">
                    <xsl:apply-templates select=".">
                        <xsl:with-param name="lookup" select="$lookup"/>
                    </xsl:apply-templates>
                    
                    <xsl:next-iteration>
                        <xsl:with-param name="lookup" select="$lookup"/>
                    </xsl:next-iteration>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:next-iteration>
                        <xsl:with-param name="lookup" select="snapshot()"/>
                    </xsl:next-iteration>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:iterate>        
    </xsl:template>
    <!-- This is the end of the SSK framework code.  The following code is specific to the Import WWS and the Cloud Log message you wish to have logged. -->
    
    <xsl:template match="wd:Import_Contract_Contingent_Worker_Request">
        <xsl:param name="lookup" as="item()?"/>
        
        <wd:Messages>
            <xsl:apply-templates select="key('lookupKey', '0' , $lookup)" mode="in-memory"/>                

            <xsl:iterate select="*">
                <xsl:param name="p" as="xsd:integer?"/>
                
                <xsl:choose>
                    <xsl:when test="exists($p)">
                        <xsl:variable name="messageRecord" as="item()*" select="key('lookupKey', xsd:string($p), $lookup)"/>
                        
                        <xsl:if test="exists($messageRecord)">
                            <xsl:apply-templates select=".">
                                <xsl:with-param name="p" select="$p"/>
                                <xsl:with-param name="messageRecord" select="$messageRecord"/>
                            </xsl:apply-templates>
                        </xsl:if>
                        
                        <xsl:next-iteration>
                            <xsl:with-param name="p" select="$p + 1"/>
                        </xsl:next-iteration>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:next-iteration>
                            <xsl:with-param name="p" select="1"/>
                        </xsl:next-iteration>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:iterate>
        </wd:Messages>
    </xsl:template>    
    
    <xsl:template match="wd:Contract_Contingent_Worker_Data">
        <xsl:param name="p" as="xsd:integer"/>
        <xsl:param name="messageRecord" as="item()*"/>
        
        <xsl:variable name="requestRecord" as="item()?" select="copy-of(.)"/>
        
        <xsl:for-each select="$messageRecord">
            <xsl:apply-templates select="." mode="in-memory">
                <xsl:with-param name="requestRecord" select="$requestRecord"/>
            </xsl:apply-templates>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template match="wd:Import_Process_Message" mode="in-memory">
        <xsl:param name="requestRecord" as="item()?"/>
        
		<!-- function options for cl:createMessage are one of the following:
			cl:createMessage(summary)
			cl:createMessage(level, summary)
			cl:createMessage(level, summary, details)
			cl:createMessage(level, summary, details, referenceId)
			cl:createMessage(level, summary, details, referenceId, recordNumber, localIn, errorCode, supportData)
			 -->
        <xsl:message select="cl:createMessage(
            ./wd:Import_Process_Message_Data/wd:Severity/xsd:string(.), 
            'Issue with Import WWS Data', 
            ./wd:Import_Process_Message_Data/wd:Message_Summary/xsd:string(.), 
            $inProcessWID, 
            ./wd:Import_Process_Message_Data/wd:Line_Number/xsd:string(.), 
            'Call_CloudLogXSLTMessages_Dynamic_118', 
            '11802', 
            concat(
            'The Reference Id contains the WID of the background process executing the import.  The timestamp of the error reported by the tenant is ',
            ./wd:Import_Process_Message_Data/wd:Timestamp/xsd:string(.),
            '.  The original import request data was: ',
            normalize-space(serialize($requestRecord))))"/>
    </xsl:template>
</xsl:stylesheet>
