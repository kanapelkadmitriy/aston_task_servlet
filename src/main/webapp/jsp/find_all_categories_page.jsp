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

<h2>Категории: </h2>

<c:forEach var="categories" items="${requestScope.categories}">
    <ul>
        <c:out value="${categories.name}"/> <br>
    </ul>
    <hr/>

</c:forEach>

</body>
</html>