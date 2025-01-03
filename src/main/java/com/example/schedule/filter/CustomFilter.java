package com.example.schedule.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
@Slf4j

public class CustomFilter implements Filter {
    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

      // Filter에서 수행할 Logic
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      String requestURI = httpRequest.getRequestURI();

      log.info("request URI={}", requestURI);
      // chain 이 없으면 Servlet을 바로 호출
      chain.doFilter(request, response);
    }
  }

