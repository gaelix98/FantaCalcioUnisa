<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestoreBacheca.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FantaFootball</title>
<%@include file="header.html"%>
</head>
<body>
<%@include file="menu.jsp"%>
<%ArrayList<Post> allPost=(ArrayList<Post>) session.getAttribute("allPost");
if (allPost==null) {response.sendRedirect("getAllPostServlet");}%>
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
<%@include file="footer.jsp"%>
</body>
</html>