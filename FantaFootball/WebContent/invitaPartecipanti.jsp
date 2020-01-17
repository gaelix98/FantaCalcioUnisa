<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<style>
#invitausername {
    display: block;
}
#invitaemail{
display:none;
}
</style>
<meta charset="ISO-8859-1">
<title>Invita Partecipanti</title>
<%@include file="header.html"%>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container ">
		<div class="row">
			<div class="col-lg-8 col-md-8">
			<%          String lega=(String) request.getParameter("nomelega");
			
			            
						String message = (String) request.getAttribute("message");
						if (message != null && !message.equals("")) {
					%>
					<h4 class="alert alert-danger"><%=message%></h4>
					<%
						}
					%>
					<%=lega %>
				<h3 class="mb-30">Invita Partecipanti</h3>
				<form name="scelta">

					<div class="mt-10">
						<select id="test" name="form_select"
							onchange="showDiv(this)">
							<option value="0">Invita utente registrato</option>
							<option value="1">Invita utente non registrato</option>
						</select>
						

						
					</div>
				</form>
						
								
								
								
					<div class="mt-10" id="invitausername" >	
				<form action="InviaInvitoLegaServlet?lega=<%=lega%>" method="post" id="form1">
						<h2>Invita tramite username</h2>
						
							<input type="text" name="userall" required="required"
								placeholder="Username" id="userall" class="single-input"> <span
								class="error">L'username non è presente</span><br>
						
						<button type="submit" class="genric-btn primary circle arrow">
							Invita l'allenatore<span class="lnr lnr-arrow-right"></span>
						</button>
						</form>
						</div>
						<div class="mt-10" id="invitaemail" >
								<h2>Invita tramite Email</h2>
								<form action="InviaInvitoLegaServletlega=<%=lega%>" method="post" id="form2">
						
						
							<input type="text" name="emailall" required="required"
								placeholder="Email" id="emaiall" class="single-input"> <span
								class="error">L'email non è¨ corretta</span><br>
								
						<button type="submit" class="genric-btn primary circle arrow">
							Invita l'allenatore<span class="lnr lnr-arrow-right"></span>
						</button>
						</form>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
			function showDiv(element)
{
    if(element.value==1){
			document.getElementById("invitausername").style.display = "none";
			document.getElementById("invitaemail").style.display ="block";
    }
    if(element.value==0){
    	document.getElementById("invitausername").style.display ="block";
    	document.getElementById("invitaemail").style.display ="none";
    }
}
			</script>
			</body>
			
			</html>
				
				
								
								
								
								
								
								
								
								
						
						
						
						
						
						
						
