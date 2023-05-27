package com.yutech.back.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class RandomGeneratorUtil {
	/**
	 * 生成订单号
	 *
	 * @return 根据时间戳生成的订单号
	 */
	public static String generateTradeNo() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String orderNo = LocalDateTime.now().format(formatter);
		log.debug("生成的订单号是==={}", orderNo);
		return orderNo;
	}

	public static String getRandomUsrIdByUUID() {
		Format sdf = new SimpleDateFormat("yyyyMMdd");
		String usrId = sdf.format(new Date()) + "-" + UUID.randomUUID();
		log.trace("生成的用户ID是==={}", usrId);
		return usrId;
	}
}
