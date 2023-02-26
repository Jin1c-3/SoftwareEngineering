package com.yutech.back.common.config;

import com.yutech.back.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置项
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/**/login",
						"/**/login.html",
						"/**/register",
						"/**/register.html",
						"/**/*.js",
						"/**/*.css",
						"/**/*.woff",
						"/**/*.ttf",
						"/**/*.jpg",
						"/css/**",
						"/js/**",
						"/img/**",
						"/media/**",
						"/vendors/**",
						"/avatar/**",
						"/download/**");
	}

	@Bean
	public JwtInterceptor authenticationInterceptor() {
		return new JwtInterceptor();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
				.maxAge(60 * 60 * 24);
	}
}
