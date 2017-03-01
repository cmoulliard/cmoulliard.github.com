---
layout: page
permalink: /talks/index.html
title: Some of my talks
tags: Talk Integration
---

## Title : Hawtio – The modular AngularJS Web Console for managing your Java JMX stuffs

## Abstract :

The management of java applications is mainly based on JMX technology and rely on MBeans objects & MBean server. While many client tools exist, ... they are mostly used by developer and not by teams in charge of the operations. Moreover JMX uses "proprietary" protocols for the communication between the agent and the server (RMI, IIOP, ...) which are generally not allowed for different reasons by the security advisers.
The goal of the hawtio project is to propose a Web HTML5 architecture to let the developer to manage their MBeans and extend them with specific plugins using a jolokia gateway which is a bridge between the MBean Server, translating REST JSON requests into JMX calls.
During this talk, we’ll present what is the internal architecture of the platform (html5 - jolokia - angularJS) & how we can connect/inspect different JVMs using web Console. Next, we will have a look to some of the plugins developed & explore the various ways how hawtio can be extended; developing a plugin, design your own dashboard and manage the resources using git -wiki.
Slides, code & demos


****************************************************

## Title : Improve your Documentation publication process 

## Abstract : 

As Software engineer, teacher, technical writer, we spend lot of time to design and prepare the content of our presentations, documentation, book, course for a training ... using different software like Powerpoint, Impress, Confluence, Keynote...

As these tools don't separate the content from the layout like we can do with HTML & CSS we have the impression that we loose our time to move objects, change color, text font, styles, rendering and so on. This methodology is counter productive and does not let the presenter to focus its time on the content like you can do with a markup language (Mardown, Kramdown, Asciidoc, Asciidoctor) where we can in a more natural way design the content in plain text using macro instructions to let the parser/processor to render the content in an appropriate format (HTML, PDF, Slideshow).

The goal of this presentation is to show you how you can leverage the Asciidoctor projects (Asciidoc / Markup language and parser) to design quickly and in an efficient way your documentation, web site & slideshow presentation.

During this presentation, we will show how a publication process could be simplified with Asciidoctor/Hyla including : how to set up a project from template (book, article, blog, report, ...), Create a project structure from a table of content, Add new pages from artefacts (audio, video, table, article, book, ...), Generate the content in HTML or Slideshow (DeckJS, RevealJS), PDF, watch it to make modifications (using LiveReload), Edit the content in your browser and developp content in a collaborative way, Change the look and feel, publish it.

****************************************************

## Title : Big Data & Elasticsearch Integration

## Abstract :

With the explosion of the data collected by the Web 2.0 platforms (Twitter, Facebook, Linkedin, ...), projects have integrated the concept of 'Big Data' in their architecture design and adopted 'No SQL' backends to host the JSON Data, Documents collected. While such backends offer a lot of capabilities including replication, hig-speed access to data, indexing they have not been designed to decide What and How the information should be stored. This is why the usage of a Java Integration framework like Apache Camel becomes a key success in such a project's conception to aggregate, validate, transform or inter-exchange formats between systems/platforms. 

With so huge volumes of data collected, it becomes necessary not only to combine integration, the NoSQL backends but also elastic Search capabilities to manage, store, transfom and query such ecosystem.

This presentation will introduce Apache Camel Integration framework and will show how its can help to design such architecture by formating the data, collecting and transforming them and integrate information from Hadoop, MongoDB backends. Next we will combine the power of Apache Camel with ElasticSearch/Kibana projects to use the Full Text Search features of Lucene to query aggregated data from different 'big data' centers and manage them.

***************************************************

## Title : Development of Social Network Projects with Camel

## Abstract: 

Apache Camel (Java Integration Framework) which has been created 6 years ago follow the Web 2.0 evolution and specifically the new trend that we categorize today as 'Social Network'. As a framework supporting the Enterprise Integration Patterns Camel simplifies the way you format the data, take decision (Content Base Routing, Dynamic Routing), manage the exchange of informations, distribute/split or aggregate the informations according to rules defined.
It provides not only the features to integrate systems/applications but components to operate with such socialized platform like Facebook, Twitter, Yammer, Dropbox, Gmail ...

The goal of this presentation is to show how developers/architects can use Camel technology to manage marketing information, organize campaign, promote products/ideas/information to people connected by leveraging these new Camel components. 

We will focus on also on how to orchestrate communication, exchange of informations with the social networks by using the EIP patterns, ActiveMQ as transport layer
, OAuth to secure the communications. 

