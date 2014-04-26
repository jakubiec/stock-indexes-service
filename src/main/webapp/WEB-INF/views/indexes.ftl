<#import "templates/spring.ftl" as spring>
<#import "templates/commons.ftl" as c>

<@c.header>
	<style>
		#chooseIndex {
			margin-top: 5px;
		}
	</style>
</@c.header>

	<h1>Indexes</h1>
	
	<#if (indexCommand.indexesMap?size > 0)>
		<@spring.bind "indexCommand" />
		<form class="form-horizontal" >
			<div class="form-group">
			    <@spring.formSingleSelect "indexCommand.symbol", indexCommand.indexesMap, 'class="form-control"' />
			    <button id="chooseIndex" name="chooseIndex" class="btn btn-primary" formaction="<@spring.url '/indexes/values'/>" formmethod="post">Choose index</button>
			</div>
		<#if indexCommand.symbol??>
			<div class="form-group" >
				<div class="panel panel-default">
					<div class="panel-heading">

						    <div class="input-daterange input-group" id="datepicker">
						    		<span class="input-group-addon">From</span>
		    						<@spring.formInput "indexCommand.startDate" , 'placeholder="Start" class="form-control" ' />
							    	<span class="input-group-addon">to</span>
							    	<@spring.formInput "indexCommand.endDate" , 'placeholder="End" class="form-control" ' />
							    	<span class="input-group-btn">
							    		<button id="filter" name="filter" class="btn btn-primary" formaction="<@spring.url '/indexes/values'/>" formmethod="post">show</button>
									</span>
							</div>

					</div>
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
				</div>
			</div>
		</#if>
		</form>
	<#else>
		<@c.info "No indexes found!" "Currently there are no indexes you can browse." />
	</#if>

<@c.footer>
	
	<script>
		    $('.input-daterange').datepicker({
		    	format: "yyyy-mm-dd",
		    	todayBtn: "linked",
		    	autoclose: true,
		    	forceParse: true
		    });
	</script>
</@c.footer>