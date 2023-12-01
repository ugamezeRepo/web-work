<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	// 1. 폼 전송되는 가입회원 정보를 읽어와서
	String id =request.getParameter("id");
	String pwd =request.getParameter("pwd");
	String email =request.getParameter("email");
	
	// 2. UserDto 객체에 담아서
	UserDto dto = new UserDto(id, pwd, email);
	
	// 3. DB에 저장한다.
	boolean isSuccess = UserDao.getInstance().insert(dto);
	
	// 4. 응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup.jsp</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<%if (isSuccess) { %>
			<p>
				<string><%=id %></string>님 가입되었습니다.
				<a href="login_form.jsp">로그인 하러가기</a>
			</p>
		<%} else { %>
			<p>
				가입을 실패했습니다.
				<a href="signup_form.jsp">다시 가입하러가기</a>
			</p>
		<%} %>
	</div>
</body>
</html>