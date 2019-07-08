package com.insigma.common.struct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insigma.common.enums.BusiStatus;
import com.insigma.common.enums.CommStatus;
import com.insigma.common.util.JsonUtils;

/**
 * 接口通用返回类
 *
 * @author admin
 */

public class R  {

    private static final Logger logger = LoggerFactory.getLogger(R.class);

    private static final long serialVersionUID = 1L;

    private String commStatus; // 系统返回状态码,默认200
    private boolean success; // 业务状态码，默认为true
    private String message = ""; // 业务状态中文说明
    private String busiStatus;
    private String body;


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getCommStatus() {
		return commStatus;
	}


	public void setCommStatus(String commStatus) {
		this.commStatus = commStatus;
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


	public String getBusiStatus() {
		return busiStatus;
	}


	public void setBusiStatus(String busiStatus) {
		this.busiStatus = busiStatus;
	}
	
	
	private R() {
        this.commStatus = CommStatus.OK.getCode(); // 系统返回状态码,默认ok
        this.busiStatus = BusiStatus.SUCCESS.getCode();
        this.success = true; // 业务状态码，默认为true
    }

    /**
     * 成功返回
     *
     * @param message
     * @return
     */
    public static R success(String message) {
        R dto = new R();
        dto.setSuccess(true);
        dto.setMessage(message);
        logger.debug(JsonUtils.objectToJson(dto));
        return dto;
    }
    
    /**
     * 成功返回
     *
     * @param message
     * @return
     */
    public static R success(Object body) {
        R dto = new R();
        dto.setSuccess(true);
        dto.setBody(JsonUtils.objectToJson(body));
        logger.debug(JsonUtils.objectToJson(dto));
        return dto;
    }

    /**
     * 成功返回
     *
     * @param
     * @return
     */
    public static R success(String message, Object body) {
        R dto = new R();
        dto.setSuccess(true);
        dto.setMessage(message);
        dto.setBody(JsonUtils.objectToJson(body));
        logger.debug(JsonUtils.objectToJson(dto));
        return dto;
    }

    
    /**
     * 异常返回
     * @param commStatus
     * @param msg
     * @return
     */
    public static R fail(BusiStatus busiStatus,String msg){
        R dto=new R();
        dto.setSuccess(false);
        dto.setCommStatus(CommStatus.FAIL.getCode());
        dto.setBusiStatus(busiStatus.getCode());
        dto.setMessage(msg);
        dto.setBody("{}");
        logger.debug(JsonUtils.objectToJson(dto));
        return dto;
    }

    /**
     * 异常返回
     * @param sysCode
     * @return
     */
    public static R fail(BusiStatus busiStatus){
        return fail(busiStatus,busiStatus.getName());
    }
    
    
    /**
     * 异常返回
     * @param commStatus
     * @param msg
     * @return
     */
    public static R error(CommStatus commStatus,String msg){
        R dto=new R();
        dto.setSuccess(false);
        dto.setCommStatus(commStatus.getCode());
        dto.setBusiStatus(BusiStatus.NODATA.getCode());
        dto.setMessage(msg);
        dto.setBody("{}");
        logger.debug(JsonUtils.objectToJson(dto));
        return dto;
    }

    /**
     * 异常返回
     * @param sysCode
     * @return
     */
    public static R error(CommStatus sysCode){
        return error(sysCode,sysCode.getName());
    }
    
    /**
     * 异常返回
     * @param sysCode
     * @return
     */
    public static R error(String msg){
        return error(CommStatus.FAIL,msg);
    }

}
