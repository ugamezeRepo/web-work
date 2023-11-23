<%@page import="test.dept.dao.DeptDao"%>
<%@page import="test.dept.dto.DeptDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int deptno = Integer.parseInt(request.getParameter("deptno"));
	String dname = request.getParameter("dname");
	String loc = request.getParameter("loc");
	DeptDto dto = new DeptDto(deptno, dname, loc);

	boolean isSuccess = DeptDao.getInstance().updateDept(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/dept/updateDept.jsp</title>
</head>
<body>
	<div class="container">
		<%if (isSuccess) { %>
			<script>
				alert("수정했습니다.");
				// location 객체를 이용하여 회원 목록보기로 리다이렉트
				location.href="${pageContext.request.contextPath}/dept/list.jsp";
			</script>
		<%} else { %>
			<h1>알림</h1>
			<p>
				회원정보 수정 <strong>실패!</strong>
				<a href="updateform.jsp?deptno<%=deptno%>">다시 수정하러 가기</a>
			</p>
			
		<%} %>
	</div>
</body>
</html>