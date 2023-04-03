package com.yutech.back;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestAutoGenerator.class)
public class TestAutoGenerator {

	@Value("${databaseName}")
	private String databaseName;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("#{'${tableNames}'.split(',')}")
	private String[] tableNames;


	@Test
	public void autoGenerate() {

		// Step1：代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// Step2：全局配置
		GlobalConfig gc = new GlobalConfig();
		// 填写代码生成的目录(需要修改)
		String projectPath = System.getProperty("user.dir");
		// 拼接出代码最终输出的目录
		gc.setOutputDir(projectPath + "/src/main/java");
		// 配置开发者信息（可选）（需要修改）
		gc.setAuthor("Jin1c-3");
		// 生成后是否打开资源管理器，false 为不打开（可选）
		gc.setOpen(false);
		// 实体属性 Swagger2 注解，添加 Swagger 依赖，开启 Swagger2 模式（可选）
		gc.setSwagger2(true);
		// 重新生成文件时是否覆盖，false 表示不覆盖（可选）
		gc.setFileOverride(false);
		// 配置主键生成策略，此处为 ASSIGN_ID（可选）
		//该策略使用接口IdentifierGenerator的方法nextId（以实现类为DefaultIdentifierGenerator雪花算法），下面是雪花算法介绍：
		//雪花算法（雪花）是微博开源的分布式ID生成算法其核心思想就是：使用一个64位的长型的数字作为全局唯一ID。在分布式系统中的应用十分广泛，且ID引入了时间戳，基本上保持自增的。
		//对于像MySQL这样的支持主键自动递增的数据库，我们可以使用IdType.AUTO策略。
		//无（无状态）如果使用IdType.NONE策略，表示未设置主键类型（注解里等于跟随上下，左右里约等于INPUT）
//		gc.setIdType(IdType.AUTO);
		// 配置日期类型，此处为 ONLY_DATE（可选）
//		gc.setDateType(DateType.ONLY_DATE);
		// 默认生成的 service 会有 I 前缀
		gc.setServiceName("%sService");
		mpg.setGlobalConfig(gc);

		// Step3：数据源配置（需要修改）
		DataSourceConfig dsc = new DataSourceConfig();
		// 配置数据库 url 地址
		dsc.setUrl("jdbc:sqlserver://localhost:1433;database=" + databaseName + ";encrypt=false");
		// dsc.setSchemaName("testMyBatisPlus"); // 可以直接在 url 中指定数据库名
		// 配置数据库驱动
		dsc.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// 配置数据库连接用户名
		dsc.setUsername(userName);
		// 配置数据库连接密码
		dsc.setPassword(password);
		dsc.setDbType(DbType.SQL_SERVER);
		mpg.setDataSource(dsc);

		// Step:4：包配置
		PackageConfig pc = new PackageConfig();
		// 配置父包名（需要修改）
		pc.setParent("com.yutech.back");
		// 配置模块名（需要修改）
//		pc.setModuleName("back");
		// 配置 entity 包名
		pc.setEntity("entity.po");
		// 配置 mapper 包名
		pc.setMapper("mapper.po");
		// 配置 xml 包名
		pc.setXml("mapper.po.xml");
		// 配置 service 包名
		pc.setService("service.persistence");
		// 配置 service.impl 包名
		pc.setServiceImpl("service.persistence.impl");
		// 配置 controller 包名
		pc.setController("controller");
		mpg.setPackageInfo(pc);

		// Step5：策略配置（数据库表配置）
		StrategyConfig strategy = new StrategyConfig();
		// 指定表名（可以同时操作多个表，使用 , 隔开）（需要修改）
		strategy.setInclude(tableNames);
		// 配置数据表与实体类名之间映射的策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 配置数据表的字段与实体类的属性名之间映射的策略
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// 配置 lombok 模式
		strategy.setEntityLombokModel(true);
		// 配置 rest 风格的控制器（@RestController）
		strategy.setRestControllerStyle(true);
		// 配置驼峰转连字符
		strategy.setControllerMappingHyphenStyle(true);
		// 配置表前缀，生成实体时去除表前缀
		// 此处的表名为 test_mybatis_plus_user，模块名为 test_mybatis_plus，去除前缀后剩下为 user。
		strategy.setTablePrefix(pc.getModuleName() + "_");
		// 配置是否生成实体时，生成字段注解
		strategy.setEntityTableFieldAnnotationEnable(true);
		mpg.setStrategy(strategy);

		// Step6：执行代码生成操作
		mpg.execute();
	}
}
