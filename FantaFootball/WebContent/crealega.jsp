<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% Allenatore utente = (Allenatore) session.getAttribute("utente");
	if(utente == null){
		response.sendRedirect("index.jsp");
	}
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creazione Lega</title>
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
					<form action="CreaLegaServlet" method="post" id="form1"
						enctype="multipart/form-data">
						<h2>Registrazione di una nuova Lega</h2>
						<div class="mt-10">
							<input type="text" name="nome" required="required"
								placeholder="Nome lega" id="nome" class="single-input">
							<span class="error">Il nome della lega deve contenere solo
								lettere dell'alfabeto e deve avere una lughezza compresa tra i 4
								e i 50 caratteri</span><br>
						</div>
						<div class="mt-10">
							<label for="logoLega">Scegli un logo per la tua lega: </label> <input
								type="file" name="logoLega" required="required"
								placeholder="Seleziona un logo" id="logoLega"
								class="single-input "
								accept="image/jpeg, image/jpg, image/png, image/img"> <span
								class="error">Il logo deve essere un'immagine .jpg,.png o
								.img </span><br>

						</div>

						<div class="single-element-widget mt-10">
							<div class="default-select" >
								Max allenatori: <select name="maxAllenatori">
									<%for (int i=4;i<=10;i++){%>
									<option value=<%=i%>><%=i%></option>
									<%} %>
								</select>
							</div>
						</div>

						<div class="mt-10">
							<input type="number" name="quotaMensile" required="required"
								placeholder="Quota mensile" id="quotaMensile" class="single-input">
						</div>
						<div class="mt-10">
							Budget squadra: <select name="budget" id="budget">
								<option value="1">260 FM</option>
								<option value="2">500 FM</option>
								<option value="3">1000 FM</option>
							</select>
						</div>

						<div class="mt-10">

							<input type="number" name="primoPosto" required="required"
								placeholder="Percentuale vincita primo posto" id="primoPosto" class="single-input ">

							<span class="error">La percentuale deve essere espressa
								con un numero da 0 a 100 </span><br>

						</div>
						<div class="mt-10">

							<input type="number" name="secondoPosto" required="required"
								placeholder="Percentuale vincita secondo posto" id="secondoPosto" class="single-input ">

							<span class="error">La percentuale deve essere espressa
								con un numero da 0 a 100 </span><br>

						</div>
						<div class="mt-10">

							<input type="number" name="terzoPosto" required="required"
								placeholder="Percentuale vincita terzo posto" id="terzoPosto" class="single-input ">

							<span class="error">La percentuale deve essere espressa
								con un numero da 0 a 100 </span><br>

						</div>
						<input type="hidden" name="utente" value="<%=utente%>">
						<button type="submit" class="genric-btn primary circle arrow"
							value="submit">
							Conferma<span class="lnr lnr-arrow-right"></span>
						</button>

					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script src="scripts/validaLega.js"></script>
</body>
</html>