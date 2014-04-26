<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.header />

	<div class="container" >
		<@spring.bind "command"/>
		<form class="form-horizontal" >
			<fieldset>
				<@spring.formHiddenInput 'command.isNew'/>

				<legend>User ${command.login}</legend>
				
				<div class="form-group">
					<div class="col-md-6">
						<@spring.formInput "command.login" 'placeholder="Username" class="form-control input-md" required=""'/>
						<@spring.showErrors "<br>" "help-block"/>
					</div>
				</div>
	
				<div class="form-group">
					<div class="col-md-6">
						<@spring.formPasswordInput "command.password" 'placeholder="Password" class="form-control input-md"'/>
						<@spring.showErrors "<br>" "help-block"/>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-6">
						<div class="checkbox">
							<label>
								<@c.formCheckbox "command.isAdmin" />
								Admin
							</label>
							<@spring.showErrors "<br>" "help-block"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-6">
						<label>Available indexes:</label><br>
						<@spring.formCheckboxes "command.indexes" allIndexes "<br>" />
						<@spring.showErrors "<br>" "help-block"/>
					</div>
				</div>
				
				<span>
					<button id="updateUser" name="updateUser" class="btn btn-primary" formaction="<@spring.url '/user/update/${userId}'/>" formmethod="post">Update</button>
					<button id="deleteUser" name="deleteUser" class="btn btn-primary" formaction="<@spring.url '/user/delete/${userId}'/>" formmethod="post">Delete</button>
				</span>
	
			</fieldset>
		</form>
	</div>

<@c.footer>
<script>
   	$('.help-block').closest('.form-group').addClass('has-error');
</script>
</@c.footer>