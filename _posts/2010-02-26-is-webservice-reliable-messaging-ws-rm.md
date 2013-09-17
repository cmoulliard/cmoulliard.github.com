---
layout: post
title: "Is Webservice Reliable Messaging (WS-RM) the ugly duck of the WS-* story ?"
date: 2010-02-26
comments: true
tags:
share: true
---

The Webservice Reliable Messaging (WS-RM) specification has been designed a couple of years ago (2005) but few projects used it. Different reasons could explain this situation, the complexity of the specification, the cost to implement it and the lack of use cases. Personally, I think that developers and architects adopt a low profile when designing a solution and spend times developing alternative solutions providing functionality similar to what WS-RM propose.

WS-RM is not the ugly duck of the story and need more attention from community because it allows you to design solutions where you will be able to

- Guaranty message (= web services) delivery, which is required by example in financial order processing, alarm monitoring systems, ...
- Control the communication between the client and the server (retransmission, ...)
- Implement asynchronous solution by decoupling the request from the reply

Well, what I have presented could be assimilated to a style exercise or a faith act and you will adhere or not. To convince you about the interests to use WS-RM, I will show you How easy it is to design such a solution using Apache CXF framework. This process can be achieved in three simple steps

**STEP 1 : Turn on your WSDL contract into a WS-addressing**

WS-addressing needs to be used because the communication between the client and server will be governed by the Reliable Message Server which is running on a different port address number. This decoupling is required to allow to retransmit message, acknowledge the messages received and guaranty the delivery

Here is one way that you can adopt :
{% highlight xml %}
<!-- Service definition -->
<wsdl:service name="ReportIncidentEndpointService">
  <wsdl:port name="ReportIncidentPort" binding="tns:ReportIncidentBinding">
    <soap:address location="http://localhost:8080/cxf/camel-example/incident">
      <!-- <wswa:usingaddressing wswa="http://www.w3.org/2005/02/addressing/wsdl"> -->
      <wswa:usingaddressing wswa="http://www.w3.org/2005/02/addressing/wsdl"></wswa:usingaddressing>
    </soap:address>
  </wsdl:port>
</wsdl:service>
{% endhighlight %}

the other consists in to use a Policy Rule (= WS-Policy)

{% highlight xml %}
<wsp:policy wsp="http://www.w3.org/2006/07/ws-policy">
  <wsam:addressing wsam="http://www.w3.org/2007/02/addressing/metadata">
    <wsp:policy></wsp:policy>
  </wsam:addressing>
</wsp:policy>
{% endhighlight %}

**STEP 2 : Activate the Reliable Server**

Now that the WSDL contract is modified, we need to add the Reliable Server to the configuration of our platform. I will show you using a spring xml configuration file but you can use the Apache CXF classes in your java code if you want

To activate the RM server, you simply needs to add the tags wsa:addressing and wsrm-mgr in the bus definition of Apache CXF. Different parameters are available like retransmission interval, acknowledge interval. I will not digg into these ones and if you need information, I invite you to check Apache CXF javadoc and specifications.

By example< it is possible to activate also the persistence manager of the RM server ;-)

{% highlight xml %}
<cxf:bus>
  <cxf:features>
    <cxf:logging>
      <wsa:addressing>
        <wsrm-mgr:reliablemessaging>
          <wsrm-policy:rmassertion>
            <wsrm-policy:baseretransmissioninterval milliseconds="4000">
              <wsrm-policy:acknowledgementinterval milliseconds="2000"></wsrm-policy:acknowledgementinterval>
              <wsrm-mgr:destinationpolicy>
                <wsrm-mgr:ackspolicy intramessagethreshold="0"></wsrm-mgr:ackspolicy>
              </wsrm-mgr:destinationpolicy>
            </wsrm-policy:baseretransmissioninterval>
          </wsrm-policy:rmassertion>
        </wsrm-mgr:reliablemessaging>
      </wsa:addressing>
    </cxf:logging>
  </cxf:features>
