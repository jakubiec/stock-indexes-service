<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.head>
</@c.head>

<h1>Stock Indexes Client</h1>

<h3>Welcome</h3>

<h4>Test links:</h4>
<a href="<@spring.url '/jmsExample'/>">JMS</a><br/>
<a href="<@spring.url '/users'/>">Users</a>

<@c.footer>
</@c.footer>