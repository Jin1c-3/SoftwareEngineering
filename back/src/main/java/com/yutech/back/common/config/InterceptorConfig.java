package com.yutech.back.common.config;

import com.yutech.back.interceptor.ServiceProviderInterceptor;
import com.yutech.back.interceptor.SuperUsrInterceptor;
import com.yutech.back.interceptor.UsrInterceptor;
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

	/**
	 * 任何拦截器都不拦截的路径
	 */
	private final String[] EXCLUDE_PATH_PATTERN = {
			"/**/login",
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
			"/**/webjars/**",
			"/**/v3/**",
			"/**/before-update/**",
	};

	/**
	 * 注入拦截器，交给spring管理
	 */
	@Bean
	public UsrInterceptor getUsrInterceptor() {
		return new UsrInterceptor();
	}
	@Bean
	public SuperUsrInterceptor getSuperUsrInterceptor() {
		return new SuperUsrInterceptor();
	}
	@Bean
	public ServiceProviderInterceptor getServiceProviderInterceptor() {
		return new ServiceProviderInterceptor();
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(getUsrInterceptor())
				//这里配置要拦截的路径
				.addPathPatterns("/**")
				//这里配置不要拦截的路径
				.excludePathPatterns(EXCLUDE_PATH_PATTERN)
				.excludePathPatterns("/**/super-usr/**");

		registry.addInterceptor(getSuperUsrInterceptor())
				//这里配置要拦截的路径
				.addPathPatterns("/**/super-usr/**")
				//这里配置不要拦截的路径
				.excludePathPatterns(EXCLUDE_PATH_PATTERN);

		registry.addInterceptor(getServiceProviderInterceptor())
				//这里配置要拦截的路径
				.addPathPatterns("/**/service-provider/**")
				//这里配置不要拦截的路径
				.excludePathPatterns(EXCLUDE_PATH_PATTERN);
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
