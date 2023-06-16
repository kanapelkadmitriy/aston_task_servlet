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

<h2>Категория: </h2>
Название: <c:out value="${requestScope.category.name}"/>
<hr/>
Товары:
<c:forEach var="products" items="${requestScope.category.products}">
    <ul>
        Название: <c:out value="${products.name}"/> <br>
        Цена: <c:out value="${products.cost}"/> <br>
        Количество: <c:out value="${products.quantity}"/> <br>
    </ul>
    <hr/>
</c:forEach>

</body>
</html>
