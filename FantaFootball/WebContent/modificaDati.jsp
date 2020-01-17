<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica Dati</title>
<%@include file="header.html"%>
</head>
<%
	String tipo = (String) session.getAttribute("tipoUtente");
	Allenatore a = null;
	Scout s = null;
	String email = null;
	String password = null;
	if (tipo.equals("allenatore")) {
		a = (Allenatore) session.getAttribute("utente");
		email = a.getEmail();
		password = a.getPassword();
	} else {
		s = (Scout) session.getAttribute("scout");
		email = s.getEmail();
		password = s.getPassword();
	}
%>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container ">
		<div class="row">
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
				<form action="ModificaDatiPersonaliServlet" method="post" id="form1">
					<h3 class="mb-30" align="center">Modifica i tuoi dati</h3>
					<div class="mt-10">
						<input type="text" name="email" placeholder="Email" id="email"
							onfocus="this.placeholder = ''"
							onblur="this.placeholder = 'Email'" required class="single-input"
							value=<%=email%>> <span class="error">E-mail
							formato errato</span><br>
					</div>
					<div class="mt-10">
						<input type="password" name="password" placeholder="Password"
							id="password" onfocus="this.placeholder = ''"
							onblur="this.placeholder = 'Password'" required
							class="single-input" value=<%=password%>> <span
							class="error">Formato errato password (lunghezza minima 5
							caratteri)</span><br>
					</div>
					<div align="center">
						<input type="checkbox" onclick="showPassword()">Show
						Password
					</div>
					<hr>
					<div align="center" style="padding-top: 20px">
						<button type="submit" class="genric-btn primary circle arrow">
							Conferma<span class="lnr lnr-arrow-right"></span>
						</button>
					</div>
				</form>
			</div>
			<div class="col-lg-3 col-md-3"></div>
		</div>
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
	<script src="scripts/validaModificaDati.js"></script>
</body>
</html>