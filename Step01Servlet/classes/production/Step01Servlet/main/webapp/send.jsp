<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String msg = request.getParameter("msg"); 
%> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/send.jsp</title>
</head>
<body>
	<p>메세지 잘 받았어, <strong>client</strong>야!</p>
	<% System.out.printf("msg: %s%n", msg); %>
</body>
</html>