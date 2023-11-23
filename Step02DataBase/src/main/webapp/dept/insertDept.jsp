<%@page import="java.util.List"%>
<%@page import="test.dept.dao.DeptDao"%>
<%@page import="test.dept.dto.DeptDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	int deptno = Integer.parseInt(request.getParameter("deptno"));
	String dname = request.getParameter("dname");
	String loc = request.getParameter("loc");
	
	DeptDto dto = new DeptDto();
	dto.setDeptno(deptno);
	dto.setDname(dname);
	dto.setLoc(loc);
	
	DeptDao dao = DeptDao.getInstance();
	boolean isSuccess = dao.insertDept(dto);
	List<DeptDto> list = dao.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/dept/insertDept.jsp</title>
</head>
<body>
	<div class="container">
		<h1>알람</h1>
		<%if (isSuccess) { %>
			<p>
				<strong><%=dname %></strong>부서를 추가했습니다.<br />
				<a href="${pageContext.request.contextPath}/dept/list.jsp">목록으로</a>
			</p>
		<%} else { %>
			<p>
				부서 추가 실패<br />
				<a href="${pageContext.request.contextPath}/dept/insertDeptFrom.jsp">다시</a>
			</p>
		<%} %>
	</div>
</body>
</html>