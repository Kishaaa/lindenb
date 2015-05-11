# Introduction #

This project was started on FriendFeed by Andrew Su : http://friendfeed.com/e/afa1d1e4-3466-4ae6-8043-3f5472fb75c1/Anyone-interested-in-a-side-programming-project/

"This project would follow on the Gene Wiki effort, specifically by creating a poster-sized visualization for the growth of the Gene Wiki over time. The project involves using the MediaWiki API, database inserting and querying, and SVG output. Some minimum programming skills are required, but all the specifics could be learned "on the job". I'd estimate 2-4 months work at half-time."

## Requirements ##

  * Java Development Kit 1.6 (JDK contains the java compiler)
  * apache ant (the Makefile for java)
  * Derby (aka JavaDB): the java database. Was formerly packaged in the  JDK (search for derby.jar ). If not, download it from http://developers.sun.com/javadb/downloads/index.jsp

# Compiling #

in your home directory create a file called '.ant-properties'
with the following lines:

```
derby.dir=<YOUR PATH TO JAVADB>
derby.lib=${derby.dir}/lib
```

then

```
export JAV_HOME=_your_jre_path_
cd proj/wikipedia/
ant generate-keys
ant
```
will create a tool in **../../build//genewikistats.jar** downloading the data and storing it in a derby database. and **../../build/revisionsviz.jar** to display the data.

# GeneWikiStats #

## clear the database ##

```
java -cp ${DERBYLIB}/derby.jar:${DERBYLIB}/derbyclient.jar:genewikistats.jar org.gnf.genewiki.GeneWikiAnalysis -f ~/tmp/yourderbyfile -p clear
```

## download the data ##

```
java -cp ${DERBYLIB}/derby.jar:${DERBYLIB}/derbyclient.jar:genewikistats.jar org.gnf.genewiki.GeneWikiAnalysis -f ~/tmp/yourderbyfile -p build
```

## export the data ##

```
java -cp ${DERBYLIB}/derby.jar:${DERBYLIB}/derbyclient.jar:genewikistats.jarorg.gnf.genewiki.GeneWikiAnalysis -f ~/tmp/yourderbyfile -p export2 > file.txt
```

# View the data #

```
java -jar revisionsviz.jar
```