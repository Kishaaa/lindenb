Metaweb01 is a tool used to test [Freebase](http://www.freebase.com)

# About #
This tool fetchs data from [Freebase](http://www.freebase.com) and create a XUL based timeline.

![http://farm3.static.flickr.com/2012/2264306553_9933aa3f38.jpg](http://farm3.static.flickr.com/2012/2264306553_9933aa3f38.jpg)

See also this blog entry http://plindenbaum.blogspot.com/2008/02/freebase-and-history-of-sciences.html

# How To #
compilation

> ant freebase

running

> java -jar build/freebase.jar

options:

  * -d 

&lt;dir&gt;

 save to directory (required)
  * -c 

&lt;string&gt;

 metaweb cookie (required)
  * -u 

&lt;string&gt;

 base url where the file will be hosted