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

@Api(description = "�û���֤")
@RestController
public class ApiTokenController {

    Logger LOGGER= LoggerFactory.getLogger(ApiTokenController.class);

	@Resource
	private ApiTokenService apiLoginService;


    /**
     * �����û��������ȡ��֤token
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ��֤token", notes = "��ȡ��֤token",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/token",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg gettoken(@RequestBody SUser suser) throws Exception {
        AccessToken accessToken=apiLoginService.getToken(suser.getUsername(),suser.getPassword());
        if(accessToken!=null){
            return AjaxReturnMsg.success(accessToken);
        }else{
            return AjaxReturnMsg.fail("��֤ʧ��");
        }
    }


    /**
     * ��ȡ��֤token���˵�
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ��֤token���˵�", notes = "��ȡ��֤token���˵�",produces = MediaType.APPLICATION_JSON_VALUE)
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
            return AjaxReturnMsg.fail("��֤ʧ��");
        }
    }


    /**
     * ��ȡ�˵�
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ�˵�", notes = "��ȡ�˵�")
    @PostMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg menus(@RequestBody SUser suser) throws Exception {
        List<SPermission> menuTree=apiLoginService.findMenuTree(suser.getUsername());
        return AjaxReturnMsg.success(menuTree);
    }

    /**
     * �����û�����ȡ�û���ɫ��Ȩ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����û�����ȡ�û���ɫ", notes = "�����û�����ȡ�û���ɫ",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg roles(@RequestBody SUser suser) throws Exception {
        List<SRole> roleList=apiLoginService.findRolesStr(suser.getUsername());
        return AjaxReturnMsg.success(roleList);
    }

    /**
     * �����û�����ȡ�û�Ȩ��
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "�����û�����ȡ�û�Ȩ��", notes = "�����û�����ȡ�û�Ȩ��",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/permissions",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg permissions(@RequestBody SUser suser) throws Exception {
        List<SPermission> permissionList=apiLoginService.findPermissionStr(suser.getUsername());
        return AjaxReturnMsg.success(permissionList);
    }

    /**
     * �����¼rest
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "cas�����¼", notes = "cas�����¼", produces = MediaType.APPLICATION_JSON_VALUE)
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
                    //���벻ƥ��
                    result= new ResponseEntity(HttpStatus.BAD_REQUEST);
                }

                if (casUser.isDisable()) {
                    //���� 403
                    result= new ResponseEntity(HttpStatus.FORBIDDEN);
                }
                if (casUser.isLocked()) {
                    //���� 423
                    result= new ResponseEntity(HttpStatus.LOCKED);
                }
                if (casUser.isExpired()) {
                    //���� 428
                    result= new ResponseEntity(HttpStatus.PRECONDITION_REQUIRED);
                }
            } else {
                //������ 404
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
        //�ɹ�����json
        //������������ͻ���
        this.writeJSON(response, result);
    }

    /**
     * ��������ͷ��ȡ�û���������
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
        //���������ʱ����ͨ�����û���Ϣ��������ͷauthorization�У�����ͨ��Basic��֤��ʽ����
        String authorization = httpHeaders.getFirst ("Authorization");//���õ� Basic Base64(�û���:����)
        String baseCredentials = authorization.split(" ")[1];
        String  usernamePassword = new String(Base64.decodeBase64(baseCredentials));//�û���:����
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
            //�趨����
            response.setCharacterEncoding("UTF-8");
            //��ʾ��json���͵�����
            response.setContentType("application/json");
            //��ȡPrintWriter ���������д����
            PrintWriter writer = response.getWriter();

            //���ǲ����������ݵ�ʱ���趨״̬
            if(object instanceof ResponseEntity) {
                HttpStatus status = ((ResponseEntity) object).getStatusCode();
                //�趨״̬���
                response.setStatus(status.value());
            }

            ObjectMapper mapper = new ObjectMapper(); //ת����
            //��ȡ��ת�����JSON ����
            String json = mapper.writeValueAsString(object);
            //д���ݵ������
            writer.write(json);
            //ˢ�£���ʾȫ��д�꣬�ѻ������ݶ�ˢ��ȥ
            writer.flush();

            //�ر�writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
