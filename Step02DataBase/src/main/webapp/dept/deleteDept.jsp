<%@page import="test.dept.dao.DeptDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int deptno = Integer.parseInt(request.getParameter("deptno"));
	boolean isSuccess = DeptDao.getInstance().deleteDept(deptno);	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/dept/deleteDept.jsp</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<%if (isSuccess) { %>
			<p>
				<%=deptno %>번 부서를 삭제했습니다.
				<a href="${pageContext.request.contextPath}/dept/list.jsp">목록으로</a>
			</p>
		<%} else { %>
			<p>
				부서 삭제 실패!
				<a href="list.jsp">확인</a>
			</p>
		<%} %>
	</div>
</body>
</html>