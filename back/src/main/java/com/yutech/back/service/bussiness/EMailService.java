package com.yutech.back.service.bussiness;

import com.yutech.back.entity.bo.EMail4CodeBO;

/**
 * 邮件发送服务
 */
public interface EMailService {
	Boolean sendSimpleMail(String from, String to, String subject, String content);

	Boolean sendVerificationCode(String to, String code);

	Boolean sendVerificationCode(EMail4CodeBO eMail4CodeBO);

	Boolean sendGreetings(String to, String name);


}
