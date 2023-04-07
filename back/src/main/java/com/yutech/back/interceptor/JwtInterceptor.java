package com.yutech.back.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yutech.back.common.utils.JwtUtil;
import com.yutech.back.mapper.po.UsrMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
	private UsrMapper usrMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
		String token = request.getHeader("token");
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			log.debug("不是映射到方法直接通过拦截器");
			return true;
		}
		if (!StringUtils.isEmpty(token)) {
			String usrId = JwtUtil.getUsrIdByToken(request);
			if (JwtUtil.verify(token, usrId, usrMapper.selectById(usrId).getUsrPwd())) {
				log.info("用户 " + usrId + " 通过了token验证");
				return true;
			}
			log.info("用户 " + usrId + " 未通过token验证");
			return false;
		}
		log.warn("返回的token是空字符串");
		return false;
	}
}
