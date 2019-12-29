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
	%>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="row">
			<h1></h1>
		</div>
		<div class="row">
			<div class="col-lg-8 col-md-8">
				<div class="blog_left_sidebar">
					<div class="blog_details">
						<h2>Classifica</h2>
						<%
							if (classifica!=null && classifica.size() > 0) {
								for (int i = 0; i < 5 && i < classifica.size(); i++) {
									Squadra squadra = classifica.get(i);
						%>
						<p><%=squadra.getNome()%>
						<p>
							<%
								}
								} else {
							%>
						
						<p>Classifica non disponibile</p>
						<%
							}
						%>
						<button class="genric-btn primary circle arrow">
							Classifica completa<a href="classificaLega.jsp"></a>
						</button>
					</div>
					<div class="blog_details"></div>
				</div>
			</div>
			<div class="col-lg-4 col-md-4">
				<aside class="single_sidebar_widget search_widget">
					<h4>Classifica</h4>
					<h4>Formazioni</h4>
					<h4>Aste</h4>
					<h4>Calendario</h4>
				</aside>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>
</body>
</html>