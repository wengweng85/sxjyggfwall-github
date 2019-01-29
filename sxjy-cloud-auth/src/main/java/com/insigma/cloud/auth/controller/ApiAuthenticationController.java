package com.insigma.cloud.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insigma.cloud.auth.service.ApiAuthenticationService;
import com.insigma.cloud.common.dto.AjaxReturnMsg;
import com.insigma.cloud.common.dto.CasUser;
import com.insigma.cloud.common.exception.AppException;
import com.insigma.cloud.common.utils.JSONUtils;
import com.insigma.mvc.model.AccessToken;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Api(description = "�û���֤")
@RestController
public class ApiAuthenticationController {

    Logger LOGGER= LoggerFactory.getLogger(ApiAuthenticationController.class);

    @Resource
    private ApiAuthenticationService apiAuthenticationService;


    /**
     * �����û��������ȡ��֤token
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "��ȡ��֤token", notes = "��ȡ��֤token",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/token",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxReturnMsg gettoken(@RequestBody SUser suser) throws Exception {
        AccessToken accessToken= apiAuthenticationService.getToken(suser.getUsername(),suser.getPassword());
        if(accessToken!=null){
            return AjaxReturnMsg.success(accessToken);
        }else{
            return AjaxReturnMsg.fail("��֤ʧ��");
        }
    }


    /**
     * �����¼rest
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "cas�����¼", notes = "cas�����¼", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/cas" , produces = MediaType.APPLICATION_JSON_VALUE)
    public void castoken(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("Rest api login.");
        LOGGER.debug("request headers: {}", httpHeaders);
        Object result = null;
        CasUser casUser = null;
        SUser sUser=null;
        try {
            casUser = obtainUserFormHeader(httpHeaders);
            if (casUser != null) {
                sUser= apiAuthenticationService.getUser(casUser.getUsername(), casUser.getPassword());
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
    private CasUser obtainUserFormHeader(HttpHeaders httpHeaders) throws UnsupportedEncodingException,AppException {
        /**
         *
         * This allows the CAS server to reach to a remote REST endpoint via a POST for verification of credentials.
         * Credentials are passed via an Authorization header whose value is Basic XYZ where XYZ is a Base64 encoded version of the credentials.
         */
        //���������ʱ����ͨ�����û���Ϣ��������ͷauthorization�У�����ͨ��Basic��֤��ʽ����
        String authorization = httpHeaders.getFirst ("Authorization");//���õ� Basic Base64(�û���:����)
        if(null==authorization){
            throw new AppException("�Ƿ�����,����ͷ��û����֤��Ϣ");
        }
        String baseCredentials = authorization.split(" ")[1];
        String usernamePassword = new String(Base64.decodeBase64(baseCredentials));//�û���:����
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
