---
layout: post
title: "Trick to pass an uri declared in a property file to a Camel route"
date: 2009-05-05
share: true
tags: spring
share: true
---

Defining Camel uris in a file is not so easy. Until now, the only possibility was to create a camel endpoint where the uri was declared in the XML file.
Here is a trick who will simplify your life to declare/define the uri in a property file.
Spring injection is used to replaced the variable declared in your XML file with the value discovered in the property file.
By example, to work with the following uri : file://d:/temp/data/?move=d:/temp/done/${file.name}

Remark : as you can see, the uri contains a variable to a Camel variable and in consequence is not a Spring bean.
So we have to inform Spring that the variable to be searched in the XML file use another syntax delimiter (#{} instead of ${}).

Here is what you have to do

Step 1 : Create org.springframework.beans.factory.config.PropertyPlaceholderConfigurer bean

Define a PropertyPlaceHolder (using another prefix/suffix delimiter) to avoid conflict between your camel variable and Spring property

{% highlight xml %}

    <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">

    <property name="placeholderPrefix" value="#{"> (1)
        <property name="placeholderSuffix" value="}"> (2)
        <property name="location" value="classpath:META-INF/spring/org.apache.camel.example.reportincident.routing.properties"> (2)
    </property>

{% endhighlight %}

(1) : Define a different prefix instead of ${ which is the value by default and used as variable
(2) : Define the value of the suffix. In this case, this is the same as the default value

Step 2 : Create org.apache.camel.example.reportincident.routing.properties

Create the following file : org.apache.camel.example.reportincident.routing.properties and put it in your classpath.
If you plan to deploy your camel route under Apache ServiceMix Kernel --> Apache Karaf, add this file in the META-INF/spring directory by example
Add the following property : uriFile=file://d:/temp/data/?move=d:/temp/done/${file.name}

Step 3 : Create an endpoint in your Camel Spring DSL file

Create a camel endpoint for the uri that you would like to use

{% highlight xml %}
    <camel:endpoint id="fileEndpoint" uri="#{uriFile}"> (1)
{% endhighlight %}

(1) As you can see, we use as a Spring variable reference the syntax #{} so spring will retrieve the value from the variable declared in the file

Step 4 : Adapt your route

Define the uri of your route like this :

{% highlight xml %}
    <camel:from ref="fileEndpoint">
    Cool isn't !!
    </camel:from></camel:endpoint></property></property></bean>
{% endhighlight %}


