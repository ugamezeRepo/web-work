<%@page import="test.dept.dto.DeptDto"%>
<%@page import="test.dept.dao.DeptDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int deptno = Integer.parseInt(request.getParameter("deptno"));
	DeptDto dto = DeptDao.getInstance().getdept(deptno);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/dept/updateDeptForm.jsp</title>
</head>
<body>
	<div class="container">
		<h1>부서 정보 수정 양식</h1>
		<form action="updateDept.jsp" method="post">
			<div>
				<label for="deptno">부서번호</label>
				<input type="text" id="deptno" name="deptno" value="<%=dto.getDeptno() %>" readonly/>
			</div>
			<div>
				<label for="dname">부서명</label>
				<input type="text" id="dname" name="dname" placeholder="<%=dto.getDname() %>"/>
			</div>
			<div>
				<label for="loc">부서 위치</label>
				<input type="text" id="loc" name="loc" placeholder="<%=dto.getLoc() %>"/>
			</div>
			<button type="submit">수정 확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>