package com.insigma.cloud.auth.controller.token;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insigma.cloud.auth.service.token.ApiTokenService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.dto.CasUser;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SPermission;
import com.insigma.mvc.model.SRole;
import com.insigma.mvc.model.SUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@Api(description = "用户认证")
@RestController
public class ApiTokenController {

    Logger LOGGER= LoggerFactory.getLogger(ApiTokenController.class);

	@Resource
	private ApiTokenService apiLoginService;


    /**
     * 根据用户名密码获取认证token
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取认证token", notes = "获取认证token",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/token",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg gettoken(@RequestBody SUser suser) throws Exception {
        AccessToken accessToken=apiLoginService.getToken(suser.getUsername(),suser.getPassword());
        if(accessToken!=null){
            return AjaxReturnMsg.success(accessToken);
        }else{
            return AjaxReturnMsg.fail("认证失败");
        }
    }


    /**
     * 获取认证token及菜单
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取认证token及菜单", notes = "获取认证token及菜单",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/tokenmenus", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg tokenAndmenus(@RequestBody SUser suser) throws Exception {
        AccessToken accessToken=apiLoginService.getToken(suser.getUsername(),suser.getPassword());
        if(accessToken!=null){
            List<SPermission> menuTree=apiLoginService.findMenuTree(suser.getUsername());
            HashMap map=new HashMap();
            map.put("token",accessToken);
            map.put("menus", JSONArray.toJSON(menuTree));
            AjaxReturnMsg ajaxReturnMsg= AjaxReturnMsg.success(map);
            return ajaxReturnMsg;
        }else{
            return AjaxReturnMsg.fail("认证失败");
        }
    }


    /**
     * 获取菜单
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取菜单", notes = "获取菜单")
    @PostMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg menus(@RequestBody SUser suser) throws Exception {
        List<SPermission> menuTree=apiLoginService.findMenuTree(suser.getUsername());
        return AjaxReturnMsg.success(menuTree);
    }

    /**
     * 根据用户名获取用户角色及权限
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据用户名获取用户角色", notes = "根据用户名获取用户角色",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg roles(@RequestBody SUser suser) throws Exception {
        List<SRole> roleList=apiLoginService.findRolesStr(suser.getUsername());
        return AjaxReturnMsg.success(roleList);
    }

    /**
     * 根据用户名获取用户权限
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据用户名获取用户权限", notes = "根据用户名获取用户权限",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/permissions",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg permissions(@RequestBody SUser suser) throws Exception {
        List<SPermission> permissionList=apiLoginService.findPermissionStr(suser.getUsername());
        return AjaxReturnMsg.success(permissionList);
    }

    /**
     * 单点登录rest
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "cas单点登录", notes = "cas单点登录", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/cas" , produces = MediaType.APPLICATION_JSON_VALUE)
    public void castoken(@RequestHeader HttpHeaders httpHeaders,HttpServletResponse response) throws Exception {
        LOGGER.info("Rest api login.");
        LOGGER.debug("request headers: {}", httpHeaders);
        Object result = null;
        CasUser casUser = null;
        SUser sUser=null;
        try {
            casUser = obtainUserFormHeader(httpHeaders);
            if (casUser != null) {
                sUser=apiLoginService.getUser(casUser.getUsername(), casUser.getPassword());
                if(sUser==null){
                    //密码不匹配
                    result= new ResponseEntity(HttpStatus.BAD_REQUEST);
                }

                if (casUser.isDisable()) {
                    //禁用 403
                    result= new ResponseEntity(HttpStatus.FORBIDDEN);
                }
                if (casUser.isLocked()) {
                    //锁定 423
                    result= new ResponseEntity(HttpStatus.LOCKED);
                }
                if (casUser.isExpired()) {
                    //过期 428
                    result= new ResponseEntity(HttpStatus.PRECONDITION_REQUIRED);
                }
            } else {
                //不存在 404
                result= new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
            new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("[{}] login is ok", casUser.getUsername());
        HashMap map=new HashMap();
        map.put("token","");
        casUser.setAttributes(map);
        casUser.setUsername(JSONUtils.writeValueAsString(sUser));
        result=casUser;
        //成功返回json
        //将数据输出到客户端
        this.writeJSON(response, result);
    }

    /**
     * 根据请求头获取用户名及密码
     *
     * @param httpHeaders
     * @return
     * @throws UnsupportedEncodingException
     */
    private CasUser obtainUserFormHeader(HttpHeaders httpHeaders) throws UnsupportedEncodingException {
        /**
         *
         * This allows the CAS server to reach to a remote REST endpoint via a POST for verification of credentials.
         * Credentials are passed via an Authorization header whose value is Basic XYZ where XYZ is a Base64 encoded version of the credentials.
         */
        //当请求过来时，会通过把用户信息放在请求头authorization中，并且通过Basic认证方式加密
        String authorization = httpHeaders.getFirst ("Authorization");//将得到 Basic Base64(用户名:密码)
        String baseCredentials = authorization.split(" ")[1];
        String  usernamePassword = new String(Base64.decodeBase64(baseCredentials));//用户名:密码
        LOGGER.debug("login user: {}", usernamePassword);
        CasUser casUser=new CasUser();
        String credentials[] = usernamePassword.split(":");
        casUser.setUsername(credentials[0]);
        casUser.setPassword(credentials[1]);
        return casUser;
    }

    /**
     * writeJSON
     * @return
     */
    @SuppressWarnings("rawtypes")
    public void writeJSON(HttpServletResponse response, Object object){
        try {
            //设定编码
            response.setCharacterEncoding("UTF-8");
            //表示是json类型的数据
            response.setContentType("application/json");
            //获取PrintWriter 往浏览器端写数据
            PrintWriter writer = response.getWriter();

            //当是不正常的数据的时候，设定状态
            if(object instanceof ResponseEntity) {
                HttpStatus status = ((ResponseEntity) object).getStatusCode();
                //设定状态码表
                response.setStatus(status.value());
            }

            ObjectMapper mapper = new ObjectMapper(); //转换器
            //获取到转化后的JSON 数据
            String json = mapper.writeValueAsString(object);
            //写数据到浏览器
            writer.write(json);
            //刷新，表示全部写完，把缓存数据都刷出去
            writer.flush();

            //关闭writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
