<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Organizza Asta</title>
<%@include file="header.html"%>
</head>
<body>

	<%
		String nomeLega = request.getParameter("lega");
		if (nomeLega == null || nomeLega.equals(""))
			response.sendRedirect("errorPage.jsp");
	%>
	<%@ include file="menu.jsp"%>

	<div class="container ">
		<div class="row align-items-center">
			<div class="col-md-12 col-md-offset-3">
				<form action="OrganizzaAstaServlet" method="post" id="form1">
					<h2>
						Organizza l'asta per la lega "<%=nomeLega%>"
					</h2>
					<div class="row">
						<div class="col-lg-4 col-md-4 divOrarioInizio">




							<h3>Data e ora di inizio</h3>
							<div class="mt-10">
								<input type="date" name="dataInizioAsta" required="required"
									id="dataInizio" placeholder="1111-11-11" class="single-input">
							</div>
							<div class="mt-10">
								<input type="time" name="oraInizioAsta" required="required"
									placeholder="Orario di inizio dell'asta" id="oraInizioAsta"
									class="single-input">
							</div>


							<div class="mt-10">
								<input type="date" name="dataFineAsta" required="required"
									id="dataFine"
									class="single-input">
							</div>

						</div>
	

						
		



						</div>
				<input type="hidden" name="nomeLega" value="<%=nomeLega%>">
					<button type="submit" class="genric-btn primary circle arrow"
						value="submit">
						Conferma<span class="lnr lnr-arrow-right"></span>
					</button>
				</form>
					</div>
				
			</div>
		</div>


	<%@ include file="footer.jsp"%>
</body>
<script>

	$(function() {
		setDataInizioMinima();
		checkDivOrarioFine();
		$("#dataInizio").change(checkDivOrarioFine);
	});
	

	function checkDivOrarioFine(){

		var dataInput = new Date($("#dataInizio").val());
		var dataMinima = new Date($("#dataInizio").prop("min"));

		if ( dataInput >= dataMinima) {
			$("#dataFine").prop("disabled", false);
			$("#dataFine").prop("min",dataInput.toISOString().substring(0, 10));
		} else {
			$("#dataFine").prop("disabled", true);
		}

	}
	
	function setDataInizioMinima(){
		var dataCorrente = new Date();
		dataCorrente.setDate(dataCorrente.getDate() + 3);
		$("#dataInizio").attr("min",dataCorrente.toISOString().substring(0, 10));
		$("#dataFine").prop("disabled", true);
	}
</script>
</html>