<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href='<c:url value="media/css/bootstrap.css"></c:url>'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src='<c:url value="media/js/bootstrap.min.js"></c:url>'></script>
<title>Connection</title>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" method="post"
			action='<c:url value="/login"></c:url>'>
			<fieldset>
				<legend>Login</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="textinput">Email</label>
					<div class="col-md-4">
						<input id="email" name="email" type="email"
							placeholder="Type@mail.fr" class="form-control input-md"
							required="">
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="textinput">Mot
						de passe</label>
					<div class="col-md-4">
						<input id="mdp" name="mdp" type="password"
							class="form-control input-md" required="">
					</div>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="singlebutton">Se
						connecter</label>
					<div class="col-md-4">
						<button type="submit" id="singlebutton" name="singlebutton"
							class="btn btn-success">Valider</button>
					</div>
				</div>

			</fieldset>
		</form>
		<c:if test="${!empty message}">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-alert" aria-hidden="true"></span>
			<span class="sr-only">Error:</span> ${message}
		</div>
		</c:if>
		
	</div>
</body>
</html>