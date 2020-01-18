<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Classifica</title>
<%@include file="header.html"%>
</head>
<body>
	<%
		String nomeLega = ((Lega)session.getAttribute("lega")).getNome();
		List<Squadra> classifica = (List<Squadra>) session.getAttribute("classifica");
	%>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="row">
			<h1></h1>
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
									for (int i = 0; i < classifica.size(); i++) {
											Squadra squadra = classifica.get(i);
								%>
								<tr>
									<th scope="row"><%=i+1 %></th>
									<td><a href="getRosaServlet?s=<%=squadra.getNome()%>"><%=squadra.getNome()%></a></td>
									<td><%=squadra.getPunti()%></td>
								</tr>
								<%
									}%>
								</tbody>
								</table>
								<% 	} else {
								%>

								<p>Classifica non disponibile</p>
								<%
									}
								%>
					</div>
					<div class="blog_details"></div>
				</div>
			</div>
			<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-2 col-md-2">
				<aside class="single_sidebar_widget search_widget">
					<h4><a href="classificaLega.jsp">Classifica</a></h4>
					<h4><a href="formazioni.jsp">Formazioni</a></h4>
					<h4><a href="aste.jsp">Aste</a></h4>
					<h4><a href="VisualizzaCalendarioServlet?nomeLega=<%=nomeLega%>&giornata=<%=(int)getServletContext().getAttribute("giornata")%>">Calendario</a></h4>
				</aside>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>
</body>
</html>