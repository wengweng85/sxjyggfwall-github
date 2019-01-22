package com.insigma.cloud.common.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.cloud.common.fastdfs.FastDFSClient;
import com.insigma.mvc.model.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 标准化平台请求自定义工具类
 *
 */
public class HttpUtil<T extends UserInfo> {

	private Log log = LogFactory.getLog(HttpUtil.class);

	public AjaxReturnMsg post(Item item, T t) {
		 return post(item,t,null);
	}
	/**
	 * 获取陕西就业相关接口数据
	 * 
	 * @param item
	 * @param t
	 * @return
	 */
	public AjaxReturnMsg post(Item item, T t,List<SuploadFile> filelist) {
		HashMap map = new HashMap();
		// 请求数据
		String business_info = JSONObject.toJSONString (t);
		JSONObject jsonObject = null;
	    //直接调用业务平台
		if (item.getItem_submit_type().equals("1")) {
			map.put("item_code", item.getItem_code());
			map.put("aae011", "SXJYGGFW");
			map.put("business_id", "SXJYGGFW");
			map.put("business_info", business_info);
			map.put("name", t.getName());// 申请人姓名/单位名称 人员填写姓名，单位填写单位名称
			map.put("number", t.getUsername());// 身份证/社会信用号 人员填写身份证，单位填写社会信用号
			map.put("ztlx", t.getUsertype().equals("1") ? "2" : "1");// 主体类型// 1：单位；2：人员
			map.put("handle_type", "20");// 受理渠道 1：APP；2：自助外部系统；20：网上申报
			HashMap requesdata = new HashMap();
			String requestdata_string = JSONObject.toJSONString(map);
			requesdata.put("data", requestdata_string);
			log.info("-->" + requestdata_string);
			jsonObject = HttpClientUtil.doPost(item.getBusiness_senddeclareinfo(), requesdata,"gbk");
		}
		//调用标准化平台
		else {
			//文件上传
			PostMethod filePost = null;
			HttpClient client = null;
			Part[] parts = null;
			try {
				if(filelist!=null){
					List<PubFile> listFinal = new ArrayList<PubFile>(); //最终传递的集合    
					 //写入文件到临时文件夹
		            for(SuploadFile file: filelist){
		                String filePath = file.getAaa007();
		                String path = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
		                byte[] bytes = FastDFSClient.downFile(filePath);
		                String finalCataPath = getFilePath();
		                String filePathnew = finalCataPath+File.separator+path;
		                OutputStream os = new FileOutputStream(filePathnew);
		                os.write(bytes, 0, bytes.length);
		                os.flush();
		                os.close();
		                //组装新的传递数据
		                PubFile pub = new PubFile();
		                pub.setFileIdentify(file.getAaa004());
		                pub.setFileName(file.getAaa003());
		                pub.setFilePath(filePathnew);
		                listFinal.add(pub);
		            }
					parts = new Part[7+filelist.size()];
				    parts[0] =  new StringPart("item_code", item.getItem_code());
		            parts[1] =  new StringPart("name",t.getName());// 申请人姓名/单位名称 人员填写姓名，单位填写单位名称
		            parts[2] =  new StringPart("number", t.getUsername());// 身份证/社会信用号 人员填写身份证，单位填写社会信用号
		            parts[3] =  new StringPart("ztlx",t.getUsertype().equals("1") ? "2" : "1");// 主体类型// 1：单位；2：人员
		            parts[4] =  new StringPart("handle_type", "20");// 受理渠道 1：APP；2：自助外部系统；20：网上申报
		            parts[5] =  new StringPart("business_sys", "222");
		            parts[6] =  new StringPart("business_info", business_info);
		            // CustomFilePart 第一个参数待定
		            for(int i=0;i<listFinal.size();i++){
		            	parts[7+i] = new CustomFilePart(listFinal.get(i).getFileIdentify(), listFinal.get(i).getFileName(), new File(listFinal.get(i).getFilePath()));
		            }
				}else{
					parts = new Part[7];
					parts[0] =  new StringPart("item_code", item.getItem_code(),"gbk");
					parts[1] =  new StringPart("name",t.getName(),"gbk");// 申请人姓名/单位名称 人员填写姓名，单位填写单位名称
					parts[2] =  new StringPart("number", t.getUsername(), "gbk");// 身份证/社会信用号 人员填写身份证，单位填写社会信用号
					parts[3] =  new StringPart("ztlx",t.getUsertype().equals("1") ? "2" : "1", "gbk");// 主体类型// 1：单位；2：人员
					parts[4] =  new StringPart("handle_type", "20", "gbk");// 受理渠道 1：APP；2：自助外部系统；20：网上申报
		            parts[5] =  new StringPart("business_sys", "222", "gbk");
		            parts[6] =  new StringPart("business_info", business_info, "gbk");
				}
				//文件上传
				filePost = new PostMethod(item.getBsplatform_senddeclareinfo());
				client = new HttpClient();
				filePost.setRequestEntity(new MultipartRequestEntity(parts,filePost.getParams()));
				int status = client.executeMethod(filePost);
				byte[] b = filePost.getResponseBody();
				if (status == HttpStatus.SC_OK) {
					jsonObject = JSONObject.parseObject(new String(b));
					System.out.println(jsonObject.toString()+"==========");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				filePost.releaseConnection();
				((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
			}
		}
		if (jsonObject != null) {
			if (jsonObject.get("status").equals("1")) {
				if (jsonObject.get("msg") != "") {
					if (jsonObject.get("msg").equals("成功")) {
						return AjaxReturnMsg.success("成功",jsonObject.getString("msg"));
					} else {
						JSONObject msgjson = JSONObject.parseObject(jsonObject.getString("msg"));
						return AjaxReturnMsg.success("成功", msgjson);
					}
				} else {
					return AjaxReturnMsg.success("成功");
				}
			} else {
				return AjaxReturnMsg.fail((String) jsonObject.get("msg"));
			}
		} else {
			return AjaxReturnMsg.fail("调用标准化平台接口失败");
		}
	}

	/**
	 * changeCharset
	 * @param str
	 * @param oldCharset
	 * @param newCharset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset,String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}
	
	/**
	 * getFilePath
	 * @return
	 */
	public String getFilePath(){
        //获取跟目录
        File path = new File( this.getClass().getResource("/").getPath());
        if(!path.exists()) {
            path = new File("");
         System.out.println("path:"+path.getAbsolutePath());
        }
        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static"+File.separator+"images"+File.separator+"upload"+File.separator);
        if(!upload.exists()) {
            upload.mkdirs();
            System.out.println("upload url:"+upload.getAbsolutePath());
        }
        return upload.getAbsolutePath();
    }
	
	/**
	 * 从标准化平台获取材料列表
	 * @param item
	 * @return
	 */
	public List<ItemMaterial> getItemMaterialList(Item item) throws AppException{
		 List<ItemMaterial> list = new ArrayList<ItemMaterial>();
		 CloseableHttpClient httpCilent = HttpClients.createDefault();
		 String urlStr = item.getBsplatform_material()+"&item_code="+item.getItem_code();
		 HttpGet httpGet = new HttpGet(urlStr);
		 try {
			 CloseableHttpResponse response   = httpCilent.execute(httpGet);
			 HttpEntity entity = response.getEntity();
	         String result = EntityUtils.toString(entity, "UTF-8");
	         JSONObject json =JSONObject.parseObject(result);
	         if("1".equals(json.get("status"))){
	        	 JSONArray array =  JSONArray.parseArray(json.getString("data"));
	        	 for (int i = 0; i < array.size(); i++) {
	        		 JSONObject json2 = array.getJSONObject(i);
	        		 ItemMaterial  item2 = new ItemMaterial();
	        		 item2.setMaterial_code(json2.getString("material_code"));
	        		 item2.setMaterial_name(json2.getString("material_name"));
	        		 item2.setNecessity(json2.getString("necessity"));
	        		 list.add(item2);
				}
	         }
		 } catch (IOException e) {
		     e.printStackTrace();
	         throw  new AppException("调用标准化平台接口失败");
		 }finally {
		     try {
		         httpCilent.close();//释放资源
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
		 }
		 return list;
	}
	
}
