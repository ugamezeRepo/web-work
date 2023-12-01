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
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath}/user/protected/update_form.jsp">개인정보 수정</a>
		<h1>가입 정보입니다.</h1>
		<table>
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
				<td><%=profile %></td>
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