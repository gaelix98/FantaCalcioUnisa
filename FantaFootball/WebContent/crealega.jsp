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
					<form action="CreaLegaServlet" method="post" id="form1"
						enctype="multipart/form-data">
						<h3 class="mb-30" align="center">Crea nuova lega</h3>
						<div class="mt-10">
							<input type="text" name="nome" required="required"
								placeholder="Nome lega" id="nome" class="single-input">
							<span class="error">Lega già presente o formato errato</span><br>
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
								<option value="260">260 FM</option>
								<option value="500">500 FM</option>
								<option value="1000">1000 FM</option>
							</select>
						</div>

						<div class="mt-10">

							<input type="number" name="primoPosto" required="required"
								placeholder="Percentuale vincita primo posto" id="primoPosto" class="single-input ">

						</div>
						<div class="mt-10">

							<input type="number" name="secondoPosto" required="required"
								placeholder="Percentuale vincita secondo posto" id="secondoPosto" class="single-input ">

						</div>
						<div class="mt-10">

							<input type="number" name="terzoPosto" required="required"
								placeholder="Percentuale vincita terzo posto" id="terzoPosto" class="single-input ">

						</div>
						<input type="hidden" name="utente" value="<%=utente%>">
						<div align="center"  style="padding-top:20px">
						<button type="submit" class="genric-btn primary circle arrow"
							value="submit">
							Conferma<span class="lnr lnr-arrow-right"></span>
						</button></div>

					</form>
				</div>
				<div class="col-lg-3 col-md-3"></div>
			</div>
		</div>
	<%@include file="footer.jsp"%>
	<script src="scripts/verificaNomeLega.js"></script>
	<script src="scripts/validaLega.js"></script>
</body>
</html>