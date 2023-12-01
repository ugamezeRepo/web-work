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
	<div class="container">
		<h3 class="form-header mt-4">회원가입 폼입니다.</h3>
		<form action="signup.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id" />
				<small class="form-text">영문자 소문자로 시작하고 5글자~10글자 이내로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="text" name="pwd" id="pwd" />
				<div class="form-text">특수문자를 하나 이상 추가하세요.</div>
				<div class="invalid-feedback">비밀번호를 확인하세요.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="text" id="pwd2" />
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label>
				<input class="form-control" type="text" name="email" id="email" />
			</div>
			<button class="btn btn-primary btn-sm" type="submit">가입</button>
		</form>
	</div>
	
	<script>
		// canSingupButtonToggle
		let tglId = false, tglPwd = false, tglEmail = false;
		const canSignup = () => {
			console.log("얍");
			const toggleBtn = document.querySelector("[type=submit]");
			tglId && tglPwd && tglEmail 
				? toggleBtn.removeAttribute("disabled") 
				: toggleBtn.setAttribute("disabled", "");
		}
		
		// id validation
		const reg_id = /^[a-z].{4,9}$/;
		const id = document.getElementById("id");
		
		id.addEventListener("input", ()=> {			
			if (!reg_id.test(id.value)) {
				id.classList.remove("is-valid");
				id.classList.add("is-invalid");
				tglId = false;
				canSignup();
				return;
			}
			
			fetch("${pageContext.request.contextPath}/user/check_id.jsp?id="+id.value)
			.then (res => res.json())
			.then (data => {
				id.classList.remove("is-valid", "is-invalid");
				
				if (data.canUse) {
					id.classList.add("is-valid");
					tglId = true;
				} else {
					id.classList.add("is-invalid");
					tglId= false;
				}
				canSignup();
			});
		});
		
		// password validation
		const reg_pwd = /[\W]/;
		
		const checkPwd = () => {
			const pwd = document.getElementById("pwd");
			const pwd2 = document.getElementById("pwd2");
			
			pwd.classList.remove("is-valid", "is-invalid");
			if (!reg_pwd.test(pwd.value) || !reg_pwd.test(pwd2.value)) {
				pwd.classList.add("is-invalid");
				tglPwd = false;
				canSignup();
				return;
			}

			if (pwd.value == pwd2.value) {
				pwd.classList.add("is-valid");
				tglPwd = true;
			} else {
				pwd.classList.add("is-invalid");
				tglPwd = false;
			}
			canSignup();
		};
		
		pwd.addEventListener("input", checkPwd);
		pwd2.addEventListener("input", checkPwd);
		
		// eamil validation
		const reg_email=/@/;
		const email = document.getElementById("email");
		
		email.addEventListener("input", (e) => {
			console.log(e);

			email.classList.remove("is-valid", "is-invalid");
			if (reg_email.test(email.value)) {
				email.classList.add("is-valid");
				tglEmail = true;
			} else {
				email.classList.add("is-invalid");
				tglEmail = false;
			}
			canSignup();
		});
	</script>
</body>
</html>