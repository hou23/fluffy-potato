package com.marshall.web.socket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Created by yaojie.hou on 2017/12/28.
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
			Map<String, String[]> parameterMap = serverHttpRequest.getServletRequest().getParameterMap();
			String queryString = serverHttpRequest.getServletRequest().getQueryString();
			System.out.println(queryString);
			System.out.println(parameterMap);
		}
		//String name = request.getPrincipal().getName();
		//attributes.put("userId", name);
		//System.out.println("name: " + name);
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

	}
}
