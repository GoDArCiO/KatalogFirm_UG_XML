<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:key name="klucze" match="firma" use="@nip" />

<xsl:template match="/">
  <html>
  <body>
  <h2>Firmy posortowane po nrtel</h2>  
<xsl:apply-templates select="katalog_firm/firmy/firma">
<xsl:sort select="kontakt/telefon" data-type="number" order="descending"/>
</xsl:apply-templates>
  <h2>ich wyswietlenia posortowane po kosztach</h2>  
<xsl:apply-templates select="katalog_firm/wyswietlenia/wyswietlenie">
<xsl:sort select="koszt" data-type="number" order="descending"/>
</xsl:apply-templates>
  </body>
  </html>
</xsl:template>

<xsl:template match="firma">
  <p>
<span style="color:#00ff00">
  <xsl:value-of select="nazwa"/></span>
 -xnip
  <xsl:value-of select="@nip"/>
-iban:
<xsl:value-of select="iban"/>
-telefon:
<xsl:value-of select="kontakt/telefon"/>
-mail:
<xsl:value-of select="kontakt/mail"/>
-pesel:
<xsl:value-of select="zglaszajacy/pesel"/>
-dowod_osobisty:
<xsl:value-of select="zglaszajacy/dowod_osobisty"/>
  </p>

</xsl:template>

<xsl:template match="wyswietlenie">

<p>
 nip firmy: <xsl:value-of select="@nipFirmy"/>
od <xsl:value-of select="dataStart"/> do 
<xsl:value-of select="dataStop"/>
    koszty: <xsl:value-of select="koszt"/>
</p>

</xsl:template>


</xsl:stylesheet>