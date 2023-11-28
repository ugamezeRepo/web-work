<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// GET방식 요청 파라미터 읽어오기
	String msg = request.getParameter("msg");
	System.out.println("msg:" + msg);
%>
<%-- 오류난 경우 --%> 
{"isSuccess": true, "toClient": "메세지 잘 받았어 C야"};