<%@page import="test.util.DbcpBean"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	Connection conn = new DbcpBean().getConn();
	if (conn != null) conn.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test.jsp</title>
</head>
<body>
	<%if (conn != null) { %>
		<p>Connection 객체를 성공적으로 얻어왔습니다.</p>
	<%} else {  %>
		<p>Connection 객체 얻어오기 실패!</p>
	<%} %>
</body>
</html>