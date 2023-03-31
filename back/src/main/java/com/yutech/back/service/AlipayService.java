package com.yutech.back.service;

import com.yutech.back.bo.PaymentBO;


/**
 * 支付宝服务
 */
public interface AlipayService {
	public String toPay(PaymentBO paymentBO);
}
