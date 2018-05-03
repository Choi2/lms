package com.cafe24.lms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.lms.domain.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		if (auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		if (auth == null) {
			return true;
		}

		// @Auth가 있기 때문에 인증 여부 확인 필요
		HttpSession session = request.getSession();
		User authUser = null;

		if (session != null) {
			authUser = (User) session.getAttribute("authUser");
		}

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// Role 가져오기
		Auth.Role role = auth.value();

		// USER Role 접근이면 인증만 되어있으면 허용
		if (role == Auth.Role.USER) {
			return true;
		}

		// 계정이 admin으로 시도한 경우
		String admin = auth.value().toString();
		if ("ADMIN".equals(admin)) {
			if ("admin@naver.com".equals(authUser.getEmail()) == false) {
				response.sendRedirect(request.getContextPath() + "/user/login");
				return false;
			}
		}

		return true;
	}
}
