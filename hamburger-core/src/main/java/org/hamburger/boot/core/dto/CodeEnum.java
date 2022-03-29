package org.hamburger.boot.core.dto;

public enum CodeEnum implements ICodeEnum {
    SYS_ERROR("500", "系统异常"),
    CLIENT_ERROR("400", "客户端请求异常");

    private final String errCode;

    private final String errMsg;

    CodeEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public String getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }
}
