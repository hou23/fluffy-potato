package com.marshall.spring_security.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author yaojie.hou
 * @create 2018/5/3
 */
public class BeforeLoginFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("This is a filter before UsernamePasswordAuthenticationFilter.");
		// 继续调用 Filter 链
		chain.doFilter(request, response);
	}
}
