<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../media/css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="../media/js/bootstrap.min.js"></script>
<title>Liste des Pizzas</title>
</head>
<body>
	
	<div class="container">
	<h2>Ma liste de Pizza</h2>
	<a href="<%=request.getContextPath()%>/pizzas/edit" class="btn  btn-primary">Ajouter une
		Pizza</a>
	<table class="table table-bordered" style="text-align: center">
		<thead >
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
			<%
				List<Pizza> maListe = (List<Pizza>) request.getAttribute("liste-pizzas");
				if (maListe.isEmpty()) {
			%>
			<tr>
				<td colspan="5" style="text-align: center">Aucune Pizza</td>
			</tr>
			<%
				} else {
					for (Pizza pizza : maListe) {
			%>
			<tr>
				<td><%=pizza.getCode()%></td>
				<td><%=pizza.getNom()%></td>
				<td><%=pizza.getPrix()%></td>
				<td><%=pizza.getCategorie()%></td>
				<td><img height="100px" src="../media/image/<%=pizza.getUrl()%>"
					alt="<%=pizza.getNom()%>" /></td>
				<td><a
					href="<%=request.getContextPath()%>/pizzas/edit?code=<%=pizza.getCode()%> " class="btn btn-warning">Modifier</a></td>
				<td><a
					href="<%=request.getContextPath()%>/pizzas/delete?code=<%=pizza.getCode()%>" class="btn btn-danger">Supprimer</a></td>
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>
	</div>
</body>
</html>