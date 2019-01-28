package com.insigma.cloud.base.service.demo;

import com.github.pagehelper.PageInfo;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.DemoAc01;


/**
 * demo ac01 service
 * @author admin
 *
 */
public interface ApiDemoService {
	
	 PageInfo<DemoAc01>  getDemoAc01List(DemoAc01 ac01);
	
	 int deleteDemoById(String aac001);
	
	 int batDeleteDemoData(DemoAc01 ac01);
	
	 DemoAc01 getDemoById(String aac001);
	
	 DemoAc01 getDemoNameById(String aac001);
	
	 void saveDemoData(DemoAc01 ac01) throws AppException;
	
	 int updateDemoAc01DemBusUuid(DemoAc01 ac01);
	
}
