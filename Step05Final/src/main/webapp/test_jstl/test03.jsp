<%@page import="test.file.dto.FileDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// Servlet 단에서 담은 sample 데이터라고 가정하자
	List<FileDto> list = new ArrayList<>();

	FileDto dto1=new FileDto();
	dto1.setNum(1);
	dto1.setWriter("송태정");
	dto1.setTitle("과자");
	
	FileDto dto2 = new FileDto();
	dto2.setNum(2);
	dto2.setWriter("김태준");
	dto2.setTitle("젤리");
	
	FileDto dto3 = new FileDto();
	dto3.setNum(3);
	dto3.setWriter("김혜란");
	dto3.setTitle("커피");
	
	list.add(dto1);
	list.add(dto2);
	list.add(dto3);
	// EL로 추출할 수 있도록 request 영역에 담는다. 
	request.setAttribute("list", list);
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test_jstl/test03.jsp</title>
</head>
<body>
	<h1>파일 목록</h1>
	
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tmp" items="${list }">
				<tr>
					<th>${tmp.num }</th>
					<th>${tmp.writer }</th>
					<th>${tmp.title }</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>