package com.yutech.back.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.validator.group.AddGroup;
import com.yutech.back.common.validator.group.UpdateGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传下载工具类
 */
@Slf4j
@Component
public class FileUtil {
	/**
	 * 文件上传的本地存储路径
	 */
	private static String uploadPath;

	/**
	 * 通过 @Value 注解，将 application.properties 中的 web.upload-path 属性值注入到 uploadPath 中
	 *
	 * @param uploadPath 文件上传本地路径，存储在配置文件中
	 */
	@Value("${web.upload-path}")
	public void setUploadPath(String uploadPath) {
		FileUtil.uploadPath = uploadPath;
	}

	/**
	 * 文件默认按照时间作为文件夹存储
	 */
	private static final Format sdf = new SimpleDateFormat("yyyy/MM/dd/");

	/**
	 * 将输入的单个Multipart文件存储在本地
	 * 本地路径为application.properties中的web.upload-path
	 * 即C:/WorkSpace/web/SEProj/
	 *
	 * @param prePath 可输入路径，输入“”空字符串时为默认路径。设想此参数为用户昵称
	 * @param file    被存储的文件
	 * @param request 网页请求，方便返回网页地址
	 * @return 被存储文件的网络地址
	 */
	@NotEmpty(message = "上传的文件和网络请求不能为空", groups = {AddGroup.class, UpdateGroup.class})
	public static String storeMultipartFile(String prePath, MultipartFile file, HttpServletRequest request) {
		// 在 uploadPath 文件夹中通过日期对上传的文件归类保存
		// 比如：/2019/06/06/test.png
		String format = sdf.format(new Date());
		File folder = new File(StringUtils.isEmpty(prePath) ? uploadPath + format : uploadPath + prePath + "/" + format);
		log.debug("目标文件夹 {}", folder);
		if (!folder.isDirectory()) {
			log.debug("正在新建文件夹 {}", folder);
			folder.mkdirs();
		}
		// 对上传的文件重命名，避免文件重名
		String oldName = file.getOriginalFilename();
		assert oldName != null;
		String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
		try {
			// 文件保存
			file.transferTo(new File(folder, newName));
			// 返回上传文件的访问路径
			String httpAddress = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + format + newName;
			log.info("图片存储成功，真实文件位置：" + folder + "/" + newName + "；返回的网络文件地址：" + httpAddress);
			return httpAddress;
		} catch (IOException e) {
			throw new GlobalException("文件存储发生异常" + ExceptionUtil.getMessage(e));
		}
	}

	/**
	 * 调用storeMultipartFile，将输入的多个Multipart文件数组存储在本地
	 *
	 * @param prePath 可输入路径，输入“”空字符串时为默认路径
	 * @param files   被存储的文件数组
	 * @param request 网页请求，方便返回网页地址
	 * @return 被存储文件的网络地址数组
	 */
	@NotEmpty(message = "上传的文件组和网络请求不能为空", groups = {AddGroup.class, UpdateGroup.class})
	public static String[] storeMultipartFiles(String prePath, MultipartFile[] files, HttpServletRequest request) {
		List<String> paths = new ArrayList<String>(files.length);
		for (MultipartFile file : files) {
			paths.add(storeMultipartFile(prePath, file, request));
		}
		return paths.toArray(new String[files.length]);
	}

	/**
	 * 读取txt文件的内容
	 *
	 * @param path 被读取的txt文件路径
	 * @return txt文件的内容
	 */
	public static String readFileFromTxt(String path) {
		log.info("正在从此路径读取txt文件: {}", path);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			char[] buf = new char[1024];
			int len = -1;
			while ((len = bufferedReader.read(buf)) != -1) {
				sb.append(new String(buf, 0, len));
			}
		} catch (IOException e) {
			throw new GlobalException("文件读取发生问题，文件可能不存在" + ExceptionUtil.getMessage(e));
		}
		return sb.toString();
	}
}
