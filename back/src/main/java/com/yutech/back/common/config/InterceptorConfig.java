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
<<<<<<< HEAD
				//这里配置要拦截的路径
				.addPathPatterns("/**")
				//这里配置不要拦截的路径
=======
				.addPathPatterns("/**")
>>>>>>> 6aeb4a1e6db15a6f65b781cad5d4b29a7c0e77d4
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
<<<<<<< HEAD
						"/download/**",
						"/test/**");
=======
						"/download/**");
>>>>>>> 6aeb4a1e6db15a6f65b781cad5d4b29a7c0e77d4
	}

	@Bean
	public JwtInterceptor authenticationInterceptor() {
		return new JwtInterceptor();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
<<<<<<< HEAD
				.allowedOriginPatterns("*")
				.allowCredentials(true)
				.allowedMethods("*")
=======
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS", "HEAD")
>>>>>>> 6aeb4a1e6db15a6f65b781cad5d4b29a7c0e77d4
				.maxAge(60 * 60 * 24);
	}
}
