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
<%Formazione formazione=(Formazione) session.getAttribute("formazione");
List<Giocatore> giocatori = (List<Giocatore>) session.getAttribute("giocatori");
List<Giocatore> formazioneg = new ArrayList<Giocatore>();
if (formazione==null)
{response.sendRedirect("getFormazioneSquadra");
}
else{



for(int i=0;i<formazione.getGiocatori().length && formazione.getGiocatori()[i]!=null;i++){
if(formazioneg.contains(formazione.getGiocatori()[i])){
	continue;
}
formazioneg.add(formazione.getGiocatori()[i]);
}
}
%>
<h2>i tuoi giocatori</h2>
<style type="text/css">

#hidden_div {
    display: none;
}
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	margin: 0px;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: black;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: black;
}

.tg .tg-0pky {
	border-color: inherit;
	text-align: left;
	vertical-align: top
}

.tg .tg-pcvp {
	border-color: inherit;
	text-align: left;
	vertical-align: top
}

.tg-sort-header::-moz-selection {
	background: 0 0
}

.tg-sort-header::selection {
	background: 0 0
}

.tg-sort-header {
	cursor: pointer
}

.tg-sort-header:after {
	content: '';
	float: right;
	margin-top: 7px;
	border-width: 0 5px 5px;
	border-style: solid;
	border-color: #404040 transparent;
	visibility: hidden
}

.tg-sort-header:hover:after {
	visibility: visible
}

.tg-sort-asc:after, .tg-sort-asc:hover:after, .tg-sort-desc:after {
	visibility: visible;
	opacity: .4
}

.tg-sort-desc:after {
	border-bottom: none;
	border-width: 5px 5px 0
}

@media screen and (max-width: 767px) {
	.tg {
		width: auto !important;
	}
	.tg col {
		width: auto !important;
	}
	.tg-wrap {
		overflow-x: auto;
		-webkit-overflow-scrolling: touch;
		margin: auto 0px;
	}
}
</style>

<div class="tg-wrap">
<select id="test" name="form_select" onchange="showDiv('hidden_div', this)">
   <option value="0">Scegli modulo</option>
   <option value="3-4-3">3-4-3</option>
   <option value="4-4-2">4-4-2</option>
   <option value="3-5-2">3-5-2</option>
   </select>
	<table id="tg-wDj3P" class="tg">
		<tr>

			<th class="tg-0pky" colspan="6">I tuoi giocatori</th>
		</tr>

		<tr>
			<td class="tg-pcvp">No</td>
			<td class="tg-pcvp">Nome</td>
			<td class="tg-pcvp">Ruolo</td>
			<td class="tg-pcvp">Punteggio</td>
			<td class="tg-pcvp">Presenze</td>
			<td class="tg-pcvp">Goal</td>
		</tr>
		<tr>
			<%
				int i = 1;
			
			
			for(int x=0;x<formazioneg.size();x++){
				for(int s=0;s<giocatori.size();s++){
					
					if(formazioneg.get(x).getId()
							==giocatori.get(s).getId() ){
						giocatori.remove(s);
						s--;
					}
					
				}
			
			}
				for (Giocatore g : giocatori) {
					
					
					
			%>
			<td class="tg-0pky"><%=i++%></td>
			<td class="tg-0pky"><button onclick="inseriscigiocatore(<%=g.getId()%>,true)"><%=g.getNome()%></button></td>
			<td class="tg-0pky"><%=g.getRuolo()%></td>
			<td class="tg-0pky"><%=g.getVotoMedio()%></td>
			<td class="tg-0pky"><%=g.getPresenze()%></td>
			<td class="tg-0pky"><%=g.getGoal()%></td>
		</tr>
		<%
			}
		%>

	</table>
	

<div id="hidden_div">This is a hidden div</div>
</div>
<table id="tg-wDj3P" class="tg">
		<tr>

			<th class="tg-0pky" colspan="6">I tuoi giocatori schierati</th>
		</tr>

		<tr>
			<td class="tg-pcvp">No</td>
			<td class="tg-pcvp">Nome</td>
			<td class="tg-pcvp">Ruolo</td>
			<td class="tg-pcvp">Punteggio</td>
			<td class="tg-pcvp">Presenze</td>
			<td class="tg-pcvp">Goal</td>
		</tr>
		<tr>
			<%
				int f = 1;
				for (Giocatore g : formazioneg) {
					
			%>
			<td class="tg-0pky"><%=f++%></td>
			<td class="tg-0pky"><%=g.getNome()%></td>
			<td class="tg-0pky"><%=g.getRuolo()%></td>
			<td class="tg-0pky"><%=g.getVotoMedio()%></td>
			<td class="tg-0pky"><%=g.getPresenze()%></td>
			<td class="tg-0pky"><%=g.getGoal()%></td>
		</tr>
		<%
			}
		%>

	</table>









<script charset="utf-8">
function showDiv(divId, element)
{
    document.getElementById(divId).style.display = element.value != 0 ? 'block' : 'none';
    if(document.getElementById("test").value=="3-4-3"){
    document.getElementById(divId).innerHTML = document.getElementById("test").value;
    }
    if(document.getElementById("test").value=="4-4-2"){
        document.getElementById(divId).innerHTML = "aaaaa";
        }
    if(document.getElementById("test").value=="3-5-2"){
        document.getElementById(divId).innerHTML = "XXXX";
        }
}
function inseriscigiocatore(id,titolare){
var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
    
};
xhr.open('GET', 'InserisciGiocatoreFormazioneServlet?giocatore='
		+id+'&titolare='+titolare+'&modulo='+document.getElementById("test").value,true);
xhr.send();
}

</script>
</body>
</html>