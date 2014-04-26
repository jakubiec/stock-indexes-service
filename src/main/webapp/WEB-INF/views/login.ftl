<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.plainHeader>
	<style>
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #eee;
			max-width: 330px;
			margin: 0 auto;
		}
	</style>
</@c.plainHeader>

	<div class="container" >
		<form class="form-horizontal" >
			<fieldset>

				<legend>Please log in</legend>
	
				<div class="form-group">
					<div class="col-md-6">
						<input id="username" name="j_username" type="text" placeholder="Username" class="form-control input-md" required="">
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-md-6">
						<input id="password" name="j_password" type="password" placeholder="Password" class="form-control input-md" required="">
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-md-8">
						<button id="logIn" name="logIn" class="btn btn-primary" formaction="<@spring.url '/j_spring_security_check'/>" formmethod="post">Log In</button>
						<button id="signUp" name="signUp" class="btn btn-inverse" onclick="window.location.href='<@spring.url '/user/create/form'/>'">Sign Up</button>
					</div>
				</div>

			</fieldset>
		</form>
	</div>

<@c.footer/>