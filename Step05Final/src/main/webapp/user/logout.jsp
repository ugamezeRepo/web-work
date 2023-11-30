<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute("id", null);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/logout.jsp</title>
</head>
<body>
	<div class="container">
		<p>
			로그아웃 되었습니다
			<a href="${pageContext.request.contextPath}/index.jsp">돌아가기</a>
		</p>
	</div>
	
</body>
</html>