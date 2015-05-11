Pivot digests tabular source and prints a summary of the data.

# Introduction #

Pivot digests tabular source and prints a summary of the data.


# Requirement #
java 1.5

# Compilation #
The distribution contains a ant 'buix.xml'. Type
```
ant pivot
```
# Example #

## Getting Help ##
```
java -jar pivot.jar -h
Author: Pierre Lindenbaum PhD. 2007
$LastChangedRevision$
 -L 'column1,column2,column3,...' columns for left. (required)
 -T 'column1,column2,column3,...' columns for top. (optional)
 -D 'column1,column3,column3,...' columns for data.(required)
 -p <regex> pattern used to break the input into tokens default:TAB
 -i case insensitive
 -t trim each column
 -null <string> value for null
 -e <string> value for empty string
 -f first line is NOT the header
 -html html output
 -no-vt disable vertical summary
 -no-ht disable horizontal summary
 -default (display option)
 -min (display option)
 -max (display option)
 -sum (display option)
 -mean (display option)
 -count-distinct (display option)
 -stdev (display option)
 -count (display option)
```

## Basic Report ##

> Say, you have the following source of data in CSV format:

```
~/pivot-table> cat test/report01.csv
Country,Factory,Divison,Production,Data1,Who
a,  C ,E,0.914969685,0.291066,Peter
b,C,E,0.467030205,0.845814,John
A,C,F,0.876311547,0.152208,Delphine
b,C,E,0.183959371,0.585537,Christine
A,,E,0.716930316,0.193475,Christelle
B,,E,0.706598677,0.810623,Maud
A,C,  E ,0.971947385,0.173531,Celine
D,C,E,0.672412241,0.484983,Celine
d,C,E,0.747628662,0.151863,Maud
c,C,E,0.234653936,0.366957,Christine
A,C,E,0.138990258,0.491736,Peter
B,D,E,0.865383124,0.910094,Maud
A,D,E,0.919728547,0.265257,Marie
B,D,E,0.50429777,0.893188,christine
A,D,E,0.422491902,0.220351,christine
B,D,E,0.20906335,0.631798,celine
A,D,E,0.757865567,0.571077, Maud
B,D,E,0.483509473,0.332158, peter
A,D,E,0.732946969,0.104455, maud
B,D,E,0.068681562,0.502931, marie
A,D,E,0.997338087,0.567394, marie
D,D,,0.09650606,0.854165, marie
```
we want to make a summary with the first (Country) and the second column (Factory) on the left, the Divison as the top label and the 'Who' column as the observed data.

```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,2 -T 3 -D 6  test/report01.csv
Error Pivot:java.io.IOException:[1011]: Found 1 columns : out of range with left indexes
```
We get this message because the default delimiter is the tabulation. We correct this using the '-p' option.

```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,2 -T 3 -D 6  -p , test/report01.csv
                Divison           E     E       F
Country Factory Data    1       2       3       4       Total
A               default NULL    NULL    Christelle      NULL    Christelle
A       C       default NULL    Celine  Peter   Delphine        {Celine:1;Delphine:1;Peter:1}
A       D       default NULL    NULL    { Maud:1; marie:1; maud:1;Marie:1;christine:1}  NULL    { Maud:1; marie:1; maud:1;Marie:1;christine:1}
B               default NULL    NULL    Maud    NULL    Maud
B       D       default NULL    NULL    { marie:1; peter:1;Maud:1;celine:1;christine:1} NULL    { marie:1; peter:1;Maud:1;celine:1;christine:1}
D       C       default NULL    NULL    Celine  NULL    Celine
D       D       default  marie  NULL    NULL    NULL     marie
a         C     default NULL    NULL    Peter   NULL    Peter
b       C       default NULL    NULL    {Christine:1;John:1}    NULL    {Christine:1;John:1}
c       C       default NULL    NULL    Christine       NULL    Christine
d       C       default NULL    NULL    Maud    NULL    Maud
                default  marie  Celine  { Maud:1; marie:2; maud:1; peter:1;Celine:1;Christelle:1;Christine:2;John:1;Marie:1;Maud:3;Peter:2;celine:1;christine:2}Delphine        { Maud:1; marie:3; maud:1; peter:1;Celine:2;Christelle:1;Christine:2;Delphine:1;John:1;Marie:1;Maud:3;Peter:2;celine:1;christine:2}
```

### Case sensibility ###

