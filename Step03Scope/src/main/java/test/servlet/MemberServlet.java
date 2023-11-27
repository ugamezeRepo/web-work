package test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import text.dto.MemberDto;

@WebServlet("/member")
public class MemberServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 회원 한 명의 정보를 얻어오는 비즈니스 로직 수행
		MemberDto dto = new MemberDto(3, "김혜란", "수지");
		
		// Quiz. 위의 회원 정보를 /test/member.jsp 페이지에서 응답하도록 프로그래밍 해보세요.
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher rd = req.getRequestDispatcher("/test/member.jsp");
		rd.forward(req, resp);		
	}
}
