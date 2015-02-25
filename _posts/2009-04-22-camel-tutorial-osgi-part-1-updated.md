---
layout: post
title: "Camel Tutorial - OSGI part 1 (UPDATED)"
date: 2009-04-22
comments: true
tags: [camel, Tutorial, fuse]
share: true
---

I'm currently working on the part of the camel - OSGI tutorial part 2 but I have found the time to update the first part. This was required because I would like to use the maven PAX plugin instead of spring maven plugin and test it against SMX 1.1.0 and Camel 1.6.0.

The PAX maven plugin offers a lot of advantages regarding to the one of Spring (which is currently becoming a new tool called - bundlor)

- pom.xml file generated only include dependencies, plugin required,
- project can be designed with several modules,
- project can be tested with <a href="http://wiki.ops4j.org/display/paxexam/Pax+Exam">PAX Exam</a> and launched using <a href="http://wiki.ops4j.org/display/paxrunner/Pax+Runner">PAX runner</a>
- ...
    
Here is the link to <a href="http://camel.apache.org/tutorial-osgi-camel-part1.html">tutorial</a>
