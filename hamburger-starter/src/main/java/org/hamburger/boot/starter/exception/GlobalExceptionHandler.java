package org.hamburger.boot.starter.exception;

import lombok.extern.slf4j.Slf4j;
import org.hamburger.boot.core.dto.BaseResponse;
import org.hamburger.boot.core.dto.CodeEnum;
import org.hamburger.boot.core.exception.HamException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse parameterExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("请求验证失败：", e);
        if (e.getBindingResult().hasErrors()) {
            return BaseResponse.buildFailure(
                    CodeEnum.CLIENT_ERROR.getErrCode(),
                    e.getAllErrors().get(0).getDefaultMessage());
        }
        return BaseResponse.buildFailure(CodeEnum.CLIENT_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse otherExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("系统异常：", e);
        return BaseResponse.buildFailure(CodeEnum.SYS_ERROR);
    }
}
