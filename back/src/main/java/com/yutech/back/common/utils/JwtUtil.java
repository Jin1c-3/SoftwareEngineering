package com.yutech.back.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * JWT工具类
 */
@Slf4j
public class JwtUtil {
	/**
	 * Token过期时间30分钟
	 */
	public static final long EXPIRE_TIME = 60 * 60 * 1000;


	/**
	 * 校验token是否正确
	 *
	 * @param token    前端传回的token值
	 * @param username user的唯一标识符
	 * @param secret   设定为password
	 * @return boolean 返回是否合格
	 */
	public static boolean verify(String token, String username, String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT
					.require(algorithm)
					.withClaim("username", username)
					.build();
			DecodedJWT djwt = verifier.verify(token);
			log.info("验证token成功，username：" + username);
			return true;
		} catch (Exception e) {
			log.warn("验证token失效，username：" + username);
			return false;
		}
	}

	/**
	 * 生成签名，设定1小时后过期
	 *
	 * @param username user的唯一标识符
	 * @param secret   设定为password
	 * @return String 返回以username和secret为基础创建的token
	 */
	public static String sign(String username, String secret) {
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT
				.create()
				.withClaim("username", username)
				.withExpiresAt(date)
				.sign(algorithm);
	}

	/**
	 * 通过token获取userID
	 *
	 * @param request 前端的王爷请求
	 * @return String 返回token中的username
	 */
	public static String getUsernameByToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		DecodedJWT djwt = JWT.decode(token);
		return djwt
				.getClaim("username")
				.asString();
	}
}
