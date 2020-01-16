<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="gestoreUtente.*"%>
<!-- Header section -->
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
					<div id="logo">
						<a href="index.html"><img src="img/Logo.png" alt="" title="" /></a>
					</div>
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