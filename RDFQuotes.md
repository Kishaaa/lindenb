A java interface storing quotes in a RDF/XML file.

# Download #

download the JAR 'quotes.jar' from: http://code.google.com/p/lindenb/downloads/list

# Usage #

```
java -jar lindenb/build/quotes.jar [-f <rdf-file> ]
```

the option **-f** holds the name of the XML/RDF file where the quotes fill be added. If this file does not exists, it will be created by RDFQuotes.

# The Window #

  * Author: must be a **URL** or a name that must be resolved in wikipedia. For example 'Victor Hugo' will be resolved as http://en.wikipedia.org/wiki/Victor_Hugo
  * Source: a free text. The source of the quote
  * Date: a free text: A date for this quote
  * Keywords: one or more **keywords** ( or **URL** ) , space delimited. Each keyword must be resolved as a **Category** in wikipedia. For example **Scientists**  will be resolved as http://en.wikipedia.org/wiki/Category:Scientists
  * Quote Source: a free text. Where this quote was found
  * lang: the language for this quote

each time a new quote is saved it will be added at the end of the rdf-file.

# Example #

```

<?xml version="1.0" encoding="UTF-8"?>
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:q="urn:ontology:quotes:">
  <q:Quote>
    <q:quote xml:lang="en">A joke's a very serious thing.</q:quote>
    <q:author rdf:resource="http://en.wikipedia.org/wiki/Charles_Churchill_%28satirist%29"/>
    <q:source>The Ghost</q:source>
    <q:date>1762</q:date>
    <q:origin>http://www.quotationspage.com/quote/34239.html</q:origin>
    <q:subject rdf:resource="http://en.wikipedia.org/wiki/Category:Jokes"/>
  </q:Quote>
  <q:Quote>
    <q:quote xml:lang="fr">La vie est autre que ce qu'on écrit.</q:quote>
    <q:author rdf:resource="http://en.wikipedia.org/wiki/André Breton"/>
    <q:source>Nadja (1928), André Breton, éd. Gallimard, coll. nrf, 1928, p. 92</q:source>
    <q:date>1928</q:date>
    <q:origin>http://fr.wikiquote.org/wiki/Andr%C3%A9_Breton</q:origin>
    <q:subject rdf:resource="http://en.wikipedia.org/wiki/Category:Writing"/>
    <q:subject rdf:resource="http://en.wikipedia.org/wiki/Category:Life"/>
  </q:Quote>
(...)

```