package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/test/save")
public class SaveServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 폼 전송되는 닉네임을 읽어와서
		req.setCharacterEncoding("utf-8");
		String nick = req.getParameter("nick");
		
		// session scope에 저장하고
		HttpSession session = req.getSession();
		session.setAttribute("nick", nick);
		session.setMaxInactiveInterval(3);
		
		// 응답하기
		//응답 인코딩 설정
		resp.setCharacterEncoding("utf-8");
		//응답 컨텐트 설정
		resp.setContentType("text/html; charset=utf-8");
		//요청을 한 클라이언트에게 문자열을 출력할수 있는 객체의 참조값 얻어내기
		PrintWriter pw=resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p><strong>"+nick+"<strong>이라는 닉네임을 기억하겠습니다.</p>");
		pw.println("<a href='../index.jsp'>인덱스로</a>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
