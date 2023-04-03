package com.yutech.back.service.bussiness;

import com.yutech.back.entity.bo.Sms4CodeBO;

public interface AliSmsService {
	public Boolean sendSmsYZM(String phone, String code);

	public Boolean sendSmsYZM(Sms4CodeBO sms4CodeBO);
}
