<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session scope에 저장된 아이디를 이용해서
	String id = (String)session.getAttribute("id");
	// 수정할 회원의 정보를 얻어온다.
	UserDto dto = UserDao.getInstance().getData(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/update_form.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h3>회원 정보 수정 양식</h3>
		<form action="update.jsp" method="post">
			<div>
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" id="id" value="<%=dto.getId() %>" readonly/>
			</div>
			<div>
				<label class="form-label" for="email">이메일</label>
				<input class="form-control" type="text" id="email" name="email" value="<%=dto.getEmail() %>"/>
			</div>
			<div class="mt-2">
				<button class ="btn btn-primary" type="submit">수정확인</button>
				<button class ="btn btn-primary" type="reset">취소</button>
				<a href="${pageContext.request.contextPath}/">
					<button class ="btn btn-primary" type="button">돌아가기</button>
				</a>
			</div>
		</form>
	</div>
</body>
</html>