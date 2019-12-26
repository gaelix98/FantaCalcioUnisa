<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Area Personale</title>
<%@include file="header.html"%>
</head>
<body>
	<%
		ArrayList<Lega> legheCreate = (ArrayList<Lega>) session.getAttribute("legheCreate");
		ArrayList<Lega> leghe = (ArrayList<Lega>) session.getAttribute("leghe");
		ArrayList<Scambio> scambi = (ArrayList<Scambio>) session.getAttribute("scambi");
		ArrayList<Invito> inviti = (ArrayList<Invito>) session.getAttribute("inviti");
		Allenatore a = (Allenatore) session.getAttribute("utente");
	%>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="row">
			<h4><%=a.getNome()%>
				<%=a.getCognome()%></h4>
		</div>
		<div class="row">
			<div class="col-lg-8 col-md-8">
				<div class="blog_left_sidebar">
					<div class="blog_details">
						<h2>Le tue leghe</h2>
						<%
							if (leghe.size() > 0) {
								for (Lega lega : leghe) {
						%>
						<p><%=lega.getNome()%></p>
						<%
							}
							} else {
						%>
						<p>Non ci sono leghe da mostrare</p>
						<%
							}
						%>
						<button class="genric-btn primary circle arrow">
							Crea nuova lega<a href="creaLega"></a>
						</button>
					</div>
					<div class="blog_details">
						<h2>Messaggi</h2>
						<h3>Inviti di unione</h3>
						<%
							if (inviti.size() > 0) {
								for (Invito invito : inviti) {
						%>
						<div class="row">
						<div class="col-lg-7 col-md-7">
						<p>
							Invito di unione alla lega
							<%=invito.getLega().getNome()%></p></div>
							<div class="col-lg-5 col-md-5">
							<button class="genric-btn primary circle arrow">
								Unisciti alla lega<a href="uniscitiAllaLega"></a>
							</button>
							<button class="genric-btn primary circle arrow">
								Rifiuta<a href="rifiutaUnioneLega"></a>
							</button>
							</div>
						</div>
							<%
								}
								} else {
							%>
						
						<p>
							Non hai nuovi inviti%>
							<%
													}
												%>
						
						<hr>
						<h3>Richieste di scambio</h3>
						<%
							if (scambi.size() > 0) {
								for (Scambio scambio : scambi) {
						%>
						<div class="row">
						<div class="col-lg-7 col-md-7">
						<p>
							L'allenatore
							<%=scambio.getSquadra1().getAllenatore().getUsername()%>
							ti propone uno scambio per
							<%=scambio.getGiocatore1().getNome()%>
							<%=scambio.getGiocatore1().getCognome()%>
							per
							<%=scambio.getGiocatore2().getNome()%>
							<%=scambio.getGiocatore2().getCognome()%>
							e
							<%=scambio.getPrezzoOfferto()%>
							</p></div>
							<div class="col-lg-5 col-md-5">
							<button class="genric-btn primary circle arrow">
								Accetta scambio<a href="accettaScambio"></a>
							</button>
							<button class="genric-btn primary circle arrow">
								Rifiuta scambio<a href="rifiutaScambio"></a>
							</button>
							</div></div>
							<%
								}
								} else {
							%>
						
						<p>Non hai nuove proposte di scambio</p>
						<%
							}
						%>
					</div>
					<div class="blog_details">
						<h2>Leghe create</h2>
						<%
							if (legheCreate.size() > 0) {
								for (Lega lega : legheCreate) {
						%>
						<div class="row">
						<div class="col-lg-5 col-md-5">
						<p>
							<%=lega.getNome() %>
							</p></div>
							<div class="col-lg-7 col-md-7">
							<button class="genric-btn primary circle arrow">
								Invita partecipanti<a href="invitaPartecipanti.jsp"></a>
							</button>
							<button class="genric-btn primary circle arrow">
								Organizza asta<a href="organizzaAsta.jsp"></a>
							</button>
							</div></div>
							<%
								}
								} else {
							%>
						
						<p>Non ci sono leghe da mostrare</p>
						<%
							}
						%>
					</div>
				</div>
			</div>

			<div class="col-lg-4 col-md-4">
				<aside class="single_sidebar_widget search_widget">
					<h2>Bacheca</h2>
					<button class="button rounded-0 primary-bg text-white w-100">Visualizza
						altro</button>
				</aside>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
			<button class="button rounded-0 primary-bg text-white w-100">
				Cancella account<a href="cancellaAccount"></a>
			</button>
			</div>
		</div>
	</div>


	<%@include file="footer.jsp"%>
</body>
</html>