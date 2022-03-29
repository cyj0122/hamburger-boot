package org.hamburger.boot.core.dto;

import lombok.Data;

@Data
@SuppressWarnings("rawtypes")
public class BaseResponse<T> {

    private String errCode;

    private String errMsg;

    private boolean success = false;

    private T data;

    public static BaseResponse buildSuccess() {
        return new BaseResponse();
    }

    public static BaseResponse buildFailure(ICodeEnum baseCode) {
        BaseResponse response = new BaseResponse<>();
        response.setSuccess(false);
        response.setErrCode(baseCode.getErrCode());
        response.setErrMsg(baseCode.getErrMsg());
        return response;
    }

    public static BaseResponse buildFailure(String errCode, String errMsg) {
        BaseResponse response = new BaseResponse<>();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMsg(errMsg);
        return response;
    }

    public static <T> BaseResponse<T> of(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
}
