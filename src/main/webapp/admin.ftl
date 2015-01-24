<html>
<head>
    <title>Administration</title>
    <#include "includes/common.ftl">
</head>
<body>
    <#include "includes/header.ftl"/>
    <h1>Customers</h1>
    <ul>
	<#list users as user>
	<li>${user.name}</li>
	</#list>
	</ul>
    <#include "includes/footer.ftl"/>
</body>
</html>