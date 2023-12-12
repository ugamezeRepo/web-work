<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// String currentPage = request.getParameter("current");
%>
<nav class="navbar bg-primary navbar-expand-md" data-bs-theme="dark">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">
      <img src="https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
      Acorn
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
    	data-bs-target="#navbarText"
    >
     	<span class="navbar-toggler-icon"></span>
   	</button>

    <div class="collapse navbar-collapse" id="navbarText">
	   	<ul class="navbar-nav me-auto">
        	<li class="nav-item">
          		<a class="nav-link ${param.current eq 'file' ? 'active' : '' }" href="${pageContext.request.contextPath }/file/list.jsp">자료실</a>
        	</li>
        	<li class="nav-item">
          		<a class="nav-link ${param.current eq 'cafe' ? 'active' : '' }" href="${pageContext.request.contextPath }/cafe/list.jsp">게시판</a>
        	</li>
      	</ul>
      	
      	<%
      		// 로그인 여부 확인을 위해 session 영역에서 id를 읽어온다.
      		String id = (String)session.getAttribute("id");
      	%>
      	<div class="navbar-nav">
      		<c:choose>
      			<c:when test="${not empty id }">
	      			<a class="nav-link" href="${pageContext.request.contextPath}/user/protected/info.jsp"><%=id %>님</a>
	      			<a class="nav-link" href="${pageContext.request.contextPath}/user/logout.jsp">로그아웃</a>
      			</c:when>
      			<c:otherwise>
	      			<a class="nav-link" href="${pageContext.request.contextPath}/user/login_form.jsp">로그인</a>
		      		<a class="nav-link" href="${pageContext.request.contextPath}/user/signup_form.jsp">회원가입</a>
      			</c:otherwise>
      		</c:choose>
      	</div>
    </div>
  </div>
</nav>