package org.hamburger.boot.example.simple.common;

import org.hamburger.boot.core.dto.ICodeEnum;

public enum ResultCode implements ICodeEnum {
    SYS_ERROR("1001", "业务异常");

    private final String errCode;

    private final String errMsg;

    ResultCode(String errCode, String errMsg) {
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
