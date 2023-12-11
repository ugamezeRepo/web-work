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
					@input="onIdInput"
					
					v-bind:class="{'is-invalid': !isIdValid && isIdDirty, 'is-valid': isIdValid}" />
				<small class="form-text">영문자 소문자로 시작하고 5글자~10글자 이내로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="text" name="pwd" id="pwd"
					@input="onPwdInput" v-model="pwd"
					v-bind:class="{'is-invalid': !isPwdValid && isPwdDirty, 'is-valid': isPwdValid}" />
				<div class="form-text">특수문자를 하나 이상 추가하세요.</div>
				<div class="invalid-feedback">비밀번호를 확인하세요.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="text" id="pwd2"
					@input="onPwdInput" v-model="pwd2" />
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label>
				<input class="form-control" type="text" name="email" id="email"
					@input="onEmailInput"
					
					v-bind:class="{'is-invalid': !isEmailValid && isEmailDirty, 'is-valid': isEmailValid}" />
			</div>
			<button class="btn btn-primary btn-sm" type="submit">가입</button>
		</form>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
		new Vue({
			el:"#app",
			data:{
				isIdValid:false,
				isPwdValid:false,
				isEmailValid:false
			},
			methods:{
				onBlur(e){
					//현재까지 입력한 아이디를 읽어온다.
					let inputId=e.target.value;
					//만일 정규 표현식을 통과 하지 못했다면 
					if(!reg_id.test(inputId)){
						//사용할수 없는 아이디라는 의미에서 모델에 false 를 넣어준다.
						this.isIdValid=false;
						return;//함수를 여기서 종료해라 
					}
					//fetch() 함수를 이용해서 get 방식으로 입력한 아이디를 보내고 사용가능 여부를 json 으로 응답받는다.
					fetch("${pageContext.request.contextPath}/user/check_id.jsp?id="+inputId)
					.then(res=>res.json())
					.then(data=>{
						//data 는 {canUse:true} or {canUse:false} 형태의 object 이다.
						if(data.canUse){
							//사용할수 있는 아이디라는 의미에서 true 를 넣어준다.
							this.isIdValid=true;
						}else{
							//사용할수 없는 아이디라는 의미에서 false 를 넣어준다.
							this.isIdValid=false;
						}
					});
				}
			}
		});
    </script>
    <%--
	<script>
		// canSingupButtonToggle
		let tglId = false, tglPwd = false, tglEmail = false;
		const canSignup = () => {
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
		
		// email validation
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
	--%>
</body>
</html>