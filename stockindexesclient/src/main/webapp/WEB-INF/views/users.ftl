<#import "templates/spring.ftl" as spring>
<#import "templates/formMacros.ftl" as form>

<table>
	<thead>
		<th>id</th>
		<th>login</th>
		<th>isAdmin</th>
	</thead>
	<tbody>
		<#list users as user>
			<tr>
				<td>${user.id}</td>
				<td>${user.login}</td>
				<td>${user.isAdmin}</td>
			</tr>
		</#list>
	</tbody>
</table>

<br/>
<br/>
<h4>Add user</h4>
<form id="createUser" name="createUserForm" method="post" action="newUser">
	<table>
		<tr>
			<td>
				<strong>
					<label for="login">Login</label>
				</strong>
				<input type="text" name="login" id="login" />
			</td>
		</tr>
		<tr>
			<td>
				<strong>
					<label for="pass">Password</label>
				</strong>
				<input type="text" name="pass" id="pass"/>
			</td>
		</tr>
		<tr>
			<td>
				<strong>
					<label for="isAdmin">Admin</label>
				</strong>
				<input type="checkbox" id="isAdmin" name="isAdmin">
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Add User">
			</td>
		</tr>
	</table>
</form>