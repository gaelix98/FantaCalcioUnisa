<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String nomeLega = request.getParameter("nomeLega");
	if(nomeLega == null || nomeLega.isEmpty()){
		response.sendRedirect("index.jsp");
	}
	%>
	<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione e Crea squadra</title>
<%@include file="header.html"%>
</head>
<body>
	<%@include file="menu.jsp"%>
	<div class="container ">
		<div class="row align-items-center">
			<div class="col-md-12 col-md-offset-3">
				<div class="col-lg-8 col-md-8">
				<%
						String message = (String) request.getAttribute("message");
						if (message != null && !message.equals("")) {
					%>
					<h4 class="alert alert-danger"><%=message%></h4>
					<%
						}
					%>
						<form action="RegistrazioneServlet" method="post" id="form1">
						<h2>Registrati</h2>
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
								<span class="error">E-mail non disponibile</span><br>
								 <span
								class="error">Formato errato email</span><br>
						</div>
						<div class="mt-10">
							<input type="text" name="username" required="required"
								placeholder="Username" id="username" class="single-input">
								<span class="error">Username non disponibile</span><br>
							<span class="error">Formato errato username</span><br>
						</div>
						<div class="mt-10">
							<input type="password" name="password" required="required"
								placeholder="Password" id="password" class="single-input">
							<span class="error">Formato errato password (lunghezza
								minima 5 caratteri)</span><br>
						</div>
						<input type="checkbox" onclick="showPassword()" name="bottone">Show
						Password
						<hr>
						<button type="submit" class="genric-btn primary circle arrow">
							Registrati<span class="lnr lnr-arrow-right"></span>
						</button>
					</form>
					
					
	              <form action="CreaSquadraServlet" method="post" id="form1" enctype="multipart/form-data">
						<h2>Iscrizione Squadra alla Lega "<%=nomeLega%>" </h2>
						<div class="mt-10">
							<input type="text" name="nome" required="required"
								placeholder="Nome Squadra" id="nome" class="single-input"> <span
								class="error">Il nome della squadra deve contenere solo lettere
								dell'alfabeto e deve avere lughezza minima 2</span><br>
						</div>
						<div class="mt-10" >
						
						<label for="logoSquadra" >Scegli un logo per la tua squadra: </label>
							<input type="file" name="logoSquadra" required="required"
								placeholder="Seleziona un logo" id="logoSquadra" class="single-input " accept="image/jpeg, image/jpg, image/png, image/img"> 
							
							<span class="error">Il logo deve essere un'immagine .jpg,.png o .img </span><br>
						
						</div>
						<input type="hidden" name = "nomeLega" value = "<%=nomeLega%>">
						<button type="submit" class="genric-btn primary circle arrow">
							Conferma<span class="lnr lnr-arrow-right"></span>
						</button>
						
					</form>
				</div>
			</div>
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
	<script src="scripts/verificaUsername.js"></script>
<script src="scripts/verificaEmail.js"></script>
	<script src="scripts/validaRegistrazioneSquadra.js"></script>

</body>
</html>
					
					
					
					
					
					