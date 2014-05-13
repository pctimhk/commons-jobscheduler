<?xml version="1.0" encoding="iso-8859-1"?>
<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	- - - APL/Software GmbH - Berlin ######### generated by ClaviusXPress (http://www.sos-berlin.com) 
	############# 2007-03-29, Stefan Sch�dlich (sglo113) - - - - - - - - - - 
	- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - <docu type="smcw" 
	version="1.0"> <name>JobChain2MindMap</name> <title>Jobkette des Job-Schedulers 
	in Freemind-Mindmap konvertieren</title> <typ>xsl</typ> <description> Jobkette 
	des Job-Schedulers in Freemind-Mindmap konvertieren </description> <date>2007-03-29</date> 
	<copyright>� 2007 by Stefan Sch�dlich</copyright> <author>Stefan Sch�dlich</author> 
	</docu> - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	- - - - - -->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="iso-8859-1" indent="yes" />

	<xsl:template match="/">
		<xsl:element name="map">
			<xsl:attribute name="version">0.8.0</xsl:attribute>
			<xsl:apply-templates select="//job_chain" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="job_chain">
		<xsl:message>
			start processing with jobchain
		</xsl:message>
		<node ID="{generate-id()}" TEXT="{@name}" STYLE="bubble">
			<font NAME="Arial" SIZE="16" BOLD="true" />
			<xsl:call-template name="proceedNode">
				<xsl:with-param name="node" select="job_chain_node[1]" />
				<xsl:with-param name="color" select="'#008000'" />
				<xsl:with-param name="position" select="'right'" />
			</xsl:call-template>
		</node>
	</xsl:template>

	<xsl:template name="proceedNode">
		<xsl:param name="node" />
		<xsl:param name="color" select="'#008000'" />
		<xsl:param name="position" select="'left'" />
		<xsl:param name="image" select="'button_ok'" />
		<xsl:variable name="text">
			<xsl:choose>
				<xsl:when test="$node/@job">
					<xsl:value-of
						select="concat('&lt;html&gt;',$node/@state,'&lt;br&gt;&lt;div style=color:blue;font-weight:normal&gt;job: ',$node/@job,'&lt;/div&gt;&lt;/html&gt;')" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat('&lt;html&gt;',$node/@state,'&lt;/html&gt;')" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<!-- BACKGROUND_COLOR wird im Applet nicht umgesetzt -->
		<node ID="{generate-id()}" TEXT="{$text}" COLOR="{$color}"
			BACKGROUND_COLOR="#9999ff" POSITION="{$position}">
			<font NAME="Arial" SIZE="14" BOLD="true" />
			<icon BUILTIN="{$image}" />
			<edge STYLE="linear" WIDTH="1" />
			<xsl:if test="$node/@next_state">
				<xsl:call-template name="proceedNode">
					<xsl:with-param name="node"
						select="$node/parent::job_chain/job_chain_node[@state=$node/@next_state]" />
					<xsl:with-param name="color" select="'#008000'" />
					<xsl:with-param name="position" select="$position" />
					<xsl:with-param name="image" select="'button_ok'" />
				</xsl:call-template>
			</xsl:if>
			<xsl:if test="$node/@error_state">
				<xsl:call-template name="proceedNode">
					<xsl:with-param name="node"
						select="$node/parent::job_chain/job_chain_node[@state=$node/@error_state]" />
					<xsl:with-param name="color" select="'#cc3300'" />
					<xsl:with-param name="position" select="$position" />
					<xsl:with-param name="image" select="'button_cancel'" />
				</xsl:call-template>
			</xsl:if>
		</node>
	</xsl:template>

	<xsl:template match="text()|@*" />

</xsl:stylesheet>