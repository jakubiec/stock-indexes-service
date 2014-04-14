<h1>Indexes</h1>


<form id="chooseIndex" name="chooseIndexForm" method="post" action="chooseIndex">
<div>
Indexes:
  
<select id="indexesList"  >
    <#list indexesList as indx>
        <option value="${indx.symbol}">${indx.symbol}</option>
    </#list>
</select>
</div>
<input type="submit" value="Choose index">
</form>