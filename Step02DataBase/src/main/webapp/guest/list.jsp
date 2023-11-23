<%@page import="java.util.List"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<GuestDto> list = GuestDao.getInstace().getList();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/list.jsp</title>
</head>
<body>
	<div class="container">
		<h1>방명록 목록입니다.</h1>
		<a href="insertform.jsp">글작성</a>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>등록일</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%for (GuestDto tmp: list) { %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getWriter() %></td>
						<td>
							<textarea row="5" readonly><%=tmp.getContent() %></textarea>
						</td>
						<td><%=tmp.getRegdate() %></td>
						<td>
							<form action="delete.jsp">
								<input type="hidden" name="num" value="<%=tmp.getNum() %>" />
								<input type="password" name="pwd" placeholder="비밀번호 입력" />
								<button type="submit">삭제</button>
							</form>
						</td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>