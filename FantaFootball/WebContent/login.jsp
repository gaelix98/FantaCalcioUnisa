<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@include file="header.html"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container ">
		<div class="row">
			<div class="col-lg-8 col-md-8">
				<h3 class="mb-30">Effettua il login</h3>
				<%
					String message = (String) request.getAttribute("message");
					if (message != null && !message.equals("")) {
				%>
				<h4 class="alert alert-danger"><%=message%></h4>
				<%
					}
				%>
				<form action="LoginServlet" method="post" id="form1">

					<div class="mt-10">
						<input type="text" name="username" placeholder="Username"
							id="username" onfocus="this.placeholder = ''"
							onblur="this.placeholder = 'Username'" required
							class="single-input">
					</div>
					<div class="mt-10">
						<input type="password" name="password" placeholder="Password"
							id="password" onfocus="this.placeholder = ''"
							onblur="this.placeholder = 'Password'" required
							class="single-input">
					</div>
					<input type="checkbox" onclick="showPassword()">Show
					Password
					<hr>
					<button type="submit" class="genric-btn primary circle arrow">
						Login<span class="lnr lnr-arrow-right"></span>
					</button>
				</form>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script>
		function showPassword() {
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>
</body>
</html>