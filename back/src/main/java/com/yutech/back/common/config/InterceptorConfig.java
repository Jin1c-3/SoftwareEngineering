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
				//这里配置要拦截的路径
				.addPathPatterns("/**")
				//这里配置不要拦截的路径
				.excludePathPatterns("/**/login",
						"/**/login.html",
						"/**/registry",
						"/**/registry.html",
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
						"/download/**",
						"/test/**",
						"/swagger-ui.html/**",
						"/swagger-ui.html#/**",
						"/swagger-resources/**",
						"/**/super-usr/**",
						"/**/webjars/**",
						"/**/v3/**");
	}

	@Bean
	public JwtInterceptor authenticationInterceptor() {
		return new JwtInterceptor();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOriginPatterns("*")
				.allowCredentials(true)
				.allowedMethods("*")
				.maxAge(60 * 60 * 24);
	}
}
