package com.insigma.cloud.base.service.common.excel;

import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.SysExcelBatch;

import java.util.List;


/**
 * excel??service
 * @author admin
 *
 */
public interface ExcelVS {

   public void executeListToTemp(List<String[]> list, SysExcelBatch sExcelBatch) throws AppException;
   public void executeProc(SysExcelBatch sExcelBatch)throws AppException;
}
