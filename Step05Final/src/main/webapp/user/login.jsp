<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	// 1. 폼 전송되는 id, pwd를 읽어와서
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	// 2. id에 해당하는 회원정보를 UserDao 객체를 이용해서 얻어와서
	UserDto dto = UserDao.getInstance().getData(id);
	
	// 3. 실제로 존재하는 id이고, pwd도 일치하는 지 비교해서
	boolean isLoginSuccess = false;
	if (dto != null) {
		if (dto.getPwd().equals(pwd)) {
			// 로그인처리 해주기
			session.setAttribute("id", id);
			isLoginSuccess = true;
		}
	}

	
	// 4. 일치하면 로그인 처리, 아니면 "id 혹은 pwd가 틀려요"라 응답한다. 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
</head>
<body>
	<div class="container">
		<%if (isLoginSuccess) { %>
			<p>
				<strong><%=dto.getId() %></strong>님 로그인되었습니다.
				<a href="${pageContext.request.contextPath}/">확인</a>
			</p>
		<%} else { %>
			<p>
				id 혹은 pwd가 틀렸습니다.
				<a href="${pageContext.request.contextPath}/user/login_form.jsp">다시 로그인</a>
			</p>
		<%} %>
	</div>
</body>
</html>