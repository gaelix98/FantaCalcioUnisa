<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Offerte</title>
<%@include file="header.html"%>
</head>
<body>
<%
		Lega lega = (Lega) session.getAttribute("lega");
		List<Asta> aste = (List<Asta>) session.getAttribute("aste");
		List<Asta> prossimeAste = new ArrayList<>();
		Asta astaInCorso = null;
		List<Asta> astePrecedenti = new ArrayList<>();
		Date dataAttuale = new Date();
		for (Asta asta : aste) {
			if (asta.getDataFine().before(dataAttuale)) {
				astePrecedenti.add(asta);
			} else if (asta.getDataInizio().after(dataAttuale)) {
				prossimeAste.add(asta);
			} else {
				astaInCorso = asta;
				
			}
		}
	%>
	<%
		ArrayList<Giocatore> giocatori = (ArrayList<Giocatore>) session.getAttribute("giocatori");
	%>
	<div class="container">
		<div class="row">
			<h1></h1>
		</div>
		<div class="row">
			<div class="col-lg-8 col-md-9">
				<div class="blog_left_sidebar">
					<div style="overflow: scroll; height: 20em">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Nome</th>
									<th scope="col">Ruolo</th>
									<th scope="col">Squadra</th>
									<th scope="col">Prezzo base</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < giocatori.size(); i++) {
								%>
								<tr>
									<td><a
										href="prendiGiocatore?id=<%=giocatori.get(i).getId()%>"><%=giocatori.get(i).getNome()%>
											<%=giocatori.get(i).getCognome()%></a></td>
									<td><%=giocatori.get(i).getRuolo()%></td>
									<td><%=giocatori.get(i).getSquadra()%></td>
									<td><%=giocatori.get(i).getPrezzoBase()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			Filtra qui i tuoi giocatori!
			<div>
				<form action="filtraGiocatoriServlet?p=0" method="post" id="form1">


					<input type="text" name="ruolo" placeholder="ruolo" id="ruolo"><br>
					<input type="text" name="squadra" placeholder="squadra"
						id="squadra"> <br> <input type="number"
						name="prezzoBase" placeholder="prezzo" id="prezzoBase"><br>


					<hr>
					<button type="submit" class="genric-btn primary circle arrow">
						Filtra!<span class="lnr lnr-arrow-right"></span>
					</button>
				</form>
			</div>
		</div><a
href="PrendiOfferteServlet?&data=<%=astaInCorso.getDataInizio()%>&lega=<%=astaInCorso.getLega().getNome()%>">Per visualizzare tue offerte clicca qui<br></a>
		

	</div>

	<%@include file="footer.jsp"%>
</body>
</html>