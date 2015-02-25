---
layout: post
title: "Secure Web Services - WS-Security"
date: 2010-09-24
tags: [webservice, ws-security, integration, fuse]
comments: true
---

Security is one of the important key in the success of a IT project but most of the time only user authentication or data encryption are taken into account. So security of the application is often not adressed or leave aside due to complexity of the implementation.
One of the reason explaining this situation comes from the fact that solutions or frameworks proposed to secure an application are difficult to configure and maintain. And this remark prevalls over the specification WS-Security.

In large company having deployed WS-Services to allow intra or inter connection between applications, Web application authentication with HTTPS protocol mechanisms are use to secure platforms. That means that users discovering the credentials used to connect to the web server can potentially have access to the services of the company.

WS-Security offers a way to authenticate the user connected to a web service or allow also a user to be trusted on the web server it is connected. This mechanism is interesting because it reinforce the security but provides also a way to restrict access to unauthorized users to web services.

Apache Camel and CXF frameworks offers a simplify way to implement this with only few lines of code and spring beans definition. Let's see that in action :

**STEP 1**

We only need to use JAAS api to authenticate the user using the following java package "javax.security.auth.callback" and the project
[WS4J](http://ws.apache.org/wss4j/) of Apache. Here is a simple example authenticate a user using a list and the password provided.

{% highlight java %}
package org.apache.camel.example.reportincident;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * Callback handler to handle passwords
 */
public class UTPasswordCallback implements CallbackHandler {

    private Map<String, String> passwords = new HashMap<String, String>();

    public UTPasswordCallback() {
        passwords.put("claus", "sualc");
        passwords.put("charles", "selrahc");
        passwords.put("james", "semaj");
        passwords.put("abcd", "dcba");
    }

    /**
     * Here, we attempt to get the password from the private alias/passwords map.
     */
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        String user = "";

        for (Callback callback : callbacks) {
            WSPasswordCallback pc = (WSPasswordCallback) callback;
            user = pc.getIdentifier();

            String pass = passwords.get(user);
            if (pass != null) {
                pc.setPassword(pass);
                return;
            }
        }

        // Password not found
        throw new IOException("Password does not exist for the user : " + user);
    }

    /**
     * Add an alias/password pair to the callback mechanism.
     */
    public void setAliasPassword(String alias, String password) {
        passwords.put(alias, password);
    }
}
{% endhighlight %} 
   
**STEP 2**

This is here that the magic will operates as we will use Spring beans definition with Apache Web Services Framework - [CXF]("http://cxf.apache.org/) and Apache [Camel](http://camel.apache.org/cxf.html) to expose the web service
    
Instantiate your WS4J bean

{% highlight xml %}
    <bean id="wss4jInInterceptor" class="org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor">
        
        <constructor-arg></constructor-arg>
    </bean>    <bean id="wss4jInInterceptor" class="org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor">
        <constructor-arg>
            <map>
                <entry key="action" value="UsernameToken Timestamp">
                    <entry key="passwordType" value="PasswordDigest">
                        <entry key="passwordCallbackClass" value="org.apache.camel.example.reportincident.UTPasswordCallback">
                            </entry>
                    </entry>
                </entry>
            </map>
            </constructor-arg>
        </bean>
{% endhighlight %}

Add it as an interceptor to CXF to allow CXF to authenticate the user using the credentials provided in the SOAP header definition.

{% highlight xml %}
<cxf:cxfendpoint id="reportIncident" address="http://localhost:9080/camel-example-reportincident/webservices/incident" wsdlurl="etc/report_incident.wsdl" serviceclass="org.apache.camel.example.reportincident.ReportIncidentEndpoint">        
        <cxf:ininterceptors>
            <ref bean="loggingInInterceptor">
                <ref bean="wss4jInInterceptor"></ref>
                
                <cxf:outinterceptors>
                    <ref bean="loggingOutInterceptor"> <!--  <ref bean="wss4jOutInterceptor"> --></ref>
                    </cxf:outinterceptors>
                
                <cxf:cxfendpoint id="reportIncident" address="http://localhost:9080/camel-example-reportincident/webservices/incident" wsdlurl="etc/report_incident.wsdl" serviceclass="org.apache.camel.example.reportincident.ReportIncidentEndpoint">
                    <cxf:ininterceptors></pre>
    Here is an example of the SOAP header
    <pre name="code" class="xml"><soap:envelope soap="http://schemas.xmlsoap.org/soap/envelope/">
        <soap:header>
            <wsse:security wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" mustunderstand="1">
                
                <wsu:timestamp wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" id="Timestamp-2">
                    
                    <wsu:created>2010-09-24T07:31:06.308Z</wsu:created>                    
                    <wsu:expires>2010-09-24T07:36:06.308Z</wsu:expires>
                    </wsu:timestamp>
                
                <wsse:usernametoken wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" id="UsernameToken-1">
                    
                    <wsse:username>charles</wsse:username>                    
                    <wsse:password type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest">KoNvkEh9jwgvxTfcTza6+kHkKNI=</wsse:password>                    
                    <wsse:nonce encodingtype="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary">havIKNKvlRuatlp3CncMKw==</wsse:nonce>                    
                    <wsu:created>2010-09-24T07:31:06.306Z</wsu:created>
                    </wsse:usernametoken>
                </wsse:security>
            </soap:header>
        
        <soap:body>
            <ns2:inputreportincident ns2="http://reportincident.example.camel.apache.org">
                <incidentid>222</incidentid>
                
                <incidentdate>2010-07-14</incidentdate>
                <givenname>Charles</givenname>
                <familyname>Moulliard</familyname>
                <summary>Bla</summary>
                <details>Bla bla</details>
                <email>cmoulliard@apache.org</email>
                <phone>0011 22 33 44</phone>
                </ns2:inputreportincident>
            </soap:body>
        </soap:envelope>
{% endhighlight %}
And finally, declare your camel route using the web services
{% highlight xml %}
<camel:camelcontext id="camel" xmlns="http://camel.apache.org/schema/spring/camel-spring.xsd">
        
<camelcontext id="camel">
  <route>
      <from uri="cxf:bean:reportIncident">
        <convertbodyto type="org.apache.camel.example.reportincident.InputReportIncident">

        <setheader headername="CamelFileName">
          <method bean="filenameGenerator" method="generateFilename">
        </method>

        <to uri="velocity:etc/MailBody.vm">
        <to uri="file://target/subfolder">
          <transform>
            <method bean="myBean" method="getOK">
            </method>
          </transform>
{% endhighlight %}

To play with the example, follow this
    [example of Camel CXF](https://svn.apache.org/repos/asf/camel/trunk/examples/camel-example-reportincident-ws-security-osgi/) and enjoy it !