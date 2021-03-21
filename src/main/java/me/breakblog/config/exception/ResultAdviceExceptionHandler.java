package me.breakblog.config.exception;

import lombok.extern.slf4j.Slf4j;
import me.breakblog.util.Result;
import me.breakblog.util.ResultEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ResultAdviceExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public Result handleCommonException(CommonException e) {
        log.error(e.getMessage(), e);
        return Result.failure(e.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    public Result handleTokenException(TokenException e) {
        log.error(e.getMessage(), e);
        return Result.failure(ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getMsg());
    }

}
