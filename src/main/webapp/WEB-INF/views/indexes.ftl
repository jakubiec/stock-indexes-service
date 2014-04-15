<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.header>
	<style>
		#chooseIndex {
			margin-top: 5px;
		}
	</style>
</@c.header>

<div class="container" >
	<h1>Indexes</h1>
	
	<@spring.bind "indexCommand" />
	<form class="form-horizontal" >
	    <@spring.formSingleSelect "indexCommand.symbol", indexCommand.indexesMap, 'class="form-control"' />
	    <button id="chooseIndex" name="chooseIndex" class="btn btn-primary" formaction="<@spring.url '/indexes/values'/>" formmethod="post">Choose index</button>
	</form>
	
	<#if indexCommand.symbol??>
		<table class="table">
			<thead>
				<tr>
					<th>Date</th>
					<th>Value</th> 
				</tr>
			</thead>
			<tbody>
			<#list indexCommand.indexValues as indexValue>
				<tr>
					<td>${indexValue.valueDate}</td>
					<td>${indexValue.value}</td>
				</tr>
			</#list>
			</tbody>
		</table>
	</#if>
</div>

<@c.footer/>