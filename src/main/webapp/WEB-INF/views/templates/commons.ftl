<#import "spring.ftl" as spring>

<#macro plainHeader container=true>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Stock Indexes Client</title>
	
	<link rel="shortcut icon" href="<@spring.url '/resources/images/favicon.ico'/>">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/styles/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/styles/datepicker.css'/>">
	
	<#nested>

</head>
<body>
	<#if container>
		<div class="container">
	</#if>
</#macro>

<#macro header>
	<@plainHeader false>
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
			<a href="<@spring.url '/index'/>" class="navbar-brand">Stock Indexes Client</a>
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

	<div class="container">
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

<#macro paginationBar page url beginIndex currentIndex endIndex>
	<ul class="pagination">
		<#if currentIndex == 1>
			<li class="disabled"><a href="#">&laquo;</a></li>
			<li class="disabled"><a href="#">&lt;</a></li>
		<#else>
			<li><a href="<@spring.url '${url}/1'/>">&laquo;</a></li>
			<li><a href="<@spring.url '${url}/${currentIndex-1}'/>">&lt;</a></li>
		</#if>
		<#list beginIndex..endIndex as i>
			<#if currentIndex == i>
				<li class="active"><a href="<@spring.url '${url}/${i}'/>">${i}</a></li>
			<#else>
				<li><a href="<@spring.url '${url}/${i}'/>">${i}</a></li>
			</#if>
		</#list>
		<#if currentIndex == page.totalPages>
			<li class="disabled"><a href="#">&gt;</a></li>
			<li class="disabled"><a href="#">&raquo;</a></li>
		<#else>
			<li><a href="<@spring.url '${url}/${currentIndex+1}'/>">&gt;</a></li>
			<li><a href="<@spring.url '${url}/${page.totalPages}'/>">&raquo;</a></li>
		</#if>
	</ul>
</#macro>

<#macro footer>
	</div>
	<script type="text/JavaScript" src="<@spring.url '/resources/scripts/jquery-1.11.0.min.js'/>"></script>
	<script type="text/JavaScript" src="<@spring.url '/resources/scripts/bootstrap.min.js'/>"></script>
	<script type="text/JavaScript" src="<@spring.url '/resources/scripts/bootstrap-datepicker.js'/>"></script>
	<#nested>
</body>
</html>
</#macro>

<#macro formCheckbox path attributes="">
    <@spring.bind path />
    <input type="hidden" name="_${spring.status.expression}" value="false"/>
    <input type="checkbox" id="${spring.status.expression}" name="${spring.status.expression}"
           <#if spring.status.value?? && spring.status.value?string=="true">checked="true"</#if>
    ${attributes}
    <@spring.closeTag/>
</#macro>

<#macro rowMdSix>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<#nested>
		</div>
		<div class="col-md-3"></div>
	</div>
</#macro>

<#macro alert message alertClass="alert alert-danger">
	<@rowMdSix>
		<div class="${alertClass}">
			${message}
		</div>
	</@rowMdSix>
</#macro>

<#macro info header message>
	<div class="page-header">
		<h1>${header}</h1>
	</div>

	<h3>${message}</h3>
</#macro>