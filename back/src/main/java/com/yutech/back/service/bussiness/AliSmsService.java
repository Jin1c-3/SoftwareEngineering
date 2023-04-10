package com.yutech.back.service.bussiness;

import com.yutech.back.entity.bo.Sms4CodeBO;

public interface AliSmsService {
	Boolean sendSms(String phone, String code);

	Boolean sendSmsVerificationCode(Sms4CodeBO sms4CodeBO);
}
