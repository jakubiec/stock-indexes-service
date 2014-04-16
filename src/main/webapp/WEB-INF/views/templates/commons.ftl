<#import "spring.ftl" as spring>

<#macro plainHeader>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Stock Indexes Client</title>
	
	<link rel="shortcut icon" href="<@spring.url '/resources/images/favicon.ico'/>">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/styles/bootstrap.min.css'/>">
	
	<#nested>

</head>
<body>
</#macro>

<#macro header>
	<@plainHeader>
		<style>
			.username {
				padding: 15px 15px;
				display: block;
				position: relative;
				color: #777;
				line-height: 20px;
			}
		</style>
		<#nested>
	</@plainHeader>

<nav role="navigation" class="navbar navbar-default navbar-static-top">
	<div class="navbar-header">
		<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a href="#" class="navbar-brand">Stock Indexes Client</a>
	</div>

	<div id="navbarCollapse" class="collapse navbar-collapse navbar-responsive-collapse">
		<ul class="nav navbar-nav">
			<#list menuItems as menuItem>
				<@createMenu menuItem/>
			</#list>
        </ul>
        <ul style="margin-right: 0px;" class="nav navbar-nav navbar-right">
			<li><span class="username">${username}</span></li>
			<li><a href="<@spring.url '/j_spring_security_logout'/>">Log out</a></li>
        </ul>
    </div>
</nav>
</#macro>

<#macro createMenu item>
<#if item.isAccessible()?string('yes', 'no') == 'yes'>
	<li <#if item.itemsSublist??>class="dropdown"</#if>>
		<a href="<@spring.url '${item.url}'/>" <#if item.itemsSublist??>data-toggle="dropdown" class="dropdown-toggle"</#if>>
			${item.label}
			<#if item.itemsSublist??><b class="caret"></b></#if>
		</a>
		<#if item.itemsSublist??>
			<ul class="dropdown-menu">
				<#list item.itemsSublist as subitem>
					<@createMenu subitem/>
				</#list>
			</ul>
		</#if>
	</li>
</#if>
</#macro>

<#macro footer>
	<script type="text/JavaScript" src="<@spring.url '/resources/scripts/jquery-1.11.0.min.js'/>"></script>
	<script type="text/JavaScript" src="<@spring.url '/resources/scripts/bootstrap.min.js'/>"></script>
	
	<#nested>
</body>
</html>
</#macro>