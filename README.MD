# A simple LRU cache for Java

[![Build Status](https://travis-ci.org/markenwerk/java-utils-lrucache.svg?branch=master)](https://travis-ci.org/markenwerk/java-utils-lrucache)
[![Coverage Status](https://coveralls.io/repos/github/markenwerk/java-utils-lrucache/badge.svg?branch=master)](https://coveralls.io/github/markenwerk/java-utils-lrucache?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57100c90fcd19a00415b15f9/badge.svg)](https://www.versioneye.com/user/projects/57100c90fcd19a00415b15f9)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-lrucache/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-lrucache)
[![Issues](https://img.shields.io/github/issues/markenwerk/java-utils-lrucache.svg)](https://github.com/markenwerk/java-utils-lrucache/issues)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/java-utils-lrucache/blob/master/LICENSE)

This library provides a simple LRU cache based on a linked hash map.

## Overview

This library is hosted in the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/utils-lrucache). You can use it with the following coordinates:

```xml
<dependency>
	<groupId>net.markenwerk</groupId>
	<artifactId>utils-lrucache</artifactId>
	<version>1.0.1</version>
</dependency>
```

Consult the [usage description](#usage) and [Javadoc](http://markenwerk.github.io/java-utils-lrucache/index.html) for further information.

# Motivation

As every Java programmer should know, it is possible to create a simple LRU cache from a [`LinkedHashMap`][LinkedHashMap] with a few lines of code. The goal of this library is to provide a ready made LRU cache, so that is isn't necessary to reproduce these few lines of code every time and have an appropriately named class.  

# Usage

## LRU caching

This library provides the simple [`LruCache`][LruCache] which is a [`LinkedHashMap`][LinkedHashMap] that is configured to hold it's entries in access order and evicts the least recently accessed entry, if the configured cache size is exceeded.

```java
// the desired cache size
int cacheSize = ...

// a map that never holds more than cacheSize elements
Map<String, Object> cache = new LruCache<>(cacheSize);
```

### Listening to eviction events

A [`LruCache`][LruCache] can be created with a [`LruCacheListener`][LruCacheListener] that gets notified, when an entry is evicted from the [`LruCache`][LruCache]. 

```java
// the desired cache size
int cacheSize = ...

// a map that never holds more than cacheSize elements
Map<String, Object> cache = new LruCache<>(
	cacheSize,
	entry -> System.out.println(entry.getKey() + " got evicted")
);
```

[LruCache]: http://markenwerk.github.io/java-utils-lrucache/index.html?net/markenwerk/utils/lrucache/LruCache.html
[LruCacheListener]: http://markenwerk.github.io/java-utils-lrucache/index.html?net/markenwerk/utils/lrucache/LruCacheListener.html

[LinkedHashMap]: http://docs.oracle.com/javase/8/docs/api/index.html?java/util/LinkedHashMap.html
