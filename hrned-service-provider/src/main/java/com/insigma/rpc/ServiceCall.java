
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
     * 调用省厅就业创业接口相关服务
     * @param INTERFACE_CONFIG_ID 方案编号
     * @param INTERFACE_SCRIPT_ID 脚本编号
     * @param requestmap 请求数据 hashmap格式
     * @return
     */
    public  R callService(String INTERFACE_CONFIG_ID,String INTERFACE_SCRIPT_ID,String requestmap) {
        List<Row> list=new ArrayList<Row>();
        try {
            //数据访问接口参数
            //String QUERY_PARAM = "[{paramBM:\"AAC002\",paramValue:\"610425198909152612\",paramType:\"String\",paramMC:\"身份证号码\"}]";
            //将hashmap数据转换成字符串
            String QUERY_PARAM=parseMapToQueryParam(requestmap);
            //调用接口
            DataSourceSimpleDllServiceStub stub = new DataSourceSimpleDllServiceStub();
            DataSourceSimpleDllServiceStub.DSM_GENERAL dsm_generalSM_GENERAL = new DataSourceSimpleDllServiceStub.DSM_GENERAL();
            //interface_config_id 方案编号
            dsm_generalSM_GENERAL.setINTERFACE_CONFIG_ID(INTERFACE_CONFIG_ID);
            //interface_script_id 脚本编号
            dsm_generalSM_GENERAL.setINTERFACE_SCRIPT_ID(INTERFACE_SCRIPT_ID);
            //query_param 参数价
            dsm_generalSM_GENERAL.setQUERY_PARAM(QUERY_PARAM);
            //调用接口
            DataSourceSimpleDllServiceStub.DSM_GENERALResponse resp = stub.dSM_GENERAL(dsm_generalSM_GENERAL);
            //接口返回xml
            String xmlData = resp.getDSM_GENERALReturn();
            System.out.println("response:"+xmlData);

            //xmlData是调用DataAccessKit.DSM_GENERAL生成的xml信息

            //获取所有的头信息
            List<Head> heads = DataAccessKitUtil.getFAHead(xmlData);
            //打印出头信息中的flag(反馈代码)和Mess(反馈信息)
            for (Head head : heads) {
                System.out.println("head flag : " + head.getFlag());
                System.out.println("head Mess : " + head.getMess());
                if(!head.getFlag().equals("0001")){
                    return  R.error("全国就业监测国家数据共享交换平台接调用失败");
                }
            }

            String tabName = "";
            //xmlData是调用DataAccessKit.DSM_GENERAL生成的xml信息
            //解析出来的脚本头信息封装在HashMap中String类型的表示数据集的名称
            //Head类型表示脚本头信息其中flag:表示脚本头反馈代码0002表示脚本执行成功，具体
            //请参考附录1，Mess表示反馈信息
            HashMap<String, Head> head = DataAccessKitUtil.getScriptHead(xmlData);
            Set<Map.Entry<String, Head>> entrys = head.entrySet();

            String flag="";
            String mess="";
            for (Map.Entry<String, Head> entry : entrys) {
                //打印出数据集名称
                tabName = entry.getKey();
                System.out.println(tabName);
                //打印出脚本头信息的反馈参数和反馈信息
                System.out.println("flag 1 :" + entry.getValue().getFlag() + ",mess 1 : " + entry.getValue().getMess());
                flag=entry.getValue().getFlag();
            }
            //flag 为0002代表请求成功 脚本执行成功
            if(flag.equals("0002")){
                //xmlData是调用DataAccessKit.DSM_GENERAL生成的xml信息，"DY01"为数据集名称
                //Table中有三个属性分别是name,columns和keys，本接口主要用到name和columns两个属性
                //name表示数据集的名称，colums表示字段集List
                //column中有三个属性分别是name,type和value

                //list是返回的结果集
                List<Table> tableList = DataAccessKitUtil.praseXml(xmlData, tabName);
                
                list=parseTableToRow(tableList);
            }else{
                return  R.error( "全国就业监测国家数据共享交换平台接调用失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  R.error( "全国就业监测国家数据共享交换平台接调用失败!"+e.getMessage());
        }
        //如果返回结果列表大于1 证明为多条结果返回
        if(list.size()>0){
            return  R.success(list.get(0));
        }else{
            return  R.fail(BusiStatus.NODATA, "无数据");
        }
    }
   
    /**
     * 将hashmap转换成以下"[{paramBM:\"AAC002\",paramValue:\"610425198909152612\",paramType:\"String\",paramMC:\"身份证号码\"}]";
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
     * 将kit接口返回对象转换成row对象
     * @param tableList
     * @return
     */
    private  List<Row>  parseTableToRow(List<Table> tableList){
        List<Row> rowList=new ArrayList<Row>();
        if(null!=tableList){
            for (Table table : tableList) {
                Row temprow = new Row();
                String name = table.getName();
                //System.out.println("tabName : " + name);   //打印出数据集名称
                List<Column> columns = table.getColumns();//获取结果集每张表的信息
                for (Column column : columns) {               //获取表的具体信息
                    //System.out.println(column.getName().toLowerCase());       //打印出记录的名称
                    //System.out.println(column.getValue());      //打印出记录的值
                    try {
                        //通过对象反射机制设置价值
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