</cxf:bus>
{% endhighlight %}

If you use CXF in combination with the Routing engine Apache Camel, you don't need to do additional things as the Apache CXF endpoint who will consume the web servicesmessages
will be drived by the RM server of CXF.

Here is an example of Camel integration with CXF

{% highlight xml %}
<!-- webservice endpoint -->
<cxfcamel:cxfendpoint id="reportIncident" address="http://localhost:8080/cxf/camel-example/incident" serviceclass="org.apache.camel.example.reportincident.ReportIncidentEndpoint" s="http://reportincident.example.camel.apache.org"></cxfcamel:cxfendpoint>
<!-- Camel route -->
<camel:camelcontext trace="true" xmlns="http://camel.apache.org/schema/osgi">
  <route>
    <from uri="cxf:bean:reportIncident">// messages are consumed from CXF endpoint
      <convertbodyto type="org.apache.camel.example.reportincident.InputReportIncident">// the body of the WS message is converted directly into a POJOI
        <to uri="bean:webservice">// We call a POJO to save the reportincident in a DB
          <inOnly uri="osgiqueuingservice:queue:in">
          // We put the object in a queue
          <transform>
            <method bean="feedback" method="setOk">// We provide a feedback message as the WS is of type request/replyt</method>
          </transform>
        </to>
      </to>
    </convertbodyto>
  </from>
</route>
</camel:camelcontext> 
{% endhighlight %}

**STEP : Verify**

If you use a java client using Apache CXF java classes, you can easily enable the logging of the IN/OUT WS messages exchanged with the RM server and the application.

The following trace shows that :
- a sequence has been started
- each message is identified '' with a UID
- Within a sequence, the messages send receive a messageID
- The server acknowledge the reception (= processing) of the messages

{% highlight text %}
{% endhighlight %}

<textarea rows="10" cols="60">
>>>>> Invoking reportIncident ... for accident : 024-févr.-2010 9:48:39 org.apache.cxf.transport.http.HTTPConduit setUpDecoupledDestinationINFO: creating decoupled endpoint: http://localhost:9999/camel-example/incident2010-02-24 09:48:39.796::INFO:  Logging to STDERR via org.mortbay.log.StdErrLog2010-02-24 09:48:39.828::INFO:  jetty-6.1.212010-02-24 09:48:39.921::INFO:  Started SelectChannelConnector@localhost:999924-févr.-2010 9:48:40 org.apache.cxf.ws.rm.Proxy invokeINFO: Sending out-of-band RM protocol message {http://schemas.xmlsoap.org/ws/2005/02/rm}CreateSequence.24-févr.-2010 9:48:41 org.apache.cxf.interceptor.LoggingOutInterceptor$LoggingCallback onCloseINFO: Outbound Message
---------------------------ID: 1Address: http://localhost:8080/cxf/camel-example/incidentEncoding: UTF-8Content-Type: text/xmlHeaders: {SOAPAction=["http://schemas.xmlsoap.org/ws/2005/02/rm/CreateSequence"], Connection=[Keep-Alive], Accept=[*/*]}Payload:<soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
<soap:header>
  <action xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://schemas.xmlsoap.org/ws/2005/02/rm/CreateSequence</action>
  <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:508b8914-1621-4992-ae2d-2249d417069a</messageid>
  <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://localhost:8080/cxf/camel-example/incident</to>
  <replyto xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">
    <address>http://localhost:9999/camel-example/incident</address>
  </replyto>
</soap:header>
<soap:body>
  <createsequence xmlns="http://schemas.xmlsoap.org/ws/2005/02/rm" ns2="http://schemas.xmlsoap.org/ws/2004/08/addressing">
    <acksto>
      <ns2:address>http://localhost:9999/camel-example/incident</ns2:address>
    </acksto>
    <expires>PT0S</expires>
    <offer>
      <identifier>urn:uuid:b12e4ccf-3bec-4f76-85f6-2b64f24745b5</identifier>
      <expires>PT0S</expires>
    </offer>
  </createsequence>
