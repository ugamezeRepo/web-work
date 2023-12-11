<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form2.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container" id="app">
		<h3>회원가입 폼 입니다</h3>
		<form action="signup.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"
					@blur="onBlur"
					:class="{'is-valid':isIdValid, 'is-invalid': !isIdValid && isIdDirty}"/>
				<small class="form-text">영문자 소문자로 시작하고 5글자~10글자 이내로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디 입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"
					@input="onPwdInput"
					v-model="pwd"
					:class="{'is-valid':isPwdValid, 'is-invalid':!isPwdValid && isPwdDirty}"/>
				<small class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password"  id="pwd2"
					@input="onPwdInput"
					v-model="pwd2"/>
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label>
				<input class="form-control" type="email" name="email" id="email"
					@input="onEmailInput"
					:class="{'is-valid':isEmailValid, 'is-invalid':!isEmailValid && isEmailDirty}"/>
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요.</div>
			</div>
			<button class="btn btn-primary btn-sm" type="submit" 
				:disabled="!isIdValid || !isPwdValid || !isEmailValid">가입</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script>
		new Vue({
			el:"#app",
			data:{
				isIdValid:false,
				isPwdValid:false,
				isEmailValid:false,
				pwd:"",
				pwd2:"",
				isIdDirty: false,
				isPwdDirty: false,
				isEmailDirty: false,
			},
			methods:{
				onEmailInput(e){
					//이메일을 검증할 정규 표현식
					const reg_email=/@/;
					//입력한 문자열 읽어오기  (e.target 은 이벤트가 발생한 바로 그 요소의 참조값이다)
					const email=e.target.value;
					if(reg_email.test(email)){
						this.isEmailValid=true;
					}else{
						this.isEmailvalid=false;
					}
					this.isEmailDirty = true;
				},
				onPwdInput(){
					//비밀 번호를 검증할 정규 표현식(특수문자 포함여부)
					const reg_pwd=/[\W]/;
					if(reg_pwd.test(this.pwd) && (this.pwd === this.pwd2)){
						this.isPwdValid=true;
					}else{
						this.isPwdValid=false;
					}
					this.isPwdDirty = true;
				},
				onBlur(e){
					//현재까지 입력한 아이디를 읽어온다.
					let inputId=e.target.value;
					//아이디를 검증할 정규표현식 객체 
					const reg_id=/^[a-z].{4,9}$/;
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
					this.isIdDirty = true;
				},
			}
		});
	</script>
</body>
</html>




