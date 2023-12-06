<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session scope에 id라는 키값으로 저장된 값이 있는 지 읽어온다.
	// null이면 로그인을 하지 않은 상태, null이 아니면 로그인된 id 리턴
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<div class="container">
		<%if(id != null) { %>
			<p>
				<a href="${pageContext.request.contextPath}/user/protected/info.jsp"><%=id %></a>님 로그인 중...
				<a href="${pageContext.request.contextPath}/user/logout.jsp">로그아웃</a>
			</p>
		<%} else { %>
			<p>
				<a href="${pageContext.request.contextPath}/user/login_form.jsp">로그인</a>
			</p>
		<%} %>
		<h1>인덱스 페이지입니다.</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/user/signup_form.jsp">회원가입</a></li>
			<li><a href="${pageContext.request.contextPath}/user/protected/info.jsp">개인정보</a></li>
			<li><a href="${pageContext.request.contextPath}/shop/buy.jsp?code=1&amount=3">1번 상품 3개 구입하기</a></li>
			<li><a href="test/upload_form.jsp">파일 업로드 테스트</a></li>
			<li><a href="test/upload_form2.jsp">이미지 업로드 테스트</a></li>
			<li><a href="test/upload_form3.jsp">이미지 업로드 테스트(fetch 활용)</a></li>
			<li><a href="test/upload_form4.jsp">이미지 단독 업로드 테스트(fetch 활용)</a></li>
			<li><a href="file/list.jsp">자료실 목록보기</a></li>	 
		</ul>
	</div>
</body>
</html>