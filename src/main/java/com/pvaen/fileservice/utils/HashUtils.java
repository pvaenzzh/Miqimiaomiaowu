package com.pvaen.fileservice.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
	
	private static final int PERLENGTH = 1024;

	/**
	 * 获得文件sha256</br>
	 * 如果获取异常, 返回""
	 * @param in
	 * @return
	 */
	public static String getSHA256(InputStream in) {
		MessageDigest messageDigest;
		String encodestr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] bytes = new byte[PERLENGTH];
			int n = 0;
			while((n= in.read(bytes)) != -1) {
				messageDigest.update(bytes, 0, n);	
			}
			encodestr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
		return encodestr;
	}

	/**
	 * 将byte转为16进制
	 * 
	 * @param bytes
	 * @return
	 */
	private static String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				// 1得到一位的进行补0操作
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}

}
