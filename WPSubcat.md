Retrieves the articles of a given category in Wikipedia.
## Requirements ##
java6 and BerkeleyDB java Edition ( http://www.oracle.com/database/berkeley-db/je/index.html )
## Download ##
http://lindenb.googlecode.com/files/wpsubcat.jar
## Usage ##
```
 -debug-level <java.util.logging.Level> default:OFF
 -base <url> default:http://en.wikipedia.org
 -ns <int> restrict to given namespace default:14 (Category)
 -db-home BDB default directory:/tmp/bdb
 -d <integer> max recursion depth default:3
 -add <category> add a starting article
 OR
 (stdin|files) containing articles' titles
```
## Examples ##
Retrieves all the subClasses of 'Category:Scientists'
```
java -cp je-3.3.75.jar:wpsubcat.jar  org.lindenb.tinytools.WPSubCat \
    -add "Category:Scientists" > catscientists.txt
```

Retrieves all the scientists.
```
 java -cp je-3.3.75.jar:wpsubcat.jar org.lindenb.tinytools.WPSubCat  \
     -ns 0 -d 0 catscientists.txt > scientists.txt
```