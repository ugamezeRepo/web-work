<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/insert_form.jsp</title>
</head>
<body>
	<div class="container">
		<h3>카페글 작성 폼입니다.</h3>
		
		<form action="insert.jsp" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" />
			</div>
			<div>
				<label for="content">내용</label>
				<textarea name="content" id="content" cols="30" rows="10"></textarea>
			</div>
			<button type="submit">작성</button>
		</form>
	</div>
</body>
</html>