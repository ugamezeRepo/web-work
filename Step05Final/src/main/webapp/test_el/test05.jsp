<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_el/test05.jsp</title>
</head>
<body>
	<%
		String msg = request.getParameter("msg");
	%>
	<p>msg라는 파라미터명으로 전송된 내용: <strong><%=msg %></strong></p>
	<%-- 위 2줄의 코딩 대신 아래와 같이 응답 가능 --%>
	<p>msg라는 파라미터명으로 전송된 내용: <strong>${param.msg }</strong></p> <%-- param. 생략 불가 --%>
</body>
</html>