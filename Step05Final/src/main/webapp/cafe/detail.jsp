<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	CafeDto dto = CafeDao.getInstance().getData(num);
	int viewCount = dto.getViewCount();
	
	String prevPage = request.getHeader("referer");
	String validView = "/cafe/list.jsp";
	System.out.println(prevPage);
	System.out.println(validView);
	if (prevPage != null && prevPage.contains(validView)) {
		System.out.println("1");
		dto.setViewCount(viewCount+1);
		CafeDao.getInstance().updateView(dto);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
<style>
	body {
	  font-family: Arial, sans-serif;
	  background-color: #f8f9fa;
	  margin: 0;
	  padding: 0;
	}
	
	.container {
	  max-width: 800px;
	  margin: 20px auto;
	  background-color: #fff;
	  padding: 20px;
	  border-radius: 8px;
	  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	}
	
	h1 {
	  color: #007bff;
	}
	
	a {
	  text-decoration: none;
	  color: #007bff;
	  margin-right: 10px;
	}
	
	h2 {
	  margin-top: 20px;
	}
	
	p {
	  line-height: 1.6;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>카페 글</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><%=dto.getNum() %></td>
					<td><%=dto.getWriter() %></td>
					<td><%=dto.getViewCount() %></td>
					<td><%=dto.getRegdate() %></td>
				</tr>
			</tbody>
		</table>
		<div>
			<h2>제목</h2>
			<p><h2><%=dto.getTitle() %></h2></p>
		</div>
		<div>
			<h2>내용</h2>
			<textarea cols="30" rows="10"><%=dto.getContent() %></textarea>
		</div>
		
		<div>
			<a href="${pageContext.request.contextPath}/cafe/protected/update_form.jsp?num=<%=dto.getNum() %>">수정</a>
			<a href="${pageContext.request.contextPath}/cafe/protected/delete.jsp?num=<%=dto.getNum() %>">삭제</a>
			<a href="${pageContext.request.contextPath}/cafe/list.jsp">목록으로</a>
		</div>
	</div>
</body>
</html>