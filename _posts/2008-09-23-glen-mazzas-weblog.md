---
layout: post
title: "Charles Moulliard's Weblog"
date: 2008-09-23
tags: apache cxf wsdl Fuse
comments: true
share: true
---

<div class='post'>
    <div style="border: 1px solid black; margin: 0px; padding: 2px; left: -100px; top: -100px; visibility: hidden; display: none; width: auto; height: auto; position: absolute; background-color: rgb(168, 236, 255); -moz-border-radius-topleft: 5px; -moz-border-radius-topright: 5px; -moz-border-radius-bottomright: 5px; -moz-border-radius-bottomleft: 5px; font-size: 12px; color: rgb(0, 0, 0); text-align: left; z-index: 1410065406;" id="gmbabelFish">
        <div style="border-bottom: 1px dotted black; padding-bottom: 2px; padding-top: 2px;">
            <span title="Close BabelFish" class="gmBabelMousishToolBar" style="cursor: pointer;"></span><span title="Language configuration" class="gmBabelMousishToolBar" style="cursor: pointer;"></span><span style="cursor: pointer;" class="gmBabelMousishToolBar"></span><span style="cursor: pointer;" class="gmBabelMousishToolBar"></span><span style="cursor: copy;" class="gmBabelMousishToolBar" title="Copy result to clipboard"></span><img style="border: medium none ; margin: 0px; cursor: pointer;" title="click to translate" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAOCAYAAAA8E3wEAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH1QUUDyoqJjAqRwAAAN1JREFUOMu1lMkVwyAMBYe0JGpCNUFNVk3k4AUwxPGS+ILxkzX8jyTH/Sfu9nrmJ3cXlnMASyWRPwd2d5XlHCBZn1BthcbRAdxTZQDI8k3mQzg11rhF+QZ9jdNOcQib6GFQYJYgCFucSRf6GsLU6wEY5yubTFqF2yq1vRwr3INXdQUWG+je1pELX4ED1wDyRAR0WfuAA9gloITyvsFMIMgYInYRqF6rO9Sqz9qkO5ilyo0o3YBwJ+6vrdQonxWUQllhXeHcb/wabMPkP2n81ocAIoLZrMqn/4y2RwP8DcQ+d6rT9ATiAAAAAElFTkSuQmCC" align="middle"/>
        </div>
        <span></span></div>
    <div style="border: 1px solid black; margin: 0px; padding: 2px; left: -100px; top: -100px; visibility: hidden; display: none; width: auto; height: auto; position: absolute; background-color: rgb(168, 236, 255); -moz-border-radius-topleft: 5px; -moz-border-radius-topright: 5px; -moz-border-radius-bottomright: 5px; -moz-border-radius-bottomleft: 5px; color: rgb(0, 0, 0); text-align: left; z-index: 1410065406;font-size:12px;" id="gmbabelFish">
        <div style="border-bottom: 1px dotted black; padding-bottom: 2px; padding-top: 2px;">
            <span title="Close BabelFish" class="gmBabelMousishToolBar" style="cursor: pointer;"></span><span title="Language configuration" class="gmBabelMousishToolBar" style="cursor: pointer;"></span><span style="cursor: pointer;" class="gmBabelMousishToolBar"></span><span style="cursor: pointer;" class="gmBabelMousishToolBar"></span><span style="cursor: copy;" class="gmBabelMousishToolBar" title="Copy result to clipboard"></span><img style="border: medium none ; margin: 0px; cursor: pointer;" title="click to translate" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAOCAYAAAA8E3wEAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH1QUUDyoqJjAqRwAAAN1JREFUOMu1lMkVwyAMBYe0JGpCNUFNVk3k4AUwxPGS+ILxkzX8jyTH/Sfu9nrmJ3cXlnMASyWRPwd2d5XlHCBZn1BthcbRAdxTZQDI8k3mQzg11rhF+QZ9jdNOcQib6GFQYJYgCFucSRf6GsLU6wEY5yubTFqF2yq1vRwr3INXdQUWG+je1pELX4ED1wDyRAR0WfuAA9gloITyvsFMIMgYInYRqF6rO9Sqz9qkO5ilyo0o3YBwJ+6vrdQonxWUQllhXeHcb/wabMPkP2n81ocAIoLZrMqn/4y2RwP8DcQ+d6rT9ATiAAAAAElFTkSuQmCC" align="middle"/>
        </div>
        <span></span></div>
    <div style="border: 1px solid black; margin: 0px; padding: 2px; left: -100px; top: -100px; visibility: hidden; display: none; width: auto; height: auto; position: absolute; background-color: rgb(168, 236, 255); -moz-border-radius-topleft: 5px; -moz-border-radius-topright: 5px; -moz-border-radius-bottomright: 5px; -moz-border-radius-bottomleft: 5px; color: rgb(0, 0, 0); text-align: left; z-index: 1410065406;font-size:12px;" id="gmbabelFish">
        <div style="border-bottom: 1px dotted black; padding-bottom: 2px; padding-top: 2px;">
            <span title="Close BabelFish" class="gmBabelMousishToolBar" style="cursor: pointer;"></span><span title="Language configuration" class="gmBabelMousishToolBar" style="cursor: pointer;"></span><span style="cursor: pointer;" class="gmBabelMousishToolBar"></span><span style="cursor: pointer;" class="gmBabelMousishToolBar"></span><span style="cursor: copy;" class="gmBabelMousishToolBar" title="Copy result to clipboard"></span><img style="border: medium none ; margin: 0px; cursor: pointer;" title="click to translate" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAOCAYAAAA8E3wEAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH1QUUDyoqJjAqRwAAAN1JREFUOMu1lMkVwyAMBYe0JGpCNUFNVk3k4AUwxPGS+ILxkzX8jyTH/Sfu9nrmJ3cXlnMASyWRPwd2d5XlHCBZn1BthcbRAdxTZQDI8k3mQzg11rhF+QZ9jdNOcQib6GFQYJYgCFucSRf6GsLU6wEY5yubTFqF2yq1vRwr3INXdQUWG+je1pELX4ED1wDyRAR0WfuAA9gloITyvsFMIMgYInYRqF6rO9Sqz9qkO5ilyo0o3YBwJ+6vrdQonxWUQllhXeHcb/wabMPkP2n81ocAIoLZrMqn/4y2RwP8DcQ+d6rT9ATiAAAAAElFTkSuQmCC" align="middle"/>
        </div>
        <span></span></div>
    <div>
        <span class="Apple-style-span" style=";font-family:verdana;font-size:13;">Web service related links of interest this week:</span><br/>
    </div>
    <div><br/></div>
    <ul>
        <li>
            <a href="http://www.jroller.com/gmazza/date/20080417"><span class="Apple-style-span" style="color: rgb(0, 0, 0);"></span></a><a href="http://www.jroller.com/gmazza/date/20080417">Glen Mazza's Weblog</a>: "Creating a WSDL-first web service with Apache CXF or GlassFish Metro"<br/>
        </li>
        <li>
            <a href="http://java.sys-con.com/node/345638">Enterprise Mashup Services </a>:This is the second part of the "Enterprise Mashup services" article explaining how to use JSF and AJAX instead of javascript in JSP page<br/>
        </li>
        <li>
            <a href="http://soa.sys-con.com/node/325192">SOA - Enterprise Mashup Services: </a>This interesting article shows How we can design SOA solutions in enterprise world by leveraging J2EE platform and using Java to JavaScript API, Google Map, DWR &amp; Spring<br/>
        </li>
        <li><span class="Apple-style-span" style="font-family:'Times New Roman';"><div face="Georgia,serif" size="3" style="border-width: 0px; margin: 0px; padding: 3px; width: auto; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal; font-size-adjust: none; font-stretch: normal; text-align: left;">
            <a href="http://blog.sherifmansour.com/?p=187">Why Mashups = (REST + ‘Traditional SOA’) * Web 2.0 - Blog the web : </a><br/>A Mashup is a new service, that combines functionality or content from existing sources. These existing sources can be
            <span id="apture_prvw2" class="aptureLink"><span class="aptureLinkIcon" style="background-position: 100% -1049px;"></span><a href="http://en.wikipedia.org/wiki/Web%20service" class="aptureLink snap_noshots">Web Services</a></span> (through the use of
            <span id="apture_prvw3" class="aptureLink"><span class="aptureLinkIcon" style="background-position: 100% -1049px;"></span><a href="http://en.wikipedia.org/wiki/API" class="aptureLink snap_noshots">API</a></span>’s), RSS feeds or even just other Websites (by screen-scraping).
        </div></span></li>
    </ul>
    <span class="Apple-style-span" style="font-family:'Times New Roman';"><div face="Georgia,serif" size="3" style="border-width: 0px; margin: 0px; padding: 3px; width: auto; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal; font-size-adjust: none; font-stretch: normal; text-align: left;">
        <a href="http://soa.sys-con.com/node/325192"></a></div><div style="border-width: 0px; margin: 0px; padding: 3px; width: auto; font-family: Georgia,serif; font-style: normal; font-variant: normal; font-weight: normal; font-size: 100%; line-height: normal; font-size-adjust: none; font-stretch: normal; text-align: left;"></div></span><span class="Apple-style-span" style="font-family:'Times New Roman';"><div style="border-width: 0px; margin: 0px; padding: 3px; width: auto; font-family: Georgia,serif; font-style: normal; font-variant: normal; font-weight: normal; font-size: 100%; line-height: normal; font-size-adjust: none; font-stretch: normal; text-align: left;">
    <span class="Apple-style-span" style="font-family:'Times New Roman';"><div style="border-width: 0px; margin: 0px; padding: 3px; width: auto; font-family: Georgia,serif; font-style: normal; font-variant: normal; font-weight: normal; font-size: 100%; line-height: normal; font-size-adjust: none; font-stretch: normal; text-align: left;">
        <br/></div></span></div></span></div>
