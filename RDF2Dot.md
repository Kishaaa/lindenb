Transforms an XML/RDF input to Graphviz-dot ( http://www.graphviz.org/ )

## Usage ##
```
Options:
 -h help; This screen.
 -p <prefix> <uri> add this prefix mapping
 (rdf stdin | rdf files | rdf urls )
```

## Example ##

```
 xsltproc --html linkedin2foaf.xsl http://www.linkedin.com/in/lindenbaum |\
       java -jar rdf2dot.jar |\
       dot -Tsvg |\
       java -jar svg2canvas.jar > file.html 
```

## Download ##
http://code.google.com/p/lindenb/downloads/list