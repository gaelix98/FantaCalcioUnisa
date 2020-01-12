<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lega</title>
<%@include file="header.html"%>
</head>
<body>
	<%
		Lega lega = (Lega) session.getAttribute("lega");
		List<Asta> aste = (List<Asta>) session.getAttribute("aste");
		List<Asta> prossimeAste = new ArrayList<>();
		Asta astaInCorso = null; 
		List<Asta> astePrecedenti = new ArrayList<>();  
		Date dataAttuale = new Date();
		for (Asta asta : aste) {
			if (asta.getDataFine().before(dataAttuale)) {
				astePrecedenti.add(asta);
			} else if (asta.getDataInizio().after(dataAttuale)) {
				prossimeAste.add(asta);
			} else {
				astaInCorso = asta;
				
			}
		}
	%>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="row">
		<div class="col-lg-9 col-md-9">
			<h1><%=lega.getNome()%></h1>
		</div>
		</div>
		<div class="row">
			<div class="col-lg-9 col-md-9">
				<div class="blog_left_sidebar">
					<div class="blog_details">
						<h2>Prossime aste</h2>
						<%
							if (prossimeAste.size() > 0) {
								for (Asta a : prossimeAste) {
						%>
						<p>
							Data inizio:
							<%=a.getDataInizio()%></p>
						<p>
							Orario inizio:
							<%=a.getOra()%></p>
						<p>
							Data fine:
							<%=a.getDataFine()%></p>
						<%
							}
							} else {
						%>

						<p>Non sono previste nuove aste</p>
						<%
							}
						%>
					</div>
				</div>

				<div class="blog_details">
					<h2>Asta in corso</h2>
					<%
						if (astaInCorso != null) {
					%>
					<a href="filtraGiocatoriServlet?squadra=&p=0&data=<%=astaInCorso.getDataInizio() %>&prezzoBase=&ruolo="><button class="genric-btn primary circle arrow">Fai offerte per
							l'asta</button></a>
							
					<%
						} else {
					%>

					<p>Non ci sono aste in corso</p>
					<%
						}
					%>

				</div>
				<div class="blog_details">
					<h2>Aste passate</h2>
					<%
						if (astePrecedenti.size() > 0) {
							for (Asta a : astePrecedenti) {
					%>
					<div class="row">
					<div class="col-md-4">
					<p>
						Data inizio:
						<%=a.getDataInizio()%><br>
						Orario inizio:
						<%=a.getOra()%><br>
						Data fine:
						<%=a.getDataFine()%><br></p>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-6">
					<a href="getRisultatiAstaServlet?q=<%=a.getDataInizio()%>"><button class="genric-btn primary circle arrow">
							Risultati Asta
						</button></a>
					</div>
					</div>
				
				<%
					}
					} else {
				%>

				<p>Non ci sono aste passate in questa lega</p>
				<%
					}
				%>
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