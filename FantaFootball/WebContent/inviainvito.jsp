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
			<%
						String message = (String) request.getAttribute("message");
						if (message != null && !message.equals("")) {
					%>
					<h4 class="alert alert-danger"><%=message%></h4>
					<%
						}
					%>
				<h3 class="mb-30">Invita Partecipanti</h3>
				<form name="scelta">
				
				<div class="mt-10">
							<select name="sceltainvito" id="sceltainvito" required="required">
								<option value="1" >Tramite Username</option>
								<option value="2">Tramite Email</option>
							
							</select>
						</div>
						</form>
						<%  
						String s=request.getParameter("sceltainvito");
						int x = s.compareTo("Tramite Username");
							if(s!=null){ 
								if(x==0){
								%>
								
								
								
						
				<form action="InvitoServlet" method="post" id="form1">
						<h2>Invita tramite username</h2>
						<div class="mt-10">
							<input type="text" name="userall" required="required"
								placeholder="Username" id="userall" class="single-input"> <span
								class="error">L'username non è corretto</span><br>
						</div>
						<button type="submit" class="genric-btn primary circle arrow">
							Invita l'allenatore<span class="lnr lnr-arrow-right"></span>
						</button>
						</form>
						<% }
								else{%>
								
								<form action="InvitoServlet" method="post" id="form1">
						<h2>Invita tramite Email</h2>
						<div class="mt-10">
							<input type="text" name="emailall" required="required"
								placeholder="Email" id="emaiall" class="single-input"> <span
								class="error">L'email non è corretto</span><br>
								</div>
						<button type="submit" class="genric-btn primary circle arrow">
							Invita l'allenatore<span class="lnr lnr-arrow-right"></span>
						</button>
						</form>
					
								
								
								
								
								
								
								
						
						
						
						
						
						
						
						
						
