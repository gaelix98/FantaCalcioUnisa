<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreBacheca.*,gestoreUtente.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formazione</title>
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
</head>
<%@include file="header.html"%>
<%String tipoUtente=(String)session.getAttribute("tipoUtente");
String username=null;
String paginaPersonale=null;
if (tipoUtente!=null &&tipoUtente.equals("allenatore")){
	Allenatore allenatore=(Allenatore) session.getAttribute("utente");
	if (allenatore!=null){
		username=allenatore.getUsername();
		paginaPersonale="areaPersonaleAllenatore.jsp";
	}
}
else if (tipoUtente!=null && tipoUtente.equals("scout")){
	Scout scout=(Scout) session.getAttribute("utente");
	if (scout!=null){
		username=scout.getUsername();
	}
	paginaPersonale="areaPersonaleScout.jsp";
}%>
<header class="header_area">
	<div class="sub_header">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-md-4 col-xl-6">
					
				</div>
				<%if (tipoUtente==null){ %>
				<div class="col-md-8 col-xl-6">
					<div class="sub_header_social_icon float-right">
						<a href="registrazione.jsp" class="register_icon"><i
							class="ti-arrow-right"></i>REGISTRATI</a>
					</div>
				</div>
				<%} %>
			</div>
		</div>
	</div>
	<div class="main_menu">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
					<nav class="navbar navbar-expand-lg navbar-light">
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>

						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav mr-auto">
								<li class="nav-item"><a class="nav-link active"
									href="index.jsp">Home</a></li>
								<li class="nav-item"><a href="#" class="nav-link">Live</a>
								</li>
								<li class="nav-item"><a href="#" class="nav-link">Il
										Gioco</a></li>
								<li class="nav-item"><a href="#" class="nav-link">Help</a>
								</li>
								<%if (tipoUtente==null || tipoUtente==""){ %>
								<li class="nav-item"><a href="login.jsp" class="nav-link">Login</a>
								</li>
								<%}else{ %>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" id="navbarDropdown"
									role="button" data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false"> <%=username%>
								</a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdown">
										<a class="dropdown-item" href=<%=paginaPersonale%>>Area Personale</a>
										<a class="dropdown-item" href="modificaDati.jsp">Modifica dati</a>
										 <a class="dropdown-item" href="LogoutServlet">Logout</a>
									</div></li>
								<%} %>
							</ul>
							<div class="header_social_icon d-none d-lg-block">
								<ul>
									<li><a href="#"><i class="ti-facebook"></i></a></li>
									<li><a href="#"> <i class="ti-twitter"></i></a></li>
									<li><a href="#"><i class="ti-instagram"></i></a></li>
									<li><a href="#"><i class="ti-skype"></i></a></li>
								</ul>
							</div>
						</div>
					</nav>
					<div class="header_social_icon d-block d-lg-none">
						<ul>
							<li><a href="#"><i class="ti-facebook"></i></a></li>
							<li><a href="#"> <i class="ti-twitter"></i></a></li>
							<li><a href="#"><i class="ti-instagram"></i></a></li>
							<li><a href="#"><i class="ti-skype"></i></a></li>
						</ul>
					</div>
				</div></div>
				</div>
				</div>
</header>
<section class="breadcrumb breadcrumb_bg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb_iner">
                        <div class="breadcrumb_iner_item">
                            <h1>FantaFootball</h1>
                            <p></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
   </section>
    <div style="height: 30px"></div>

<%
	String modulo=(String) session.getAttribute("modulo");
	Formazione formazione = (Formazione) session.getAttribute("formazione");
	List<Giocatore> giocatori = (List<Giocatore>) session.getAttribute("giocatori");
	List<Giocatore> formazioneg = new ArrayList<Giocatore>();
	List<Giocatore> panchinaro = new ArrayList<Giocatore>();
	
	if (formazione == null) {
		response.sendRedirect("getFormazioneSquadra");
		 //response.setIntHeader("Refresh", 0);
	} else{
		for (int i=0;i<formazione.getGiocatori().length;i++){
			if (formazione.getGiocatori()[i]!=null){
				formazioneg.add(formazione.getGiocatori()[i]);
			}
		}
		for (int i=0;i<formazione.getPanchina().length;i++){
			if (formazione.getPanchina()[i]!=null){
				panchinaro.add(formazione.getPanchina()[i]);
			}
		}
	}
	
	
%>


<div class="container">

