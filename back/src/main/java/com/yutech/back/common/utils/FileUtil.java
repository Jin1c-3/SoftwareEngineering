package com.yutech.back.common.utils;

import com.mysql.cj.util.StringUtils;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.validator.group.AddGroup;
import com.yutech.back.common.validator.group.UpdateGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.io.File;
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
public class FileUtil {
	//绑定文件上传路径到uploadPath
	@Value("${web.upload-path}")
	private static String uploadPath;

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
		File folder = new File(StringUtils.isNullOrEmpty(prePath) ? uploadPath + format : uploadPath + prePath + "/" + format);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
		// 对上传的文件重命名，避免文件重名
		String oldName = file.getOriginalFilename();
		assert oldName != null;
		String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
		try {
			// 文件保存
			file.transferTo(new File(folder, newName));

			// 返回上传文件的访问路径
			String httpAddress = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + format + newName;
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
}
