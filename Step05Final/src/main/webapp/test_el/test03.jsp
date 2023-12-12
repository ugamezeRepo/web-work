<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Servlet 페이지에서 작업-->
<% 
	// CafeDto 객체를 생성해서 글 하나의 정보를 담고
	CafeDto dto = new CafeDto();
	dto.setNum(1);
	dto.setWriter("송태정");
	dto.setTitle("오늘의 군것질은?");
	
	// "dto"라는 키값으로 request scope에 담기
	request.setAttribute("dto", dto);
%>

<!-- 응답을 위임 (foward 이동) -->
<!-- Jsp 페이지에서 작업 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_el/test03.jsp</title>
</head>
<body>
	<%
		// request 영역에 "dto"라는 키값으로 담긴 CafeDto 객체의 참조값 얻어와서
		CafeDto result = (CafeDto)request.getAttribute("dto");
	%>
	<%-- CafeDto 객체의 getter 메서드를 이용해 원하는 정보를 얻어와서 출력 --%>
	<p>글번호: <strong><%=result.getNum() %></strong></p>
	<p>작성자: <strong><%=result.getWriter() %></strong></p>
	<p>제목: <strong><%=result.getTitle() %></strong></p>
	
	<h3>EL을 이용해 위와 동일한 작업하기</h3>
	<p>글번호: <strong>${requestScope.dto.getNum() }</strong></p>
	<p>글번호: <strong>${dto.getNum() }</strong></p> <%-- requestScoe. 은 생략 가능 --%>
	<p>글번호: <strong>${dto.num }</strong></p> <%-- getNum() 대신에 필드명만 적어도 된다. --%>
	<p>작성자: <strong>${dto.writer }</strong></p> 
	<p>제목: <strong>${dto.title }</strong></p>
</body>
</html>