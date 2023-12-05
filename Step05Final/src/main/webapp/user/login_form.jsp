<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// GET 방식 파라미터 url이라는 이름으로 전달되는 값이 있는 지 읽어와본다.
	String url = request.getParameter("url");
	if (url == null) {
		// 로그인 후에 인덱스 페이지로 갈 수 있도록 한다.
		String cPath = request.getContextPath();
		url = cPath + "/index.jsp";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login_form.jsp</title>
</head>
<body>
	<div class="container">
		<h3>로그인 폼</h3>
		<form action="login.jsp" method="post">
			<!-- 폼에 입력한 정보 외에 추가로 같이 전송할 값이 있으면 input type="hidden"을 활용 -->
			<input type="hidden" name="url" value="<%=url %>" />
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" />
			</div>
			<div>
				<label for="pwd">비밀번호</label>
				<input type="password" name="pwd" id="pwd" />
			</div>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>