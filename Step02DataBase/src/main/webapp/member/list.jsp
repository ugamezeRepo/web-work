<%@page import="test.util.DbcpBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 아래의 table에 출력할 회원목록 얻어오기
	List<MemberDto> list = new ArrayList<> ();
	MemberDto dto = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		// DbcpBean() 객체를 이용해서 Connection 객체 하나 얻어내기(Connection pool에서 하나 꺼내오기)
		conn = new DbcpBean().getConn();
		String sql = "SELECT num, name, addr" +
				" FROM member" +
				" ORDER BY num DESC";
		pstmt = conn.prepareStatement(sql);
		// query문 수행하고 결과(ResultSet) 얻어내기
		rs = pstmt.executeQuery();
		
		// 반복문 돌면서
		while (rs.next()) {
			// MemberDto 객체에 각 회원의 정보를 담아
			int num = rs.getInt("num");
			String name = rs.getString("name");
            String addr = rs.getString("addr");
            dto = new MemberDto(num, name, addr);
            // ArrayList 객체에 누적
            list.add(dto);
		}
	} catch (Exception e) {
        e.printStackTrace();
    } finally {
    	try {
    		if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            // Connection 객체의 close() 메서드 호출 시 Pool에 반납
            if (conn != null) conn.close();
    	} catch ( Exception e) {
    		e.printStackTrace();
    	}
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/list.jsp</title>
<style type="text/css">
	td {
		text-align: center;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>회원 목록입니다.</h1>
		
		<table border="1" aligb="center">
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody>
			<%for(MemberDto tmp:list) { %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getAddr() %></td>
				</tr>
			<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>