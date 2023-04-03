package com.yutech.back.service.impl;

import com.yutech.back.service.EMailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EMailSenderImpl implements EMailSenderService {
	@Autowired
	private final JavaMailSender javaMailSender;

	public EMailSenderImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendSimpleMail(String from, String to, String subject, String content) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		// 发送者
		simpleMailMessage.setFrom(from);
		// 接收者
		simpleMailMessage.setTo(to);
		// 邮件主题
		simpleMailMessage.setSubject(subject);
		// 邮件内容
		simpleMailMessage.setText(content);
		// 发送邮件
		javaMailSender.send(simpleMailMessage);
		log.debug("邮件发送成功:from: {} ====== to: {}", from, to);
	}

	@Override
	public void sendCodeMail(String to, String code) {
		String from = "3396024490@qq.com";
		sendSimpleMail(from, to, "[网络鱼科技]验证码", "您的验证码为:" + code + "，5分钟内有效，请勿将验证码告诉他人。");
		log.info("验证码邮件发送成功:from: {} ======= to: {} ,with code {}", from, to, code);
	}

}
