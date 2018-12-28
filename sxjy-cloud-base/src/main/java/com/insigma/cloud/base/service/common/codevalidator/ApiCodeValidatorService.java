package com.insigma.cloud.base.service.common.codevalidator;


import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SVerCode;

public interface ApiCodeValidatorService{
  
    String sendVerCode(String mobile);

    /**
     * 保存验证码信息
     * @param sVerCode
     * @return
     */
    String saveSVerCode(SVerCode sVerCode);
    
    /**
     * 校验验证码
     * @param mobile 手机号码
     * @param vercode 验证码
     * @param optype 业务类型
     * @return
     */
    boolean checkMobileVerCode(String mobile, String vercode, String optype);
}
