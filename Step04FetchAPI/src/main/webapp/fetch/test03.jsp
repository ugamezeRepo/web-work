<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test03.jsp</title>
</head>
<body>
	<button id="getBtn">회원정보 가져오기</button>
	<p>번호: <strong id="num"></strong></p>
	<p>이름: <strong id="name"></strong></p>
	<p>주소: <strong id="addr"></strong></p>
	<button id="getBtn2">친구목록 가져오기</button>
	<ul id="friend">
		
	</ul>
	
	<script>
		// 친구목록 가져오기 버튼을 눌렀을 때, get_friend.jsp 페이지로 fetch() 요청을 하고
		// 응답되는 데이터를 이용해 친구 이름을 ul에 li 요소로 목록을 출력해보세요.
		document.querySelector('#getBtn2').addEventListener('click', () => {
			fetch('get_friend.jsp')
			.then(res => res.json())
			.then(data => {
				console.log(data);
				for(item of data) {
					const li = `<li>\${item}</li>`;
					// const li = "<li>" + item + "</li>";
					document.querySelector("#friend").insertAdjacentHTML("beforeend", li);
				}
				/* 위는 배열용 for-of문, 밑은 for-each문
					data.forEach(item => {
						const li = `<li>\${item}</li>`;
						document.querySelector("#friend").insertAdjacentHTML("beforeend", li);
					})
				*/
				
			})
		})
		
	
		// 회원정보 가져오기 버튼을 눌렀을 때, get_member.jsp 페이지로 fetch() 요청을 하고
		// 응답되는 데이터를 이용해 위에 회원의 번호, 이름, 주소를 출력해보세요.
		document.querySelector("#getBtn").addEventListener("click", () => {
			fetch("get_member.jsp")
			.then((res) => res.json())
			.then((data) => {
				console.log(data);
				document.querySelector("#num").innerText = data["num"];
				document.querySelector("#name").innerText = data["name"];
				document.querySelector("#addr").innerText = data["addr"];
				// data는 object이므로 .을 이용해 데이터 참조
				// document.querySelector("#num").innerText = data.num;
				// document.querySelector("#name").innerText = data.name;
				// document.querySelector("#addr").innerText = data.addr;
		    });
		});
    </script>
</body>
</html>