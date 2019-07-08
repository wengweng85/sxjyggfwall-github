package com.insigma.common.struct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insigma.common.enums.BusiStatus;
import com.insigma.common.enums.CommStatus;
import com.insigma.common.util.JsonUtils;

/**
 * �ӿ�ͨ�÷�����
 *
 * @author admin
 */

public class R  {

    private static final Logger logger = LoggerFactory.getLogger(R.class);

    private static final long serialVersionUID = 1L;

    private String commStatus; // ϵͳ����״̬��,Ĭ��200
    private boolean success; // ҵ��״̬�룬Ĭ��Ϊtrue
    private String message = ""; // ҵ��״̬����˵��
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
        this.commStatus = CommStatus.OK.getCode(); // ϵͳ����״̬��,Ĭ��ok
        this.busiStatus = BusiStatus.SUCCESS.getCode();
        this.success = true; // ҵ��״̬�룬Ĭ��Ϊtrue
    }

    /**
     * �ɹ�����
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
     * �ɹ�����
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
     * �ɹ�����
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
     * �쳣����
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
     * �쳣����
     * @param sysCode
     * @return
     */
    public static R fail(BusiStatus busiStatus){
        return fail(busiStatus,busiStatus.getName());
    }
    
    
    /**
     * �쳣����
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
     * �쳣����
     * @param sysCode
     * @return
     */
    public static R error(CommStatus sysCode){
        return error(sysCode,sysCode.getName());
    }
    
    /**
     * �쳣����
     * @param sysCode
     * @return
     */
    public static R error(String msg){
        return error(CommStatus.FAIL,msg);
    }

}