We now enable case-insensitive comparaisons: we use the **'-i'** option.
```
                Divison           E     E       F
Country Factory Data    1       2       3       4       Total
A               default NULL    NULL    Christelle      NULL    Christelle
a         C     default NULL    NULL    Peter   NULL    Peter
A       C       default NULL    Celine  Peter   Delphine        {Celine:1;Delphine:1;Peter:1}
A       D       default NULL    NULL    { marie:1; Maud:2;christine:1;Marie:1} NULL     { marie:1; Maud:2;christine:1;Marie:1}
B               default NULL    NULL    Maud    NULL    Maud
b       C       default NULL    NULL    {Christine:1;John:1}    NULL    {Christine:1;John:1}
B       D       default NULL    NULL    { marie:1; peter:1;celine:1;christine:1;Maud:1} NULL    { marie:1; peter:1;celine:1;christine:1;Maud:1}
c       C       default NULL    NULL    Christine       NULL    Christine
D       C       default NULL    NULL    {Celine:1;Maud:1}       NULL    {Celine:1;Maud:1}
D       D       default  marie  NULL    NULL    NULL     marie
                default  marie  Celine  { marie:2; Maud:2; peter:1;Celine:2;Christelle:1;Christine:4;John:1;Marie:1;Maud:3;Peter:2}     Delphine        { marie:3; Maud:2; peter:1;Celine:3;Christelle:1;Christine:4;Delphine:1;John:1;Marie:1;Maud:3;Peter:2}
```

## Blanks ##

we now, want to remove the blanks surrounding each words, the **'-t'** option is used
```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,2 -T 3 -D 6  -p , -t -i test/report01.csv
                Divison         E       F
Country Factory Data    1       2       3       Total
A               default NULL    Christelle      NULL    Christelle
a       C       default NULL    {Celine:1;Peter:2}      Delphine        {Celine:1;Delphine:1;Peter:2}
A       D       default NULL    {christine:1;marie:2;Maud:2}    NULL    {christine:1;marie:2;Maud:2}
B               default NULL    Maud    NULL    Maud
b       C       default NULL    {Christine:1;John:1}    NULL    {Christine:1;John:1}
B       D       default NULL    {celine:1;christine:1;marie:1;Maud:1;peter:1}  NULL     {celine:1;christine:1;marie:1;Maud:1;peter:1}
c       C       default NULL    Christine       NULL    Christine
D       C       default NULL    {Celine:1;Maud:1}       NULL    {Celine:1;Maud:1}
D       D       default marie   NULL    NULL    marie
                default marie   {Celine:3;Christelle:1;Christine:4;John:1;Marie:3;Maud:5;Peter:3}       Delphine        {Celine:3;Christelle:1;Christine:4;Delphine:1;John:1;Marie:4;Maud:5;Peter:3}
```

### Removing vertical and horizontal summaries ###

The vertical summary was removed using the **'-no-vt'** option

```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,2 -T 3 -D 6  -p , -t -i  -no-vt test/report01.csv
                Divison         E       F
Country Factory Data    1       2       3       Total
A               default NULL    Christelle      NULL    Christelle
a       C       default NULL    {Celine:1;Peter:2}      Delphine        {Celine:1;Delphine:1;Peter:2}
A       D       default NULL    {christine:1;marie:2;Maud:2}    NULL    {christine:1;marie:2;Maud:2}
B               default NULL    Maud    NULL    Maud
b       C       default NULL    {Christine:1;John:1}    NULL    {Christine:1;John:1}
B       D       default NULL    {celine:1;christine:1;marie:1;Maud:1;peter:1}  NULL     {celine:1;christine:1;marie:1;Maud:1;peter:1}
c       C       default NULL    Christine       NULL    Christine
D       C       default NULL    {Celine:1;Maud:1}       NULL    {Celine:1;Maud:1}
D       D       default marie   NULL    NULL    marie
```

The horizontal summary was removed using the **'-no-ht'** option

```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,2 -T 3 -D 6  -p , -t -i  -no-vt -no-ht test/report01.csv
                Divison         E       F
Country Factory Data    1       2       3
A               default NULL    Christelle      NULL
a       C       default NULL    {Celine:1;Peter:2}      Delphine
A       D       default NULL    {christine:1;marie:2;Maud:2}    NULL
B               default NULL    Maud    NULL
b       C       default NULL    {Christine:1;John:1}    NULL
B       D       default NULL    {celine:1;christine:1;marie:1;Maud:1;peter:1}  NULL
c       C       default NULL    Christine       NULL
D       C       default NULL    {Celine:1;Maud:1}       NULL
D       D       default marie   NULL    NULL
```

### Empty Strings ###

The **empty strings** are now replaced with the word "

&lt;empty&gt;

" with the option **-e**

