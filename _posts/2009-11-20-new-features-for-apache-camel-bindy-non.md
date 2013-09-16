---
layout: post
title: "New features for Apache Camel Bindy (non XML binding framework)"
date: 2009-11-20
comments: true
tags: camel bindy dataformat
share: true
---

Today has been a very productive day. I have found the time to update the documentation about the Apache Camel Tutorial on
[OSGI](http://camel.apache.org/tutorial-osgi-camel-part2.html) and commit the last new features added to camel-bindy : the non XML binding framework
used to parse or generate CSV, FIX, ... messages

This new version of bindy includes important new features like :

- Required (@DataField) to check mandatory field
- Position (@DataField and @KeyValuePairField) when the CSV/FIX message to be generate contain fields which are placed at a different position then those used to parse it
- Section which allow to define the class containing by example the header, body or footer section

and

- OneToMany which allow to :

* Read a FIX message containing repetitive groups (= group of tags/keys)</li>
* Generate a CSV with repetitive data</li>

More info can be find <a href="http://camel.apache.org/bindy.html">here</a>.<br/><br/>

  
        <div style="border-bottom: 1px dotted black ! important; background: rgb(168, 236, 255) none repeat scroll 0% 0% ! important; -moz-background-clip: border ! important; -moz-background-origin: padding ! important; -moz-background-inline-policy: continuous ! important; font-family: arial ! important; font-size: 12px ! important; color: rgb(0, 0, 0) ! important; line-height: normal ! important; font-weight: normal ! important; vertical-align: middle ! important; padding-bottom: 2px ! important; padding-top: 2px ! important;">
            <span id="bfconfigButton" title="Language configuration" style="border: 1px dotted gray ! important; margin: 1px ! important; padding: 0px 2px ! important; background: rgb(168, 236, 255) none repeat scroll 0% 0% ! important; -moz-background-clip: border ! important; -moz-background-origin: padding ! important; -moz-background-inline-policy: continuous ! important; font-family: arial ! important; font-size: 12px ! important; color: rgb(0, 0, 0) ! important; line-height: normal ! important; font-weight: normal ! important; vertical-align: middle ! important; cursor: pointer ! important;">Lang</span><span id="bfdetectButton" title="Detect and set language" style="border: 1px dotted gray ! important; margin: 1px ! important; padding: 0px 2px ! important; background: rgb(168, 236, 255) none repeat scroll 0% 0% ! important; -moz-background-clip: border ! important; -moz-background-origin: padding ! important; -moz-background-inline-policy: continuous ! important; font-family: arial ! important; font-size: 12px ! important; color: rgb(0, 0, 0) ! important; line-height: normal ! important; font-weight: normal ! important; vertical-align: middle ! important; cursor: pointer;">Detect</span><span title="From English To French (switch direction)" id="bflangsSpan" style="border: 1px dotted gray ! important; margin: 1px ! important; padding: 0px 2px ! important; background: rgb(168, 236, 255) none repeat scroll 0% 0% ! important; -moz-background-clip: border ! important; -moz-background-origin: padding ! important; -moz-background-inline-policy: continuous ! important; font-family: arial ! important; font-size: 12px ! important; color: rgb(0, 0, 0) ! important; line-height: normal ! important; font-weight: normal ! important; vertical-align: middle ! important; cursor: pointer ! important;">en>fr </span><span title="Translation service: Yahoo (switch service)" id="bfsvcSpan" style="border: 1px dotted gray ! important; margin: 1px ! important; padding: 0px 2px ! important; background: rgb(168, 236, 255) none repeat scroll 0% 0% ! important; -moz-background-clip: border ! important; -moz-background-origin: padding ! important; -moz-background-inline-policy: continuous ! important; font-family: arial ! important; font-size: 12px ! important; color: rgb(0, 0, 0) ! important; line-height: normal ! important; font-weight: normal ! important; vertical-align: middle ! important; cursor: pointer ! important;">Yahoo</span><span title="Copy result to clipboard" id="bfclipboardSpan" style="border: 1px dotted gray ! important; margin: 1px ! important; padding: 0px 2px ! important; background: rgb(168, 236, 255) none repeat scroll 0% 0% ! important; -moz-background-clip: border ! important; -moz-background-origin: padding ! important; -moz-background-inline-policy: continuous ! important; font-family: arial ! important; font-size: 12px ! important; color: rgb(0, 0, 0) ! important; line-height: normal ! important; font-weight: normal ! important; vertical-align: middle ! important; cursor: copy ! important;">C</span><span title="" id="bferrorSpan" style="border: 1px dotted gray ! important; margin: 1px ! important; padding: 0px 2px ! important; background: rgb(168, 236, 255) none repeat scroll 0% 0% ! important; -moz-background-clip: border ! important; -moz-background-origin: padding ! important; -moz-background-inline-policy: continuous ! important; font-family: arial ! important; font-size: 12px ! important; color: rgb(0, 0, 0) ! important; line-height: normal ! important; font-weight: normal ! important; vertical-align: middle ! important; cursor: pointer ! important; display: none ! important; visibility: hidden ! important;"></span><img id="bffishImg" style="border: medium none  ! important; margin: 0px ! important; float: none ! important; vertical-align: top ! important; cursor: pointer ! important; display: inline ! important;" title="Click to translate" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAOCAYAAAA8E3wEAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH1QUUDyoqJjAqRwAAAN1JREFUOMu1lMkVwyAMBYe0JGpCNUFNVk3k4AUwxPGS+ILxkzX8jyTH/Sfu9nrmJ3cXlnMASyWRPwd2d5XlHCBZn1BthcbRAdxTZQDI8k3mQzg11rhF+QZ9jdNOcQib6GFQYJYgCFucSRf6GsLU6wEY5yubTFqF2yq1vRwr3INXdQUWG+je1pELX4ED1wDyRAR0WfuAA9gloITyvsFMIMgYInYRqF6rO9Sqz9qkO5ilyo0o3YBwJ+6vrdQonxWUQllhXeHcb/wabMPkP2n81ocAIoLZrMqn/4y2RwP8DcQ+d6rT9ATiAAAAAElFTkSuQmCC"/>
        </div>
        <div style="background: rgb(168, 236, 255) none repeat scroll 0% 0% ! important; -moz-background-clip: border ! important; -moz-background-origin: padding ! important; -moz-background-inline-policy: continuous ! important; font-family: arial ! important; font-size: 12px ! important; color: rgb(0, 0, 0) ! important; line-height: normal ! important; font-weight: normal ! important; vertical-align: middle ! important; width: auto;"> OSGI</div>
    </div>
</div>

