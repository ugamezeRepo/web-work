package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 3. 어떤 경로 요청에 대해 이 서블릿이 동작하게 할 것인 지 설정
@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet{ // 1. 상속
	// 2. service() 메서드 오버라이드
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("오잉?");
		
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
		pw.println("<title>오늘의 운세 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		// 오늘의 운세 5개를 미리 준비해둔다.
		String[] fortunes= {"반복돼 오늘도 이렇게",
				"힘겨운 스물네 시간",
				"반복돼 오늘도 머릿속엔",
				"온통 너라는 사람",
				"또 반복해 오늘도 너에게",
				"하지 못한 말",
				"수없이 반복해 나도 모르게",
				"너와 함께 듣던 이 노래"};
		
		int value = new Random().nextInt(fortunes.length);
		String sentence = fortunes[value];
		pw.println("<p> 오늘의 운세: <strong>" + sentence + "</strong></p>");
		pw.println("</body>");
		pw.println("</html>");

		// 요청을 한 클라이언트에게 문자열을 출력할 수 있는 객체의 참조값 얻어내기
		pw.println("fire friday!!");
		
		pw.close();
	}	
}
