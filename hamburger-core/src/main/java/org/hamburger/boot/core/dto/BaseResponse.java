package org.hamburger.boot.core.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private String errCode;

    private String errMsg;

    private boolean success = true;

    private T data;

    public static BaseResponse<?> buildSuccess() {
        return new BaseResponse<>();
    }

    public static BaseResponse<?> buildFailure(ICodeEnum baseCode) {
        BaseResponse<?> response = new BaseResponse<>();
        response.setSuccess(false);
        response.setErrCode(baseCode.getErrCode());
        response.setErrMsg(baseCode.getErrMsg());
        return response;
    }

    public static <T> BaseResponse<T> of(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
}
