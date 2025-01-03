package com.example.schedule.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginFilter implements Filter {

  private static final String[] WHITE_LIST = {"/", "/user/signup", "/login", "/logout"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();

    HttpServletResponse httpResponse = (HttpServletResponse) response;

    log.info("로그인 필터 로직 실행");

    if (!isWhiteList(requestURI)) {

      HttpSession session = httpRequest.getSession(false);

      if (session == null || session.getAttribute("sessionKey") == null) {
        throw new RuntimeException("로그인 해주세요");

      }
      log.info("로그인에 성공했습니다.");
    }

    chain.doFilter(request, response);

  }


  private boolean isWhiteList(String requestURI) {
    return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
  }

}