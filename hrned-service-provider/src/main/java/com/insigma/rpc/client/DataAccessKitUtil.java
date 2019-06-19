package com.insigma.rpc.client;

import com.insigma.rpc.client.entity.Column;
import com.insigma.rpc.client.entity.Head;
import com.insigma.rpc.client.entity.Key;
import com.insigma.rpc.client.entity.Table;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataAccessKitUtil {

	/**
	 * 根据调用数据访问接口服务返回的xml格式的数据集获取数据访问接口方案信息头
	 * @param XMLDATA        客户端传入的XML数据集字符串
	 * @return               List<Head>
	 * @throws Exception
	 */
	public static List<Head> getFAHead(String XMLDATA) throws Exception{
		List<Head> heads = null;
		SAXReader reader = null;
		Document  document = null;
		try {
			reader = new SAXReader();
			document = reader.read(new ByteArrayInputStream(XMLDATA.getBytes("GBK")));
			Element rootElm = document.getRootElement();
			List<Element> faHeadElms = rootElm.elements("FAHEAD");
			if(faHeadElms!=null && !faHeadElms.isEmpty()){
				heads = new ArrayList<Head>();
			}else{
				return null;
			}
			for(int i =0;i<faHeadElms.size();i++){
				Head head = new Head();
				Element faHeadElm = faHeadElms.get(i);
				String Flag = faHeadElm.attributeValue("Flag");
				String Mess = faHeadElm.attributeValue("Mess");
				head.setFlag(Flag);
				head.setMess(Mess);
				heads.add(head);
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally{
			if(document!=null){
				document.clearContent();
			}
			if(reader!=null){
				reader=null;
			}
		}
		return heads;
	}

	/**
	 * 根据调用数据访问接口服务返回的xml格式的数据集获取数据访问接口方案脚本信息头
	 * @param XMLDATA        客户端传入的XML数据集字符串
	 * @return               List<Head>
	 * @throws Exception
	 */
	public static HashMap<String,Head> getScriptHead(String XMLDATA) throws Exception{
		HashMap<String,Head> scriptHead = null;
		SAXReader reader = null;
		Document  document = null;
		try {
			reader = new SAXReader();
			document = reader.read(new ByteArrayInputStream(XMLDATA.getBytes("UTF-8")));
			Element rootElm = document.getRootElement();
			Element scriptHeadElms = rootElm.element("SCRIPTHEAD");
			List<Element> script= scriptHeadElms.elements();
			if(script!=null && !script.isEmpty()){
				scriptHead = new HashMap<String,Head>();
			}else{
				return null;
			}
			for(int i =0;i<script.size();i++){
				Head head = new Head();
				Element scriptHeadElm = script.get(i);
				String Flag = scriptHeadElm.attributeValue("Flag");
				String Mess = scriptHeadElm.attributeValue("Mess");
				head.setFlag(Flag);
				head.setMess(Mess);
				scriptHead.put(scriptHeadElm.getName(), head);
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally{
			if(document!=null){
				document.clearContent();
			}
			if(reader!=null){
				reader=null;
			}
		}
		return scriptHead;
	}

	/**
	 * 解析WebServices客户端发过来的表信息XML字符串
	 * @param xmlData 客户端传入的XML字符串
	 * @param tabName 数据集名称
	 * @return 返回解析后表信息
	 * @throws Exception
	 */
	public static List<Table> praseXml(String xmlData, String tabName) throws Exception{
		SAXReader reader = null;
		Document  document = null;
		try {
			reader = new SAXReader();
			document = reader.read(new ByteArrayInputStream(xmlData.getBytes("GBK")));
			Element rootElm = document.getRootElement();
			Element DataElm = rootElm.element("Data");
			List<Element> tableElms = DataElm.elements("Table");
			List<Table> tables = null;
			if(tableElms!=null && !tableElms.isEmpty()){
				tables = new ArrayList<Table>();
			}else{
				return null;
			}

			for (int i = 0; i < tableElms.size(); i++) {
				Element tableElm = tableElms.get(i);
				if(tabName.equalsIgnoreCase(tableElm.attributeValue("TabName"))){
					List<Element> rowElms = tableElm.elements("rowData");
					if(rowElms!=null && !rowElms.isEmpty()){
						for(int i1 =0;i1<rowElms.size();i1++){
							Table table=new Table();
							table.setName(tableElm.attributeValue("TabName"));
							Element rowElm = rowElms.get(i1);
							List<Element> colnodes = rowElm.elements("ColumnData");
							List<Column> columns=new ArrayList<Column>();
							for (int j = 0; j < colnodes.size(); j++) {
								Column column=new Column();
								column.setName(colnodes.get(j).attributeValue("ColName"));
								column.setType(colnodes.get(j).attributeValue("ColType"));
								column.setValue(colnodes.get(j).attributeValue("ColValue"));
								columns.add(column);
							}
							table.setColumns(columns);

							List<Element> keynodes = rowElm.elements("KeyData");
							List<Key> keys = null;
							if(keynodes!=null && !keynodes.isEmpty()){
								keys = new ArrayList<Key>();
							}
							for (int k = 0; k < keynodes.size(); k++) {
								Key key=new Key();
								key.setName(keynodes.get(k).attributeValue("KeyName"));
								key.setValue(keynodes.get(k).attributeValue("KeyValue"));
								key.setType(keynodes.get(k).attributeValue("KeyType"));
								keys.add(key);
							}
							table.setKeys(keys);
							tables.add(table);
						}

					}

				}
			}
			return tables;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally{
			if(document!=null){
				document.clearContent();
			}
			if(reader!=null){
				reader=null;
			}
		}
	}

}