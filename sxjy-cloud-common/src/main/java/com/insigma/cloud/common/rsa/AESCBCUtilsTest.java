package com.insigma.cloud.common.rsa;

import com.insigma.cloud.common.exception.AppException;

public class AESCBCUtilsTest {

	public static void main(String [] args){
		try{
			String data="{\"syscode\":\"200\",\"success\":true,\"message\":\"\",\"code\":\"\",\"obj\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDY5MzcyMTcwMTMsInBheWxvYWQiOiJ7XCJjdXJwYWdlXCI6MSxcImxpbWl0XCI6MTAsXCJvZmZzZXRcIjowLFwidXNlcmlkXCI6XCJDRkJDRURBRTExRUY0NEU5OUEwODFGRTU3RkE5NTRFRFwiLFwidXNlcm5hbWVcIjpcInBlcnNvblwiLFwicGFzc3dvcmRcIjpcImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlXCIsXCJ1c2VydHlwZVwiOlwiMVwiLFwibG9naW50aW1lc1wiOjAsXCJsYXN0bG9naW5pcFwiOlwiMTI3LjAuMC4xXCIsXCJpc2JsYWNrbGlzdFwiOlwiMFwiLFwiaXNzZXRwd2RcIjpmYWxzZX0ifQ.a-Sd2C2UC94oFxPEJh3ZPutN650iGUHUF9ctwjUzeJc\"}}";
			final  String result=AESCBCUtils.encrypt(data,"2E197B1B38B2490D");
			System.out.println(result);
			String result_decode=AESCBCUtils.decrypt(result,"2E197B1B38B2490D");
			System.out.println(result_decode);
		}catch (AppException ex){
			ex.printStackTrace();
		}


	}
}