---
layout: post
title: "Cloud Integration with Fabric Camel (Part I)"
date: 2012-06-28
categories:
 - Fuse Fabric
 - Cloud
 - Apache Camel
tags: cloud fabric8 integration middleware Fuse
image:
  feature: croatia-4.png
  credit: Charles Moulliard
  creditlink: cmoulliard.github.io
comments: true
share: true
---

Since the launch of <a href="http://fusesource.com/products/fuse-esb-enterprise/" target="_blank">Fuse ESB 7</a>, <a href="http://fusesource.com/products/fuse-mq-enterprise/" target="_blank">Fuse MB 7</a>
and in fact the official release of&nbsp;<a href="http://fuse.fusesource.org/fabric/download.html" target="_blank">Fuse Fabric</a>, Camel has moved in a new era called "Cloud Integration&nbsp;where machines,
services are distributed everywhere.<br /><a href="http://www.fusesource.com/" target="_blank">FuseSource</a>&nbsp;(which is now part of <a href="http://www.redhat.com/" target="_blank">RedHat</a> company)
is the leader of "Integration Everywhere" has supported the development of the Fabric project to facilitate the creation of such integration project in the "cloud".<br /><br />

With Fabric, the information of the Camel endpoints are registered in a global registry which can be accessed by the different machines part of the cloud.

This mechanism which is presented here :
<figure>
<object width="320" height="266" class="BLOG_video_class" id="BLOG_video-17c3ad9608687d75" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0">
<param name="movie" value="//www.youtube.com/get_player">
<param name="bgcolor" value="#FFFFFF">
<param name="allowfullscreen" value="true">
<param name="flashvars" value="flvurl=http://redirector.googlevideo.com/videoplayback?id%3D17c3ad9608687d75%26itag%3D5%26source%3Dblogger%26app%3Dblogger%26cmo%3Dsensitive_content%253Dyes%26ip%3D0.0.0.0%26ipbits%3D0%26expire%3D1380471702%26sparams%3Did,itag,source,ip,ipbits,expire%26signature%3D8BDF83DA94DB5455AAC0B0431AEFD7FA00C619CF.A8D8C878CA6078820AFCEA3967C7598C307DEA42%26key%3Dck2&amp;iurl=http://video.google.com/ThumbnailServer2?app%3Dblogger%26contentid%3D17c3ad9608687d75%26offsetms%3D5000%26itag%3Dw160%26sigh%3DsPqzA4NdAE8ESIBjpYmAnZwqLbM&amp;autoplay=0&amp;ps=blogger"><embed src="//www.youtube.com/get_player" type="application/x-shockwave-flash" width="320" height="266" bgcolor="#FFFFFF" flashvars="flvurl=http://redirector.googlevideo.com/videoplayback?id%3D17c3ad9608687d75%26itag%3D5%26source%3Dblogger%26app%3Dblogger%26cmo%3Dsensitive_content%253Dyes%26ip%3D0.0.0.0%26ipbits%3D0%26expire%3D1380471702%26sparams%3Did,itag,source,ip,ipbits,expire%26signature%3D8BDF83DA94DB5455AAC0B0431AEFD7FA00C619CF.A8D8C878CA6078820AFCEA3967C7598C307DEA42%26key%3Dck2&iurl=http://video.google.com/ThumbnailServer2?app%3Dblogger%26contentid%3D17c3ad9608687d75%26offsetms%3D5000%26itag%3Dw160%26sigh%3DsPqzA4NdAE8ESIBjpYmAnZwqLbM&autoplay=0&ps=blogger" allowFullScreen="true" />
</object>
<figcaption>You Tube link of the video : <a href="http://www.youtube.com/watch?v=CO1WcTFivT0">http://www.youtube.com/watch?v=CO1WcTFivT0</a></figcaption>
</figure>
requires some Fabric agents which are used to communicate with the registry (= Fabric ensemble).
This ensemble is in fact a Zookeeper server which will contain configurations, profiles and of course services registered. When a Camel producer wants to
publish a camel exchange, it will perform a lookup in the registry to find endpoints using as a key the name associated to a fabric group.
The endpoints available are returned as a list :

