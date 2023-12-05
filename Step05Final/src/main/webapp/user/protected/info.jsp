<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 현재 로그인된 아이디
	String id = (String)session.getAttribute("id");
	// 가입정보를 DB에서 읽어온다.
	UserDto dto = UserDao.getInstance().getData(id);
	String email = dto.getEmail();
	String profile = dto.getProfile();
	String regdate = dto.getRegdate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/info.jsp</title>
<style>
	#profileImage {
		width: 100px;
		height: 100px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
</style>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath}/user/protected/update_form.jsp">개인정보 수정</a>
		<h1>가입 정보입니다.</h1>
		<table border="1" cellpadding="10">
			<tr>
				<th>아이디</th>
				<td><%=id %></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<a href="pwd_update_form.jsp">수정하기</a>
				</td>
			</tr>
			<tr>
				<th>프로필 이미지</th>
				<td>
					<% if(dto.getProfile() == null) { %>
					<svg xmlns="http://www.w3.org/2000/svg" width="200" height="200" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
					  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
					  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
					</svg>
					<%} else { %>
						<img id="profileImage" 
							src="${pageContext.request.contextPath}/upload/<%=dto.getProfile() %>" 
							alt="프로필 이미지" />
					<%} %>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=email %></td>
			</tr>
			<tr>
				<th>가입일</th>
				<td><%=regdate %></td>
			</tr>
		</table>
	</div>
</body>
</html>