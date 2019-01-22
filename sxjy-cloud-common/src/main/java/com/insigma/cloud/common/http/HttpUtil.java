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
 * ��׼��ƽ̨�����Զ��幤����
 *
 */
public class HttpUtil<T extends UserInfo> {

	private Log log = LogFactory.getLog(HttpUtil.class);

	public AjaxReturnMsg post(Item item, T t) {
		 return post(item,t,null);
	}
	/**
	 * ��ȡ������ҵ��ؽӿ�����
	 * 
	 * @param item
	 * @param t
	 * @return
	 */
	public AjaxReturnMsg post(Item item, T t,List<SuploadFile> filelist) {
		HashMap map = new HashMap();
		// ��������
		String business_info = JSONObject.toJSONString (t);
		JSONObject jsonObject = null;
	    //ֱ�ӵ���ҵ��ƽ̨
		if (item.getItem_submit_type().equals("1")) {
			map.put("item_code", item.getItem_code());
			map.put("aae011", "SXJYGGFW");
			map.put("business_id", "SXJYGGFW");
			map.put("business_info", business_info);
			map.put("name", t.getName());// ����������/��λ���� ��Ա��д��������λ��д��λ����
			map.put("number", t.getUsername());// ���֤/������ú� ��Ա��д���֤����λ��д������ú�
			map.put("ztlx", t.getUsertype().equals("1") ? "2" : "1");// ��������// 1����λ��2����Ա
			map.put("handle_type", "20");// �������� 1��APP��2�������ⲿϵͳ��20�������걨
			HashMap requesdata = new HashMap();
			String requestdata_string = JSONObject.toJSONString(map);
			requesdata.put("data", requestdata_string);
			log.info("-->" + requestdata_string);
			jsonObject = HttpClientUtil.doPost(item.getBusiness_senddeclareinfo(), requesdata,"gbk");
		}
		//���ñ�׼��ƽ̨
		else {
			//�ļ��ϴ�
			PostMethod filePost = null;
			HttpClient client = null;
			Part[] parts = null;
			try {
				if(filelist!=null){
					List<PubFile> listFinal = new ArrayList<PubFile>(); //���մ��ݵļ���    
					 //д���ļ�����ʱ�ļ���
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
		                //��װ�µĴ�������
		                PubFile pub = new PubFile();
		                pub.setFileIdentify(file.getAaa004());
		                pub.setFileName(file.getAaa003());
		                pub.setFilePath(filePathnew);
		                listFinal.add(pub);
		            }
					parts = new Part[7+filelist.size()];
				    parts[0] =  new StringPart("item_code", item.getItem_code());
		            parts[1] =  new StringPart("name",t.getName());// ����������/��λ���� ��Ա��д��������λ��д��λ����
		            parts[2] =  new StringPart("number", t.getUsername());// ���֤/������ú� ��Ա��д���֤����λ��д������ú�
		            parts[3] =  new StringPart("ztlx",t.getUsertype().equals("1") ? "2" : "1");// ��������// 1����λ��2����Ա
		            parts[4] =  new StringPart("handle_type", "20");// �������� 1��APP��2�������ⲿϵͳ��20�������걨
		            parts[5] =  new StringPart("business_sys", "222");
		            parts[6] =  new StringPart("business_info", business_info);
		            // CustomFilePart ��һ����������
		            for(int i=0;i<listFinal.size();i++){
		            	parts[7+i] = new CustomFilePart(listFinal.get(i).getFileIdentify(), listFinal.get(i).getFileName(), new File(listFinal.get(i).getFilePath()));
		            }
				}else{
					parts = new Part[7];
					parts[0] =  new StringPart("item_code", item.getItem_code(),"gbk");
					parts[1] =  new StringPart("name",t.getName(),"gbk");// ����������/��λ���� ��Ա��д��������λ��д��λ����
					parts[2] =  new StringPart("number", t.getUsername(), "gbk");// ���֤/������ú� ��Ա��д���֤����λ��д������ú�
					parts[3] =  new StringPart("ztlx",t.getUsertype().equals("1") ? "2" : "1", "gbk");// ��������// 1����λ��2����Ա
					parts[4] =  new StringPart("handle_type", "20", "gbk");// �������� 1��APP��2�������ⲿϵͳ��20�������걨
		            parts[5] =  new StringPart("business_sys", "222", "gbk");
		            parts[6] =  new StringPart("business_info", business_info, "gbk");
				}
				//�ļ��ϴ�
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
					if (jsonObject.get("msg").equals("�ɹ�")) {
						return AjaxReturnMsg.success("�ɹ�",jsonObject.getString("msg"));
					} else {
						JSONObject msgjson = JSONObject.parseObject(jsonObject.getString("msg"));
						return AjaxReturnMsg.success("�ɹ�", msgjson);
					}
				} else {
					return AjaxReturnMsg.success("�ɹ�");
				}
			} else {
				return AjaxReturnMsg.fail((String) jsonObject.get("msg"));
			}
		} else {
			return AjaxReturnMsg.fail("���ñ�׼��ƽ̨�ӿ�ʧ��");
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
			// �þɵ��ַ���������ַ�����������ܻ�����쳣��
			byte[] bs = str.getBytes(oldCharset);
			// ���µ��ַ����������ַ���
			return new String(bs, newCharset);
		}
		return null;
	}
	
	/**
	 * getFilePath
	 * @return
	 */
	public String getFilePath(){
        //��ȡ��Ŀ¼
        File path = new File( this.getClass().getResource("/").getPath());
        if(!path.exists()) {
            path = new File("");
         System.out.println("path:"+path.getAbsolutePath());
        }
        //����ϴ�Ŀ¼Ϊ/static/images/upload/����������»�ȡ��
        File upload = new File(path.getAbsolutePath(),"static"+File.separator+"images"+File.separator+"upload"+File.separator);
        if(!upload.exists()) {
            upload.mkdirs();
            System.out.println("upload url:"+upload.getAbsolutePath());
        }
        return upload.getAbsolutePath();
    }
	
	/**
	 * �ӱ�׼��ƽ̨��ȡ�����б�
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
	         throw  new AppException("���ñ�׼��ƽ̨�ӿ�ʧ��");
		 }finally {
		     try {
		         httpCilent.close();//�ͷ���Դ
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
		 }
		 return list;
	}
	
}
