<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.header/>
<h1>Indexes</h1>


<@spring.bind "indexCommand" />

<form class="form-horizontal" >
    <@spring.formSingleSelect "indexCommand.symbol", indexCommand.indexesMap, 'class="form-control"' />
    <button id="chooseIndex" name="chooseIndex" class="btn btn-primary" formaction="<@spring.url '/indexes/values'/>" formmethod="post">Choose index</button>
</form>

<#if indexCommand.symbol??>

	<table>
		<tr> <th>Date</th> <th>Value</th> </tr>
		<#list indexCommand.indexValues as indexValue>
			<tr> <td> ${indexValue.valueDate} </td> <td>${indexValue.value}</td> </tr>
		</#list>
	</table>

</#if>

<@c.footer/>