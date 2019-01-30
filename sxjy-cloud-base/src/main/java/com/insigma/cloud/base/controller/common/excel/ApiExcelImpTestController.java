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
 *   excel�ϴ�����
 *
 */
@RestController
@Api(description = "excel�ļ��ϴ��������ӵڶ���")
@RequestMapping("/api")
public class ApiExcelImpTestController {
	
	private static Log log=LogFactory.getLog(ApiExcelImpTestController.class);
	
	
	@Resource
	private ApiExcelImpTestService apiExcelImpTestService;
	
	@Resource
	private ApiFileUploadService apifileUploadService;
	
	/**
	 * �б��ѯ ����������ϸ��� 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "����������ϸ���  ", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/exceltest/detail",method = RequestMethod.POST)
	public AjaxReturnMsg getPovertyImprtDataList(HttpServletRequest request,Model model, DemoAc01ExcelTemp demoAc01ExcelTemp ) throws Exception {
		return AjaxReturnMsg.success(apiExcelImpTestService.queryExcelInfoList(demoAc01ExcelTemp));
	}
	
	/**
     *  excel�ļ����ݵ���
     *  �ֳɶ�������
     *  һ ���ݴ����ҵ��id����mapper����2007 excel�ļ�
     *  �� �����ɺõ�excelͨ������ʽ���� 
     *
     * @param demoAc01ExcelTemp
     * @param response
     */
    @RequestMapping(value = "/exceltest/export", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation(value = " excel�ļ����ݵ���", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void export(DemoAc01ExcelTemp demoAc01ExcelTemp , HttpServletResponse response) {
        try {
            //һ ���ݴ����ҵ��id����mapper����2007 excel�ļ�
        	String filepath=apiExcelImpTestService.queryAllExcelInfoList(demoAc01ExcelTemp);
            //�� �����ɺõ�excelͨ������ʽ����
        	File file=new File(filepath);
            //���д����Ƿ�ֹ��������Ĺؼ�����
            response.setHeader("Content-disposition", "attachment; filename=" +URLEncoder.encode(file.getName(), "utf-8"));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            //�½�һ��2048�ֽڵĻ�����
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