The presentation will briefly present Apache Camel as Integration framework, Patterns to be used to aggregate/orchestrate the information, social components (Twitter, Facebook, Yammer, Gmail) and we will finish with a Demo demonstrating how a social campaign (twitter) could be easily designed, developed and results (metrics) measured to quantify the campaign.

****************************************************

## Title : Services at Works with Fuse Technology

## Abstract:

 With the emergence of 'cloud' capabilities (OpenShift, Openstack, ...) which is currently offered for IT, integration projects are moving into a new era where Services are not only exposed locally within client's premises but in the cloud. Fuse Fabric technology has been designed to address specifically this need in term of Clustering, Loadbalancing, Virtualization but also by provisioning containers where Services (WS, Rest, DOSGI, Queues/Topics, ...) will be hosted.  By combining Fuse Fabric & AMQ, information received from services can be persisted, replicated between different 'cloud' data centers before to be consumed. This talk will present the Fuse Fabric (aka iPaas) platform and how services can be deployed, clustered, loadbalanced & maintained in a cloud farm.

****************************************************

## Title : When CDI - Weld meets OSGI Modularity World

## Abstract:

 Modularity for Java World has been addressed since 10 years using OSGI (EE)  specifications and is currently used by the platform Apache Karaf, JBoss Fuse as their core Java Container to simplify packaging, deployment and maintenance of ‘integration’ projects but also to support multi-technology like : Camel - Integration, CXF - Web/Rest Service, ActiveMQ - Messaging engine, Spring - Framework, CDI - IoC, JPA - Persistence, JTA - Transaction, Web, ...).

Context and Dependency Injection (aka CDI) was invented and standardized by Red Hat to integrate a modern IoC solution in Java EE. Even if they use a different approach, Injection like OSGI allows to think our applications in a modular and different way as we can expose public API, Services ... It is used as a key - centric technology by JBoss EAP platform and related projects/products.

This talk will show how these 2 technologies create a new paradigm to design and develop Java applications as reusable collection of Modules/Services.

After a short introduction to OSGi (Modularity) and CDI (Context & Dependency Injection) concepts, we will present the Open Source Pax CDI project which is the OSGi specification/implementation for CDI. The next part and we will demonstrate how these technologies can be used together using JBoss Weld and JBoss Fuse.

To conclude we’ll show some interesting patterns/demos that can be implemented.

***************************************************

## DSL and Lambdas: the perfect match for more readable and maintainable code

## Abstract:

Software Code is written once but will be read many times and understanding what it does could often be harder than writing it for the first time. That's why readability is a key feature to achieve better maintainability. The adoption of FluentAPI or well designed DSL can dramatically improve it. Functional programming with the declarative style it implies is a perfect fit for creating readable and expressive DSL. The main goal of the talk is to show some techniques and give some hints to design and implement effective and readable DSLs, mostly leveraging the new functional Lambdas features provided by Java 8. The most important patterns that can be helpful for DSL implementation will be explained with many practical examples also showing how some frameworks like Camel and Drools use them.

*****************************************************

## Benefit of microservice & microcontainer architectures

## Abstract:

Microservice & microcontainer aren't new concepts but they gained momentum since a couple months with the emergence of new technology or patterns like Docker, SpringBoot, Kubernetes, Fast messaging, Decentralized Bus, ...

SOA & Integration architectures have been designed for a while around central monolithic ESB, Servers but this model has failed for many reasons (lack of agility, flexibility to evolve according to business changes, ...). Moving to microservice & microcontainer require to figure out what are the benefits, pros & cons and how such technology could be used to match the business requirements and manage, maintain in a more fluent ways the applications.

The goal of this talk is to review the strengths and weaknesses of the "Microservice & microcontainer" architectures/patterns but also to present a "real" use case designed using the Fuse Fabric8 technology with the container Docker, the integration framework Apache Camel, the Fabric8 Gateway (HTTP) and the event message MQTT protocol of Apache ActiveMQ.

*****************************************************

## How Apache Camel technology can simplify your Integration Project Development

## Abstract : 

The Java Integration Framework - Apache Camel  has proven since many years that it is one of the core technology to design java integration projects and a de facto standard to resolve complex enterprise issues using integration patterns : splitter, aggregator, content based router.

The goal of this talk is present and demonstrate how Apache Camel can simplify such Integration Project Development where we have to expose a service and consume it. For the purpose of the talk, we will use a REST endpoint, its DSL language combined with the Netty component to expose the service.

The service will be documented using the Swagger API and we will explain how we can collect the metrics. The service created will be compiled, tested and deployed using a continuous delivery strategy.

