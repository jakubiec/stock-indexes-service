<#import "spring.ftl" as spring />

<#macro form legend>
	<form class="form-horizontal" >
		<fieldset>
			<legend>${legend}</legend>

			<#nested>

		</fieldset>
	</form>
</#macro>

<#macro formField fieldClass="col-md-12">
<div class="form-group">
	<div class="${fieldClass}">
		<#nested>
	</div>
</div>
</#macro>