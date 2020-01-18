<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreBacheca.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post</title>
<%@include file="header.html"%>

</head>
<body>
	<%
		Post post = (Post) session.getAttribute("post1");
	session.setAttribute("POSTA", post);
	Scout scouto=null;
	if(session.getAttribute("tipoUtente").equals("scout") ){
      scouto=(Scout)session.getAttribute("utente");
	}
		
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
							<%
							if (post == null) {
						%>

						<p>Post non disponibile</p>
						<%
							} else{
						%>
						<h2><%=post.getTitolo()%></h2>
						<div class="row">
							
							<div class="col-lg-8 col-md-8">
								<p>
									
									<%=post.getTesto()%><br>
									
									</p>
							</div>
						</div>
						<%String tipo=(String)session.getAttribute("tipoUtente");
						if(session.getAttribute("tipoUtente").equals("scout")){
						if (tipo.equals("scout")&& post.getScout().getUsername().equals(scouto.getUsername())){ %>
							<div class="col-lg-4 col-md-4">
							<a href="modificaPost.jsp"> <button class="genric-btn primary circle arrow">
							 Modifica</button></a>
					</div>
						<% }else{
							
						%>
								
                           <%}}
						%>
			<%} %>
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