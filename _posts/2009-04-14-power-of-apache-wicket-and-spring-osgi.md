---
layout: post
title: "The power of Apache wicket and Spring OSGI Service top of ServiceMix 4"
date: 2009-04-14
comments: true
tags: Apache Wicket ServiceMix Fuse
---

In a previous post, I have explained how you can transform ServiceMix 4 as a Web Application Server. In this new post, I will show you How you can combine the power of Apache Wicket and Spring OSGI services together on ServiceMix 4 with the help of PAX Web.

This mix can be achieved very easily. Only a few steps are required.

1) Create a maven project where you will design your spring service and expose it as an OSGI service according to the <a href="http://static.springframework.org/osgi/docs/1.2.0/reference/html-single/#service-registry:export">Spring documentation</a>.

{% highlight xml %}
<osgi:service ref="incidentService"   interface="org.apache.camel.example.reportincident.service.IncidentService">
</osgi:service>
{% endhighlight %}        
        
2) Create a new maven project that you will use to package your Apache web application. The trick here is to modify your web.xml file like this :

a) add listener and context parameter for the Spring OSGI Context Loader

{% highlight xml %}
    <context-param>
        <param-name>contextClass</param-name>
        <param-value>
org.springframework.osgi.web.context.support.OsgiBundleXmlWebApplicationContext
        </param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
     </listener>
{% endhighlight %}
    
b) Add Spring Factory to Apache (required to allow injection of dependency in java classes of Wicket)

{% highlight xml %}
    <filter>
        <filter-name>camel.example.reportincident.web</filter-name>
        <filter-class>org.apache.wicket.protocol.http.WicketFilter</filter-class>
        <init-param>
            <param-name>applicationClassName</param-name>
            <param-value>org.apache.camel.example.reportincident.WicketApplication</param-value>
            <param-name>applicationFactoryClassName</param-name>
            <param-value>org.apache.wicket.spring.SpringWebApplicationFactory</param-value>
        </init-param>
    </filter>
 {% endhighlight %}
    
Please refer to the <a href="http://cwiki.apache.org/WICKET/spring.html">Apache Wicket Web</a> site for more info about Spring integration

3) create an applicationContext.xml file under WEB-INF folder containing the reference to the OSGI service :

<span style="font-weight: bold;">&lt;osgi:reference id="incidentService" interface="org.apache.camel.example.reportincident.service.IncidentService"/&gt;</span>

4) And of course, in your Wicket page, inject dependency using the Wicket annotation :

<span style="font-weight: bold;">@SpringBean</span><br/><span style="font-weight: bold;">private IncidentService incidentService;</span>

5) Now generate the bundles (JAR/WAR) and deploy them top of SMX4. Before to deploy the war containing the web project, verify that the following bundles are deployed on SMX4 :

- OPS4J Pax Web - Web Container (0.6.0)
- OPS4J Pax Web - Jsp Support (0.6.0)
- OPS4J Pax Web Extender - WAR (0.5.1)
- OPS4J Pax Web Extender - Whiteboard (0.5.1)
- OPS4J Pax Url - war:, war-i: (0.4.0)
- spring-osgi-web (1.2.0.rc1)
- Wicket (1.3.5)
- Wicket IoC common code (1.3.5)
- Wicket Spring Integration (1.3.5)
- Wicket Spring Integration through Annotations (1.3.5)
- Wicket Extensions (1.3.5)
- Apache ServiceMix Bundles: jetty-6.1.14 (6.1.14.1)

A tutorial will be published soon with material and step by step.

Remarks :

- PAX-runner can be used as running environment (instead of Servicemix) with Equinox, Felix, ...
- Many thanks to Alin Dreghiciu (PAX project) for its help/support