<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	
	System.out.printf("번호: %d, 이름: %s 주소: %s %n", num, name, addr);
	MemberDto dto = new MemberDto(num, name, addr);
	boolean isSuccess = MemberDao.getInstance().update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>
	<div class="container">
		<%if (isSuccess) { %>
			<script>
				alert("수정했습니다.");
				// location 객체를 이용하여 회원 목록보기로 리다이렉트
				location.href="${pageContext.request.contextPath}/member/list2.jsp";
			</script>
		<%} else { %>
			<h1>알림</h1>
			<p>
				회원정보 수정 <strong>실패!</strong>
				<a href="updateform.jsp?num<%=num%>">다시 수정하러 가기</a>
			</p>
			
		<%} %>
	</div>
</body>
</html>