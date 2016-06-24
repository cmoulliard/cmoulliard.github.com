---
layout: post
title: "Security Enforcement of the Microservices"
date: 2016-06-22
tags: [fuse, camel, cxf, integration, apiman, security, keycloak, microservices]
comments: true
share: true
---

## Introduction

Securing an application is a common task that many developers will embrace during the Architecture design and coding phase of a project. Although, the securing of a Web Service, RESTfull endpoint could be achieved easily
when the modules of a project are deployed within the same Java container, when they rely on the same Security Spec (JAAS, WS-Security, WS-Trust, JAX-RS) or Security Framework (Apache Shiro, Spring Security, ...) the 
design could become really complex when we have to build a distributed application.
 
As the current trend is to split a Monolithic application into a collection of Microservices exposing the service as a RESTfull endpoint, Web Service, interconnected to each offer, our security officer will be very embarrassed
to guide and provide recommendations for the actors of such a project.

The difficulty is not related to the Microservice Architecture itself but because:

- The number of Services to be managed within such project is multiplied by a factor of 10x, 100x,
- Each service, which is part of a logical/business domain, could be managed according to different Security Governance Rules (Basic Auth, Digest & Encryption),
- The information transported between the services belong to a different confidentiality level (public, private, ...)

For all these reasons, the security and the governance will become even more critical for the success of a Microservice Architecture. 
From a technical point of view, we will be faced to the following concern; as the modules will be deployed within different type of Java Container (Apache Tomcat, JBoss WildFly, Apache Karaf, JBoss Swarm, Vert.x, ...)
, that each container addresses a specific need (Non Blocking, high throughput of connections, http/2 protocol, ...) and that they doesn't necessarily support/follow the JavaEE spec or JAXRS/JAXWS standards,
different security patterns or frameworks will be required to resolve our security challenge.

To help the architects and developers to adopt the security approach which best fits the company requirements, practices, Microservices Architecture and their skills, we will investigate within this blog some patterns that you could
easily implement within your project :

- Interceptor
- Web Container
- Api Gateway

We will now present, detail and mitigate the approaches in order to measure the consequences of the adoption of the different patterns. 
The use case that we will discuss is very simple and consists to secure a RESTfull Service with has been created using the Java Integration Framework - Apache Camel.
As we can see within the following picture, the REST Service or Endpoint is exposed by a local HTTP Web container created using Eclipse Jetty.
A client which is our HTTP Agent will issue HTTP requests using a HTTP method (GET, POST, DELETE, ...) and a URL to access the Web resources of the service ("/rest/myservice/").

![interceptor]({{ site.url }}/images/security/rest-1.png)

## Interceptor

The goal of the interceptor is to include within the flow of the code a class responsible to collect/handle the information passed through the HTTP Request like the Authorization Header, the http URI, URL
and the credentials provided by the HTTP agent. The principe is presented within the next picture

![camel-intercept]({{ site.url }}/images/security/interceptor.png)

Within the flow describing how a request or an exchange is processed, an interceptor is added before or after the call to the processor responsible to handle the logic declared within the application. This 
interceptor wraps your code and will act as a security guardian. This pattern is supported by the Apache Camel Framework and Apache CXF using respectively a [Policy](https://camel.apache.org/maven/camel-2.15.0/camel-core/apidocs/org/apache/camel/spi/Policy.html).

The interceptor can use the existing Security Frameworks/Technologies to handle the authentication/authorization process like JAAS, Apache Shiro, Spring Security, Apache WS4J, ...) but you can also create your own Interceptor and plug it within the 
flow as we will demonstrate hereafter.

An Apache Camel interceptor will look the **SimpleAuthenticationPolicy** class where the wrap method will be called when the framework will consume an Exchange which corresponds to a HTTP Request received by the Jetty Server acting as a consumer.
 
{% highlight java %}
public class SimpleAuthenticationPolicy implements AuthorizationPolicy {

