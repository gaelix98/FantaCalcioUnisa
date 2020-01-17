<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, gestoreSquadra.*, gestoreBacheca.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica Post</title>
<%@include file="header.html"%>
</head>

<body>
	<%@ include file="menu.jsp"%>
	<div class="container ">
		<div class="row">
		<div class="col-lg-3 col-md-3"></div>
			<div class="col-lg-6 col-md-6 well box-login has-footer">
				<h3 class="mb-30">Modifica il tuo post</h3>
				<%
					Post post = (Post) session.getAttribute("POSTA");

					String message = (String) request.getAttribute("message");
					if (message != null && !message.equals("")) {
				%>
				<h4 class="alert alert-danger"><%=message%></h4>
				<%
					}
				%>
				<form action="modificaPostServlet" method="post" id="form1">
					<input type="hidden" name="idpost" value=<%=post.getIdPost()%>>

					<textarea name="text" id="testo"><%=post.getTesto()%></textarea>
					<hr>
					<button type="submit" class="genric-btn primary circle arrow">
						Conferma<span class="lnr lnr-arrow-right"></span>
					</button>
				</form>
			</div>
			<div class="col-lg-3 col-md-3"></div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script src="scripts/VALIDAPOSTA.js"></script>
</body>
</html>
