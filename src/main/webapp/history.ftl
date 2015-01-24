<html>
<head>
    <#include "includes/common.ftl"/>
    <title>Transaction History</title>
</head>
<body>
    <#include "includes/header.ftl"/>

    <h1>Transaction History</h1>
    <ul>
    <#list transactions as transaction>
    	<li>Transaction on ${transaction.dateTime}<br />
    	Movies Rented:
		<#list transaction.rentals as rental>
			${rental.movie.title}<#if rental_has_next>, </#if>
		</#list>
		</li>
	</#list>
	</ul>
    <#include "includes/footer.ftl"/>

</body>
</html>