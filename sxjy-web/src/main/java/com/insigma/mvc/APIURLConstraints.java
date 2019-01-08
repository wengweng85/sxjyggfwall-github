package com.insigma.mvc;

/**
 * api地址约束
 * @author wengsh
 *
 */
public class APIURLConstraints {
	
	//api-auth
	private static String API_AUTH="/api-auth";
	
	///api-sxjysy
	private static String API_SXJYSY="/api-sxjysy";
		
	//参数列表
	public static String API_AUTH_CODETYPELIST=API_AUTH+"/codetype/getInitcodetypeList";
	//参数明细
	public static String API_AUTH_CODEVALUElIST=API_AUTH+"/codetype/getInitCodeValueList";
	
	
	//ac11 detail 
	public static String API_AC11_DETAIL=API_SXJYSY+"/ac11";

}
