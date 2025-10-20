<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:wd="urn:com.workday/bsvc"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <xsl:output method="xml" indent="yes"/>
    <xsl:param name="VisaCountry"/>
    <xsl:param name="VisaID"/>
    <xsl:param name="VisaSubClass"/>
    <xsl:param name="VisaExpiry"/>
    <xsl:param name="PassportType"/>
    <xsl:param name="PassportCountry"/>
    <xsl:param name="PassportNumber"/>
    <xsl:param name="PassportExpiry"/>
    <xsl:param name="EmployeeId"/>
    <xsl:param name="VisaIssueDate"/>
	<xsl:template match="/">
		<!-- TODO: Auto-generated template -->
<env:Envelope
    xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <env:Body>
        <wd:Change_Passports_and_Visas_Request
            xmlns:wd="urn:com.workday/bsvc"
            wd:version="v44.1">
            <wd:Business_Process_Parameters>
                <wd:Auto_Complete>true</wd:Auto_Complete>
                <wd:Run_Now>true</wd:Run_Now>
                <wd:Discard_On_Exit_Validation_Error>false</wd:Discard_On_Exit_Validation_Error>
            </wd:Business_Process_Parameters>
            <wd:Change_Passports_and_Visas_Data>
                <wd:Person_Reference>
                    <wd:ID wd:type="Employee_ID"><xsl:value-of select="$EmployeeId"/></wd:ID>
                </wd:Person_Reference>
                <wd:Passports_and_Visas_Identification_Data wd:Replace_All="false">
                    <wd:Passport_ID wd:Delete="false">
                        <wd:Passport_ID_Data>
                            <wd:ID><xsl:value-of select="$PassportNumber"/></wd:ID> <!-- Passport Number -->
                            <wd:ID_Type_Reference>
                                <wd:ID wd:type="Passport_ID_Type_ID"><xsl:value-of select="$PassportType"/></wd:ID> <!-- Passport type -->
                            </wd:ID_Type_Reference>
                            <wd:Country_Reference>
                                <wd:ID wd:type="ISO_3166-1_Alpha-3_Code"><xsl:value-of select="$PassportCountry"/></wd:ID> <!-- Passport Country -->
                            </wd:Country_Reference>
                            <wd:Expiration_Date><xsl:value-of select="$PassportExpiry"/></wd:Expiration_Date>
                            <wd:Verification_Date><xsl:value-of select="format-date(current-date(), '[Y0001]-[M01]-[D01]')"/></wd:Verification_Date>
                        </wd:Passport_ID_Data>
                    </wd:Passport_ID>
                    
                    <wd:Visa_ID wd:Delete="false">
                        <wd:Visa_ID_Data>
                            <wd:ID><xsl:value-of select="$VisaID"/></wd:ID> <!-- Visa Number -->
                            <wd:ID_Type_Reference>
                                <wd:ID wd:type="Visa_ID_Type_ID"><xsl:value-of select="$VisaSubClass"/></wd:ID> <!-- Visa Subclass -->
                            </wd:ID_Type_Reference>
                            <wd:Country_Reference>
                                <wd:ID wd:type="ISO_3166-1_Alpha-3_Code"><xsl:value-of select="$VisaCountry"/></wd:ID> <!-- Visa Country -->
                            </wd:Country_Reference>
                            <wd:Expiration_Date><xsl:value-of select="$VisaExpiry"/></wd:Expiration_Date>
                            <wd:Verification_Date><xsl:value-of select="format-date(current-date(), '[Y0001]-[M01]-[D01]')"/></wd:Verification_Date>
                            <wd:Issued_Date><xsl:value-of select="$VisaIssueDate"/></wd:Issued_Date>
                        </wd:Visa_ID_Data>
                    </wd:Visa_ID>
                </wd:Passports_and_Visas_Identification_Data>
            </wd:Change_Passports_and_Visas_Data>
        </wd:Change_Passports_and_Visas_Request>
    </env:Body>
</env:Envelope>
	</xsl:template>
</xsl:stylesheet>