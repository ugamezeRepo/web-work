package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/send")
public class SendServlet extends HttpServlet {
	// service() 메서드에는 2개의 참조값이 매개변수에 전달된다.
	// HttpServletRequest 객체 => 요청과 관련된 작업을 처리할 때 사용한다.
	// HttpServletResponse 객체 => 응답과 관련된 작업을 처리할 때 사용한다.
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// "msg"라는 파라미터명으로 전달되는 문자열을 읽어와서 msg라는 지역변수에 담기
		// 해당 파라미터명으로 전달되는 정보가 없으면 null이 리턴된다.
		String msg = req.getParameter("msg");
		// 콘솔창에 출력해보기
		System.out.printf("msg: %s%n", msg);
		
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
		pw.println("<title>메세지 전송 결과 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		
			pw.println("<p>메세지 잘 받았어, <strong>client</strong>야!</p>");
		
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}	
}
