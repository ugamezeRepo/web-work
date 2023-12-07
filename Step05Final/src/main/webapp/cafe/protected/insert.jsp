<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String realPath = application.getRealPath("/upload");
	
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String writer=(String)session.getAttribute("id");
	
	//DB 에 저장할 정보를 FileDto 에 담는다.
	CafeDto dto = new CafeDto();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	
	//FileDao 를 이용해서 DB 에 업로드된 파일의 정보를 저장하고
	boolean isSuccess = CafeDao.getInstance().insert(dto);
	
	//응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/protected/upload.jsp</title>
</head>
<body>
	<div class="container">
		<%if (isSuccess) { %>
			<p>
				<%=writer %>님이 업로드한 <%=title %> 글을 작성했습니다.
				<a href="${pageContext.request.contextPath}/cafe/list.jsp">목록보기</a>
			</p>
			<p><%=realPath %></p>
		<%} else { %>
			<p>
				업로드 실패!
				<a href="insert_form.jsp">다시 시도</a>
				<a href="${pageContext.request.contextPath}/cafe/list.jsp">목록으로 돌아가기</a>
			</p>		
		<%} %>
	</div>
</body>
</html>