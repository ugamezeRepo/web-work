<%@page import="java.util.List"%>
<%@page import="text.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberDto> list = (List<MemberDto>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1 class="text-center mt-3">회원 목록</h1>
		
		<div class="container table-container mt-5">
			<table class="table table-striped table-bordered">
				<thead class="table-dark">
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>주소</th>
					</tr>
				</thead>
				<tbody>
					<%for(MemberDto member: list) { %>
					<tr>
						<td><%=member.getNum() %></td>
						<td><%=member.getName() %></td>
						<td><%=member.getAddr() %></td>	
					</tr>		
					<% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
