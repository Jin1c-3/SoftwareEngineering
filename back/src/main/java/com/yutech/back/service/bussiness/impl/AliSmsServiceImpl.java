package com.yutech.back.service.bussiness.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.entity.bo.Sms4CodeBO;
import com.yutech.back.service.bussiness.AliSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AliSmsServiceImpl implements AliSmsService {

	@Value("${aliyun.sms.accessKeyId}")
	private String accessKeyId;

	@Value("${aliyun.sms.accessKeySecret}")
	private String accessKeySecret;

	@Value("${aliyun.sms.templateCodeOfYZM}")
	private String templateCodeOfYZM;

	@Value("${aliyun.sms.signNameOfYZM}")
	private String signNameOfYZM;

	/**
	 * 发送短信
	 *
	 * @param phone        手机号
	 * @param templateCode 模板code
	 * @param code         验证码
	 * @return boolean
	 */
	public Boolean sendSms(String phone, String templateCode, String code) {

		// 创建DefaultAcsClient实例并初始化
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId,
				accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		CommonRequest request = new CommonRequest();
		request.setSysMethod(MethodType.POST);
		request.setSysDomain("dysmsapi.aliyuncs.com");
		request.setSysVersion("2017-05-25");
		request.setSysAction("SendSms");

		request.putQueryParameter("PhoneNumbers", phone);// 手机号
		request.putQueryParameter("SignName", signNameOfYZM);// 短信签名
		request.putQueryParameter("TemplateCode", templateCode);// 模板code

		request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");// 验证码

		try {
			CommonResponse response = client.getCommonResponse(request);
			log.info("发送短信========" + response.getData());
			return response.getHttpResponse().isSuccess();
		} catch (Exception e) {
			throw new GlobalException("发送短信失败");
		}
	}

	/**
	 * 发送验证码短信
	 *
	 * @param phone 手机号
	 * @param code  验证码
	 * @return boolean
	 */
	@Override
	public Boolean sendSmsYZM(String phone, String code) {
		return sendSms(phone, templateCodeOfYZM, code);
	}

	/**
	 * 发送验证码短信
	 *
	 * @param sms4CodeBO 短信验证码业务对象
	 * @return boolean
	 */
	@Override
	public Boolean sendSmsYZM(Sms4CodeBO sms4CodeBO) {
		return sendSms(sms4CodeBO.getPhone(), templateCodeOfYZM, sms4CodeBO.getCode());
	}
}
