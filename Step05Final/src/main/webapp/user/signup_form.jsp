<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container" id="app">
		<h3 class="form-header mt-4">회원가입 폼입니다.</h3>
		<form action="signup.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"
					@blur="onBlur"
					:class="{'is-valid': isIdValid,'is-invalid': !isIdValid}" />
				<small class="form-text">영문자 소문자로 시작하고 5글자~10글자 이내로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"
					@input="onPwdInput"
					v-model="pwd"
					:class="{'is-valid': isPwdValid, 'is-invalid': !isPwdValid}" />
				<div class="form-text">특수문자를 하나 이상 추가하세요.</div>
				<div class="invalid-feedback">비밀번호를 확인하세요.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password" id="pwd2"
					@input="onPwdInput"
					v-model="pwd2" />
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label>
				<input class="form-control" type="text" name="email" id="email"
					@input="onEmailInput"
					:class="{'is-valid': isEmailValid, 'is-invalid': !isEmailValid}" />
			</div>
			<button class="btn btn-primary btn-sm" type="submit">가입</button>
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
	    new Vue({
			el: "#app",
			data: {
				isIdValid: false,
				isPwdValid: false,
				isEmailValid: false,
				pwd,
				pwd2,
			},
			methods: {
				onEmailInput(e) {
					// 이메일을 검증할 정규표현식
					const reg_email=/@/;
					
					const email = e.target.value;
					
					if (reg_email.test(email)) {
						this.isEmailValid = true;
					} else {
						this.isEmailValid = false;
					}
				},
				onPwdInput() {
					// 비밀번호를 검증할 정규표현식 (특수문자 포함여부)
					const reg_pwd = /[\W]/;
					
					if (reg_pwd.test(this.pwd) && (this.pwd === this.pwd2)) {
						this.isPwdValid = true;
					} else {
						this.isPwdValid = false;
					}
				},
				onBlur(e) {
					// 현재까지 입력한 아이디를 읽어온다.
					let inputId = e.target.value;
					// d아이디를 검증할 정규표현식 객체
					const reg_id = /^[a-z].{4,9}$/;
					// 만약 정규표현식을 통과하지 못한다면
					if (!reg_id.test(inputId)) {
						this.isIdValid = false;
						return;
					}
					
					fetch("${pageContext.request.contextPath}/user/check_id.jsp?id="+inputId)
					.then (res => res.json())
					.then (data => {
						if (data.canUse) {
							isIdValid = true;
						} else {
							isIdValid = false;
						}
					});
				},
			},
		});
    </script>
</body>
</html>