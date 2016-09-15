<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="fr.pizzeria.model.CategoriePizza"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../media/css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="../media/js/bootstrap.min.js"></script>
<title>Pizza</title>
</head>
<body>
<div class="container">
	<%
		Pizza maPizza = (Pizza) request.getAttribute("maPizza");
	%>
	<%
		if (maPizza != null) {
	%>
	<form class="form-horizontal" method="Post"
		action="<%=request.getContextPath()%>/pizzas/edit?code=<%=maPizza.getCode()%>">
		<%
			} else {
		%>
		<form class="form-horizontal" method="Post"
			action="<%=request.getContextPath()%>/pizzas/edit">
			<%
				}
			%>
			<fieldset>
				<!-- Form Name -->
				<%if(maPizza!=null){
					%>
					<legend>Modification Pizza</legend>
					<%
					}else{
						%>
						<legend>Ajout Pizza</legend>
						<%
					
				} %>
				<%
					if (maPizza != null) {
				%>
				<div>
					<input name="id" type="hidden" value="<%=maPizza.getId()%>">
				</div>
				<%
					}
				%>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="code">Code</label>
					<div class="col-md-4">
						<input id="code" name="code" type="text"
							placeholder="code de la pizza (3 lettres)"
							class="form-control input-md" required=""
							<%if (maPizza != null) {%> value="<%=maPizza.getCode()%>" <%}%>>

					</div>
				</div>
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="nom">Nom</label>
					<div class="col-md-4">
						<input id="nom" name="nom" type="text"
							placeholder="Nom de la Pizza" class="form-control input-md"
							required="" <%if (maPizza != null) {%>
							value="<%=maPizza.getNom()%>" <%}%>>
					</div>
				</div>
				<!-- Select Basic -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="cat">Catégorie</label>
					<div class="col-md-4">
						<select id="cat" name="cat" class="form-control">
							<%
								for (int i = 0; i < CategoriePizza.values().length; i++) {
							%><option value="<%=CategoriePizza.values()[i]%>"><%=CategoriePizza.values()[i]%></option>
							<%
								}
							%>
						</select>
						<%
							if (maPizza != null) {
						%>
						<span>Catégorie actuelle : <%=maPizza.getCategorie()%></span>
						<%
							}
						%>
					</div>
				</div>
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="prix">Prix</label>
					<div class="col-md-4">
						<input id="prix" name="prix" type="text"
							placeholder="Prix de la pizza" class="form-control input-md"
							required="" <%if (maPizza != null) {%>
							value="<%=maPizza.getPrix()%>" <%}%>>
					</div>
				</div>
				<!-- File Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="url">Image</label>
					<div class="col-md-4">
						<input id="url" name="url" class="input-file" type="file"
							 <%if (maPizza != null) {%>
							value="noImage.jpg" <%}%>>
					</div>
				</div>
				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="valider">Envoyer</label>
					<div class="col-md-4">
						<button id="valider" class="btn btn-success">Envoyer</button>
					</div>
				</div>
			</fieldset>
		</form>
		</div>
</body>
</html>