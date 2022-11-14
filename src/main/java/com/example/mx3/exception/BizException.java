package com.example.mx3.exception;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpStatus;

public class BizException extends RuntimeException {

    private final int code;

    public BizException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST.value();
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.code = HttpStatus.BAD_REQUEST.value();
    }

    public BizException(String message, Object... args) {
        super(StrUtil.format(message, args));
        this.code = HttpStatus.BAD_REQUEST.value();
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(int code, String message, Object... args) {
        super(StrUtil.format(message, args));
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
