package com.yutech.back.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptorUtil {

	public static String encrypt(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	public static boolean match(String password, String encryptedPassword) {
		return new BCryptPasswordEncoder().matches(password, encryptedPassword);
	}
}
