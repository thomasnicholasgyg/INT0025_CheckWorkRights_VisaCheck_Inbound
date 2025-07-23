<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	exclude-result-prefixes="xsd"
	version="3.0">
	
	<xsl:mode streamable="yes" on-no-match="shallow-skip"/>
    <xsl:mode streamable="no" on-no-match="shallow-copy" name="replicate"/>
	
	<xsl:output method="xml" indent="no" encoding="UTF-8"/>
	<xsl:param name="inNextChainEventLinkWID" as="xsd:string"/>
	<xsl:param name="inAggregatedTabName" as="xsd:string"/>
	
	<xsl:template match="/Chain">
		<xsl:copy>
			<xsl:attribute name="filename" select="@filename"/>
			<xsl:apply-templates select="*/copy-of()" mode="replicate"/>
			<Event tab="{$inAggregatedTabName}"><xsl:value-of select="$inNextChainEventLinkWID"/></Event>			
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>
