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

    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    AjaxReturnMsg exception(Exception e) {
        logger.debug(e.getMessage());
        return AjaxReturnMsg.error(e);
    }

    @ExceptionHandler(value=AppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    AjaxReturnMsg appexception(Exception e) {
        logger.debug(e.getMessage());
        return AjaxReturnMsg.error(e);
    }
}
