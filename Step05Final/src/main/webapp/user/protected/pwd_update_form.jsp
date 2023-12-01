<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	UserDto dto = UserDao.getInstance().getData(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<!-- 
		1. 비밀번호 수정하기 링크를 누르면 /user/protected/pwd_updateform.jsp 페이지로 이동되게 한다.
		2. pwd_updateform.jsp 페이지에서는 form 안에 기존 pwd, 새 pwd, 새 pwd 확인 3가지 정보를 입력받도록 한다.
		3. 새 pwd와 새 pwd 확인 란에 입력한 pwd가 다르면 폼 전송이 안 되도록 막아야한다.
		4. 폼이 전송되면 /user/protected/pwd_update.jsp 페이지에서 pwd 수정 작업을 처리하도록 한다.
		5. DB에서 기존 pwd를 읽어와서 실제 입력한 구 pwd와 같은 지 확인해서
			다르면 비밀번호 수정 실패!
			같으면 DB에 새 pwd로 수정 반영하고, 로그아웃을 시키고 새로 로그인할 수 있도록 링크를 제공한다.
	 -->
	 
	 <div class="container">
	 	<h3>비밀번호 수정 양식</h3>
	 	<form action="pwd_update.jsp" method="post" id="myForm">
			<div class="mt-2">
				<label class="form-label" for="pwd">기존 비밀번호</label>
				<input class="form-control" type="password" id="pwd" name="pwd" />
			</div>
			<div class="mt-2">
				<label class="form-label" for="newPwd">비밀번호</label>
				<input class="form-control" type="password" id="newPwd" name="newPwd" />
				<div class="form-text">특수문자를 하나 이상 추가하세요.</div>
				<div class="invalid-feedback">비밀번호를 확인하세요.</div>
			</div>
			<div class="mt-2">
				<label class="form-label" for="newPwd2">비밀번호 확인</label>
				<input class="form-control" type="password" id="newPwd2" name="newPwd2" />
			</div>
			<div class="mt-2">
				<button class ="btn btn-primary" type="submit" id="updateBtn">수정하기</button>
				<button class ="btn btn-primary" type="reset">다시 작성</button>
				<a href="${pageContext.request.contextPath}/">
					<button class ="btn btn-primary" type="button">돌아가기</button>
				</a>
			</div> 
		</form>
	 </div>
	 
	 <script>
	 	// 폼에 submit 이벤트가 일어났을 때 실행할 함수를 등록하고
	 	document.getElementById("myForm").addEventListener("submit", (e) => {
	 		//입력한 새 비밀번호 2개를 읽어와서
			let pwd1 = document.getElementById("newPwd");
			let pwd2 = document.getElementById("newPwd2");
			//만일 새 비밀번호와 비밀번호 확인이 일치하지 않으면 폼 전송을 막는다.
			if(pwd1.value != pwd2.value){
				alert("비밀번호가 일치하지 않습니다.");
				e.preventDefault();
			}
	 	});
	 </script>
</body>
</html>