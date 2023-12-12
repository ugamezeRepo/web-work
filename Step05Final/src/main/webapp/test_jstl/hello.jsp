<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_jstl/hello.jsp</title>
</head>
<body>
	<h1>JSTL의 core 라이브러리 사용해보기</h1>
	<c:forEach var="i" begin="0" end="9" step="1">
		<p>안녕 JSTL <strong>${i }</strong></p>
	</c:forEach>
	
	<h1>JSTL이 아닌 java code로 위와 같은 동작 구현.</h1>
	<%for (int i = 0; i < 10; i++) { %>
		<p>안녕 JSTL <strong><%=i %></strong></p>
	<%} %>	
</body>
</html>