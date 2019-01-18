package com.insigma.cloud.auth.controller.common.codetype;

import com.insigma.cloud.auth.service.common.codetype.ApiCodeTypeService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.CodeType;
import com.insigma.mvc.model.CodeValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����������
 * @author 
 *
 */
@RestController
@Api(description = "����������")
public class ApiCodeTypeController  {

    @Resource
    private ApiCodeTypeService apiCodeTypeService;

    /**
     * ��ȡ��������б�
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "��ȡ��������б�", notes = "��ȡ��������б�", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/codetype/getInitcodetypeList", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getInitcodetypeList() throws AppException {
        return AjaxReturnMsg.success(apiCodeTypeService.getInitcodetypeList());
    }


    /**
     * ���ݲ�������ȡ����������б�
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "���ݲ�������ȡ����������б�", notes = "���ݲ�������ȡ����������б���������ΪAAC011������ѧ����Ϣ���б�", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/codetype/getInitCodeValueList", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getInitCodeValueList(@RequestBody CodeType codetype) throws AppException {
        //throw new AppException("�����쳣��");
        return AjaxReturnMsg.success(apiCodeTypeService.getInitCodeValueList(codetype));
    }

    

    /**
     * ���ݸ����ȡ����
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "���ݸ����ȡ����", notes = "���ݸ����ȡ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_type", value = "��������ֵ", required = true, paramType = "query"),
        @ApiImplicitParam(name = "code_value", value = "����ֵ", required = true, paramType = "query")
    })
    @PostMapping(value = "/codetype/getChildrenByParentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getChildrenByParentId(@RequestBody CodeValue codevalue) throws Exception {
        String codeValue = new String(codevalue.getCode_value().getBytes("iso-8859-1"), "utf-8");
        return AjaxReturnMsg.success(apiCodeTypeService.getChildrenByParentId(codevalue.getCode_type(), codeValue));
    }


    /**
     * ���ݴ������ͻ�ȡ�༶����JSON����
     *
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "���ݴ������ͻ�ȡ�༶����JSON��ʽ����", notes = "���ݴ������ͻ�ȡ�༶����JSON��ʽ����", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_type", value = "�������", required = true, paramType = "path")
    })
    @PostMapping(value = "/codetype/getMulticodeValuebyType", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getMulticodeValuebyType(@RequestBody CodeType codetype) throws AppException {
        return AjaxReturnMsg.success(apiCodeTypeService.getMulticodeValuebyType(codetype.getCode_type()));
    }
   
    /**
     * ���ݴ������ͼ����븸������ȡ����ֵ
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "���ݴ������ͼ����븸������ȡ����ֵ", notes = "���ݴ������ͼ����븸������ȡ����ֵ", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/codetype/queryCodeValueByCodeTypeAndParent", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg queryCodeValueByCodeTypeAndParent(@RequestBody CodeValue codevalue) throws Exception {
        return AjaxReturnMsg.success(apiCodeTypeService.queryCodeValueByCodeTypeAndParent(codevalue));
    }
    
    
    /**
     * ������
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "������", notes = "������", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/codetype/treedata", produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg treedata(@RequestBody CodeValue codevalue) throws AppException {
		return AjaxReturnMsg.success(apiCodeTypeService.getCodeValueTree(codevalue));
	}

}
