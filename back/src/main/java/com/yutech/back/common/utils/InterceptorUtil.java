package com.yutech.back.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.yutech.back.common.exception.GlobalException;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class InterceptorUtil {
	public static void createResponse(HttpServletResponse response, String message, int code) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.print(JSONObject.toJSONString(Result.error().message(message).code(code)));
		} catch (Exception e) {
			throw new GlobalException("服务器拦截器错误", 500);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static void createResponse(HttpServletResponse response, String message) {
		createResponse(response, message, 500);
	}

	public static void createLevelConflictResponse(HttpServletResponse response) {
		createResponse(response, "您的权限不足");
	}

	public static void createTimeLimitResponse(HttpServletResponse response) {
		createResponse(response, "请重新登录", 50008);
	}
}
