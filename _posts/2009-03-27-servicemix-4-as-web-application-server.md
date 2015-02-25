---
layout: post
title: "ServiceMix 4 as a Web Application Server"
date: 2009-03-27
comments: true
categories:
- SMX4
- Web Application Server
- ServiceMix
tags: Apache ServiceMix Web Container Fuse
comments: true
share: true
---

This week, I have discovered the web possibilities offered by the ServiceMix 4 platform. SMX ("pour les intimes") uses as its core platform the excellent packages of the project <a href="http://wiki.ops4j.org/display/ops4j/Pax">PAX </a>which is the umbrella project for all OSGi bundles and tools.

In standard, the following bundles are installed :

[ 9] [Active ] [ ] [ 8] OPS4J Pax Logging - API (1.3.0)<br/>[ 10] [Active ] [ ] [ 8] OPS4J Pax Logging - Service (1.3.0)
[ 11] [Active ] [ ] [ 5] OPS4J Pax Url - wrap: (0.3.3)<br/>[ 12] [Active ] [ ] [ 5] OPS4J Pax Url - mvn: (0.3.3)

They are used to log information of the server and to install or wrap bundles. Nevertheless, PAX project is not limited to the mentioned bundles but proposes also Web support for jetty.

To install or transform ServiceMix as a Web Application Server, you can use the "web" and "web-core" feature (available under ServiceMix 4) or install the following bundles manually :
- OPS4J Pax Web - Web Container (0.5.2)<br/>- OPS4J Pax Web - Jsp Support (0.5.2)
- OPS4J Pax Web Extender - WAR (0.5.0)<br/>- OPS4J Pax Web Extender - Whiteboard (0.5.0)
- OPS4J Pax Url - war:, war-i: (0.3.3)
- Apache ServiceMix Bundles: jetty-6.1.14 (6.1.14.1)
- The Web Application Server becomes available at the url http://localhost:8080.

Evidently, no jsp pages, servlets are available. Don't worry, to deploy a WAR, you only have to copy it to the deploy directory of ServiceMix and it will convert be converted as a bundle

By example, you can install a simple Hello World war using the command
install war:file:///d:/temp/sample.war?Webapp-Context=sample

remarks :

- file:/// must be changed according to the location of your war
- The parameter WebApp-Context is added to have a nicer web address

and see the result at the address : http://localhost:8080/sample/index.html
<img src="file:///C:/DOCUME%7E1/CHARLE%7E1.STR/LOCALS%7E1/Temp/moz-screenshot.png" alt=""/><img src="file:///C:/DOCUME%7E1/CHARLE%7E1.STR/LOCALS%7E1/Temp/moz-screenshot-1.png" alt=""/>Yes, this looks beautiful but the web port number is 8080, my boss would like to secure the web site, change the session timeout, ....

This is very simple to do, you create a file named (<span style="font-weight: bold;">org.ops4j.pax.web.cfg</span>) and copy it in the directory
<span style="font-weight: bold;">etc </span>of servicemix, in this file you can parametrize by example the following properties (see
 <a href="http://wiki.ops4j.org/display/ops4j/Pax+Web+-+Configuration">PAX</a> for more information) :

<b>org.osgi.service.http.port=80<br/>org.ops4j.pax.web.session.timeout=10</b>

You restart Servicemix and now the web site is available at the following address :

http://localhost/sample/index.html