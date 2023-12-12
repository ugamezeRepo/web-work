<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String id = (String) session.getAttribute("id");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>index.jsp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="index" name="current" />
	</jsp:include>
	
    <div class="container">
        <div class="login-info">
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
        </div>
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
            <li><a href="cafe/list.jsp">카페글 목록보기</a></li>
        </ul>
    </div>
    
    <jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>
