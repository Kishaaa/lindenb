Convert a **SVG** file to a **canvas** script.

![http://19.media.tumblr.com/tumblr_ktj36unaOt1qznaooo1_400.jpg](http://19.media.tumblr.com/tumblr_ktj36unaOt1qznaooo1_400.jpg)

# Usage #
```
java -jar svg2canvas.jar [options] (stdin| <svg files> )
```
# Requirement #
java 6
# Download #
**svg2canvas.jar** in http://code.google.com/p/lindenb/downloads/list
# options #
  * -o (fileout)
  * -p (integer) precision default:2
  * -help (this screen)

# Example #
```
java -jar svg2canvas.jar  ~/file.svg 

<html><body>
<div>
<canvas id='ctx1258901170932' width='744.09448819' height='1052.3622047'></canvas>
<script>
function paint1258901170932(){var canvas=document.getElementById('ctx12589011709
32');if (!canvas.getContext) return;var c=canvas.getContext('2d');
c.fillStyle="white";
c.strokeStyle="black";
c.globalAlpha="1.0";
c.lineWidth="1";
c.lineCap="butt";
c.lineJoin="round";
c.mitterLimit="1";
c.textAnchor="start";
c.font="normal normal 12 Courier";
c.fillStyle="#000203";
c.globalAlpha="0.81327801";
c.lineWidth="11.39999961999999911";
c.lineCap="round";
c.mitterLimit="4";
c.beginPath();
c.moveTo(250.52,330.10);
c.bezierCurveTo(249.81,333.11,252.96,338.22,254.56,342.22);
c.bezierCurveTo(255.64,344.94,257.87,348.49,259.61,350.81);
c.bezierCurveTo(261.07,352.76,260.11,358.44,260.11,360.91);
c.bezierCurveTo(260.11,363.90,260.41,366.64,261.12,369.50);
c.bezierCurveTo(261.91,372.65,264.32,374.38,265.67,377.08);
(...)
```