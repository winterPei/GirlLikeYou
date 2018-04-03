package com.winterpei.girllikeyou.http.exception;

/**
 * 非法请求
 */
public class RequestIllegalException extends RuntimeException {
    public RequestIllegalException() {
    }

    public RequestIllegalException(String message) {
        super(message);
    }
}
