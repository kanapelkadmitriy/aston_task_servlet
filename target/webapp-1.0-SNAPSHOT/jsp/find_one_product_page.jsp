<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Товары</title>
    <style>
        <%@include file="/css/style.css" %>
    </style>

</head>
<body>

<h2>Товар: </h2>
Название: <c:out value="${requestScope.product.name}"/><hr/>
Цена: <c:out value="${requestScope.product.cost}"/><hr/>
Количество: <c:out value="${requestScope.product.quantity}"/><hr/>
<hr/>
Характеристики:
<c:forEach var="characteristics" items="${requestScope.product.characteristics}">
    <ul>
        <c:out value="${characteristics.name}"/> <br>
    </ul>
    <hr/>
</c:forEach>

</body>
</html>
