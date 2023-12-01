<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>/index.html</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지입니다.</h1>
		<p>컨텍스트 경로: <strong>${pageContext.request.contextPath}</strong></p>
		<ul>
			<li><a href="/Step01Servlet/friend/list">친구 목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list">회원 목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/friend/list.jsp">친구 목록보기(jsp)</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list.jsp">회원 목록보기(jsp)</a></li>
		</ul>
		
		<form action="${pageContext.request.contextPath}/send" method="get">
			<input type="text" name="msg" placeholder="servlet get 방식..." />
			<button type="submit">전송</button>
		</form>
		
		<form action="${pageContext.request.contextPath}/send2" method="post">
			<input type="text" name="msg" placeholder="servlet post 방식..." />
			<button type="submit">전송</button>
		</form>
		
		<form action="${pageContext.request.contextPath}/send.jsp" method="get">
			<input type="text" name="msg" placeholder="jsp get 방식..." />
			<button type="submit">전송</button>
		</form>
		
		<form action="${pageContext.request.contextPath}/send2.jsp" method="post">
			<input type="text" name="msg" placeholder="jsp post 방식..." />
			<button type="submit">전송</button>
		</form>
		
		<p>링크를 눌러도 GET 방식 전송 파라미터를 전달할 수 있다.</p>
		<p>아래의 링크를 눌러보세요</p>
		<ul>
			<li><a href="shop/buy.jsp?num=1&amount=2">1번 상품 2개 구입하기</a></li>
			<li><a href="shop/buy.jsp?num=5&amount=3" target="_blank">5번 상품 3개 구입하기</a></li>
			<li>
				<form action="${pageContext.request.contextPath}/shop/buy.jsp" method="get">
				 	<input type="text" name="num" placeholder="상품번호..."/>
				 	<input type="text" name="amount" placeholder="개수..."/>
			 	<button type="submit">구입</button>
				</form>
			</li>
		</ul>
	</div>
</body>
</html>