<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreBacheca.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formazione</title>
<%@include file="header.html"%>
<%@ include file="menu.jsp"%>
<%List<Giocatore> giocatori = (List<Giocatore>) session.getAttribute("giocatori"); %>
<h2>Aste passate</h2>
					<%
						
							for (Giocatore g : giocatori) {
					%>
					<div class="col-md-6">
					<p>
						Nome:
						<%=g.getNome()  %><br>
						
						</p>
						<%
					}
					%>
					</div>


</body>
</html>