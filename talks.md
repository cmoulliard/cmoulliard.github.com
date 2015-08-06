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

*****************************************************

## Continuous Delivery & Integration with Fabric8 on Cloud platform

## Abstract : 

The cost of a change within an IT project impacts all the departments and specifically the integration projects where we have to interface many different systems, play with different protocols and formats. This is why a DevOps strategy to manage this change, as to deliver the modifications from the development to the production environment is critical to guarantee the success/profitability of a project and to simplify the maintenance process too. Such strategy will naturally provide a more agile development model, reduce the costs and will allow new business requirements to be adopted more frequently.

To sustain this vision and architecture paradigm the opensource Fabric8 project has developed some continuous delivery tools (Maven Plugin, API) around the Docker container & Google Kubernetes API to manage the required applications part of the DevOps strategy : Jenkins Jobs & DSL, Gerrit for code review, Gogs / Gitlab to host the git repositories and Nexus to manage the artifacts.

In this session, we’ll present and demystify the technology used and we will demonstrate based on a simple integration project (Apache Camel REST service), how we could manage it from dev to integration by adopting the DevOps Principe and by deploying using the Fabric8 tools the required applications top of the Openshift V3 platform.


