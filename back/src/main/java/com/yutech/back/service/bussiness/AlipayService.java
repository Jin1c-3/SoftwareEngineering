package com.yutech.back.service.bussiness;

import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.yutech.back.entity.bo.PaymentBO;
import com.yutech.back.entity.dto.RefundDTO;


/**
 * 支付宝服务
 */
public interface AlipayService {
	String toPay(PaymentBO paymentBO);

	AlipayTradeRefundResponse refund(RefundDTO refundDTO);
}
