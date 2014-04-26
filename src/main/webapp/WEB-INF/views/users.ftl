<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.header />

	<#if (page.getContent()?size > 0)>
		<@c.paginationBar page '/user/users' beginIndex currentIndex endIndex/>

		<table class="table table-hover table-bordered" id="tableUsers">
	        <thead>
	            <tr>
	                <th>Id</th>
	                <th>Login</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<#list page.getContent() as user>
	            <tr>
	            	<td>
	            		<a href="<@spring.url '/user/edit/${user.id}'/>">${user.id}</a>
	            	</td>
	            	<td>
	            		<a href="<@spring.url '/user/edit/${user.id}'/>">${user.login}</a>
	            	</td>
	            </tr>
	            </#list>
	        </tbody>
	    </table>

		<@c.paginationBar page '/user/users' beginIndex currentIndex endIndex/>
	<#else>
		<@c.info "No users found!" "Currently there are no users in system." />
	</#if>

<@c.footer>
	<script type="text/JavaScript" src="<@spring.url '/resources/scripts/jquery.tablesorter.min.js'/>"></script>
	<script>
		$(document).ready(function(){
			$(function(){
				$("#tableUsers").tablesorter();
			});
		});
	</script>
</@c.footer>
