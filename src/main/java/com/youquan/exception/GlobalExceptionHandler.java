package com.youquan.exception;

import com.youquan.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Result<?> exception(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage(), runtimeException);
        return Result.error(runtimeException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> exception(Exception exception) {
        log.error(exception.getMessage(), exception);
        return Result.error("系统繁忙，请稍后再试");
    }
}
