package com.insigma.cloud.base.dao.common.excel;

import com.insigma.mvc.model.Ac60ExcelTemp;
import com.insigma.mvc.model.ExcelExportModel;
import com.insigma.mvc.model.SysExcelBatch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * PovertyEmployInfoImportMapper
 * @author admin
 *
 */
@Mapper
public interface ApiFuPingExcelImportMapper {
	
     void insertExcelTempData(Ac60ExcelTemp ac60Temp);
    
     void executePovertyData(SysExcelBatch sExcelBatch);
    
     List<Ac60ExcelTemp> queryPovertyDataTotalByexcelBatchNumber(Ac60ExcelTemp ac60ExcelTemp);
    
     List<Ac60ExcelTemp> queryPovertyDataByexcelBatchNumber(Ac60ExcelTemp ac60ExcelTemp);
    
     int deleteTempDataByNumber(String number);
    
     List<ExcelExportModel> queryExportDataByNumber(String number);
    
     Ac60ExcelTemp queryImpDataById(String aac001);

}
