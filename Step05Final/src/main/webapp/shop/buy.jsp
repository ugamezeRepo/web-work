<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/shop/buy.jsp</title>
</head>
<body>
	<%
		String id = (String)session.getAttribute("id");
		String code = request.getParameter("code");
		String amount = request.getParameter("amount");
	%>
	
	<div class="container">
		<p>
			<strong><%=id %></strong>님
			<strong><%=code %></strong>번 상품
			<strong><%=amount %></strong>을(를) 구입했습니다.
		</p>
	</div>
</body>
</html>