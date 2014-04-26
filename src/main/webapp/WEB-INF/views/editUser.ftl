<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>
<#import "templates/formMacros.ftl" as f>

<@c.header />

	<@c.rowMdSix>
		<@spring.bind "command"/>
		<@f.form 'User ${command.login}'>
			<@spring.formHiddenInput 'command.isNew'/>

			<@f.formField>
				<@spring.formInput "command.login" 'placeholder="Username" class="form-control input-md" required=""'/>
				<@spring.showErrors "<br>" "help-block"/>
			</@f.formField>

			<@f.formField>
				<@spring.formPasswordInput "command.password" 'placeholder="Password" class="form-control input-md"'/>
				<@spring.showErrors "<br>" "help-block"/>
			</@f.formField>

			<@f.formField>
				<div class="checkbox">
					<label>
						<@c.formCheckbox "command.isAdmin" />
						Admin
					</label>
					<@spring.showErrors "<br>" "help-block"/>
				</div>
			</@f.formField>

			<@f.formField>
				<label>Available indexes:</label><br>
				<@spring.formCheckboxes "command.indexes" allIndexes "<br>" />
				<@spring.showErrors "<br>" "help-block"/>
			</@f.formField>

			<@f.formField "col-md-8">
				<button id="updateUser" name="updateUser" class="btn btn-primary" formaction="<@spring.url '/user/update/${userId}'/>" formmethod="post">Update</button>
				<button id="deleteUser" name="deleteUser" class="btn btn-primary" formaction="<@spring.url '/user/delete/${userId}'/>" formmethod="post">Delete</button>
			</@f.formField>
		</@f.form>
	</@c.rowMdSix>

<@c.footer>
<script>
   	$('.help-block').closest('.form-group').addClass('has-error');
</script>
</@c.footer>