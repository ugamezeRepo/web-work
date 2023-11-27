<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지입니다.</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/fortune">오늘의 운세</a></li>
			<li><a href="${pageContext.request.contextPath}/test/fortune.jsp">테스트</a></li>
			<li><a href="${pageContext.request.contextPath}/member">회원정보</a></li>
			<li><a href="${pageContext.request.contextPath}/friend/list">친구목록</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list">회원목록</a></li>
		</ul>
		<form action="${pageContext.request.contextPath}/test/save.jsp" method="post">
			<p>jsp 환경</p>
			<input type="text" name="nick" placeholder="닉네임 입력..." />
			<button type="submit">닉네임 기억시키기</button>
		</form>
		<br />
		<form action="${pageContext.request.contextPath}/test/save" method="post">
			<p>servlet 환경</p>
			<input type="text" name="nick" placeholder="닉네임 입력..." />
			<button type="submit">닉네임 기억시키기</button>
		</form>
		<%
			// session scope에 "nick"이라는 키값으로 저장된 문자열이 있는 지 읽어와본다.
			String nick = (String)session.getAttribute("nick");
		%>
		<%if (nick != null) { %>
			<p>
				<strong><%=nick %></strong>님 반갑습니다.
				<a href="${pageContext.request.contextPath}/test/logout.jsp">로그아웃</a>
			</p>
		<%} else { %>
			<p>
				기억된 닉네임이 없습니다.
			</p>
		<%} %>
	</div>
</body>
</html>