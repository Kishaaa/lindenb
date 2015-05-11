SAXScript is an event-driven  [SAX parser](http://en.wikipedia.org/wiki/Simple_API_for_XML) java program invoking **javascript** **callbacks**

## Download ##
Download saxscript.jar from http://code.google.com/p/lindenb/downloads/list

## Invoke ##
```
java -jar saxscript.jar (options) [file|url]s
```
## Callbacks/Examples ##
```
function startDocument()
        {println("Start doc");}
function endDocument()
        {println("End doc");}
function startElement(uri,localName,name,atts)
        {
        print(""+__FILENAME__+" START uri: "+uri+" localName:"+localName);
        for(var i=0;atts!=undefined && i< atts.getLength();++i)
                {
                print(" @"+atts.getQName(i)+"="+atts.getValue(i));
                }
        println("");
        }
function characters(s)
        {println("Characters :" +s);}
function endElement(uri,localName,name)
        {println("END: uri: "+uri+" localName:"+localName);}
```

## Options ##
```
-h (help) this screen
-f <file> read javascript script from file
-e 'script' read javascript script from argument
-D <variable-name> <variable-value> add a variable (as string) in the scripting context.
   __FILENAME__ is the current uri.
-n SAX parser is NOT namespace aware (default true)
-v SAX parser is validating (default false)
```
## Source Code ##
http://code.google.com/p/lindenb/source/browse/trunk/proj/tinytools/src/org/lindenb/tinytools/SAXScript.java


## Example ##
The following shell script invoke NCBI/ESearch to retrieve a key to get all the bibliographic references about **Rotavirus**. This key is then used to download each pubmed entry and we count the number of time each journal ("MedlineTA") was cited.
```
#!/bin/sh
JAVA=${JAVA_HOME}/bin/java
WEBENV=`${JAVA} -jar saxscript.jar \
    -e '
var WebEnv=null;
function startElement(uri,localName,name,atts)
        {
        if(name=="WebEnv") WebEnv="";
        }

function characters(s)
        {
        if(WebEnv!=null) WebEnv+=s;
        }

function endElement(uri,localName,name)
    	{
    	if(WebEnv!=null)
    		{
    		print(WebEnv);
    		WebEnv=null;
    		}
    	}
    ' \
    "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&usehistory=y&retmode=xml&term=Rotavirus"`


${JAVA} -jar saxscript.jar -e '
var content=null;
var hash=new Array();
function startElement(uri,localName,name,atts)
        {
        if(name=="MedlineTA") content="";
        }

function characters(s)
        {
        if(content!=null) content+=s;
        }

function endElement(uri,localName,name)
    	{
    	if(content!=null)
    		{
    		var c=hash[content];
    		hash[content]=(c==null?1:c+1);
    		content=null;
    		}
    	}
function endDocument()
	{
	for(var content in hash)
		{
		println(content+"\t"+ hash[content]);
		}
	}
' "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&query_key=1&WebEnv=${WEBENV}&retmode=xml" |\
sort -t '	' -k2n
```

### Result ###
```
Acta Gastroenterol Latinoam     1
Acta Histochem Suppl    1
Acta Microbiol Acad Sci Hung    1
Acta Microbiol Hung     1
Acta Microbiol Immunol Hung     1
Acta Pathol Microbiol Scand C   1
Acta Vet Acad Sci Hung  1
Adv Neonatal Care       1
Adv Nurse Pract 1
Adv Ther        1
Adv Vet Med     1
Afr J Med Med Sci       1
Age Ageing      1
AIDS Res Hum Retroviruses       1
AJNR Am J Neuroradiol   1
AJR Am J Roentgenol     1
Akush Ginekol (Sofiia)  1
(...)
Appl Environ Microbiol  87
J Pediatr Gastroenterol Nutr    97
J Virol Methods 130
Lancet  130
Vaccine 158
Pediatr Infect Dis J    177
J Gen Virol     217
Arch Virol      254
J Med Virol     262
J Infect Dis    265
Virology        278
J Virol 460
J Clin Microbiol        514
```