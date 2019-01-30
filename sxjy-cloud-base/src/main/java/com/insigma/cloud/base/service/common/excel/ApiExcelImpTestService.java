package com.insigma.cloud.base.service.common.excel;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.DemoAc01ExcelTemp;


/**
 * excel??service
 * @author admin
 *
 */
public interface ApiExcelImpTestService {

	public PageInfo queryExcelInfoList(DemoAc01ExcelTemp demoAc01ExcelTemp);

	public String queryAllExcelInfoList(DemoAc01ExcelTemp demoAc01ExcelTemp);
	
}