</soap:body>
</soap:envelope>
--------------------------------------24-févr.-2010 9:48:41 org.apache.cxf.interceptor.LoggingInInterceptor loggingINFO: Inbound Message
----------------------------ID: 1Response-Code: 202Encoding: UTF-8Content-Type: text/xml; charset=utf-8Headers: {content-type=[text/xml; charset=utf-8], Content-Length=[532], Server=[Jetty(6.1.x)]}Payload:<soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
<soap:header>
  <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:73d86df6-7d45-42df-8008-2a4ca48682fd</messageid>
  <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</to>
  <replyto xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">
    <address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/none</address>
  </replyto>
</soap:header>
<soap:body></soap:body>
--------------------------------------24-févr.-2010 9:48:41 org.apache.cxf.interceptor.LoggingInInterceptor loggingINFO: Inbound Message
----------------------------ID: 2Address: /camel-example/incidentResponse-Code: 200Encoding: UTF-8Content-Type: text/xml; charset=UTF-8Headers: {content-type=[text/xml; charset=UTF-8], connection=[keep-alive], Host=[localhost:9999], Content-Length=[1006], User-Agent=[Progress FUSE Services Framework 2.2.6-fuse-01-00], Content-Type=[text/xml; charset=UTF-8], Accept=[*/*], Pragma=[no-cache], Cache-Control=[no-cache]}Payload:
<soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:header>
    <action xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://schemas.xmlsoap.org/ws/2005/02/rm/CreateSequenceResponse</action>
    <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:c8e59735-7738-40b2-98c1-28be15274b35</messageid>
    <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://localhost:9999/camel-example/incident</to>
    <relatesto xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:508b8914-1621-4992-ae2d-2249d417069a</relatesto>
  </soap:header>
  <soap:body>
    <createsequenceresponse xmlns="http://schemas.xmlsoap.org/ws/2005/02/rm" ns2="http://schemas.xmlsoap.org/ws/2004/08/addressing">
      <identifier>urn:uuid:b35dff50-43cb-43ad-9c9e-4b59f7cb55e7</identifier>
      <expires>P0Y0M0DT0H0M0.0S</expires>
      <accept>
        <acksto>
          <ns2:address>http://localhost:8080/cxf/camel-example/incident</ns2:address>
        </acksto>
      </accept>
    </createsequenceresponse>
  </soap:body>
</soap:envelope>
--------------------------------------
24-févr.-2010 9:48:41 org.apache.cxf.ws.rm.soap.RMSoapInterceptor updateServiceModelInfoINFO: Updating service model info in exchange24-févr.-2010 9:48:41 org.apache.cxf.interceptor.LoggingOutInterceptor$LoggingCallback onCloseINFO: Outbound Message
---------------------------
ID: 3Address: http://localhost:8080/cxf/camel-example/incidentEncoding: UTF-8Content-Type: text/xmlHeaders: {SOAPAction=["http://reportincident.example.camel.apache.org/ReportIncident"], Connection=[Keep-Alive], Accept=[*/*]}Payload:
<soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:header>
    <action xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://reportincident.example.camel.apache.org/ReportIncident</action>
    <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:85a1ebac-5293-4daa-a656-987967aa2a4d</messageid>
    <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://localhost:8080/cxf/camel-example/incident</to>
    <replyto xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">
      <address>http://localhost:9999/camel-example/incident</address>
    </replyto>
    <wsrm:sequence ns2="http://schemas.xmlsoap.org/ws/2004/08/addressing" wsrm="http://schemas.xmlsoap.org/ws/2005/02/rm">
      <wsrm:identifier>urn:uuid:b35dff50-43cb-43ad-9c9e-4b59f7cb55e7</wsrm:identifier>
      <wsrm:messagenumber>1</wsrm:messagenumber>
    </wsrm:sequence>
  </soap:header>
  <soap:body>
    <ns2:inputreportincident ns2="http://reportincident.example.camel.apache.org">
      <incidentid>0</incidentid>
      <incidentdate>29-04-2009</incidentdate>
      <givenname>moulliard</givenname>
      <familyname>charles</familyname>
      <summary>This is a web service incident</summary>
      <details>This is a web service incident - details</details>
      <email>cmoulliard@gmail.com</email>
      <phone>+222 10 50 22</phone>
    </ns2:inputreportincident>
  </soap:body>
