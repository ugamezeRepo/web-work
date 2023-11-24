<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 폼형식으로 전송되는 삭제할 글의 글번호와 비밀번호를 추출해서
	int num = Integer.parseInt(request.getParameter("num"));
	String pwd = request.getParameter("pwd");
	// GuestDao를 이용하여 글을 삭제하고
	GuestDto dto = new GuestDto();
	dto.setNum(num);
	dto.setPwd(pwd);
	boolean isSuccess = GuestDao.getInstace().delete(dto);
	// 응답한다.
	if (isSuccess) {
		// ContextPath 읽어오기
		String cpath = request.getContextPath();
		
		// jsp 기본 객체인 HttpServletResponse 객체의 sendRedirect() 메서드를 이용하여 응답하기
		// 이 응답은 메서드를 호출하면서 전달한 경로로 웹브라우저에게 다시 요청하라는 응답이다.
		// 이런 응답을 받은 웹브라우저는 해당 경로로 요청을 다시 하게된다. (페이지 이동)
		response.sendRedirect(cpath + "/guest/list.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/delete.jsp</title>
</head>
<body>
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		location.href="${pageContext.request.contextPath}/guest/list.jsp";
	</script>
</body>
</html>