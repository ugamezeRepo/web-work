/* 요청을 중간에 가로채서 encoding 설정을 해주는 Filter 만들기 */

package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// 이 context에 들어오는 모든 요청에 대해 필터링을 수행한겠다는 의미
@WebFilter("/*") // 3.
public class EncodingFilter implements Filter{ // 1.
	// 2.
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("EncodingFilter 동작...");
		// 1. 만일 인코딩 설정이 되지 않았다면
		if (request.getCharacterEncoding() == null) {
			// POST 방식으로 전송했을 때 한글 깨짐 방지
			request.setCharacterEncoding("utf-8");
		}
		// 2. 요청의 흐름 이어가기
		chain.doFilter(request, response);
	}
	
}
