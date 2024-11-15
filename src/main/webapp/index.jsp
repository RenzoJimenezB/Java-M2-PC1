<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center">Formulario</h2>
    <h3 class="text-center">Solicitud de categoría</h3>
    <form class="mt-3" method="post" action="<c:url value="/categories"/>">
        <div class="mb-3">
            <label for="textInput" class="form-label">¿Desea obtener un listado de todas las categorías?</label>
            <input name="question" type="text" class="form-control" id="textInput" placeholder="Sí / No">
        </div>
        <button type="submit" class="btn btn-primary">Enviar</button>
    </form>
</div>

<!-- Bootstrap JS (Optional) -->
<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>-->
</body>
</html>