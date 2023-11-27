<%@page import="test.music.dto.MusicDto"%>
<%@page import="test.music.dao.MusicDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	MusicDto dto = MusicDao.getInstance().findSong(num);
	System.out.println(dto.getNum());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/music/updateform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="../index.jsp">Home</a></li>
				<li class="breadcrumb-item"><a href="list.jsp">Play List</a></li>
				<li class="breadcrumb-item active">곡 추가</a></li>
			</ol>
		</nav>
		<h1>곡 수정하기</h1>
		<form action="update.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="num">번호</label>
				<input class="form-control" type="text" id="num" name="num" value="<%=dto.getNum() %>" readonly/>
			</div>
			<div class="mb-2">
				<label class="form-label" for="name">곡명</label>
				<input class="form-control" type="text" id="name" name="name" value="<%=dto.getName() %>"/>
				<div class="form-text">곡명을 수정하시겠습니까?</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="artist">아티스트</label>
				<input class="form-control" type="text" id="artist" name="artist" value="<%=dto.getArtist() %>"/>
				<div class="form-text">아티스트를 수정하시겠습니까?</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="rdate">발매일</label>
				<input class="form-control" type="text" id="rdate" name="rdate" value="<%=dto.getRdate() %>"/>
				<div class="form-text">발매일을 수정하시겠습니까?</div>
			</div>
			<button class="btn btn-outline-primary" type="submit">수정 확인</button>
			<button class="btn btn-outline-primary" type="reset">취소</button>
		</form>
	</div>
</body>
</html>