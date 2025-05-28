package com.example.exception;

import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务器异常", e);
        return Result.error("服务器异常");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("业务异常", e);
        String message = e.getMessage();
        int duplicateEntry = message.indexOf("Duplicate entry");
        String errorMsg = message.substring(duplicateEntry);
        String[] arr = errorMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }
}
