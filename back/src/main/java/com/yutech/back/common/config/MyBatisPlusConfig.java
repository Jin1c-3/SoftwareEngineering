package com.yutech.back.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义一个 MybatisPlus 配置类，配置分页插件、乐观锁插件
 * mapper 扫描也可在此写上
 */
@Configuration
@MapperScan("com.yutech.back.mapper")
public class MyBatisPlusConfig {
	/**
	 * 分页插件
	 *
	 * @return 分页插件的实例
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