    @Override
    public Processor wrap(RouteContext routeContext, Processor processor) {
        return new SimpleAuthenticationProcessor(processor, this);
    }
}
{% endhighlight %}

The **SimpleAuthenticationProcessor** class contains the logic needed to access the content of the Exchange when the processor will be called and of course, it will call the class/method responsible 
to authenticate ot authorize the incoming request **applySecurityPolicy**

{% highlight java %}
@Override
public boolean process(Exchange exchange, AsyncCallback callback) {
    try {
        applySecurityPolicy(exchange);
    } catch (Exception e) {
        // exception occurred so break out
        exchange.setException(e);
        callback.done(true);
        return true;
    }
    return super.process(exchange, callback);
}
{% endhighlight %}

The last thing to be done is to register the interceptor within the Apache Camel Route definition in order to secure your Microservice.

{% highlight java %}
  SimpleAuthenticationPolicy auth = new SimpleAuthenticationPolicy();
  
  // Definition of the RESTFull Endpoint using the REST DSL syntax
  restConfiguration()
      .component("jetty").scheme("http").host("0.0.0.0").port("9090")
      .bindingMode(RestBindingMode.json);

      // Service exposed at the path "/customerservice/customer/{id}
      rest("/customerservice").produces("json")
         .get("/customer/{id}")
         .to("direct:hello");

      from("direct:hello")
         .policy(auth) // Policy registered to authenticate the HTTP Request
         .beanRef("customerService","getCustomerDetail");
{% endhighlight %}

As you can see this approach is very flexible as you can plug whatever technology you prefer to use to secure the access to the REST service exposed as Endpoint by the Jetty Server.

## Web Container

We can also enrich the architecture and use the features offered by the Jetty/Netty HTTP Server in order to 

- Enable the SSL/TLS Security layer to encrypt the data exchanged between the HTTP Agent and the Server,
- Setup mutual TLS,
- To restrict the access to the Web Resources using a **SecurityConstraint** associated with a user's role. 

{% highlight java %}
// Describe the Authentication Constraint to be applied (BASIC, DIGEST, NEGOTIATE, ...)
Constraint constraint = new Constraint(Constraint.__BASIC_AUTH, "user");
constraint.setAuthenticate(true);

// Map the Auth Constraint with a Path
ConstraintMapping cm = new ConstraintMapping();
cm.setPathSpec("/*");
cm.setConstraint(constraint);

HashLoginService loginService = new HashLoginService("MyRealm",
        "myrealm.props");

ConstraintSecurityHandler sh = new ConstraintSecurityHandler();
sh.setAuthenticator(new BasicAuthenticator());
sh.setConstraintMappings(cm);
sh.setLoginService(loginService);
{% endhighlight %}

- Authenticate the user using some JAAS Plugin (HashLogin with Property file, LDAP, JDBC)

As you can see within the next diagram, the security architecture proposed is more robust as managed by two distinct levels if you combine the Interceptor pattern with the HTTP Security model :

- The Web HTTP Container will be responsible to Authenticate the user and restrict the access to the Web Resoruces,
- The Connection between the HTTP agent and the Server is more secured as the data will be encrypted and the confidentiality of the data exchanged will be guarantee
- The interceptor will become responsible to authorize the access to the Service

![interceptor-jetty]({{ site.url }}/images/security/rest-2.png)

## Time to mitigate the approach



<table class="pure-table pure-table-bordered">
    <thead>
        <tr>
            <th>Pros</th>
            <th>Cons</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>No product lock</td>
            <td>Intrusive</td>
        </tr>
        <tr>
            <td>Great Flexibility </td>
            <td>Low Management Capability </td>
       </tr>
        <tr>
            <td>Spec Managed</td>
            <td>Lack of Governance </td>
        </tr>
    </tbody>
</table>

## Gateway

![interceptor-jetty]({{ site.url }}/images/security/rest-3.png)



