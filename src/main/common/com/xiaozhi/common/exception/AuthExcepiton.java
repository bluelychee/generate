package com.xiaozhi.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AuthExcepiton extends RuntimeException {
    private static Log logger = LogFactory.getLog(AuthExcepiton.class);

    private ErrorCode code;

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public AuthExcepiton(String msg) {
        super(msg);
        this.code = ErrorCode.NoneError;
        code.setMsg(msg);
    }

    public AuthExcepiton(ErrorCode code, String msg) {
        super(msg);
        this.code = code;
        code.setMsg(msg);
    }

    public AuthExcepiton(ErrorCode code) {
        super(code.getMsg());
        this.code = code;
    }

    public AuthExcepiton(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        code.setMsg(message);

    }
    public AuthExcepiton(String code, String message, Throwable cause) {
        super(message, cause);
        this.code =ErrorCode.NoneError;
        this.code.setMsg(message);
        this.code.setCode(code);
    }
    public AuthExcepiton(Throwable cause) {
        super(cause);
        this.code = ErrorCode.NoneError;
    }
}
