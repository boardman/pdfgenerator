<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <!-- Define the output format as XSL-FO -->
    <xsl:output method="xml" indent="yes"/>

    <!-- Main template to match the root element -->
    <xsl:template match="/">
        <!-- XSL-FO root element -->
        <fo:root>
            <!-- XSL-FO layout master set -->
            <fo:layout-master-set>
                <!-- Define page layout -->
                <fo:simple-page-master master-name="page" page-width="8.5in" page-height="11in">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <!-- XSL-FO page sequence -->
            <fo:page-sequence master-reference="page">
                <!-- XSL-FO flow for the main content -->
                <fo:flow flow-name="xsl-region-body">
                    <!-- Insert your content here -->
                    <!-- Example of an external-graphic that may break across pages -->
                    <fo:block>
                        <fo:external-graphic src="url('path/to/your/image.jpg')" content-width="scale-to-fit" content-height="100%"/>
                        <!-- Ensure the block allows breaking across pages -->
                        <fo:block break-after="page"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>