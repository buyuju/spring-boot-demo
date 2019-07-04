package com.example.demo.exception;

import com.example.demo.common.DemoRespCode;
import com.example.demo.model.base.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@ApiIgnore
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ClientException.class)
    public Object clientException(HttpServletRequest request, HttpServletResponse response, ClientException e)  {
        logger.error("----------------- ClientParameterException -------------------");
        logger.error("code:{}, message:{}, extra: {}", e.getCode(), e.getMessage(), e.getExtra());
        return ResultJson.fail(e.getCode(), e.getMessage(), e.getExtra());
    }

    @ExceptionHandler(value = ServerException.class)
    public Object exception(HttpServletRequest request, HttpServletResponse response, ServerException e)  {
        logger.error("----------------- ServerException -------------------");
        logger.error("code:{}, message:{}", e.getCode(), e.getMessage());
        response.setStatus(DemoRespCode._066666.getCode());
        return ResultJson.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    public Object serverException(HttpServletRequest request, HttpServletResponse response, Throwable e)  {
        logger.error("----------------- Throwable -------------------");
        logger.error(e.getMessage(), e);
        response.setStatus(DemoRespCode._066666.getCode());
        return ResultJson.fail(DemoRespCode._066666.getCode(), e.getMessage());
    }

}
