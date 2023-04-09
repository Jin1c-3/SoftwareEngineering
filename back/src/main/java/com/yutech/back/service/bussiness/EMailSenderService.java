package com.yutech.back.service.bussiness;

import com.yutech.back.entity.bo.EMail4CodeBO;

/**
 * 邮件发送服务
 */
public interface EMailSenderService {
	public Boolean sendSimpleMail(String from, String to, String subject, String content);

	public Boolean sendCodeMail(String to, String code);

	public Boolean sendCodeMail(EMail4CodeBO eMail4CodeBO);

	public Boolean sendGreetingsMail(String to, String name);


}
