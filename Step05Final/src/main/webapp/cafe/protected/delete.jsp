<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	CafeDto dto = CafeDao.getInstance().getData(num);

	// 로그인된 아이디와 글의 작성자가 일치하는 지 확인해서 일치하지 않으면
	String id = (String)session.getAttribute("id");
	if (!dto.getWriter().equals(id)) {
		// 에러 페이지 응답
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "글을 삭제할 권한이 없는 계정입니다.");
		return; // 메서드를 여기서 끝내기 (service() 메서드)
	}
	
	Boolean isSuccess = CafeDao.getInstance().delete(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/delete.jsp</title>
</head>
<body>
	<script>
		const isSuccess = <%=isSuccess %>
		if (isSuccess) {
			alert("삭제 되었습니다.");
			location.href = "${pageContext.request.contextPath}/cafe/list.jsp";
		} else {
			alert("실패했습니다.");
		}
	</script>
</body>
</html>