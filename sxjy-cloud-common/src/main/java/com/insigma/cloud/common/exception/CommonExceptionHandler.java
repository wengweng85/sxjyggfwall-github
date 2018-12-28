package com.insigma.cloud.common.exception;

import com.insigma.cloud.common.dto.AjaxReturnMsg;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(Exception.class)
    AjaxReturnMsg exception(Exception e) {
        return AjaxReturnMsg.error(e);
    }
}
