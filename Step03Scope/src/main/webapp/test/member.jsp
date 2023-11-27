<%@page import="text.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDto dto = (MemberDto)request.getAttribute("dto");
	int num = dto.getNum();
	String name = dto.getName();
	String addr = dto.getAddr();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/text/member.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar bg-primary navbar-expand-md" data-bs-theme="dark">
	  <div class="container">
	    <a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">
	      <img src="https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
	      Acorn
	    </a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
	    		data-bs-target="#navbarText">
	     		<span class="navbar-toggler-icon"></span>
	   	</button>
	   	<%
	   		String current=request.getParameter("current"); // "index" or "member" or "guest"
	   	%>
	    <div class="collapse navbar-collapse" id="navbarText">
		   	<ul class="navbar-nav me-auto">
	        	<li class="nav-item">
	          		<a class="nav-link" href="${pageContext.request.contextPath }/member/list2.jsp">회원목록</a>
	        	</li>
	        	<li class="nav-item">
	          		<a class="nav-link" href="${pageContext.request.contextPath }/guest/list.jsp">방명록</a>
	        	</li>
	      	</ul>
	      	<form class="d-flex">
		        <input class="form-control me-2" type="search" placeholder="Search" >
		        <button class="btn btn-info" type="submit">Search</button>
	      	</form>
	      	<span class="navbar-text ms-2">
	      		Made by Udada
	      	</span>
	    </div>
	  </div>
	</nav>

	<div class="container mt-3">
		
			<h2 class="text-info">멤버</h2>
			<p class="text-success"><strong>번호: <%=num %></strong></p>
			<p class="text-success"><strong>이름: <%=name %></strong></p>
			<p class="text-success"><strong>주소: <%=addr %></strong></p>
		
	</div>
</body>
</html>