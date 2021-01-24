RSS Reader
==========

[![Build Status](https://github.com/w3stling/rssreader/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)](https://github.com/w3stling/rssreader/actions)
[![Download](https://api.bintray.com/packages/apptastic/maven-repo/rssreader/images/download.svg)](https://bintray.com/apptastic/maven-repo/rssreader/_latestVersion)
[![Javadoc](https://img.shields.io/badge/javadoc-2.4.1-blue.svg)](https://w3stling.github.io/rssreader/javadoc/2.4.1)
[![License](http://img.shields.io/:license-MIT-blue.svg?style=flat-round)](http://apptastic-software.mit-license.org)   
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Arssreader&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.apptastic%3Arssreader)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Arssreader&metric=coverage)](https://sonarcloud.io/component_measures?id=com.apptastic%3Arssreader&metric=Coverage)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Arssreader&metric=bugs)](https://sonarcloud.io/component_measures?id=com.apptastic%3Arssreader&metric=bugs)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Arssreader&metric=vulnerabilities)](https://sonarcloud.io/component_measures?id=com.apptastic%3Arssreader&metric=vulnerabilities)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.apptastic%3Arssreader&metric=code_smells)](https://sonarcloud.io/component_measures?id=com.apptastic%3Arssreader&metric=code_smells)

RSS (Rich Site Summary) is a type of web feed which allows users to access updates to online content in a
standardized, computer-readable format. Subscribing to a website RSS removes the need for the user to manually
check the website for new content.

Atom feeds is supported from version 2.0.0 and later.

This Java library makes it easier to automate data extraction from RSS or Atom feeds via Java stream API.

Examples
--------
### Read RSS feed
Reads from a RSS (or Atom) feeds and extract all items that contains the word football in the title. 
```java
RssReader reader = new RssReader();
Stream<Item> rssFeed = reader.read(URL);
List<Item> articles = rssFeed.filter(i -> i.getTitle().equals(Optional.of("football")))
                             .collect(Collectors.toList());
```

### Merging RSS feeds
Merging several feeds into one feed sored in ascending (oldest first) publication date order and prints the title.
```java
RssReader reader = new RssReader();
Stream.concat(reader.read(URL1),
              reader.read(URL2),  
              reader.read(URL3))
      .sorted()
      .map(Item::getTitle)
      .forEach(System.out::println);
```

Download
--------

Download [the latest JAR][1] or grab via [Maven][2] or [Gradle][3].

### Maven setup
Add JCenter repository for resolving artifact:
```xml
<project>
    ...
    <repositories>
        <repository>
            <id>jcenter</id>
            <url>https://jcenter.bintray.com</url>
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
            <version>2.4.1</version>
        </dependency>
    </dependencies>
    ...
</project>
```

### Gradle setup
Add JCenter repository for resolving artifact:
```groovy
repositories {
    jcenter()
}
```

Add dependency declaration:
```groovy
dependencies {
    implementation 'com.apptastic:rssreader:2.4.1'
}
```

RSS Reader library requires at minimum Java 11.

License
-------

    MIT License
    
    Copyright (c) 2020, Apptastic Software
    
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