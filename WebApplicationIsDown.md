Tinytoo tool generating a small J2EE application "xxx is down for maintenance".

# Usage #

```
java -jar webappisdown.jar (options) file.war

```
# Example #
```
java -jar webappisdown.jar $(TOMCAT_HOME)/webapps/locustree.war
```
# Options #
```
Options:
 -f <file> load custom message as file
 -s <s> set custom message as string
 -help (this screen)
 -log-level <level> default:OFF
```
# Download #

http://code.google.com/p/lindenb/downloads/list