<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<c:if test="${!empty maPizza}">
			<form class="form-horizontal" method="Post"
				action='<c:url value="edit?code=${maPizza.code}"></c:url>'>
				<legend>Modification Pizza</legend>
				<div>
					<input name="id" type="hidden" value="${maPizza.id }">
				</div>
		</c:if>
		<c:if test="${empty maPizza}">
			<form class="form-horizontal" method="Post"
				action='<c:url value="edit"></c:url>'>
				<legend>Ajout Pizza</legend>
		</c:if>
		<fieldset>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="code">Code</label>
				<div class="col-md-4">
					<input id="code" name="code" type="text"
						placeholder="code de la pizza (3 lettres)"
						class="form-control input-md" required=""
						<c:if test="${!empty maPizza}">
							value="${maPizza.code}"
						</c:if>>

				</div>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nom">Nom</label>
				<div class="col-md-4">
					<input id="nom" name="nom" type="text"
						placeholder="Nom de la Pizza" class="form-control input-md"
						required=""
						<c:if test="${!empty maPizza}">
							value="${maPizza.nom}"
						</c:if>>
				</div>
			</div>
			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="cat">Catégorie</label>
				<div class="col-md-4">
					<select id="cat" name="cat" class="form-control">
						<c:forEach var="categorie" items="${catPizza}">
							<option
							<c:if test="${maPizza.categorie == categorie}">selected</c:if>
							 value="${categorie}">${categorie}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="prix">Prix</label>
				<div class="col-md-4">
					<input id="prix" name="prix" type="text"
						placeholder="Prix de la pizza" class="form-control input-md"
						required=""
						<c:if test="${!empty maPizza}">
							value="${maPizza.prix}"
						</c:if>>
				</div>
			</div>
			<!-- File Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="url">Image</label>
				<div class="col-md-4">
					<input id="url" name="url" class="input-file" type="file">
				</div>
			</div>
			<script type="text/javascript">
				$("#valider").click(function() {
					var formData = new FormData();

					formData.append("file", $('#url')[0].files[0]);
					$.ajax({
						method : "post",
						enctype : "multipart/form-data",
						data : formData,
						contentType : false, // obligatoire pour de l'upload
						processData : false, // obligatoire pour de l'upload
						url : "<c:url value="../ajaxUpload"></c:url>"
					});
				});
			</script>

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