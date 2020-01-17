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
<%@ include file="menu.jsp"%>
<div>

<select id="modulo" name="moxdulo">
    <option value="0">Scegli modulo</option>
   <option value="3-4-3">3-4-3</option>
   <option value="4-4-2">4-4-2</option>
   <option value="3-5-2">3-5-2</option>
</select>
<input type="button" value="Send" onclick = "Send()" />

       
        
<img src="img/moduli.jpg" alt="" title=""></img>
</div>

<script type="text/javascript">
    function Send() {
    	
      
        var tech = document.getElementById("modulo").value;
    //    var url = "filtraGiocatoriServlet?p=1&ruolo=&squadra=&modulo=" + encodeURIComponent(tech);
    var url="getFormazioneSquadra?modulo="+ encodeURIComponent(tech);
        window.location.href = url;
    };
</script>
     
 
</html>