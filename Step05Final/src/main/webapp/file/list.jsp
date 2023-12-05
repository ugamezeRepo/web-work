<%@page import="test.file.dto.FileDto"%>
<%@page import="java.util.List"%>
<%@page import="test.file.dao.FileDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<FileDto> list = FileDao.getInstance().getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/list.jsp</title>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath}/file/protected/upload_form.jsp">업로드하러 가기</a>
		<h1>자료실 목록입니다.</h1>
		<table border="1" cellpadding="5">
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>파일명</th>
					<th>크기</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<%for(FileDto dto:list) { %>
				<tr>
					<td><%=dto.getNum() %></td>
					<td><%=dto.getWriter() %></td>
					<td><%=dto.getTitle() %></td>
					<td><a href="${pageContext.request.contextPath}/file/download?num=<%=dto.getNum() %>"><%=dto.getOrgFileName() %></a></td>
					<td><%=dto.getFileSize() %></td>
					<td><%=dto.getRegdate() %></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>