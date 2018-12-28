package com.insigma.cloud.base.dao.common.codevalidator;

import com.insigma.mvc.model.SVerCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by LENOVO on 2017/10/19.
 */
@Mapper
public interface ApiCodeValidatorMapper {
    /**
     * 保存验证码
     *
     * @param sVerCode
     */
    void saveSVerCode(SVerCode sVerCode);

    /**
     * 将历史验证码设置为无效
     *
     * @param sVerCode
     */
    void deleteSVerCode(SVerCode sVerCode);

    /**
     * 获取最新验证码信息
     *
     * @param sVerCode
     * @return
     */
    List<SVerCode> getMobileVerCode(SVerCode sVerCode);

    /**
     * 更新验证码状态等信息
     *
     * @param sVerCode
     */
    void updateSVerCode(SVerCode sVerCode);
}
