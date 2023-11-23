<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/dept/insertDeptForm.jsp</title>
</head>
<body>
	<div class="container">
		<h1>부서 추가 양식</h1>
		<form action="${pageContext.request.contextPath}/dept/insertDept.jsp">
			<div>
				<label for="deptno">부서번호</label>
				<input type="text" name="deptno" id="deptno" />
			</div>
			<div>
				<label for="dname">부서명</label>
				<input type="text" name="dname" id="dname" />
			</div>
			<div>
				<label for="loc">부서 위치</label>
				<input type="text" name="loc" id="loc" />
			</div>
			<button type="submit">부서 추가</button>
		</form>
	</div>
</body>
</html>