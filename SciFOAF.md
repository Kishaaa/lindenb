SciFOAF builds a RDF/FOAF profile from NCBI/PUBMED

# Introduction #
SciFOAF is the second version of a tool I created to build a FOAF/RDF file from your publications in ncbi/pubmed. The FOAF project defines a semantic format based on RDF/XML to define persons or groups, their relationships, as well as their basic properties such as name, e-mail address, subjects of interest, publications, and so on... The first version was introduced in 2006 here as a java webstart interface and had many problems:

  * the RDF file could not be loaded/saved
  * only a few properties could be edited
  * authors'name definition may vary from one journal to another as some journal may use the initial of an author while another may use the complete first name.
  * the interaction was just a kind of multiple-choice questionnaire

The new version now uses the Jena API, the rdf repository can be loaded and saved.

# Running #

```
export JENA_LIB=your_path_to/Jena/lib
export CLASSPATH=${JENA_LIB}/antlr-2.7.5.jar:${JENA_LIB}/arq-extra.jar:\
 ${JENA_LIB}/arq.jar:${JENA_LIB}/commons-logging-1.1.1.jar:${JENA_LIB}/concurrent.jar:\
 ${JENA_LIB}/icu4j_3_4.jar:${JENA_LIB}/iri.jar:${JENA_LIB}/jena.jar:\
 ${JENA_LIB}/jenatest.jar:${JENA_LIB}/json.jar:${JENA_LIB}/junit.jar:\
 ${JENA_LIB}/log4j-1.2.12.jar:${JENA_LIB}/lucene-core-2.3.1.jar:\
 ${JENA_LIB}/stax-api-1.0.jar:${JENA_LIB}/wstx-asl-3.0.0.jar:\
 ${JENA_LIB}/xercesImpl.jar:${JENA_LIB}/xml-apis.jar:\
 YOUR_PATH_TO/scifoaf.jar
java org.lindenb.scifoaf.SciFOAF
```


# Details #

see full documentation at http://code.google.com/p/lindenb/source/browse/trunk/proj/scifoaf/doc/scifoaf.html