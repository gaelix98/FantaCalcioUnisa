<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rosa</title>
<%@include file="header.html"%>
</head>
<body>
	<%
		Squadra squadra = (Squadra) session.getAttribute("squadra");
		Giocatore[] rosa = (Giocatore[]) session.getAttribute("rosa");
		String path=getServletContext().getInitParameter("path-loghi-leghe");
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
						<h2><%=squadra.getNome()%></h2>
						<div class="row">
							<div class="col-lg-4 col-md-4">
								<%String nomeFile=squadra.getLogo();
								if (nomeFile==null){
									path=path+"logoDefault.jpg";
								}
								else{
									path=path+nomeFile;
								}
								%>
								<img src="<%=path%>">
							</div>
							<div class="col-lg-8 col-md-8">
								<p>
									<b>Allenatore</b>:
									<%=squadra.getAllenatore().getUsername()%><br>
									<b>Punti in classifica</b>:
									<%=squadra.getPunti()%><br>
									<b>Budget rimanente</b>:
									<%=squadra.getBudgetRimanente()%>FM</p>
							</div>
						</div>
						<%
							if (rosa != null && rosa.length > 0) {
						%>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Nome</th>
									<th scope="col">Ruolo</th>
									<th scope="col">Squadra</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < rosa.length && rosa[i] != null; i++) {
								%>
								<tr>
									<td><a href="visualizzaStatisticheGiocatore.jsp?q=<%=i%>"><%=rosa[i].getNome()%>
											<%=rosa[i].getCognome()%></a></td>
									<td><%=rosa[i].getRuolo()%></td>
									<td><%=rosa[i].getSquadra()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<%
							} else {
						%>

						<p>Rosa non disponibile</p>
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
</body>
</html>