<%@page import="test.music.dao.MusicDao"%>
<%@page import="test.music.dto.MusicDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	String artist = request.getParameter("artist");
	String rdate = request.getParameter("rdate");
	
	MusicDto dto = new MusicDto(num, name, artist, rdate);
	boolean isSuccess = MusicDao.getInstance().editSong(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/Music/update.jsp</title>
</head>
<body>
	<div class="container">
		<%if (isSuccess) { %>
			<script>
				alert("수정을 완료했습니다.");
				location.href="${pageContext.request.contextPath}/music/list.jsp";
			</script>
		<%} else { %>
			<h1>알림</h1>
			<p>
				곡 수정을 완료하지 못했습니다. <br />
				<a href="updateform.jsp?num=<%=num %>">다시 수정하기</a>
				<a href="list.jsp">리스트로</a>
			</p>
			
		<%} %>
	</div>
</body>
</html>