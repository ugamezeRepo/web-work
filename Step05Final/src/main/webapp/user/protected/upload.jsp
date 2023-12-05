<%@page import="test.file.dto.FileDto"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String realPath = application.getRealPath("/upload");
	File f = new File(realPath);
	if (!f.exists()) { 
		f.mkdir(); 
	}
	MultipartRequest mr = new MultipartRequest(
		request, 
		realPath,
		1024*1024*100, 
		"utf-8", 
		new DefaultFileRenamePolicy() 
	);
	String title = mr.getParameter("title");
	String orgFileName = mr.getOriginalFileName("myImage");
	String saveFileName = mr.getFilesystemName("myImage");
	long fileSize = mr.getFile("myImage").length();
	
	// 로그인된 아이디(작성자)
	String writer=(String)session.getAttribute("id");
	
	// DB에 저장할 정보를 FileDto에 담는다.
	FileDto dto = new FileDto();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setOrgFileName(orgFileName);
	dto.setSaveFileName(saveFileName);
	dto.setFileSize(fileSize);
	
	// FileDao를 이용해서 DB에 업로드된 파일의 정보를 저장하고
	
	
	// 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/protected/upload.jsp</title>
</head>
<body>

</body>
</html>