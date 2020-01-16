<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Risultati Mia Asta</title>
<%@include file="header.html"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<%
		HashMap giocatoreSquadra = (HashMap) request.getAttribute("giocatoreSquadra");
		Asta asta = (Asta) request.getAttribute("asta");
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
			<div class="col-lg-7 col-md-7">
				<div class="blog_left_sidebar">
					<h3>
						Asta del
						<%=asta.getDataInizio()%></h3>
						<h4>
						Giocatori per cui hai fatto delle offerte</h4>
					<br>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Giocatore</th>
									<th scope="col">Squadra aggiudicante</th>
								</tr>
							</thead>
							<tbody>
								<%
									Set keys = giocatoreSquadra.keySet();

									for (Iterator i = keys.iterator(); i.hasNext();) {
										Giocatore key = (Giocatore) i.next();
										Squadra value = (Squadra) giocatoreSquadra.get(key);
								%>
								<tr>
									<td><%=key.getNome()%> <%=key.getCognome()%></td>
									<td><%=value.getNome()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>

				<div class="col-lg-3 col-md-3"></div>
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

		<div style="height: 150px"></div>
		<%@include file="footer.jsp"%>
</body>
</html>