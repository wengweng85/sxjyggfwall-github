package com.insigma.cloud.auth.service.common.svercord;

import com.insigma.mvc.model.SVerCode;

public interface ApiSvercordService {


	/**
	 * ������֤��
	 * @param mobile
	 * @return
	 */
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
