package com.wode.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.wode.common.constant.UserConstant;
import com.wode.common.util.image.ImageUtil;
import com.wode.common.web.BaseController;


/**
 * 文件util
 *
 * @author mengkaixuan
 */
@Component
public class FileUtils {

	private static String BASE_UPLOAD_PATH;

	private static ReloadableResourceBundleMessageSource messageSource;


	@Value("${upload.dir:}")
	public void setPrivateName(String privateName) {
		FileUtils.BASE_UPLOAD_PATH = privateName;
	}

	/**
	 * get file name
	 *
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {

		return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
	}

	/**
	 * get file suffix
	 *
	 * @param filePath
	 * @return
	 */
	public static String getFileSuffix(String filePath) {
		String filename = FileUtils.getFileName(filePath);
		String suffix = filename.substring(filename.lastIndexOf('.') + 1);
		return suffix;
	}

	/**
	 * Move file to destPath
	 *
	 * @param srcFile
	 * @param destPath
	 * @return
	 */
	public static String Move(String srcFile, String destPath) {
		// Destination directory
		mkDir(new File(destPath));
		String fileName = "";
		String filePath = "";
		File file = new File(srcFile);
		if (file.getPath().indexOf('.') != -1) {
			String path = file.getPath();
			// path = path.substring(0,path.lastIndexOf('\\'));
			// filePath = new File(path);
			fileName = path.substring(path.lastIndexOf(File.separator) + 1);
			System.out.println(fileName);
		}
		// File dir = new File(destPath);
		// Move file to new directory
		File toFile = new File(destPath, fileName);
		boolean success = file.renameTo(toFile);
		if (success)
			return fileName;
		else
			return null;
	}

	public static void mkDir(File file) {
		File filePath = new File(file.getPath());
		if (file.getPath().indexOf('.') != -1) {
			String path = file.getPath();
			path = path.substring(0, path.lastIndexOf(File.separator));
			filePath = new File(path);
		}
		if (filePath.getParentFile().exists()) {
			filePath.mkdir();
		} else {
			mkDir(filePath.getParentFile());
			filePath.mkdir();
		}
	}

	public static void main(String[] args) {
		String bc = FileUtils.getFileSuffix("E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\monitors.xml");

//		String bc = FileUtils
//				.Move("E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\monitors.xml",
//						"d:\\abvc\\def\\"
//								+ new SimpleDateFormat("YY/MM/DD")
//										.format(new Date()));
		System.out.println(bc);
		// mkDir(file);
	}

	/**
	 * Copy file to newPath
	 *
	 * @param oldPath
	 * @param newPath
	 */
	public static void Copy(String oldPath, String newPath) {
		try {
			mkDir(new File(newPath));
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[8 * 1024];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("error  ");
			e.printStackTrace();
		}
	}

