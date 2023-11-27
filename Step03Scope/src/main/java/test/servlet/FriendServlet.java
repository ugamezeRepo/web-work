package test.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import text.dto.MemberDto;

/**
 * 	/friend/list 요청이 왔을 때, 요청을 처리할 서블릿을 만들어보세요.
 * - 친구 이름은 3개입니다. "송태정" "김태준" "김혜란"
 * - 친구 이름을 출력해줄 jsp 페이지는 /test/friend.jsp 페이지입니다.
 * - 친구 이름을 ul, li 요소를 이용해서 목록을 출력해보세요.
 */
@WebServlet("/friend/list")
public class FriendServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DB에서 읽어온 데이터(모델)라고 가정
//		List<MemberDto> list = new ArrayList<> ();
//		list.add(new MemberDto(1, "송태정", "수서"));
//		list.add(new MemberDto(2, "김태준", "양주"));
//		list.add(new MemberDto(3, "김혜란", "수지"));
		List<String> list = new ArrayList<String>();
		list.add("오영찬");
		list.add("이승우");
		list.add("이 담");
		
		// "list"라는 키값으로 List<String< type을 request scope에 담기
		req.setAttribute("list", list);
		// 요청 전달자 객체를 얻어내서
		RequestDispatcher rd = req.getRequestDispatcher("/test/friend.jsp");
		// jsp 페이지(view page)로 forward 이동해서 응답
		rd.forward(req, resp);
	}
}
