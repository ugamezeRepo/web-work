<%@page import="test.dept.dto.DeptDto"%>
<%@page import="java.util.List"%>
<%@page import="test.dept.dao.DeptDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	DeptDao dao = DeptDao.getInstance();
	List<DeptDto> list = dao.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>depart list.jsp</title>
</head>
<body>
	<div class="container">
		<h1>부서 목록입니다.</h1>
		<a href="${pageContext.request.contextPath}/dept/insertDeptForm.jsp">부서 추가하기</a>
		
		<table border="1" align="center">
			<thead>
				<tr>
					<th>부서 번호</th>
					<th>부서명</th>
					<th>부서 위치</th>
					<th>수정하기</th>
					<th>삭제하기</th>
				</tr>
			</thead>
			<tbody>
			<%for (DeptDto dto:list) { %>
				<tr>
					<td><%=dto.getDeptno() %></td>
					<td><%=dto.getDname() %></td>
					<td><%=dto.getLoc() %></td>
					<td>
						<a href="updateDeptForm.jsp?deptno=<%=dto.getDeptno() %>">수정하기</a>
					</td>
					<td>
						<a href="deleteDept.jsp?deptno=<%=dto.getDeptno() %>">삭제하기</a>
					</td>
				</tr>
			<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>