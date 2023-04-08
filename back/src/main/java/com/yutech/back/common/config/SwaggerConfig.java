package com.yutech.back.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//使用@profile注解的目的是未了多环境开发，比如开发环境使用dev, 生产环境使用prod,就可以使用@Profile注解实现不同的开发环境使用不同的数据源；
@Configuration
@EnableSwagger2
@EnableKnife4j
@Profile({"dev", "test"})
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.pathMapping("/")
				.enable(true)
				.apiInfo(apiInfo())
				.select()
				// 加了ApiOperation注解的类，才会生成接口文档
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// 指定包下的类，才生成接口文档
				.apis(RequestHandlerSelectors.basePackage("com.yutech.back.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("网上购票系统API文档")
				.description("本文档描述了网上购票系统接口定义和实体类定义")
				.termsOfServiceUrl("http://localhost:8080/")
				.version("1.0.0")
				.build();
	}
}
