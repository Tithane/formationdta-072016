<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../media/css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="../media/js/bootstrap.min.js"></script>
<title>Liste des Pizzas</title>
</head>
<body>

	<div class="container">
		<h2>Ma liste de Pizza</h2>
		<a href="<c:url value="edit"></c:url>" class="btn  btn-primary">Ajouter
			une Pizza</a>
		<table class="table table-bordered" style="text-align: center">
			<thead>
				<tr>
					<th>Code</th>
					<th>Nom</th>
					<th>Prix</th>
					<th>Catégorie</th>
					<th>Image</th>
					<th colspan="2" style="text-align: center">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty listePizzas}">
					<c:forEach var="pizza" items="${listePizzas}">
						<tr>
							<td>${pizza.code}</td>
							<td>${pizza.nom}</td>
							<td>${pizza.prix}</td>
							<td>${pizza.categorie}</td>
							<td><img height="100px" src="../media/image/${pizza.url}"
								alt="${pizza.nom}" /></td>
							<td><a
								href="<c:url value="edit?code=${pizza.code}"></c:url>"
								class="btn btn-warning">Modifier</a></td>
							<td><a
								href="<c:url value="delete?code=${pizza.code}"></c:url>"
								class="btn btn-danger">Supprimer</a></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty listePizzas }">
					<tr>
						<td colspan="7" style="text-align: center">Aucune Pizza</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<a href='<c:url value="/logout"></c:url>'>Se deconnecter</a>
	</div>
</body>
</html>