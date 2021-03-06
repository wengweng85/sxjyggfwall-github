package com.insigma.cloud.base.controller.rpctest;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.cloud.rpc.TestAc11RpcService;
import com.insigma.mvc.model.Ac11;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rpctest
 * @author admin
 *
 */
@RestController
@Api(description = "rpctest")
@RequestMapping(value = "/api")
public class ApiRpcTestController {

    @Autowired
    private TestAc11RpcService ac11PrcService;

    /**
     * 获取参数类别列表
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "rpctest", notes = "rpctest", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/rpctest", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg list(@RequestBody Ac11 ac11) throws AppException {
        return ac11PrcService.test(ac11);
    }

}
