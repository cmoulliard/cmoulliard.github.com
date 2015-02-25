---
layout: post
title: "Apache Camel, Cxf, ActiveMQ & ServiceMix at Devoxx"
date: 2011-11-21
tags: apache camel cxf servicemix devoxx Fuse
comments: true
share: true
---

This year, I get the chance to present 2 talks at the
[Devoxx](http://www.devoxx.com/display/DV11/Home) event,

<figure>
  <img src="{{ site.url }}/assets/images/LogoDevoxx150dpi.jpg"/>
</figure>
  
one for University Talk and the other for Hands on Lab. With the help of Gert Vanthienen, we took the time to present in more detail what finally we can design as solution, architecture with Apache projects like Camel, CXF, ActiveMQ and ServiceMix and move it into a scalable, high-available platform.

That was a great challenge as this is not really easy to introduce "integration" which is not so sexy comparing to a Web Development framework, an iPhone or Android application.

But times are changing and a lot of developers / architects are interested by agile approaches that we develop at Apache foundation.

With [Fuse IDE](http://fusesource.com/products/fuse-ide/) tool,

<figure>
    <img src="{{ site.url }}/assets/images/fuse_ide.png"/>
</figure>  
  
we can now accelerate the creation of a project using Apache Camel. The wysiwig editor and EIPs patterns allow you to quickly create new routes while the runtime editor enable to review existing project. IDE is more than a tool as it allows to run and deploy the routes in Fuse ESB, Tomcat application servers or locally. The tool is not yet finalized (release 2.1 should be available soon) but it offers a lot of possibilities to facilitate integration projects, tracing of messages (= exchanges) and investigation about time passing through the different processors.  
    
Integration projects are moving into cloud space and this is what we have presented next with FuseSource [Fabric](http://fabric.fusesource.org/). Fabric is not a new hype but a strategy to reduce impact of OSGI dependencies calculation, provisioning of Apache Camel routes on local, remote or cloud instances using Apache Zookeeper as registry of artefacts to be deployed (features, jars, configurations). It offers also elastic services when deploying services into the cloud.

<figure>
  <img src="{{ site.url }}/assets/images/fabric-cloud.png" width="320"/></a>
</figure>

For those who were not there, here are the links of the presentation like also the hands on lab material that we have used in the afternoon to develop a real Japa Application Project (Spring, JPA, Web) on Fuse ESB.

* <a href="http://www.slideshare.net/cmoulliard/devoxx-2011-integrationcamelcxfservicemixactivemq" target="_blank">Presentation</a> : http://www.slideshare.net/cmoulliard/devoxx-2011-integrationcamelcxfservicemixactivemq
       
* <a href="https://github.com/cmoulliard/Devoxx-2011-HandsOnLab" target="_blank">Hands on Lab</a> : https://github.com/cmoulliard/Devoxx-2011-HandsOnLab

* <a href="http://fabric.fusesource.org/" target="_blank">Fusesource Fabric</a> :&nbsp;http://fabric.fusesource.org/

* <a href="http://fusesource.com/" target="_blank">Fusesource : Leader in Integration OpenSource</a>
    
Remark : The step by guide is available on the github repo like also the skeleton (zip file)
