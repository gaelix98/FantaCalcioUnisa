<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrati</title>
<%@include file="head.html" %>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container ">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<div class="well margin-30-top">
						<%String message=(String)request.getAttribute("message");
						if (message!=null && !message.equals("")){%>
							<h4 class="alert alert-danger"><%=message %></h4>
						<%} %>
							<form action="LoginServlet" method="post" id="form1">

								<div class="row justify-content-center">
									<div class="form-group col-md-6 col-xs-12 br-1px span6" style="float: none; margin: 0 auto;">
										<h2>Registrati</h2>
										<input type="text" name="Nome" required="required"
												placeholder="Nome" class="form-control" id="nome">
										<input type="text" name="cognome" required="required"
												placeholder="Cognome" class="form-control" id="cognome">
										<input type="text" name="email" required="required"
												placeholder="Email" class="form-control" id="email">
										<input type="text" name="username" required="required"
												placeholder="Username" class="form-control" id="username">
										<input type="password" name="password" required="required"
												placeholder="Password" class="form-control" id="password">		
										<input type="checkbox" onclick="showPassword()">Show Password
										<hr>
										<button type="submit" class="btn btn-info btn-primary">
											<i class="fa fa-lock"></i> Registrati
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
<%@include file="footer.html"%>
<script>
function showPassword() {
	  var x = document.getElementById("password1");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
}
</script>
</body>
</html>