<h2>Inserisci formazione</h2>
	<div class="row">
		
			<div class="col-md-6">
			
				<table id="tg-wDj3P" class="tg">
					<tr>

						<th class="tg-0pky" colspan="6">I tuoi giocatori</th>
					</tr>

					<tr>
						<td class="tg-pcvp">No</td>
						<td class="tg-pcvp">Cognome</td>
						<td class="tg-pcvp">Ruolo</td>
						<td class="tg-pcvp">Punteggio</td>
						<td class="tg-pcvp">Presenze</td>
						<td class="tg-pcvp">Goal</td>
					</tr>
					<tr>
						<%
							int i = 1;

							for (int x = 0; x < formazioneg.size(); x++) {
								for (int s = 0; s < giocatori.size(); s++) {

									if (formazioneg.get(x).getId() == giocatori.get(s).getId()) {
										giocatori.remove(s);
										s--;
									}

								}

							}
							
							for (int x = 0; x < panchinaro.size(); x++) {
								for (int s = 0; s < giocatori.size(); s++) {

									if (panchinaro.get(x).getId() == giocatori.get(s).getId()) {
										giocatori.remove(s);
										s--;
									}

								}

							}
							for (Giocatore g : giocatori) {
						%>
						<td class="tg-0pky"><%=i++%></td>
						<td class="tg-0pky"><a href="InserisciGiocatoreFormazioneServlet?giocatore=<%=g.getId()%>&titolare=true&modulo=<%=modulo %>"><%=g.getCognome()%></a></td>
						<td class="tg-0pky"><%=g.getRuolo()%></td>
						<td class="tg-0pky"><%=g.getVotoMedio()%></td>
						<td class="tg-0pky"><%=g.getPresenze()%></td>
						<td class="tg-0pky"><%=g.getGoal()%></td>
					</tr>
					<%
						}
					%>

				</table>
			</div>
			<div class="col-md-6">
			

				<table id="tg-wDj3P" class="tg">
					<tr>

						<th class="tg-0pky" colspan="6">I tuoi giocatori schierati</th>
					</tr>

					<tr>
						<td class="tg-pcvp">No</td>
						<td class="tg-pcvp">Cognome</td>
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
						<td class="tg-0pky"><a href="rimuoviGiocatoreFormazioneServlet?giocatore=<%=g.getId()%>"><%=g.getCognome()%></a></td>
						<td class="tg-0pky"><%=g.getRuolo()%></td>
						<td class="tg-0pky"><%=g.getVotoMedio()%></td>
						<td class="tg-0pky"><%=g.getPresenze()%></td>
						<td class="tg-0pky"><%=g.getGoal()%></td>
					</tr>
					<%
						}
					%>

				</table>
			</div>
			<table id="tg-wDj3P" class="tg">
					<tr>
			<th class="tg-0pky" colspan="6">I tuoi giocatori da mettere in panchina</th>
					</tr>

					<tr>
						<td class="tg-pcvp">No</td>
						<td class="tg-pcvp">Cognome</td>
						<td class="tg-pcvp">Ruolo</td>
						<td class="tg-pcvp">Punteggio</td>
						<td class="tg-pcvp">Presenze</td>
						<td class="tg-pcvp">Goal</td>
					</tr>
					<tr>
						<%
							int V = 1;

							
							for (Giocatore g : giocatori) {
						%>
						<td class="tg-0pky"><%=V++%></td>
						<td class="tg-0pky"><a href="InserisciGiocatoreFormazioneServlet?giocatore=<%=g.getId()%>&titolare=false&modulo=<%=modulo %>)"><%=g.getNome()%></a></td>
						<td class="tg-0pky"><%=g.getRuolo()%></td>
						<td class="tg-0pky"><%=g.getVotoMedio()%></td>
						<td class="tg-0pky"><%=g.getPresenze()%></td>
						<td class="tg-0pky"><%=g.getGoal()%></td>
					</tr>
					<%
						}
					%>

				</table>
				<table id="tg-wDj3P" class="tg">
					<tr>

						<th class="tg-0pky" colspan="6">I tuoi giocatori Panchinari</th>
					</tr>

					<tr>
						<td class="tg-pcvp">No</td>
						<td class="tg-pcvp">Cognome</td>
						<td class="tg-pcvp">Ruolo</td>
						<td class="tg-pcvp">Punteggio</td>
						<td class="tg-pcvp">Presenze</td>
						<td class="tg-pcvp">Goal</td>
					</tr>
					<tr>
						<%  
							int b = 1;
							for (int g=0;g<panchinaro.size();g++) {
								
						%>
						<td class="tg-0pky"><%=b++%></td>
						<td class="tg-0pky"><a href="rimuoviGiocatoreFormazioneServlet?giocatore=<%=panchinaro.get(g).getId()%>"> <%=panchinaro.get(g).getCognome()%></a></td>
						<td class="tg-0pky"><%=panchinaro.get(g).getRuolo()%></td>
						<td class="tg-0pky"><%=panchinaro.get(g).getVotoMedio()%></td>
						<td class="tg-0pky"><%=panchinaro.get(g).getPresenze()%></td>
						<td class="tg-0pky"><%=panchinaro.get(g).getGoal()%></td>
					</tr>
					<%
							}
					%>
					
					
					
					
					
				</table>
				<%if(formazioneg.size()==11){
						if(panchinaro.size()==7){
							
						
					
					
						
						
						%>
						<button href="salvaFormazione">Salva!</button>
						
						<%}
						
				}
						%>
			</div>
		</div>
	
<script charset="utf-8">
/*function showDiv(divId, element)
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
}*/

</script>
</body>
</html>