---
layout: post
title: "Apache Camel / OSGI / Wicket (real example - tutorial part2)"
date: 2009-05-05
comments: true
tags:
share: true
---

I have finalized a first draft of the second part of my tutorial about Camel 2.0 / ServiceMix-Karaf / CXF / OSGI / Apache Wicket

The purpose of the second part was to explain how to design a simple project where different concepts like :

  - persistence (Hibernate/spring),
  - routing (Apache Camel 2.0),
  - mapping between CSV file and objects (using camel-bindy),
  - webservices (Apache CXF - OSGI),
  - osgi stuffs,
  - packaging and deployment on Apache Karaf/ServiceMix OSGI server (features, PAX url),
  - web application integration (Apache Wicket, PAX Web) have been addressed.
  
The tutorial has been designed in 4 parts :
  
  - Part 2a : real example, architecture, project setup, database creation
  - Part 2b : transform projects in bundles
  - Part 2c : add infrastructure and routin
  - Part 2d : web and deployment
  
 and is available here : <a href="http://cwiki.apache.org/CAMEL/tutorial-osgi-camel-part2.html">http://cwiki.apache.org/CAMEL/tutorial-osgi-camel-part2.html</a>
 
Don't hesitate to provide me your remarks/comments to improve it.