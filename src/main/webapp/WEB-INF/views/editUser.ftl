<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.header />

	<div class="container" >
		<@spring.bind "command"/>
		<form class="form-horizontal" >
			<fieldset>

				<legend>User ${command.login}</legend>
				
				<div class="form-group">
					<div class="col-md-6">
						<@spring.formInput "command.login" 'placeholder="Username" class="form-control input-md" required=""'/>
						<@spring.showErrors "<br>" "help-block"/>
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-md-6">
						<@spring.formPasswordInput "command.password" 'placeholder="Password" class="form-control input-md" required=""'/>
						<@spring.showErrors "<br>" "help-block"/>
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