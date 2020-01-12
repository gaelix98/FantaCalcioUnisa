<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Risultati Asta</title>
<%@include file="header.html"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<%
		List<Offerta> offerte = (List<Offerta>) request.getAttribute("risultatiAsta");
		Asta asta = (Asta) request.getAttribute("asta");
		List<Squadra> squadre = (List<Squadra>) session.getAttribute("classifica");
	%>
	<div class="container">
		<div class="row">
			<h4>
				<a href="getRisultatiAstaServlet?q=<%=asta.getDataInizio()%>">Risultati
					asta</a> | <a
					href="getRisultatiMiaAstaServlet?q=<%=asta.getDataInizio()%>">Risultati
					mia asta</a>
			</h4>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<div class="col-lg-9 col-md-9"> 
				<div class="blog_left_sidebar">
					<h3>
						Asta del
						<%=asta.getDataInizio()%></h3>
					<br>
					<div class="row">
						<%
							for (int i = 0; i < squadre.size(); i++) {
								if (i % 2 == 0) {
						%>
					</div>
					<div class="row">
						<%
							}
								Squadra squadra = squadre.get(i);
						%>
						<div class="col-lg-5 col-md-5">
							<h4><%=squadra.getNome()%></h4>
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Giocatore</th>
										<th scope="col">Prezzo</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (int j = 0; j < offerte.size(); j++) {
												Offerta offerta = offerte.get(j);
												if (offerta.getSquadra().getNome().equals(squadra.getNome())){
									%>
									<tr>
										<td><%=offerta.getGiocatore().getNome()%> <%=offerta.getGiocatore().getCognome()%></td>
										<td><%=offerta.getSomma()%>FM</td>
									</tr>
									<%
												}}
									%>
								</tbody>
							</table>
						</div>
						<div class="col-lg-2 col-md-2"></div>
						<%
							}
						%>
					</div>
				</div>
			</div>

			<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-2 col-md-2">
				<aside class="single_sidebar_widget search_widget">
					<h4>
						<a href="classificaLega.jsp">Classifica</a>
					</h4>
					<h4>
						<a href="formazioni.jsp">Formazioni</a>
					</h4>
					<h4>
						<a href="aste.jsp">Aste</a>
					</h4>
					<h4>
						<a href="calendario.jsp">Calendario</a>
					</h4>
				</aside>
			</div>
		</div>
	</div>
	
	<div style="height:150px"></div>
	<%@include file="footer.jsp"%>
</body>
</html>