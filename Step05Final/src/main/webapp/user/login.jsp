<%@page import="java.net.URLEncoder"%>
<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 폼 전송되는 id, pwd를 읽어와서
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String isSave = request.getParameter("isSave");
	
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
	// 로그인 후 가야할 목적지 정보
	String url = request.getParameter("url");
	// 로그인 실패를 대비해서 목적지 정보를 인코딩한 결과도 준비한다.
	String encodedUrl = URLEncoder.encode(url);
	
	if (isSave != null) {
		// 아이디 비밀번호를 쿠키로 응답하고 1주일동안 유지되도록 한다.
		Cookie cook1 = new Cookie("savedId", id);
		Cookie cook2 = new Cookie("savedPwd", pwd);
		cook1.setMaxAge(60*60*24*7);
		cook2.setMaxAge(60*60*24*7);
		response.addCookie(cook1);
		response.addCookie(cook2);
	} else {
		// 특정 키값으로 저장된 쿠키값 삭제하기 (value에는 아무 값이나 넣어도 상관없다.)
		Cookie cook1 = new Cookie("savedId", "");
		Cookie cook2 = new Cookie("savedPwd", null);
		cook1.setMaxAge(0);
		cook2.setMaxAge(0);
		response.addCookie(cook1);
		response.addCookie(cook2);		
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
				<a href=<%=url %>>확인</a>
			</p>
		<%} else { %>
			<p>
				id 혹은 pwd가 틀렸습니다.
				<a href="${pageContext.request.contextPath}/user/login_form.jsp?url=<%=encodedUrl %>">다시 로그인</a>
			</p>
		<%} %>
	</div>
</body>
</html>