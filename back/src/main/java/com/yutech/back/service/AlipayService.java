package com.yutech.back.service;

import java.math.BigDecimal;

/**
 * 支付宝服务
 */
public interface AlipayService {
	public String toPay(String subject, BigDecimal money, String orderNO);
}
