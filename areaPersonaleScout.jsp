<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestoreSquadra.*, gestoreBacheca.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Area Personale</title>
<%@include file="header.html"%>
</head>
<body>
<%Scout s=(Scout) session.getAttribute("utente");
ArrayList<Post> postScout=(ArrayList<Post>) session.getAttribute("post");
ArrayList<Post> posts=(ArrayList<Post>) session.getAttribute("allPost");%>
	<%@ include file="menu.jsp"%>
		<div class="container">
		<div class="row">
			<h4><%=s.getNome()%>
				<%=s.getCognome()%></h4>
		</div>
		<div class="row">
			<div class="col-lg-8 col-md-8">
				<div class="blog_left_sidebar">
						<h2>I tuoi post</h2>
						<a href="newPost.jsp"> <button class="genric-btn primary circle arrow">
							 Inserisci Post</button></a> 
						<%
							if (postScout.size() > 0) {
								for (Post post : postScout) {
						%>
						<div class="row">
						<div class="col-lg-7 col-md-7">
						<div class="col-lg-8 col-md-8">
						<h3> <%=post.getIdPost()%> 
							<a href="getPostServlet?p=<%=post.getIdPost()%>"><%=post.getTitolo() %></a>
							</h3>
						</div>
						<div class="col-lg-4 col-md-4">
						<h4><%=post.getData() %></h4>
						</div>
						</div>
							<div class="col-lg-8 col-md-8">
							<p><%=post.getTesto()%>
							</p></div> 
							<div class="col-lg-4 col-md-4">
							<a href="rimuoviPostServlet?idPost=<%=post.getIdPost()%>"> <button class="genric-btn primary circle arrow">
							 Elimina</button></a>
							
						
							</div>
						</div><hr><br>
							<%
								}
								} else {
							%>
						
						<p>
							Non hai inserito post
							<%
													}
												%>
					</div>
					</div>

			<div class="col-lg-4 col-md-4">
				<aside class="single_sidebar_widget search_widget">
					<h2>Bacheca</h2>
					<%
							if (posts.size() > 0) {
								for (int i=0;i<5 && i<posts.size();i++) {
									Post p=posts.get(i);
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
							<p><%=p.getTesto()%></p>
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
					<a href="getAllPost"> <button class="genric-btn primary circle arrow">
							 Visualizza Altro</button></a>
				</aside>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>