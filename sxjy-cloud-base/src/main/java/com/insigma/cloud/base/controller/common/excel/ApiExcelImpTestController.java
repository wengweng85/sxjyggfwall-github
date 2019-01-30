package com.insigma.cloud.base.controller.common.excel;

import com.insigma.cloud.base.service.common.excel.ApiExcelImpTestService;
import com.insigma.cloud.base.service.common.fileupload.ApiFileUploadService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.mvc.model.DemoAc01ExcelTemp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

/**
 *  
 *   excel上传例子
 *
 */
@RestController
@Api(description = "excel文件上传测试例子第二例")
@RequestMapping("/api")
public class ApiExcelImpTestController {
	
	private static Log log=LogFactory.getLog(ApiExcelImpTestController.class);
	
	
	@Resource
	private ApiExcelImpTestService apiExcelImpTestService;
	
	@Resource
	private ApiFileUploadService apifileUploadService;
	
	/**
	 * 列表查询 导入数据明细情况 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "导入数据明细情况  ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/exceltest/detail",method = RequestMethod.POST)
	public AjaxReturnMsg getPovertyImprtDataList(HttpServletRequest request,Model model, DemoAc01ExcelTemp demoAc01ExcelTemp ) throws Exception {
		return AjaxReturnMsg.success(apiExcelImpTestService.queryExcelInfoList(demoAc01ExcelTemp));
	}
	
	/**
     *  excel文件数据导出
     *  分成二个步骤
     *  一 根据传入的业务id调用mapper生成2007 excel文件
     *  二 将生成好的excel通过流方式下载 
     *
     * @param demoAc01ExcelTemp
     * @param response
     */
    @RequestMapping(value = "/exceltest/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation(value = " excel文件数据导出", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void export(DemoAc01ExcelTemp demoAc01ExcelTemp , HttpServletResponse response) {
        try {
            //一 根据传入的业务id调用mapper生成2007 excel文件
        	String filepath=apiExcelImpTestService.queryAllExcelInfoList(demoAc01ExcelTemp);
            //二 将生成好的excel通过流方式下载
        	File file=new File(filepath);
            //此行代码是防止中文乱码的关键！！
            response.setHeader("Content-disposition", "attachment; filename=" +URLEncoder.encode(file.getName(), "utf-8"));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            //新建一个2048字节的缓冲区
            byte[] buff = new byte[2048];
            int bytesRead = 0;
            while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
            bis.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
