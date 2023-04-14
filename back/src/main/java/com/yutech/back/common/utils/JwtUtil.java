package com.yutech.back.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * JWT工具类
 */
@Slf4j
public class JwtUtil {
	/**
	 * Token过期时间60分钟
	 */
	public static final long EXPIRE_TIME = 60 * 60 * 1000;


	/**
	 * 校验token是否正确
	 *
	 * @param token  前端传回的token值
	 * @param id     user的唯一标识符
	 * @param secret 设定为password
	 * @return boolean 返回是否合格
	 */
	public static boolean verify(String token, String id, String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT
					.require(algorithm)
					.withClaim("id", id)
					.build();
			verifier.verify(token);
			log.info("验证token成功，id======" + id);
			return true;
		} catch (Exception e) {
			log.warn("token失效，id======" + id);
			return false;
		}
	}

	/**
	 * 生成签名,EXPIRE_TIME后过期
	 *
	 * @param id     用户名
	 * @param secret 用户的密码
	 * @return 加密的token
	 */
	public static String sign(String id, String secret) {
		log.trace("生成token，id&secret==={}==={}", id, secret);
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT
				.create()
				.withClaim("id", id)
				.withExpiresAt(date)
				.sign(algorithm);
	}

	/**
	 * 通过token获取ID
	 *
	 * @param request 前端的网页请求
	 * @return String 返回token中的username
	 */
	public static String getIdByToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		log.trace("从token中获取id，token==={}", token);
		return JWT.decode(token)
				.getClaim("id")
				.asString();
	}
}
