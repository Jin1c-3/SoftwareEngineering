package com.yutech.back.service.bussiness;

import com.yutech.back.entity.bo.PaymentBO;


/**
 * 支付宝服务
 */
public interface AlipayService {
	String toPay(PaymentBO paymentBO);
}
