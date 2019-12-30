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
	%>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="row">
			<h1><%=lega.getNome()%></h1>
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
									<td><%=squadra.getNome()%></td>
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
			<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-2 col-md-2">
				<aside class="single_sidebar_widget search_widget">
					<h4>Classifica</h4>
					<h4>Formazioni</h4>
					<h4>Aste</h4>
					<h4>Calendario</h4>
				</aside>
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