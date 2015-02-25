---
layout: post
title: "Loadbalancing with fuse"
description: "Different options available to loadbalance requests, message with fuse (Broker, Camel, ...)"
date: 2008-09-11
categories:
- Load Balancing
- Integration
tags: [fuse, load balancing]
comments: true
share: true
---
# Load Balancing with fuse

As load balancing can be achieved with different pieces of the architecture, we must make the distinction between the broker engine and camel

## Broker

- [Only for JMS Consumer : Loadbalancing (only available for JMS Consumer)](http://activemq.apache.org/clustering.html)[, config](http://rabidwoodpecker.blogspot.be/2010/09/configuring-apache-activemq-for.html)
- Using Fabric

## fuse platform


### Without Fabric

- [Hardware](http://http://www.serverwatch.com/trends/article.php/3937981/5-Load-Balancers-You-Need-to-Know.htm)
	- [F5 Big-IP](http://http://www.f5.com/products/big-ip/),
	- [Cisco](),
	- [Juniper](http://http://www.juniper.net/us/en/products-services/network-edge-services/)

- Software
    - External to fuse :
    	- [RedHat HTTP Mod_cluster](http://www.jboss.org/mod_cluster),
    	- [Apache mod_proxy](https://httpd.apache.org/docs/2.2/mod/mod_proxy_balancer.html),
    	
    	- [HA_Proxy](http://haproxy.1wt.eu/),
    	- [BalanceNG ](http://www.inlab.de/balanceng/)
    - Using fuse Technology :
    	- Apache Camel ([Loadbalancing EIP](http://camel.apache.org/load-balancer.html)

### With Fabric

Use one of the fabric client (http://fuse.fusesource.org/fabric/docs/user-guide.html) as we need to query the Zookeeper registry to find a "client" which could used for loadbalancing. 

  * [fabric-camel](https://github.com/jboss-fuse/fuse/tree/master/fabric/fabric-examples/fabric-camel-cluster-loadbalancing),	
  * [fabric-cxf](https://access.redhat.com/site/documentation/en-US/JBoss_fuse/6.0/html-single/Configuring_Web_Service_Endpoints/index.html#FabricHA-LoadBal-Client),
  * [fabric-amq](http://fuse.fusesource.org/mq/docs/mq-fabric.html)