---
layout: post
title: "3 reasons to use, fuse]Source Documentation"
date: 2012-04-13
categories:
- fuse
- Camel
- ActiveMQ
- Apache
- ServiceMix
- Documentation
tags: [documentation, fuse]
comments: true
share: true
---

As an Apache Committer but also, fuse]Source Consultant and Solution Architect, the documentation and its quality is a critical factor in my day by day work.
Until now, even using google search engine, that was difficult to find in one place all the information required and a wasting time.
This situation has changed and I will give you three reasons to use/adopt [fuseSource documentation](http://fusesource.com/documentation/) for your Apache Camel,
Apache ServiceMix and Apache Karaf projects.

  * Reason 1 :, fuse] maintains history of Apache releases

    Apache websites use Atlassian Confluence to maintain content publishes for each Apache project. This tool does not allow to create a history of the different releases
    of Camel by example (2.6, 2.7, 2.8). This is not the case with, fuse]Source documentation which allow you to browse a [specific version](http://fusesource.com/documentation/) like the current.
    And with the help of google, you can make specific search like that

    {% highlight text %}
    site:fusesource.com/docs/router/2.8/ "followed by the keywords"
    site:fusesource.com/docs/router "followed by the keywords"
    {% endhighlight %}

  * Reason 2 : Javadoc and Schema are well documented

    [Javadoc](http://fusesource.com/docs/router/2.8/apidoc/index.html) & [XML Schema](http://fusesource.com/docs/router/2.8/xmlref/index.html) are also 2 interesting examples as there is no XML Schema javadoc
    on Apache websites for by example Spring DSL language. This is completely different on, fuse]Source web site as you can read the documentation and discover what are the tags or attributes
    to be used for specific Apache Camel DSL words. Here is an example containing the options proposed for [Apache Camel Dataformat](http://fusesource.com/docs/router/2.8/xmlref/http.camel.apache.org.2033734988/element/dataformats.html).

    <a href="{{site.url}}/assets/images/xsd_schema_doc.png" imageanchor="1" style="margin-left: 1em; margin-right: 1em;"><img border="0" height="200" src="{{site.url}}/assets/images/xsd_schema_doc.png" width="320"/></a>

  * Reason 3 : New chapters have been added

   , fuse]Source documentation contains missing pieces of information or cover in depth points which are described poorly on Apache websites or in an disparate way.
    So take the time to read the following chapters when you need more info about Transactions, Security, ...

    - [Camel and Transactions](http://fusesource.com/docs/router/2.8/transactions/front.html)
    - [Camel and WebServices](http://fusesource.com/docs/router/2.8/camel_cxf/front.html)
    - [fuse ESB - Security guide](http://fusesource.com/docs/esb/4.4.1/esb_security/front.html)
    - [Transactions with, fuse] ESB](http://fusesource.com/docs/esb/4.4.1/camel_tx/front.html)
    - [ActiveMQ - Security](http://fusesource.com/docs/esb/4.4.1/amq_security/front.html)
    - [ActiveMQ - Tuning Guide](http://fusesource.com/docs/esb/4.4.1/amq_tuning/front.html)
