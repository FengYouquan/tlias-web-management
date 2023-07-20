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

    @ExceptionHandler(ClassException.class)
    public Result<?> handleClassException(ClassException classException) {
        log.error(classException.getMessage(), classException);
        return Result.error(classException.getMessage());
    }
}
