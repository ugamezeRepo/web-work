<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test04.jsp</title>
</head>
<body>
	<h1>폼에 입력한 내용을 페이지 전환없이 전송하기</h1>
	<form action="login.jsp" method="post" id="myForm">
		<input type="text" name="id" placeholder="아이디 입력..." />
		<input type="text" name="pwd" placeholder="비밀번호 입력..." />
		<button type="submit">로그인</button>
	</form>
	
	<script>
		// 폼 안에 있는 제출버튼(submit)을 클릭하면 폼에는 submit이라는 이벤트가 발생한다.
		document.querySelector("#myForm").addEventListener("submit", (event) => {
			alert("카드값이 2배가 넘게 나왔네?? 어쩌냐");
			// 함수에 전달되는 event 객체의 preventDefault() 함수를 호출하면 폼 제출이 방지된다.
			event.preventDefault();
			// 폼에 입력한 내용을 직접 읽어와서 fetch() 함수를 이용해서 원하는 페이지로 전송을 해야한다.
		});
	</script>
</body>
</html>