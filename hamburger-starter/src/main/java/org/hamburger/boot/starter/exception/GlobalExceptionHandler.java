package org.hamburger.boot.starter.exception;

import lombok.extern.slf4j.Slf4j;
import org.hamburger.boot.core.dto.BaseResponse;
import org.hamburger.boot.core.exception.HamException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(HamException.class)
    @ResponseBody
    public BaseResponse hamExceptionHandler(HttpServletRequest request, HamException e) {
        log.error("业务异常：", e);
        return BaseResponse.buildFailure(e.getErrCode(), e.getErrMsg());
    }
}
