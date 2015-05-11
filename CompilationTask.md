The C preprocessor contains some predefined macros that can be used to identify the date when your pogram was compiled:

```
printf("%s Compiled on %s at %s.\n",argv[0],__DATE__,__TIME__);
```



Java has no preprocessor, and is missing this kind of information: I wrote a custom ant task generating a java file called Compilation.java and containing all the needed informations.


# In build.xml #
```
<taskdef name="compileInfoTask"
                 classname="org.lindenb.ant.CompileInfoTask"
                 classpath="build/ant"/>
(...)
<compileInfoTask name="Pubmed2Wikipedia" package="org.lindenb.util" dir="build/compile"/>
```



# Result #

```
package org.lindenb.util;
import java.util.GregorianCalendar;
public class Compilation
 {
 private Compilation() {}
 public static String getName() { return "Pubmed2Wikipedia";}
 public static String getPath() { return "~/lindenb";}
 public static String getUser() { return "pierre";}
 public static GregorianCalendar getCalendar() { return new GregorianCalendar(2007,10,1,22,30,11);}
 public static String getDate() {return "2007-11-01 at 22:30:11"; }
 public static String getLabel() { return (getName()==null?"":getName()+" : ")+"Compiled by "+getUser()+" on "+getDate()+" in "+getPath();}
 public static void main(String args[]) { System.out.println(getLabel());}
 }
```