package com.yutech.back;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest(classes = {TestDBDocsGenerator.class})
public class TestDBDocsGenerator {
	@Test
	public void screwGenerator(){
		// 数据源
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		hikariConfig.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=SEP;encrypt=false");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("root");
		// 设置可以获取tables remarks信息
		hikariConfig.addDataSourceProperty("useInformationSchema", "true");
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaximumPoolSize(5);
		DataSource dataSource = new HikariDataSource(hikariConfig);
		// 1、生成文件配置
		EngineConfig engineConfig = EngineConfig.builder()
				// 生成文件路径
				.fileOutputDir("C:\\Users\\yujin\\Desktop")
				// 打开目录
				.openOutputDir(false)
				// 文件类型
				.fileType(EngineFileType.WORD)
				// 生成模板实现
				.produceType(EngineTemplateType.freemarker).build();

		// 3、生成文档配置（包含以下自定义版本号、描述等配置连接）
		Configuration config = Configuration.builder().version("1.0.0").description("数据库文档").dataSource(dataSource)
				.engineConfig(engineConfig).build();

		// 4、执行生成
		new DocumentationExecute(config).execute();
	}
}
