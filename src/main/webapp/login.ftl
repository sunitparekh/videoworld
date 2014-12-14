<html>
<head>
    <title>Login</title>
    <#include "includes/common.ftl"/>
</head>
<body>
    <h1>Login</h1>
	<form id="login" method="post">
	    <p>Select user:</p>
		<select class="customer" name="username">
	    	<#list customers as customer>
	    	<option value="${customer.name}">${customer.name}</option>
	    	</#list>
		</select>
    	<input type="submit" value="login" />
	</form>
    <#include "includes/footer.ftl"/>
</body>
</html>