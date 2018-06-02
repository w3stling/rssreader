RSS Reader
==========

This Java library makes it easier to automate data extraction from RSS feeds.

Examples
--------
### Search
Searches all items in the RSS feed that contains the word football in the title. 
```java
RssReader reader = new RssReader();
List<Item> items = reader.read(URL)
                        .filter(i -> i.getTitle().contains("football"))
                        .collect(Collectors.toList());
```

Download
--------

Download [the latest JAR][1] or grab via [Maven][2] or [Gradle][3].

### Maven setup
Add repository for resolving artifact:
```xml
<project>
    ...
    <repositories>
        <repository>
            <id>apptastic-maven-repo</id>
            <url>https://dl.bintray.com/apptastic/maven-repo</url>
        </repository>
    </repositories>
    ...
</project>
```

Add dependency declaration:
```xml
<project>
    ...
    <dependencies>
        <dependency>
            <groupId>com.apptastic</groupId>
            <artifactId>rssreader</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
    ...
</project>
```

### Gradle setup
Add repository for resolving artifact:
```groovy
repositories {
    maven {
        url  "https://dl.bintray.com/apptastic/maven-repo" 
    }
}
```

Add dependency declaration:
```groovy
dependencies {
    implementation 'com.apptastic:rssreader:1.0.0'
}
```

RSS Reader library requires at minimum Java 8.

License
-------

    MIT License
    
    Copyright (c) 2018, Apptastic Software
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


[1]: https://bintray.com/apptastic/maven-repo/rssreader/_latestVersion
[2]: https://maven.apache.org
[3]: https://gradle.org