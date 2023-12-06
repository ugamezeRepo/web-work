<%@page import="java.io.File"%>
<%@page import="test.file.dao.FileDao"%>
<%@page import="test.file.dto.FileDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	FileDto dto = FileDao.getInstance().getData(num);
	
	// 로그인된 아이디와 글의 작성자가 일치하는 지 확인해서 일치하지 않으면
	String id = (String)session.getAttribute("id");
	if (!dto.getWriter().equals(id)) {
		// 에러 페이지 응답
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "남의 파일 지우면 혼난다!");
		return; // 메서드를 여기서 끝내기 (service() 메서드)
	}
	
	// 1. 파일 시스템에서 실제로 파일 삭제하기
	String path = application.getRealPath("/upload") + File.separator + dto.getSaveFileName();
	File f= new File(path);
	f.delete();
	
	// 2. DB에서 파일 정보 삭제
	FileDao.getInstance().delete(num);
	
	// 3. 응답하기
	String cpath = request.getContextPath();
	response.sendRedirect(cpath+"/file/list.jsp"); // 파일 목록보기로 다시 리다이렉트 이동
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/protected/delete.jsp</title>
</head>
<body>
</body>
</html>