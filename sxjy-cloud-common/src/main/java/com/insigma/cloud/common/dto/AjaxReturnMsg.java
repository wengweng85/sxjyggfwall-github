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
 * 接口通用返回类
 *
 * @author admin
 */

@ApiModel(value="接口通用返回类形态为json")
public class AjaxReturnMsg  {

    private static final String DefaultSuccessMessage="成功";

    private static final String DefaultFailMessage="失败";


    private static final Logger logger = LoggerFactory.getLogger(AjaxReturnMsg.class);

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "系统返回状态码",value = "\"200\",\"成功\" \"40001\",\"appkey为空\"\"40002\",\"appkey不正确\"\"40003\",\"token为空,请先登录\"\"40004\",\"token值不正确或已经过期,请重新登录\"\"40005\",\"登录信息与token信息不匹配,非法请求请确认\"\"40006\",\"没有访问此服务的权限或地址地址,请确认\"\"50001\",\"api发生异常\"")
    private int syscode; // 系统返回状态码,默认200
    @ApiModelProperty(name="业务状态码",value="true 成功 flase 失败")
    private boolean success; // 业务状态码，默认为true
    @ApiModelProperty(name="业务状态中文说明",value="业务状态中文说明")
    private String message = ""; // 业务状态中文说明
    @ApiModelProperty(name="业务状态中文状态码",value="业务状态中文状态码")
    private int code;
    @ApiModelProperty(name="业务返回对象集合",value="业务返回对象集合")
    private Object obj; //对象

    public int getSyscode() {
        return syscode;
    }

    public void setSyscode(int syscode) {
        this.syscode = syscode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }


    private AjaxReturnMsg() {
        this.syscode = SysCode.SYS_CODE_200.getCode(); // 系统返回状态码,默认200
        this.success = true; // 业务状态码，默认为true
    }

    /**
     * 校验并返回对象
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
     * 成功返回
     *
     * @return
     */
    public static AjaxReturnMsg success() {
        return success(DefaultSuccessMessage);
    }

    /**
     * 成功返回
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
     * 成功返回
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
     * 成功返回
     *
     * @param map
     * @return
     */
    public static AjaxReturnMsg success(HashMap map) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setObj(map);
        return dto;
    }

    /**
     * 成功返回
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
     * 成功返回 返回分页面信息 用于接口返回
     *
     * @param pageinfo
     * @return
     */
    public static AjaxReturnMsg success(PageInfo pageinfo) {
        AjaxReturnMsg dto = new AjaxReturnMsg();
        dto.setSuccess(true);
        dto.setObj(pageinfo);
        logger.debug(JSONUtils.beanToJson(dto));
        return dto;
    }

    /**
     * 成功返回分页用于manager管理平台
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
     * 成功返回
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
     * 失败返回
     *
     * @return
     */
    public static AjaxReturnMsg fail() {
       return fail(DefaultFailMessage);
    }
    /**
     * 失败返回
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
     * 失败返回
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
     * 失败返回
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
     * 异常返回
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
     * 异常返回
     * @param sysCode
     * @return
     */
    public static AjaxReturnMsg error(SysCode sysCode){
        return error(sysCode,sysCode.getName());
    }

    /**
     * 异常返回
     * @return
     */
    public static AjaxReturnMsg error500(){
        return error(SysCode.SYS_API_EXCEPTION);
    }

    /**
     * 异常返回
     * @return
     */
    public static AjaxReturnMsg error500(String message){
        return error(SysCode.SYS_API_EXCEPTION,message);
    }

    /**
     * 异常返回
     * @return
     */
    public static AjaxReturnMsg error401(){
        return error(SysCode.SYS_APPKEY_EMPTY);
    }

    /**
     * 异常返回
     * @return
     */
    public static AjaxReturnMsg error402(){
        return error(SysCode.SYS_APPKEY_ERROR);
    }
    /**
     * 异常返回
     * @return
     */
    public static AjaxReturnMsg error403(){
        return error(SysCode.SYS_TOKEN_EMPTY);
    }

    /**
     * 异常返回
     * @return
     */
    public static AjaxReturnMsg error404(){
        return error(SysCode.SYS_TOKEN_ERROR);
    }

    /**
     * 异常返回
     * @return
     */
    public static AjaxReturnMsg error408(){
        return error(SysCode.SYS_APPKEY_NO_PERM);
    }
    /**
     * 异常返回
     *
     * @param e
     * @return
     */
    public static AjaxReturnMsg error(Exception e) {
        return error(SysCode.SYS_API_EXCEPTION,e.getLocalizedMessage());
    }



}
