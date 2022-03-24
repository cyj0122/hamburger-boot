package org.hamburger.boot.core.exception;

import org.hamburger.boot.core.dto.ICodeEnum;

public class HamException extends RuntimeException {

    private final ICodeEnum codeEnum;

    public HamException(ICodeEnum codeEnum) {
        super(codeEnum.getErrMsg());
        this.codeEnum = codeEnum;
    }

    public HamException(ICodeEnum codeEnum, Throwable e) {
        super(codeEnum.getErrMsg(), e);
        this.codeEnum = codeEnum;
    }

    public String getErrCode() {
        return codeEnum.getErrCode();
    }

    public String getErrMsg(){
        return codeEnum.getErrMsg();
    }
}
