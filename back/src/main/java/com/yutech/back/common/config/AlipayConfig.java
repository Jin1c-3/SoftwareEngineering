package com.yutech.back.common.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.yutech.back.common.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 支付宝配置项
 */
@Configuration
public class AlipayConfig {

	@Value("${alipay.appId}")
	private String appId;

	@Value("${alipay.keyPath}")
	private String keyPath;

	@Value("${alipay.publicKey}")
	private String publicKey;

	@PostConstruct
	public void init() {
		Factory.setOptions(getOptions());
	}

	private Config getOptions() {
		Config config = new Config();
		config.protocol = "https";
		// 沙箱环境修改为 openapi.alipaydev.com
		// 支付宝网关地址：https://openapi-sandbox.dl.alipaydev.com/gateway.do
		config.gatewayHost = "openapi.alipaydev.com";
		config.signType = "RSA2";
		config.appId = appId;
		// 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
		config.merchantPrivateKey = FileUtil.readFileFromTxt(keyPath);
		config.alipayPublicKey = publicKey;
		// 设置异步通知接收服务地址，需要外网可以访问，可以设置内网穿透
		config.notifyUrl = "";
		config.encryptKey = "";
		return config;
	}
}