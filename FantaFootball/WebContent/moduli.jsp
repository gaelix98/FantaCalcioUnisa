<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreBacheca.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formazione</title>
</head>
<%@include file="header.html"%>
<body>
	<%@ include file="menu.jsp"%>
	<div style="height: 20px"></div>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4"></div>
			<div class="col-lg-4 col-md-4 well box-login has-footer">

				<h3 class="mb-30" align="center">Scegli modulo</h3>
				<div align="center"><div class="single-element-widget mt-10">
					<div class="default-select">
						<select id="modulo" name="modulo">
				<option value="0">Scegli modulo</option>
				<option value="3-4-3">3-4-3</option>
				<option value="4-4-2">4-4-2</option>
				<option value="3-5-2">3-5-2</option>
			</select></div></div>
				</div>
				<div align="center"  style="padding-top:10px"><button class="genric-btn primary circle arrow" value="Send"
					onclick="Send()">Conferma</button></div>
			</div>
			<div class="col-lg-4 col-md-4"></div>
		</div>
	

	<img src="img/moduli.jpg" alt="" title=""></img>
</div>
	<%@include file="footer.jsp"%>

	<script type="text/javascript">
		function Send() {

			var tech = document.getElementById("modulo").value;
			//    var url = "filtraGiocatoriServlet?p=1&ruolo=&squadra=&modulo=" + encodeURIComponent(tech);
			var url = "getFormazioneSquadra?modulo=" + encodeURIComponent(tech);
			window.location.href = url;
		};
	</script>
</body>
</html>