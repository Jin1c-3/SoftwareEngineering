package com.yutech.back.common.utils;

import com.yutech.back.common.exception.GlobalException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
}
