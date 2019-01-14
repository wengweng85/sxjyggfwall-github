package com.insigma.cloud.auth.controller.cas;

import com.insigma.cloud.auth.service.token.ApiTokenService;
import com.insigma.cloud.common.dto.CasUser;
import com.insigma.cloud.common.rsa.MD5Util;
import com.insigma.mvc.model.AccessToken;
import com.insigma.mvc.model.SUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Api(description = "cas�����¼")
@RestController
public class CasTokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasTokenController.class);

    @Resource
    private ApiTokenService apiLoginService;

    /**
     * �����¼rest
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "cas�����¼", notes = "cas�����¼")
    @RequestMapping(value = "/cas", method = RequestMethod.POST)
    public Object castoken(@RequestHeader HttpHeaders httpHeaders) throws Exception {
        LOGGER.info("Rest api login.");
        LOGGER.debug("request headers: {}", httpHeaders);
        CasUser casUser = null;
        AccessToken accessToken=null;
        try {
            casUser = obtainUserFormHeader(httpHeaders);
            if (casUser != null) {
                accessToken=apiLoginService.getToken(casUser.getUsername(), MD5Util.MD5Encode(casUser.getPassword()));
                if(accessToken==null){
                    //���벻ƥ��
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }

                if (casUser.isDisable()) {
                    //���� 403
                    return new ResponseEntity(HttpStatus.FORBIDDEN);
                }
                if (casUser.isLocked()) {
                    //���� 423
                    return new ResponseEntity(HttpStatus.LOCKED);
                }
                if (casUser.isExpired()) {
                    //���� 428
                    return new ResponseEntity(HttpStatus.PRECONDITION_REQUIRED);
                }
            } else {
                //������ 404
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
            new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("[{}] login is ok", casUser.getUsername());
        HashMap map=new HashMap();
        map.put("token",accessToken.getToken());
        casUser.setAttributes(map);
        //�ɹ�����json
        return casUser;
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

}
