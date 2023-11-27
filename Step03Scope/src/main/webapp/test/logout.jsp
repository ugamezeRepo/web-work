<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/logout.jsp</title>
</head>
<body>
	<%
		// session scope에 "nick"이라는 키값으로 저장된 값 삭제하기
		// session.removeAttribute("nick");
		// 세션 초기화
		session.invalidate();
	%>
	<p>로그아웃되었습니다.</p>
	<a href="${pageContext.request.contextPath}/index.jsp">인덱스로</a>
</body>
</html>