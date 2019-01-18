package com.insigma.cloud.auth.dao.common.svercord;

import java.util.List;

import com.insigma.mvc.model.SVerCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.insigma.mvc.model.Svercord;

@Mapper
public interface ApiSvercordMapper {

    void saveSVerCode(SVerCode sVerCode);

    void deleteSVerCode(SVerCode sVerCode);

    List<SVerCode> getMobileVerCode(SVerCode sVerCode);

    void updateSVerCode(SVerCode sVerCode);
}