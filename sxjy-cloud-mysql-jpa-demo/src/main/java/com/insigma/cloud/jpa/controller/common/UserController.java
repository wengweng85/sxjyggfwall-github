package com.insigma.cloud.jpa.controller.common;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.jpa.entity.SysUser;
import com.insigma.cloud.jpa.service.common.UserRepository;
import com.insigma.cloud.jpa.serviceimpl.common.UserSerivceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wengsh on 2019/2/27.
 */

@RestController
@Api(description = "测试")
@RequestMapping("/jpa")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSerivceImpl userSerivce;

    /**
     * 获取人员列表
     * @return
     */
    @ApiOperation(value = "获取人员列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg findUserList(){
        Sort sort=new Sort(Sort.Direction.DESC,"createdate");
        Pageable pageable=new PageRequest(0,2,sort);
        return  AjaxReturnMsg.success(userRepository.findAll(pageable));
    }

    /**
     * 获取人员列表
     * @return
     */
    @ApiOperation(value = "获取人员列表通过用户名", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/findByUserName", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg findByUserName(@RequestBody  SysUser sysUser){
        return  AjaxReturnMsg.success(userRepository.findByUsername(sysUser.getUsername()));
    }

    /**
     * 获取人员列表
     * @return
     */
    @ApiOperation(value = "获取人员列表通过中文名", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg findByName(@RequestBody  SysUser sysUser){
        return  AjaxReturnMsg.success(userRepository.findByNameLike(sysUser.getName()));
    }

    /**
     * 保存人员
     * @return
     */
    @ApiOperation(value = "保存人员", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg save(){
        userSerivce.addMoreUsers();
        return AjaxReturnMsg.success();
    }

    /**
     * 删除人员
     * @return
     */
    @ApiOperation(value = "删除人员", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value="/userdelete", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg deleteById(@RequestBody  SysUser sysUser){
        userRepository.deleteById(sysUser.getUserid());
        return AjaxReturnMsg.success();
    }
}
