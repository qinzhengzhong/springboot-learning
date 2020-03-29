package com.allan.springbootcommon.util;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

public class FileUtils {
		/**
		 * 下载文件时，针对不同浏览器，进行附件名的编码
		 * 
		 * @param filename
		 *            下载文件名
		 * @param agent
		 *            客户端浏览器
		 * @return 编码后的下载附件名
		 * @throws IOException
		 */
		public static String encodeDownloadFilename(String filename, String agent)
				throws IOException {
			if (agent.contains("Firefox")) { // 火狐浏览器
				filename = "=?UTF-8?B?"
						+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
						+ "?=";
				filename = filename.replaceAll("\r\n", "");
			} else { // IE及其他浏览器
				filename = URLEncoder.encode(filename, "utf-8");
				filename = filename.replace("+"," ");
			}
			return filename;
		}


	/**
	 * 文件上传（按日期生产目录）
	 * @param file
	 * @return
	 */
		public static String upload(MultipartFile file){
			String upload_url="";
			try {
				if (file.isEmpty()){
					return upload_url;
				}
				String fileName = file.getName();
				//按日期生产文件夹
				String dateFile = DateUtil.format(new Date(),"yyyyMMdd" );
				String path = dateFile;
				File dest = new File(path+"/"+fileName);
				if (!dest.getParentFile().exists()){
					dest.getParentFile().mkdirs();//判断文件父目录是否存在
				}
				file.transferTo(dest);//保存文件
				upload_url = dest.toString();
			}catch (Exception e){
				e.printStackTrace();
			}
			return upload_url;
		}
}
