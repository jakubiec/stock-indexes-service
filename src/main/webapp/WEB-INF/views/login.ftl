<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.header>
	<style>
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #eee;
			max-width: 330px;
			margin: 0 auto;
		}
	</style>
</@c.header>

	<div class="container" >
		<form class="form-horizontal" method="post" action="<@spring.url '/j_spring_security_check'/>">
			<fieldset>

				<legend>Please, log in</legend>
	
				<div class="form-group">
					<div class="col-md-6">
						<input id="username" name="j_username" type="text" placeholder="Username" class="form-control input-md" required="">
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-md-6">
						<input id="password" name="j_password" type="password" placeholder="Password" class="form-control input-md" required="Provide password">
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-md-8">
						<button id="logIn" name="logIn" class="btn btn-primary">Log In</button>
						<button id="signUp" name="signUp" class="btn btn-inverse">Sign Up</button>
					</div>
				</div>

			</fieldset>
		</form>
	</div>

<@c.footer>
	<script type="text/JavaScript" src="<@spring.url '/resources/scripts/login.js'/>"></script>
</@c.footer>