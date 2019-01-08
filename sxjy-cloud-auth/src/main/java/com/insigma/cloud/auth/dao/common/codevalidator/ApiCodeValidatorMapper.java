package com.insigma.cloud.auth.dao.common.codevalidator;

import com.insigma.mvc.model.SVerCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by LENOVO on 2017/10/19.
 */
@Mapper
public interface ApiCodeValidatorMapper {
    /**
     * ������֤��
     *
     * @param sVerCode
     */
    void saveSVerCode(SVerCode sVerCode);

    /**
     * ����ʷ��֤������Ϊ��Ч
     *
     * @param sVerCode
     */
    void deleteSVerCode(SVerCode sVerCode);

    /**
     * ��ȡ������֤����Ϣ
     *
     * @param sVerCode
     * @return
     */
    List<SVerCode> getMobileVerCode(SVerCode sVerCode);

    /**
     * ������֤��״̬����Ϣ
     *
     * @param sVerCode
     */
    void updateSVerCode(SVerCode sVerCode);
}
