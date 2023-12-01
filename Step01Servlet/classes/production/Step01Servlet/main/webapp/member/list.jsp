<%@page import="text.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 회원 목록을 담을 ArrayList 객체
	List<MemberDto> list = new ArrayList<>();
	list.add(new MemberDto(1, "송태정", "수서"));
	list.add(new MemberDto(2, "김태준", "양주"));
	list.add(new MemberDto(3, "김혜란", "수지"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 페이지</title>
</head>
<body>
	<h1>회원 목록입니다.</h1>
	
	<table border="1">
		<thead>
			<hr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
			</hr>
		</thead>
		
		<%for(MemberDto tmp:list) { %>
			<tbody>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getAddr() %></td>
				</tr>
			</tbody>
		<%} %>
	</table>
</body>
</html>