```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,2 -T 3 -D 6  -p , -t -i  -no-vt -no-ht -e '<empty>' test/report01.csv
                Divison <empty> E       F
Country Factory Data    1       2       3
A       <empty> default NULL    Christelle      NULL
a       C       default NULL    {Celine:1;Peter:2}      Delphine
A       D       default NULL    {christine:1;marie:2;Maud:2}    NULL
B       <empty> default NULL    Maud    NULL
b       C       default NULL    {Christine:1;John:1}    NULL
B       D       default NULL    {celine:1;christine:1;marie:1;Maud:1;peter:1}  NULL
c       C       default NULL    Christine       NULL
D       C       default NULL    {Celine:1;Maud:1}       NULL
D       D       default marie   NULL    NULL
```

### NULL ###

The NULL string used for output when there is no data is now replaced with the word "#N/A" with the option **-null**

```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,2 -T 3 -D 6  -p , -t -i  -no-vt -no-ht -e '<empty>' -null "#N/A" test/report01.csv
                Divison <empty> E       F
Country Factory Data    1       2       3
A       <empty> default #N/A    Christelle      #N/A
a       C       default #N/A    {Celine:1;Peter:2}      Delphine
A       D       default #N/A    {christine:1;marie:2;Maud:2}    #N/A
B       <empty> default #N/A    Maud    #N/A
b       C       default #N/A    {Christine:1;John:1}    #N/A
B       D       default #N/A    {celine:1;christine:1;marie:1;Maud:1;peter:1}  #N/A
c       C       default #N/A    Christine       #N/A
D       C       default #N/A    {Celine:1;Maud:1}       #N/A
D       D       default marie   #N/A    #N/A
```

### producing a HTML output ###
use option **-html**

pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,2 -T 3 -D 6  -p , -t -i  -no-vt -no-ht -e '

&lt;empty&gt;

' -null "#N/A" -html test/report01.csv
|Divison| | |

&lt;empty&gt;

|E|F|
|:------|:|:|:----------------|:|:|
|Country|Factory|Data|1 |2 |3 |
|A |

&lt;empty&gt;

|default|#N/A|Christelle|#N/A|
|a |C |default|#N/A|{Celine:1;Peter:2}|Delphine|
|A |D |default|#N/A|{christine:1;marie:2;Maud:2}|#N/A|
|B |

&lt;empty&gt;

|default|#N/A|Maud|#N/A|
|b |C |default|#N/A|{Christine:1;John:1}|#N/A|
|B |D |default|#N/A|{celine:1;christine:1;marie:1;Maud:1;peter:1}|#N/A|
|c |C |default|#N/A|Christine|#N/A|
|D |C |default|#N/A|{Celine:1;Maud:1}|#N/A|
|D |D |default|marie|#N/A|#N/A|

### Numeric Data ###

Let's play with numeric data using the 4th column as the data to be observed. Here is the default output:

```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,3,6 -T 2 -D 4  -p , -t -i  -no-vt -no-ht -e '<empty>' -null "#N/A"  test/report01.csv
                        Factory <empty> C       D
Country Divison Who     Data    1       2       3
A       E       Celine  default #N/A    0.971947385     #N/A
A       E       Christelle      default 0.716930316     #N/A    #N/A
A       E       christine       default #N/A    #N/A    0.422491902
A       E       Marie   default #N/A    #N/A    {0.919728547:1;0.997338087:1}
A       E       Maud    default #N/A    #N/A    {0.732946969:1;0.757865567:1}
a       E       Peter   default #N/A    {0.138990258:1;0.914969685:1}   #N/A
A       F       Delphine        default #N/A    0.876311547     #N/A
B       E       celine  default #N/A    #N/A    0.20906335
b       E       Christine       default #N/A    0.183959371     0.50429777
b       E       John    default #N/A    0.467030205     #N/A
B       E       marie   default #N/A    #N/A    0.068681562
B       E       Maud    default 0.706598677     #N/A    0.865383124
B       E       peter   default #N/A    #N/A    0.483509473
c       E       Christine       default #N/A    0.234653936     #N/A
D       <empty> marie   default #N/A    #N/A    0.09650606
D       E       Celine  default #N/A    0.672412241     #N/A
d       E       Maud    default #N/A    0.747628662     #N/A
```


we now want to print the **min/max/sum/mean/standard-deviation/count** of the observed data

