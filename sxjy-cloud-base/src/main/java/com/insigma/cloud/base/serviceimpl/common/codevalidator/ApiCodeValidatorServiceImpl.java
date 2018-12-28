package com.insigma.cloud.base.serviceimpl.common.codevalidator;

import com.insigma.cloud.base.dao.common.codevalidator.ApiCodeValidatorMapper;
import com.insigma.cloud.base.service.common.codevalidator.ApiCodeValidatorService;
import com.insigma.cloud.common.dto.MessageType;
import com.insigma.cloud.common.utils.RandomNumUtil;
import com.insigma.mvc.model.SVerCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Service
public class ApiCodeValidatorServiceImpl implements ApiCodeValidatorService {
    @Resource
    private ApiCodeValidatorMapper apiCodeValidatorMapper;

    private static final int CODE_LENGTH = 6; // �ֻ���֤�볤��
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS");

   /**
    * ������֤��
    */
    @Override
    public String sendVerCode(String mobile) {
    	SVerCode sVerCode = new SVerCode();
        final String vercode = RandomNumUtil.getRandomNum(CODE_LENGTH);
        StringBuffer sb = new StringBuffer();
        sb.append(vercode);
        sVerCode.setUserlogid("000000");//������־����
        sVerCode.setMobile(mobile);//�ֻ���
        sVerCode.setVercode(vercode);//��֤��
       //sVerCode.setOptype(OpType.P00000.getCode());//ҵ������
    	String messagenew = "��������֤��Ϊ"+vercode+",2������Ч������й¶��";
        sVerCode.setMessage(messagenew);//��������
        sVerCode.setMessagetype(MessageType.MESSAGE_TYPE_SMS_VERCODE.getCode());//��Ϣ����
       
        return saveSVerCode(sVerCode);
    }

    /**
     * ������֤����Ϣ
     *
     * @param sVerCode
     * @return
     */
    @Override
    public String saveSVerCode(SVerCode sVerCode) {
        //����ʷ��֤������Ϊ��Ч
        apiCodeValidatorMapper.deleteSVerCode(sVerCode);
        //��Ч״̬ 1-��Ч
        sVerCode.setAae100("1");
        //��֤��� 0-δ��֤
        sVerCode.setVerresult("0");
        sVerCode.setCreatedate(new Date());
        sVerCode.setEnddate(new Date(System.currentTimeMillis() +2*60*1000));//2����
        //������֤����Ϣ
        apiCodeValidatorMapper.saveSVerCode(sVerCode);
        return sVerCode.getVercode();
    }

    /**
     * У����֤��
     *
     * @param mobile      �ֻ�����
     * @param vercode     ��֤��
     * @param optype      ҵ������
     * @return
     */
    @Override
    public boolean checkMobileVerCode(String mobile, String vercode, String optype) {
        SVerCode sVerCode = new SVerCode();
        sVerCode.setMobile(mobile);
        sVerCode.setVercode(vercode);
        sVerCode.setOptype(optype);
        List<SVerCode> list = apiCodeValidatorMapper.getMobileVerCode(sVerCode);
        if (list.size() == 0) {
            return false;
        }
        SVerCode result = list.get(0);
        Date now = new Date();
        //������Ч��
        if(now.compareTo(result.getEnddate()) == 1){
            return false;
        }

        //�û��������֤��
        result.setInputcode(vercode);
        //�����ʱ��
        result.setInputtime(new Date());

        //У����֤���Ƿ���ȷ
        if(!result.getVercode().equals(vercode)){
            //У���� 2-У�鲻ͨ��
            result.setVerresult("2");
            //������֤��״̬����Ϣ
            apiCodeValidatorMapper.updateSVerCode(result);
            return false;
        }

        //У���� 1-У��ͨ��
        result.setVerresult("1");
        //У��ͨ�������ó���Ч
        result.setAae100("0");

        //������֤��״̬����Ϣ
        apiCodeValidatorMapper.updateSVerCode(result);
        return true;
    }
}
