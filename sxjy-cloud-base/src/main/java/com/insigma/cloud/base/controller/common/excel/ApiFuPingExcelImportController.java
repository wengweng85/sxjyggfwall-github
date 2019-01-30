package com.insigma.cloud.base.controller.common.excel;

import com.insigma.cloud.base.service.common.excel.ApiFuPingExcelImportService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.Ac60ExcelTemp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * ƶ����Ա��Ϣ����-����
 *
 */
@RestController
@Api(description = "excel�ļ��ϴ�����")
public class ApiFuPingExcelImportController{
	
	@Resource
	private ApiFuPingExcelImportService apiFuPingExcelImportService;
	
	
	/**
	 * �б��ѯ ��������������� 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "��������������� ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/excel/total",method = RequestMethod.POST)
	public AjaxReturnMsg queryPovertyDataTotalByexcelBatchNumber(HttpServletRequest request,Model model, Ac60ExcelTemp ac60ExcelTemp ) throws Exception {
		return AjaxReturnMsg.success(apiFuPingExcelImportService.queryPovertyDataTotalByexcelBatchNumber(ac60ExcelTemp));
	}
	
	/**
	 * �б��ѯ ����������ϸ��� 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "����������ϸ���  ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/excel/detail",method = RequestMethod.POST)
	public AjaxReturnMsg getPovertyImprtDataList(HttpServletRequest request,Model model, Ac60ExcelTemp ac60ExcelTemp ) throws Exception {
		return AjaxReturnMsg.success(apiFuPingExcelImportService.getPovertyImprtDataList(ac60ExcelTemp));
	}
	
	/**
	 * ɾ����ʱ������
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "ɾ����ʱ������  ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/excel/{excel_batch_number}",method = RequestMethod.DELETE)
	public AjaxReturnMsg  deleteTempDataByNumber(HttpServletRequest request ,@PathVariable String excel_batch_number) throws Exception {
		return AjaxReturnMsg.success(apiFuPingExcelImportService.deleteTempDataByNumber(excel_batch_number));
	}
   
}
