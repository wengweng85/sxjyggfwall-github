package com.insigma.cloud.base.dao.common.excel;

import com.insigma.mvc.model.DemoAc01ExcelTemp;
import com.insigma.mvc.model.SysExcelBatch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * ApiExcelImpTestMapper
 * @author admin
 *
 */
@Mapper
public interface ApiExcelImpTestMapper {
	
     void insertExcelTempData(DemoAc01ExcelTemp demoAc01ExcelTemp);
    
     void executeProc(SysExcelBatch sExcelBatch);
    
     List<DemoAc01ExcelTemp> queryExcelInfoList(DemoAc01ExcelTemp demoAc01ExcelTemp);
    
}
