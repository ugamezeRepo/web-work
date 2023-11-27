<%@page import="java.util.List"%>
<%@page import="test.music.dao.MusicDao"%>
<%@page import="test.music.dto.MusicDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	String artist = request.getParameter("artist");
	String rdate = request.getParameter("rdate");
	
	MusicDto dto = new MusicDto();
	dto.setName(name);
	dto.setArtist(artist);
	dto.setRdate(rdate);
	
	MusicDao dao = MusicDao.getInstance();
	boolean isSuccess = dao.addSong(dto);
	List<MusicDto> list = dao.findSongList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/music/insert.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
	<%if (isSuccess) { %>
		<p class="alert aler-success">
			리스트에 <strong>[<%=name %>]</strong>을 추가했습니다. <br />
			<a href="${pageContext.request.contextPath}/music/list.jsp" class="aler-link">리스트로</a>
		</p>
	<%} else { %>
		<p class="alert alert-danger">
			리스트에 추가하지 못했습니다. <br />
			<a class="alert-link" href="${pageContext.request.contextPath}/music/insertform.jsp">다시 추가</a>
			<a href="${pageContext.request.contextPath}/music/list.jsp" class="aler-link">리스트로</a>
		</p>
	<%} %>
	</div>
</body>
</html>