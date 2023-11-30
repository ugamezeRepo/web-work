<%-- fetch() 전 유효성 검증 기능 추가하기 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/form_validation.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>폼 유효성 검증 style 테스트</h1>
		<form action="signup.jsp" method="post">
			<div>
				<label class="form-label" for="nick">닉네임</label>
				<input class="form-control" type="text" name="nick" id="nick" />
				<div>영문자 대소문자만 가능합니다.</div>
				<div class="invalid-feedback">사용할 수 없는 닉네임입니다.</div>
				<div class="valid-feedback">사용 가능한 닉네임입니다.</div>
			</div>
			<div>
				<label class="mt-3" for="pwd">비밀번호</label>
				<input class="form-control" type="text" name="pwd" id="pwd" />
				<div class="invalid-feedback">비밀번호를 확인하세요</div>
			</div>
			<div>
				<label class="mt-3" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="text" id="pwd2" />
			</div>
			<button class="btn btn-primary mt-3" type="submit" disabled>가입</button>
		</form>
	</div>
	<script>
		let isNickValid = false, isPwdValid = false;
		
		/*
			1. 닉네임을 입력했을 때 유효성 여부를 변수에 저장한다.
			2. 비밀번호를 입력했을 때 유효성 여부를 변수에 저장한다.
			3. 두 변수에 있는 값이 모두 true일 때만 가입 버튼의 disabled 속성을 제거하고,
				나머지 경우에는 disabled 속성을 추가하는 함수를 미리 만들어두고
			4. 적절한 시점에 그 함수를 호출하게 하면 된다.
		*/
		const canSignup = () => {
			const toggleBtn = document.querySelector("[type=submit]");
			isNickValid && isPwdValid ? toggleBtn.removeAttribute("disabled") : toggleBtn.setAttribute("disabled", "");
		}
		
		// fetch() 전 유효성 검증
		const reg_nick=/^[a-zA-Z]+$/;
		
		// 닉네임을 입력했을 때 실행할 함수 등록
		document.querySelector("#nick").addEventListener("input", ()=> {
			const selectNick = document.querySelector("#nick");
			// 현재까지 입력한 닉네임을 읽어온다.
			let inputNick = selectNick.value;

			if (!reg_nick.test(inputNick)) {
				selectNick.classList.remove("is-valid");
				selectNick.classList.add("is-invalid");
				canSignup();
				isNickValid = false;
				return;
			}
			// fetch() 함수를 이용해서 get 방식으로 입력한 닉네임 보내고 사용가능 여부를 json으로 응답받는다.
			fetch("${pageContext.request.contextPath}/fetch/check_nick.jsp?nick="+inputNick)
			.then(res => res.json())
			.then(data => {
				console.log(data);
				// 일단 클래스를 제거한 후에
				selectNick.classList.remove("is-valid", "is-invalid");
				
				// data는 {canUse: true} or {canUse: false} 형태의 object이다.
				if (data.canUse) {
					selectNick.classList.add("is-valid");
					isNickValid = true;
				} else {
					selectNick.classList.add("is-invalid");
					isNickValid = false;
				}
				canSignup();
			});
		});
		
		// 함수를 미리 만들어서
		const chkPwd = () => {
			// 양 쪽에 입력한 비밀번호를 읽어와서
			let inputPwd = selectPwd.value;
			let inputPwd2 = selectPwd2.value;
			
			// 양 쪽을 같게 입력하면 is-valid를 추가하고, 그렇지 않으면 is-invalid를 추가한다.
			selectPwd.classList.remove("is-valid", "is-invalid");
			if (inputPwd == inputPwd2) {
				selectPwd.classList.add("is-valid");
				isPwdValid = true;
			} else {
				selectPwd.classList.add("is-invalid");
				isPwdValid = false;
			}
			canSignup();
		}
		
		const selectPwd = document.querySelector("#pwd");
		const selectPwd2 = document.querySelector("#pwd2");
		selectPwd.addEventListener("input", chkPwd);
		selectPwd2.addEventListener("input", chkPwd);
		
	</script>
</body>
</html>