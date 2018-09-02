package com.xiaozhi.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RunException extends RuntimeException {
    private static final long serialVersionUID = 8047908572920298917L;
    private static Log logger = LogFactory.getLog(RunException.class);
    private ErrorCode code;

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }


    public RunException(String message) {
        super(message);
        this.code = ErrorCode.NoneError;
        code.setMsg(message);
    }

    public RunException(ErrorCode code) {
        super(code.getMsg());
        this.code = code;
    }

    public RunException(ErrorCode code, Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = code;
    }

    public RunException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        code.setMsg(message);
    }
    public RunException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code =ErrorCode.NoneError;
        this.code.setMsg(message);
        this.code.setCode(code);
    }

    /**
     * 传递异常信息及异常对象
     *
     * @param message
     * @param cause
     */
    public RunException(String message, Throwable cause) {
        super(message, cause);
        this.code = ErrorCode.NoneError;
        code.setMsg(message);
    }

    /**
     * 直接传递异常对象
     *
     * @param cause
     */
    public RunException(Throwable cause) {
        super(cause);
        this.code = ErrorCode.NoneError;
    }

}