</soap:envelope>
--------------------------------------
24-févr.-2010 9:48:41 org.apache.cxf.interceptor.LoggingInInterceptor loggingINFO: Inbound Message
----------------------------
ID: 3Response-Code: 202Encoding: UTF-8Content-Type: text/xml; charset=utf-8Headers: {content-type=[text/xml; charset=utf-8], Content-Length=[532], Server=[Jetty(6.1.x)]}Payload:
<soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:header>
    <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:37dceb90-e1f5-4014-9749-8d4ae7eebb13</messageid>
    <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</to>
    <replyto xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">
      <address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/none</address>
    </replyto>
  </soap:header>
  <soap:body></soap:body>
--------------------------------------
2010-02-24 09:48:41.937::INFO: seeing JVM BUG(s) - cancelling interestOps==024-févr.-2010 9:48:42 org.apache.cxf.interceptor.LoggingInInterceptor loggingINFO: Inbound Message
----------------------------
ID: 4Address: /camel-example/incidentResponse-Code: 200Encoding: UTF-8Content-Type: text/xml; charset=UTF-8Headers: {content-type=[text/xml; charset=UTF-8], connection=[keep-alive], Host=[localhost:9999], Content-Length=[1057], User-Agent=[Progress FUSE Services Framework 2.2.6-fuse-01-00], Content-Type=[text/xml; charset=UTF-8], Accept=[*/*], Pragma=[no-cache], Cache-Control=[no-cache]}Payload:
  <soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:header>
      <action xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://reportincident.example.camel.apache.org/ReportIncidentEndpoint/ReportIncidentResponse</action>
      <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:6163b07e-c983-47f7-8699-c4670b61a213</messageid>
      <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://localhost:9999/camel-example/incident</to>
      <relatesto xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:85a1ebac-5293-4daa-a656-987967aa2a4d</relatesto>
      <wsrm:sequence ns2="http://schemas.xmlsoap.org/ws/2004/08/addressing" wsrm="http://schemas.xmlsoap.org/ws/2005/02/rm">
        <wsrm:identifier>urn:uuid:b12e4ccf-3bec-4f76-85f6-2b64f24745b5</wsrm:identifier>
        <wsrm:messagenumber>1</wsrm:messagenumber>
      </wsrm:sequence>
    </soap:header>
    <soap:body>
      <ns2:outputreportincident ns2="http://reportincident.example.camel.apache.org">
        <code>OK</code>
      </ns2:outputreportincident>
    </soap:body>
  </soap:envelope>
--------------------------------------
>>>> Result - code : OK
24-févr.-2010 9:48:43 org.apache.cxf.interceptor.LoggingInInterceptor loggingINFO: Inbound Message
----------------------------
ID: 5Address: /camel-example/incidentResponse-Code: 200Encoding: UTF-8Content-Type: text/xml; charset=UTF-8Headers: {content-type=[text/xml; charset=UTF-8], connection=[keep-alive], Host=[localhost:9999], Content-Length=[955], SOAPAction=["http://schemas.xmlsoap.org/ws/2005/02/rm/SequenceAcknowledgement"], User-Agent=[Progress FUSE Services Framework 2.2.6-fuse-01-00], Content-Type=[text/xml; charset=UTF-8], Accept=[*/*], Pragma=[no-cache], Cache-Control=[no-cache]}Payload:
  <soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:header>
      <action xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://schemas.xmlsoap.org/ws/2005/02/rm/SequenceAcknowledgement</action>
      <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:f57c0b96-fcc3-4543-bbc3-fd15a54622e7</messageid>
      <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://localhost:9999/camel-example/incident</to>
      <replyto xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">
        <address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/none</address>
      </replyto>
      <wsrm:sequenceacknowledgement ns2="http://schemas.xmlsoap.org/ws/2004/08/addressing" wsrm="http://schemas.xmlsoap.org/ws/2005/02/rm">
        <wsrm:identifier>urn:uuid:b35dff50-43cb-43ad-9c9e-4b59f7cb55e7</wsrm:identifier>
        <wsrm:acknowledgementrange lower="1" upper="1"></wsrm:acknowledgementrange>
      </wsrm:sequenceacknowledgement>
      <soap:body></soap:body>
