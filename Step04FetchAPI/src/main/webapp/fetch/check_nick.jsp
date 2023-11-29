<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /fetch/check_nick.jsp --%>
<%
	// 무슨 방식으로 해볼까
	
	// 일단 GET방식부터
	String nick = request.getParameter("nick");
	System.out.println("nick: " + nick);
	
	// DB에 존재하는 지 확인해서 사용가능한 닉네임인 지 여부를 json 문자열로 응답
	boolean canUse = true;
	if ( nick.equals("kimgura")) {
		canUse = false;
	}
%>
{"canUse": <%=canUse %>}