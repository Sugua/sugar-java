package com.sugar.blog.common.exception;

import com.sugar.blog.common.base.Response;
import com.sugar.blog.common.base.ResponseCode;
import com.sugar.blog.common.base.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 系统异常处理
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData<Object> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            log.error("404",e);
            /**
             * 404异常
             */
            return Response.fail(e.getMessage(), ResponseCode.NOT_FOUND_CODE);
        } else {
            log.error("异常", e);
            return Response.fail(e.getMessage(), ResponseCode.SERVER_ERROR_CODE);
        }
    }

    /**
     * 自定义异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = SugarException.class)
    @ResponseBody
    public ResponseData<Object> customerErrorHandler(HttpServletRequest request, SugarException e) throws Exception {
        log.error("异常抛出",e);
        return Response.fail(e.getMessage(), e.getCode());

    }
}