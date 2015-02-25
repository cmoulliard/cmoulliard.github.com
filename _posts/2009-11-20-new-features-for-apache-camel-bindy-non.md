---
layout: post
title: "New features for Apache Camel Bindy (non XML binding framework)"
date: 2009-11-20
comments: true
tags: [camel, bindy, dataformat, fuse]
share: true
---

Today has been a very productive day. I have found the time to update the documentation about the Apache Camel Tutorial on
[OSGI](http://camel.apache.org/tutorial-osgi-camel-part2.html) and commit the last new features added to camel-bindy : the non XML binding framework
used to parse or generate CSV, FIX, ... messages

This new version of bindy includes important new features like :

- Required (@DataField) to check mandatory field
- Position (@DataField and @KeyValuePairField) when the CSV/FIX message to be generate contain fields which are placed at a different position then those used to parse it
- Section which allow to define the class containing by example the header, body or footer section

and

- OneToMany which allow to :

* Read a FIX message containing repetitive groups (= group of tags/keys)
* Generate a CSV with repetitive data

More info can be find <a href="http://camel.apache.org/bindy.html">here</a>.