<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insertform.jsp</title>
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
	   		//어느 페이지에 포함되었는지 정보를 얻어오기
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
	<nav>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="../index.jsp">Home</a></li>
			<li class="breadcrumb-item"><a href="list2.jsp">회원 목록</a></li>
			<li class="breadcrumb-item active">회원 추가</a></li>
	  </ol>
	</nav>
	
	<div class="container">
		<h1>회원 추가양식</h1>
		<form action="${pageContext.request.contextPath}/member/insert.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="name">이름</label>
				<input class="form-control" type="text" name="name" id="name"/>
			</div>
			<div>
				<label class="form-label" for="addr">주소</label>
				<input class="form-control" type="text" name="addr" id="addr"/>
			</div>
			<button class="btn btn-primary" type="submit">추가</button>
		</form>
	</div>
</body>
</html>