package com.yutech.back.service.bussiness.impl;

import com.yutech.back.entity.bo.EMail4CodeBO;
import com.yutech.back.service.bussiness.EMailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EMailSenderImpl implements EMailSenderService {
	@Autowired
	private final JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;

	public EMailSenderImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * 发送简单邮件
	 *
	 * @param from    发送者
	 * @param to      接收者
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	@Override
	public Boolean sendSimpleMail(String from, String to, String subject, String content) {
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
		try {
			javaMailSender.send(simpleMailMessage);
			log.debug("邮件发送成功:from: {} ====== to: {}", from, to);
			return true;
		} catch (Exception e) {
			log.error("邮件发送失败:from: {} ====== to: {}", from, to);
			return false;
		}
	}

	/**
	 * 发送验证码邮件
	 *
	 * @param to   接收者
	 * @param code 验证码
	 */
	@Override
	public Boolean sendCodeMail(String to, String code) {
		if (sendSimpleMail(from, to, "[网络鱼科技]验证码", "您的验证码为:" + code + "，5分钟内有效，请勿将验证码告诉他人。")) {
			log.info("验证码邮件发送成功:from: {} ======= to: {} ,with code {}", from, to, code);
			return true;
		} else {
			log.error("验证码邮件发送失败:from: {} ======= to: {} ,with code {}", from, to, code);
			return false;
		}
	}

	/**
	 * 发送验证码邮件
	 *
	 * @param eMail4CodeBO 邮件验证码业务对象
	 */
	@Override
	public Boolean sendCodeMail(EMail4CodeBO eMail4CodeBO) {
		if (sendSimpleMail(from, eMail4CodeBO.getTo(), "[网络鱼科技]验证码", "您的验证码为:" + eMail4CodeBO.getCode() + "，5分钟内有效，请勿将验证码告诉他人。")) {
			log.info("验证码邮件发送成功:from: {} ======= to: {} ,with code {}", from, eMail4CodeBO.getTo(), eMail4CodeBO.getCode());
			return true;
		} else {
			log.error("验证码邮件发送失败:from: {} ======= to: {} ,with code {}", from, eMail4CodeBO.getTo(), eMail4CodeBO.getCode());
			return false;
		}
	}

	/**
	 * 发送欢迎邮件
	 *
	 * @param to   接收者
	 * @param name 用户名
	 */
	public Boolean sendGreetingsMail(String to, String name) {
		if (sendSimpleMail(from, to, "[网络鱼科技]欢迎", "欢迎您的加入，" + name + "。")) {
			log.info("欢迎邮件发送成功:from: {} ======= to: {} ,with name {}", from, to, name);
			return true;
		} else {
			log.error("欢迎邮件发送失败:from: {} ======= to: {} ,with name {}", from, to, name);
			return false;
		}
	}

}
