<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	// 폼 전송되는 기존 및 새 비밀번호 읽어오기
	String pwd = request.getParameter("pwd");
	String newPwd = request.getParameter("newPwd");
	
	// 작업의 성공 여부
	boolean isSuccess = false;
	// 현재 비밀번호
	String currentPwd = UserDao.getInstance().getData(id).getPwd();
	// 만일 현재 비밀번호와 입력한 비밀번호가 같으면
	if (currentPwd.equals(pwd)) {
		// 수정 작업을 진행
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setPwd(newPwd);
		isSuccess = UserDao.getInstance().updatePwd(dto);
	}
	// 만일 비밀번호 수정 성공이면 
	if (isSuccess) {
		// 로그아웃 처리
		session.invalidate();
	}
	// 응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/pwd_update.jsp</title>
</head>
<body>
	<script>
	<%if (isSuccess) { %>
			alert("비밀번호가 수정되었습니다.");
			location.href = "info.jsp"; 
	<%} else { %>
		alert("기존 비밀번호와 일치하지 않습니다.");
			location.href = "pwd_update_form.jsp";
	<%} %>
	</script>
</body>
</html>