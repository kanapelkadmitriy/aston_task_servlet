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

<h2>Сохраненный товар: </h2>

<div>Название: <c:out value="${requestScope.product.name}"/> </div>

</body>
</html>
