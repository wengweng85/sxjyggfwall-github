package com.insigma.mvc;

/**
 * api��ַԼ��
 * @author wengsh
 *
 */
public class ApiUriContraints {
	
	/**
	 * Ȩ����֤�����ַ
	 */
	private static String API_AUTH="/api-auth";
	
	/**
	 * ��ʧҵ�����ַ
	 */
	private static String API_SXJYSY="/api-sxjysy";
		
	/**
	 * �����б�����ַ
	 */
	public static String API_AUTH_CODETYPELIST=API_AUTH+"/codetype/getInitcodetypeList";
	
	/**
	 * ������ϸ�����ַ
	 */
	public static String API_AUTH_CODEVALUElIST=API_AUTH+"/codetype/getInitCodeValueList";
	
	
	/**
	 * ac11�����ַ
	 */
	public static String API_AC11_DETAIL=API_SXJYSY+"/ac11";

}
