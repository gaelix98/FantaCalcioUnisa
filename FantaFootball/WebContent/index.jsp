<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, gestoreBacheca.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FantaFootball</title>
<%@include file="header.html"%>
</head>
<body>
	<%@include file="menu.jsp"%>
	<%
		ArrayList<Post> allPost = (ArrayList<Post>) session.getAttribute("allPost");
		if (allPost == null) {
			response.sendRedirect("getAllPostServlet");
		}
	%>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-8">

			 </div>

			<div class="col-lg-4 col-md-4">
				<div class="blog_left_sidebar">
					<h2>Bacheca</h2>
					<%
						if (allPost != null && allPost.size() > 0) {
							for (int i = 0; i < 5 && i < allPost.size(); i++) {
								Post p = allPost.get(i);
					%>
					<div class="row">
						<div class="col-lg-7 col-md-7">
							<h3>
								<a href="getPostServlet?p=<%=p.getIdPost()%>"><%=p.getTitolo()%></a>
							</h3>
						</div>
						<div class="col-lg-5 col-md-5">
							<h3>
								<%=p.getData()%>
							</h3>

						</div>

						<p style="padding-left: 20px; padding-right: 20px"><%=p.getTesto()%>
						<p>
					</div>
					<hr>
					<br>
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
	</div>
	
	
	

	<%@include file="footer.jsp"%></ body>
</html>