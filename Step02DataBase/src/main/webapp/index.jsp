<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<%-- 
		/include/navbar.jsp 포함시키기 
		
		피 포함되는 jsp 페이지에 파라밑를 전달할 수 있다.
		아래 코드는 current라는 파라미터명으로 index라는 문자열을 전달한다.
		따라서, navbar.jsp 페이지에서 해당 문자열을 아래와 같이 추출할 수 있다.
		String result = request.getParameter("current");
	--%>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="index" name="current"/>
	</jsp:include>

	<div class="container">
		<div id="carouselExample" class="carousel slide mt-4 mb-4">
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="images/top01.jpg" class="d-block w-100" alt="...">
		    </div>
		    <div class="carousel-item">
		      <img src="images/top02.jpg" class="d-block w-100" alt="...">
		    </div>
		    <div class="carousel-item">
		      <img src="images/top03.jpg" class="d-block w-100" alt="...">
		    </div>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
		  </button>
		</div>
		
		<ul>
			<li><a href="test.jsp">Connection 객체 잘 얻어오는 지 확인하기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list.jsp">회원 목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list2.jsp">회원 목록보기2</a></li>
			<li><a href="${pageContext.request.contextPath}/dept/list.jsp">부서 목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/guest/list.jsp">방명록 목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/music/list.jsp">음악 리스트</a></li>
		</ul>
	</div>	
	
	<%-- 
		webapp/include/navbar.jsp 페이지에게 이 부분만 응답하도록 한다. 
		page="경로"
		여기서 경로는 client가 요청하는 경로가 아니므로 context path를 출력하면 안 된다.
		page="/"는 webapp 폴더를 가리킨다.
	--%>
	<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>