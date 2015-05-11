XML extraction utility
# Motivation #
I'm generating code with XSLT but I want to generate more than one file with the same xslt stylesheet. For example:

```
my-structures.xml + structure2c.xsl  -> src/struct1.h src/struct1.c Makefile README.txt
```

Xar will extract the files from a XML-based archive. A XML archive contains a set of **`<file>`** tags under the root **`<archive>`**. The relative path of the files is declared by the **@path** attribute. The **@content-type** attribute ("text/plain" or "text/html") is optional.

```
<?xml version="1.0"?>
<archive>
 <file path="mydir/file.01.txt">
  Hello World !
 </file>
 <file path="mydir/file.02.text" content-type="text/plain">
  Hello World &lt;!
 </file>
 <file path="mydir/file.02.xml" content-type="text/xml">
  <a>Hello World !<b xmlns="urn:any" att="x">azdpoazd<i/></b></a>
 </file>
</archive>
```

## Options ##
  * -h this screen
  * -X eXclusive create: files should not already exist
  * -N No overwrite: a file will not be over-written if it already exists.
  * -D output directory : where to exand the file (default is the current-directory)
> (stdin|urls|files) sources ending with **.xarz or**.xml.gz will be g-unzipped.

## Example ##
```
%java -jar xar.jar -D /tmp test.xar
/tmp/mydir/file.01.txt ... Done.
/tmp/mydir/file.02.text ... Done.
/tmp/mydir/file.02.xml ... Done.

%more /tmp/mydir/file.02.text 

Hello World <!
```
## Download ##
[http://lindenb.googlecode.com/files/xar.jar](http://lindenb.googlecode.com/files/xar.jar)