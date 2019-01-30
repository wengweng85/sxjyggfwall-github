package com.insigma.cloud.base.controller.common.excel;

import com.insigma.cloud.base.service.common.fileupload.ApiFileUploadService;
import com.insigma.cloud.common.context.SUserUtil;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.SysExcelBatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公共接口-excel文件上传批次信息
 */
@RestController
@Api(description = "公共接口-excel文件上传批次信息")
@RequestMapping("/api")
public class ApiExcelBatchController {
	
	@Resource
	private ApiFileUploadService apiFileUploadService;
	
	/**
	 * 列表查询
	 * @return
	 */
	@ApiOperation(value = "获取excel上传批次列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/excel/batchs",method = RequestMethod.POST)
	public AjaxReturnMsg getExcelBatchList(SysExcelBatch sExcelBatch ) throws Exception {
		String userid= SUserUtil.getUserId();
		sExcelBatch.setExcel_batch_aae011(userid);
		return  AjaxReturnMsg.success(apiFileUploadService.getExcelBatchList(sExcelBatch));
	}
	
	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "根据批次编号获取excel上传批次明细信息", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/excel/batch/{excel_batch_id}",method = RequestMethod.POST)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "excel_batch_id", value = "批次表编号", required = true, paramType = "path")
	})
	public AjaxReturnMsg getExcelBatchById(@PathVariable String  excel_batch_id ) throws Exception {
		return AjaxReturnMsg.success(apiFileUploadService.getExcelBatchById(excel_batch_id));
	}
   
}
