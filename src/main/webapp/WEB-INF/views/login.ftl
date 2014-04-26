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
		<@f.form "Please log in">
			<@f.formField>
				<input id="username" name="j_username" type="text" placeholder="Username" class="form-control input-md" required="">
			</@f.formField>

			<@f.formField>
				<input id="password" name="j_password" type="password" placeholder="Password" class="form-control input-md" required="">
			</@f.formField>

			<@f.formField "col-md-8">
				<button id="logIn" name="logIn" class="btn btn-primary" formaction="<@spring.url '/j_spring_security_check'/>" formmethod="post">Log In</button>
				<a href="<@spring.url '/user/create/form'/>" class="btn btn-info active" role="button">Go to Sign Up</a>
			</@f.formField>
		</@f.form>
	</@c.rowMdSix>

	<#if loginError??>
		<@c.alert loginError />
	</#if>

<@c.footer/>