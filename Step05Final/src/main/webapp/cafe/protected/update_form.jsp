<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	CafeDto dto = CafeDao.getInstance().getData(num);
	
	// 로그인된 아이디와 글의 작성자가 일치하는 지 확인해서 일치하지 않으면
	String id = (String)session.getAttribute("id");
	if (!dto.getWriter().equals(id)) {
		// 에러 페이지 응답
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "글을 수정할 권한이 없는 계정입니다.");
		return; // 메서드를 여기서 끝내기 (service() 메서드)
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafe/protected/update_form.jsp</title>
</head>
<body>
	<div class="container">
		<h3>글 수정</h3>
		<form action="update.jsp?num=<%=dto.getNum() %>" method="post" id="myForm">
			<table>
				<thead>
					<tr>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=dto.getWriter() %></td>
						<td><%=dto.getViewCount() %></td>
						<td><%=dto.getRegdate() %></td>
					</tr>
				</tbody>
			</table>
			<div>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" value="<%=dto.getTitle() %>"/>
			</div>
			<div>
				<label for="content">내용</label>
				<textarea name="content" id="content" cols="30" rows="10"><%=dto.getContent() %></textarea>
			</div>
			
			
			<div>
				<button type="submit">수정</button>
				<button type="reset">초기화</button>
				<a href="${pageContext.request.contextPath}/cafe/detail.jsp?num=<%=dto.getNum() %>">
					<button type="button">뒤로 가기</button>
				</a>
			</div>
		</form>
	</div>
</body>
</html>