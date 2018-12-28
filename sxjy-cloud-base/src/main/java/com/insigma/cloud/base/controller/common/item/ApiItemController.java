package com.insigma.cloud.base.controller.common.item;

import com.insigma.cloud.base.service.common.item.ApiItemService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.mvc.model.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * �����Ӧ������¼��
 * @author admin
 *
 */
@RestController
@Api(description = "�����Ӧ����")
@RequestMapping(value = "/api")
public class ApiItemController {

    @Resource
    private ApiItemService apiItemService;

    /**
     * ��ȡ��������б�
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "��ȡ��������б�", notes = "��ȡ��������б�", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/itemmaterial", method = RequestMethod.POST)
    public AjaxReturnMsg itemmaterial(@RequestBody Item item) throws AppException {
    	/*if("1".equals(item.getItem_submit_type())){
    		return apiItemService.getItemMaterialById(item.getItem_id());
    	}else{
    		Item item2 = apiItemService.getItemById(item.getItem_id());
    		return new HttpUtil().getItemMaterialList(item2);
    	}*/
    	return null;
    }

}
