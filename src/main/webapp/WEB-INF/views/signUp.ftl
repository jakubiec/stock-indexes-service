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
		<@spring.bind "userCommand"/>
		<form class="form-horizontal" >
			<fieldset>

				<legend>Sign up</legend>
				
				<#if userExists??>	
					<div class="form-group has-error">
						<div class="col-md-6">
							<span class="help-block">${userExists}</span>
						</div>
					</div>
				</#if>
				
				<div class="form-group">
					<div class="col-md-6">
						<@spring.formInput "userCommand.login" 'placeholder="Username" class="form-control input-md" required=""'/>
						<@spring.showErrors "<br>" "help-block"/>
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-md-6">
						<@spring.formPasswordInput "userCommand.password" 'placeholder="Password" class="form-control input-md" required=""'/>
						<@spring.showErrors "<br>" "help-block"/>
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-md-8">
						<button id="signUp" name="signUp" class="btn btn-primary" formaction="<@spring.url '/user/createUser'/>" formmethod="post">Sign Up</button>
						<button id="login" name="login" class="btn btn-inverse"  onclick="window.location.href='<@spring.url '/login'/>'">Login page</button>
					</div>
				</div>

			</fieldset>
		</form>
	</div>

<@c.footer>
<script>
   	$('.help-block').closest('.form-group').addClass('has-error');
</script>
</@c.footer>