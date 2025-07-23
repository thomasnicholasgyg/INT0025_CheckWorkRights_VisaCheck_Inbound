<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:wd="urn:com.workday/bsvc"
	xmlns:tube="java:com.capeclear.mediation.impl.cc.MediationTube"
    xmlns:ctx="java:com.capeclear.mediation.MediationContext"
    xmlns:int0025="urn:com.workday.custom.int0025.common"
    exclude-result-prefixes="xsd tube ctx int0025"
    version="3.0">
    
	<!-- The following code is SSK framework code.  It is designed to work with the flow of SSK113 and SSK118 and should not be altered unless you are fundamentally re-writing the whole component. -->   

    <!--
    <xsl:param name="int0025XsltLibMessage" as="xsd:string" select="ctx:getProperty(tube:getCurrentMediationContext(),'int0025XsltLibMessage')" static="yes"/>

        FOR DEVELOPMENT AND DEBUG PURPOSES USING THIRD-PARTY TOOLS (e.g. Oxygen XML Editor)
	
		Comment out the parameter definitions above for int0025XsltLibMessage and then uncomment the one below.
		Update the file path to be accurate for your system and workspace.  The one given illustrates a Windows-based PC.	
	-->
    <xsl:param name="int0025XsltLibMessage" as="xsd:string" select="'file:/C:/Users/john.smail/workspace/ConfigCatalog/INT0025_CheckWorkRights_VisaCheck_Inbound/ws/WSAR-INF/xslt/int0025112/SSK112_FunctionLib_Messages.xsl'" static="yes"/>

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
    
    <xsl:template match="wd:Import_Ad_hoc_Bank_Transaction_Request">
        <xsl:param name="lookup" as="item()?"/>
        
        <wd:Messages>
        	<!-- Note that this iterate assumes an XML order in the request that has Business_Process_Parameters occurring first followed then by only the Ad_hoc_Bank_Transaction_Data. -->
            <xsl:message select="int0025:createMessage(
                'info', 
                'Ad hoc Bank Transaction Reference', 
                'The transaction reference of the import request is logged in the Support Data.  If the value is blank, then it was not included in the original request.', 
                $inProcessWID, 
                '0', 
                'Call_CloudLogXSLTMessages_Dynamic_118', 
                '', 
                normalize-space(serialize(wd:Ad_hoc_Bank_Transaction_Reference/copy-of())))"/>
            
            <xsl:message select="int0025:createMessage(
	            'info', 
	            'Business Process Parameters', 
	            'The business process parameters of the import request are logged in the Support Data.  If the value is blank, then they were not included in the original request.', 
	            $inProcessWID, 
	            '0', 
	            'Call_CloudLogXSLTMessages_Dynamic_118', 
	            '', 
	            normalize-space(serialize(wd:Business_Process_Parameters/copy-of())))"/>
        	
            <xsl:apply-templates select="wd:Ad_hoc_Bank_Transaction_Data">
                <xsl:with-param name="lookup" select="$lookup"/>
            </xsl:apply-templates>
        </wd:Messages>
    </xsl:template>    
    
    <xsl:template match="wd:Ad_hoc_Bank_Transaction_Data">
        <xsl:param name="lookup" as="item()?"/>
        
        <xsl:message select="int0025:createMessage(
            'info', 
            'Transaction Date', 
            'The transaction date of the import request is logged in the Support Data.', 
            $inProcessWID, 
            '0', 
            'Call_CloudLogXSLTMessages_Dynamic_118', 
            '', 
            normalize-space(serialize(wd:Transaction_Date/copy-of())))"/>

        <xsl:message select="int0025:createMessage(
            'info', 
            'Transaction Memo', 
            'The transaction memo of the import request is logged in the Support Data.', 
            $inProcessWID, 
            '0', 
            'Call_CloudLogXSLTMessages_Dynamic_118', 
            '', 
            normalize-space(serialize(wd:Transaction_Memo/copy-of())))"/>
        
        <xsl:message select="int0025:createMessage(
            'info', 
            'Company Reference', 
            'The company reference of the import request is logged in the Support Data.', 
            $inProcessWID, 
            '0', 
            'Call_CloudLogXSLTMessages_Dynamic_118', 
            '', 
            normalize-space(serialize(wd:Company_Reference/copy-of())))"/>
        
        <xsl:message select="int0025:createMessage(
            'info', 
            'Currency Reference', 
            'The currency reference of the import request is logged in the Support Data.', 
            $inProcessWID, 
            '0', 
            'Call_CloudLogXSLTMessages_Dynamic_118', 
            '', 
            normalize-space(serialize(wd:Currency_Reference/copy-of())))"/>
        
        <xsl:message select="int0025:createMessage(
            'info', 
            'Bank Account Reference', 
            'The bank account reference of the import request is logged in the Support Data.', 
            $inProcessWID, 
            '0', 
            'Call_CloudLogXSLTMessages_Dynamic_118', 
            '', 
            normalize-space(serialize(wd:Bank_Account_Reference/copy-of())))"/>
        
        <xsl:message select="int0025:createMessage(
            'info', 
            concat('Transaction Amount (', (if (wd:Deposit/xsd:boolean(.)) then 'Deposit' else 'Withdrawal'), ')'), 
            'The amount of the transaction of the import request is logged in the Support Data.', 
            $inProcessWID, 
            '0', 
            'Call_CloudLogXSLTMessages_Dynamic_118', 
            '', 
            normalize-space(serialize(wd:Transaction_Amount/copy-of())))"/>
        
        <xsl:iterate select="wd:Transaction_Line_Replacement_Data">
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
                    <xsl:variable name="line" as="item()?" select="snapshot()"/>
                    
                    <xsl:apply-templates select="key('lookupKey', '0', $lookup)" mode="in-memory"/>

                    <xsl:variable name="messageRecord" as="item()*" select="key('lookupKey', '1', $lookup)"/>
                    <xsl:apply-templates select="$line" mode="in-memory">
                        <xsl:with-param name="messageRecord" select="$messageRecord"/>
                    </xsl:apply-templates>
                    
                    <xsl:next-iteration>
                        <xsl:with-param name="p" select="2"/>
                    </xsl:next-iteration>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:iterate>
    </xsl:template>
    
    <xsl:template match="wd:Transaction_Line_Replacement_Data">
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
        <xsl:variable name="isDataRecord" as="xsd:boolean" select="exists($requestRecord)"/>
        
        <xsl:message select="int0025:createMessage(
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
            '.',
            (if ($isDataRecord) then concat('  The original import request line data was: ', normalize-space(serialize($requestRecord))) else '')))"/>
    </xsl:template>
    
    <!-- 
    	This is essential and do not remove the following line of code.  
    	
    	The XSLT library of message-logging functions is included based on the static param above.
    	The param contains the path to the library file, and it is evaluated before the stylesheet is compiled so it can be set dynamically.
    -->
    <xsl:include _href="{$int0025XsltLibMessage}"/>
</xsl:stylesheet>
