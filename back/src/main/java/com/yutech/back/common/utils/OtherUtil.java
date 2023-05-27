package com.yutech.back.common.utils;

import com.yutech.back.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class OtherUtil {

	public static int getWeek(String date) {
		Date getWeekDayDate = null;
		Calendar getWeekDayCal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			getWeekDayDate = sdf.parse(date);
		} catch (Exception e) {
			throw new GlobalException("日期转换错误", e);
		}
		getWeekDayCal.setTime(getWeekDayDate);
		int weekDay = getWeekDayCal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDay == 0 ? 7 : weekDay;
	}


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
		return sdf.format(new Date()) + "-" + UUID.randomUUID()
	}
}
