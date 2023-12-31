<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 한 페이지에 몇 개씩 표시할 것인 지
	final int PAGE_ROW_COUNT = 4;
	// 하단 페이지를 몇 개씩 표시할 건인 지
	final int PAGE_DISPLAY_COUNT = 5;

	// 보여줄 페이지의 번호를 일단 1이라고 초기값 설정
	int pageNum = 1;
	
	// 페이지 번호가 파라미터로 전달되는 지 읽어와본다.
	String strPageNum = request.getParameter("pageNum");
	// 만일 페이지 번호가 파라미터로 넘어온다면
	if (strPageNum != null) {
		// 숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
		pageNum = Integer.parseInt(strPageNum);
	}
	
	// 보여줄 페이지의 시작 ROWNUM
	int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
	// 보여줄 페이지의 끝 ROWNUM
	int endRowNum = pageNum * PAGE_ROW_COUNT;
	
	// 하단 시작 페이지 번호 
	int startPageNum = 1 + ((pageNum-1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
	// 하단 끝 페이지 번호
	int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
	// 전체 글의 개수
	int totalRow = CafeDao.getInstance().getCount();
	// 전체 페이지의 개수 구하기
	int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
	// 끝 페이지 번호가 이미 전체 페이지 개수보다 크게 계산되었다면 잘못된 값이다.
	if(endPageNum > totalPageCount){
		endPageNum = totalPageCount; //보정해 준다. 
	}
	
	//CafeDto 객체를 생성해서 
	CafeDto dto=new CafeDto();
	//위에서 계산된 startRowNum 과 endRowNum 을 담고
	dto.setStartRowNum(startRowNum);
	dto.setEndRowNum(endRowNum);
	
	// 파일 전체 목록 읽어오기
	// List<CafeDto> list = CafeDao.getInstance().getList();
	// 보여줄 페이지에 맞는 목록만 얻어오기
	List<CafeDto> list = CafeDao.getInstance().getList(dto);
	
	// 로그인된 사용자 읽어오기(로그인 되지 않았다면 null)
	String id = (String)session.getAttribute("id");
	
	request.setAttribute("list", list);
	request.setAttribute("startPageNum", startPageNum);
	request.setAttribute("endPageNum", endPageNum);
	request.setAttribute("totalPageCount", totalPageCount);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    .page-list {
        display: flex;
        margin: 20px 0;
        padding: 0;
        list-style-type: none;
        justify-content: center;
    }

    .page-list li {
        margin: 0 5px;
    }

    .page-list li a {
        display: block;
        padding: 10px 15px;
        border: 1px solid #3498db;
        background-color: #3498db;
        color: #fff;
        text-align: center;
        text-decoration: none;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .page-list li a:hover,
    .page-list li.active a {
        background-color: #297fb8;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 12px;
        text-align: center;
    }

    th {
        background-color: #131313;
        color: #fff;
    }

    table a {
        color: #3498db;
        text-decoration: none;
    }

    table a:hover {
        text-decoration: underline;
    }

    .delete-link {
        color: #e74c3c;
        cursor: pointer;
    }
</style>
</head>
<body>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="cafe" name="current" />
	</jsp:include>
	
	<div class="container">
		<h1>카페글 목록입니다.</h1>
		<a href="${pageContext.request.contextPath}/cafe/protected/insert_form.jsp">작성</a>
		<table border="1" cellpadding="5">
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list }">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.writer }</td>
						<td>
							<a href="${pageContext.request.contextPath}/cafe/detail.jsp?num=${tmp.num }">${tmp.title }</a>
						</td>
						<td>${tmp.viewCount }</td>
						<td>${tmp.regdate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 페이징 UI -->
		<ul class="page-list">
			<c:if test="${startPageNum ne 1 }">
				<li><a href="list.jsp?pageNum=${startPageNum - 1 }">Prev</a></li>
			</c:if>
			
			<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
				<li ${i eq param.pageNum ? 'class="active"' : ''}>
					<a href="list.jsp?pageNum=${i }">${i }</a>
				</li>
			</c:forEach>
			
			<%--
				마지막 페이지 번호가 전체 페이지의 개수보다 작으면 Next 링크를 제공한다.
			--%>
			<c:if test="${endPageNum lt totalPageCount }">
				<li><a href="list.jsp?pageNum=<%=endPageNum+1 %>">Next</a></li>
			</c:if>
		</ul>
	</div>
	
	<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>
