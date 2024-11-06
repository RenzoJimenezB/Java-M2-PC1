<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Categories list</title>
</head>
<body>
<h1>Lista de Categorías</h1>
<ul>
    <jsp:useBean id="categories" scope="request" type="java.util.List"/>
    <c:forEach var="category" items="${categories}" varStatus="i">
        <p>${i.count}</p>
        <li>${category}</li>
    </c:forEach>
</ul>

</body>
</html>
