<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
 	xmlns:xsd="http://www.w3.org/2001/XMLSchema"
    xmlns:wd="urn:com.workday/bsvc"
 	xmlns:is="java:com.workday.esb.intsys.xpath.ParsedIntegrationSystemFunctions"
    xmlns:tv="java:com.workday.esb.intsys.TypedValue"
    xmlns:decrypt="java:DecryptId"
    xmlns:env="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:map="http://www.w3.org/2005/xpath-functions/map"
    xmlns:Id="http://example.com/my-functions"
    exclude-result-prefixes="#all">
    
	<xsl:output method="xml" indent="yes"/>
    <xsl:param name="DecryptKey"/>
    
	    <xsl:template match="/">
        <!-- TODO: Auto-generated template -->
        <xsl:apply-templates select="Root/CandidateData"/>
    </xsl:template>
    <xsl:template match="Root/CandidateData">
        <xsl:variable name="EmpId" select="Payroll_ID"/>
   
        <root>
        <xsl:apply-templates select="Check_Array">
            <xsl:with-param name="EmpId" select="$EmpId"/>
        </xsl:apply-templates>
        </root>		
    </xsl:template>
    <xsl:template match="Check_Array">
        <xsl:param name="EmpId"/>  
        
        <data>
            <EmployeeId>
                <xsl:value-of select="$EmpId"/>
            </EmployeeId>
            
            <xsl:choose>
            	<xsl:when test="xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaSubclass']/File_Metadata_Value) != 'null' and 
            xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'TravelDocumentNumber']/File_Metadata_Value) != 'null'">
            	<xsl:variable name="VisaSubClass" select="CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaSubclass']/File_Metadata_Value"/>     
        	<VisaInf>
            	<Country>
            		<xsl:value-of select="tv:getReferenceData(is:integrationMapReverseLookup('Visa Country', 'Australia'), 'ISO_3166-1_Alpha-3_Code')"/>
            	</Country>
            	<VisaSubclass>
                	<xsl:value-of select="tv:getReferenceData(is:integrationMapReverseLookup('Workday Visa to CWR Visa',$VisaSubClass),'Visa_ID_Type_ID')"/>						
            	</VisaSubclass>
            	<VNumber>
            	<xsl:variable name="VisaNumber" select="CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'TravelDocumentNumber']/File_Metadata_Value"/>
            	<xsl:value-of select="Id:Decryption($VisaNumber,$DecryptKey)"/>
            	</VNumber>
            	<xsl:choose>
            		<xsl:when test="xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaExpiryDate']/File_Metadata_Value) != 'null'">
            			<VisaExpiryDate>
            				<xsl:value-of select="format-date(xs:date(substring-before(xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaExpiryDate']/File_Metadata_Value), 'T')), '[Y0001]-[M01]-[D01]')"/>
            			</VisaExpiryDate>
            		</xsl:when>
            		<xsl:when test="xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Expiry']/File_Metadata_Value) != 'null'">
            			<VisaExpiryDate>
            				<xsl:value-of select="format-date(xs:date(substring-before(xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Expiry']/File_Metadata_Value), 'T')), '[Y0001]-[M01]-[D01]')"/>
            			</VisaExpiryDate>
            		</xsl:when>
            		<xsl:otherwise></xsl:otherwise>
            	</xsl:choose>
            	<VisaIssueDate>
            		<xsl:value-of select="format-date(xs:date(substring-before(xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaGrantDate']/File_Metadata_Value), 'T')), '[Y0001]-[M01]-[D01]')"/>
            	</VisaIssueDate>
            	<VerificationDate>
            		<xsl:value-of select="format-date(current-date(), '[Y0001]-[M01]-[D01]')"/>
            	</VerificationDate>
            </VisaInf>
            	</xsl:when>
            	<xsl:otherwise>
            	<VisaInf>
            <xsl:text>null</xsl:text>
            </VisaInf>
            	</xsl:otherwise>
            </xsl:choose>
            
            <xsl:choose>
            	<xsl:when test="(xs:string(CandidateData/CheckSupportingFiles/CandidateData/Filetype/File_Type_Label) = 'Passport' or 
            	xs:string(CandidateData/CheckSupportingFiles/CandidateData/Filetype/File_Type_Label) = 'ImmiCard') and 
            	xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Country']/File_Metadata_Value) !='null' and
            	xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Number']/File_Metadata_Value) != 'null'">
            	<xsl:variable name="PassCountry" select="CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Country']/File_Metadata_Value"/>
            	<PassportInf>
            	<PIDType>
            			<xsl:value-of select="tv:getReferenceData(is:integrationMapReverseLookup('Passport ID Type', CandidateData/CheckSupportingFiles/CandidateData/Filetype/File_Type_Label), 'Passport_ID_Type_ID')"/>
            		</PIDType>
            		<PCountry>
            			<!-- <xsl:value-of select="tv:getReferenceData(is:integrationMapReverseLookup('Passport Country', $PassCountry), 'ISO_3166-1_Alpha-3_Code')"/> -->
            			<xsl:value-of select="$PassCountry"/>
            		</PCountry>
           	 		<xsl:choose>
            		<xsl:when test="xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Expiry']/File_Metadata_Value) != 'null'">
            			<PExpiry>
            				<xsl:value-of select="format-date(xs:date(substring-before(xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Expiry']/File_Metadata_Value), 'T')), '[Y0001]-[M01]-[D01]')"/>
             			</PExpiry>
            		</xsl:when>
            		<xsl:when test="xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaExpiryDate']/File_Metadata_Value) != 'null'">
            			<PExpiry>
           	 				<xsl:value-of select="format-date(xs:date(substring-before(xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaExpiryDate']/File_Metadata_Value), 'T')), '[Y0001]-[M01]-[D01]')"/>
            			</PExpiry>
            		</xsl:when>
            		<xsl:otherwise>
            		<PExpiry><xsl:text>null</xsl:text></PExpiry>
            		</xsl:otherwise>
            		</xsl:choose>
            	<PNumber>
            	<xsl:variable name="PassportNumber" select="CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Number']/File_Metadata_Value"/>
            		<xsl:value-of select="Id:Decryption($PassportNumber,$DecryptKey)"/>
            	</PNumber>
            	</PassportInf>
            	</xsl:when>
            	<xsl:when test="CandidateData/CheckSupportingFiles/CandidateData/Filetype/File_Type_Label = 'Australian Passport'">
            	<PassportInf> <xsl:text>Australian Citizen</xsl:text></PassportInf>
            	</xsl:when>
            	<xsl:otherwise><PassportInf>
            <xsl:text>null</xsl:text>
            </PassportInf></xsl:otherwise>
            	</xsl:choose>
           
            <!-- 
            	<xsl:choose>
            	<xsl:when test="(xs:string(CandidateData/CheckSupportingFiles/CandidateData/Filetype/File_Type_Label) = 'Passport' or 
            	xs:string(CandidateData/CheckSupportingFiles/CandidateData/Filetype/File_Type_Label) = 'ImmiCard') and 
            	xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Country']/File_Metadata_Value) !='null' and
            	xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Number']/File_Metadata_Value) != 'null'">
            	<xsl:variable name="PassCountry" select="CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Country']/File_Metadata_Value"/>
            	<PassportInf>
            		<PIDType>
            			<xsl:value-of select="tv:getReferenceData(is:integrationMapReverseLookup('Passport ID Type', CandidateData/CheckSupportingFiles/CandidateData/Filetype/File_Type_Label), 'Passport_ID_Type_ID')"/>
            		</PIDType>
            		<PCountry>
            			<xsl:value-of select="tv:getReferenceData(is:integrationMapReverseLookup('Passport Country', $PassCountry), 'ISO_3166-1_Alpha-3_Code')"/>
            		</PCountry>
           	 		<xsl:choose>
            		<xsl:when test="xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Expiry']/File_Metadata_Value) != 'null'">
            			<PExpiry>
            				<xsl:value-of select="format-date(xs:date(substring-before(xs:string(CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Expiry']/File_Metadata_Value), 'T')), '[Y0001]-[M01]-[D01]')"/>
             			</PExpiry>
            		</xsl:when>
            		<xsl:when test="xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaExpiryDate']/File_Metadata_Value) != 'null'">
            			<PExpiry>
           	 				<xsl:value-of select="format-date(xs:date(substring-before(xs:string(CandidateData/CheckResultFile/File_Metadata/CandidateData[File_Metadata_Label = 'VisaExpiryDate']/File_Metadata_Value), 'T')), '[Y0001]-[M01]-[D01]')"/>
            			</PExpiry>
            		</xsl:when>
            		<xsl:otherwise>
            		<PExpiry><xsl:text>null</xsl:text></PExpiry>
            		</xsl:otherwise>
            		</xsl:choose>
            	<PNumber>
            	<xsl:variable name="PassportNumber" select="CandidateData/CheckSupportingFiles/CandidateData/File_Metadata/CandidateData[File_Metadata_Label = 'Passport Number']/File_Metadata_Value"/>
            		<xsl:value-of select="Id:Decryption($PassportNumber,$DecryptKey)"/>
            	</PNumber>
            		</PassportInf>
            	</xsl:when>
            	<xsl:when test="CandidateData/CheckSupportingFiles/CandidateData/Filetype/File_Type_Label = 'Australian Passport'">
            	<PassportInf> <xsl:text>Australian Citizen</xsl:text></PassportInf>
            	</xsl:when>
            	<xsl:otherwise><PassportInf>
            <xsl:text>null</xsl:text>
            </PassportInf></xsl:otherwise>
            	</xsl:choose> -->
           
            
            
        </data>
    </xsl:template>
    <xsl:function name="Id:Decryption">
    	<xsl:param name="EncryptedText" as="xs:string"/>
    	<xsl:param name="Key" as="xs:string"/>
    	<xsl:value-of select="translate(decrypt:decrypt($EncryptedText,$Key),'&quot;','')"/>
<!--     	<xsl:value-of select="$Id"/> -->
    </xsl:function>
</xsl:stylesheet>