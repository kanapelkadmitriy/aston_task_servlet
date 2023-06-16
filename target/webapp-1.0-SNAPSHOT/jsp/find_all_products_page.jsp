<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Категории</title>
    <style>
        <%@include file="/css/style.css" %>
    </style>

</head>
<body>

<h2>Товары: </h2>

<c:forEach var="products" items="${requestScope.products}">
    <ul>
        Название: <c:out value="${products.name}"/> <br>
        Цена: <c:out value="${products.cost}"/> <br>
        Количество: <c:out value="${products.quantity}"/> <br>
    </ul>
    <hr/>

</c:forEach>

</body>
</html>