The talk contains slides and a demo.

## How Red Hat Middleware Technology Can Simplify Your Integration Project Development
   
The Java Integration Framework - Apache Camel has proven since many years that it is one of the key technology to design easily and quickly java integration projects and a de facto standard to resolve complex enterprise use cases using integration patterns like the splitter, aggregator, content based router, dynamic router and so on.

The goal of this talk is to present and demonstrate how Apache Camel Technology part of the JBoss Fuse product can simplify such Integration Project Development (functional & technical design), reduce the design & development costs by adopting a modular & microservice architecture model and accelerate the deployment process by implementing a continuous delivery strategy as proposed by JBoss Fabric & Openshift Technologies.

*****************************************************

## Continuous Delivery & Integration with Fabric8 on Cloud platform

## Abstract : 

The cost of a change within an IT project impacts all the departments and specifically the integration projects where we have to interface many different systems, play with different protocols and formats. This is why a DevOps strategy to manage this change, as to deliver the modifications from the development to the production environment is critical to guarantee the success/profitability of a project and to simplify the maintenance process too. Such strategy will naturally provide a more agile development model, reduce the costs and will allow new business requirements to be adopted more frequently.

To sustain this vision and architecture paradigm the opensource Fabric8 project has developed some continuous delivery tools (Maven Plugin, API) around the Docker container & Google Kubernetes API to manage the required applications part of the DevOps strategy : Jenkins Jobs & DSL, Gerrit for code review, Gogs / Gitlab to host the git repositories and Nexus to manage the artifacts.

In this session, we’ll present and demystify the technology used and we will demonstrate based on a simple integration project (Apache Camel REST service), how we could manage it from dev to integration by adopting the DevOps Principe and by deploying using the Fabric8 tools the required applications top of the Openshift V3 platform.

******************************************************

## JBoss Fuse in Action - 2-3 Hours lab
   
## Abstract :

This lab will cover JBoss Fuse 6.2 technology in action. The students will design a REST service using the new Camel REST DSL language within JBDS 8, run the project into JBDS studio & debug it. The camel project developed will some common patterns (Content Based Router, Request/Reply, ...) like a Data Transformation (json2json or json2xml) using the new DataMapper Tool. The project created will be tested locally by implementing a A JUnit Test Case and next packaged as a Fabric profile in order to deploy it later on. The REST service will be documented using the Swagger API (which is supported by Fuse) and deployed in a new Fuse Managed container using the Fabric profile created. We will deploy the same service in 2 containers to demonstrate that requests could be loadbalanced.  In an additional step, the REST service will be secured using Apiman & Keycload using Oauth2.  We will see also how we could collect the camel metrics & usage about the service (using Fabric Insight technology & also ApiMan Statistics).

*****************************************************

## Security Enforcement of the Java MicroService Applications

## Abstract :

The Security around the Java Web Services, RESTfull endpoints exposing the Services accessed by the clients are critical in many business applications. Although many technologies/specifications exist currently and can be implemented like WS-Security, WS-SecurityPolicy, 
WS-Trust, WS-Federation, SAML & Oauth2 to secure the endpoints, they require strong Security skills from the Java developers and unfortunately this approach doesn't fit very well in a MicroService architecture running potentially hundred of endpoints and where we have to manage also hundreds of certificates.
During this talk, you will see and discover how you can leverage the advantages to use an API Management platform to enforce the security of your REST endpoints and externalize such management process. We will also discover how we can deploy the Swagger REST documented endpoint within a catalog, define the security of the endpoints and the policies to be applied.
This security enforcement will be designed using some of the policies available to authenticate or authorize the MicroService Application running in a Linux Container using Basic Auth, Oauth2 and Roles. This talk contains slides and demos.

La sécurité des Web Services ou endpoints de type RESTfull qui exposent des services consommés par des clients sont critiques pour les applications métier. Bien que de nombreuses technologies et specifications existent et peuvent être implémentées comme WS-Security, WS-SecurityPolicy, WS-Trust, SAML ou Oauth2 pour sécuriser les endpoints, celles-ci requièrent de fortes compétences
 de la part des codeurs Java et malheureusement, elles ne peuvent pas être facilement mises en chantier dans une architecture de type microservice qui potentiellement contient des centaines de endpoints voir autant de certificats/clés pour les clients connectés. Durant cette conférence, nous allons vous montrer comment on peut tirer partie d'une plateforme de type Api Management & Gateway
  afin de renforcer la sécurité des endpoints de type RESTfull. Vous allez également voir comment on peut utiliser les endpoints REST documentés avec l'API Swagger afin de populer un registre Catalogue, les sécuriser avec l'une des polices existantes (Basic HTTP, OAuth2, Basé sur un rôle) et au final sécuriser le endpoint exposé par un containeur Linux.
