
package com.insigma.rpc;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.insigma.common.enums.BusiStatus;
import com.insigma.common.struct.R;
import com.insigma.common.struct.Row;
import com.insigma.common.util.JsonUtils;
import com.insigma.rpc.client.DataAccessKitUtil;
import com.insigma.rpc.client.DataSourceSimpleDllServiceStub;
import com.insigma.rpc.client.entity.Column;
import com.insigma.rpc.client.entity.Head;
import com.insigma.rpc.client.entity.Table;


/**
 * Created by wengsh
 */
public class ServiceCall {

    /**
     * ����ʡ����ҵ��ҵ�ӿ���ط���
     * @param INTERFACE_CONFIG_ID �������
     * @param INTERFACE_SCRIPT_ID �ű����
     * @param requestmap �������� hashmap��ʽ
     * @return
     */
    public  R callService(String INTERFACE_CONFIG_ID,String INTERFACE_SCRIPT_ID,String requestmap) {
        List<Row> list=new ArrayList<Row>();
        try {
            //���ݷ��ʽӿڲ���
            //String QUERY_PARAM = "[{paramBM:\"AAC002\",paramValue:\"610425198909152612\",paramType:\"String\",paramMC:\"���֤����\"}]";
            //��hashmap����ת�����ַ���
            String QUERY_PARAM=parseMapToQueryParam(requestmap);
            //���ýӿ�
            DataSourceSimpleDllServiceStub stub = new DataSourceSimpleDllServiceStub();
            DataSourceSimpleDllServiceStub.DSM_GENERAL dsm_generalSM_GENERAL = new DataSourceSimpleDllServiceStub.DSM_GENERAL();
            //interface_config_id �������
            dsm_generalSM_GENERAL.setINTERFACE_CONFIG_ID(INTERFACE_CONFIG_ID);
            //interface_script_id �ű����
            dsm_generalSM_GENERAL.setINTERFACE_SCRIPT_ID(INTERFACE_SCRIPT_ID);
            //query_param ������
            dsm_generalSM_GENERAL.setQUERY_PARAM(QUERY_PARAM);
            //���ýӿ�
            DataSourceSimpleDllServiceStub.DSM_GENERALResponse resp = stub.dSM_GENERAL(dsm_generalSM_GENERAL);
            //�ӿڷ���xml
            String xmlData = resp.getDSM_GENERALReturn();
            System.out.println("response:"+xmlData);

            //xmlData�ǵ���DataAccessKit.DSM_GENERAL���ɵ�xml��Ϣ

            //��ȡ���е�ͷ��Ϣ
            List<Head> heads = DataAccessKitUtil.getFAHead(xmlData);
            //��ӡ��ͷ��Ϣ�е�flag(��������)��Mess(������Ϣ)
            for (Head head : heads) {
                System.out.println("head flag : " + head.getFlag());
                System.out.println("head Mess : " + head.getMess());
                if(!head.getFlag().equals("0001")){
                    return  R.error("ȫ����ҵ���������ݹ�����ƽ̨�ӵ���ʧ��");
                }
            }

            String tabName = "";
            //xmlData�ǵ���DataAccessKit.DSM_GENERAL���ɵ�xml��Ϣ
            //���������Ľű�ͷ��Ϣ��װ��HashMap��String���͵ı�ʾ���ݼ�������
            //Head���ͱ�ʾ�ű�ͷ��Ϣ����flag:��ʾ�ű�ͷ��������0002��ʾ�ű�ִ�гɹ�������
            //��ο���¼1��Mess��ʾ������Ϣ
            HashMap<String, Head> head = DataAccessKitUtil.getScriptHead(xmlData);
            Set<Map.Entry<String, Head>> entrys = head.entrySet();

            String flag="";
            String mess="";
            for (Map.Entry<String, Head> entry : entrys) {
                //��ӡ�����ݼ�����
                tabName = entry.getKey();
                System.out.println(tabName);
                //��ӡ���ű�ͷ��Ϣ�ķ��������ͷ�����Ϣ
                System.out.println("flag 1 :" + entry.getValue().getFlag() + ",mess 1 : " + entry.getValue().getMess());
                flag=entry.getValue().getFlag();
            }
            //flag Ϊ0002��������ɹ� �ű�ִ�гɹ�
            if(flag.equals("0002")){
                //xmlData�ǵ���DataAccessKit.DSM_GENERAL���ɵ�xml��Ϣ��"DY01"Ϊ���ݼ�����
                //Table�����������Էֱ���name,columns��keys�����ӿ���Ҫ�õ�name��columns��������
                //name��ʾ���ݼ������ƣ�colums��ʾ�ֶμ�List
                //column�����������Էֱ���name,type��value

                //list�Ƿ��صĽ����
                List<Table> tableList = DataAccessKitUtil.praseXml(xmlData, tabName);
                
                list=parseTableToRow(tableList);
            }else{
                return  R.error( "ȫ����ҵ���������ݹ�����ƽ̨�ӵ���ʧ��!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  R.error( "ȫ����ҵ���������ݹ�����ƽ̨�ӵ���ʧ��!"+e.getMessage());
        }
        //������ؽ���б����1 ֤��Ϊ�����������
        if(list.size()>0){
            return  R.success(list.get(0));
        }else{
            return  R.fail(BusiStatus.NODATA, "������");
        }
    }
   
    /**
     * ��hashmapת��������"[{paramBM:\"AAC002\",paramValue:\"610425198909152612\",paramType:\"String\",paramMC:\"���֤����\"}]";
     * @param map
     * @return
     */
    private  String parseMapToQueryParam(String reqjson){
    	HashMap map=JsonUtils.jsonToPojo(reqjson,HashMap.class);
        JSONArray jsonArray=new JSONArray();
        Set<Map.Entry<String,String>> set=map.keySet();
        Iterator iterator=set.iterator();
        while(iterator.hasNext()){
            String key=(String)iterator.next();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("paramBM",key.toUpperCase());//key
            jsonObject.put("paramValue",map.get(key));//value
            jsonObject.put("paramType","String");//
            jsonObject.put("paramMC",key);
            jsonArray.add(jsonObject);
        }
        String result=jsonArray.toString();
        System.out.println("request:"+result);
        return result;
    }

    /**
     * ��kit�ӿڷ��ض���ת����row����
     * @param tableList
     * @return
     */
    private  List<Row>  parseTableToRow(List<Table> tableList){
        List<Row> rowList=new ArrayList<Row>();
        if(null!=tableList){
            for (Table table : tableList) {
                Row temprow = new Row();
                String name = table.getName();
                //System.out.println("tabName : " + name);   //��ӡ�����ݼ�����
                List<Column> columns = table.getColumns();//��ȡ�����ÿ�ű����Ϣ
                for (Column column : columns) {               //��ȡ��ľ�����Ϣ
                    //System.out.println(column.getName().toLowerCase());       //��ӡ����¼������
                    //System.out.println(column.getValue());      //��ӡ����¼��ֵ
                    try {
                        //ͨ��������������ü�ֵ
                        String method_name = "set" + column.getName().substring(0, 1) + column.getName().toLowerCase().substring(1).toLowerCase();
                        //System.out.println("method_name=" + method_name);
                        Method method = temprow.getClass().getMethod(method_name, column.getValue().getClass());
                        method.invoke(temprow, column.getValue());
                    } catch (Exception ex) {
                        //ex.printStackTrace();;
                    }
                }
                rowList.add(temprow);
            }
        }
        return rowList;
    }

}