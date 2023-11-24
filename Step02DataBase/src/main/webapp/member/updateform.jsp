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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="../index.jsp">Home</a></li>
				<li class="breadcrumb-item"><a href="list2.jsp">회원 목록</a></li>
				<li class="breadcrumb-item active">회원 추가</a></li>
			</ol>
		</nav>
		<h1>회원 정보 수정 양식</h1>
		<form action="update.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="num">번호</label>
				<input class="form-control" type="text" id="num" name="num" value="<%=dto.getNum() %>" readonly/>
			</div>
			<div class="mb-2">
				<label class="form-label" for="name">이름</label>
				<input class="form-control" type="text" id="name" name="name" value="<%=dto.getName() %>"/>
				<div class="form-text">본인의 이름을 입력하세요!</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="addr">주소</label>
				<input class="form-control" type="text" id="addr" name="addr" value="<%=dto.getAddr() %>"/>
				<div class="form-text">현재 거주하는 주소를 입력하세요!</div>
			</div>
			<button class="btn btn-outline-primary" type="submit">수정 확인</button>
			<button class="btn btn-outline-primary" type="reset">취소</button>
		</form>
	</div>
</body>
</html>