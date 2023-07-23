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

    /**
     * handleException 方法用于处理 Exception 类型的异常
     *
     * @param exception 捕获到的异常对象
     * @return 返回一个包含错误信息的 Result 对象，提示系统繁忙，请稍后再试
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return Result.error("系统繁忙，请稍后再试");
    }

    /**
     * handleClassException 方法用于处理 TliasException 类型的异常
     *
     * @param tliasException 捕获到的 TliasException 对象
     * @return 返回一个包含错误信息的 Result 对象，具体错误信息为捕获到的 TliasException 的消息
     */
    @ExceptionHandler(TliasException.class)
    public Result<?> handleClassException(TliasException tliasException) {
        log.error(tliasException.getMessage(), tliasException);
        return Result.error(tliasException.getMessage());
    }
}

