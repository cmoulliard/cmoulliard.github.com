---
layout: post
title: "Run Camel route in ServiceMix 3.3.x"
date: 2010-03-30
tags: [servicemix, esb, camel, fuse]
comments: true
share: true
---

Even if Apache ServiceMix (ESB) was mainly designed as a JBI container, it is perfectly possible to use camel routes and camel components (bindy, file, jms, ...) on this platform.
This allows by example to : 

- Combine JBI binding components or Services with Camel routes,
- Use a camel component when the binding component does not exist,
- Deploy camel as Service Unit in Service Assembly

Here is a small example showing that in action. The creation of the project is very simple as you have to create a Service Unit. If you work with maven, you can use the servicemix plugin which will allow to create the maven project. Otherwise, you can create the maven structure manually.To be able to deploy camel next, you must create a Service Assembly (= SA) who will include the Service Unit.
Don't be afraid and [follow the tutorial described here](http://servicemix.apache.org/2-beginner-using-maven-to-develop-jbi-applications.html) for that part of the work.

When the project is ready, you create a camel-context.xml file in the directory src/main/resources. This file which is a spring xml file will contain the camel routes + beans definitions or will point to your packages if you prefer to code your routes using Camel Java DSL language instead of Spring DSL language.Remark : in my example, I use Spring DSL language.The route is very basic and will create a message containing the text "Hello World from SMX3!" as body value. The message is copied in an activemq queue called "in". A second route take the messages and send it to the log. The log message is readable from the console where ServiceMix 3.3.x has been started.In the Spring file, you must add the camel bean "org.apache.camel.component.jms.JmsComponent" definition to instantiate a connection factory to the queueing engine. The broker is created and started at the launch of ServiceMix3 so you don't need to do anything else here !        
    
{% highlight xml %}
<beans xmlns="http://www.springframework.org/schema/beans"       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd       http://camel.apache.org/schema/spring
       http://camel.apache.org/schema/spring/camel-spring.xsd">
       
       <bean id="activemq" class="org.apache.camel.component.jms.JmsComponent">        
          <property name="connectionFactory">
            <bean class="org.apache.activemq.ActiveMQConnectionFactory">
                <property name="brokerURL" value="tcp://localhost:61616"/>
            </bean>
          </property>
        </bean>
        
       <camelContext xmlns="http://camel.apache.org/schema/spring">
        <package>org.apache.servicemix.samples</package>
         
        <route>
            <from uri="timer:myTimerEvent?fixedRate=true&amp;period=15000"/>           
            <setBody>
                <constant>Hello World from SMX3 !</constant>
            </setBody>           
            <to uri="activemq:queue:in"/>
            </route>
         
        <route>
            <from uri="activemq:queue:in"/>           
            <to uri="log:org.apache.servicemix.samples.camel.ExampleCamelRoute"/>
            </route>
        </camelContext>
     </beans>
{% endhighlight %}


To allow Camel to work with its jms component, the following dependency must be added in the pom of the camel-su.

{% highlight xml%}
<dependencies>
  <dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-core</artifactId>
    <version>2.2.0</version>
  </dependency>
  <dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-spring</artifactId>
    <version>2.2.0</version>
  </dependency>
  <dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-jms</artifactId>
    <version>2.2.0</version>
  </dependency>
</dependencies>
{% endhighlight %}
    
To compile the project, simply launch the command "mvn install" to generate the SU and do the same for the SA. When the Service Assembly jar file is available, copy it in the hotdeploy directory of ServiceMix 3 server and check the log.This example which is very basic can be extended with another camel components. In that case, don't forget to add their dependency in the SU pom as the camel lib must be added in the lib directory of the SU.

Have fun with that !