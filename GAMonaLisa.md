GAMonaLisa is a Genetic Programming tool inspired from Roger Alsing's blog ( http://rogeralsing.com/2008/12/07/genetic-programming-evolution-of-mona-lisa ).

This tool uses a **genetic algorithm** to find the best set of colored triangles that can be used to re-create an image

The tool is available as a java jar executable program (JSE 1.6).

http://code.google.com/p/lindenb/downloads/list

# Screenshots #

Original Image (via wikipedia) / algorithm after a few iterations

![http://media.tumblr.com/NngfN9gsDhddlh46o0yPF2m1o1_400.png](http://media.tumblr.com/NngfN9gsDhddlh46o0yPF2m1o1_400.png)


![http://media.tumblr.com/NngfN9gsDhbu9tybOD7LoRCzo1_500.png](http://media.tumblr.com/NngfN9gsDhbu9tybOD7LoRCzo1_500.png)

# Usage/Option #
  * i input image (file|url) (required)
  * d output directory
  * r read previous chilren <.txt> (optional). Read the description of a previous saved result
  * s export SVG
  * n1 numberOfIndividualSurviving=10
  * n2 initialPopulationSize=40
  * n3 max\_generation=-1 -1=forever
  * n4 max Diff time Saving (Millisec)=0
  * n5 max Triangle per solution =250
  * n6 min Triangle per solution =50
  * n7 min Thread count =4