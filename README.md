RSS Reader
==========

[![Build](https://github.com/w3stling/rssreader/actions/workflows/build.yml/badge.svg)](https://github.com/w3stling/rssreader/actions/workflows/build.yml)
[![Download](https://img.shields.io/badge/download-3.1.0-brightgreen.svg)](https://search.maven.org/artifact/com.apptasticsoftware/rssreader/3.1.0/jar)
[![Javadoc](https://img.shields.io/badge/javadoc-3.1.0-blue.svg)](https://w3stling.github.io/rssreader/javadoc/3.1.0)
[![License](http://img.shields.io/:license-MIT-blue.svg?style=flat-round)](http://apptastic-software.mit-license.org)   
[![CodeQL](https://github.com/w3stling/rssreader/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/w3stling/rssreader/actions/workflows/codeql-analysis.yml)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=w3stling_rssreader&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=w3stling_rssreader)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=w3stling_rssreader&metric=coverage)](https://sonarcloud.io/summary/new_code?id=w3stling_rssreader)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=w3stling_rssreader&metric=bugs)](https://sonarcloud.io/summary/new_code?id=w3stling_rssreader)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=w3stling_rssreader&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=w3stling_rssreader)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=w3stling_rssreader&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=w3stling_rssreader)

> **Note** - from version 3.0.0:
> * New Java package name
> * New group ID in Maven / Gradle dependency declaration
> * Moved repository from `JCenter` to `Maven Central Repository`

RSS (Rich Site Summary) is a type of web feed which allows users to access updates to online content in a
standardized, computer-readable format. Subscribing to a website RSS removes the need for the user to manually
check the website for new content.

Atom feeds is supported from version 2.0.0 and later.

This Java RSS parser library makes it easier to automate data extraction from RSS or Atom feeds via Java stream API.

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
              reader.read(URL2))
      .sorted()
      .map(Item::getTitle)
      .forEach(System.out::println);
```

### RSS / Atom feed extensions
Support for mapping custom tags and attributes in the RSS / Atom feed to item and channel object.
```java
List<Item> items = new RssReader()
             .addItemExtension("dc:creator", Item::setAuthor)
             .addItemExtension("dc:date", Item::setPubDate)
             .read("https://lwn.net/headlines/rss")
             .collect(Collectors.toList());
```

Download
--------

Download [the latest JAR][1] or grab via [Maven][2] or [Gradle][3].

### Maven setup
Add dependency declaration:
```xml
<project>
    ...
    <dependencies>
        <dependency>
            <groupId>com.apptasticsoftware</groupId>
            <artifactId>rssreader</artifactId>
            <version>3.1.0</version>
        </dependency>
    </dependencies>
    ...
</project>
```

### Gradle setup
Add dependency declaration:
```groovy
dependencies {
    implementation 'com.apptasticsoftware:rssreader:3.1.0'
}
```

RSS Reader library requires at minimum Java 11.

License
-------

    MIT License
    
    Copyright (c) 2022, Apptastic Software
    
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


[1]: https://search.maven.org/artifact/com.apptasticsoftware/rssreader/3.1.0/jar
[2]: https://maven.apache.org
[3]: https://gradle.org