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
 * 参数控制器
 * @author 
 *
 */
@RestController
@Api(description = "参数控制器")
public class ApiCodeTypeController  {

    @Resource
    private ApiCodeTypeService apiCodeTypeService;

    /**
     * 获取参数类别列表
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "获取参数类别列表", notes = "获取参数类别列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/codetype/getInitcodetypeList", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getInitcodetypeList() throws AppException {
        return AjaxReturnMsg.success(apiCodeTypeService.getInitcodetypeList());
    }


    /**
     * 根据参数类别获取该类参数的列表
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "根据参数类别获取该类参数的列表", notes = "根据参数类别获取该类参数的列表，如参数类别为AAC011，返回学历信息的列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/codetype/getInitCodeValueList", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getInitCodeValueList(@RequestBody CodeType codetype) throws AppException {
        //throw new AppException("发生异常了");
        return AjaxReturnMsg.success(apiCodeTypeService.getInitCodeValueList(codetype));
    }

    

    /**
     * 根据父类获取子类
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "根据父类获取子类", notes = "根据父类获取子类", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_type", value = "参数类别的值", required = true, paramType = "query"),
        @ApiImplicitParam(name = "code_value", value = "参数值", required = true, paramType = "query")
    })
    @PostMapping(value = "/codetype/getChildrenByParentId", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getChildrenByParentId(@RequestBody CodeValue codevalue) throws Exception {
        String codeValue = new String(codevalue.getCode_value().getBytes("iso-8859-1"), "utf-8");
        return AjaxReturnMsg.success(apiCodeTypeService.getChildrenByParentId(codevalue.getCode_type(), codeValue));
    }


    /**
     * 根据代码类型获取多级代码JSON数据
     *
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "根据代码类型获取多级代码JSON格式数据", notes = "根据代码类型获取多级代码JSON格式数据", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code_type", value = "参数类别", required = true, paramType = "path")
    })
    @PostMapping(value = "/codetype/getMulticodeValuebyType", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg getMulticodeValuebyType(@RequestBody CodeType codetype) throws AppException {
        return AjaxReturnMsg.success(apiCodeTypeService.getMulticodeValuebyType(codetype.getCode_type()));
    }
   
    /**
     * 根据代码类型及代码父类名获取代码值
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "根据代码类型及代码父类名获取代码值", notes = "根据代码类型及代码父类名获取代码值", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/codetype/queryCodeValueByCodeTypeAndParent", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg queryCodeValueByCodeTypeAndParent(@RequestBody CodeValue codevalue) throws Exception {
        return AjaxReturnMsg.success(apiCodeTypeService.queryCodeValueByCodeTypeAndParent(codevalue));
    }
    
    
    /**
     * 代码树
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "代码树", notes = "代码树", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/codetype/treedata", produces = MediaType.APPLICATION_JSON_VALUE)
	public AjaxReturnMsg treedata(@RequestBody CodeValue codevalue) throws AppException {
		return AjaxReturnMsg.success(apiCodeTypeService.getCodeValueTree(codevalue));
	}

}
