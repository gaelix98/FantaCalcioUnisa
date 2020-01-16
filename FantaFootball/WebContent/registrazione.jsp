<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrati</title>
<%@include file="header.html"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container ">
		<div class="row align-items-center">
		<div class="col-lg-3 col-md-3"></div>
				<div class="col-lg-6 col-md-6 well box-login has-footer">
					<%
						String message = (String) request.getAttribute("message");
						if (message != null && !message.equals("")) {
					%>
					<h4 class="alert alert-danger"><%=message%></h4>
					<%
						}
					%>
					<form action="RegistrazioneServlet" method="post" id="form1">
						<h3 class="mb-30" align="center">Registrati</h3>
						<div class="mt-10">
							<input type="text" name="nome" required="required"
								placeholder="Nome" id="nome" class="single-input"> <span
								class="error">Il nome deve contenere solo lettere
								dell'alfabeto e deve avere lughezza minima 2</span><br>
						</div>
						<div class="mt-10">
							<input type="text" name="cognome" required="required"
								placeholder="Cognome" id="cognome" class="single-input">
							<span class="error">Il cognome deve contenere solo lettere
								dell'alfabeto e deve avere lunghezza minima 2</span><br>
						</div>
						<div class="mt-10">
							<input type="text" name="email" required="required"
								placeholder="Email" id="email" class="single-input">
								<span class="error">E-mail non disponibile o formato errato</span><br>
						</div>
						<div class="mt-10">
							<input type="text" name="username" required="required"
								placeholder="Username" id="username" class="single-input">
								<span class="error">Username non disponibile o formato errato</span><br>
						</div>
						<div class="mt-10">
							<input type="password" name="password" required="required"
								placeholder="Password" id="password" class="single-input">
							<span class="error">Formato errato password (lunghezza
								minima 5 caratteri)</span><br>
						</div>
						<div align="center">
						<input type="checkbox" onclick="showPassword()" name="bottone">Show
						Password</div>
						<hr>
						<div align="center"  style="padding-top:20px">
						<button type="submit" class="genric-btn primary circle arrow">
							Registrati<span class="lnr lnr-arrow-right"></span>
						</button></div>
					</form>
				</div>
			</div>
			<div class="col-lg-3 col-md-3"></div>
		</div>
	<%@include file="footer.jsp"%>
	<script>
		function showPassword() {
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>
	<script src="scripts/verificaUsername.js"></script>
<script src="scripts/verificaEmail.js"></script>
	<script src="scripts/validaRegistrazione.js"></script>
</body>
</html>