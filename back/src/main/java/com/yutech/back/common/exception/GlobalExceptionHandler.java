package com.yutech.back.common.exception;

import com.yutech.back.common.utils.Result;
import com.yutech.back.entity.dto.ValidationErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理类。
 * 使用 slf4j 保存日志信息。
 * 此处使用了 统一结果处理 类 Result 用于包装异常信息。
 * <p>
 * 使用Slf4j就是为了能够少写两行代码，不用每次都在类的最前边写上：
 * private static final Logger logger = LoggerFactory.getLogger(this.XXX.class);
 * 我们只需要在类前面添加注解@Slf4j，即可使用log日志的功能了
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	/**
	 * 处理 Exception 异常
	 *
	 * @param e 异常
	 * @return 处理结果
	 */
	@ExceptionHandler(Exception.class)
	public Result<String> handlerException(Exception e) {
		log.error(e.getMessage(), e);
		return Result.error("后端报错信息=>" + e.getMessage()).message(e.getMessage() == null ? "后端报错信息为空" : e.getMessage());
	}

	/**
	 * 处理空指针异常
	 *
	 * @param e 异常
	 * @return 处理结果
	 */
	@ExceptionHandler(NullPointerException.class)
	public Result<String> handlerNullPointerException(NullPointerException e) {
		log.error(e.getMessage(), e);
		return Result.error("后端空指针异常=>" + e.getMessage()).message("空指针异常，请联系管理员");
	}

	/**
	 * 处理自定义异常
	 *
	 * @param e 异常
	 * @return 处理结果
	 */
	@ExceptionHandler(GlobalException.class)
	public Result<String> handlerGlobalException(GlobalException e) {
		log.error(e.getMessage(), e);
		return Result.error("后端自定义全局异常=>" + e.getMessage()).message(e.getMessage()).code(e.getCode());
	}

//	/**
//	 * 处理 JSR303 校验的异常
//	 *
//	 * @param e 异常
//	 * @return 处理结果
//	 */
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Result<List<ValidationErrorDTO>> handlerValidException(MethodArgumentNotValidException e) {
//		log.error(e.getMessage(), e);
//		BindingResult result = e.getBindingResult();
//		List<ValidationErrorDTO> v = new ArrayList<>();
//		// 获取校验结果，遍历获取捕获到的每个校验结果
//		result.getFieldErrors().forEach(item -> {
//			// 存储得到的校验结果
//			v.add(new ValidationErrorDTO(item.getField(), item.getDefaultMessage()));
//		});
//		return Result.error(v).message("数据校验不合法");
//	}

	@ExceptionHandler(BindException.class)
	public Result<List<ValidationErrorDTO>> handlerValidException(BindException e) {
		log.error(e.getMessage(), e);
		BindingResult result = e.getBindingResult();
		List<ValidationErrorDTO> v = new ArrayList<>();
		// 获取校验结果，遍历获取捕获到的每个校验结果
		result.getFieldErrors().forEach(item -> {
			// 存储得到的校验结果
			v.add(new ValidationErrorDTO(item.getField(), item.getDefaultMessage()));
		});
		return Result.error(v).message("数据校验不合法");
	}
}
