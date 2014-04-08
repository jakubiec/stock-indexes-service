<#import "spring.ftl" as spring>

<#macro header>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Basic Bootstrap Template</title>
	
	<link rel="shortcut icon" href="<@spring.url 'images/favicon.ico'/>">
	<link rel="stylesheet" type="text/css" href="<@spring.url 'css/bootstrap.min.css'/>">
	
	<#nested>
	
</head>
<body>
</#macro>

<#macro footer>
	<script type="text/JavaScript" src="<@spring.url 'scripts/jquery-1.11.0.min.js'/>"></script>
	<script type="text/JavaScript" src="<@spring.url 'scripts/bootstrap.min.js'/>"></script>
	
	<#nested>
</body>
</html>
</#macro>