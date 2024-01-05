package com.example.boot09.config;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  // 1. 요청 캐시 객체를 직접 생성해서
  private RequestCache requestCache = new HttpSessionRequestCache();

  // 2. 생성자에서 부모 객체에 전달
  public AuthSuccessHandler() {
    super.setRequestCache(requestCache);
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    // 세션 유지 시간 설정
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(60); // 초단위로 설정

    String userName = authentication.getName();
    System.out.println("로그인된 사용자: " + userName);

    // 3. 로그인 성공 이후 미리 저장된 요청이 있었는 지 읽어와서
    SavedRequest cached = requestCache.getRequest(request, response);

    // 4. 만일 미리 저장된 요청이 없다면 (로그인하지 않는 상태로 인증이 필요한 경로를 요청하지 않아싸면)
    if (cached == null) {
      // 5. 로그인 환영 페이지로 foward 이동
      RequestDispatcher rd = request.getRequestDispatcher("/user/login_success");
      rd.forward(request, response);
    } else {
      super.onAuthenticationSuccess(request, response, authentication);
    }
  }

}
