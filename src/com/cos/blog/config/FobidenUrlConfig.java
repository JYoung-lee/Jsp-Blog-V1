package com.cos.blog.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FobidenUrlConfig implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		if(request.getRequestURI().equals("/blog/") || request.getRequestURI().equals("/blog/index.jsp")) {
			chain.doFilter(request, response);
		}else {
			PrintWriter out = response.getWriter();
			out.print("잘못된 접근입니다.");
			out.flush();
		}
		
		
		
	}

}
