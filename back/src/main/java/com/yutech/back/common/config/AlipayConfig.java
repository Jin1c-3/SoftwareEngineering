package com.yutech.back.common.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.yutech.back.common.utils.FileUtil;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝配置项
 */
@Configuration
public class AlipayConfig {

	static {
		Factory.setOptions(getOptions());
	}

	private static Config getOptions() {
		Config config = new Config();
		config.protocol = "https";
		// 沙箱环境修改为 openapi.alipaydev.com
		// 支付宝网关地址：https://openapi-sandbox.dl.alipaydev.com/gateway.do
		config.gatewayHost = "openapi.alipaydev.com";
		config.signType = "RSA2";
		config.appId = "2021000122670344";
		// 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
		config.merchantPrivateKey = FileUtil.readFileFromTxt("C:/Users/yujin/Documents/支付宝开放平台密钥工具/密钥20230324153926/应用私钥RSA2048-敏感数据，请妥善保管.txt");
		config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvVWun3DYvz/fjOMJ/P0+qogcRpjiB6Pmz/hYQQ97KP8SdKFhd6WWGVOKH3/8m0pP6lhkIiVh74A1F8eS1rcgy2DgTuYG5RCj/DQpgPow7Uy5sv1iQgM+WbGe0Ydp44+E8qzFdqx6avVdYOkNNiS9oOugok89Yqghz2I3gcKSBDcy0TiQYnXTY+zNV1tvzN5BZiV8J1of9GzlP4uak/kK8BFxURQJdkL0Yb+k8Y+fE9ig+UUBdBoC5uac6xkv4Pa5k3KI9Z8ZlUxwtA1zm5MzT1ODb2HOS4qiSzcKpx6GgxpIJOD18968BBKwMh7CP0m2dZDBiQOSrG3FXcAqGlm0JwIDAQAB";
		// 设置异步通知接收服务地址，需要外网可以访问，可以设置内网穿透
		config.notifyUrl = "";
		config.encryptKey = "";
		return config;
	}
}
