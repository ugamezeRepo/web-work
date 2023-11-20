package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/friends")
public class FriendsServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		// 응답 인코딩 설정
		resp.setCharacterEncoding("utf-8");
		// 응답 컨텐츠 설정
		resp.setContentType("text/html; charset=utf-8");
		// 요청을 한 클라이언트에게 문자열을 출력할 수 있는 객체의 참조값 얻어내기
		PrintWriter pw = resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>친구 목록</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		// 클라이언트에게 응답할 친구의 목록이 List 객체에 들어있다고 가정하자
		List<String> list = new ArrayList<>();
		list.add("후쿠오카");
		list.add("에버랜드");
		list.add("롯데월드");
		
		pw.println("<h2> 놀러갈 곳 </h2>");
		pw.println("<ul>");
		// 여기에서 li 요소를 이용해 친구 목록 응답.
		list.forEach(item -> pw.println("<li>"+ item +"</li>"));
		pw.println("</ul>");
		
		pw.println("<h3> 칭구 </h3>");
		pw.println("<ul>");
		pw.println("<li>송태정</li>");
		pw.println("<li>김태준</li>");
		pw.println("<li>김혜란</li>");
		pw.println("</ul>");
		pw.println("</body>");
		pw.println("</html>");
		
		pw.close();
	}	
}
