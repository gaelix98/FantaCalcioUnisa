<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreBacheca.*,gestoreUtente.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formazione</title>
</head>
<%@include file="header.html"%>
<%
	String tipoUtente = (String) session.getAttribute("tipoUtente");
	String username = null;
	String paginaPersonale = null;
	if (tipoUtente != null && tipoUtente.equals("allenatore")) {
		Allenatore allenatore = (Allenatore) session.getAttribute("utente");
		if (allenatore != null) {
			username = allenatore.getUsername();
			paginaPersonale = "areaPersonaleAllenatore.jsp";
		}
	} else if (tipoUtente != null && tipoUtente.equals("scout")) {
		Scout scout = (Scout) session.getAttribute("utente");
		if (scout != null) {
			username = scout.getUsername();
		}
		paginaPersonale = "areaPersonaleScout.jsp";
	}
%>
<body>

	<header class="header_area">
		<div class="sub_header">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-md-4 col-xl-6"></div>
					<%
						if (tipoUtente == null) {
					%>
					<div class="col-md-8 col-xl-6">
						<div class="sub_header_social_icon float-right">
							<a href="registrazione.jsp" class="register_icon"><i
								class="ti-arrow-right"></i>REGISTRATI</a>
						</div>
					</div>
					<%
						}
					%>
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
									<%
										if (tipoUtente == null || tipoUtente == "") {
									%>
									<li class="nav-item"><a href="login.jsp" class="nav-link">Login</a>
									</li>
									<%
										} else {
									%>
									<li class="nav-item dropdown"><a
										class="nav-link dropdown-toggle" id="navbarDropdown"
										role="button" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <%=username%>
									</a>
										<div class="dropdown-menu" aria-labelledby="navbarDropdown">
											<a class="dropdown-item" href=<%=paginaPersonale%>>Area
												Personale</a> <a class="dropdown-item" href="modificaDati.jsp">Modifica
												dati</a> <a class="dropdown-item" href="LogoutServlet">Logout</a>
										</div></li>
									<%
										}
									%>
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
					</div>
				</div>
			</div>
		</div>
	</header>
	<div style="height: 30px"></div>

	<%
		String modulo = (String) session.getAttribute("modulo");
		Formazione formazione = (Formazione) session.getAttribute("formazione");
		List<Giocatore> giocatori = (List<Giocatore>) session.getAttribute("giocatori");
		List<Giocatore> formazioneg = new ArrayList<Giocatore>();
		List<Giocatore> panchinaro = new ArrayList<Giocatore>();

		if (formazione == null) {
			response.sendRedirect("getFormazioneSquadra");
			//response.setIntHeader("Refresh", 0);
		} else {
			for (int i = 0; i < formazione.getGiocatori().length; i++) {
				if (formazione.getGiocatori()[i] != null) {
					formazioneg.add(formazione.getGiocatori()[i]);
				}
			}
			for (int i = 0; i < formazione.getPanchina().length; i++) {
				if (formazione.getPanchina()[i] != null) {
					panchinaro.add(formazione.getPanchina()[i]);
				}
			}
		}
	%>


	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4">
				<h2>Inserisci formazione</h2>
			</div>
			<div class="col-lg-2 col-md-2"></div>
			<div class="col-lg-4 col-md-4">
				<%
					if (formazioneg.size() == 11) {
						if (panchinaro.size() == 7) {
				%>
				<div align="center">
					<a href="SalvaFormazioneServlet">
						<button class="genric-btn primary circle arrow">Schiera
							la formazione</button>
					</a>
				</div>
			</div>


			<%
				}

				}
			%>
		</div>
		<div style="height: 30px"></div>
		<div class="row">
			<div class="col-lg-10 col-md-10">
				<div class="blog_left_sidebar">
					<div class="row">
						<div class="col-lg-5">
							<b>Seleziona giocatori titolari</b>
							<table class="table">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Giocatore</th>
										<th scope="col">Ruolo</th>
										<th scope="col">Punteggio</th>
										<th scope="col">Presenze</th>
										<th scope="col">Goal</th>
									</tr>
								</thead>
								<tbody>

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
									<tr>
										<td><%=i++%></td>
										<td><a
											href="InserisciGiocatoreFormazioneServlet?giocatore=<%=g.getId()%>&titolare=true&modulo=<%=modulo%>"><%=g.getCognome()%></a></td>
										<td><%=g.getRuolo()%></td>
										<td><%=g.getVotoMedio()%></td>
										<td><%=g.getPresenze()%></td>
										<td><%=g.getGoal()%></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<div class="col-lg-1"></div>
						<div class="col-lg-5">
							<b>Titolari</b>
							<table class="table">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Giocatore</th>
										<th scope="col">Ruolo</th>
										<th scope="col">Punteggio</th>
										<th scope="col">Presenze</th>
										<th scope="col">Goal</th>
									</tr>
								</thead>
								<tbody>
									<%
										int f = 1;
										for (Giocatore g : formazioneg) {
									%>
									<tr>
										<td><%=f++%></td>
										<td><a
											href="rimuoviGiocatoreFormazioneServlet?giocatore=<%=g.getId()%>"><%=g.getCognome()%></a></td>
										<td><%=g.getRuolo()%></td>
										<td><%=g.getVotoMedio()%></td>
										<td><%=g.getPresenze()%></td>
										<td><%=g.getGoal()%></td>
									</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div style="height: 30px"></div>
				<div class="row">
					<div class="col-lg-5">
						<b>Seleziona giocatori panchina</b>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Giocatore</th>
									<th scope="col">Ruolo</th>
									<th scope="col">Punteggio</th>
									<th scope="col">Presenze</th>
									<th scope="col">Goal</th>
								</tr>
							</thead>
							<tbody>
								<%
									int V = 1;

									for (Giocatore g : giocatori) {
								%>
								<tr>
									<td><%=V++%></td>
									<td><a
										href="InserisciGiocatoreFormazioneServlet?giocatore=<%=g.getId()%>&titolare=false&modulo=<%=modulo%>)"><%=g.getNome()%></a></td>
									<td><%=g.getRuolo()%></td>
									<td><%=g.getVotoMedio()%></td>
									<td><%=g.getPresenze()%></td>
									<td><%=g.getGoal()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<div class="col-lg-1"></div>
					<div class="col-lg-5">
						<b>Panchina</b>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Giocatore</th>
									<th scope="col">Ruolo</th>
									<th scope="col">Punteggio</th>
									<th scope="col">Presenze</th>
									<th scope="col">Goal</th>
								</tr>
							</thead>
							<tbody>
								<%
									int b = 1;
									for (int g = 0; g < panchinaro.size(); g++) {
								%>
								<tr>
									<td><%=b++%></td>
									<td><a
										href="rimuoviGiocatoreFormazioneServlet?giocatore=<%=panchinaro.get(g).getId()%>">
											<%=panchinaro.get(g).getCognome()%></a></td>
									<td><%=panchinaro.get(g).getRuolo()%></td>
									<td><%=panchinaro.get(g).getVotoMedio()%></td>
									<td><%=panchinaro.get(g).getPresenze()%></td>
									<td><%=panchinaro.get(g).getGoal()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>

			</div>
			<div class="col-lg-1 col-md-1"></div>
			<div class="col-lg-1 col-md-1">
				<aside class="single_sidebar_widget search_widget">
					<h4>
						<a href="classificaLega.jsp">Classifica</a>
					</h4>
					<h4>
						<a href="formazioni.jsp">Formazioni</a>
					</h4>
					<h4>
						<a href="aste.jsp">Aste</a>
					</h4>
					<h4>
						<a href="calendario.jsp">Calendario</a>
					</h4>
				</aside>
			</div>
		</div>
	</div>
</div>
	<%@include file="footer.jsp"%>
	
</body>
</html>