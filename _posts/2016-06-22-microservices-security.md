---
layout: post
title: "Security Enforcement of the Microservices"
date: 2016-06-22
tags: [fuse, camel, cxf, integration, apiman, security, keycloak, microservices]
comments: true
share: true
---

Securing an application is a common task that many developers will embrace during the Architecture design and coding phase of a project. Although, the securing of a Web Service, RESTfull endpoint could be achieved easily
when the modules of a project are deployed within the same Java container, when they rely on the same Security Spec (JAAS, WS-Security, WS-Trust, JAX-RS) or Security Framework (Apache Shiro, Spring Security, ...) the 
design could become really complex when we have to build a distributed application.
 
As the current trend is to split a Monolithic application into a collection of Microservices exposing the service as a RESTfull endpoint, Web Service, interconnected to each offer and packaging the business logic, our security officer will be very embarrassed
to guide and provide recommendations to the Architect and developers. The difficulty is not related to the Microservice Architecture itself but because we will increase the number of Services to secure within a domain or between domains where the 
Security Rules or Level of Data securing is governed differently. Another concern is also related to the fact that the modules will be deployed within different type of Java Container (Apache Tomcat, JBoss WildFly, Apache Karaf, JBoss Swarm, Vert.x, ...)
as each container can provide a better runtime environment to support by example non blocking connections (Vert.x), a lightweight footprint container packaging only the jar required and consuming less resources on the machine.

Some of these containers doesn't necessarily support/follow the JavaEE spec or JAXRS/JAXWS standards and by consequence, different security patterns should be investigated to help our Security Officer.

To help the architects and developers to adopt the security approach which best fits the company requirements, practices, Microservices Architecture and their skills, we will investigate within this blog some patterns that you could
easily implement within your project by adopting the OpenSource projects supporting them :

- Interceptor
- Web Container
- External Player

We will now present, detail them and mitigate their adoption in order to measure the consequences of their adoption.



To resolve this problem, we could adopt the proxy pattern where the guardian responsible  to manage the security is played by an external player, an Api Manager




Since the release 2.15 of Apache Camel, a new **Domain Specific Language or DSL** has been developed to simplify the definition/creation of the REST endpoints and their syntax. 
This DSL syntax contains 2 words that we will use to :

- Configure the component and endpoint (**restConfiguration()**)
- Define the action to be performed (GET, PUT, POST or DELETE) and path to access the service (**rest().verb()**)

The syntax exists in Java or in XML format. Here is a an example where the path of the application is defined to access this URL resource "/blog" and where the HTTP verb "put" is defined for the 
the subpath "/article". A parameter has been added using as convention the syntax "{id}". The format to be used when the HTTP endpoint produce an HTTP Response or process a HTTP request
can be specified as you can see using "produces("")" or "consumes("").

{% highlight java %}
rest("/blog/").id("rest-blog-service")
       .produces("application/json")
       .consumes("application/json")

       .put("/article/{id}").id("rest-put-article")
       .type(Blog.class)
       .to("direct:add");
{% endhighlight %}

The formal descritpion of the REST DSL language is designed [here](http://camel.apache.org/rest-dsl.html) 


One of the benefit of the DSL is that it does not rely on any Java Specification (jax-rs 1.0, ...) and annotations but allows to design in one place the services to be mapped/exposed behind the REST endpoints.
The second benefit is that the syntax is supported by many Apache Camel components and by consequence, you have the possibility to design your project using one for them or to combine them. 

- camel-netty-http
- camel-netty4-http 
- camel-jetty 
- camel-restlet 
- camel-servlet 
- camel-spark-rest
- camel-undertow

To configure the component/endpoint and their corresponding properties (enable CORS, port, hostname, contextPath, bindingMode, ...), you will use the **restConfigure() DSL word** as showed hereafter  

{% highlight java %}
restConfiguration().component("servlet")
   .enableCORS(true)
   .bindingMode(RestBindingMode.json)
   .dataFormatProperty("prettyPrint", "true");
{% endhighlight %}

So, when Apache Camel will read the Java REST DSL or XML DSL syntax, it will instantiate using the factory class of the component selected an endpoint, set the fields of the component or endpoint object
and next a HTTP handler responsible to process for a specific URL path the processing of the HTTP Request and HTTP response will be created and registered within the corresponding HTTP Web container.

Remark : As the implementation of the HTTP handler like also the verbs used (OPTION, CORS) is component dependent, I recommend that you evaluate them to verify/validate the one which is able to best process your HTTP requests.

To help you to work with this new REST DSL syntax, this project hosted on [FuseByExample github repository](https://github.com/FuseByExample/rest-dsl-in-action/) has been created. 

It provides a real application designed to manage Blog Articles (Create, Search, Delete). It will allow you to post Blog Article that we save into an [ElasticSearch](https://github.com/FuseByExample/rest-dsl-in-action/#setup-elasticsearch-data-mapping) backend. The result posted within the NoSQL JSon Databae can be consulted using a [Kibana Dashboard](https://github.com/FuseByExample/rest-dsl-in-action/#kibana-dashboard-and-services) created for the purpose of this project.

![REST DSL Blog]({{ site.url }}/images/camel-rest-dsl.png)

To [document the REST DSL Api](https://github.com/FuseByExample/rest-dsl-in-action/#swagger-documentation) of the REST endpoints managed by Apache Camel, we have used the Camel **Swagger** Servlet has been configured in order to allow the Swagger UI to get the json stream generated by Camel from the REST DSL syntax parsed within
the project. The Servlet can be registered using a web.xml file or when you deploy the project on JBoss Fuse which is an OSGI container by using the Blueprint IoC container and a OSGI HTTP Service

{% highlight java %}
<service interface="javax.servlet.http.HttpServlet">
        <service-properties>
            <entry key="alias" value="/rest/api-docs/*"/>
            <entry key="init-prefix" value="init."/>
            <entry key="init.cors" value="true"/>
            <entry key="init.base.path" value="${swaggerBasePath}"/>
        </service-properties>
        <bean class="org.apache.camel.component.swagger.DefaultCamelSwaggerServlet"/>
    </service>

    <!-- to setup camel servlet with OSGi HttpService -->
    <reference id="httpService" interface="org.osgi.service.http.HttpService"/>
{% endhighlight %}

The Service properties fields define the fields to be configured for the Swagger Servlet that Swagger will use to setup the path to access the JSON doc generated and path also to access the endpoint deployed on the server.

You can find the required info about how to configure and use the Camel-Swagger component [here](http://camel.apache.org/swagger.html). A new component has been created recently within the [Apache Camel project](http://camel.apache.org/swagger-java.html) as Swagger has refactored its API to use Java as language instead of Scala.




