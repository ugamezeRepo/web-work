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

import text.dto.MemberDto;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DB에서 읽어온 회목 목록이라고 가정
		// 회원 목록을 담을 ArrayList 객체
		List<MemberDto> list = new ArrayList<>();
		list.add(new MemberDto(1, "송태정", "수서"));
		list.add(new MemberDto(2, "김태준", "양주"));
		list.add(new MemberDto(3, "김혜란", "수지"));
		
		// 회원 목록을 client에게 html 형식으로 응답(table 활용)
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
		pw.println("<title>회원목록 페이지</title>");
		pw.println("</head>");
		pw.println("<body>");
		
		pw.println("<h1>회원 목록입니다.</h1>");
		
		pw.println("<table border='1'>");
			pw.println("<thead>");
				pw.println("<tr>");
					pw.println("<th>번호</th>");
					pw.println("<th>이름</th>");
					pw.println("<th>주소</th>");
				pw.println("</tr>");
			pw.println("</thead>");
		
			list.forEach(item -> {
				pw.println("<tbody>");
					pw.println("<tr>");
						pw.printf("<td> %s </td>", item.getNum());
						pw.printf("<td> %s </td>", item.getName());
						pw.printf("<td> %s </td>", item.getAddr());
					pw.println("</tr>");
				pw.println("</tbody>");
			});
		pw.println("</table>");
		
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}	
}

