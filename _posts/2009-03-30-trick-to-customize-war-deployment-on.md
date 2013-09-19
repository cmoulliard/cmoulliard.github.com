---
layout: post
title: "Trick to customize war deployment  on ServiceMix4 using PAX"
date: 2009-03-30
comments: true
categories:
- PAX
- WAR
- ServiceMix
tags:
share: true
---

Here is another very interesting <span style="font-weight: bold;">trick </span>that you can use to the war the deployment process on ServiceMix.

customize in this context, this word means How can I add property or extend existing of a MANIFEST file

e.g. : The WAR to be deployed contains tlb files created under the folder WEB-INF/tags/form and WEB-INF/tags/jms. By default <a href="http://wiki.ops4j.org/display/ops4j/Pax+URL+-+war">PAX</a> will create a MANIFEST file containing the property Bundle-Classpath with :
    
    * WEB-INF/classes
    * all jars from WEB-INF/lib directory
    
but will not take care about your folders

Two possibilities are available to tell to PAX that it must include in the Bundle-Classpath your folders

1) Command + uri
    
This one consists in to pass the parameters as an uri like this :
install war:<a href="file:///d:/temp/activemq-web-console-5.2.0.war?Bundle-Classpath=">file:///d:/temp/activemq-web-console-5.2.0.war?Bundle-Classpath=</a> ., WEB-INF/tags/form, WEB-INF/tags/jms&amp;Webapp-Context= activemqweb

2) Create a file.bnd + command
Another interesting option is to provide as a file the location of the WAR and the properties that you want to add
    
    * Create a file called xxxx.bnd
    * Add in the file the following lines : WAR-URL=file:///c/temp/activemq-web-console-5.2.0.war<br/>Bundle-Classpath=., WEB-INF/tags/form, WEB-INF/tags/jms<br/>Webapp-Context=activemqweb
    
* use in your favorite OSGI server (ServiceMix) the following command to install the WAR :
* install war-i:file:///c:/temp/activemq-web-console-5.2.0.bnd