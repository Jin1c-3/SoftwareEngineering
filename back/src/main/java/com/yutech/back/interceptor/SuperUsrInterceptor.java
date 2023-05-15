package com.yutech.back.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yutech.back.common.utils.InterceptorUtil;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.service.persistence.SuperUsrService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class SuperUsrInterceptor implements HandlerInterceptor {
	@Autowired
	private SuperUsrService superUsrService;

	@Override
	public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
		log.debug("{}===进入拦截器", request.getRequestURI());
		String token = request.getHeader("token");
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			log.debug("不是映射到方法直接通过拦截器");
			return true;
		}
		if (!StringUtils.isEmpty(token)) {
			String id = JwtUtil.getId(request);
			log.debug(id + "===的token是===" + token);
			if (JwtUtil.verify(token, id, superUsrService.getById(id).getSuperUsrPwd())) {
				log.info(id + "===通过了token验证");
				return true;
			}
			log.info(id + "===未通过token验证");
			InterceptorUtil.createTimeLimitResponse(response);
			return false;
		}
		log.warn("返回的token是空字符串");
		return false;
	}
}
