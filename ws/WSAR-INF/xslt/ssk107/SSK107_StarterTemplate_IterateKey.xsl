<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:tube="java:com.capeclear.mediation.impl.cc.MediationTube"
    xmlns:ctx="java:com.capeclear.mediation.MediationContext"
    xmlns:int0025="urn:com.workday.custom.int0025.common" 
    xmlns:ns1="urn:the.namespace.of.your.lookup.data"
    xmlns:ns2="urn:the.namespace.of.your.transformation.data"
    exclude-result-prefixes="xsd tube ctx int0025"
    version="3.0">
    
    <xsl:param name="int0025XsltLibMessage" as="xsd:string" select="ctx:getProperty(tube:getCurrentMediationContext(),'int0025XsltLibMessage')" static="yes"/>
    <!--
		FOR DEVELOPMENT AND DEBUG PURPOSES USING THIRD-PARTY TOOLS (e.g. Oxygen XML Editor)
	
		Comment out the parameter definitions above for int0025XsltLibMessage and then uncomment the one below.
		Update the file path to be accurate for your system and workspace.  The one given illustrates a Windows-based PC.	
    <xsl:param name="int0025XsltLibMessage" as="xsd:string" select="'file:/C:/Users/john.smail/workspace/YourWorkspaceFolder/YourAssemblyProjectFolder/ws/WSAR-INF/xslt/int0025112/SSK112_FunctionLib_Messages.xsl'" static="yes"/>
	-->
    
    <xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:mode streamable="no" on-no-match="shallow-skip" name="in-memory"/>
    
    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:key name="mergeKey" match="ns1:nodeToBeReturned_relativeToLookupDataRootNode" use="ns1:nodeToMatchAgainst_relativeToNodeToBeReturned"/>
    
    <xsl:template match="Results">
        <xsl:iterate select="*">
            <xsl:param name="mergeData" as="item()?"/>
            
            <xsl:choose>
                <xsl:when test="exists($mergeData)">
                    <xsl:apply-templates select=".">
                        <xsl:with-param name="mergeData" select="$mergeData"/>
                    </xsl:apply-templates>
                    
                    <xsl:next-iteration>
                        <xsl:with-param name="mergeData" select="$mergeData"/>
                    </xsl:next-iteration>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:next-iteration>
                        <xsl:with-param name="mergeData" select="snapshot()"/>
                    </xsl:next-iteration>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:iterate>        
    </xsl:template>
    
    <xsl:template match="ns2:transformationDataRootNode">
        <xsl:param name="mergeData" as="item()?"/>
        
		<!-- 
			Write your XSLT here to both transform to the new schema as well as lookup/merge to the additional data in $mergeData.
			Use the key() function for lookup/matching with the mergeKey defined above.
		 -->


    </xsl:template>



    <!-- 
    	This is essential and do not remove the following line of code.  
    	
    	The XSLT library of message-logging functions is included based on the static param above.
    	The param contains the path to the library file, and it is evaluated before the stylesheet is compiled so it can be set dynamically.
    -->
    <xsl:include _href="{$int0025XsltLibMessage}"/>
</xsl:stylesheet>
