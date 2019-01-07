package com.insigma.cloud.common.dto;

import com.github.pagehelper.PageInfo;
import com.insigma.cloud.common.utils.JSONUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

/**
 * ajax��Ϣ����
 *
 * @author admin
 */

@ApiModel(value="�ӿ�ͨ�÷�������̬Ϊjson")
public class AjaxReturnMsg   {

    private static final Logger logger = LoggerFactory.getLogger(AjaxReturnMsg.class);

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "ϵͳ����״̬��",value = "\"200\",\"�ɹ�\" \"40001\",\"appkeyΪ��\"\"40002\",\"appkey����ȷ\"\"40003\",\"tokenΪ��,���ȵ�¼\"\"40004\",\"tokenֵ����ȷ���Ѿ�����,�����µ�¼\"\"40005\",\"��¼��Ϣ��token��Ϣ��ƥ��,�Ƿ�������ȷ��\"\"40006\",\"û�з��ʴ˷����Ȩ�޻��ַ��ַ,��ȷ��\"\"50001\",\"api�����쳣\"")
    private String syscode; // ϵͳ����״̬��,Ĭ��200
    @ApiModelProperty(name="ҵ��״̬��",value="true �ɹ� flase ʧ��")
    private boolean success; // ҵ��״̬�룬Ĭ��Ϊtrue
    @ApiModelProperty(name="ҵ��״̬����˵��",value="ҵ��״̬����˵��")
    private String message = ""; // ҵ��״̬����˵��
    @ApiModelProperty(name="ҵ��״̬����״̬��",value="ҵ��״̬����״̬��")
    private String code = "";
    @ApiModelProperty(name="ҵ�񷵻ض��󼯺�",value="ҵ�񷵻ض��󼯺�")
    private Object obj; //����
    @ApiModelProperty(name="��ҳ����֮����",value="��ҳ����֮����")
    private Long total; //��ҳ�����֮����

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private AjaxReturnMsg() {
        this.syscode = SysCode.SYS_CODE_200.getCode(); // ϵͳ����״̬��,Ĭ��200
        this.success = true; // ҵ��״̬�룬Ĭ��Ϊtrue
    }

    /**
     * У�鲢���ض���
     *
     * @param result
     * @return
     */
    public static AjaxReturnMsg validate(BindingResult result) {
        FieldError fielderror = result.getFieldErrors().get(result.getErrorCount() - 1);
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(fielderror.getDefaultMessage());
        dto.setObj(fielderror.getField());
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param message
     * @return
     */
    public static AjaxReturnMsg success(String message) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param
     * @return
     */
    public static AjaxReturnMsg success(String message, Object obj) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setObj(obj);
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * �ɹ�����
     *
     * @param o
     * @return
     */
    public static AjaxReturnMsg  success(Object o) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(o);
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * �ɹ����� ���ط�ҳ����Ϣ ���ڽӿڷ���
     *
     * @param pageinfo
     * @return
     */
    public static AjaxReturnMsg success(PageInfo pageinfo) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(pageinfo);
        dto.setTotal(pageinfo.getTotal());
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * �ɹ����ط�ҳ����manager����ƽ̨
     *
     * @param pageinfo
     * @return
     */
    public static HashMap<String, Object> success_hashmap_response(PageInfo pageinfo) {
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        hashmap.put("total", pageinfo.getTotal());
        hashmap.put("rows", pageinfo.getList());
        return hashmap;
    }

    /**
     * �ɹ�����
     *
     * @param pageinfo
     * @return
     */
    public static HashMap<String, Object> success_hashmap_response(PageInfo pageinfo, Object o) {
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        hashmap.put("pageinfo", pageinfo);
        hashmap.put("obj", o);
        return hashmap;
    }

    /**
     * ʧ�ܷ���
     *
     * @param message
     * @return
     */
    public static AjaxReturnMsg fail(String message) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * ʧ�ܷ���
     *
     * @param message
     * @param obj
     * @return
     */
    public static AjaxReturnMsg fail(String message, Object obj) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setMessage(message);
        dto.setObj(obj);
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * ʧ�ܷ���
     *
     * @param obj
     * @return
     */
    public static AjaxReturnMsg fail(Object obj) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setObj(obj);
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * �쳣����
     * @param syscode
     * @param msg
     * @return
     */
    public static AjaxReturnMsg error(SysCode syscode,String msg){
        AjaxReturnMsg dto=new AjaxReturnMsg();
        dto.setSuccess(false);
        dto.setSyscode(syscode.getCode());
        dto.setMessage(msg);
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * �쳣����
     * @param sysCode
     * @return
     */
    public static AjaxReturnMsg error(SysCode sysCode){
        return error(sysCode,sysCode.getName());
    }

    /**
     * �쳣����
     * @return
     */
    public static AjaxReturnMsg error500(){
        return error(SysCode.SYS_API_EXCEPTION);
    }

    /**
     * �쳣����
     * @return
     */
    public static AjaxReturnMsg error500(String message){
        return error(SysCode.SYS_API_EXCEPTION,message);
    }

    /**
     * �쳣����
     * @return
     */
    public static AjaxReturnMsg error40003(){
        return error(SysCode.SYS_TOKEN_EMPTY);
    }

    /**
     * �쳣����
     * @return
     */
    public static AjaxReturnMsg error40004(){
        return error(SysCode.SYS_TOKEN_ERROR);
    }
    /**
     * �쳣����
     *
     * @param e
     * @return
     */
    public static AjaxReturnMsg error(Exception e) {
        return error(SysCode.SYS_API_EXCEPTION,e.getLocalizedMessage());
    }



}
