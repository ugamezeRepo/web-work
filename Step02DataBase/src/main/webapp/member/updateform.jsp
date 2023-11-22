<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// GET 방식 파라미터로 전달되는 수정할 회원의 번호를 읽어온다.
	int num = Integer.parseInt(request.getParameter("num"));

	// DB에서 해당 회원의 정보를 읽어온다.(MemberDto)
	MemberDto dto = MemberDao.getInstance().getMember(num);
	
	// 아래 form 요소의 기본 value값으로 출력한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 정보 수정 양식</h1>
		<form action="update.jsp" method="post">
			<div>
				<label for="num">번호</label>
				<input type="text" id="num" name="num" value="<%=dto.getNum() %>" readonly/>
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name" placeholder="<%=dto.getName() %>"/>
			</div>
			<div>
				<label for="addr">주소</label>
				<input type="text" id="addr" name="addr" placeholder="<%=dto.getAddr() %>"/>
			</div>
			<button type="submit">수정 확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>