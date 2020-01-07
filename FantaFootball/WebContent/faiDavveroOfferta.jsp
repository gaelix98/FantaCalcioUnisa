<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*, gestoreUtente.*"%>

<!DOCTYPE html>
<html>
<head><%@include file="header.html"%>
<meta charset="ISO-8859-1">
<title>Conferma Offerta</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<%  Allenatore allenatore=(Allenatore)request.getAttribute("utente");
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

	<% Giocatore giocatore=(Giocatore) request.getAttribute("giocatore"); %>
	<div class="container">
		<div class="row">
			<h2>Fai offerta</h2>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Ruolo</th>
					<th scope="col">Squadra</th>
					<th scope="col">Prezzo base</th>
					<th scope="col">VotoMedio</th>
					<th scope="col">Presenze</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td>
						<%out.println(giocatore.getNome()); %> <%out.println(giocatore.getCognome()); %>
					</td>
					<td>
						<%out.println(giocatore.getRuolo()); %>
					</td>
					<td>
						<%out.println(giocatore.getSquadra()); %>
					</td>
					<td>
						<%out.println(giocatore.getPrezzoBase()); %>
					</td>
					<td>
						<%out.println(giocatore.getVotoMedio()); %>
					</td>
					<td>
						<%out.println(giocatore.getPresenze()); %>
					</td>
				</tr>

			</tbody>
		</table>
		<div style="height:30px"></div>
		<div>
			<form
				action="FaiOffertaServlet?giocatore=<%=giocatore.getId() %>&data=<%=astaInCorso.getDataInizio()%>&lega=<%=astaInCorso.getLega().getNome() %>"
				method="post" id="form1">


				<div class="row">
					<div class="col-lg-3 col-md-3">
						<div class="mt-10">
							<h5>Inserisci qui la tua offerta</h5>
							<input type="number" name="sommaOfferta"
								placeholder="Quanto Offri?" id="sommaOfferta"
								class="single-input"><br>
						</div>
					</div>
				</div>


				<hr>


				<button type="submit" class="genric-btn primary circle arrow">
					Conferma offerta<span class="lnr lnr-arrow-right"></span>
				</button>

			</form>
		</div>
	<div style="height:100px"></div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>