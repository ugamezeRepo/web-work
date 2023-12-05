package test.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/user/protected/*", "/shop/*", "/file/protected/*"})
public class LoginFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. 로그인된 클라이언트인 지 확인한다.
		// 부모 type을 자식 type으로 casting
		HttpServletRequest req = (HttpServletRequest)request;
		// 자식 type을 이용해서 HttpSession 객체의 참조값을 얻어낸다.
		HttpSession session = req.getSession();
		// 로그인된 아이디가 있는 지 읽어와본다.
		String id = (String)session.getAttribute("id");
		if (id != null) { // 2. 만일 로그인을 했으면 관여하지 않고 요청의 흐름을 이어간다.
			chain.doFilter(request, response);
		} else { // 3. 로그인을 하지 않았으면 로그인 폼으로 이동할 수 있도록 리다이렉트 응답을 한다.
			/*
			 *  로그인 페이지로 강제 리다이렉트 됐다면 
			 *  로그인 성공 후 원래 가려던 목적지로 다시 보내야 하고
			 *  GET 방식 전송 파라미터가 있다면 파라미터 정보도 같이 가지고갈 수 있도록 해야한다.
			 */
			//원래 가려던 url 정보 읽어오기
			String url=req.getRequestURI();
			//GET 방식 전송 파라미터를 query 문자열로 읽어오기 ( a=xxx&b=xxx&c=xxx )
			String query=req.getQueryString();
			//특수 문자는 인코딩을 해야한다.
			String encodedUrl=null;
			if(query==null) {//전송 파라미터가 없다면 
				encodedUrl=URLEncoder.encode(url);
			}else {
				// 원래 목적지가 /test/xxx.jsp 라고 가정하면 아래와 같은 형식의 문자열을 만든다.
				// "/test/xxx.jsp?a=xxx&b=xxx ..."
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}
			
			// 절대 경로 이동을 위해 context path도 읽어온다.
			String cPath = req.getContextPath();
			// ServletResponse type을 HttpServletResponse type으로 casting			
			HttpServletResponse res = (HttpServletResponse)response;
			// 리다이렉트 응답(새로운 경로로 요청을 다시하라고 응답)
			res.sendRedirect(cPath+"/user/login_form.jsp?url="+encodedUrl);
		}	
	}
}
