package com.yutech.back.service.bussiness.impl;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.OtherUtil;
import com.yutech.back.entity.bo.PaymentBO;
import com.yutech.back.entity.dto.RefundDTO;
import com.yutech.back.entity.po.WholeOrder;
import com.yutech.back.service.bussiness.AlipayService;
import com.yutech.back.service.persistence.WholeOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {

	@Autowired
	private WholeOrderService wholeOrderService;

	/**
	 * 支付宝支付成功后，跳转的页面
	 */
	@Value("${alipay.returnUrl}")
	private String returnUrl;

	/**
	 * 支付宝支付
	 *
	 * @param paymentBO 支付信息
	 * @return 支付宝支付页面
	 */
	@Override
	public String toPay(PaymentBO paymentBO) {
		if (StringUtils.isBlank(paymentBO.getOrderNO())) {
			paymentBO.setOrderNO(OtherUtil.generateTradeNo());
		}
		try {
			AlipayTradePagePayResponse response = Factory.Payment.Page().pay(
					paymentBO.getSubject(),
					paymentBO.getOrderNO(),
					paymentBO.getMoney().toString(),
					returnUrl);
			String payForm = null;
			WholeOrder wholeOrder = new WholeOrder(
					paymentBO.getOrderNO(),
					"否",
					paymentBO.getVehicleType(),
					paymentBO.getUsrId());
			log.debug("存入订单表whole_order中的数据==={}", wholeOrder);
			try {
				wholeOrderService.save(wholeOrder);
			} catch (Exception e) {
				throw new GlobalException("JDBC操作失败", e);
			}
			if (ResponseChecker.success(response)) {
				payForm = response.getBody();
			}
			log.debug("支付宝支付接口调用完成，返回的数据为===" + response.getBody());
			return payForm;
		} catch (Exception e) {
			throw new GlobalException("支付宝支付接口调用失败", e);
		}
	}

	public AlipayTradeRefundResponse refund(RefundDTO refundDTO) {
		try {
			AlipayTradeRefundResponse response = Factory.Payment.Common().refund(
					refundDTO.getOrderId(),
					refundDTO.getMoney().toString());
			if (ResponseChecker.success(response)) {
				log.debug("退款成功");
			} else {
				log.debug("退款失败");
			}
			return response;
		} catch (Exception e) {
			throw new GlobalException("支付宝退款接口调用失败", e);
		}
	}

}
