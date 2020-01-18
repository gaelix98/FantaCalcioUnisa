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
		List<Squadra> classifica = (List<Squadra>) session.getAttribute("classifica");
		Lega lega = (Lega) session.getAttribute("lega");
		List<Partita> calendario = (List<Partita>) session.getAttribute("calendario");
		List<Formazione> formazioni = (List<Formazione>) session.getAttribute("formazioni");
		List<Asta> aste = (List<Asta>) session.getAttribute("aste");
		int ultimaGiornata = Integer.parseInt(getServletContext().getInitParameter("giornata")) - 1;
		int prossimaGiornata = Integer.parseInt(getServletContext().getInitParameter("giornata"));
		String path = getServletContext().getInitParameter("path-loghi-leghe") + "\\" + lega.getLogo();
	%>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-lg-9 col-md-9">
				<h1><%=lega.getNome()%></h1>
				<div class="row">
					<div class="col-lg-6 col-md-6">
						<img src="<%=path%>"
							class="grid-item grid-item--height2 bg_img img-gal">
					</div>
					<div class="col-lg-6 col-md-6">
						<p>
							<b>Presidente</b>:
							<%=lega.getPresidente().getUsername()%><br>
						</p>
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
					<% if(!calendario.isEmpty()){ %>
					<h4>
					
						<a href="VisualizzaCalendarioServlet?nomeLega=<%=lega.getNome()%>&giornata=<%=(int)getServletContext().getAttribute("giornata")%>">Calendario</a>
					</h4>
					<%}else{ %>
					<h4>
					
						<a href="OrganizzaPartiteServlet?nomeLega=<%=lega.getNome()%>">Inizia Campionato</a>
					</h4>
					<%} %>
				</aside>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-9 col-md-9">
				<div class="blog_left_sidebar">
					<div class="blog_details">
						<h2>Classifica</h2>
						<%
							if (classifica != null && classifica.size() > 0) {
						%>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Squadra</th>
									<th scope="col">Punteggio</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < 3 && i < classifica.size(); i++) {
											Squadra squadra = classifica.get(i);
								%>
								<tr>
									<th scope="row"><%=i + 1%></th>
									<td><a href="getRosaServlet?s=<%=squadra.getNome()%>"><%=squadra.getNome()%></a></td>
									<td><%=squadra.getPunti()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<%
							} else {
						%>

						<p>Classifica non disponibile</p>
						<%
							}
						%>
						<a href="classificaLega.jsp"><button
								class="genric-btn primary circle arrow">Classifica
								completa</button></a>
					</div>
				</div>
			</div>
			
		</div>
		<div class="row">
			<div class="col-lg-5 col-md-5">
				<div class="blog_left_sidebar">
					<div class="blog_details">
						<h2>Ultima giornata</h2>
					</div>
				</div>
			</div>
			<div class="col-lg-5 col-md-5">
				<div class="blog_left_sidebar">
					<div class="blog_details">
						<h2>Ultima giornata</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>
</body>
</html>