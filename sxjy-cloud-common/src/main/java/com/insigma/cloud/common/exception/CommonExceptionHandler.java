package com.insigma.cloud.common.exception;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * exception
     * @param e
     * @return
     */
    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    AjaxReturnMsg exception(Exception e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return AjaxReturnMsg.error500();
    }

    /**
     * appexception
     * @param e
     * @return
     */
    @ExceptionHandler(value=AppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    AjaxReturnMsg appexception(AppException e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return AjaxReturnMsg.error500();
    }
}
