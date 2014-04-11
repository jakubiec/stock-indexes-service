<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.header>
</@c.header>

<div class="container" >
	<div class="page-header">
		<h1>Stock Indexes Client</h1>
	</div>
	
	<h3>Welcome</h3>
	
	<button id="usersButton" type="button" class="btn btn-default">Users</button>
	<button id="jmsButton" type="button" class="btn btn-default">JMS</button>
	<button id="logoutButton" type="button" class="btn btn-default">Log out</button>
</div>

<@c.footer>
	<script type="text/javascript">
		$('#usersButton').click(function () {
			location.href = "<@spring.url '/users'/>";
		});
		$('#jmsButton').click(function () {
			location.href = "<@spring.url '/jmsExample'/>"
		});
		$('#logoutButton').click(function () {
			location.href = "<@spring.url '/j_spring_security_logout'/>"
		});
	</script>
</@c.footer>
