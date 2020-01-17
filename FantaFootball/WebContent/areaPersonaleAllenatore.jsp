<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreBacheca.*"%>
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
		ArrayList<Post> post=(ArrayList<Post>) session.getAttribute("allPost");
		Allenatore a = (Allenatore) session.getAttribute("utente");
	%>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="row">
		<div class="col-lg-9 col-md-9">
			<h4><%=a.getNome()%>
				<%=a.getCognome()%></h4>
		</div>
		</div>
		<div class="row">
			<div class="col-lg-8 col-md-8">
				<div class="blog_left_sidebar">
					<div class="blog_details">
						<h2>Le tue leghe</h2>
						<%
							if (leghe.size() > 0) {
								for (Lega lega : leghe) {
									String path = getServletContext().getInitParameter("path-loghi-leghe") + "\\" + lega.getLogo();
						%>
						<div class="row">
						<div class="col-md-4 col-lg-4">
						<img src="<%=path%>">
						</div>
						<div class="col-md-4 col-lg-4">
						<p><a href="getLegaServlet?q=<%=lega.getNome()%>"><%=lega.getNome() %></a></p>
						</div>
						</div>
						<%
							}
							} else {
						%>
						<p>Non ci sono leghe da mostrare</p>
						<%
							}
						%>
						<a href="crealega.jsp"><button class="genric-btn primary circle arrow">
							Crea nuova lega
						</button></a>
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
							<a href="UniscitiAllaLega?q=1&nome=<%= invito.getLega().getNome()%>"><button class="genric-btn primary circle arrow">
								Unisciti alla lega
							</button></a>
							<a href="UniscitiAllaLega?q=0&nome=<%= invito.getLega().getNome()%>"><button class="genric-btn primary circle arrow">
								Rifiuta
							</button></a>
							</div>
						</div>
							<%
								}
								} else {
							%>
						
						<p>
							Non hai nuovi inviti
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
							<a href="invitaPartecipanti.jsp?nomelega=<%=lega.getNome()%>"><button class="genric-btn primary circle arrow">
								Invita partecipanti
							</button></a>
							<a href="organizzaAsta.jsp?nome=<%=lega.getNome()%>"><button class="genric-btn primary circle arrow">
								Organizza asta
							</button></a>
							<% %>
							<a href="organizzaAsta.jsp?nome=<%=lega.getNome()%>"><button class="genric-btn primary circle arrow" style="margin-top:5px; margin-bottom:5px">
								Organizza asta
							</button></a>
							
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
				<div class="blog_left_sidebar">
					<h2>Bacheca</h2>
					<%
							if (post!=null && post.size() > 0) {
								for (int i=0;i<5 && i<post.size();i++) {
									Post p=post.get(i);
						%>
						<div class="row">
						<div class="col-lg-7 col-md-7">
						<h3>
							<a href="getPostServlet?p=<%=p.getIdPost()%>"><%=p.getTitolo() %></a>
							</h3>
							</div>
							<div class="col-lg-5 col-md-5">
							<h3>
							<%=p.getData() %>
							</h3>
							
							</div>
					
							<p style="padding-left: 20px; padding-right: 20px"><%=p.getTesto()%><p>
							</div>
							<hr><br>
							<%
								}
								} else {
							%>
						
						<p>Non ci sono post da mostrare</p>
						<%
							}
						%>
					<button class="button rounded-0 primary-bg text-white w-100">Visualizza
						altro</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
			<a href="cancellaAccount"><button class="button rounded-0 primary-bg text-white w-100">
				Cancella account
			</button></a>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>