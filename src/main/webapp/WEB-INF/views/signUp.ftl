<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>
<#import "templates/formMacros.ftl" as f>

<@c.plainHeader>
	<style>
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #eee;
			margin: 0 auto;
		}
	</style>
</@c.plainHeader>

	<@c.rowMdSix>
		<@spring.bind "command"/>
		<@f.form "Sign up">
			<@spring.formHiddenInput 'command.isNew'/>

			<@f.formField>
				<@spring.formInput "command.login" 'placeholder="Username" class="form-control input-md" required=""'/>
				<@spring.showErrors "<br>" "help-block"/>
			</@f.formField>

			<@f.formField>
				<@spring.formPasswordInput "command.password" 'placeholder="Password" class="form-control input-md" required=""'/>
				<@spring.showErrors "<br>" "help-block"/>
			</@f.formField>

			<@f.formField "col-md-8">
				<button id="signUp" name="signUp" class="btn btn-primary" formaction="<@spring.url '/user/create'/>" formmethod="post">Sign Up</button>
				<a href="<@spring.url '/login'/>" class="btn btn-info active" role="button">Back to Login</a>
			</@f.formField>
		</@f.form>
	</@c.rowMdSix>

	<#if userExists??>
		<@c.alert userExists />
	</#if>
<@c.footer>
<script>
   	$('.help-block').closest('.form-group').addClass('has-error');
</script>
</@c.footer>