package com.insigma.cloud.common.utils;

import java.util.Random;

public class RandomNumUtil {

	/**
	 * ������֤��
	 * @param size
	 * @return
	 */
	public static String getRandomNum(int size){
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for(int i=0;i<size;i++){
			int random = r.nextInt(10);
			sb.append(random);
		}
		return sb.toString();
	}

	public static String getRandomString(int length) { //length��ʾ�����ַ����ĳ���
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}