	public static String fileCopy(String src, String desPath) {
		String target = desPath + "/" + new SimpleDateFormat("YY/MM/dd").format(new Date());
		File srcFile = new File(BASE_UPLOAD_PATH + src);
		File targetFile = new File(BASE_UPLOAD_PATH + target);
		try {
			org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, targetFile);
			InputStream fin = new FileInputStream(srcFile);
			byte[] buf = new byte[fin.available()];
			fin.read(buf);
			new ImageUtil().savePicProperty(buf, targetFile.getPath() + "/" + srcFile.getName(), messageSource);
		} catch (IOException e) {
			e.printStackTrace(); // 需要修改异常
		}
		deleteFile(src);
		return target + "/" + srcFile.getName();
	}

	public static String fileCopy(String src) {
		String target = "upload/course/" + new SimpleDateFormat("YY/MM/dd").format(new Date());
		File srcFile = new File(BASE_UPLOAD_PATH + src);
		File targetFile = new File(BASE_UPLOAD_PATH + target);
		try {
			org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, targetFile);
			InputStream fin = new FileInputStream(srcFile);
			byte[] buf = new byte[fin.available()];
			fin.read(buf);
			new ImageUtil().savePicProperty(buf, targetFile.getPath() + "/" + srcFile.getName(), messageSource);
		} catch (IOException e) {
			e.printStackTrace(); // 需要修改异常
		}
		deleteFile(src);
		return target + "/" + srcFile.getName();
	}

	public static String fileCopyV(String src) {
		String target = "upload/video/" + new SimpleDateFormat("YY/MM/dd").format(new Date());
		File srcFile = new File(BASE_UPLOAD_PATH + src);
		File targetFile = new File(BASE_UPLOAD_PATH + target);
		try {
			org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, targetFile);
			InputStream fin = new FileInputStream(srcFile);
			byte[] buf = new byte[fin.available()];
			fin.read(buf);
			new ImageUtil().savePicProperty(buf, targetFile.getPath() + "/" + srcFile.getName(), messageSource);
		} catch (IOException e) {
			e.printStackTrace(); // 需要修改异常
		}
		deleteFile(src);
		return target + "/" + srcFile.getName();
	}

	/**
	 * 删除指定路径的目录下所有文件和文件夹
	 *
	 * @param directoryPath 目录的路径
	 * @return boolean
	 */
	public static boolean deleteAllChildFile(String directoryPath) {
		boolean flag = true;
		File file = new File(directoryPath);
		if (!file.exists() || !file.isDirectory()) {
			flag = false;
		} else {
			File[] fileArray = file.listFiles();
			for (int i = 0; i < fileArray.length; i++) {
				File temp = fileArray[i];
				//目录类型
				if (temp.isDirectory()) {
					flag = deleteAllChildFile(temp.getAbsolutePath());
					if (!flag) {
						break;
					}
				}
				//最后对文件或空目录进行删除
				flag = temp.delete();
			}
		}
		return flag;
	}

	/**
	 * 删除单个文件
	 *
	 * @param sPath 被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(BASE_UPLOAD_PATH + sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	@Autowired
	public void setMessageSource(
			ReloadableResourceBundleMessageSource messageSource) {
		FileUtils.messageSource = messageSource;
	}

	public static String fileCopyByFile(String src, String desPath) {
		File srcFile = new File(BASE_UPLOAD_PATH + src);
		String target = desPath + "/" + new SimpleDateFormat("YY/MM/dd").format(new Date()) + "/";
		File targetFile = new File(BASE_UPLOAD_PATH + target);
		try {
			org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace(); // 需要修改异常
		}
		return target + srcFile.getName();
	}

	/**
	 * 将临时文件夹中的文件复制到目标文件夹并保存指定尺寸的图片
	 *
	 * @param src
	 * @param desPath
	 * @return String
	 */
	public static String fileCopyAndSaveFixedSizePic(String src, String desPath) {
		File srcFile = new File(BASE_UPLOAD_PATH + src);
		String target = desPath + "/" + new SimpleDateFormat("YY/MM/dd").format(new Date()) + "/";
		File targetFile = new File(BASE_UPLOAD_PATH + target);
		String imageName = "";
		try {
			org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, targetFile);
			InputStream fin = new FileInputStream(srcFile);
			byte[] buf = new byte[fin.available()];
			fin.read(buf);
			imageName = ImageUtil.saveFixedPic(buf, targetFile.getPath() + "/",
					srcFile.getName().substring(0, srcFile.getName().lastIndexOf(".")), UserConstant.UPLOADIMAGEWIDTH,
					UserConstant.UPLOADIMAGEHEIGHT);
		} catch (IOException e) {
			e.printStackTrace(); // 需要修改异常
		}
		return target + imageName;
	}


	/**
	 * byte数组转换成16进制字符串
	 *
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
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


	/**
	 * 根据文件流读取图片文件真实类型
	 *
	 * @param is
	 * @return
	 */
	public static String getPicType(InputStream is) {
		byte[] b = new byte[4];
		try {
			is.read(b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = bytesToHexString(b).toUpperCase();

		if (type.contains("FFD8FF")) {
			return "jpg";
		} else if (type.contains("89504E47")) {
			return "png";
		} else if (type.contains("47494638")) {
			return "gif";
//		} else if (type.contains("49492A00")) {
//			return "tif";
		} else if (type.contains("424D")) {
			return "bmp";
		} else if (type.contains("25504446")) {
			return "pdf";
		}

		return null;
	}


}