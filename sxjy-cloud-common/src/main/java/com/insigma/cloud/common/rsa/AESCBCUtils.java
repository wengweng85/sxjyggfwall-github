package com.insigma.cloud.common.rsa;

import com.insigma.cloud.common.exception.AppException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public class AESCBCUtils {

	public static final String KEY_ALGORITHM = "AES";
	// 加密模式
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
	// 字符集
	public static final String ENCODING = "UTF-8";
	// 向量
	public static final String IV_SEED = "1234567812345678";

	/**
	 * AES加密算法
	 *
	 * @param str 密文
	 * @param key 密key
	 * @return
	 */
	public static String encrypt(String str, String key) throws AppException {
		try {
			// 判断Key是否正确
			if (key == null) {
				throw new AppException("AES解密出错:Key为空null");
			}
			// 判断Key是否为16位
			if (key.length() != 16) {
				throw new AppException("AES解密出错：Key长度不是16位");
			}
			/**
			 * 这个地方调用BouncyCastleProvider
			 *让java支持PKCS7Padding
			 */
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			byte[] raw = key.getBytes(ENCODING);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(ENCODING));
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] srawt = str.getBytes(ENCODING);
			/*int len = srawt.length;
			*//* 计算补空格后的长度 *//*
			while (len % 16 != 0)
				len++;
			byte[] sraw = new byte[len];
			*//* 在最后空格 *//*
			for (int i = 0; i < len; ++i) {
				if (i < srawt.length) {
					sraw[i] = srawt[i];
				} else {
					sraw[i] = 32;
				}
			}*/
			byte[] encrypted = cipher.doFinal(srawt);
			return formatString(new String(Base64.encodeBase64(encrypted), "UTF-8"));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new AppException("AES加密出错");
		}
	}

	/**
	 * AES解密算法
	 *
	 * @param str 密文
	 * @param key 密key
	 * @return
	 */
	public static String decrypt(String str, String key) throws AppException {
		try {
			// 判断Key是否正确
			if (key == null) {
				throw new AppException("AES解密出错:Key为空null");
			}
			// 判断Key是否为16位
			if (key.length() != 16) {
				throw new AppException("AES解密出错：Key长度不是16位");
			}
			byte[] raw = key.getBytes(ENCODING);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(ENCODING));
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] bytes = Base64.decodeBase64(str.getBytes("UTF-8"));
			bytes = cipher.doFinal(bytes);
			return new String(bytes, ENCODING);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new AppException("AES解密出错");
		}
	}

	private static String formatString(String sourceStr) {
		if (sourceStr == null) {
			return null;
		}
		return sourceStr.replaceAll("\\r", "").replaceAll("\\n", "");
	}

}