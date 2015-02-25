---
layout: post
title: "Run a Google Web Toolkit 2 project on Apache Karaf/ServiceMix"
date: 2011-12-16
categories:
- WAB
- Karaf
- GWT
- Pax-Web
- WAR
- Apache
- ServiceMix
tags: [google-gwt, pax-web, osgi, servicemix, fuse]
comments: true
share: true
---

To simplify the development of Web projects on Apache Karaf/Apache ServiceMix, we have created [archetypes](https://github.com/ops4j/org.ops4j.pax.web/tree/master/pax-web-archetypes) to setup WAR or WAB projects. They are very basic but they can be enriched with framework like Struts 2, Wicket, plain JSP or MyFaces JSF as they are currently supported on Apache Karaf - ServiceMix.

For the [GWT users](http://code.google.com/webtoolkit/), it exists now an archetype which will create a GWT 2.4 project. To create such a project, you must generate a project from the archetype

{% highlight text %}
mvn archetype:generate \
   -DarchetypeGroupId=org.ops4j.pax.web.archetypes\
   -DarchetypeArtifactId=wab-gwt-archetype \
   -DarchetypeVersion=2.1.2 \
   -DgroupId=com.mycompany \
   -DartifactId=hello \
   -Dversion=1.0
{% endhighlight %}   
   
{% highlight text %}
    <a href="{{site.url}}/assets/images//archetype-creation.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="279" src="{{site.url}}/assets/images//archetype-creation.png" width="320"/></a>
{% endhighlight %}    

build next the WAB using hello/mvn clean install
and deploy it on Apache Karaf

<figure>
  <img src="{{site.url}}/assets/images//karaf-gwt2.png"/>
  <figcaption>Fig. 1 : GWT deployed on Apache Karaf</figcaption>
</figure>

Verify that the web site is well registered : 

<figure>
  <img src="{{site.url}}/assets/images//web-list.png"/>
  <figcaption>Fig. 2 : Web site deployed on Apache Karaf</figcaption>
</figure>   

Next, you can navigate to your application in your browser and click on the button to say Hello.

<figure>
  <img border="0" height="225" src="{{site.url}}/assets/images//browser-gwt2.png" width="320"/>
  <figcaption>Fig. 3 : Hello Message of GWT in the browser</figcaption>
</figure>  

Remark : A WAB project is nothing more than a WAR excepted it is packaged as a bundle file, that we have removed the WEB-INF/lib dependencies and create a MANIFEST file containing the OSGI metadata of the packages to be imported.
