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
<title>/user/protected/pwd_update_form.jsp</title>
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
	 
	 <div class="container" id="app">
	 	<h1>비밀번호 수정 양식</h1>
	 	<form action="pwd_update.jsp" method="post" id="myForm">
			<div class="mt-2">
				<label class="form-label" for="pwd">기존 비밀번호</label>
				<input class="form-control"
					@input="onPwdInput" 
					v-bind:class="{'is-invalid': !isPwdValid && isPwdDirty, 'is-valid': isPwdValid}" type="password" name="pwd" id="pwd"/>
				<div class="invalid-feedback">반드시 입력하세요</div>
			</div>
			<div class="mt-2">
				<label class="form-label" for="newPwd">새 비밀번호</label>
				<input class="form-control" type="password" name="newPwd" id="newPwd"
					@input="onNewPwdInput" 
					v-model="newPwd"
					v-bind:class="{'is-invalid': !isNewPwdValid && isNewPwdDirty, 'is-valid': isNewPwdValid}"/>
				<small class="form-text">반드시 입력하고 아래의 확인란과 동일해야 합니다.</small>
				<div class="invalid-feedback">새 비밀번호를 확인하세요.</div>
			</div>
			<div class="mt-2">
				<label class="form-label" for="newPwd2">비밀번호 확인</label>
				<input class="form-control" type="password" id="newPwd2"
					@input="onNewPwdInput" v-model="newPwd2" />
			</div>
			<div class="mt-2">
				<button class ="btn btn-success" type="submit" v-bind:disabled="!isPwdValid || isNewPwdValid">수정하기</button>
				<button class ="btn btn-danger" type="reset">다시 작성</button>
				<a href="${pageContext.request.contextPath}/">
					<button class ="btn btn-primary" type="button">돌아가기</button>
				</a>
			</div> 
		</form>
	 </div>
	 
	 <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
     <script>
       new Vue({
         el: "#app",
         data: {
        	 isPwdValid: false,
        	 isNewPwdValid: false,
        	 newPwd: "",
        	 newPwd2: "",
        	 isPwdDirty: false, // 비밀번호 입력란에 한 번이라도 입력했는 지 여부
        	 isNewPwdDirty: false, // 새 비밀번호 입력란에 한 번이라도 입력했는 지 여부
         },
         methods: {
        	 onPwdInput(e) {
        		 // 현재까지 입력한 비밀번호
        		 const pwd = e.target.value;
        		 // 공백이 아닌 한 글자가 한 번 이상 반복되어야 통과되는 정규표현식
        		 const reg_pwd=/[\S]+/;
        		 
        		 if (reg_pwd.test(pwd)) {
        			 this.isPwdValid = true;
        		 } else {
        			 this.isPwdValid = false;
        		 }
        		 
        		 this.isPwdDirty = true;
        	 },
        	 onNewPwdInput() {
        		 //공백이 아닌 글자를 하나이상 입력했는지 확인할 정규 표현식
        		 const reg_pwd=/[\S]+/;
        		 //만일 정규표현식도 통과하고 그리고 두개의 비밀번호가 같다면 
        		 if (reg_pwd.test(this.newPwd) && (this.newPwd === this.newPwd2)) {
        			 //새 비밀번호 유효성 여부를 true 로 변경
        			 this.isNewPwdValid = true;
        		 } else { // 그렇지 않으면
        			 //새 비밀번호 유효성 여부를 false 로 변경 
        			 this.isNewPwdValid = false;
        		 }
        		 this.isNewPwdDirty = true;
        	 },
         },
       });
     </script>
</body>
</html>