```
pierre@linux:~/pivot-table> java -jar pivot.jar -L 1,3,6 -T 2 -D 4  -p , -t -i  -no-vt -no-ht -e '<empty>' -null "." -sum -mean -min -max -stdev -count test/report01.csv
                        Factory <empty> C       D
Country Divison Who     Data    1       2       3
A       E       Celine  min     .       0.971947385     .
A       E       Celine  max     .       0.971947385     .
A       E       Celine  sum     .       0.971947385     .
A       E       Celine  mean    .       0.971947385     .
A       E       Celine  stdev   .       .       .
A       E       Celine  count   0       1       0
A       E       Christelle      min     0.716930316     .       .
A       E       Christelle      max     0.716930316     .       .
A       E       Christelle      sum     0.716930316     .       .
A       E       Christelle      mean    0.716930316     .       .
A       E       Christelle      stdev   .       .       .
A       E       Christelle      count   1       0       0
A       E       christine       min     .       .       0.422491902
A       E       christine       max     .       .       0.422491902
A       E       christine       sum     .       .       0.422491902
A       E       christine       mean    .       .       0.422491902
A       E       christine       stdev   .       .       .
A       E       christine       count   0       0       1
A       E       Marie   min     .       .       0.919728547
A       E       Marie   max     .       .       0.997338087
A       E       Marie   sum     .       .       1.917066634
A       E       Marie   mean    .       .       0.958533317
A       E       Marie   stdev   .       .       0.054878232018768666
A       E       Marie   count   0       0       2
A       E       Maud    min     .       .       0.732946969
A       E       Maud    max     .       .       0.757865567
A       E       Maud    sum     .       .       1.490812536
A       E       Maud    mean    .       .       0.745406268
A       E       Maud    stdev   .       .       0.01762010962346151
A       E       Maud    count   0       0       2
a       E       Peter   min     .       0.138990258     .
a       E       Peter   max     .       0.914969685     .
a       E       Peter   sum     .       1.053959943     .
a       E       Peter   mean    .       0.5269799715    .
a       E       Peter   stdev   .       0.5487003148929516      .
a       E       Peter   count   0       2       0
A       F       Delphine        min     .       0.876311547     .
A       F       Delphine        max     .       0.876311547     .
A       F       Delphine        sum     .       0.876311547     .
A       F       Delphine        mean    .       0.876311547     .
A       F       Delphine        stdev   .       .       .
A       F       Delphine        count   0       1       0
B       E       celine  min     .       .       0.20906335
B       E       celine  max     .       .       0.20906335
B       E       celine  sum     .       .       0.20906335
B       E       celine  mean    .       .       0.20906335
B       E       celine  stdev   .       .       .
B       E       celine  count   0       0       1
b       E       Christine       min     .       0.183959371     0.50429777
b       E       Christine       max     .       0.183959371     0.50429777
b       E       Christine       sum     .       0.183959371     0.50429777
b       E       Christine       mean    .       0.183959371     0.50429777
b       E       Christine       stdev   .       .       .
b       E       Christine       count   0       1       1
b       E       John    min     .       0.467030205     .
b       E       John    max     .       0.467030205     .
b       E       John    sum     .       0.467030205     .
b       E       John    mean    .       0.467030205     .
b       E       John    stdev   .       .       .
b       E       John    count   0       1       0
B       E       marie   min     .       .       0.068681562
B       E       marie   max     .       .       0.068681562
B       E       marie   sum     .       .       0.068681562
B       E       marie   mean    .       .       0.068681562
B       E       marie   stdev   .       .       .
B       E       marie   count   0       0       1
B       E       Maud    min     0.706598677     .       0.865383124
B       E       Maud    max     0.706598677     .       0.865383124
B       E       Maud    sum     0.706598677     .       0.865383124
B       E       Maud    mean    0.706598677     .       0.865383124
B       E       Maud    stdev   .       .       .
B       E       Maud    count   1       0       1
B       E       peter   min     .       .       0.483509473
B       E       peter   max     .       .       0.483509473
B       E       peter   sum     .       .       0.483509473
B       E       peter   mean    .       .       0.483509473
B       E       peter   stdev   .       .       .
B       E       peter   count   0       0       1
c       E       Christine       min     .       0.234653936     .
c       E       Christine       max     .       0.234653936     .
c       E       Christine       sum     .       0.234653936     .
c       E       Christine       mean    .       0.234653936     .
c       E       Christine       stdev   .       .       .
c       E       Christine       count   0       1       0
D       <empty> marie   min     .       .       0.09650606
D       <empty> marie   max     .       .       0.09650606
D       <empty> marie   sum     .       .       0.09650606
D       <empty> marie   mean    .       .       0.09650606
D       <empty> marie   stdev   .       .       .
D       <empty> marie   count   0       0       1
D       E       Celine  min     .       0.672412241     .
D       E       Celine  max     .       0.672412241     .
D       E       Celine  sum     .       0.672412241     .
D       E       Celine  mean    .       0.672412241     .
D       E       Celine  stdev   .       .       .
D       E       Celine  count   0       1       0
d       E       Maud    min     .       0.747628662     .
d       E       Maud    max     .       0.747628662     .
d       E       Maud    sum     .       0.747628662     .
d       E       Maud    mean    .       0.747628662     .
d       E       Maud    stdev   .       .       .
d       E       Maud    count   0       1       0
```