---
layout: post
title: "Real Time HTML5 application with Websocket and ActiveMQ/camel"
date: 2012-04-26
comments: true
categories:
- WebSocket
- Integration
tags: html5 websocket real-time camel activemq
share: true
---

As part of my [presentation](http://fusesource.com/apache-camel-conference-2012/"CamelOne), I have prepared some examples to dig into what Apache
[ActiveMQ](http://activemq.apache.org/) and [Camel](http://camel.apache.org/) propose to work with
[HTML5](http://www.html5rocks.com/en/) and [WebSocket](http://www.websocket.org/) technology.

Developing "Real Time Web Applications" has always been painful not matter if the technology used was based on Java Applet,
Adobe Flash, Adobe ShockWave, Microsoft Silverlight and the protocol (HTTP, RMI, ...).

Since HTML5 publication (2009) and the work done by W3C and IETF organisations, we now have a standard
[rfc-6455](http://datatracker.ietf.org/doc/rfc6455/) that we can use to exchange in a bi-directional way "messages" between the browser and the Web Server.
Only one HTTP(s) request is required to initiate the WebSocket communication and later on the exchange of data frames (text or bytes).

ActiveMQ (release 5.6) like Camel (release 2.10) proposes a WebSocket Transport Connector or Endpoint using Jetty WebServer WebSocket implementation (v7.5).
This allow not only to retrieve data from topics but when combining the EIP patterns of Camel and some components like : sql, jpa, file, rss, atom, twitter, ...
we can "aggregate", "enrich" or "filter" content receive from feed providers before to publish them for feed consumers.

ActiveMQ uses Stomp as a wired format to send WebSockets messages between the WebSocket server running within the ActiveMQ broker and the Web browser.
In this context, we must use one of the two javascript libraries available [stomp.js](http://www.jmesnil.net/stomp-websocket/doc/),
[stomple](https://github.com/krukow/stomple) to develop the project

{% highlight javascript %}
$(document).ready(function() {
var client, destinationQuotes;
$('#connect_form').submit(function() {
var url = $("#connect_url").val();
client = Stomp.client(url);

// the client is notified when it is connected to the server.
var onconnect = function(frame) {

var stockTable = document.getElementById("stockTable");
var stockRowIndexes = {};

client.subscribe(destinationQuotes, function(message) {
var quote = JSON.parse(message.body);
$('.' + "stock-" + quote.symbol).replaceWith("" +
"" + quote.symbol + "" +
"" + quote.open.toFixed(2) + "" +
"" + quote.last.toFixed(2) + "" +
"" + quote.change.toFixed(2) + "" +
"" + quote.high.toFixed(2) + "" +
"" + quote.low.toFixed(2) + "" +
"");
...

client.connect(login, passcode, onconnect);
{% endhighlight %}

and of course the WebSocket protocol must be enable.

{% highlight xml %}
<transportconnectors><br/>
    <transportconnector name="websocket" uri="ws://0.0.0.0:61614"></transportconnector>
</transportconnectors><br/>
{% endhighlight %}

Camel does not need a special format to exchange the data between its WebSocket endpoint and the browser as JSon text will be send through the WebSocket Data Frames to the browser. We must just expose a Camel Route as a WebSocket Server.

{% highlight java linenos %}
public class WebSocketStockPricesRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
       from("activemq:topic:stockQuoteTopic")
       .log(LoggingLevel.DEBUG,"&gt;&gt; Stock price received : ${body}")
       .to("websocket:stockQuoteTopic?sendToAll=true");
     }
}
{% endhighlight %}

and use in the browser the WebSocket HTML5 js script.

{% highlight javascript %}
var socket;
$('#connect_form').submit(function () {

var stockTable = document.getElementById("stockTable");
var stockRowIndexes = {};
var host = $("#connect_url").val();
socket = new WebSocket(host);

// Add a connect listener
socket.onopen = function () {
$('#msg').append('<div class="event">
Socket Status: ' + socket.readyState + ' (open)</div>
');
}

socket.onmessage = function (msg) {
// $('#msg').append('<div class="message">
Received: ' + msg.data + "</div>
");

var quote = JSON.parse(msg.data);

....
{% endhighlight %}

In both cases, you can combine other javascript librairies (jquery, jquery-ui) to improve the design of the JSon objects to be displayed in the browser.
Here are some screenshots about the demos

<a href="{{site.url}}/assets/images//activemq-stocks.png" imageanchor="1">
  <img border="0" height="200" src="{{site.url}}/assets/images//activemq-stocks.png" width="146"/>
</a>

Stock Trader

<a href={{site.url}}/assets/images/chat-camel.png" imageanchor="1" style="clear: left; float: left; margin-bottom: 1em; margin-right: 1em;">
   <img border="0" height="118" src="{{site.url}}/assets/images/chat-camel.png" width="200"/>
</a>

Chat Room

<a href="{{site.url}}/assets/images//news-camel.png" imageanchor="1" style="clear: left; float: left; margin-bottom: 1em; margin-right: 1em;">
   <img border="0" height="163" src="{{site.url}}/assets/images/news-camel.png" width="200"/></a>

Twitter and News Feed Code can be retrieved from [FuseByExample](https://github.com/FuseByExample/) web site.

Look to "[websocket-activemq-camel](https://github.com/FuseByExample/websocket-activemq-camel)" git hub project.

Enjoy WebSocket with Apache Camel and ActiveMQ !