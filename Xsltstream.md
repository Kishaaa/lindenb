Xsltstream is a simple tool applying an XSLT stylesheet to some fragments of a SAX/XML stream.

## Download ##
Download xsltstream.jar at http://code.google.com/p/lindenb/downloads/list

## Usage ##


  * -x 

&lt;xslt-stylesheet&gt;

 required
  * -p 

&lt;param-name&gt;

 

&lt;param-value&gt;

 (add parameter to the xslt engine)
  * -d depth (0 based) default:-1
  * -q qName target default:null

you can use -d or -q to tell Xsltstream about the fragment of xml that should be processed

## Example ##
The following example transforms all the &lt;Rs&gt; items from [ftp://ftp.ncbi.nih.gov/snp/organisms/human_9606/XML/ds_ch1.xml.gz](ftp://ftp.ncbi.nih.gov/snp/organisms/human_9606/XML/ds_ch1.xml.gz). the output is a series of xml documents.

```
wget http://lindenb.googlecode.com/svn/trunk/src/xsl/dbsnp2rdf.xsl
java -jar xsltstream.jar -x dbsnp2rdf.xsl   -q "Rs" \
     'ftp://ftp.ncbi.nih.gov/snp/organisms/human_9606/XML/ds_ch1.xml.gz' |\
     grep -v "rdf:RDF" | grep -v '<?xml version'
```
Result:
```
<o:SNP rdf:about="http://www.ncbi.nlm.nih.gov/snp/751">
<dc:title>rs751</dc:title>
<o:taxon rdf:resource="http://www.ncbi.nlm.nih.gov/taxonomy/9606"/>
<o:het rdf:datatype="http://www.w3.org/2001/XMLSchema#float">0.27</o:het>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:WIAF"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:HU-CHINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:TSC-CSHL"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:SC_JCM"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:WI_SSAHASNP"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:SSAHASNP"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:PERLEGEN"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:PERLEGEN"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:HGSV"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:KRIBB_YJKIM"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:BCMHGSC_JDW"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:HUMANGENOME_JCVI"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:BGI"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:1000GENOMES"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:1000GENOMES"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA-UK"/>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:Celera/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">86103650</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">86103651</o:end>
<o:orient>+</o:orient>
</o:Mapping>
</o:hasMapping>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:HuRef/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">85971571</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">85971572</o:end>
<o:orient>-</o:orient>
</o:Mapping>
</o:hasMapping>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:reference/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">87630556</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">87630557</o:end>
<o:orient>+</o:orient>
</o:Mapping>
</o:hasMapping>
</o:SNP>
<o:SNP rdf:about="http://www.ncbi.nlm.nih.gov/snp/759">
<dc:title>rs759</dc:title>
<o:taxon rdf:resource="http://www.ncbi.nlm.nih.gov/taxonomy/9606"/>
<o:het rdf:datatype="http://www.w3.org/2001/XMLSchema#float">0.49</o:het>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:WIAF"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:WIAF"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:TSC-CSHL"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:CSHL-HAPMAP"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:SSAHASNP"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:PERLEGEN"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ABI"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:BCMHGSC_JDW"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:HUMANGENOME_JCVI"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:BGI"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:1000GENOMES"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:ILLUMINA-UK"/>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:Celera/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">188082895</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">188082896</o:end>
<o:orient>-</o:orient>
</o:Mapping>
</o:hasMapping>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:HuRef/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">185533439</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">185533440</o:end>
<o:orient>+</o:orient>
</o:Mapping>
</o:hasMapping>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:reference/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">212926298</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">212926299</o:end>
<o:orient>-</o:orient>
</o:Mapping>
</o:hasMapping>
</o:SNP>
<o:SNP rdf:about="http://www.ncbi.nlm.nih.gov/snp/761">
<dc:title>rs761</dc:title>
<o:taxon rdf:resource="http://www.ncbi.nlm.nih.gov/taxonomy/9606"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:WIAF"/>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:Celera/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">136771722</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">136771723</o:end>
<o:orient>-</o:orient>
</o:Mapping>
</o:hasMapping>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:HuRef/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">134912971</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">134912972</o:end>
<o:orient>+</o:orient>
</o:Mapping>
</o:hasMapping>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:reference/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">161934409</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">161934410</o:end>
<o:orient>-</o:orient>
</o:Mapping>
</o:hasMapping>
</o:SNP>
<o:SNP rdf:about="http://www.ncbi.nlm.nih.gov/snp/828">
<dc:title>rs828</dc:title>
<o:taxon rdf:resource="http://www.ncbi.nlm.nih.gov/taxonomy/9606"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:WIAF"/>
<o:hasHandle rdf:resource="urn:void:ncbi:snp:handle:SNP500CANCER"/>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:Celera/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">66444440</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">66444441</o:end>
<o:orient>+</o:orient>
</o:Mapping>
</o:hasMapping>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:HuRef/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">66263837</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">66263838</o:end>
<o:orient>-</o:orient>
</o:Mapping>
</o:hasMapping>
<o:hasMapping>
<o:Mapping>
<o:build rdf:resource="urn:void:ncbi:build:reference/36_3"/>
<o:chrom rdf:resource="urn:void:ncbi:chromosome:9606/chr1"/>
<o:start rdf:datatype="http://www.w3.org/2001/XMLSchema#int">67926165</o:start>
<o:end rdf:datatype="http://www.w3.org/2001/XMLSchema#int">67926166</o:end>
<o:orient>+</o:orient>
</o:Mapping>
</o:hasMapping>
</o:SNP>
</rdf:RDF>
(...)
```