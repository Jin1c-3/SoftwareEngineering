package com.yutech.back.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token验证拦截器
 */
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String token = request.getHeader("token");
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		if (!StringUtils.isEmpty(token)) {
			String username = JwtUtil.getUsernameByToken(request);
			if (JwtUtil.verify(token, username, userService.getPasswordByUsername(username))) {
				log.info("用户 " + username + " 通过了token验证");
				return true;
			}
			log.info("用户 " + username + " 未通过token验证");
			return false;
		}
		log.warn("返回的token是空字符串");
		return false;
	}
}