--------------------------------------
24-févr.-2010 9:48:43 org.apache.cxf.ws.rm.soap.RMSoapInterceptor updateServiceModelInfoINFO: Updating service model info in exchange24-févr.-2010 9:48:44 org.apache.cxf.ws.rm.Proxy invokeINFO: Sending out-of-band RM protocol message {http://schemas.xmlsoap.org/ws/2005/02/rm}SequenceAcknowledgement.24-févr.-2010 9:48:44 org.apache.cxf.interceptor.LoggingOutInterceptor$LoggingCallback onCloseINFO: Outbound Message
---------------------------
ID: 6Address: http://localhost:8080/cxf/camel-example/incidentEncoding: UTF-8Content-Type: text/xmlHeaders: {SOAPAction=["http://schemas.xmlsoap.org/ws/2005/02/rm/SequenceAcknowledgement"], Connection=[Keep-Alive], Accept=[*/*]}Payload:
      <soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
        <soap:header>
          <action xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://schemas.xmlsoap.org/ws/2005/02/rm/SequenceAcknowledgement</action>
          <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:c5584570-c10f-49cc-a3ef-c2c53363ec66</messageid>
          <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://localhost:8080/cxf/camel-example/incident</to>
          <replyto lns="http://schemas.xmlsoap.org/ws/2004/08/addressing">
            <address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/none</address>
          </replyto>
          <wsrm:sequenceacknowledgement ns2="http://schemas.xmlsoap.org/ws/2004/08/addressing" wsrm="http://schemas.xmlsoap.org/ws/2005/02/rm">
            <wsrm:identifier>urn:uuid:b12e4ccf-3bec-4f76-85f6-2b64f24745b5</wsrm:identifier>
            <wsrm:acknowledgementrange lower="1" upper="1"></wsrm:acknowledgementrange>
          </wsrm:sequenceacknowledgement>
          <soap:body></soap:body>
--------------------------------------
24-févr.-2010 9:48:44 org.apache.cxf.interceptor.LoggingInInterceptor loggingINFO: Inbound Message
----------------------------
ID: 6Response-Code: 202Encoding: UTF-8Content-Type: text/xml; charset=utf-8Headers: {content-type=[text/xml; charset=utf-8], Content-Length=[532], Server=[Jetty(6.1.x)]}Payload:
          <soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
            <soap:header>
              <messageid xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">urn:uuid:786ad464-fb01-452a-9036-63c383955133</messageid>
              <to xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">http://schemas.xmlsoap.org/ws/2004/08/addressing/role/anonymous</to>
              <replyto xmlns="http://schemas.xmlsoap.org/ws/2004/08/addressing">
                <address>http://schemas.xmlsoap.org/ws/2004/08/addressing/role/none</address>
              </replyto>
            </soap:header>
            <soap:body></soap:body>
--------------------------------------
24-févr.-2010 9:49:12 org.apache.cxf.transport.http_jetty.JettyHTTPServerEngine shutdown
          </soap:envelope>
        </soap:header>
      </soap:envelope>
    </soap:header>
  </soap:envelope>
</soap:envelope>
</soap:envelope>
</textarea>

**Conclusion**

Awesome, isn'it. Even, if you continue to doubt about this specification, I'm pretty sure that in the future you will take it into account into your decision process regarding to what has been developed here.
