<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// post 방식으로 전송했을 때 한글이 꺠지지 않도록
	request.setCharacterEncoding("utf-8");

	// form에 전송되는 회원의 이름과 주소를 읽어와서
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	
	// DB에 저장하고
	MemberDto dto = new MemberDto();
	dto.setName(name);
	dto.setAddr(addr);
	
	MemberDao dao = MemberDao.getInstance();
	boolean isSuccess = MemberDao.getInstance().insert(dto);
	List<MemberDto> list = dao.getList();
	
	// 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insert.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<%if (isSuccess) { %>
			<p class="alert alert-success">
				<strong><%=name %></strong>님의 정보를 추가했습니다.<br />
				<a class="alert-link" href="${pageContext.request.contextPath}/member/list2.jsp">목록보기</a>
			</p>
		<%} else { %>
			<p class="alert alert-danger">
				회원정보 추가 실패!!<br />
				<a class="alert-link" href="${pageContext.request.contextPath}/member/insertform.jsp">다시</a>
			</p>
		<%} %>
	</div>
</body>
</html>