<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="gestoreLega.*, java.util.*, gestoreSquadra.*, gestoreLega.*, gestoreUtente.*"%>

<!DOCTYPE html>
<html>
<head><%@include file="header.html"%>
<meta charset="ISO-8859-1">
<title>Mie Offerte</title>
</head>
<body>
	

	<%
		ArrayList<Offerta> offerte = (ArrayList<Offerta>) request.getAttribute("offerte");
	%>
	<%@ include file="menu.jsp"%>
	<div class="container">
		<div class="row">
			<h1></h1>
		</div>
		<div class="row">
			<div class="col-lg-8 col-md-16">
				<div class="blog_left_sidebar">
					<div style="overflow: scroll; height: 30em; width: 90em">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Nome</th>
									<th scope="col">Ruolo</th>
									<th scope="col">Squadra</th>
									<th scope="col">Prezzo base</th>
									<th scope="col">Prezzo offerto</th>
									<th scope="col">Media</th>
									<th scope="col">Presenze</th>
									<th scope="col">Modifica prezzo</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < offerte.size(); i++) {
								%>
								<tr>
									<td><a
										href="CancellaOffertaServlet?data=<%=offerte.get(i).getAsta().getDataInizio()%>
									&lega=<%=offerte.get(i).getSquadra().getLega().getNome()%>
									&squadra=<%=offerte.get(i).getSquadra().getNome()%>
									&giocatore=<%=offerte.get(i).getGiocatore().getId()%>">X
									</a><%=offerte.get(i).getGiocatore().getNome()%> <%=offerte.get(i).getGiocatore().getCognome()%></td>
									<td><%=offerte.get(i).getGiocatore().getRuolo()%></td>
									<td><%=offerte.get(i).getGiocatore().getSquadra()%></td>
									<td><%=offerte.get(i).getGiocatore().getPrezzoBase()%></td>
									<td><%=offerte.get(i).getSomma()%></td>
									<td><%=offerte.get(i).getGiocatore().getVotoMedio()%></td>
									<td><%=offerte.get(i).getGiocatore().getPresenze()%></td>
									<td><form
											action="ModificaOffertaServlet?giocatore=<%=offerte.get(i).getGiocatore().getId()%>
									&data=<%=offerte.get(i).getAsta().getDataInizio()%>
									&squadra=<%=offerte.get(i).getSquadra().getNome()%>
									&lega=<%=offerte.get(i).getSquadra().getLega().getNome()%>"
											method="post" id="form1">




											<input type="number" name="sommaOfferta"
												placeholder="Cambi offerta?" id="sommaOfferta">


											<hr>
											<button type="submit" class="genric-btn primary circle arrow">
												Offri!<span class="lnr lnr-arrow-right"></span>
											</button>
										</form></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>