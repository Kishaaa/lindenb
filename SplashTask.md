# Introduction #


**Ant**, the java Make can be extended by creating your own custom task. I had fun today by creating a new Ant Task called SplashTask. It generates an new logo on the fly to be used as a java splashScreen (http://java.sun.com/developer/technicalArticles/J2SE/Desktop/javase6/splashscreen/).

# Declaration #

```
(...)    <taskdef name="makeSplash"
             classname="org.lindenb.ant.SplashTask"
             classpath="build/ant"/>
(...)
<target name="splash" depends="compile-ant-tasks">
 <makeSplash title="Hello World !" file="task.jpeg"/>
</target>
(...)
```


# Usage #


```
login> ant x
Buildfile: build.xml

compile-ant-tasks:

splash:
[makeSplash] Saved SplashScreen "Hello World !" to task.jpeg[349 x 85]
```

# Result #

http://farm3.static.flickr.com/2372/1816124122_62c0b5d72b.jpg?v=0