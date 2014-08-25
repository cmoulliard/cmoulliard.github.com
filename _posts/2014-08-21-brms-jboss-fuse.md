---
layout: post
title: "BRMS runs on OSGI container JBoss Fuse !"
date: 2014-08-22
tags: apache camel drools jbpm brms jbossfuse rules Fuse
share: true
---

We have worked hard this year to improve the **[Drools - Business Logic integration Platform](http://www.kiegroup.org/)** to make it **OSGI compliant**. The modules
of the project have been reviewed to :

- Export/import correctly the packages required by the bundles when deployed into an OSGI container like [Apache Felix](http://felix.apache.org), 
- Register some OSGI services,
- Design a new OSGI classloader to load the Drools resources (drl, bpmn, bpmn2) packaged into the bundles
 
Moreover, the project provides now a [features XML file](http://karaf.apache.org/manual/latest-2.3.x/users-guide/provisioning.html) to provision the different modules (drools, drools-decision-table,bpm, ...)
into **JBoss Fuse** or Apache Karaf
 
Since the release [6.1.0](https://github.com/droolsjbpm/drools) of **Drools/jBPM**, you can capitalize the efforts done and deploys **BRMS** onto the [JBoss Fuse](https://www.jboss.org/products/fuse/overview/) Java Container
which is a **Multi-Technology OSGI Platform** running your **Integration projects** with these technologies :

- [Apache Camel / Java Integration Framework](http://camel.apache.org),
- [Apache ActiveMQ / Messaging broker](http://activemq.apache.org),
- [Apache CXF / REST & WebService Framework](http://cxf.apache.org)

Typically a BRMS project can be designed as a bundle containing the knowledge repository, session, resources and rules to be fired. Using the [BundleActivator](https://github.com/cmoulliard/droolsjbpm-osgi-examples/tree/RH.6.0.3.ER4#simple-rule-example) class,
the project can be started directly by the OSGI container when the event "start" will be called.
This is not the only thing that you can do as you can also use a *Dependency Injection framework* supported by JBoss Fuse product :
 
- [Spring DM](http://docs.spring.io/spring-osgi/docs/current/reference/html/)
- [Apache Aries Blueprint](http://aries.apache.org/modules/blueprint.html)
- [Apache Felix Service Component Runtime](http://felix.apache.org/documentation/subprojects/apache-felix-maven-scr-plugin/scr-annotations.html)
- [CDI](https://ops4j1.jira.com/wiki/display/PAXCDI/Pax+CDI)

to design your architecture. No matter which injection mechanism you prefer to use, JBoss Fuse will scan the bundles deployed and will create with the classloader of the bundle 
create the corresponding Application Context (Spring, Blueprint, Weld, ...) to register the beans discovered as singletons objects.

To help you to start your project, a **quickstart** github repository has been created covering different scenario presented [here](https://github.com/cmoulliard/droolsjbpm-osgi-examples/blob/RH.6.0.3.ER4/README.adoc)
They cover the different strategies described below but also how to externalize the rules, use rules defined in an Microsoft Excell spreadsheet and run some business processes (using or not a persistent layer).

One of the key benefit to use **BRMS** on the **JBoss Fuse** platform is that you can integrate it easily with the *Camel Integration framework* to collect data from different sources, validate or enrich them and next  
inject such info into the Ksession before to fire the rules. Such a scenario is described [here](https://github.com/cmoulliard/droolsjbpm-osgi-examples/blob/RH.6.0.3.ER4/README.adoc#integration-with-camel-example) and a quick demo is attached to this post
too to show you **BRMS** and **Apache Camel** in action.

BRMS & Camel Live !

<script type="text/javascript" src="https://asciinema.org/a/11644.js" id="asciicast-11644" async></script>


