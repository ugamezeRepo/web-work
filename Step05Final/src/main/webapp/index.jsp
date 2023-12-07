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
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }

        a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
            font-size: 18px;
        }

        a:hover {
            color: #e74c3c;
        }

        h1 {
            color: #333;
        }

        p {
            font-size: 16px;
            color: #555;
        }

        .login-info {
            margin-bottom: 20px;
        }

        .login-info a {
            margin-right: 20px;
            color: #3498db;
        }

        .login-info a:hover {
            color: #e74c3c;
        }
    </style>
</head>
<body>
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
</body>
</html>
