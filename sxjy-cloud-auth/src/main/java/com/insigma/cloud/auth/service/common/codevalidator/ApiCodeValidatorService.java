package com.insigma.cloud.auth.service.common.codevalidator;


import com.insigma.mvc.model.SVerCode;

public interface ApiCodeValidatorService{
  
    String sendVerCode(String mobile);

    /**
     * ������֤����Ϣ
     * @param sVerCode
     * @return
     */
    String saveSVerCode(SVerCode sVerCode);
    
    /**
     * У����֤��
     * @param mobile �ֻ�����
     * @param vercode ��֤��
     * @param optype ҵ������
     * @return
     */
    boolean checkMobileVerCode(String mobile, String vercode, String optype);
}
