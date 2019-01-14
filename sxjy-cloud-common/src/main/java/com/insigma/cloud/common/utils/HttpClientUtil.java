package com.insigma.cloud.common.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ����HttpClient����post����Ĺ�����
 * @author xxx
 *
 */
public class HttpClientUtil {
	private static String  CHARTSET="UTF-8";
	/**
	 * doPost
	 * @param url
	 * @param map
	 * @return
	 */
	public static JSONObject doPost(String url, Map<String,String> map){
		return doPost(url,map,CHARTSET);
	}

	/**
	 * doPost
	 * @param url
	 * @return
	 */
	public static JSONObject doPost(String url){
		return doPost(url,null,CHARTSET);
	}

	/**
	 * doPost
	 * @param url
	 * @param map
	 * @param charset
	 * @return
	 */
	public static JSONObject doPost(String url,Map<String,String> map,String charset){
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		HttpResponse response = null;
		try{
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			//���ò���
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			if(map!=null){
				Iterator iterator = map.entrySet().iterator();
				while(iterator.hasNext()){
					Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
					list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
				}
				if(list.size() > 0){
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
					httpPost.setEntity(entity);
				}
			}
			response = httpClient.execute(httpPost);
			if(response != null){
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity resEntity = response.getEntity();
					if(resEntity != null){
						result = EntityUtils.toString(resEntity,charset);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		JSONObject jsonobject = JSONObject.parseObject(result);
		return jsonobject;
	}

	/**
	 * doGet
	 * @param url
	 * @param charset
	 * @return
	 */
	public static JSONObject doGet(String url,String charset){
		HttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;
		try{
			httpClient = new SSLClient();
			httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			if(response != null){
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity resEntity = response.getEntity();
					if(resEntity != null){
						result = EntityUtils.toString(resEntity,charset);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		JSONObject jsonobject = JSONObject.parseObject(result);
		return jsonobject;
	}

	/**
	 * doPostJson
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPostJson(String url, String json) {
		// ����Httpclient����
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String result = "";
		try {
			// ����Http Post����
			HttpPost httpPost = new HttpPost(url);
			// ������������
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// ִ��http����
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(response.getEntity(), "utf-8");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}


	/**
	 * doGet
	 * @param url
	 * @return
	 */
	public static JSONObject doGet(String url){
		return doGet(url,CHARTSET);
	}

}

