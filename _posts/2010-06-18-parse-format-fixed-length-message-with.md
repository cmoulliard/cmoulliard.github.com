---
layout: post
title: "Parse / Format fixed length message with camel-bindy"
date: 2010-06-18
comments: true
categories:
- fixed length
- Camel
- camel-bindy
- Apache
tags: camel bindy dataformat
share: true
---

Camel-bindy which is a data formatting tool to parse / format non XML message will be enriched in the next camel release 2.4. This new version will allow to work with Fixed Length message.<br/><br/>A Fixed Length message as its name mention it contains data positioned at a fixed position which is by far different from Comma Separate Value format where we use a separator.

So, you will be able able to model your message like that :

{% highlight java %}
@FixedLengthRecord(length = 60, paddingChar = ' ')
public static class Order {

    @DataField(pos = 1, length = 2) private int orderNr;
    @DataField(pos = 3, length = 2) private String clientNr;
    @DataField(pos = 5, length = 9) private String firstName;
    @DataField(pos = 14, length = 5, align = "L") private String lastName;
    @DataField(pos = 19, length = 4) private String instrumentCode;
    @DataField(pos = 23, length = 10) private String instrumentNumber;
    @DataField(pos = 33, length = 3) private String orderType;
    @DataField(pos = 36, length = 5) private String instrumentType;
    @DataField(pos = 41, precision = 2, length = 7) private BigDecimal amount;
    @DataField(pos = 48, length = 3) private String currency;
    @DataField(pos = 51, length = 10, pattern = "dd-MM-yyyy") private Date orderDate;
{% endhighlight %}

to parse/format the following text :

{% highlight text %}
"10A9 PaulineM ISINXD12345678BUYShare2500.45USD01-08-2009"
{% endhighlight %}

The tool allows to add 'padding' characters and to align "LEFT" or " RIGHT" the string in the field.

More info are available on the wiki web page of camel-bindy : http://camel.apache.org/bindy.html