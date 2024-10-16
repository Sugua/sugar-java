package com.sugar.blog.common.exception;

import com.sugar.blog.common.base.ResponseCode;
import lombok.Getter;

@Getter
public class SugarException extends RuntimeException {
    private static final long serialVersionUID = 1156039841605240094L;

    private ResponseCode code;

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public SugarException(String message) {
        super(message);
    }

    public SugarException(String message, ResponseCode code) {

        super(message);

        this.code = code;

    }
}
