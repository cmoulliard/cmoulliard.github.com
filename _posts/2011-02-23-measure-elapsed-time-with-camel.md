---
layout: post
title: "Measure elapsed time with Camel"
date: 2011-02-23
tags: [camel, event-notifier, fuse]
comments: true
share: true
---

With the last version of Apache Camel, we provide a
[event notifier support class](ttp://svn.apache.org/viewvc/camel/trunk/camel-core/src/main/java/org/apache/camel/management/EventNotifierSupport.java?view=markup) which allow to keep information about what happen on Exchange, Route and Endpoint. One of the benefit of this class is that you can easily audit messages created in Camel Routes, collect information and report that in log by example.

When developing an application, it is very important to calculate/measure elapsed time on the platform to find which part of your code, processor or system integrated which is the bad duck and must be improved.

In three steps, I would show you How to enable this mechanism to report :

- Time elapsed to call an endpoint (could be another camel route, web service, ...)
- Time elapsed on the route exchange

STEP 1 - Create a Class implementing the EventNotifierSupport

{% highlight java %}
public class AuditEventNotifier extends EventNotifierSupport {

    public void notify(EventObject event) throws Exception {
        if (event instanceof ExchangeSentEvent) {
            ExchangeSentEvent sent = (ExchangeSentEvent) event;
            log.info(">>> Took " + sent.getTimeTaken() + " millis to send to external system : " + sent.getEndpoint());
        }

        if (event instanceof ExchangeCompletedEvent) {
            ExchangeCompletedEvent exchangeCompletedEvent = (ExchangeCompletedEvent) event;
            Exchange exchange = exchangeCompletedEvent.getExchange();
            String routeId = exchange.getFromRouteId();
            Date created = ((ExchangeCompletedEvent) event).getExchange().getProperty(Exchange.CREATED_TIMESTAMP, Date.class);
            // calculate elapsed time
            Date now = new Date();
            long elapsed = now.getTime() - created.getTime();
            log.info(">>> Took " + elapsed + " millis for the exchange on the route : " + routeId);
        }
    }

    public boolean isEnabled(EventObject event) {
        return true;
    }

    protected void doStart() throws Exception {

        // filter out unwanted events
        setIgnoreCamelContextEvents(true);
        setIgnoreServiceEvents(true);
        setIgnoreRouteEvents(true);
        setIgnoreExchangeCreatedEvent(true);
        setIgnoreExchangeCompletedEvent(false);
        setIgnoreExchangeFailedEvents(true);
        setIgnoreExchangeRedeliveryEvents(true);
        setIgnoreExchangeSentEvents(false);
    }

    protected void doStop() throws Exception {
        // noop
    }
}
{% endhighlight %}

Not really complicated and the code is explicit. Check the doStart() method to enable/disable the events for which you would like to gather information.

This example uses only Exchange.CREATED_TIMESTAMP property but the next version of Camel 2.7.0 will provide you the property exchange.RECEIVED_TIMESTAMP and so you will be able to calculate more easily the time spend by the exchange to call the different processors till it arrives at the end of the route.<br/><br/>This example collects Date information but you can imagine to use this mechanism to check if your route processes the message according to SLA, ....

STEP 2 - Instantiate the bean in Camel Spring XML
{% highlight xml %}
<!-- Event Notifier -->
<bean id="auditEventNotifier" class="com.sfr.audit.AuditEventNotifier">
</bean>
{% endhighlight %}    

By adding this bean definition, Camel will automatically register it to the CamelContext created.
    
STEP 3 - Collect info from nto the log
{% highlight text %}
18:10:46,060 | INFO  | tp1238469515-285 | AuditEventNotifier               | ?                                   ? | 68 - org.apache.camel.camel-core - 2.6.0.fuse-00-00 | >>> Took 3 millis for the exchange on the route : mock-HTTP-Server
18:10:46,062 | INFO  | tp2056154542-293 | AuditEventNotifier               | ?                                   ? | 68 - org.apache.camel.camel-core - 2.6.0.fuse-00-00 | >>> Took 25 millis to send to external system : Endpoint[http://localhost:9191/sis]
18:10:46,077 | INFO  | tp2056154542-293 | AuditEventNotifier               | ?                                   ? | 68 - org.apache.camel.camel-core - 2.6.0.fuse-00-00 | >>> Took 103 millis for the exchange on the route : ws-to-sis
{% endhighlight %}