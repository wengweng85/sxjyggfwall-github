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

    private static final int CODE_LENGTH = 6; // 手机验证码长度
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS");

   /**
    * 发送验证码
    */
    @Override
    public String sendVerCode(String mobile) {
    	SVerCode sVerCode = new SVerCode();
        final String vercode = RandomNumUtil.getRandomNum(CODE_LENGTH);
        StringBuffer sb = new StringBuffer();
        sb.append(vercode);
        sVerCode.setUserlogid("000000");//操作日志内码
        sVerCode.setMobile(mobile);//手机号
        sVerCode.setVercode(vercode);//验证码
       //sVerCode.setOptype(OpType.P00000.getCode());//业务类型
    	String messagenew = "您本次验证码为"+vercode+",2分钟有效，请勿泄露！";
        sVerCode.setMessage(messagenew);//短信内容
        sVerCode.setMessagetype(MessageType.MESSAGE_TYPE_SMS_VERCODE.getCode());//消息类型
       
        return saveSVerCode(sVerCode);
    }

    /**
     * 保存验证码信息
     *
     * @param sVerCode
     * @return
     */
    @Override
    public String saveSVerCode(SVerCode sVerCode) {
        //将历史验证码设置为无效
        apiCodeValidatorMapper.deleteSVerCode(sVerCode);
        //有效状态 1-有效
        sVerCode.setAae100("1");
        //验证结果 0-未验证
        sVerCode.setVerresult("0");
        sVerCode.setCreatedate(new Date());
        sVerCode.setEnddate(new Date(System.currentTimeMillis() +2*60*1000));//2分钟
        //保存验证码信息
        apiCodeValidatorMapper.saveSVerCode(sVerCode);
        return sVerCode.getVercode();
    }

    /**
     * 校验验证码
     *
     * @param mobile      手机号码
     * @param vercode     验证码
     * @param optype      业务类型
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
        //超过有效期
        if(now.compareTo(result.getEnddate()) == 1){
            return false;
        }

        //用户输入的验证码
        result.setInputcode(vercode);
        //输入的时间
        result.setInputtime(new Date());

        //校验验证码是否正确
        if(!result.getVercode().equals(vercode)){
            //校验结果 2-校验不通过
            result.setVerresult("2");
            //更新验证码状态等信息
            apiCodeValidatorMapper.updateSVerCode(result);
            return false;
        }

        //校验结果 1-校验通过
        result.setVerresult("1");
        //校验通过后设置成无效
        result.setAae100("0");

        //更新验证码状态等信息
        apiCodeValidatorMapper.updateSVerCode(result);
        return true;
    }
}
