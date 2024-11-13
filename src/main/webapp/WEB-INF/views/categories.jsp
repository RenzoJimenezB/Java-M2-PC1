<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Categories list</title>
    <!-- Link to Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h1 class="text-center mb-4">Lista de Categorías</h1>

    <!-- Table with header row -->
    <table class="table table-bordered">
        <!-- Table Header Row -->
        <thead>
        <tr>
            <th scope="col" class="font-weight-bold text-center">Categorías</th>
            <th scope="col" class="font-weight-bold text-center">Nombre de la categoría</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}" varStatus="i">
            <tr>
                <td class="text-center">Categoría ${i.count}</td>
                <td class="text-center">${category}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>