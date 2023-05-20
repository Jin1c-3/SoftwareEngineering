package com.yutech.back.service.bussiness.impl;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.entity.bo.PaymentBO;
import com.yutech.back.entity.po.WholeOrder;
import com.yutech.back.service.bussiness.AlipayService;
import com.yutech.back.service.persistence.WholeOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
			paymentBO.setOrderNO(generateTradeNo());
		}
		try {
			AlipayTradePagePayResponse response = Factory.Payment.Page().pay(
					paymentBO.getSubject(),
					paymentBO.getSubject(),
					paymentBO.getMoney().toString(),
					returnUrl);
			String payForm = null;
			WholeOrder wholeOrder = new WholeOrder(
					paymentBO.getOrderNO(),
					"否",
					paymentBO.getVehicleType(),
					paymentBO.getUsrId());
			log.debug("存入订单表中的数据===", wholeOrder);
			wholeOrderService.save(wholeOrder);
			if (ResponseChecker.success(response)) {
				payForm = response.getBody();
			}
			log.debug("支付宝支付接口调用成功，返回的数据为===" + response.getBody());
			return payForm;
		} catch (Exception e) {
			throw new GlobalException("支付宝支付接口调用失败", e);
		}
	}

	/**
	 * 生成订单号
	 *
	 * @return 根据时间戳生成的订单号
	 */
	private String generateTradeNo() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String orderNo = LocalDateTime.now().format(formatter);
		log.debug("生成的订单号是==={}", orderNo);
		return orderNo;
	}
}
