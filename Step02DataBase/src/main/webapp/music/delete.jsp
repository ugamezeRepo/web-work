<%@page import="test.music.dao.MusicDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// GET 방식 파라미터로 전달되는 삭제할 회원의 번호를 읽어온다.
	int num = Integer.parseInt(request.getParameter("num"));

	// 회원 한 명의 정보를 삭제하고
	boolean isSuccess = MusicDao.getInstance().removeSong(num);
	
	// 응답
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/Music/delete.jsp</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<%if (isSuccess) { %>
			<p>
				<%=num %>번 곡을 삭제했습니다.
				<a href="${pageContext.request.contextPath}/music/list.jsp">리스트로</a>
			</p>
		<%} else { %>
			<p>
				곡을 삭제할 수 없습니다.
				<a href="list.jsp">리스트로</a>
			</p>
		<%} %>
	</div>
</body>
</html>