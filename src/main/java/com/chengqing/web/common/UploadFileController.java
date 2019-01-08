package com.chengqing.web.common;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chengqing.constant.Constant;
import com.chengqing.utils.FileType;

/**
 * 公共文件上传类
 * @author chengqing
 *
 */
@Controller
@RequestMapping("/uploadFile")
public class UploadFileController {

	/**
	 * 上传选择的图片到服务器上
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/uploadPicMethod", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String uploadPicMethod(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {

		String jsonStr = "";
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		
		//文件类型
		String dataType = request.getParameter("dataType");
		try {
			if (StringUtils.isNotEmpty(dataType)) {
				//验证保险资料类型
				if (isInsuranceDoc(file)) {
					// 设置临时文件存储位置，获取服务器的地址
					String tomcatPath = request.getServletContext().getRealPath("/");
					tomcatPath = tomcatPath.replace("\\", "/");
					StringBuffer destFName = new StringBuffer();
					
					//每个模块存放的图片路径也不一样,只用传递路径名称，不用传递“/”
					String picPath = request.getParameter("picPath");
					String applyNo = request.getParameter("applyNo");
					destFName.append(getRealDir(tomcatPath).replace("web/", "/"))
						.append(Constant.BASE_PATH + picPath + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date()))
						.append("/"+applyNo);
					String path = destFName.toString().replace("\\", "/");

					// 获取上传图片的名称
					String fileName = file.getOriginalFilename();
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					fileName = "" + System.currentTimeMillis();
					fileName = fileName + fileType;

					// 图片文件大小大于500KB
					long imgSize = file.getSize();
					if (imgSize > 500 * 1024) {
						jsonStr = "{\"msg\":'fail',\"fileName\":'',\"filePath\":'',\"documentName\":''}";
					} else {
						File targetFile = new File(path, fileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs(); // 上传图片到服务器
							file.transferTo(targetFile);
							String fileAllName = file.getOriginalFilename();
							jsonStr = "{\"msg\":'success',\"fileName\":'"
									+ fileName
									+ "',\"filePath\":'"
									+ path.substring(path
											.indexOf(Constant.BASE_PATH))
									+ "',\"documentName\":'" + fileAllName + "'}";
						}
					}
				} else {
					jsonStr = "{\"msg\":'error',\"fileName\":'',\"filePath\":'',\"documentName\":''}";
				}
			}else {
				//验证图片类型
				if (isImage(file)) {
					// 设置临时文件存储位置，获取服务器的地址
					String tomcatPath = request.getServletContext().getRealPath("/");
					tomcatPath = tomcatPath.replace("\\", "/");
					StringBuffer destFName = new StringBuffer();
					
					//每个模块存放的图片路径也不一样,只用传递路径名称，不用传递“/”
					String picPath = request.getParameter("picPath");
					
					destFName.append(getRealDir(tomcatPath).replace("web/", "/"))
						.append(Constant.BASE_PATH + picPath + "/"
									+ new SimpleDateFormat("yyyyMMdd")
											.format(new Date()));
					String path = destFName.toString().replace("\\", "/");

					// 获取上传图片的名称
					String fileName = file.getOriginalFilename();
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					fileName = "" + System.currentTimeMillis();
					fileName = fileName + fileType;

					// 图片文件大小大于5M
					long imgSize = file.getSize();
					if (imgSize > 5000 * 1024) {
						jsonStr = "{\"msg\":'fail',\"fileName\":'',\"filePath\":'',\"documentName\":''}";
					} else {
						File targetFile = new File(path, fileName);
						if (!targetFile.exists()) {
							targetFile.mkdirs(); // 上传图片到服务器
							file.transferTo(targetFile);
							String fileAllName = file.getOriginalFilename();
							jsonStr = "{\"msg\":'success',\"fileName\":'"
									+ fileName
									+ "',\"filePath\":'"
									+ path.substring(path
											.indexOf(Constant.BASE_PATH))
									+ "',\"documentName\":'" + fileAllName + "'}";
						}
					}
				} else {
					jsonStr = "{\"msg\":'error',\"fileName\":'',\"filePath\":'',\"documentName\":''}";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonStr;
	}

	// 判断是否为图片
	private boolean isImage(MultipartFile file) {
		boolean flag = false;
		try {
			byte[] b = new byte[28];
			file.getInputStream().read(b, 0, 28);
			FileType type = getType(b);
			if (null != type && !"".equals(type)
					&& StringUtils.isNotEmpty(type.name())) {
				if ("JPG".equals(type.name()) ||"JPEG".equals(type.name()) || "PNG".equals(type.name())
						|| "GIF".equals(type.name())
						|| "TIFF".equals(type.name())
						|| "BMP".equals(type.name())) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// 判断是否为jpg/excel/pdf类型
	private boolean isInsuranceDoc(MultipartFile file) {
		boolean flag = false;
		try {
			byte[] b = new byte[28];
			file.getInputStream().read(b, 0, 28);
			FileType type = getType(b);
			if (null != type && !"".equals(type)
					&& StringUtils.isNotEmpty(type.name())) {
				if ("JPG".equals(type.name()) || "JPEG".equals(type.name()) ||"ZIP".equals(type.name()) ||"XLS_DOC".equals(type.name()) || "PDF".equals(type.name())) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 判断文件类型
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件类型
	 */
	public static FileType getType(byte[] b) throws IOException {

		String fileHead = bytesToHexString(b);

		if (fileHead == null || fileHead.length() == 0) {
			return null;
		}

		fileHead = fileHead.toUpperCase();

		FileType[] fileTypes = FileType.values();

		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 验证图片的目录
	 * 
	 * @param newFileNameRoot
	 * @return
	 * @throws Exception
	 */
	private String getRealDir(String newFileNameRoot) throws Exception {
		if (newFileNameRoot == null)
			throw new Exception("get real dir failed !");
		int dp = newFileNameRoot.lastIndexOf(Constant.OBLIQUE_LINE);
		if (dp == -1)
			throw new Exception("invalid path !");
		int dpbefore = newFileNameRoot.lastIndexOf(Constant.OBLIQUE_LINE,
				dp - 1);
		if (dpbefore == -1)
			throw new Exception("invalid path !");
		String needSubStr = newFileNameRoot.substring(dpbefore + 1, dp);
		String nextStr = newFileNameRoot.substring(0, dpbefore + 1);
		if (!needSubStr.trim().equals(Constant.WEBPOSITION)
				&& !needSubStr.trim().equals(Constant.WTPPWEBAPPS)) {
			return getRealDir(nextStr);
		} else
			return newFileNameRoot;
	}

	/**
	 * 将文件头转换成16进制字符串
	 * 
	 * @param 原生byte
	 * @return 16进制字符串
	 */
	private static String bytesToHexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
