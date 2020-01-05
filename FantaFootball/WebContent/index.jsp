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
<%@include file="footer.jsp"%>
</body>
</html>