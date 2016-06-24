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

- The Web HTTP Container will be responsible to Authenticate the user and restrict the access to the Web Resources,
- The Connection between the HTTP agent and the Server is more secured as the data will be encrypted and the confidentiality of the data exchanged will be guarantee
- The interceptor will become responsible to authorize the access to the Service

![interceptor-jetty]({{ site.url }}/images/security/rest-2.png)

## Time to mitigate the approach

It is time now to review the approach from a Security Officer point of view and to mitigate the approach. He/She will be very please about the solution
as :

- It offers a great level of flexibility to support the Java Standards (JAAS, WS-Security, ...) or enacted by the company,
- The applications developed will be independent of any third party tool or vendor to support the security,
- But it will certainly complain that the approach is really intrusive, will require to develop some home security code to support the interceptor pattern.
- The management of the collection of the Microservices will be very difficult as we will suffer from a lack of governance & centralized capability as proposed
  by an Api Management platform to define in one place the security rules applied to the different services, the services secured, ...

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

The gateway pattern as depicted within the next diagram delegates the responsibility to manage the security aspect to an external player
which is an interceptor acting as a proxy. This actor which is an Api Management Actor can be part of the same network segment where the Microservices 
are deployed or can be deployed behind a firewall to reenforce the security of the Services hosted by the Java containers.

![interceptor-jetty]({{ site.url }}/images/security/rest-3.png)

The [Apiman](http://apiman.io) project has been developed to support this pattern and has the advantage that it can be deployed on premises. The role of the Api Management
platform will be to :

- Define centrally the different services/endpoints to be secured (REST, Web Service),
- Select using the available plugins the Policies to be applied (Basic Auth, OAuth2, ...), 
- Map the Web Resources, the Web Actions (GET, POST, PUT, DELETE) with the Services 
- Deploy within one to many Gateways (HTTP Web Server, Vert.x) the policies that the Server will use to intercept the HTTP requests

The management of the services and the governance is simplified for the Security officer using the Apiman Server. 

![apiman-1]({{ site.url }}/images/security/apiman-1.png)

This Web Console will allow to :

- Manage the services according to an organization which abstract the related services,
- Select the services to be secured and assign to an Api,
- To group using a plan the policies to be applied to the different services,
- To select the plugins to be used; Basic Authentication, Authorization, Black listed IP, White IP list, Throttle, Keycloak - OAuth2
- To manage the different versions of the Apis published,
- To integrate the gateway where the policies are applied top of the requests received,
- To collect the metrics/statistics of the usage done

![apiman-2]({{ site.url }}/images/security/apiman-2.png)

## Conclusions

The adoption of an Api Management Platform will certainly offer more possibilities to govern the Microservices deployed in different Java containers
and potentially non Java containers as offered by the Node.js technology.

Some additional benefits about this technology concern also :

- The disappearance of the coopling between the Services and the frameworks required to authenticate/authorize the access to the Microservices, 
- The centralization in one place of the Apis, consumers of the services,
- The collection/aggregation of the metrics/statistics about the usage of the services which is required within some institutions where Security Audit are performed regularly

There are nevertheless some drawbacks as summarized within the next table as a new technology must be investigated and adopted within the company, the performance could be impacted as a Proxy HTTP Server will interrupt the normal flow between
the HTTP Agent and the Service, the project will become dependent of the Api Product used and new processes will be defined within the company to support the Api platform like also the actors in charge to maintain the 
security governance and the infrastructure.

<table class="pure-table pure-table-bordered">
    <thead>
        <tr>
            <th>Pros</th>
            <th>Cons</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Centralized governance policy configuration</td>
            <td>Performance</td>
        </tr>
        <tr>
            <td>Loose coupling</td>
            <td>New Architecture Brick</td>
       </tr>
        <tr>
            <td>Tracking of APIs and consumers of those APIs</td>
            <td>Plugins available</td>
        </tr>
        <tr>
            <td>Gathering statistics/metrics</td>
            <td>Product locked</td>
        </tr>
        <tr>
            <td>Simplify security audit</td>
            <td></td>
        </tr>
    </tbody>
</table>

While the usage of the Interceptor completed with the Web Container Security Model will be adopted by many companies when the number of the Microservices to secure is not important
or when few security standards should be supported, it is evident that the Gateway Pattern will become the defacto standard within large institutions where the security audit and the governance
aspects are non negotiable.

Another benefit of the Api Management Platform when it is used top of a Virtualized environment as proposed by the OpenShift/Kubernetes Cloud platform is the fact that the MicroServices deployed
and running as Pod will be automatically discovered !