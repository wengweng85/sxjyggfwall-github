package com.insigma.cloud.base.service.common.excel;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.Ac60ExcelTemp;
import com.insigma.mvc.model.ExcelExportModel;
import com.insigma.mvc.model.SysExcelBatch;

import java.util.List;


/**
 * excel??service
 * @author admin
 *
 */
public interface ApiFuPingExcelImportService {

	 PageInfo<SysExcelBatch> getList(SysExcelBatch sExcelBatch);

	 PageInfo<SysExcelBatch> queryPovertyDataTotalByexcelBatchNumber(Ac60ExcelTemp ac60ExcelTemp);

	 PageInfo<SysExcelBatch>  getPovertyImprtDataList(Ac60ExcelTemp ac60ExcelTemp);
	
	 int deleteTempDataByNumber(String number);
	
	 List<ExcelExportModel> queryExportDataByNumber(String number);

	 Ac60ExcelTemp queryImpDataById(String aac002);
}
