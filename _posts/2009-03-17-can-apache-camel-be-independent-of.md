---
layout: post
title: "Can Apache Camel be independent of the queuing engine ?"
date: 2009-03-17
categories:
- JMS
- Queue
- Camel
- OSGI
- Apache
- ServiceMix
tags: [camel, jms, servicemix, fuse]
comments: true
share: true
---

Designing and deploying an Apache Camel project in an environment where you have (as an administrator) the control is something very simple to do. Most of the time, the code that you will receive from the developers will be ready to be deployed on the server (ServiceMix, J2EE, ...).

But this is not the case, when you work in big companies where the infrastructure is separated from the teams in charge to design/build/deploy and release the application. As a developer you don't have access to the acceptance or production environment. This means that you must provide different configuration files to the builder team and/or release management in order to deploy your application in development, acceptance and production environment.

Obviously, the name of the servers are not the same and the infrastructure is in charge to manage the queues servers. In this environment, infra will never accept that the queues definition or creation of the queuing engine are under the responsibility of the developer(s). Thus, it will not be possible in your spring DSL file (or Camel Java DSL classes) to declare your queuing engine like this :

{% highlight xml %}
<bean id="activemq"  class="org.apache.activemq.camel.component.ActiveMQComponent">
<property name="brokerURL" value="vm://localhost:61616" />
</bean>
{% endhighlight %} 

remark: vm://localhost:61616 must be changed to serverName.intranet.domainName:port depending on queuing engine deployed in the different environment.
    
To make Camel independent of its queuing engine :
    * ActiveMq,
    * WebSphereMQ,
    * SonicMq
    * TIBCO,
    * …
    
and parameters like :
    * Size of the queue,
    * Size of the messages accepted on the queue,
    * Persistence of the messages
    * …
    
you must made different modifications. Let's assume that you plan to use ActiveMq as your queuing engine. Here is the list of the modifications to do :
    
1) Add the required bundles to your ServiceMix 4 osgi server to allow it to work with ActiveMq. Normally, the features transaction and activemq are activated by default in the file etc/org.apache.servicemix.features.cfg. So, you don't have to do anything else except if you use ServiceMix Kernel. In this case, you have to add the bundles manually or add a link (in the config file) to the features.xml file where the bundles have been declared

2) Create a spring xml file where the parameters of the queuing engine are defined. Luckily, such a file already exists and is available from
    <a href="http://svn.apache.org/repos/asf/servicemix/smx4/features/trunk/assembly/src/main/distribution/text/deploy/activemq-broker.xml">Apache ServiceMix 4 project</a>. So, copy the file into the deploy folder of ServiceMix 4 or Servicemix 4 Kernel
    
3) Create a osgi-queueservice.xml file containing the following lines and copy it in the deploy folder of ServiceMix

{% highlight xml %}
<?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans">
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xmlns:osgi="http://www.springframework.org/schema/osgi"
     xsi:schemaLocation="
     http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
     http://www.springframework.org/schema/osgi
       http://www.springframework.org/schema/osgi/spring-osgi.xsd
     http://camel.apache.org/schema/spring
       http://camel.apache.org/schema/spring/camel-spring.xsd">
{% endhighlight %}      


{% highlight java %}
<bean id="active-mq" class="org.apache.activemq.camel.component.ActiveMQComponent">
<osgi:service id="osgiqueuingservice" ref="active-mq" interface="org.apache.camel.Component">
{% endhighlight %}
    
When the spring file will be at the server startup loaded by Spring (though its Application context), it will create the bean "active-mq" and instantiate the class : org.apache.activemq.camel.component.ActiveMQComponent.

Remarks : 

- No parameters are provided to the ActiveMqComponent like brokerUrl, ...
- If you need to work with another JMS queuing engine, you can replace the class ActiveMQComponent by : org.apache.camel.component.jms.JmsComponent

Next it will create an OSGI service called "osgiqueuingservice" and expose it through the interfaces : org.apache.camel.Component

Remark : As JmsComponent, ActiveMqComponent implements this interface, the OSGI service is generic. So you could parameterized it using a property file (cfg file in the ServiceMix world)

4) The last step is very simple. In your camel config file, you add a reference to your osgi service in order to retrieve the queuing engine like this :

<span style="font-weight: bold;">&lt;osgi:reference id="queuingservice" interface="org.apache.camel.Component" /&gt;</span>

Please remark that you import the Component interface. So, Camel becomes independent of the queuing engine used (WebSphere, TIBCO, ActiveMQ, ...)

and in your Camel route :

<span style="font-weight: bold;">&lt;camel:from uri="queuingservice:queue:in" /&gt;</span>