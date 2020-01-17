<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formazioni</title>
<%@include file="header.html"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<%List<Formazione> formazioni= (List<Formazione>) session.getAttribute("formazioni"); %>
	<div class="container">
		<div class="row">
			<h4>
				<a href="formazioni.jsp">Formazioni</a> | <a
					href="moduli.jsp">Inserisci Formazione</a>
			</h4>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<div class="col-lg-9 col-md-9">
				<div class="blog_left_sidebar">
					<h3>Ultime formazioni schierate</h3>
					<br>
					<div class="row">
					<%
						for (int i = 0; i < formazioni.size();i++) {
							if (i % 2 == 0) {
					%>
					</div><div class="row">
						<%
							} Formazione formazione=formazioni.get(i);
						%>
					<div class="col-lg-5 col-md-5">
					<h4><%=formazione.getSquadra().getNome() %></h4>
					<p><b>Titolari</b></p>
					<table class="table">
							<thead>
								<tr>
									<th scope="col">Giocatore</th>
									<th scope="col">Ruolo</th>
								</tr>
							</thead>
							<tbody>
							<%for (int j=0;j<formazione.getGiocatori().length && formazione.getGiocatori()[i]!=null; i++){ %>
							<tr>
									<td><%=formazione.getGiocatori()[i].getNome()%> <%=formazione.getGiocatori()[i].getCognome()%></td>
									<td><%=formazione.getGiocatori()[i].getRuolo()%></td>
								</tr>
							<%} %>
							</tbody>
							</table>
					<p><b>Panchina</b></p>
					<table class="table">
							<thead>
								<tr>
									<th scope="col">Giocatore</th>
									<th scope="col">Ruolo</th>
								</tr>
							</thead>
							<tbody>
							<%for (int j=0;j<formazione.getPanchina().length && formazione.getPanchina()[i]!=null; i++){ %>
							<tr>
									<td><%=formazione.getPanchina()[i].getNome()%> <%=formazione.getPanchina()[i].getCognome()%></td>
									<td><%=formazione.getPanchina()[i].getRuolo()%></td>
								</tr>
							<%} %>
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
	<%@include file="footer.jsp"%>
</body>
</html>