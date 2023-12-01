<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// /friend/list
	// 위의 요청을 처리할 servlet을 만들어 servlet에서 응답해야한다.
	// @WebServlet("/friend/list") 경로로 맵핑된 Servlet 클래스를 만들어
	// 요청처리를 하면된다.
	
	// /frined/list.jsp
	// 위의 요청경로에 맞게 jsp 페이지를 위치시키면 된다.
	// webapp에 friend라는 폴더를 만들고
	// 그 안에 list.jsp 페이지를 만들어 jsp 페이지에서 요청처리를 하면 된다.
%>       
<%
	// DB에서 불러온 데이터라고 가정하자
	List<String> names = new ArrayList<>();
	names.add("송태정");
	names.add("김태준");
	names.add("김혜란");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/friend/list.jsp</title>
</head>
<body>
	<h1>친구 목록입니다.</h1>
	<ul>
		<%for (String tmp: names) {%>
			<li><%out.print(tmp); %></li>
		<%} %>
	</ul>
	
	<ol>
		<%for (String tmp: names) {%>
			<li><%=tmp %></li>
		<%} %>
	</ol>
</body>
</html>