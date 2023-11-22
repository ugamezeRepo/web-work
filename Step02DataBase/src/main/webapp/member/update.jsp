<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	
	System.out.printf("%d %s %s %n", num, name, addr);
	MemberDto dto = new MemberDto(num, name, addr);
	boolean isSuccess = MemberDao.getInstance().update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		
		<%if (isSuccess) { %>
			<p>
				<strong><%=num %></strong>번 님의 정보를 수정했습니다.<br />
				<a href="${pageContext.request.contextPath}/member/list2.jsp">목록보기</a>
			</p>
		<%} else { %>
			<p>
				회원정보 수정 실패!!<br />
				<a href="${pageContext.request.contextPath}/member/insertfomr.jsp"></a>
			</p>
		<%} %>
	</div>
</body>
</html>