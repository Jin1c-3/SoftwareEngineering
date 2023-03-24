package com.yutech.back.service;
/**
 * 邮件发送服务
 */
public interface EMailSenderService {
	public void sendSimpleMail(String from, String to, String subject, String content);

	public void sendCodeMail(String to, String code);
}