Cette conférence contient des slides et une démo.

****************************************************************************

## Develop a Mobile Application connected to a REST Microservice

## Abstract

The development of a Mobile Aplication has been donimated during a decade by proprietary technologies developed by Apple, Android & Microsoft for their own mobile brand. With the development of the Apache Cordova Platform and the framework Ionic, this situation has completely changed as a developer can now design a project using their favorite HTML5, Javascript, AngularJS frameworks and rely on the tool provided by Apache Codova to generate the HTML Hybrid code for the target platform. The purpose of this talk is to demonstrate how such Mobile project can be designed and being integrated with a Backend System which is a REST Camel microservice. Next, we will also explain how we can simplify the Management and the packaging of the NodeJS application used to access the backend service using the Feedhenry Mobile Application platform and its Api.

*****************************************************************************

## WildFly Swarm, the Microservices JavaEE container

## Abstract

Since the emerging of the *Microservices concept* as design pattern to develop Java Applications, the architect and developer are faced to new challenges to be resolved that the traditional Java EE stack doesn't match very well.

This presentation will introduce *WildFly Swarm* as a *Microservices Java EE Container*,  demonstrate how it fits very well within the Microservices & Dockerized world.  

You will also discover the MicroProfile initiative supported by WildFly Swarm which brings new patterns/concepts for Java EE community like the lightweight container, uber jar, circuit break, health check, service discovery & reactive patterns. The concepts & patterns will be introduced using slides but also demonstrated with simple use case demos.

*****************************************************************************

## WildFly Swarm & Microservices Lab

## Abstract

This lab is focused around Microservices & WildFly Swarm. 
The use case to be designed is "A REST endpoint which expose a CD Music store Service that we will query from a REST client to fetch the CD or create a new CD. The store is designed as a NoSQL database (Redis, ...). The endpoints will be documented using Swagger. A circuit breaker will be implemented to return a static list of CD if the service operating the datastore is down."
The Technology to be used is "JAX-RS, CDI, JSON-P, Swagger" with the "Patterns: Service Discovery, Circuit Breaker, Health Check, Load Balancing".
We will guide you to setup, Implement, develop the solution locally like also to build, package & deploy the solution on Kubernetes. Finally we will review and challenge the solution developed with Q/A.

### Introduction
* What is a Microservices Architecture
* Why WildFly Swarm fits very well within this model
* Review the use case to be designed "A REST endpoint will expose a CD Music store Service that we will query from a REST client to fetch the CD or create a new CD. The store is designed as a NoSQL database (Redis, ...). The endpoints will be documented using Swagger. A circuit breaker will be implemented to return a static list of CD if the service operating the datastore is down.
* Technology used : JAX-RS, CDI, JSON-P, Swagger
* Patterns : Service Discovery, Circuit Breaker

### Setup the environment
* Implementation
* Design and develop the solution locally
* Add test case
* Build, package & deploy the solution on Kubernetes/OpenShift

### Review
* Discuss and challenge the solution developed
* Q/A
* What do you think about the lab/technology used

**************************************************************
## Design a SpringBoot Application running on Kubernetes/OpenShift

## Abstract 

The goal of this workshop is to demonstrate how easy it could be to design/develop a microservice architecture using Spring Boot technology, Kubernetes/OpenShift as Containerized & Management platform.

During the workshop, you will learn how to create a Spring Boot Application composed of 3 microservices (a CD Front Store, the backend exposing and running the service & the Database store), transform it to a Kubernetes/Docker Project using the Fabric8 Technology and deploy/operate it on OpenShift. 
 
With the help of JBoss Forge Tool, you will accelerate the creation of the project and scaffolding of the code

*********************************
## Accelerate your Spring Boot Development using JBoss Forge Tool

## Abstract

The creation of a Spring Boot project has been greatly simplified using Spring's initialzr. While this initialzr is a great tool to setup a maven project containing the dependencies of the starters you want to use,
the process to create the project is still web oriented and manual.

The goal of this talk is to introduce you the JBoss Forge Tool project that extends your Java IDE by providing wizards and extensions (add-ons) and how we can use it to create quickly and easily a Spring Boot project,
initialize it using the dependencies required and scaffold the code required for the starters REST, web & JPA.
You will also discover how you can script such creation process to make it fun and cool. 

The talk contains a few slides and is demo focused.