{% highlight text %}
zk:list -r -d fabric/registry/camel
endpoints/local/00000000000 = jetty:http://0.0.0.0:9090/fabric
endpoints/local/00000000001 = jetty:http://0.0.0.0:9191/fabric
{% endhighlight %}

As [Fabric agents](http://fuse.fusesource.org/fabric/docs/fabric-agent.html) which are deployed in [Fabric containers](http://fuse.fusesource.org/fabric/docs/fabric-cloud-containers.html)
can be added or removed dynamically according to the needs, this list is continuously refreshed. The endpoint registered could be of type Jetty, CXF, Mina, Netty, JMS as they are able to transport information
from a machine to another using HTTP, JMS, TCP/IP ... protocols.

The other advantage of this mechanism is that the Camel producer when communicating with Fabric will use a internal loabalancer if more than one Camel route exposing the service has been deployed in several Fabric containers.

To deploy a Fabric Camel project, you just need to use some of the [Fabric commands](http://fuse.fusesource.org/fabric/docs/commands/commands.html) to create the profiles of the camel routes exposed as service and also used to consume the services.

With such profiles, we can create Fabric containers and deploy the camel routes as the following video will show you
<figure>
<object width="320" height="266" class="BLOG_video_class" id="BLOG_video-ec547792c86cc7ac" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0">
<param name="movie" value="//www.youtube.com/get_player"><param name="bgcolor" value="#FFFFFF">
<param name="allowfullscreen" value="true">
<param name="flashvars" value="flvurl=http://redirector.googlevideo.com/videoplayback?id%3Dec547792c86cc7ac%26itag%3D5%26source%3Dblogger%26app%3Dblogger%26cmo%3Dsensitive_content%253Dyes%26ip%3D0.0.0.0%26ipbits%3D0%26expire%3D1380471702%26sparams%3Did,itag,source,ip,ipbits,expire%26signature%3D753800916D774327F39DDA0FAF0E75137CB3E85F.A923FB83EFFA80F15A72B5728FDDE26E19241F%26key%3Dck2&amp;iurl=http://video.google.com/ThumbnailServer2?app%3Dblogger%26contentid%3Dec547792c86cc7ac%26offsetms%3D5000%26itag%3Dw160%26sigh%3DKWQXCRxEjnKMKDumBUkF39xprmE&amp;autoplay=0&amp;ps=blogger"><embed src="//www.youtube.com/get_player" type="application/x-shockwave-flash" width="320" height="266" bgcolor="#FFFFFF" flashvars="flvurl=http://redirector.googlevideo.com/videoplayback?id%3Dec547792c86cc7ac%26itag%3D5%26source%3Dblogger%26app%3Dblogger%26cmo%3Dsensitive_content%253Dyes%26ip%3D0.0.0.0%26ipbits%3D0%26expire%3D1380471702%26sparams%3Did,itag,source,ip,ipbits,expire%26signature%3D753800916D774327F39DDA0FAF0E75137CB3E85F.A923FB83EFFA80F15A72B5728FDDE26E19241F%26key%3Dck2&iurl=http://video.google.com/ThumbnailServer2?app%3Dblogger%26contentid%3Dec547792c86cc7ac%26offsetms%3D5000%26itag%3Dw160%26sigh%3DKWQXCRxEjnKMKDumBUkF39xprmE&autoplay=0&ps=blogger" allowFullScreen="true" />
</object>
<figcaption>Youtube Link of the video : <a href="http://www.youtube.com/watch?v=RUg2rgY4BME">http://www.youtube.com/watch?v=RUg2rgY4BME</a></figcaption>
</figure>

Remark : The code of the demo is available here : <a href="https://github.com/fusesource/fuse/tree/master/fabric/fabric-examples/fabric-camel-cluster-loadbalancing">https://github.com/fusesource/fuse/tree/master/fabric/fabric-examples/fabric-camel-cluster-loadbalancing</a>

Enjoy Cloud Integration Camel Rider !


