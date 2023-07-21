package com.youquan.exception;

import com.youquan.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Fengyouquan
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return Result.error("系统繁忙，请稍后再试");
    }

    @ExceptionHandler(TliasException.class)
    public Result<?> handleClassException(TliasException tliasException) {
        log.error(tliasException.getMessage(), tliasException);
        return Result.error(tliasException.getMessage());
    }
}
