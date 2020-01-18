
<%@page import="java.io.File"%>
<%@page import="java.util.Collections"%>
<%@page import="gestoreSquadra.Squadra"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "gestoreLega.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calendario</title>
<%@include file="header.html"%>
</head>
<body>
<%@ include file="menu.jsp"%>
<div class="container">
		<div class="row">

	
	<% 
	String loghiSquadrePath = getServletContext().getInitParameter("path-loghi-squadre") + File.separator;
	List<Partita> partite = (List<Partita>) request.getAttribute("calendario");
	Lega lega = (Lega) session.getAttribute("lega");
	int giornata = (int) request.getAttribute("giornata");
	int i = 0;
	for (Partita p : partite){
		Squadra squadra1 = p.getSquadra1();
		Squadra squadra2 = p.getSquadra2();
		String ris;
		if(squadra1 == null){	
			ris = squadra2.getNome()+" riposo";
		}else if(squadra2 == null){
			ris = squadra1.getNome() +" riposo";
		}else{
			ris = squadra1.getNome() +" vs "+squadra2.getNome();
		}%>
		
		<div class="col-sm-6 col-lg-3">
                  <div class="single_team_member single-home-blog">
                     <div class="card">
                       
                        
                        <div class="card-body">
                           <div class="tean_content">
        
                              <a href="VisualizzaPartitaServlet?l=<%=lega.getNome()%>&g=<%=giornata%>&s1=<%=squadra1.getNome()%>&s2=<%=squadra2.getNome()%>">
                                 <h4 class="card-title"><%= ris%></h4>
                              </a>
                               <img src="<%=loghiSquadrePath+squadra1.getLogo()%>" class="card-img-top" alt="logo1">
                           </div>
                           <div class="tean_right_content">
                          
                              <div class="header_social_icon">
                                  <img src="<%=loghiSquadrePath+squadra2.getLogo()%>" class="card-img-top" alt="logo2">
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
	<% } %>
	</div>
	</div>
	
	
	
	
	<%@include file="footer.jsp"%>
</body>
</html>