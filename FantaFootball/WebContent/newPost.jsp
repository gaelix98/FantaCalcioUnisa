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
	
	<%@ include file="menu.jsp"%>
	<div class="container">
	<div class="row">
		<div class="col-lg-3 col-md-3"></div>
				<div class="col-lg-6 col-md-6 well box-login has-footer">
				<div class="blog_left_sidebar">
					<div class="blog_details">
							<%
						String message = (String) request.getAttribute("message");
						if (message != null && !message.equals("")) {
					%>
					<h4 class="alert alert-danger"><%=message%></h4>
					<%
						}
					%>
						<form action="InserisciPostServlet" method="post" id="form1">
						<h2>Inserisci un nuovo post  </h2>
						<div class="mt-10"> 
							<input type="text" name="titolo" required="required"
								placeholder="Titolo" id="titolo" class="single-input"> <span
								class="error">Il titolo deve essere lungo almeno 5 caratteri</span><br>
						</div>
						<div class="mt-10" >
						<textarea name="testo" rows="50" cols="70" id="testo"> 
 						</textarea>
						<span class="error">Il testo deve essere almeno di 20 caratteri</span><br>
						
						</div>
						<div style="height:20px"></div>
						<button type="submit" class="genric-btn primary circle arrow">
							Conferma<span class="lnr lnr-arrow-right"></span>
						</button>
						
					</form>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-3"></div>
	</div>
	</div>	

		
			

	<%@include file="footer.jsp"%>
	<script src="scripts/VALIDAPOSTA.js"></script>
</body>
</html>
